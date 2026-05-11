package Ejercicios.Ejercicio2;

public class main2 {
    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<>();

        // Insertar: 15, 8, 22, 5, 12, 18, 30
        try {
            for (int v : new int[]{15, 8, 22, 5, 12, 18, 30})
                bst.insert(v);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Árbol (InOrden): " + bst.toString());

        // a) destroyNodes
        System.out.println("\n=== a) destroyNodes ===");
        try {
            bst.destroyNodes();
            System.out.println("isEmpty después de destroy: " + bst.isEmpty());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
        // Intentar destruir de nuevo (árbol ya vacío)
        try {
            bst.destroyNodes();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }

        // Reconstruir el árbol para los siguientes ejercicios
        try {
            for (int v : new int[]{15, 8, 22, 5, 12, 18, 30})
                bst.insert(v);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // b) countAllNodes
        System.out.println("\n=== b) countAllNodes ===");
        System.out.println("Total de nodos (todos): " + bst.countAllNodes()); // 7

        // c) countNodes (no-hojas)
        System.out.println("\n=== c) countNodes (no-hojas) ===");
        System.out.println("Nodos internos (no-hoja): " + bst.countNodes()); // 3

        // d) height(x)
        System.out.println("\n=== d) height(x) ===");
        System.out.println("Altura desde raíz (15): " + bst.height(15)); // 2
        System.out.println("Altura desde nodo  (8): " + bst.height(8));  // 1
        System.out.println("Altura desde hoja  (5): " + bst.height(5));  // 0
        System.out.println("Altura nodo inexistente (99): " + bst.height(99)); // -1

        // e) amplitude
        System.out.println("\n=== e) amplitude ===");
        System.out.println("Amplitud máxima del árbol: " + bst.amplitude()); // 4
    }
}