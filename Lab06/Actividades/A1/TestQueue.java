package Actividad01;

  
  public class TestQueue {
      public static void main(String[] args) throws ExceptionIsEmpty {
  
          System.out.println("=== TEST CON INTEGER ===");
          QueueArray<Integer> qi = new QueueArray<>(5);
          qi.enqueue(10); qi.enqueue(20); qi.enqueue(30);
          System.out.println("Cola: " + qi);        // [10, 20, 30]
          System.out.println("Front: " + qi.front()); // 10
          System.out.println("Dequeue: " + qi.dequeue()); // 10
          System.out.println("Cola: " + qi);        // [20, 30]
          qi.enqueue(40); qi.enqueue(50); qi.enqueue(60);
          System.out.println("Cola llena: " + qi);
          qi.enqueue(70); // -> "Cola llena. No se puede encolar: 70"
  
          System.out.println("\n=== TEST CON STRING ===");
          QueueArray<String> qs = new QueueArray<>(4);
          qs.enqueue("Ana"); qs.enqueue("Luis"); qs.enqueue("María");
          System.out.println("Cola: " + qs);  // [Ana, Luis, María]
          System.out.println("Dequeue: " + qs.dequeue()); // Ana
          System.out.println("Cola: " + qs);  // [Luis, María]
      }
  }
