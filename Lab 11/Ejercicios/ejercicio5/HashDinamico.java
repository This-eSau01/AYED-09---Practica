package ejercicio5;

/**
 * Ejercicio 5: Factor de carga y redimensionamiento (rehashing)
 * Tabla hash cerrada con rehashing automático cuando α > 0.75
 * h(x) = x % size
 */
public class HashDinamico {

    private int[] table;
    private int size;
    private int count; // elementos insertados
    private static final double MAX_LOAD = 0.75;

    public HashDinamico(int initialSize) {
        this.size  = initialSize;
        this.count = 0;
        this.table = new int[size];
        for (int i = 0; i < size; i++) table[i] = -1;
    }

    private int hash(int key) {
        return key % size;
    }

    /** Inserta con sondeo lineal. Dispara rehashing si α > 0.75 */
    public void insert(int key) {
        // Verificar factor de carga ANTES de insertar
        double alpha = (double)(count + 1) / size;
        if (alpha > MAX_LOAD) {
            System.out.printf("  ⚠ α=(n+1)/M=(%d+1)/%d=%.2f > 0.75 → REHASHING%n",
                    count, size, alpha);
            rehash();
        }

        int base = hash(key);
        for (int i = 0; i < size; i++) {
            int index = (base + i) % size;
            if (table[index] == -1) {
                table[index] = key;
                count++;
                double alphaPost = (double) count / size;
                System.out.printf("  Insertar %2d → pos[%d] | n=%d, M=%d, α=%.2f%n",
                        key, index, count, size, alphaPost);
                return;
            }
        }
        System.out.println("  ✗ Tabla llena, no se pudo insertar " + key);
    }

    /**
     * Rehashing: busca el siguiente número primo después de 2*size
     * y reinserta todos los elementos existentes.
     */
    private void rehash() {
        int newSize = siguientePrimo(2 * size);
        System.out.printf("  → Nueva tabla tamaño %d (primo)%n", newSize);

        int[] old = table;
        int oldSize = size;

        // Crear nueva tabla
        size  = newSize;
        count = 0;
        table = new int[size];
        for (int i = 0; i < size; i++) table[i] = -1;

        // Reinsertar elementos
        System.out.println("  → Reinsertando elementos:");
        for (int i = 0; i < oldSize; i++) {
            if (old[i] != -1) {
                insertInternal(old[i]); // inserción interna sin chequeo de carga
            }
        }
        System.out.println("  → Rehashing completado.");
    }

    /** Inserción interna sin disparar rehashing (usada en rehash()) */
    private void insertInternal(int key) {
        int base = hash(key);
        for (int i = 0; i < size; i++) {
            int index = (base + i) % size;
            if (table[index] == -1) {
                table[index] = key;
                count++;
                System.out.printf("    %2d → pos[%d]%n", key, index);
                return;
            }
        }
    }

    /** Siguiente número primo mayor que n */
    private int siguientePrimo(int n) {
        int candidato = n + 1;
        while (!esPrimo(candidato)) candidato++;
        return candidato;
    }

    private boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /** Muestra la tabla y el factor de carga actual */
    public void printTable() {
        System.out.printf("  Tabla (M=%d, n=%d, α=%.2f):%n", size, count, (double)count/size);
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            if (table[i] == -1)
                System.out.printf("[%d:---] ", i);
            else
                System.out.printf("[%d:%3d] ", i, table[i]);
        }
        System.out.println();
    }

    public int getSize()  { return size; }
    public int getCount() { return count; }
    public double getAlpha() { return (double) count / size; }
}
