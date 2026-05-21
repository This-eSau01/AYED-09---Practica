package Ejercicios.Ejercicio4y5;
public class MainEjercicio4 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 4/5 - Recorrido por Amplitud Recursivo ===");

        RecorridoAmplitudAVL.Node root1 = null;
        int[] datos1 = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int v : datos1) root1 = RecorridoAmplitudAVL.insert(root1, v);

        System.out.println("\n--- Arbol 1 ---");
        RecorridoAmplitudAVL.printTree(root1, "", false);
        System.out.println("BFS recursivo:");
        RecorridoAmplitudAVL.bfs(root1);
        System.out.print("Inorden:  "); RecorridoAmplitudAVL.inorder(root1);  System.out.println();
        System.out.print("Preorden: "); RecorridoAmplitudAVL.preorder(root1); System.out.println();
        System.out.println("Altura: " + RecorridoAmplitudAVL.height(root1));

        RecorridoAmplitudAVL.Node root2 = null;
        int[] datos2 = {40, 20, 60, 10, 30, 50, 70, 5, 15};
        for (int v : datos2) root2 = RecorridoAmplitudAVL.insert(root2, v);

        System.out.println("\n--- Arbol 2 ---");
        RecorridoAmplitudAVL.printTree(root2, "", false);
        System.out.println("BFS recursivo:");
        RecorridoAmplitudAVL.bfs(root2);
        System.out.print("Inorden:  "); RecorridoAmplitudAVL.inorder(root2);  System.out.println();
        System.out.print("Preorden: "); RecorridoAmplitudAVL.preorder(root2); System.out.println();
        System.out.println("Altura: " + RecorridoAmplitudAVL.height(root2));
    }
}