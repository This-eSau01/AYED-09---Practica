public class ColaTurnos {
    private Estudiante[] cola;
    private int frente, final_Cola, tamaño;
    private static final int CAPACIDAD = 60;

    public ColaTurnos() {
        cola = new Estudiante[CAPACIDAD];
        frente = 0;
        final_Cola = 0;
        tamaño = 0;
    }

    public void enqueue(Estudiante e) {
        if (tamaño < CAPACIDAD) {
            cola[final_Cola] = e;
            final_Cola = (final_Cola + 1) % CAPACIDAD;
            tamaño++;
        }
    }

    public Estudiante dequeue() {
        if (isEmpty()) return null;
        
        Estudiante e = cola[frente];
        frente = (frente + 1) % CAPACIDAD;
        tamaño--;
        return e;
    }

    public Estudiante front() {
        if (isEmpty()) return null;
        return cola[frente];
    }

    public boolean isEmpty() { return tamaño == 0; }
}