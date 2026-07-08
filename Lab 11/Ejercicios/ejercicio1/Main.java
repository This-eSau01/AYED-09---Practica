package ejercicio1;

/**
 * Main del Ejercicio 1: Tabla hash sin colisiones
 * Tamaño 11, h(x) = x % 11
 * Valores: 3, 14, 25, 36, 47, 58
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  EJERCICIO 1: Tabla Hash sin Colisiones");
        System.out.println("  Tamaño: 11 | Función: h(x) = x % 11");
        System.out.println("=================================================");

        // --- Cálculo manual de direcciones hash ---
        System.out.println("\n[Cálculo manual de direcciones hash]");
        int[] valores = {3, 14, 25, 36, 47, 58};
        int M = 11;
        for (int v : valores) {
            System.out.printf("  h(%2d) = %2d %% %d = %d%n", v, v, M, v % M);
        }

        // --- Inserción en la tabla ---
        System.out.println("\n[Proceso de inserción]");
        HashSimple tabla = new HashSimple(M);
        for (int v : valores) {
            tabla.insert(v);
        }

        // --- Estado final de la tabla ---
        System.out.println("\n[Estado final de la tabla hash]");
        tabla.printTable();

        // --- Estadísticas ---
        System.out.println();
        tabla.printStats();

        // --- Reflexión sobre número primo ---
        System.out.println("\n[¿Por qué el tamaño debe ser número primo?]");
        System.out.println("  • Con M primo, h(x) = x % M distribuye las claves");
        System.out.println("    de forma más uniforme en todos los índices.");
        System.out.println("  • Si M es par o tiene factores comunes con las claves,");
        System.out.println("    solo se usaría un subconjunto de las posiciones.");
        System.out.println("  • Ejemplo con M=10: 10,20,30,40... siempre van al índice 0.");
        System.out.println("  • Con M=11 (primo): 11→0, 22→0, pero 3→3, 14→3 solo");
        System.out.println("    coinciden por ser múltiplos de 11 más el mismo residuo.");
        System.out.println("  • Los 6 valores insertados ocupan 6 posiciones distintas,");
        System.out.println("    demostrando que M=11 evita colisiones en este conjunto.");
    }
}
