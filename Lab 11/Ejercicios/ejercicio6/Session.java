package ejercicio6;

/**
 * Representa una sesión activa en el sistema.
 * Campos: token, username, role, expiresAt (timestamp Unix en ms)
 */
public class Session {
    private String token;
    private String username;
    private String role;
    private long   expiresAt; // System.currentTimeMillis() + ttlMs

    public Session(String token, String username, String role, long expiresAt) {
        this.token     = token;
        this.username  = username;
        this.role      = role;
        this.expiresAt = expiresAt;
    }

    public String getToken()     { return token; }
    public String getUsername()  { return username; }
    public String getRole()      { return role; }
    public long   getExpiresAt() { return expiresAt; }

    /** Retorna true si la sesión ha expirado */
    public boolean isExpired() {
        return System.currentTimeMillis() > expiresAt;
    }

    @Override
    public String toString() {
        long remaining = expiresAt - System.currentTimeMillis();
        String estado  = isExpired() ? "EXPIRADA" : "activa (" + remaining + "ms restantes)";
        return String.format("[token=%s | user=%s | role=%s | %s]",
                token, username, role, estado);
    }
}
