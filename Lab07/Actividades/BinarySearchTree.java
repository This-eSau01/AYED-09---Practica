package bstreeInterface;

import exceptions.*;

public interface BinarySearchTree<E extends Comparable<E>> {

    void    insert(E data) throws ItemDuplicated;
    E       search(E data) throws ItemNotFound;
    void    delete(E data) throws ExceptionIsEmpty, ItemNotFound;
    boolean isEmpty();

    String inOrder();
    String preOrder();
    String postOrder();

    E findMin() throws ExceptionIsEmpty, ItemNotFound;
    E findMax() throws ExceptionIsEmpty, ItemNotFound;
}
