package Ejercicios.Ejercicio1;
import Actividad01.*;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
    }

    public E pop() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Stack is empty.");
        E data = top.getData();
        top = top.getNext();
        return data;
    }

    public E top() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Stack is empty.");
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[TOP] ");
        Node<E> curr = top;
        while (curr != null) {
            sb.append(curr.getData());
            if (curr.getNext() != null) sb.append(" -> ");
            curr = curr.getNext();
        }
        return sb.toString();
    }
}