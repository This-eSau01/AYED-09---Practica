package Ejercicios.Ejercicio5;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node {
        public E data;
        public Node left, right;
        public Node(E data) { this.data = data; }
    }

    private Node root;
    public LinkedBST() { this.root = null; }

    @Override
    public boolean isEmpty() { return root == null; }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }
    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null) return new Node(data);
        int cmp = data.compareTo(node.data);
        if (cmp == 0) throw new ItemDuplicated("Duplicado: " + data);
        else if (cmp < 0) node.left  = insertRec(node.left,  data);
        else              node.right = insertRec(node.right, data);
        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        return searchRec(root, data);
    }
    private E searchRec(Node node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("No encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if (cmp == 0) return node.data;
        if (cmp < 0)  return searchRec(node.left,  data);
        return             searchRec(node.right, data);
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty, ItemNotFound {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol está vacío");
        root = deleteRec(root, data);
    }
    private Node deleteRec(Node node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("No encontrado: " + data);
        int cmp = data.compareTo(node.data);
        if      (cmp < 0) node.left  = deleteRec(node.left,  data);
        else if (cmp > 0) node.right = deleteRec(node.right, data);
        else {
            if (node.left == null && node.right == null) return null;
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = findMinNode(node.right);
            node.data  = successor.data;
            node.right = deleteRec(node.right, successor.data);
        }
        return node;
    }

    public void inOrder() {
        System.out.print("InOrden: ");
        inOrderRec(root);
        System.out.println();
    }
    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.print(node.data + " ");
        inOrderRec(node.right);
    }

    public void preOrder() {
        System.out.print("PreOrden: ");
        preOrderRec(root);
        System.out.println();
    }
    private void preOrderRec(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrderRec(node.left);
        preOrderRec(node.right);
    }

    public void postOrder() {
        System.out.print("PostOrden: ");
        postOrderRec(root);
        System.out.println();
    }
    private void postOrderRec(Node node) {
        if (node == null) return;
        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.print(node.data + " ");
    }

    private Node findMinNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }
    public E findMin() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        return findMinNode(root).data;
    }
    public E findMax() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío");
        Node node = root;
        while (node.right != null) node = node.right;
        return node.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb);
        return sb.toString().trim();
    }
    private void toStringRec(Node node, StringBuilder sb) {
        if (node == null) return;
        toStringRec(node.left, sb);
        sb.append(node.data).append(" ");
        toStringRec(node.right, sb);
    }

    // ── Ejercicio 05 ──────────────────────────────────────────────────────────

    // a) insertar productos — usa insert() heredado, se prueba en Ejercicio05

    // b) searchRange — retorna String con todos los valores en [min, max]
    public String searchRange(E min, E max) {
        StringBuilder sb = new StringBuilder();
        searchRangeRec(root, min, max, sb);
        return sb.toString().trim();
    }
    private void searchRangeRec(Node node, E min, E max, StringBuilder sb) {
        if (node == null) return;
        // Si el dato es mayor que min, explorar subárbol izquierdo
        if (node.data.compareTo(min) > 0)
            searchRangeRec(node.left, min, max, sb);
        // Si está en rango, agregarlo
        if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0)
            sb.append(node.data).append(" ");
        // Si el dato es menor que max, explorar subárbol derecho
        if (node.data.compareTo(max) < 0)
            searchRangeRec(node.right, min, max, sb);
    }

    // c) countLeaves — cuenta nodos hoja
    public int countLeaves() {
        return countLeavesRec(root);
    }
    private int countLeavesRec(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }

    // d) printDescending — orden descendente (der → raiz → izq)
    public void printDescending() {
        System.out.print("Descendente: ");
        printDescRec(root);
        System.out.println();
    }
    private void printDescRec(Node node) {
        if (node == null) return;
        printDescRec(node.right);
        System.out.print(node.data + " ");
        printDescRec(node.left);
    }
}