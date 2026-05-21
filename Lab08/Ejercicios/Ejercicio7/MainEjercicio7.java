package Ejercicios.Ejercicio7;

public class MainEjercicio7 {
    public static void main(String[] args) {
        InsercionEliminacionAVL.Node root = null;
        InsercionEliminacionAVL.totalRotaciones = 0;

        System.out.println("=== EJERCICIO 7 - Insercion y Eliminacion AVL ===");

        int[] insertar = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45, 55, 65, 75, 90};
        System.out.println("\n--- FASE 1: Insercion ---");
        for (int v : insertar) {
            System.out.println("Insertando: " + v);
            root = InsercionEliminacionAVL.insert(root, v);
            InsercionEliminacionAVL.printTree(root, "", false);
        }

        System.out.println("\nArbol tras inserciones:");
        InsercionEliminacionAVL.printTree(root, "", false);
        System.out.print("Inorden: ");
        InsercionEliminacionAVL.inorder(root);
        System.out.println("\nAltura: " + InsercionEliminacionAVL.height(root));
        System.out.println("Rotaciones en insercion: " + InsercionEliminacionAVL.totalRotaciones);

        int rotAntes = InsercionEliminacionAVL.totalRotaciones;

        int[] eliminar = {10, 90, 50, 30, 70};
        System.out.println("\n--- FASE 2: Eliminacion ---");
        for (int v : eliminar) {
            System.out.println("Eliminando: " + v);
            root = InsercionEliminacionAVL.delete(root, v);
            InsercionEliminacionAVL.printTree(root, "", false);
            System.out.print("Inorden: ");
            InsercionEliminacionAVL.inorder(root);
            System.out.println();
        }

        System.out.println("\nArbol final:");
        InsercionEliminacionAVL.printTree(root, "", false);
        System.out.print("Inorden final: ");
        InsercionEliminacionAVL.inorder(root);
        System.out.println("\nAltura final: " + InsercionEliminacionAVL.height(root));
        System.out.println("Rotaciones en eliminacion: " + (InsercionEliminacionAVL.totalRotaciones - rotAntes));
        System.out.println("Total rotaciones: " + InsercionEliminacionAVL.totalRotaciones);
    }
}