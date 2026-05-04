package Ejercicios.Ejercicio4;


public class PriorityQueueHybrid<E, V extends Comparable<V>> {

    class Entry {
        E data;
        V value;

        Entry(E data, V value) {
            this.data  = data;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + data + ", v=" + value + ")";
        }
    }

    private Node<Entry>[] levels;
    private int numLevels;

    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int numLevels) {
        this.numLevels = numLevels;
        levels = new Node[numLevels];
        for (int i = 0; i < numLevels; i++) {
            levels[i] = null;
        }
    }

    // Inserción ordenada por value ASC dentro del nivel 'priority'
    public void enqueue(E x, int priority, V value) {
        if (priority < 0 || priority >= numLevels) {
            System.out.println("Prioridad inválida: " + priority);
            return;
        }
        Node<Entry> newNode = new Node<>(new Entry(x, value));

        // Insertar al inicio si la lista está vacía o el nuevo value es menor
        if (levels[priority] == null ||
            value.compareTo(levels[priority].getData().value) < 0) {
            newNode.setNext(levels[priority]);
            levels[priority] = newNode;
            return;
        }

        // Buscar posición: avanzar mientras el siguiente value sea <= al nuevo
        Node<Entry> curr = levels[priority];
        while (curr.getNext() != null &&
               curr.getNext().getData().value.compareTo(value) <= 0) {
            curr = curr.getNext();
        }
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
    }

    // Desencola el primero del nivel más alto disponible
    public E dequeue() throws ExceptionIsEmpty {
        for (int i = numLevels - 1; i >= 0; i--) {
            if (levels[i] != null) {
                E data = levels[i].getData().data;
                levels[i] = levels[i].getNext();
                return data;
            }
        }
        throw new ExceptionIsEmpty("PriorityQueueHybrid está vacía.");
    }

    public E front() throws ExceptionIsEmpty {
        for (int i = numLevels - 1; i >= 0; i--) {
            if (levels[i] != null) {
                return levels[i].getData().data;
            }
        }
        throw new ExceptionIsEmpty("PriorityQueueHybrid está vacía.");
    }

    public boolean isEmpty() {
        for (int i = 0; i < numLevels; i++) {
            if (levels[i] != null) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = numLevels - 1; i >= 0; i--) {
            sb.append("  Nivel ").append(i).append(": ");
            Node<Entry> curr = levels[i];
            if (curr == null) {
                sb.append("vacío");
            } else {
                while (curr != null) {
                    sb.append(curr.getData());
                    if (curr.getNext() != null) sb.append(" -> ");
                    curr = curr.getNext();
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}