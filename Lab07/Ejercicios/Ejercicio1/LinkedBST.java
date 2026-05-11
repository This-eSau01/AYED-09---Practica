package Ejercicios.Ejercicio1;


public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node {
        public E data;
        public Node left, right;
        public Node(E data) { this.data = data; }
    }

    private Node root;

    public LinkedBST() { this.root = null; }

    // isEmpty
    @Override
    public boolean isEmpty() { return root == null; }

    // insert
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

    // search
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

    // delete
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
            if (node.left == null && node.right == null) return null;   // Caso 1
            if (node.left  == null) return node.right;                  // Caso 2
            if (node.right == null) return node.left;                   // Caso 2
            Node successor = findMinNode(node.right);                   // Caso 3
            node.data  = successor.data;
            node.right = deleteRec(node.right, successor.data);
        }
        return node;
    }

    // inOrder (privado + público)
    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }
    public void inOrder() {
        System.out.print("InOrden:   ");
        inOrder(root);
        System.out.println();
    }

    // preOrder
    private void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }
    public void preOrder() {
        System.out.print("PreOrden:  ");
        preOrder(root);
        System.out.println();
    }

    // postOrder
    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }
    public void postOrder() {
        System.out.print("PostOrden: ");
        postOrder(root);
        System.out.println();
    }

    // findMinNode (usado internamente por delete)
    private Node findMinNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // findMin público
    public E findMin() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío, no hay mínimo");
        return findMinNode(root).data;
    }

    // findMax público
    public E findMax() throws ItemNotFound {
        if (isEmpty()) throw new ItemNotFound("Árbol vacío, no hay máximo");
        Node node = root;
        while (node.right != null) node = node.right;
        return node.data;
    }

    // toString — recorrido InOrden como String
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
}