package Ejercicios.Ejercicio1;

public class MainEjercicio1 {
    public static void main(String[] args) {
        GestorTicketsAVL.Node root = null;

        System.out.println("=== EJERCICIO 1 - GestorTicketsAVL ===");

        int[] tickets = {30, 10, 20, 40, 50, 25};
        System.out.println("\n--- Insercion ---");
        for (int t : tickets) {
            System.out.println("Insertando: " + t);
            root = GestorTicketsAVL.insert(root, t);
            GestorTicketsAVL.printTree(root, "", false);
        }

        System.out.print("\nInorden: ");
        GestorTicketsAVL.inorder(root);
        System.out.println("\nAltura: " + GestorTicketsAVL.height(root));

        System.out.println("\n--- Busqueda ---");
        int[] buscar = {20, 60};
        for (int b : buscar)
            System.out.println("Ticket " + b + ": " + (GestorTicketsAVL.search(root, b) ? "ENCONTRADO" : "NO ENCONTRADO"));

        System.out.println("\n--- Eliminacion ---");
        int[] eliminar = {10, 40, 30};
        for (int e : eliminar) {
            System.out.println("Eliminando: " + e);
            root = GestorTicketsAVL.delete(root, e);
            GestorTicketsAVL.printTree(root, "", false);
            System.out.print("Inorden: ");
            GestorTicketsAVL.inorder(root);
            System.out.println();
        }
    }
}