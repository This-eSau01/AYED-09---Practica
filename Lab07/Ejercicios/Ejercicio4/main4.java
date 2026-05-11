package Ejercicios.Ejercicio4;

public class main4 {
    public static void main(String[] args) {

        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            for (int v : new int[]{15, 8, 22, 5, 12, 18, 30})
                bst.insert(v);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // parenthesize
        System.out.println("=== parenthesize ===");
        bst.parenthesize();

        // isValidBST — árbol válido
        System.out.println("\n=== isValidBST ===");
        System.out.println("BST válido: " + bst.isValidBST()); // true

        // Crear árbol inválido manualmente para probar false
        // (se inserta en orden incorrecto forzando estructura inválida
        //  — simulamos con un árbol de Strings desordenados)
        LinkedBST<String> bstStr = new LinkedBST<>();
        try {
            // Este árbol sí respeta BST con Strings
            for (String s : new String[]{"M", "F", "T", "A", "H", "P", "Z"})
                bstStr.insert(s);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BST String válido: " + bstStr.isValidBST()); // true

        System.out.println("\n=== parenthesize String BST ===");
        bstStr.parenthesize();
    }
}