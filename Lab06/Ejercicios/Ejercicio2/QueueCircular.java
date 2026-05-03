package Ejercicios.Ejercicio2;

public class QueueCircular<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueCircular(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear  = -1;
        size  = 0;
    }

    public void enqueue(E x) {
        if (size == array.length) {
            System.out.println("Cola llena. No se puede encolar: " + x);
            return;
        }
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty.");
        E elem = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return elem;
    }

    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty.");
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % array.length;
            sb.append(array[idx]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}