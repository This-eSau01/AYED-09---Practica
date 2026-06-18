package Ejercicio2;


public class MainEjercicio02 {
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> tree = new BTree<>(4);

        int[] valores = {10, 15, 20, 25, 30, 35, 40, 45};
        for (int v : valores) tree.insert(v);

        System.out.println("=== Arbol B ===");
        System.out.println(tree);

        System.out.println("--- Rango valido [20, 40] ---");
        tree.searchRange(20, 40);

        System.out.println("\n--- Rango invalido [40, 20] ---");
        tree.searchRange(40, 20);

        System.out.println("\n--- Rango inexistente [50, 60] ---");
        tree.searchRange(50, 60);

        System.out.println("\n--- Rango completo [10, 45] ---");
        tree.searchRange(10, 45);

        System.out.println("\n--- Rango parcial [22, 38] (valores intermedios) ---");
        tree.searchRange(22, 38);
    }
}