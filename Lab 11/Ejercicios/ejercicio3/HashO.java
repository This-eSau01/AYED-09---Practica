package ejercicio3;

/**
 * Tabla hash abierta con encadenamiento.
 * Usa LinkedList<Register> implementada por el estudiante.
 * h(k) = k % size
 */
public class HashO {

    private LinkedList<Register>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        // Inicializar cada posición con una lista enlazada vacía
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /** Función hash: h(k) = k % size */
    private int hash(int key) {
        return key % size;
    }

    /**
     * Inserta un registro en la tabla.
     * Si la posición ya tiene elementos → encadenamiento.
     */
    public void insert(Register reg) {
        int index = hash(reg.getKey());
        boolean colision = !table[index].isEmpty();
        table[index].add(reg);
        System.out.printf("  Insertar %s → pos %d %s%n",
                reg, index, colision ? "[COLISIÓN → encadenado]" : "[OK]");
    }

    /**
     * Busca un registro por clave.
     * Retorna el Register encontrado o null.
     */
    public Register search(int key) {
        int index = hash(key);
        Node<Register> current = table[index].getHead();
        int nodo = 0;
        while (current != null) {
            if (current.data.getKey() == key) {
                System.out.printf("  Búsqueda clave %d → pos %d, nodo %d: encontrado %s%n",
                        key, index, nodo, current.data);
                return current.data;
            }
            current = current.next;
            nodo++;
        }
        System.out.printf("  Búsqueda clave %d → pos %d: NO encontrado%n", key, index);
        return null;
    }

    /**
     * Elimina un registro por clave.
     */
    public void delete(int key) {
        int index = hash(key);
        Node<Register> current = table[index].getHead();
        while (current != null) {
            if (current.data.getKey() == key) {
                table[index].remove(current.data);
                System.out.printf("  Eliminado clave %d de posición %d. " +
                        "Nodos restantes en cadena: %d%n",
                        key, index, table[index].size());
                return;
            }
            current = current.next;
        }
        System.out.printf("  Clave %d no encontrada para eliminar.%n", key);
    }

    /** Imprime el estado completo de la tabla */
    public void printTable() {
        System.out.println("\n  ┌───────┬─────────────────────────────┐");
        System.out.println("  │  Pos  │  Cadena                     │");
        System.out.println("  ├───────┼─────────────────────────────┤");
        for (int i = 0; i < size; i++) {
            System.out.printf("  │  [%d]  │  %-27s│%n", i, table[i].toString());
        }
        System.out.println("  └───────┴─────────────────────────────┘");
    }
}
