package ejercicio6;

/**
 * TestSessionCache – Main del Ejercicio 6
 *
 * Flujo:
 *  1) Tres usuarios inician sesión (uno con TTL muy corto → expirará)
 *  2) Se validan todos los tokens
 *  3) Un usuario cierra sesión explícitamente
 *  4) Se llama cleanExpired() y se muestra cuántas sesiones quedan
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=================================================");
        System.out.println("  EJERCICIO 6: Sistema de Autenticación – SessionCache");
        System.out.println("  Tabla hash con encadenamiento | Tamaño: 13");
        System.out.println("=================================================");

        SessionCache cache = new SessionCache(13);

        // ─── 1) LOGIN ─────────────────────────────────────
        System.out.println("\n[PASO 1: Inicio de sesión de tres usuarios]");
        // Token A: expira en 5 segundos (seguirá activo)
        cache.login("abc123", "juan.perez",  "ADMIN",  5000L);
        // Token B: expira en 200 ms (expirará casi de inmediato)
        cache.login("xyz789", "ana.garcia",  "USER",    200L);
        // Token C: expira en 10 segundos (seguirá activo)
        cache.login("tok456", "luis.torres", "EDITOR", 10000L);

        cache.printCache();

        // ─── 2) VALIDAR antes de que expire xyz789 ────────
        System.out.println("\n[PASO 2a: Validación inmediata de tokens]");
        cache.validate("abc123");
        cache.validate("xyz789"); // aún activo en este instante
        cache.validate("tok456");

        // Esperamos 300 ms para que xyz789 expire
        System.out.println("\n  ⏱ Esperando 300 ms para que el token 'xyz789' expire...");
        Thread.sleep(300);

        System.out.println("\n[PASO 2b: Validación tras expiración de 'xyz789']");
        cache.validate("abc123");
        cache.validate("xyz789"); // debe retornar null (expirado)
        cache.validate("tok456");
        cache.validate("noexiste"); // token inexistente

        // ─── 3) LOGOUT explícito ──────────────────────────
        System.out.println("\n[PASO 3: Logout explícito del usuario 'luis.torres']");
        cache.logout("tok456");
        cache.logout("noexiste"); // token inexistente

        cache.printCache();

        // ─── 4) CLEAN EXPIRED ─────────────────────────────
        System.out.println("\n[PASO 4: Limpieza de sesiones expiradas]");
        cache.cleanExpired();

        cache.printCache();

        System.out.printf("%n  Sesiones activas restantes: %d%n",
                cache.getActiveSessions());

        // ─── REFLEXIÓN ────────────────────────────────────
        System.out.println("\n=================================================");
        System.out.println("  REFLEXIÓN");
        System.out.println("=================================================");
        System.out.println("  • Hash vs. lista enlazada simple:");
        System.out.println("    - Tabla hash: validate() en O(1) promedio.");
        System.out.println("      Con 1M usuarios, se accede al bucket correcto");
        System.out.println("      en una operación; solo recorre la cadena corta.");
        System.out.println("    - Lista enlazada: O(n) → 1M comparaciones en peor caso.");
        System.out.println("    → Para sistemas de alta concurrencia (Facebook, Gmail)");
        System.out.println("      la diferencia entre O(1) y O(n) es crítica.");
        System.out.println();
        System.out.println("  • HashMap de Java vs. implementación manual:");
        System.out.println("    - HashMap usa encadenamiento + árbol rojo-negro (Java 8+)");
        System.out.println("      cuando una cadena supera 8 nodos → O(log n) peor caso.");
        System.out.println("    - HashMap hace rehashing automático (factor de carga 0.75).");
        System.out.println("    - Implementación manual es didáctica pero no maneja:");
        System.out.println("      concurrencia, rehashing automático ni hash distribuido.");
    }
}
