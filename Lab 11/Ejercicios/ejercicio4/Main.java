package ejercicio4;

/**
 * Main del Ejercicio 4: Eliminación lógica y reinserción en hash cerrado
 * Tamaño 7, sondeo lineal, h(x) = x % 7
 * Claves: 5, 12, 19, 26
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  EJERCICIO 4: Eliminación Lógica y Reinserción");
        System.out.println("  Tamaño: 7 | h(x) = x % 7 | Sondeo lineal");
        System.out.println("=================================================");

        HashLogico tabla = new HashLogico(7);

        // Inserción inicial
        System.out.println("\n[Hash base de cada clave]");
        int[] inicial = {5, 12, 19, 26};
        for (int v : inicial) {
            System.out.printf("  h(%2d) = %2d %% 7 = %d%n", v, v, v % 7);
        }

        System.out.println("\n[Inserción inicial: 5, 12, 19, 26]");
        for (int v : inicial) {
            tabla.insert(v);
        }

        System.out.println("\n[Estado tras inserción]");
        tabla.printTable();

        // ─── Eliminación lógica de clave 12 ───
        System.out.println("\n[Eliminación lógica: clave 12]");
        tabla.delete(12);

        System.out.println("\n[Estado tras eliminar 12]");
        tabla.printTable();

        // ─── Búsqueda de clave 19 tras eliminación ───
        System.out.println("\n[Búsqueda: clave 19 (pasa por DELETED)]");
        tabla.search(19);

        System.out.println("\n  ¿Por qué DELETED no detiene el sondeo?");
        System.out.println("  • Si detuviéramos en DELETED, la clave 19 (que está");
        System.out.println("    después en la cadena de sondeo) parecería no existir.");
        System.out.println("  • DELETED significa 'aquí hubo algo, sigue buscando'.");
        System.out.println("  • EMPTY significa 'nunca hubo nada, detén el sondeo'.");

        // ─── Reinserción de clave 33 (reutiliza DELETED) ───
        System.out.println("\n[Reinserción: clave 33]");
        System.out.printf("  h(33) = 33 %% 7 = %d%n", 33 % 7);
        System.out.println("  → Pos 5 ocupada por 5; pos 6 ocupa 26; pos 0 ocupa DELETED de 12.");
        tabla.insert(33);

        System.out.println("\n[Estado final tras reinsertar 33]");
        tabla.printTable();

        // ─── Reflexión ───
        System.out.println("\n[Eliminación lógica vs física]");
        System.out.println("  • Lógica (DELETED): marca la celda sin borrarla.");
        System.out.println("    ✓ Simple y mantiene la cadena de sondeo intacta.");
        System.out.println("    ✗ Con muchas eliminaciones la tabla se llena de tumbas.");
        System.out.println("    → Conviene cuando hay pocas eliminaciones.");
        System.out.println("  • Física: reorganiza la tabla después de eliminar.");
        System.out.println("    ✓ Mantiene la tabla limpia, sin tumbas.");
        System.out.println("    ✗ Costosa en tiempo (O(n)) y compleja de implementar.");
        System.out.println("    → Conviene cuando hay muchas eliminaciones frecuentes.");
    }
}
