public class ListaEstudiantes {
    private Node cabeza;
    private int tamaño;

    public ListaEstudiantes() {
        cabeza = null;
        tamaño = 0;
    }

    public void agregar(Estudiante e) {
        Node nuevo = new Node(e);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Node actual = cabeza;
            while (actual.siguiente != null)
                actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public void mostrarTodos() {
        Node actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public Estudiante buscarPorCodigo(int codigo) {
        Node actual = cabeza;
        while (actual != null) {
            if (actual.dato.getCodigo() == codigo)
                return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarPorCodigo(int codigo) {
        if (cabeza == null) return false;

        if (cabeza.dato.getCodigo() == codigo) {
            cabeza = cabeza.siguiente;
            tamaño--;
            return true;
        }

        Node actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.getCodigo() == codigo) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    public int contar() { return tamaño; }
}