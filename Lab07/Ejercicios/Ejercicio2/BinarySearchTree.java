package Ejercicios.Ejercicio2;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty, ItemNotFound;
    boolean isEmpty();
}