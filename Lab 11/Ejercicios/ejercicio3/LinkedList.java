package ejercicio3;

/**
 * Lista enlazada simple implementada por el estudiante.
 * Usada internamente por HashO para encadenamiento.
 */
public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /** Agrega al final de la lista */
    public void add(T data) {
        Node<T> nuevo = new Node<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nuevo;
        }
        size++;
    }

    /** Retorna el nodo en la posición dada (0-indexed) */
    public Node<T> getNode(int index) {
        if (index < 0 || index >= size) return null;
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /** Retorna el primer nodo (para iteración manual) */
    public Node<T> getHead() {
        return head;
    }

    /** Elimina el primer nodo cuyo dato cumple .equals(data) */
    public boolean remove(T data) {
        if (head == null) return false;
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** Representación de la lista como cadena */
    @Override
    public String toString() {
        if (head == null) return "[vacía]";
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(" → ");
            current = current.next;
        }
        return sb.toString();
    }
}
