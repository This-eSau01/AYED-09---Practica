import javax.swing.*;
import java.awt.*;


public class Graficador extends JPanel {

    private Rectangulo r1;
    private Rectangulo r2;

    private static final Color COLOR_R1       = new Color(59, 130, 246);   // azul
    private static final Color COLOR_R1_BORDE = new Color(29, 78, 216);
    private static final Color COLOR_R2       = new Color(239, 68, 68);    // rojo
    private static final Color COLOR_R2_BORDE = new Color(185, 28, 28);
    private static final Color COLOR_MIX      = new Color(139, 92, 246);   // morado sobreposición
    private static final Color COLOR_GRID     = new Color(220, 220, 220);
    private static final Color COLOR_AXIS     = new Color(100, 100, 100);
    private static final Color COLOR_BG       = new Color(248, 248, 248);

    public Graficador(Rectangulo r1, Rectangulo r2) {
        this.r1 = r1;
        this.r2 = r2;
        setPreferredSize(new Dimension(600, 500));
        setBackground(COLOR_BG);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margen = 60;
        int ancho  = getWidth()  - margen * 2;
        int alto   = getHeight() - margen * 2;

        // Límites del mundo
        double minX1 = Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double maxX1 = Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double minY1 = Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY());
        double maxY1 = Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY());

        double minX2 = Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double maxX2 = Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double minY2 = Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY());
        double maxY2 = Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY());

        double pad   = 2.0;
        double wMinX = Math.min(minX1, minX2) - pad;
        double wMaxX = Math.max(maxX1, maxX2) + pad;
        double wMinY = Math.min(minY1, minY2) - pad;
        double wMaxY = Math.max(maxY1, maxY2) + pad;

        // Conversión mundo → pantalla
        double escalaX = ancho  / (wMaxX - wMinX);
        double escalaY = alto   / (wMaxY - wMinY);

        // Grid y ejes
        dibujarGrid(g2, margen, ancho, alto, wMinX, wMaxX, wMinY, wMaxY, escalaX, escalaY);

        // Rectángulo 1 (relleno con alpha)
        int rx1 = toPx(minX1, wMinX, escalaX, margen);
        int ry1 = toPy(maxY1, wMinY, escalaY, margen, alto);
        int rw1 = (int)((maxX1 - minX1) * escalaX);
        int rh1 = (int)((maxY1 - minY1) * escalaY);

        g2.setColor(new Color(COLOR_R1.getRed(), COLOR_R1.getGreen(), COLOR_R1.getBlue(), 80));
        g2.fillRect(rx1, ry1, rw1, rh1);
        g2.setColor(COLOR_R1_BORDE);
        g2.setStroke(new BasicStroke(2.5f));
        g2.drawRect(rx1, ry1, rw1, rh1);

        // Rectángulo 2 (relleno con alpha)
        int rx2 = toPx(minX2, wMinX, escalaX, margen);
        int ry2 = toPy(maxY2, wMinY, escalaY, margen, alto);
        int rw2 = (int)((maxX2 - minX2) * escalaX);
        int rh2 = (int)((maxY2 - minY2) * escalaY);

        g2.setColor(new Color(COLOR_R2.getRed(), COLOR_R2.getGreen(), COLOR_R2.getBlue(), 80));
        g2.fillRect(rx2, ry2, rw2, rh2);
        g2.setColor(COLOR_R2_BORDE);
        g2.setStroke(new BasicStroke(2.5f));
        g2.drawRect(rx2, ry2, rw2, rh2);

        // Zona de sobreposición
        double ixMin = Math.max(minX1, minX2);
        double ixMax = Math.min(maxX1, maxX2);
        double iyMin = Math.max(minY1, minY2);
        double iyMax = Math.min(maxY1, maxY2);
        if (ixMin < ixMax && iyMin < iyMax) {
            int ox = toPx(ixMin, wMinX, escalaX, margen);
            int oy = toPy(iyMax, wMinY, escalaY, margen, alto);
            int ow = (int)((ixMax - ixMin) * escalaX);
            int oh = (int)((iyMax - iyMin) * escalaY);
            g2.setColor(new Color(COLOR_MIX.getRed(), COLOR_MIX.getGreen(), COLOR_MIX.getBlue(), 130));
            g2.fillRect(ox, oy, ow, oh);
        }

        // Etiquetas R1 y R2
        g2.setFont(new Font("SansSerif", Font.BOLD, 13));
        g2.setColor(COLOR_R1_BORDE);
        g2.drawString("R1", rx1 + 6, ry1 + 16);
        g2.setColor(COLOR_R2_BORDE);
        g2.drawString("R2", rx2 + 6, ry2 + 16);

        // Leyenda
        dibujarLeyenda(g2, getWidth());
    }

    private void dibujarGrid(Graphics2D g2, int margen, int ancho, int alto,
                             double wMinX, double wMaxX, double wMinY, double wMaxY,
                             double escalaX, double escalaY) {
        g2.setStroke(new BasicStroke(0.5f));
        g2.setFont(new Font("SansSerif", Font.PLAIN, 10));

        double paso = calcularPaso(wMaxX - wMinX);

        // Líneas verticales
        for (double x = Math.ceil(wMinX / paso) * paso; x <= wMaxX; x += paso) {
            int px = toPx(x, wMinX, escalaX, margen);
            g2.setColor(COLOR_GRID);
            g2.drawLine(px, margen, px, margen + alto);
            g2.setColor(COLOR_AXIS);
            g2.drawString(String.format("%.0f", x), px - 8, margen + alto + 15);
        }

        // Líneas horizontales
        for (double y = Math.ceil(wMinY / paso) * paso; y <= wMaxY; y += paso) {
            int py = toPy(y, wMinY, escalaY, margen, alto);
            g2.setColor(COLOR_GRID);
            g2.drawLine(margen, py, margen + ancho, py);
            g2.setColor(COLOR_AXIS);
            g2.drawString(String.format("%.0f", y), margen - 28, py + 4);
        }

        // Ejes principales
        g2.setColor(COLOR_AXIS);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRect(margen, margen, ancho, alto);
    }

    private void dibujarLeyenda(Graphics2D g2, int panelAncho) {
        int lx = panelAncho - 170;
        int ly = 20;
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Fondo leyenda
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRoundRect(lx - 8, ly - 8, 160, 80, 8, 8);
        g2.setColor(COLOR_GRID);
        g2.drawRoundRect(lx - 8, ly - 8, 160, 80, 8, 8);

        // R1
        g2.setColor(new Color(COLOR_R1.getRed(), COLOR_R1.getGreen(), COLOR_R1.getBlue(), 120));
        g2.fillRect(lx, ly + 2, 16, 14);
        g2.setColor(COLOR_R1_BORDE);
        g2.drawRect(lx, ly + 2, 16, 14);
        g2.drawString("Rectángulo 1", lx + 22, ly + 14);

        // R2
        g2.setColor(new Color(COLOR_R2.getRed(), COLOR_R2.getGreen(), COLOR_R2.getBlue(), 120));
        g2.fillRect(lx, ly + 26, 16, 14);
        g2.setColor(COLOR_R2_BORDE);
        g2.drawRect(lx, ly + 26, 16, 14);
        g2.drawString("Rectángulo 2", lx + 22, ly + 38);

        // Sobreposición
        g2.setColor(new Color(COLOR_MIX.getRed(), COLOR_MIX.getGreen(), COLOR_MIX.getBlue(), 150));
        g2.fillRect(lx, ly + 50, 16, 14);
        g2.setColor(new Color(109, 40, 217));
        g2.drawRect(lx, ly + 50, 16, 14);
        g2.drawString("Sobreposición", lx + 22, ly + 62);
    }

    private int toPx(double wx, double wMinX, double escalaX, int margen) {
        return (int)((wx - wMinX) * escalaX) + margen;
    }

    private int toPy(double wy, double wMinY, double escalaY, int margen, int alto) {
        return alto - (int)((wy - wMinY) * escalaY) + margen;
    }

    private double calcularPaso(double rango) {
        double paso = Math.pow(10, Math.floor(Math.log10(rango / 5)));
        if (rango / paso > 10) paso *= 2;
        return paso;
    }

    // Método estático que abre la ventana
    public static void graficar(Rectangulo r1, Rectangulo r2) {
        JFrame frame = new JFrame("Rectángulos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new Graficador(r1, r2));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}