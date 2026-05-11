package Ejercicios.Ejercicio1;


public class Main {
    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<>();

        // ── a) Construcción con secuencia: 15, 8, 22, 5, 12, 18, 30 ──────────
        System.out.println("=== a) INSERCIÓN ===");
        int[] valores = {15, 8, 22, 5, 12, 18, 30};
        for (int v : valores) {
            try {
                bst.insert(v);
                System.out.println("Insertado: " + v);
            } catch (ItemDuplicated e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // ── b) Recorridos ─────────────────────────────────────────────────────
        System.out.println("\n=== b) RECORRIDOS ===");
        bst.inOrder();    // 5 8 12 15 18 22 30
        bst.preOrder();   // 15 8 5 12 22 18 30
        bst.postOrder();  // 5 12 8 18 30 22 15

        // ── c) Búsquedas ──────────────────────────────────────────────────────
        System.out.println("\n=== c) BÚSQUEDAS ===");
        // Buscar 12 (existe)
        try {
            System.out.println("Buscar 12 -> encontrado: " + bst.search(12));
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }
        // Buscar 21 (no existe)
        try {
            System.out.println("Buscar 21 -> encontrado: " + bst.search(21));
        } catch (ItemNotFound e) {
            System.out.println("Buscar 21 -> " + e.getMessage());
        }

        // ── d) Análisis básico ────────────────────────────────────────────────
        System.out.println("\n=== d) ANÁLISIS ===");
        try {
            System.out.println("Mínimo:  " + bst.findMin());  // 5
            System.out.println("Máximo:  " + bst.findMax());  // 30
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }
        System.out.println("toString (InOrden): " + bst.toString());
    }
}