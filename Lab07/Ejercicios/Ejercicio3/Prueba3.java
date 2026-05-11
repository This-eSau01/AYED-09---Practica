package Ejercicios.Ejercicio3;


public class Prueba3 {

    // c) sameArea — compara el área de dos árboles distintos
    public static boolean sameArea(LinkedBST<Integer> a, LinkedBST<Integer> b) {
        return a.areaBST() == b.areaBST();
    }

    public static void main(String[] args) {

        LinkedBST<Integer> bst1 = new LinkedBST<>();
        LinkedBST<Integer> bst2 = new LinkedBST<>();
        LinkedBST<Integer> bst3 = new LinkedBST<>();

        try {
            // BST1: 15, 8, 22, 5, 12, 18, 30
            for (int v : new int[]{15, 8, 22, 5, 12, 18, 30})
                bst1.insert(v);

            // BST2: misma estructura diferente valores — misma área
            for (int v : new int[]{50, 30, 70, 20, 40, 60, 80})
                bst2.insert(v);

            // BST3: estructura diferente — área distinta
            for (int v : new int[]{10, 5, 20})
                bst3.insert(v);

        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // a) areaBST
        System.out.println("=== a) areaBST ===");
        System.out.println("Área BST1: " + bst1.areaBST()); // 4 hojas * 2 altura = 8
        System.out.println("Área BST2: " + bst2.areaBST()); // 4 hojas * 2 altura = 8
        System.out.println("Área BST3: " + bst3.areaBST()); // 2 hojas * 1 altura = 2

        // b) drawBST
        System.out.println("\n=== b) drawBST BST1 ===");
        bst1.drawBST();

        System.out.println("\n=== b) drawBST BST3 ===");
        bst3.drawBST();

        // c) sameArea
        System.out.println("\n=== c) sameArea ===");
        System.out.println("BST1 y BST2 misma área: " + sameArea(bst1, bst2)); // true
        System.out.println("BST1 y BST3 misma área: " + sameArea(bst1, bst3)); // false
    }
}