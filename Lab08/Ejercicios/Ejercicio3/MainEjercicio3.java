package Ejercicios.Ejercicio3;

public class MainEjercicio3 {
    public static void main(String[] args) {
        EliminacionAVL.Node root = null;

        System.out.println("=== EJERCICIO 3 - Eliminacion en AVL ===");

        int[] inicial = {40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35, 45, 55, 65, 75};
        for (int v : inicial) root = EliminacionAVL.insert(root, v);

        System.out.println("\nArbol inicial:");
        EliminacionAVL.printTree(root, "", false);
        System.out.print("Inorden: ");
        EliminacionAVL.inorder(root);
        System.out.println();

        int[] eliminar = {10, 20, 60, 40, 5, 70};
        System.out.println("\n" + "-".repeat(70));
        System.out.printf("%-4s %-8s %-22s %-10s %-20s%n", "N", "Clave", "Caso BST", "Sucesor", "Rotacion");
        System.out.println("-".repeat(70));

        int num = 1;
        for (int k : eliminar) {
            EliminacionAVL.caseInfo = "-";
            EliminacionAVL.succInfo = "-";
            EliminacionAVL.lastRot  = "-";
            System.out.println("\nEliminando: " + k);
            root = EliminacionAVL.delete(root, k);
            System.out.printf("%-4d %-8d %-22s %-10s %-20s%n",
                num++, k, EliminacionAVL.caseInfo, EliminacionAVL.succInfo, EliminacionAVL.lastRot);
            EliminacionAVL.printTree(root, "", false);
            System.out.print("Inorden: ");
            EliminacionAVL.inorder(root);
            System.out.println();
        }
    }
}