import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class BuscarPalabrasConPuntaje {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("BUSCADOR DE PALABRAS USUALMENTE USADAS EN EL PISHING ");
        System.out.println("Instruccion general: El programa no continuara hasta ingresar una palabra de la lista ");
        System.out.print("Ingrese la cantidad de palabras que desea buscar: ");
        int cantidadPalabras = scanner.nextInt();
        scanner.nextLine(); // Consumimos el salto de línea después del número.
        Map<String, Integer> palabrasYPuntajes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("palabras.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");
                if (partes.length == 2) {
                    String palabra = partes[0].toLowerCase(); 
                    int puntaje = Integer.parseInt(partes[1]);
                    palabrasYPuntajes.put(palabra, puntaje);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
            e.printStackTrace();
            return;
        }
        int sumaPuntajes = 0;
        Map<String, Integer> palabrasEncontradas = new HashMap<>();
        for (int i = 0; i < cantidadPalabras; i++) {
            System.out.print("Ingrese la palabra #" + (i + 1) + ": ");
            String palabra = scanner.nextLine().toLowerCase(); // Convertir la palabra ingresada a minúsculas
            while (!palabrasYPuntajes.containsKey(palabra)) {
                System.out.println("La palabra " + palabra + " no fue encontrada. Intente nuevamente.");
                System.out.print("Ingrese la palabra #" + (i + 1) + ": ");
                palabra = scanner.nextLine().toLowerCase(); // Convertir la palabra ingresada a minúsculas
            }
            int puntajeEncontrado = palabrasYPuntajes.get(palabra);
            System.out.println("La palabra encontrada es " + palabra + " y el puntaje es " + puntajeEncontrado);
            sumaPuntajes += puntajeEncontrado;

            // Guardar la palabra encontrada y su puntaje para mostrar en la tabla final.
            palabrasEncontradas.put(palabra, puntajeEncontrado);
        }

        // Paso 4: Mostrar la tabla con las palabras encontradas y la suma total de puntajes.
        System.out.println("\nTabla de palabras encontradas:");
        System.out.println("--------------------------------");
        System.out.printf("| %15s | %7s |\n", "Palabra", "Puntaje");
        System.out.println("--------------------------------");
        for (Map.Entry<String, Integer> entry : palabrasEncontradas.entrySet()) {
            String palabra = entry.getKey();
            int puntaje = entry.getValue();
            System.out.printf("| %15s | %7d |\n", palabra, puntaje);
        }
        System.out.println("--------------------------------");
        System.out.printf("| %15s | %7d |\n", "Suma total", sumaPuntajes);
        System.out.println("--------------------------------");
    }
}
