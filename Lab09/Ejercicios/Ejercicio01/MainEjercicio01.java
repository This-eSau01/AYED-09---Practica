package Ejercicio01;
public class MainEjercicio01 {
    public static void main(String[] args) {
        BNode.resetCounter();
        BTree<Integer> tree = new BTree<>(4);

        int[] valores = {31, 12, 19, 3, 10, 13, 16, 22, 25, 28, 41, 57, 63, 33, 35, 40, 49, 52, 55, 60, 62, 67, 70, 72};
        for (int v : valores) tree.insert(v);

        System.out.println("=== Arbol B ===");
        System.out.println(tree);

        System.out.println("--- Busqueda de 52 ---");
        System.out.println("Encontrado: " + tree.search(52));

        System.out.println("\n--- Busqueda de 3 (hoja extremo inicial) ---");
        System.out.println("Encontrado: " + tree.search(3));

        System.out.println("\n--- Busqueda de 72 (hoja extremo final) ---");
        System.out.println("Encontrado: " + tree.search(72));

        System.out.println("\n--- Busqueda de 31 (raiz) ---");
        System.out.println("Encontrado: " + tree.search(31));

        System.out.println("\n--- Busqueda de 99 (no existe) ---");
        System.out.println("Encontrado: " + tree.search(99));
    }
}