package AvanceReto;
import java.util.Scanner;
import java.util.Random;

public class SimuladorViajeInterplanetario {
    static Scanner scanner = new Scanner(System.in);
    static String[] planetas = {"Mercurio", "Venus", "Marte", "Júpiter", "Saturno", "Urano", "Neptuno", "Plutón"};
    static double[] distancias = {77, 261, 54.6, 588, 1200, 2870, 4500, 5900}; // Distancias en millones de km
    static String[] naves = {"Voyager 2", "Pionner 10", "New Horizons"};
    static double[] velocidades = {15.46, 12.06, 15.77}; // Velocidades de las naves en km/s

    static String planetaSeleccionado = "";
    static String naveSeleccionada = "";
    static double velocidadSeleccionada = 0.0;
    static double distanciaKm = 0.0;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    seleccionarPlaneta();
                    break;
                case 2:
                    seleccionarNave();
                    break;
                case 3:
                    calcularDistanciaYRecursos();
                    break;
                case 4:
                    iniciarSimulacion();
                    break;
                case 0:
                    System.out.println("Gracias por usar el simulador. ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, inténtalo de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    // Muestra el menú principal al usuario
    public static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Seleccionar un planeta de destino");
        System.out.println("2. Seleccionar una nave espacial");
        System.out.println("3. Calcular distancia y gestionar recursos");
        System.out.println("4. Iniciar simulación del viaje");
        System.out.println("0. Salir");
        System.out.print("Por favor elige una opción: ");
    }

    public static void seleccionarPlaneta() {
        System.out.println("\n--- PLANETAS DISPONIBLES ---");
        for (int i = 0; i < planetas.length; i++) {
            System.out.println((i + 1) + ". " + planetas[i]);
        }
        System.out.print("Selecciona el número del planeta: ");
        int opcionPlaneta = scanner.nextInt();

        if (opcionPlaneta >= 1 && opcionPlaneta <= planetas.length) {
            planetaSeleccionado = planetas[opcionPlaneta - 1];
            distanciaKm = distancias[opcionPlaneta - 1] * 1_000_000; // Convertimos a kilómetros
            System.out.println("Has seleccionado el planeta: " + planetaSeleccionado);
        } else {
            System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    public static void seleccionarNave() {
        System.out.println("\n--- NAVES DISPONIBLES ---");
        for (int i = 0; i < naves.length; i++) {
            System.out.println((i + 1) + ". " + naves[i]);
        }
        System.out.print("Selecciona el número de la nave: ");
        int opcionNave = scanner.nextInt();

        if (opcionNave >= 1 && opcionNave <= naves.length) {
            naveSeleccionada = naves[opcionNave - 1];
            velocidadSeleccionada = velocidades[opcionNave - 1] * 3600; // Convertimos a km/h
            System.out.println("Has seleccionado la nave: " + naveSeleccionada + " con velocidad de " + velocidadSeleccionada + " km/h.");
        } else {
            System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    public static void calcularDistanciaYRecursos() {
        if (planetaSeleccionado.isEmpty() || naveSeleccionada.isEmpty()) {
            System.out.println("\nPor favor, selecciona un planeta y una nave primero.");
            return;
        }

        double tiempoHoras = distanciaKm / velocidadSeleccionada;
        double tiempoDias = tiempoHoras / 24;

        double combustiblePorKm = 0.1; // Combustible por kilómetro en toneladas
        double oxigenoPorPersonaPorHora = 0.5; // Oxígeno en kg por persona por hora

        System.out.print("Ingresa la cantidad de pasajeros: ");
        int pasajeros = scanner.nextInt();

        double combustibleNecesario = distanciaKm * combustiblePorKm;
        double oxigenoNecesario = pasajeros * oxigenoPorPersonaPorHora * tiempoHoras;

        System.out.println("\n--- DATOS DEL VIAJE ---");
        System.out.println("Distancia: " + distanciaKm + " km.");
        System.out.println("Tiempo estimado: " + tiempoDias + " días.");
        System.out.println("Combustible necesario: " + combustibleNecesario + " toneladas.");
        System.out.println("Oxígeno necesario: " + oxigenoNecesario + " kg.");
    }

    public static void iniciarSimulacion() {
        if (planetaSeleccionado.isEmpty() || naveSeleccionada.isEmpty()) {
            System.out.println("\nPor favor, selecciona un planeta y una nave primero.");
            return;
        }

        System.out.println("\nIniciando simulación del viaje hacia " + planetaSeleccionado + "...");
        double progreso = 0.0;
        Random random = new Random();

        while (progreso < 100) {
            progreso += 10; // Incremento del progreso en pasos del 10%
            System.out.print("\r["); // Inicio de la barra de carga
            for (int i = 0; i < 50; i++) { // Longitud de la barra (50 bloques)
                if (i < progreso / 2) {
                    System.out.print("#"); // Parte completada
                } else {
                    System.out.print(" "); // Parte restante
                }
            }
            System.out.print("] " + (int) progreso + "%"); // Porcentaje completado

            // Simular eventos aleatorios
            if (random.nextInt(100) < 20) { // 20% de probabilidad de evento
                int evento = random.nextInt(3);
                switch (evento) {
                    case 0:
                        System.out.println("\n¡Alerta! Asteroides detectados. Cambio de rumbo necesario.");
                        break;
                    case 1:
                        System.out.println("\n¡Fallo en el sistema! Reparaciones en proceso.");
                        break;
                    case 2:
                        System.out.println("\nEl viaje avanza sin problemas.");
                        break;
                }
            }

            try {
                Thread.sleep(1000); // Pausa entre actualizaciones de la barra
            } catch (InterruptedException e) {
                System.out.println("Error en la simulación.");
            }
        }

        System.out.println("\n\n¡Has llegado a " + planetaSeleccionado + " con éxito!");
    }
}
