package Actividad01;

  @SuppressWarnings("unchecked")
  public class QueueArray<E> implements Queue<E> {
      private E[] array;
      private int front;   // índice del primer elemento
      private int rear;    // índice del último elemento
      private int size;    // cantidad de elementos actuales
  
      public QueueArray(int n) {
          array = (E[]) new Object[n];
          front = 0;
          rear  = -1;
          size  = 0;
      }
  
      // Agrega x al final de la cola (circular)
      public void enqueue(E x) {
          if (isFull()) {
              System.out.println("Cola llena. No se puede encolar: " + x);
              return;
          }
          rear = (rear + 1) % array.length; // avance circular
          array[rear] = x;
          size++;
      }
  
      // Elimina y retorna el elemento del frente
      public E dequeue() throws ExceptionIsEmpty {
          if (isEmpty())
              throw new ExceptionIsEmpty("Cola vacía. No se puede desencolar.");
          E elem = array[front];
          array[front] = null;           // liberar referencia (GC)
          front = (front + 1) % array.length;
          size--;
          return elem;
      }
  
      // Retorna (sin eliminar) el elemento del frente
      public E front() throws ExceptionIsEmpty {
          if (isEmpty())
              throw new ExceptionIsEmpty("Cola vacía.");
          return array[front];
      }
  
      public boolean isEmpty() { return size == 0; }
      public boolean isFull()  { return size == array.length; }
  
      @Override
      public String toString() {
          if (isEmpty()) return "[]";
          StringBuilder sb = new StringBuilder("[");
          for (int i = 0; i < size; i++) {
              int idx = (front + i) % array.length;
              sb.append(array[idx]);
              if (i < size - 1) sb.append(", ");
          }
          return sb.append("]").toString();
      }
  }
