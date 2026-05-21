public class NodoABB {
    public int codigo;
    public NodoABB izquierdo, derecho;

    public NodoABB(int codigo) {
        this.codigo = codigo;
        this.izquierdo = null;
        this.derecho = null;
    }
}