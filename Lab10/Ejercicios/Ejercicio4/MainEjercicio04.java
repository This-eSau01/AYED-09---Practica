package Ejercicio4;


public class MainEjercicio04 {
    public static void main(String[] args) {
        GraphListEdge<String> g1 = new GraphListEdge<>(false);
        g1.insertVertex("A");
        g1.insertVertex("B");
        g1.insertVertex("C");
        g1.insertVertex("D");
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "D");
        g1.insertEdge("D", "A");

        GraphListEdge<String> g2 = new GraphListEdge<>(false);
        g2.insertVertex("1");
        g2.insertVertex("2");
        g2.insertVertex("3");
        g2.insertVertex("4");
        g2.insertEdge("1", "2");
        g2.insertEdge("2", "3");
        g2.insertEdge("3", "4");
        g2.insertEdge("4", "1");

        System.out.println("=== GRAFO G1 ===");
        System.out.println(g1);

        System.out.println("=== GRAFO G2 ===");
        System.out.println(g2);

        System.out.println("=== isConexo(G1) ===");
        System.out.println(g1.isConexo());

        System.out.println("\n=== isIsomorfo(G1, G2) ===");
        System.out.println(g1.isIsomorfo(g2));

        System.out.println("\n=== isPlano(G1) ===");
        System.out.println(g1.isPlano());

        System.out.println("\n=== isAutoComplementario(G1) ===");
        System.out.println(g1.isAutoComplementario());
    }
}