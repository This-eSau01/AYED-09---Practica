package ejercicio1;

/**
 * Ejercicio 1: Tabla hash sin colisiones – análisis de función hash
 * Tabla de tamaño 11 (número primo), h(x) = x % 11
 * Arreglo de enteros inicializado en -1 (posición vacía)
 */
public class HashSimple {

    private int[] table;
    private int size;

    public HashSimple(int size) {
        this.size = size;
        this.table = new int[size];
        // Inicializar todas las posiciones como vacías (-1)
        for (int i = 0; i < size; i++) {
            table[i] = -1;
        }
    }

    /** Función hash: h(x) = x % size */
    private int hash(int key) {
        return key % size;
    }

    /**
     * Inserta un valor en la tabla.
     * Muestra el índice calculado y si hubo colisión.
     */
    public void insert(int key) {
        int index = hash(key);
        System.out.printf("  Insertando %d → h(%d) = %d %% %d = %d",
                key, key, key, size, index);

        if (table[index] == -1) {
            table[index] = key;
            System.out.println("  → OK, insertado en posición " + index);
        } else {
            System.out.println("  → ¡COLISIÓN! posición " + index
                    + " ocupada por " + table[index] + " (no se inserta en este ejercicio)");
        }
    }

    /** Muestra el estado completo de la tabla con índices */
    public void printTable() {
        System.out.println("\n┌────────┬──────────┐");
        System.out.println("│ Índice │  Valor   │");
        System.out.println("├────────┼──────────┤");
        for (int i = 0; i < size; i++) {
            if (table[i] == -1) {
                System.out.printf("│   %2d   │  [vacío] │%n", i);
            } else {
                System.out.printf("│   %2d   │    %3d   │%n", i, table[i]);
            }
        }
        System.out.println("└────────┴──────────┘");
    }

    /** Cuenta y muestra cuántas posiciones quedan vacías */
    public void printStats() {
        int empty = 0;
        System.out.print("Posiciones vacías: ");
        for (int i = 0; i < size; i++) {
            if (table[i] == -1) {
                System.out.print(i + " ");
                empty++;
            }
        }
        System.out.println("\nTotal vacías: " + empty + " / " + size);
    }
}
