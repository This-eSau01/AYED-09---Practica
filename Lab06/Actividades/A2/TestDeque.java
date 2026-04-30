package Actividad02;
import Actividad01.ExceptionIsEmpty;
  
    public class TestDeque {
      public static void main(String[] args) throws ExceptionIsEmpty {
          DequeLink<Integer> dq = new DequeLink<>();
          dq.addLast(10); dq.addLast(20); dq.addLast(30);
          dq.addFirst(5);
          System.out.println("Deque: " + dq);  // [5 <-> 10 <-> 20 <-> 30]
          System.out.println("First: " + dq.getFirst());  // 5
          System.out.println("Last:  " + dq.getLast());   // 30
          System.out.println("RemoveFirst: " + dq.removeFirst()); // 5
          System.out.println("RemoveLast:  " + dq.removeLast());  // 30
          System.out.println("Deque final: " + dq);  // [10 <-> 20]
      }
  }
