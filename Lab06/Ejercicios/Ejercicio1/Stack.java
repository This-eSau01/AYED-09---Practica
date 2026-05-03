package Ejercicios.Ejercicio1;
import Actividad01.*;



public interface Stack<E> {
    void push(E x);
    E pop()  throws ExceptionIsEmpty;
    E top()  throws ExceptionIsEmpty;
    boolean isEmpty();
}