package Ejercicios.Ejercicio3;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNotFound;
    void delete(E data) throws ExceptionIsEmpty, ItemNotFound;
    boolean isEmpty();
}