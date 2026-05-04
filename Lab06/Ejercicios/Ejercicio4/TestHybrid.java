package Ejercicios.Ejercicio4;


public class TestHybrid {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("========================================");
        System.out.println("   TEST COLA HÍBRIDA - EJERCICIO 4");
        System.out.println("========================================\n");

        PriorityQueueHybrid<String, Integer> pq = new PriorityQueueHybrid<>(3);

        System.out.println("--- Encolando ---");
        pq.enqueue("A", 2, 5); System.out.println("enqueue(A, nivel=2, val=5)\n" + pq);
        pq.enqueue("B", 2, 1); System.out.println("enqueue(B, nivel=2, val=1)\n" + pq);
        pq.enqueue("C", 1, 3); System.out.println("enqueue(C, nivel=1, val=3)\n" + pq);
        pq.enqueue("D", 2, 3); System.out.println("enqueue(D, nivel=2, val=3)\n" + pq);

        System.out.println("front()   : " + pq.front());
        System.out.println("isEmpty() : " + pq.isEmpty());

        System.out.println("\n--- Desencolar (orden esperado: B, D, A, C) ---");
        while (!pq.isEmpty()) {
            System.out.println("dequeue() → " + pq.dequeue());
        }

        System.out.println("\n--- Test prioridad inválida ---");
        pq.enqueue("X", 5, 1);

        System.out.println("\n--- Test ExceptionIsEmpty ---");
        try {
            pq.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }

        System.out.println("\n--- Test con Double como valor secundario ---");
        PriorityQueueHybrid<String, Double> pq2 = new PriorityQueueHybrid<>(2);
        pq2.enqueue("X", 1, 3.5);
        pq2.enqueue("Y", 1, 1.2);
        pq2.enqueue("Z", 0, 2.0);
        pq2.enqueue("W", 1, 2.0);
        System.out.println("Estado:\n" + pq2);
        System.out.println("Desencolar:");
        while (!pq2.isEmpty()) {
            System.out.println("  dequeue() → " + pq2.dequeue());
        }
    }
}