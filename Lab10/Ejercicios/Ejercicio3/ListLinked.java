package Ejercicio3;

public class ListLinked<E> {
    private Node<E> head;
    private int size;

    public ListLinked() {
        head = null;
        size = 0;
    }

    public void addLast(E data) {
        Node<E> nuevo = new Node<>(data);
        if (head == null) {
            head = nuevo;
        } else {
            Node<E> temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = nuevo;
        }
        size++;
    }

    public E get(int index) {
        Node<E> temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        return temp.data;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public Node<E> getHead() { return head; }
}