package Ejercicios.Ejercicio3;

public class SimpleQueue<E> {
    private Node<E> first;
    private Node<E> last;

    public SimpleQueue() {
        first = null;
        last  = null;
    }

    public void enqueue(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("SimpleQueue is empty.");
        E data = first.getData();
        first  = first.getNext();
        if (first == null) last = null;
        return data;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("SimpleQueue is empty.");
        return first.getData();
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = first;
        while (curr != null) {
            sb.append(curr.getData());
            if (curr.getNext() != null) sb.append(", ");
            curr = curr.getNext();
        }
        return sb.append("]").toString();
    }
}