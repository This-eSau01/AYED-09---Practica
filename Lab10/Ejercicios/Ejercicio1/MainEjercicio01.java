package Ejercicio1;
import java.util.*;

public class MainEjercicio01 {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 2);
        g.insertEdgeWeight("B", "C", 5);
        g.insertEdgeWeight("B", "D", 10);
        g.insertEdgeWeight("C", "E", 3);
        g.insertEdgeWeight("E", "D", 4);

        System.out.println("=== GRAFO PONDERADO ===");
        System.out.println(g);

        System.out.println("=== shortPath(A, D) ===");
        ArrayList<String> path = g.shortPath("A", "D");
        System.out.println("Ruta: " + path);

        System.out.println("\n=== isConexo() ===");
        System.out.println("Es conexo: " + g.isConexo());

        System.out.println("\n=== Dijkstra(A, D) como Stack ===");
        Stack<String> stack = g.dijkstra("A", "D");
        System.out.println("Stack: " + stack);
    }
}