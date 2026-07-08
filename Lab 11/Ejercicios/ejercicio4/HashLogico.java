package ejercicio4;

/**
 * Tabla hash cerrada con sondeo lineal y eliminación lógica.
 * Estados: EMPTY(0), OCCUPIED(1), DELETED(2)
 * h(x) = x % size
 */
public class HashLogico {

    private Entry[] table;
    private int size;

    public HashLogico(int size) {
        this.size = size;
        this.table = new Entry[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Entry();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    /**
     * Inserción con sondeo lineal.
     * Las celdas DELETED pueden reutilizarse.
     */
    public void insert(int key) {
        int base = hash(key);
        int firstDeleted = -1; // primera tumba encontrada

        for (int i = 0; i < size; i++) {
            int index = (base + i) % size;
            int st = table[index].getStatus();

            if (st == Entry.EMPTY) {
                // Si hay una tumba anterior, reutilizarla
                int pos = (firstDeleted != -1) ? firstDeleted : index;
                table[pos].setKey(key);
                table[pos].setStatus(Entry.OCCUPIED);
                System.out.printf("  Insertar %2d → pos %d %s%n",
                        key, pos,
                        firstDeleted != -1 ? "[reutilizó DELETED]" : "[OK]");
                return;
            }
            if (st == Entry.DELETED && firstDeleted == -1) {
                firstDeleted = index;  // guardamos primera tumba
            }
            if (st == Entry.OCCUPIED && table[index].getKey() == key) {
                System.out.printf("  Insertar %2d → clave duplicada, ignorada.%n", key);
                return;
            }
        }
        // Si no hubo EMPTY pero sí una tumba
        if (firstDeleted != -1) {
            table[firstDeleted].setKey(key);
            table[firstDeleted].setStatus(Entry.OCCUPIED);
            System.out.printf("  Insertar %2d → pos %d [reutilizó DELETED]%n",
                    key, firstDeleted);
        } else {
            System.out.printf("  Insertar %2d → ✗ tabla llena%n", key);
        }
    }

    /**
     * Búsqueda con sondeo lineal.
     * No se detiene en celdas DELETED (las "salta").
     */
    public boolean search(int key) {
        int base = hash(key);
        System.out.printf("  Buscar %2d → base=%d | ", key, base);

        for (int i = 0; i < size; i++) {
            int index = (base + i) % size;
            int st = table[index].getStatus();

            if (st == Entry.EMPTY) {
                System.out.println("EMPTY encontrado en [" + index + "], clave no existe.");
                return false;
            }
            if (st == Entry.DELETED) {
                System.out.printf("[%d]=DELETED (salta) ", index);
                continue; // ← CLAVE: no detenemos el sondeo
            }
            if (st == Entry.OCCUPIED && table[index].getKey() == key) {
                System.out.println("encontrado en [" + index + "]");
                return true;
            }
            System.out.printf("[%d]=%d (salta) ", index, table[index].getKey());
        }
        System.out.println("no encontrado.");
        return false;
    }

    /**
     * Eliminación lógica: marca la celda como DELETED (no la borra).
     */
    public void delete(int key) {
        int base = hash(key);
        for (int i = 0; i < size; i++) {
            int index = (base + i) % size;
            int st = table[index].getStatus();

            if (st == Entry.EMPTY) {
                System.out.printf("  Eliminar %2d → no encontrado.%n", key);
                return;
            }
            if (st == Entry.OCCUPIED && table[index].getKey() == key) {
                table[index].setStatus(Entry.DELETED);
                System.out.printf("  Eliminar %2d → pos [%d] marcada como DELETED%n",
                        key, index);
                return;
            }
        }
        System.out.printf("  Eliminar %2d → no encontrado.%n", key);
    }

    /** Imprime la tabla con el estado de cada celda */
    public void printTable() {
        System.out.println("  ┌───────┬──────────────────┐");
        System.out.println("  │  Pos  │     Estado       │");
        System.out.println("  ├───────┼──────────────────┤");
        for (int i = 0; i < size; i++) {
            Entry e = table[i];
            String contenido;
            if (e.getStatus() == Entry.OCCUPIED)
                contenido = "key=" + e.getKey() + " [OCCUPIED]";
            else
                contenido = "[" + e.statusName() + "]";
            System.out.printf("  │  [%d]  │  %-14s  │%n", i, contenido);
        }
        System.out.println("  └───────┴──────────────────┘");
    }
}
