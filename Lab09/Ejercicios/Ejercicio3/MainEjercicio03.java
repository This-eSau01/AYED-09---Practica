package Ejercicio3;


public class MainEjercicio03 {
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> tree = new BTree<>(5);

        int[] valores = {50, 20, 70, 10, 30, 60, 80, 25, 27, 26, 65, 75, 85, 5};
        for (int v : valores) tree.insert(v);

        System.out.println("=== Arbol B inicial ===");
        System.out.println(tree);

        int[] aEliminar = {25, 10, 50, 70, 27, 5, 75};
        for (int k : aEliminar) {
            System.out.println("--- Eliminando " + k + " ---");
            tree.remove(k);
            System.out.println(tree);
        }

        System.out.println("--- Intentando eliminar 999 (no existe) ---");
        tree.remove(999);
    }
}