// 🔹 Nodo con dato + prioridad
class Nodo {
    int dato;
    int prioridad;
    Nodo next;

    public Nodo(int dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.next = null;
    }
}

// 🔹 Cola de Prioridad con lista enlazada
class ColaPrioridad {
    private Nodo front; // siempre apunta al de mayor prioridad

    // 🔹 enqueue(x, p)
    public void enqueue(int x, int p) {
        Nodo nuevo = new Nodo(x, p);

        // Caso 1: cola vacía o mayor prioridad que el primero
        if (front == null || p > front.prioridad) {
            nuevo.next = front;
            front = nuevo;
        } else {
            // Buscar posición correcta
            Nodo aux = front;

            while (aux.next != null && aux.next.prioridad >= p) {
                aux = aux.next;
            }

            nuevo.next = aux.next;
            aux.next = nuevo;
        }

        System.out.println("✔ Insertado: " + x + " (p=" + p + ")");
    }

    // 🔹 dequeue(): elimina mayor prioridad
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("❌ Cola vacía");
            return -1;
        }

        int eliminado = front.dato;
        front = front.next;

        return eliminado;
    }

    // 🔹 front(): mayor prioridad
    public int front() {
        if (isEmpty())
            return -1;
        return front.dato;
    }

    // 🔹 back(): menor prioridad
    public int back() {
        if (isEmpty())
            return -1;

        Nodo aux = front;
        while (aux.next != null) {
            aux = aux.next;
        }
        return aux.dato;
    }

    // 🔹 isEmpty
    public boolean isEmpty() {
        return front == null;
    }

    // 🔹 isFull (NO aplica en listas enlazadas)
    public boolean isFull() {
        return false; // memoria dinámica
    }

    // 🔹 destroyQueue
    public void destroyQueue() {
        front = null;
        System.out.println("💣 Cola destruida");
    }

    // 🔹 mostrar
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return;
        }

        Nodo aux = front;
        while (aux != null) {
            System.out.print("(" + aux.dato + ", p=" + aux.prioridad + ") -> ");
            aux = aux.next;
        }
        System.out.println("null");
    }
}

// 🔹 MAIN
public class ColaPrioridadDemo {
    public static void main(String[] args) {

        ColaPrioridad cola = new ColaPrioridad();

        // 🔹 enqueue con prioridad
        cola.enqueue(10, 2);
        cola.enqueue(20, 5);
        cola.enqueue(30, 1);
        cola.enqueue(40, 4);

        System.out.println("\nCola ordenada por prioridad:");
        cola.mostrar();

        // 🔹 front y back
        System.out.println("Mayor prioridad (front): " + cola.front());
        System.out.println("Menor prioridad (back): " + cola.back());

        // 🔹 dequeue
        System.out.println("Eliminado: " + cola.dequeue());

        cola.mostrar();

        // 🔹 estado
        System.out.println("¿Está vacía?: " + cola.isEmpty());
        System.out.println("¿Está llena?: " + cola.isFull());

        // 🔹 destruir
        cola.destroyQueue();

        System.out.println("¿Está vacía?: " + cola.isEmpty());
    }
}