// Nodo de la lista
class Nodo1 {
    int dato;
    Nodo siguiente;

    public Nodo1(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

// Lista enlazada
class ListaEnlazada {
    Nodo cabeza;

    // Insertar al final
    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    // Mostrar lista
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}

// Clase principal (IMPORTANTE: mismo nombre que el archivo)
public class ListaEnlazadaDemo1 {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();

        lista.insertar(10);
        lista.insertar(20);
        lista.insertar(30);

        System.out.println("Lista enlazada:");
        lista.mostrar();
    }
}
