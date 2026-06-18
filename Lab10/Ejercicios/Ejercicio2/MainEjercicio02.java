package Ejercicio2;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.GraphPath;

public class MainEjercicio02 {
     public static void main(String[] args) {
        Graph<String, DefaultWeightedEdge> red =
                new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        String[] ciudades = {"Arequipa", "Cusco", "Puno", "Tacna", "Moquegua"};
        for (String c : ciudades) red.addVertex(c);

        addRoad(red, "Arequipa",  "Cusco",    510);
        addRoad(red, "Arequipa",  "Moquegua", 230);
        addRoad(red, "Moquegua",  "Tacna",    160);
        addRoad(red, "Cusco",     "Puno",     390);
        addRoad(red, "Puno",      "Tacna",    420);

        System.out.println("=== CIUDADES ===");
        red.vertexSet().forEach(System.out::println);

        System.out.println("\n=== CARRETERAS ===");
        for (DefaultWeightedEdge e : red.edgeSet()) {
            System.out.printf("  %s -- %s : %.0f km%n",
                    red.getEdgeSource(e), red.getEdgeTarget(e), red.getEdgeWeight(e));
        }

        String origen  = "Arequipa";
        String destino = "Tacna";
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<>(red);
        GraphPath<String, DefaultWeightedEdge> resultado = dijkstra.getPath(origen, destino);

        System.out.println("\n=== CAMINO MAS CORTO: " + origen + " -> " + destino + " ===");
        System.out.println("Ruta  : " + resultado.getVertexList());
        System.out.println("Costo : " + (int) resultado.getWeight() + " km");
    }

    private static void addRoad(Graph<String, DefaultWeightedEdge> g,
                                 String a, String b, double km) {
        DefaultWeightedEdge e = g.addEdge(a, b);
        g.setEdgeWeight(e, km);
    }
}