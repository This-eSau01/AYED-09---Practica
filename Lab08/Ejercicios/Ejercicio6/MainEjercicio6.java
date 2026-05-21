package Ejercicios.Ejercicio6;

public class MainEjercicio6 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 6 - Recorrido Preorden en AVL ===");

        PreordenAVL.Node root1 = null;
        int[] seq1 = {30, 20, 40, 10, 25, 35, 50};
        for (int v : seq1) root1 = PreordenAVL.insert(root1, v);
        System.out.println("\nArbol 1 (30,20,40,10,25,35,50):");
        PreordenAVL.printTree(root1, "", false);
        System.out.print("Preorden: "); PreordenAVL.preOrder(root1); System.out.println();
        System.out.print("Inorden:  "); PreordenAVL.inorder(root1);  System.out.println();
        System.out.println("Altura: " + PreordenAVL.height(root1));

        PreordenAVL.Node root2 = null;
        int[] seq2 = {10, 20, 30, 40, 50, 60, 70};
        for (int v : seq2) root2 = PreordenAVL.insert(root2, v);
        System.out.println("\nArbol 2 (insercion ascendente con rotaciones):");
        PreordenAVL.printTree(root2, "", false);
        System.out.print("Preorden: "); PreordenAVL.preOrder(root2); System.out.println();
        System.out.print("Inorden:  "); PreordenAVL.inorder(root2);  System.out.println();
        System.out.println("Altura: " + PreordenAVL.height(root2));

        PreordenAVL.Node root3 = null;
        int[] seq3 = {50, 30, 70, 20, 40, 60, 80, 10, 25, 45, 65, 75};
        for (int v : seq3) root3 = PreordenAVL.insert(root3, v);
        System.out.println("\nArbol 3 (secuencia mixta):");
        PreordenAVL.printTree(root3, "", false);
        System.out.print("Preorden: "); PreordenAVL.preOrder(root3); System.out.println();
        System.out.print("Inorden:  "); PreordenAVL.inorder(root3);  System.out.println();
        System.out.println("Altura: " + PreordenAVL.height(root3));
    }
}