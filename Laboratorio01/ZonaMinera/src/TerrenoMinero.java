public class TerrenoMinero {
    private Zona[][] zonas;
    private int filas;
    private int columnas;

    public TerrenoMinero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.zonas = new Zona[filas][columnas];
    }

    public void setZona(int i, int j, Zona z) {
        zonas[i][j] = z;
    }


    private double valorRegion(int fi, int fj, int k) {
        double total = 0;
        for (int i = fi; i < fi + k; i++)
            for (int j = fj; j < fj + k; j++)
                total += zonas[i][j].getvalortotal();
        return total;
    }

    
    public void analizarRegion(int k) {
        if (k > filas || k > columnas) {
            System.out.println("k=" + k + " excede las dimensiones del terreno.");
            return;
        }

        double maxValor = -1;
        int mejorFila = 0, mejorCol = 0;

        for (int i = 0; i <= filas - k; i++)
            for (int j = 0; j <= columnas - k; j++) {
                double v = valorRegion(i, j, k);
                if (v > maxValor) { maxValor = v; mejorFila = i; mejorCol = j; }
            }

        // Mostrar resultado
        System.out.println("Region mas valiosa encontrada:");
        System.out.println("Posicion inicial: (" + mejorFila + ", " + mejorCol + ")");
        System.out.println("Tamanio de la region: " + k + " x " + k);
        System.out.println("\nZonas analizadas:");


        String[] nombres = new String[k * k];
        int[] conteos = new int[k * k];
        int distintos = 0;

        for (int i = mejorFila; i < mejorFila + k; i++) {
            for (int j = mejorCol; j < mejorCol + k; j++) {
                System.out.println("  " + zonas[i][j]);
                String m = zonas[i][j].getMineral();
                boolean encontrado = false;
                for (int x = 0; x < distintos; x++)
                    if (nombres[x].equals(m)) { conteos[x]++; encontrado = true; break; }
                if (!encontrado) { nombres[distintos] = m; conteos[distintos++] = 1; }
            }
        }

        String predominante = nombres[0];
        for (int x = 1; x < distintos; x++)
            if (conteos[x] > conteos[x-1]) predominante = nombres[x];

        System.out.printf("%nValor total estimado: %.1f%n", maxValor);
        System.out.println("Mineral predominante en la region: " + predominante);
    }
}