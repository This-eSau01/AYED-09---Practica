package Ejercicios.Ejercicio3;
public class TestMultiLevelPQ {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("========================================");
        System.out.println("   TEST COLA MULTI-NIVEL - EJERCICIO 3");
        System.out.println("========================================\n");

        MultiLevelPriorityQueue<String> pq = new MultiLevelPriorityQueue<>(3);

        System.out.println("--- Encolando ---");
        pq.enqueue("A", 0); System.out.println("enqueue(A, 0)\n" + pq);
        pq.enqueue("B", 2); System.out.println("enqueue(B, 2)\n" + pq);
        pq.enqueue("C", 1); System.out.println("enqueue(C, 1)\n" + pq);
        pq.enqueue("D", 2); System.out.println("enqueue(D, 2)\n" + pq);

        System.out.println("front() → mayor prioridad: " + pq.front());
        System.out.println("isEmpty(): " + pq.isEmpty());

        System.out.println("\n--- Desencolar (orden esperado: B, D, C, A) ---");
        while (!pq.isEmpty()) {
            System.out.println("dequeue() → " + pq.dequeue());
        }

        System.out.println("\n--- Test prioridad inválida ---");
        pq.enqueue("X", 5);

        System.out.println("\n--- Test ExceptionIsEmpty ---");
        try {
            pq.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}