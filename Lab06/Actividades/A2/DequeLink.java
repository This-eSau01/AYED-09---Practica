package Actividad02;
import Actividad01.ExceptionIsEmpty;
  
  public class DequeLink<E> implements Deque<E> {
      private Node<E> first;  // extremo izquierdo
      private Node<E> last;   // extremo derecho
  
      public DequeLink() { first = null; last = null; }
  
      // Inserta al inicio — O(1)
      public void addFirst(E x) {
          Node<E> n = new Node<>(x);
          if (isEmpty()) { first = n; last = n; }
          else { n.setNext(first); first = n; }
      }
  
      // Inserta al final — O(1)
      public void addLast(E x) {
          Node<E> n = new Node<>(x);
          if (isEmpty()) { first = n; last = n; }
          else { last.setNext(n); last = n; }
      }
  
      // Elimina y retorna el primero — O(1)
      public E removeFirst() throws ExceptionIsEmpty {
          if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
          E data = first.getData();
          first = first.getNext();
          if (first == null) last = null; // quedó vacío
          return data;
      }
  
      // Elimina y retorna el último — O(n)
      public E removeLast() throws ExceptionIsEmpty {
          if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
          E data = last.getData();
          if (first == last) { first = null; last = null; }
          else {
              // recorrer hasta el penúltimo
              Node<E> curr = first;
              while (curr.getNext() != last) curr = curr.getNext();
              curr.setNext(null);
              last = curr;
          }
          return data;
      }
  
      public E getFirst() throws ExceptionIsEmpty {
          if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
          return first.getData();
      }
  
      public E getLast() throws ExceptionIsEmpty {
          if (isEmpty()) throw new ExceptionIsEmpty("Deque vacío");
          return last.getData();
      }
  
      public boolean isEmpty() { return first == null; }
  
      @Override
      public String toString() {
          if (isEmpty()) return "[]";
          StringBuilder sb = new StringBuilder("[");
          Node<E> curr = first;
          while (curr != null) {
              sb.append(curr.getData());
              if (curr.getNext() != null) sb.append(" <-> ");
              curr = curr.getNext();
          }
          return sb.append("]").toString();
      }
  }

