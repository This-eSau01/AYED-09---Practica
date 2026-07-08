    package ejercicio5;

/**
 * Main del Ejercicio 5: Factor de carga y redimensionamiento
 * Tabla inicial de tamaño 7, umbral α = 0.75
 * Valores: 2, 9, 16, 23, 4, 11
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  EJERCICIO 5: Factor de Carga y Rehashing");
        System.out.println("  Tamaño inicial: 7 | α_max = 0.75");
        System.out.println("  Valores: 2, 9, 16, 23, 4, 11");
        System.out.println("=================================================");

        // Hash base de cada valor con M=7
        System.out.println("\n[Hash base de cada valor (M=7)]");
        int[] valores = {2, 9, 16, 23, 4, 11};
        for (int v : valores) {
            System.out.printf("  h(%2d) = %2d %% 7 = %d%n", v, v, v % 7);
        }

        // Factor de carga teórico antes de cada inserción
        System.out.println("\n[Factor de carga α = n/M calculado por inserción]");
        System.out.println("  ┌──────────┬───┬───┬───────┬────────────┐");
        System.out.println("  │  Insertar│ n │ M │   α   │ ¿Rehash?   │");
        System.out.println("  ├──────────┼───┼───┼───────┼────────────┤");
        int n = 0; int M = 7;
        for (int v : valores) {
            n++;
            double alpha = (double) n / M;
            boolean rehash = alpha > 0.75;
            System.out.printf("  │    %2d    │ %d │ %d │ %.3f │ %-10s │%n",
                    v, n, M, alpha, rehash ? "SÍ → M=17" : "No");
            if (rehash) { M = 17; } // simulación rápida
        }
        System.out.println("  └──────────┴───┴───┴───────┴────────────┘");

        // Tabla ANTES del rehashing (6 elementos, α=6/7≈0.857)
        System.out.println("\n[Estado ANTES del rehashing (M=7, inserción de los primeros 5)]");
        HashDinamico tabla = new HashDinamico(7);
        System.out.println("\n[Inserción con seguimiento de α]");
        for (int v : valores) {
            tabla.insert(v);
        }

        System.out.println("\n[Estado FINAL de la tabla]");
        tabla.printTable();

        System.out.println("\n[¿Por qué cambian las posiciones tras el rehashing?]");
        System.out.println("  • La función hash es h(x) = x % M.");
        System.out.println("  • Al cambiar M de 7 a 17, el residuo de cada clave cambia.");
        System.out.println("  • Ejemplo: 2%7=2 pero 2%17=2; 9%7=2 pero 9%17=9.");
        System.out.println("  • Por eso todos los elementos deben reinsertarse desde cero.");
        System.out.println("  • El rehashing redistribuye las claves más uniformemente");
        System.out.println("    y reduce colisiones al aumentar el espacio disponible.");
    }
}
