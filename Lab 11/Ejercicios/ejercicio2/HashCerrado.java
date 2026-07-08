package ejercicio2;

/**
 * Ejercicio 2: Comparación de sondeo lineal vs cuadrático en hash cerrado
 * Tamaño 7, h(x) = x % 7
 * Valores: 10, 17, 24, 31, 4
 */
public class HashCerrado {

    private int[] table;
    private int size;
    private String tipo; // "LINEAL" o "CUADRATICO"
    private int totalSaltos; // contador global de saltos

    public HashCerrado(int size, String tipo) {
        this.size = size;
        this.tipo = tipo;
        this.table = new int[size];
        this.totalSaltos = 0;
        for (int i = 0; i < size; i++) {
            table[i] = -1;
        }
    }

    private int hash(int key) {
        return key % size;
    }

    /**
     * Inserta usando sondeo lineal: siguiente = (h + i) % M
     */
    public void insertLineal(int key) {
        int base = hash(key);
        int index = base;
        int saltos = 0;

        System.out.printf("  Insertar %2d: h(%2d)=%d", key, key, base);

        for (int i = 0; i < size; i++) {
            index = (base + i) % size;
            if (i > 0) {
                saltos++;
                System.out.printf(" →colisión[%d]→ pos%d", i, index);
            }
            if (table[index] == -1) {
                table[index] = key;
                System.out.printf(" ✓ insertado en [%d] (saltos extra: %d)%n", index, saltos);
                totalSaltos += saltos;
                return;
            }
        }
        System.out.println(" ✗ tabla llena");
    }

    /**
     * Inserta usando sondeo cuadrático: siguiente = (h + i²) % M
     */
    public void insertCuadratico(int key) {
        int base = hash(key);
        int index = base;
        int saltos = 0;

        System.out.printf("  Insertar %2d: h(%2d)=%d", key, key, base);

        for (int i = 0; i < size; i++) {
            index = (base + i * i) % size;
            if (i > 0) {
                saltos++;
                System.out.printf(" →col[i=%d,i²=%d]→ pos%d", i, i * i, index);
            }
            if (table[index] == -1) {
                table[index] = key;
                System.out.printf(" ✓ insertado en [%d] (saltos extra: %d)%n", index, saltos);
                totalSaltos += saltos;
                return;
            }
        }
        System.out.println(" ✗ tabla llena");
    }

    /** Muestra el estado actual de la tabla */
    public void printTable() {
        System.out.print("  Estado [" + tipo + "]: ");
        for (int i = 0; i < size; i++) {
            if (table[i] == -1) {
                System.out.printf("[%d:---] ", i);
            } else {
                System.out.printf("[%d:%3d] ", i, table[i]);
            }
        }
        System.out.println();
    }

    public int getTotalSaltos() {
        return totalSaltos;
    }

    public String getTipo() {
        return tipo;
    }
}
