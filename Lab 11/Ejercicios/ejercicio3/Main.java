package ejercicio3;

/**
 * Main del Ejercicio 3: Tabla hash abierta con colisiones múltiples
 * Tamaño 7, h(k) = k % 7
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  EJERCICIO 3: Hash Abierto – Encadenamiento");
        System.out.println("  Tamaño: 7 | Función: h(k) = k % 7");
        System.out.println("=================================================");

        HashO tabla = new HashO(7);

        // Cálculo previo de colisiones esperadas
        System.out.println("\n[Hash base de cada clave]");
        int[] claves = {10, 17, 24, 31, 5, 12};
        String[] nombres = {"Juan", "Ana", "Luis", "Rosa", "Pedro", "Carla"};
        for (int i = 0; i < claves.length; i++) {
            System.out.printf("  h(%2d) = %2d %% 7 = %d (%s)%n",
                    claves[i], claves[i], claves[i] % 7, nombres[i]);
        }

        // Inserciones
        System.out.println("\n[Proceso de inserción]");
        tabla.insert(new Register(10, "Juan"));
        tabla.insert(new Register(17, "Ana"));
        tabla.insert(new Register(24, "Luis"));
        tabla.insert(new Register(31, "Rosa"));
        tabla.insert(new Register(5, "Pedro"));
        tabla.insert(new Register(12, "Carla"));

        // Estado inicial
        System.out.println("\n[Estado de la tabla tras todas las inserciones]");
        tabla.printTable();

        System.out.println("\n  Colisiones identificadas:");
        System.out.println("  • 10%7=3, 17%7=3, 24%7=3, 31%7=3 → pos 3 tiene 4 elementos");
        System.out.println("  • 5%7=5, 12%7=5               → pos 5 tiene 2 elementos");

        // Búsqueda clave 24
        System.out.println("\n[Búsqueda: clave 24]");
        tabla.search(24);

        // Eliminación clave 17
        System.out.println("\n[Eliminación: clave 17]");
        tabla.delete(17);

        // Estado tras eliminación
        System.out.println("\n[Estado de la tabla tras eliminar clave 17]");
        tabla.printTable();
    }
}
