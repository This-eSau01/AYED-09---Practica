package Ejercicio4;

import java.util.List;

public interface Graph<V> {
    void insertVertex(V data);
    void insertEdge(V origin, V destination);
    void removeVertex(V data);
    void removeEdge(V origin, V destination);
    boolean searchVertex(V data);
    boolean searchEdge(V origin, V destination);
    List<V> adjacentVertices(V data);
}