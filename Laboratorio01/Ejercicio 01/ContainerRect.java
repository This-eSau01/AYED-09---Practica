public class ContainerRect {

    private Rectangulo[] rectangulos;
    private double[]     distancias;
    private double[]     areas;
    private int          n;
    public static int    numRec = 0;

    public ContainerRect(int n) {
        this.n        = n;
        rectangulos   = new Rectangulo[n];
        distancias    = new double[n];
        areas         = new double[n];
    }

    public void addRectangulo(Rectangulo r) {
        if (numRec >= n) {
            System.out.println("No es posible guardar el rectángulo: contenedor lleno (máx " + n + ").");
            return;
        }

        double x1 = r.getEsquina1().getX(), y1 = r.getEsquina1().getY();
        double x2 = r.getEsquina2().getX(), y2 = r.getEsquina2().getY();

        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double area      = Math.abs(x2 - x1) * Math.abs(y2 - y1);

        rectangulos[numRec] = r;
        distancias [numRec] = distancia;
        areas      [numRec] = area;
        numRec++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Contenedor de Rectángulos ===\n");
        sb.append(String.format("%-6s %-30s %-15s %-10s%n",
                "N°", "Rectángulo", "Distancia", "Área"));
        sb.append("-".repeat(65)).append("\n");

        for (int i = 0; i < numRec; i++) {
            Coordenada c1 = rectangulos[i].getEsquina1();
            Coordenada c2 = rectangulos[i].getEsquina2();
            String desc = String.format("(%.1f,%.1f) - (%.1f,%.1f)",
                    c1.getX(), c1.getY(), c2.getX(), c2.getY());
            sb.append(String.format("%-6d %-30s %-15.2f %-10.2f%n",
                    i + 1, desc, distancias[i], areas[i]));
        }

        sb.append("-".repeat(65)).append("\n");
        sb.append("Total almacenados: ").append(numRec)
                .append(" / ").append(n).append("\n");
        return sb.toString();
    }
}