package Ejercicios.Ejercicio3;

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
        System.out.print("InOrden:   ");
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
        System.out.print("PreOrden:  ");
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

    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El árbol ya está vacío");
        root = null;
    }

    public int countAllNodes() {
        return countAllRec(root);
    }
    private int countAllRec(Node node) {
        if (node == null) return 0;
        return 1 + countAllRec(node.left) + countAllRec(node.right);
    }

    public int countNodes() {
        return countNodesRec(root);
    }
    private int countNodesRec(Node node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0;
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }

    public int height(E x) {
        Node target = findNodeIterative(root, x);
        if (target == null) return -1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(target);
        int height = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left  != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return height;
    }
    private Node findNodeIterative(Node root, E x) {
        Node current = root;
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if      (cmp == 0) return current;
            else if (cmp < 0)  current = current.left;
            else               current = current.right;
        }
        return null;
    }

    public int amplitude() {
        if (isEmpty()) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            if (levelSize > maxWidth) maxWidth = levelSize;
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left  != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        return maxWidth;
    }

    // ── Ejercicio 03 ──────────────────────────────────────────────────────────

    // a) areaBST — hojas * altura, iterativo con Queue
    public int areaBST() {
        if (isEmpty()) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int leafCount = 0;
        int treeHeight = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            treeHeight++;
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left == null && current.right == null) {
                    leafCount++;
                } else {
                    if (current.left  != null) queue.add(current.left);
                    if (current.right != null) queue.add(current.right);
                }
            }
        }
        return leafCount * treeHeight;
    }

    // b) drawBST — imprime el árbol nivel por nivel con sangría visual
    public void drawBST() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        int totalHeight = height(root.data);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // Sangría proporcional al nivel
            int spaces = (int) Math.pow(2, totalHeight - level + 1);
            StringBuilder sb = new StringBuilder();
            for (int s = 0; s < spaces; s++) sb.append(" ");
            String indent = sb.toString();
            System.out.print("Nivel " + level + ": ");
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                System.out.print(indent + current.data);
                if (current.left  != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
            System.out.println();
            level++;
        }
    }
}