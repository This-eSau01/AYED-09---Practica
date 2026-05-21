public class ArbolABB {
    private NodoABB raiz;

    public ArbolABB() { raiz = null; }

    public void insertar(int codigo) {
        raiz = insertarRec(raiz, codigo);
    }

    private NodoABB insertarRec(NodoABB nodo, int codigo) {
        if (nodo == null) return new NodoABB(codigo);
        if (codigo < nodo.codigo)
            nodo.izquierdo = insertarRec(nodo.izquierdo, codigo);
        else if (codigo > nodo.codigo)
            nodo.derecho = insertarRec(nodo.derecho, codigo);
        return nodo;
    }

    public boolean buscar(int codigo) {
        return buscarRec(raiz, codigo);
    }

    private boolean buscarRec(NodoABB nodo, int codigo) {
        if (nodo == null) return false;
        if (codigo == nodo.codigo) return true;
        if (codigo < nodo.codigo) return buscarRec(nodo.izquierdo, codigo);
        return buscarRec(nodo.derecho, codigo);
    }

    public void eliminar(int codigo) {
        raiz = eliminarRec(raiz, codigo);
    }

    private NodoABB eliminarRec(NodoABB nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo < nodo.codigo)
            nodo.izquierdo = eliminarRec(nodo.izquierdo, codigo);
        else if (codigo > nodo.codigo)
            nodo.derecho = eliminarRec(nodo.derecho, codigo);
        else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;
            NodoABB sucesor = minimoNodo(nodo.derecho);
            nodo.codigo = sucesor.codigo;
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.codigo);
        }
        return nodo;
    }

    private NodoABB minimoNodo(NodoABB nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public void inOrden() { inOrdenRec(raiz); System.out.println(); }
    private void inOrdenRec(NodoABB n) {
        if (n == null) return;
        inOrdenRec(n.izquierdo);
        System.out.print(n.codigo + " ");
        inOrdenRec(n.derecho);
    }

    public void preOrden() { preOrdenRec(raiz); System.out.println(); }
    private void preOrdenRec(NodoABB n) {
        if (n == null) return;
        System.out.print(n.codigo + " ");
        preOrdenRec(n.izquierdo);
        preOrdenRec(n.derecho);
    }

    public void postOrden() { postOrdenRec(raiz); System.out.println(); }
    private void postOrdenRec(NodoABB n) {
        if (n == null) return;
        postOrdenRec(n.izquierdo);
        postOrdenRec(n.derecho);
        System.out.print(n.codigo + " ");
    }

    public NodoABB rotarIzquierda(NodoABB nodo) {
        NodoABB nuevaRaiz = nodo.derecho;
        nodo.derecho = nuevaRaiz.izquierdo;
        nuevaRaiz.izquierdo = nodo;
        return nuevaRaiz;
    }

    public NodoABB rotarDerecha(NodoABB nodo) {
        NodoABB nuevaRaiz = nodo.izquierdo;
        nodo.izquierdo = nuevaRaiz.derecho;
        nuevaRaiz.derecho = nodo;
        return nuevaRaiz;
    }

    public NodoABB getRaiz() { return raiz; }
    public void setRaiz(NodoABB raiz) { this.raiz = raiz; }
}