package ejercicio4;

/**
 * Celda de la tabla hash con tres estados:
 *   0 = EMPTY     → nunca fue usada
 *   1 = OCCUPIED  → contiene un elemento válido
 *   2 = DELETED   → fue eliminada lógicamente (tumba)
 */
public class Entry {

    public static final int EMPTY    = 0;
    public static final int OCCUPIED = 1;
    public static final int DELETED  = 2;

    private int key;
    private int status;

    public Entry() {
        this.key    = -1;
        this.status = EMPTY;
    }

    public int getKey()    { return key; }
    public int getStatus() { return status; }

    public void setKey(int key)       { this.key = key; }
    public void setStatus(int status) { this.status = status; }

    /** Texto legible del estado */
    public String statusName() {
        switch (status) {
            case EMPTY:    return "EMPTY";
            case OCCUPIED: return "OCCUPIED";
            case DELETED:  return "DELETED";
            default:       return "?";
        }
    }

    @Override
    public String toString() {
        if (status == OCCUPIED) return "(" + key + "|OCC)";
        return "(" + statusName() + ")";
    }
}
