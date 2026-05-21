package Ejercicios.Ejercicio4y5;

public class MainEjercicio5 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 5 - BFS Recursivo por Niveles ===");

        RecorridoAmplitudAVL.Node root = null;
        int[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int v : datos) root = RecorridoAmplitudAVL.insert(root, v);

        System.out.println("\nArbol:");
        RecorridoAmplitudAVL.printTree(root, "", false);

        System.out.println("\nRecorrido BFS nivel por nivel:");
        RecorridoAmplitudAVL.bfs(root);

        System.out.println("\nOrden esperado del enunciado: 50, 30, 70, 20, 40, 60, 80, 10, 25, 65");
        System.out.print("Orden obtenido BFS: ");
        int h = RecorridoAmplitudAVL.height(root);
        for (int level = 0; level < h; level++)
            RecorridoAmplitudAVL.printLevel(root, level);
        System.out.println();
    }
}