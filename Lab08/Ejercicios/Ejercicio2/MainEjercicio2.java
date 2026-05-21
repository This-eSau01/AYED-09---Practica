package Ejercicios.Ejercicio2;

public class MainEjercicio2 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 2 - Comparacion BST vs AVL ===");

        ComparacionBSTvsAVL.comparar(
            "CASO 1: Insercion ascendente (BST degenera en lista)",
            new int[]{10, 20, 30, 40, 50, 60, 70});

        ComparacionBSTvsAVL.comparar(
            "CASO 2: Insercion descendente (BST degenera en lista inversa)",
            new int[]{70, 60, 50, 40, 30, 20, 10});

        System.out.println("\nCONCLUSION:");
        System.out.println("  BST en orden -> altura O(n), degenera en lista");
        System.out.println("  AVL siempre  -> altura O(log n), balanceado");
    }
}