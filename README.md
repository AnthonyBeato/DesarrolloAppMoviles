# DesarrolloAppMoviles
Repositorio donde se almacenarán todos los proyectos de la materia de Desarrollo de Aplicaciones Móviles

Reporte - Anthony Beato Abreu - 1014-1684

En esta práctica me sentí mucho mas fluyente a comparación de la tarea pasada... Pude trabajar con tiempo y más tranquilo pudiendo cumplir lo necesitado de la tarea y una que otra funcionalidad extra. Personalmente me tomó un tiempo entender el uso de ViewModel, LiveData y Room, por lo que fue lo que más me costó implementar. Lo bueno fue que una vez implementado, ya que seguí las buenas prácticas recomendadas por la documentación de android pude encapsular las responsabilidades en cada parte correspondiente, por lo que me facilitó ampliamente la escalabilidad de la app, permitiendome modificar y agregar detalles.
Ahora, referente a las funcionalidades extra pues decidí implementar un detalle que quería integrar en la tarea pasada, el cual, era la implementación de un sistema de importancia en las tareas. Esto se maneja al momento de crear cualquier tarea, pudiendo asignar una importancia Alta, media, baja o nula a cualquiera de las tareas. Para enseñarlo visualmente pues decidí hacerlo mediante un imageButton el cual permite poder ciclar entre cada una de las importancias para actualizar la tarea en cuestión. Esto me hizo crear una nueva versión de la Base de Datos al aumentar las columnas de la tabla de tareas, por lo que fue algo que hice por primera vez.
Aparte de esto, pues pude agregar detalles visuales para mostrar las tareas al momento de marcarlas como completada. Permitiendo que cuando se clickee una tarea completada, pues aparte de que se tache, se baje la opacidad de dicho item, y además, ordenar la lista de tareas poniendo las completadas al final. 
También implementé de que si ya no hay tareas en la lista, pues se oculte el RecyclerView y se muestre un TextView que notifique al usuario que ya la lista está vacía, y que debe agregar tareas a su día. 

Por lo que esta tarea me gustó mucho más que la anterior, permitiendome explorar más las funcionalidades de Android y disfrutando el desarrollarla. 
