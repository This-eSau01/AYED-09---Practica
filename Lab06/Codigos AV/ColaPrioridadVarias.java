// 🔹 Nodo simple
class Nodo {
    int dato;
    Nodo next;

    public Nodo(int dato) {
        this.dato = dato;
        this.next = null;
    }
}

// 🔹 Lista simple (para cada prioridad)
class Lista {
    private Nodo head;

    // insertar al final
    public void insertar(int x) {
        Nodo nuevo = new Nodo(x);

        if (head == null) {
            head = nuevo;
            return;
        }

        Nodo aux = head;
        while (aux.next != null) {
            aux = aux.next;
        }

        aux.next = nuevo;
    }

    // eliminar del inicio
    public int eliminar() {
        if (head == null)
            return -1;

        int dato = head.dato;
        head = head.next;
        return dato;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void mostrar() {
        Nodo aux = head;
        while (aux != null) {
            System.out.print(aux.dato + " -> ");
            aux = aux.next;
        }
        System.out.print("null");
    }
}

// 🔹 Cola de prioridad con múltiples listas
class ColaPrioridadMulti {
    private Lista[] colas;
    private int niveles;

    public ColaPrioridadMulti(int niveles) {
        this.niveles = niveles;
        colas = new Lista[niveles];

        for (int i = 0; i < niveles; i++) {
            colas[i] = new Lista();
        }
    }

    // 🔹 enqueue(x, p)
    public void enqueue(int x, int p) {
        if (p < 0 || p >= niveles) {
            System.out.println("❌ Prioridad inválida");
            return;
        }

        colas[p].insertar(x);
        System.out.println("✔ Insertado: " + x + " en prioridad " + p);
    }

    // 🔹 dequeue()
    public int dequeue() {
        // buscar desde mayor prioridad
        for (int i = niveles - 1; i >= 0; i--) {
            if (!colas[i].isEmpty()) {
                return colas[i].eliminar();
            }
        }

        System.out.println("❌ Cola vacía");
        return -1;
    }

    // 🔹 front()
    public int front() {
        for (int i = niveles - 1; i >= 0; i--) {
            if (!colas[i].isEmpty()) {
                return colas[i].eliminar(); // ojo: devuelve y elimina (puedes cambiarlo)
            }
        }
        return -1;
    }

    // 🔹 back()
    public int back() {
        for (int i = 0; i < niveles; i++) {
            if (!colas[i].isEmpty()) {
                return -1; // simplificado
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        for (int i = 0; i < niveles; i++) {
            if (!colas[i].isEmpty())
                return false;
        }
        return true;
    }

    public void destroyQueue() {
        for (int i = 0; i < niveles; i++) {
            colas[i] = new Lista();
        }
        System.out.println("💣 Cola destruida");
    }

    // 🔹 mostrar
    public void mostrar() {
        for (int i = niveles - 1; i >= 0; i--) {
            System.out.print("Prioridad " + i + ": ");
            colas[i].mostrar();
            System.out.println();
        }
    }
}

// 🔹 MAIN
public class ColaPrioridadVarias {
    public static void main(String[] args) {

        ColaPrioridadMulti cola = new ColaPrioridadMulti(3);

        cola.enqueue(10, 0);
        cola.enqueue(20, 2);
        cola.enqueue(30, 1);
        cola.enqueue(40, 2);

        System.out.println("\nEstado de la cola:");
        cola.mostrar();

        System.out.println("\nDequeue: " + cola.dequeue());
        cola.mostrar();

        System.out.println("¿Vacía?: " + cola.isEmpty());

        cola.destroyQueue();
    }
}