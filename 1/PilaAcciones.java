public class PilaAcciones {
    private String[] pila;
    private int tope;
    private static final int CAPACIDAD = 60; // Capacidad de aula en la torre Ucsm

    public PilaAcciones() {
        pila = new String[CAPACIDAD];
        tope = -1;
    }

    public void push(String accion) { 
        if (tope < CAPACIDAD - 1)
            pila[++tope] = accion;
    }

    public String pop() {
        if (isEmpty()) return null;
        return pila[tope--];
    }

    public String top() {
        if (isEmpty()) return null;
        return pila[tope];
    }

    public boolean isEmpty() { return tope == -1; }
}