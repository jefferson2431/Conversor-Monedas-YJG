import modelo.Conversion;
import servicio.ConversorMonedas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorMonedas conversor = new ConversorMonedas();
        List<Conversion> historial = new ArrayList<>();

        System.out.println("******************************************");
        System.out.println("  Bienvenido/a al Conversor de Moneda");
        System.out.println("******************************************");

        boolean continuar = true;

        while (continuar) {
            System.out.println("""
                    
                    1) Dólar           =>  Peso argentino
                    2) Peso argentino  =>  Dólar
                    3) Dólar           =>  Real brasileño
                    4) Real brasileño  =>  Dólar
                    5) Dólar           =>  Peso colombiano
                    6) Peso colombiano =>  Dólar
                    7) Ver historial de conversiones
                    8) Salir
                    """);

            System.out.print("Elija una opción válida: ");
            int opcion;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Por favor ingrese un número válido.");
                continue;
            }

            if (opcion == 8) {
                continuar = false;
                continue;
            }

            if (opcion == 7) {
                mostrarHistorial(historial);
                continue;
            }

            String origen;
            String destino;

            switch (opcion) {
                case 1 -> { origen = "USD"; destino = "ARS"; }
                case 2 -> { origen = "ARS"; destino = "USD"; }
                case 3 -> { origen = "USD"; destino = "BRL"; }
                case 4 -> { origen = "BRL"; destino = "USD"; }
                case 5 -> { origen = "USD"; destino = "COP"; }
                case 6 -> { origen = "COP"; destino = "USD"; }
                default -> {
                    System.out.println("⚠ Opción no válida. Elija entre 1 y 8.");
                    continue;
                }
            }

            System.out.print("Ingrese el monto a convertir: ");
            double monto;

            try {
                monto = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Por favor ingrese un monto numérico válido.");
                continue;
            }

            try {
                double resultado = conversor.convertir(origen, destino, monto);
                System.out.printf("%nEl valor %.2f [%s] equivale a %.2f [%s]%n",
                        monto, origen, resultado, destino);

                historial.add(new Conversion(origen, destino, monto, resultado));

            } catch (Exception e) {
                System.out.println("⚠ Error al conectar con la API: " + e.getMessage());
            }
        }

        System.out.println("\n¡Hasta luego!");
        scanner.close();
    }

    private static void mostrarHistorial(List<Conversion> historial) {
        if (historial.isEmpty()) {
            System.out.println("\nNo hay conversiones registradas aún.");
            return;
        }
        System.out.println("\n--- Historial de conversiones ---");
        historial.forEach(System.out::println);
        System.out.println("---------------------------------");
    }
}