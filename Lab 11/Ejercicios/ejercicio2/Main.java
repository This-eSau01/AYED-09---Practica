package ejercicio2;

/**
 * Main del Ejercicio 2: Comparación sondeo lineal vs cuadrático
 * Tamaño 7, h(x) = x % 7, valores: 10, 17, 24, 31, 4
 */
public class Main {

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};
        int M = 7;

        System.out.println("=================================================");
        System.out.println("  EJERCICIO 2: Sondeo Lineal vs Cuadrático");
        System.out.println("  Tamaño: 7 | Función: h(x) = x % 7");
        System.out.println("  Valores: 10, 17, 24, 31, 4");
        System.out.println("=================================================");

        // Calcular hash base de cada valor
        System.out.println("\n[Cálculo de hash base para cada valor]");
        for (int v : valores) {
            System.out.printf("  h(%2d) = %2d %% 7 = %d%n", v, v, v % M);
        }
        System.out.println("  → Nótese: 10%7=3, 17%7=3, 24%7=3, 31%7=3 → todos colisionan en pos 3");

        // ─────────────── SONDEO LINEAL ───────────────
        System.out.println("\n─────────────────────────────────────────────────");
        System.out.println("  SONDEO LINEAL: f(i) = i");
        System.out.println("  Fórmula: pos = (h(x) + i) % 7");
        System.out.println("─────────────────────────────────────────────────");

        HashCerrado lineal = new HashCerrado(M, "LINEAL");
        for (int v : valores) {
            lineal.insertLineal(v);
            lineal.printTable();
        }
        System.out.println("\n  Total saltos extra (sondeo lineal): " + lineal.getTotalSaltos());

        // ─────────────── SONDEO CUADRÁTICO ───────────────
        System.out.println("\n─────────────────────────────────────────────────");
        System.out.println("  SONDEO CUADRÁTICO: f(i) = i²");
        System.out.println("  Fórmula: pos = (h(x) + i²) % 7");
        System.out.println("─────────────────────────────────────────────────");

        HashCerrado cuadratico = new HashCerrado(M, "CUADRATICO");
        for (int v : valores) {
            cuadratico.insertCuadratico(v);
            cuadratico.printTable();
        }
        System.out.println("\n  Total saltos extra (sondeo cuadrático): " + cuadratico.getTotalSaltos());

        // ─────────────── COMPARACIÓN ───────────────
        System.out.println("\n─────────────────────────────────────────────────");
        System.out.println("  COMPARACIÓN FINAL");
        System.out.println("─────────────────────────────────────────────────");
        System.out.printf("  Saltos LINEAL:     %d%n", lineal.getTotalSaltos());
        System.out.printf("  Saltos CUADRÁTICO: %d%n", cuadratico.getTotalSaltos());

        System.out.println("\n[Análisis]");
        System.out.println("  • Sondeo LINEAL: busca la siguiente posición consecutiva.");
        System.out.println("    Genera 'agrupamiento primario': bloques de celdas ocupadas");
        System.out.println("    que hacen que las colisiones siguientes sean más costosas.");
        System.out.println("  • Sondeo CUADRÁTICO: salta i² posiciones, dispersando más");
        System.out.println("    los elementos y reduciendo el agrupamiento.");
        System.out.println("  • Con claves 10,17,24,31 (todas h=3) el lineal forma un bloque");
        System.out.println("    en posiciones 3,4,5,6 y los saltos se acumulan.");
        System.out.println("  • El cuadrático salta a 3+1=4, 3+4=0, 3+9=5... dispersando más.");
    }
}
