package Ejercicio3;

import java.util.*;

public class GraphLinkEx3<V> implements Graph<V> {
    private ListLinked<AdjList<V>> graph;

    public GraphLinkEx3() {
        graph = new ListLinked<>();
    }

    private AdjList<V> findAdj(V data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data))
                return graph.get(i);
        }
        return null;
    }

    @Override
    public void insertVertex(V data) {
        if (findAdj(data) == null)
            graph.addLast(new AdjList<>(new Vertex<>(data)));
    }

    @Override
    public void insertEdge(V origin, V destination) {
        AdjList<V> v1 = findAdj(origin);
        AdjList<V> v2 = findAdj(destination);
        if (v1 == null || v2 == null) return;
        v1.getEdges().addLast(new Edge<>(v2.getVertex()));
        v2.getEdges().addLast(new Edge<>(v1.getVertex()));
    }

    @Override
    public void removeVertex(V data) {
        ListLinked<AdjList<V>> newGraph = new ListLinked<>();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<V> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data)) continue;
            ListLinked<Edge<V>> newEdges = new ListLinked<>();
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<V> e = adj.getEdges().get(j);
                if (!e.getDestination().getData().equals(data))
                    newEdges.addLast(e);
            }
            AdjList<V> newAdj = new AdjList<>(adj.getVertex());
            for (int j = 0; j < newEdges.size(); j++)
                newAdj.getEdges().addLast(newEdges.get(j));
            newGraph.addLast(newAdj);
        }
        graph = newGraph;
    }

    @Override
    public void removeEdge(V origin, V destination) {
        AdjList<V> v1 = findAdj(origin);
        AdjList<V> v2 = findAdj(destination);
        if (v1 == null || v2 == null) return;
        removeFromList(v1, destination);
        removeFromList(v2, origin);
    }

    private void removeFromList(AdjList<V> adj, V target) {
        ListLinked<Edge<V>> newEdges = new ListLinked<>();
        for (int i = 0; i < adj.getEdges().size(); i++) {
            Edge<V> e = adj.getEdges().get(i);
            if (!e.getDestination().getData().equals(target))
                newEdges.addLast(e);
        }
        ListLinked<Edge<V>> current = adj.getEdges();
        while (current.size() > 0) {
            ListLinked<Edge<V>> tmp = new ListLinked<>();
            for (int i = 1; i < current.size(); i++) tmp.addLast(current.get(i));
            current = tmp;
        }
        for (int i = 0; i < newEdges.size(); i++)
            adj.getEdges().addLast(newEdges.get(i));
    }

    @Override
    public boolean searchVertex(V data) {
        return findAdj(data) != null;
    }

    @Override
    public boolean searchEdge(V origin, V destination) {
        AdjList<V> adj = findAdj(origin);
        if (adj == null) return false;
        for (int i = 0; i < adj.getEdges().size(); i++) {
            if (adj.getEdges().get(i).getDestination().getData().equals(destination))
                return true;
        }
        return false;
    }

    @Override
    public List<V> adjacentVertices(V data) {
        List<V> result = new ArrayList<>();
        AdjList<V> adj = findAdj(data);
        if (adj == null) return result;
        for (int i = 0; i < adj.getEdges().size(); i++)
            result.add(adj.getEdges().get(i).getDestination().getData());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<V> adj = graph.get(i);
            sb.append(adj.getVertex()).append(" -> ");
            for (int j = 0; j < adj.getEdges().size(); j++)
                sb.append(adj.getEdges().get(j).getDestination()).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}