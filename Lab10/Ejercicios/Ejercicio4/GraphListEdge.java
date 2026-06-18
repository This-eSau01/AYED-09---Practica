package Ejercicio4;

import java.util.*;

public class GraphListEdge<V> {
    private ListLinked<Vertex<V>> vertices;
    private ListLinked<Edge<V>[]> edges;
    private boolean directed;

    public GraphListEdge(boolean directed) {
        this.directed = directed;
        vertices = new ListLinked<>();
        edges = new ListLinked<>();
    }

    public void insertVertex(V data) {
        vertices.addLast(new Vertex<>(data));
    }

    @SuppressWarnings("unchecked")
    public void insertEdge(V origin, V destination) {
        Vertex<V> vo = findVertex(origin);
        Vertex<V> vd = findVertex(destination);
        if (vo == null || vd == null) return;
        Edge<V>[] pair = new Edge[2];
        pair[0] = new Edge<>(vo);
        pair[1] = new Edge<>(vd);
        edges.addLast(pair);
    }

    private Vertex<V> findVertex(V data) {
        for (int i = 0; i < vertices.size(); i++)
            if (vertices.get(i).getData().equals(data)) return vertices.get(i);
        return null;
    }

    private int vertexCount() { return vertices.size(); }
    private int edgeCount()   { return edges.size(); }

    private List<V> getNeighbors(V data) {
        List<V> result = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge<V>[] pair = edges.get(i);
            V src = pair[0].getDestination().getData();
            V dst = pair[1].getDestination().getData();
            if (src.equals(data)) result.add(dst);
            if (!directed && dst.equals(data)) result.add(src);
        }
        return result;
    }

    public boolean isConexo() {
        if (vertices.size() == 0) return true;
        Set<V> visited = new HashSet<>();
        dfs(vertices.get(0).getData(), visited);
        return visited.size() == vertices.size();
    }

    private void dfs(V current, Set<V> visited) {
        visited.add(current);
        for (V neighbor : getNeighbors(current))
            if (!visited.contains(neighbor)) dfs(neighbor, visited);
    }

    public boolean isIsomorfo(GraphListEdge<V> other) {
        return this.vertexCount() == other.vertexCount()
                && this.edgeCount() == other.edgeCount();
    }

    public boolean isPlano() {
        int v = vertexCount();
        int e = edgeCount();
        if (v < 3) return true;
        return e <= 3 * v - 6;
    }

    public boolean isAutoComplementario() {
        int v = vertexCount();
        int e = edgeCount();
        int totalPosibles = v * (v - 1) / 2;
        int complementoAristas = totalPosibles - e;
        return e == complementoAristas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        for (int i = 0; i < vertices.size(); i++)
            sb.append(vertices.get(i)).append(" ");
        sb.append("\nAristas:\n");
        for (int i = 0; i < edges.size(); i++) {
            Edge<V>[] pair = edges.get(i);
            sb.append("  ").append(pair[0].getDestination())
              .append(directed ? " -> " : " -- ")
              .append(pair[1].getDestination()).append("\n");
        }
        return sb.toString();
    }
}