class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Lista {
    Nodo cabeza;

    public void insertar(int x) {
        Nodo nuevo = new Nodo(x);
        if (cabeza == null)
            cabeza = nuevo;
        else {
            Nodo aux = cabeza;
            while (aux.siguiente != null)
                aux = aux.siguiente;
            aux.siguiente = nuevo;
        }
    }

    public void mostrar() {
        Nodo aux = cabeza;
        while (aux != null) {
            System.out.print(aux.dato + " -> ");
            aux = aux.siguiente;
        }
        System.out.println("null");
    }
}

public class ListaMostrarDemo {
    public static void main(String[] args) {
        Lista lista = new Lista();
        lista.insertar(5);
        lista.insertar(10);
        lista.insertar(15);

        lista.mostrar();
    }
}
