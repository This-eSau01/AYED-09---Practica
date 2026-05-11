package bstreelinklistinterfgeneric;

import bstreeInterface.BinarySearchTree;
import exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private class Node {
        public E    data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null);
        }
        public Node(E data, Node left, Node right) {
            this.data  = data;
            this.left  = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null)
            return new Node(data);

        int cmp = data.compareTo(node.data);
        if (cmp == 0)
            throw new ItemDuplicated(
                "ItemDuplicated: el valor [" + data + "] ya existe en el BST.");
        else if (cmp < 0)
            node.left  = insertRec(node.left,  data);
        else
            node.right = insertRec(node.right, data);

        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        Node result = searchNode(root, data);
        if (result == null)
            throw new ItemNotFound(
                "ItemNotFound: el valor [" + data + "] no existe en el BST.");
        return result.data;
    }

    private Node searchNode(Node node, E data) {
        while (node != null) {
            int cmp = data.compareTo(node.data);
            if (cmp == 0) return node;
            node = (cmp < 0) ? node.left : node.right;
        }
        return null;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty, ItemNotFound {
        if (isEmpty())
            throw new ExceptionIsEmpty("delete(): el árbol está vacío.");
        if (searchNode(root, data) == null)
            throw new ItemNotFound(
                "delete(): el valor [" + data + "] no existe en el BST.");
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left  = deleteRec(node.left,  data);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, data);
        } else {
            // Caso 1: hoja
            if (node.left == null && node.right == null) {
                System.out.println("  [delete] Caso 1: nodo hoja eliminado → " + data);
                return null;
            }
            // Caso 2: un solo hijo
            if (node.left == null) {
                System.out.println("  [delete] Caso 2: reemplazado por hijo derecho → " + data);
                return node.right;
            }
            if (node.right == null) {
                System.out.println("  [delete] Caso 2: reemplazado por hijo izquierdo → " + data);
                return node.left;
            }
            // Caso 3: dos hijos → sucesor InOrden (mínimo del subárbol derecho)
            Node sucesor = findMinNode(node.right);
            System.out.println("  [delete] Caso 3: sucesor InOrden = " + sucesor.data + " reemplaza a " + data);
            node.data  = sucesor.data;
            node.right = deleteRec(node.right, sucesor.data);
        }
        return node;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[BST vacío]";
        return "InOrder   : " + inOrder()   + "\n"
             + "PreOrder  : " + preOrder()  + "\n"
             + "PostOrder : " + postOrder();
    }

    @Override
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString().trim();
    }

    private void inOrderRec(Node node, StringBuilder sb) {
        if (node == null) return;
        inOrderRec(node.left,  sb);
        sb.append(node.data).append(" ");
        inOrderRec(node.right, sb);
    }

    @Override
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRec(root, sb);
        return sb.toString().trim();
    }

    private void preOrderRec(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.data).append(" ");
        preOrderRec(node.left,  sb);
        preOrderRec(node.right, sb);
    }

    @Override
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRec(root, sb);
        return sb.toString().trim();
    }

    private void postOrderRec(Node node, StringBuilder sb) {
        if (node == null) return;
        postOrderRec(node.left,  sb);
        postOrderRec(node.right, sb);
        sb.append(node.data).append(" ");
    }

    @Override
    public E findMin() throws ExceptionIsEmpty, ItemNotFound {
        if (isEmpty())
            throw new ExceptionIsEmpty("findMin(): el árbol está vacío.");
        return search(findMinNode(root).data);
    }

    @Override
    public E findMax() throws ExceptionIsEmpty, ItemNotFound {
        if (isEmpty())
            throw new ExceptionIsEmpty("findMax(): el árbol está vacío.");
        return search(findMaxNode(root).data);
    }

    private Node findMinNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMaxNode(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }
}
