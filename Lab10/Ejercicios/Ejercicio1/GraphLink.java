package Ejercicio1;

import java.util.*;

public class GraphLink<E> {
    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    public void insertVertex(E data) {
        if (findVertex(data) == null)
            graph.addLast(new AdjList<>(new Vertex<>(data)));
    }

    public void insertEdge(E origin, E destination) {
        insertEdgeWeight(origin, destination, 1);
    }

    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data)) return adj;
        }
        return null;
    }

    public ArrayList<E> shortPath(E origin, E destination) {
        Map<E, Integer> dist = new HashMap<>();
        Map<E, E> prev = new HashMap<>();
        PriorityQueue<E> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<E> visited = new HashSet<>();

        for (int i = 0; i < graph.size(); i++)
            dist.put(graph.get(i).getVertex().getData(), Integer.MAX_VALUE);

        dist.put(origin, 0);
        pq.add(origin);

        while (!pq.isEmpty()) {
            E current = pq.poll();
            if (visited.contains(current)) continue;
            visited.add(current);

            AdjList<E> adj = findVertex(current);
            if (adj == null) continue;

            for (int i = 0; i < adj.getEdges().size(); i++) {
                Edge<E> edge = adj.getEdges().get(i);
                E neighbor = edge.getDestination().getData();
                int newDist = dist.get(current) + edge.getWeight();
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, current);
                    pq.add(neighbor);
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        E step = destination;
        while (step != null) {
            path.add(0, step);
            step = prev.get(step);
        }
        if (!path.isEmpty() && path.get(0).equals(origin)) return path;
        return new ArrayList<>();
    }

    public boolean isConexo() {
        if (graph.size() == 0) return true;
        Set<E> visited = new HashSet<>();
        dfsHelper(graph.get(0).getVertex().getData(), visited);
        return visited.size() == graph.size();
    }

    private void dfsHelper(E current, Set<E> visited) {
        visited.add(current);
        AdjList<E> adj = findVertex(current);
        if (adj == null) return;
        for (int i = 0; i < adj.getEdges().size(); i++) {
            E neighbor = adj.getEdges().get(i).getDestination().getData();
            if (!visited.contains(neighbor)) dfsHelper(neighbor, visited);
        }
    }

    public Stack<E> dijkstra(E origin, E destination) {
        ArrayList<E> path = shortPath(origin, destination);
        Stack<E> stack = new Stack<>();
        for (int i = path.size() - 1; i >= 0; i--) stack.push(path.get(i));
        return stack;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            sb.append(adj.getVertex()).append(" -> ");
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> e = adj.getEdges().get(j);
                sb.append(e.getDestination()).append("(").append(e.getWeight()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}