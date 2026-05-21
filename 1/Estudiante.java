public class Estudiante {
    private int codigo_estudiante;
    private String nombre;
    private double promedio;

    public Estudiante(int codigo_estudiante, String nombre, double promedio) {
        this.codigo_estudiante = codigo_estudiante;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public int getCodigo() { return codigo_estudiante; }
    public String getNombre() { return nombre; }
    public double getPromedio() { return promedio; }

    @Override
    public String toString() {
        return codigo_estudiante + " - " + nombre + " - Promedio: " + promedio;
    }
}