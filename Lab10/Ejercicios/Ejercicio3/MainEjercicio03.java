package Ejercicio3;

import java.util.List;

public class MainEjercicio03 {
    public static void main(String[] args) {
        GraphLinkEx3<String> g = new GraphLinkEx3<>();

        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");
        g.insertEdge("C", "D");

        System.out.println("=== GRAFO INICIAL ===");
        System.out.println(g);

        System.out.println("=== searchVertex(C) ===");
        System.out.println(g.searchVertex("C"));

        System.out.println("\n=== searchEdge(A, B) ===");
        System.out.println(g.searchEdge("A", "B"));

        System.out.println("\n=== adjacentVertices(A) ===");
        List<String> adyacentes = g.adjacentVertices("A");
        System.out.println(adyacentes);

        System.out.println("\n=== removeEdge(A, B) ===");
        g.removeEdge("A", "B");
        System.out.println(g);

        System.out.println("=== removeVertex(D) ===");
        g.removeVertex("D");
        System.out.println(g);
    }
}