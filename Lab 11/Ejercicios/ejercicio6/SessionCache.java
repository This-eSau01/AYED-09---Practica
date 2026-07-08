package ejercicio6;

/**
 * Caché de sesiones implementado con tabla hash y encadenamiento.
 * Cada posición de la tabla contiene una lista enlazada de Session.
 *
 * Función hash: token.hashCode() % size (con protección de signo)
 * Tamaño de tabla: 13 (primo)
 */
public class SessionCache {

    private SessionNode[] table;
    private int size;
    private int activeSessions;

    public SessionCache(int size) {
        this.size           = size;
        this.activeSessions = 0;
        this.table          = new SessionNode[size];
        // Cada posición arranca como null (lista vacía)
        for (int i = 0; i < size; i++) table[i] = null;
    }

    /** Función hash: usa hashCode() del token */
    private int hash(String token) {
        return (token.hashCode() & 0x7fffffff) % size;
    }

    // ─────────────────────────────────────────────
    //  OPERACIONES PRINCIPALES
    // ─────────────────────────────────────────────

    /**
     * Registra una nueva sesión con TTL en milisegundos.
     * Si el token ya existe, actualiza la sesión.
     */
    public void login(String token, String username, String role, long ttlMs) {
        int index      = hash(token);
        long expiresAt = System.currentTimeMillis() + ttlMs;

        // Verificar si el token ya existe → actualizar
        SessionNode current = table[index];
        while (current != null) {
            if (current.data.getToken().equals(token)) {
                current.data = new Session(token, username, role, expiresAt);
                System.out.printf("  [LOGIN-UPDATE] token='%s' user='%s' pos=%d%n",
                        token, username, index);
                return;
            }
            current = current.next;
        }

        // Nueva sesión: insertar al frente de la lista
        Session    session  = new Session(token, username, role, expiresAt);
        SessionNode newNode = new SessionNode(session);
        newNode.next  = table[index];
        table[index]  = newNode;
        activeSessions++;
        System.out.printf("  [LOGIN] token='%s' user='%s' role='%s' pos=%d ttl=%dms%n",
                token, username, role, index, ttlMs);
    }

    /**
     * Valida un token.
     * Retorna la Session si existe y no ha expirado; null en caso contrario.
     */
    public Session validate(String token) {
        int index = hash(token);
        SessionNode current = table[index];

        while (current != null) {
            if (current.data.getToken().equals(token)) {
                if (current.data.isExpired()) {
                    System.out.printf("  [VALIDATE] token='%s' → EXPIRADO%n", token);
                    return null;
                }
                System.out.printf("  [VALIDATE] token='%s' → OK (user=%s)%n",
                        token, current.data.getUsername());
                return current.data;
            }
            current = current.next;
        }
        System.out.printf("  [VALIDATE] token='%s' → NO ENCONTRADO%n", token);
        return null;
    }

    /**
     * Cierre de sesión explícito: elimina el token del caché.
     */
    public void logout(String token) {
        int index = hash(token);

        // Caso: primer nodo
        if (table[index] != null && table[index].data.getToken().equals(token)) {
            table[index] = table[index].next;
            activeSessions--;
            System.out.printf("  [LOGOUT] token='%s' eliminado de pos=%d%n", token, index);
            return;
        }

        // Búsqueda en el resto de la cadena
        SessionNode prev    = table[index];
        SessionNode current = (prev != null) ? prev.next : null;
        while (current != null) {
            if (current.data.getToken().equals(token)) {
                prev.next = current.next;
                activeSessions--;
                System.out.printf("  [LOGOUT] token='%s' eliminado de pos=%d%n", token, index);
                return;
            }
            prev    = current;
            current = current.next;
        }
        System.out.printf("  [LOGOUT] token='%s' → no encontrado%n", token);
    }

    /**
     * Recorre toda la tabla y elimina sesiones expiradas.
     */
    public void cleanExpired() {
        int removed = 0;
        for (int i = 0; i < size; i++) {
            // Eliminar nodos expirados al frente de la lista
            while (table[i] != null && table[i].data.isExpired()) {
                table[i] = table[i].next;
                removed++;
                activeSessions--;
            }
            // Eliminar nodos expirados dentro de la cadena
            if (table[i] != null) {
                SessionNode prev    = table[i];
                SessionNode current = prev.next;
                while (current != null) {
                    if (current.data.isExpired()) {
                        prev.next = current.next;
                        removed++;
                        activeSessions--;
                    } else {
                        prev = current;
                    }
                    current = prev.next;
                }
            }
        }
        System.out.printf("  [CLEAN] %d sesión(es) expirada(s) eliminada(s). " +
                "Sesiones activas: %d%n", removed, activeSessions);
    }

    /** Muestra el contenido completo del caché */
    public void printCache() {
        System.out.println("\n  ╔══════════════════════════════════════════════════════╗");
        System.out.println("  ║             Estado del SessionCache                  ║");
        System.out.printf( "  ║  Tamaño tabla: %2d  |  Sesiones activas: %2d           ║%n",
                size, activeSessions);
        System.out.println("  ╠══════════════════════════════════════════════════════╣");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.printf("  ║ [%2d] ", i);
                SessionNode curr = table[i];
                while (curr != null) {
                    System.out.print(curr.data);
                    if (curr.next != null) System.out.print(" →");
                    curr = curr.next;
                }
                System.out.println();
            }
        }
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
    }

    public int getActiveSessions() { return activeSessions; }
}
