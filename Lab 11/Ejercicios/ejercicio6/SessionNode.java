package ejercicio6;

/**
 * Nodo de la lista enlazada interna del SessionCache.
 * Almacena una Session y apunta al siguiente nodo.
 */
public class SessionNode {
    public Session data;
    public SessionNode next;

    public SessionNode(Session data) {
        this.data = data;
        this.next = null;
    }
}
