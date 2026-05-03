package Ejercicios.Ejercicio2;

public class TestQueueCircular {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("========================================");
        System.out.println("   TEST COLA CIRCULAR - EJERCICIO 2");
        System.out.println("========================================\n");

        QueueCircular<Integer> q = new QueueCircular<>(5);

        System.out.println("--- enqueue() ---");
        q.enqueue(1); System.out.println("enqueue(1) → " + q);
        q.enqueue(2); System.out.println("enqueue(2) → " + q);
        q.enqueue(3); System.out.println("enqueue(3) → " + q);
        q.enqueue(4); System.out.println("enqueue(4) → " + q);
        q.enqueue(5); System.out.println("enqueue(5) → " + q + " (llena)");
        q.enqueue(6); // overflow

        System.out.println("\n--- dequeue() libera posiciones ---");
        System.out.println("dequeue() → " + q.dequeue() + " | cola: " + q);
        System.out.println("dequeue() → " + q.dequeue() + " | cola: " + q);

        System.out.println("\n--- reutilizar posiciones circulares ---");
        q.enqueue(6); System.out.println("enqueue(6) → " + q);
        q.enqueue(7); System.out.println("enqueue(7) → " + q);

        System.out.println("\n--- front() y size() ---");
        System.out.println("front()  : " + q.front());
        System.out.println("size()   : " + q.size());
        System.out.println("isEmpty(): " + q.isEmpty());

        System.out.println("\n--- Vaciar la cola ---");
        while (!q.isEmpty()) {
            System.out.println("dequeue() → " + q.dequeue());
        }
        System.out.println("isEmpty(): " + q.isEmpty());

        System.out.println("\n--- Test ExceptionIsEmpty ---");
        try {
            q.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
    }
}