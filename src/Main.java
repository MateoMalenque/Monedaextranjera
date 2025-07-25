import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CONVERSOR DE MONEDAS ===");

        boolean continuar = true;

        while (continuar) {
            System.out.print("Moneda origen (USD, ARS, EUR, BRL): ");
            String origen = scanner.nextLine().toUpperCase();

            System.out.print("Moneda destino (USD, ARS, EUR, BRL): ");
            String destino = scanner.nextLine().toUpperCase();

            System.out.print("Cantidad a convertir: ");
            double cantidad;

            try {
                cantidad = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresaste una cantidad inválida.");
                continue;
            }

            try {
                double tasa = ExchangeService.obtenerTasa(origen, destino);
                double resultado = cantidad * tasa;

                System.out.printf("✅ %.2f %s son %.2f %s%n", cantidad, origen, resultado, destino);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\n¿Querés hacer otra conversión? (Si/No): ");
            String respuesta = scanner.nextLine().trim().toUpperCase();
            if (!respuesta.equalsIgnoreCase("S") &&
                    !respuesta.equalsIgnoreCase("SI") &&
                    !respuesta.equalsIgnoreCase("SÍ")) {
                continuar = false;
            }

            System.out.println(); // Salto de línea
        }

        System.out.println("Gracias por usar el conversor. ¡Hasta la próxima!");
    }
}

