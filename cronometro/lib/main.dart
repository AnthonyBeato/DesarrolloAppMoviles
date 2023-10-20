//Hecho por: Anthony Beato Abreu
//ID: 1014-1684

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

void main(){
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  static final ValueNotifier<ThemeMode> themeNotifier =
    ValueNotifier(ThemeMode.light);

  const MyApp({super.key});
  
  @override
  Widget build(BuildContext context) {

    return ValueListenableBuilder<ThemeMode>(
      valueListenable: themeNotifier,
      builder: (_, ThemeMode currentMode, __){
        return MaterialApp(
            debugShowCheckedModeBanner: false,
            title: 'Stopwatch+',

            theme: ThemeData(
              useMaterial3: true,
              colorScheme: ColorScheme.fromSeed(seedColor: Colors.orange),
            ),
            darkTheme: ThemeData.dark(
              useMaterial3: true,
            ).copyWith(
              colorScheme: ColorScheme.fromSwatch(primarySwatch: Colors.orange),
            ),

            themeMode: currentMode,
            home: _HomePage()
        );
      },
    );
  }
}

class _HomePage extends StatefulWidget {
  const _HomePage({super.key});

  @override
  State<_HomePage> createState() => _HomePageState();
}
//Aqui es donde se tendrá el NavigationBar
class _HomePageState extends State<_HomePage> {
  int currentPageIndex = 0;

  @override
  Widget build(BuildContext context) {
    // return _StopWatch();

    return Scaffold(
      bottomNavigationBar: NavigationBar(
        onDestinationSelected: (int index){
          setState(() {
            currentPageIndex = index;
          });
        },
        selectedIndex: currentPageIndex,
        destinations: const <Widget> [
          NavigationDestination(
            selectedIcon: Icon(Icons.timer),
            icon: Icon(Icons.timer_outlined),
            label: 'Inicio',
          ),
          NavigationDestination(
            selectedIcon: Icon(Icons.settings),
            icon: Icon(Icons.settings_outlined),
            label: 'Configuración',
          ),
        ],
      ),
      body:  <Widget>[
        Container(
          child: const _StopWatchPage(),
        ),
        Container(
          child: const _SettingsPage(),
        )
      ][currentPageIndex],
    );
  }
}

class _StopWatchPage extends StatefulWidget {
  const _StopWatchPage({super.key});

  @override
  State<_StopWatchPage> createState() => _StopWatchPageState();
}

class _StopWatchPageState extends State<_StopWatchPage> {
  Duration duration = Duration();
  Timer? timer;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    //startTimer();
    reset();
  }

  void addTime() {
    final addSeconds = 1;
    setState(() {
      final seconds = duration.inSeconds + addSeconds;

      duration = Duration(seconds: seconds);
    });
  }

  void startTimer({bool resets = true}) {
    if (resets){
      reset();
    }

    timer = Timer.periodic(Duration(seconds: 1), (_) => addTime());
  }

  void stopTimer({bool resets = true}) {
    if (resets){
      reset();
    }

    setState(() {
      timer?.cancel();
    });
  }

  void reset() {
    setState(() {
      duration = const Duration();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
                height: 350,
                width: 350,
                child: const Image(image: AssetImage('images/stopwatch.png'))),
            buildTime(),
            const SizedBox(height: 10),
            buildButtons(),
          ],
        ),
      ),
    );
  }

  Widget buildButtons() {
    final isRunning = timer == null ? false : timer!.isActive;
    final isCompleted = duration.inSeconds == 0;

    IconData iconStart = Icons.play_arrow_rounded;
    IconData iconPause = Icons.pause_rounded;
    IconData iconRestart = Icons.restart_alt_rounded;

    return isRunning || !isCompleted
        ? Row(
        mainAxisAlignment: MainAxisAlignment.spaceAround,
        children: [
          ElevatedButton.icon(
            onPressed: (){
              stopTimer();
        },
            icon: Icon(iconRestart),
            label: Text('Reiniciar')),

        FilledButton.icon(
            onPressed: (){
            if (isRunning){
              stopTimer(resets: false);
            } else{
              startTimer(resets: false);
            }
        },
            icon: isRunning ? Icon(iconPause) : Icon(iconStart),
            label: isRunning ? Text('Pausar') : Text('Continuar'))
      ],
    )
        : FilledButton.icon(onPressed: (){
            startTimer();
          },
          icon: Icon(iconStart),
          label: Text('Iniciar'));
  }

  Widget buildTime() {
    final theme = Theme.of(context);
    String twoDigits(int n) => n.toString().padLeft(2, '0');
    final hours = twoDigits(duration.inHours);
    final minutes = twoDigits(duration.inMinutes.remainder(60));
    final seconds = twoDigits(duration.inSeconds.remainder(60));

      final style = GoogleFonts.lato( // Fuente de Google Fonts
        textStyle: theme.textTheme.displayLarge!.copyWith(
          color: theme.colorScheme.onPrimary, // Establecer el color del texto
        ),
    );


    return Card(
      color: theme.colorScheme.primary,
      child: Padding(
        padding: const EdgeInsets.all(30.0),
        child: Text(
          '$hours:$minutes:$seconds',
          style: style,
        ),
      ),
    );
  }
}

class _SettingsPage extends StatefulWidget {
  const _SettingsPage({super.key});

  @override
  State<_SettingsPage> createState() => _SettingsPageState();
}

class _SettingsPageState extends State<_SettingsPage> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
        body: SafeArea(child: Padding(
          padding: EdgeInsets.all(16.0),
          child: Column(
            children: [
              Row(
                children: [
                  Text('Modo oscuro'),
                  SizedBox(width: 10),
                  SwitchExample(),
                ],

              ),
            ],
          ),
      ),
      )
    );
  }
}

class SwitchExample extends StatefulWidget {
  const SwitchExample({super.key});

  @override
  State<SwitchExample> createState() => _SwitchExampleState();
}

class _SwitchExampleState extends State<SwitchExample> {
  // Inicializa la variable light según el valor del ThemeMode.
  late bool light;

  @override
  void initState() {
    super.initState();
    //Verifica si el darkMode está puesto o no.
    light = MyApp.themeNotifier.value == ThemeMode.dark;
  }


  @override
  Widget build(BuildContext context) {
    return Switch(
      value: light,
      activeColor: Colors.orange,
      onChanged: (bool value) {
        // Verifica si el nuevo valor es diferente del valor actual de light
        if (value != light) {
          setState(() {
            // Actualiza la variable light y el ThemeMode a través del notifier
            light = value;
            MyApp.themeNotifier.value = value ? ThemeMode.dark : ThemeMode.light;
          });
        }
      },
    );
  }
}








