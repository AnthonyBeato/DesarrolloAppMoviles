package com.example.to_do_list_enhanced.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.to_do_list_enhanced.dao.TaskDao;
import com.example.to_do_list_enhanced.entities.Task;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {


    public abstract TaskDao taskDao();


    private static volatile TaskRoomDatabase INSTANCE; //Crear una instancia (patrón singleton)

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);  // Se puso un número de hilos para correr las operaciones
                                                            // en la base de datos de manera asincronica en un hilo en el fondo

    //Creará la base de datos la primera vez que se accese
    public static TaskRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (TaskRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "task_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            //Fragmento para pruebas:
            //Para mantener la data mientras la app se reinicia
            //se debe comentar el siguiente bloque:
            databaseWriteExecutor.execute(() ->{
                //Popular la base de datos
                TaskDao dao = INSTANCE.taskDao();
                dao.deleteAll();

                Task task = new Task("Tarea 1", "Alta");
                dao.insert(task);
                task = new Task("Tarea 2", "Media");
                dao.insert(task);
                task = new Task("Tarea 3", "Baja");
                dao.insert(task);
                task = new Task("Tarea 4", "Nula");
                dao.insert(task);
            });
        }
    };

    // Nuevo método para eliminar la base de datos
    public static void deleteInstance(Context context) {
        INSTANCE = null; // Establece la instancia a null para que se cree una nueva
        context.deleteDatabase("task_database"); // Elimina la base de datos del almacenamiento local
    }
}
