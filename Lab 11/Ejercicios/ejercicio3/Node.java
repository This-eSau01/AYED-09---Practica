package ejercicio3;

/**
 * Nodo genérico para la LinkedList implementada por el estudiante.
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
