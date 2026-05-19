package avltree;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class BSTree<E extends Comparable<E>> {

    protected class Node {
        protected E data;
        protected Node left;
        protected Node right;

        public Node(E data) {
            this.data  = data;
            this.left  = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node root;

    public BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // ── Inserción BST ─────────────────────────────────────
    public void insert(E x) throws ItemDuplicated {
        root = insert(x, root);
    }

    protected Node insert(E x, Node node) throws ItemDuplicated {
        if (node == null) return new Node(x);
        int cmp = node.data.compareTo(x);
        if (cmp == 0)  throw new ItemDuplicated(x + " ya existe en el árbol.");
        if (cmp < 0)   node.right = insert(x, node.right);
        else           node.left  = insert(x, node.left);
        return node;
    }

    // ── Búsqueda ──────────────────────────────────────────
    public boolean search(E x) {
        return search(x, root);
    }

    private boolean search(E x, Node node) {
        if (node == null) return false;
        int cmp = node.data.compareTo(x);
        if (cmp == 0)  return true;
        if (cmp < 0)   return search(x, node.right);
        return search(x, node.left);
    }

    // ── Eliminación BST ───────────────────────────────────
    public void delete(E x) throws ItemNotFound {
        root = delete(x, root);
    }

    protected Node delete(E x, Node node) throws ItemNotFound {
        if (node == null) throw new ItemNotFound(x + " no encontrado.");
        int cmp = node.data.compareTo(x);
        if (cmp < 0)      node.right = delete(x, node.right);
        else if (cmp > 0) node.left  = delete(x, node.left);
        else {
            // Caso 1: hoja
            if (node.left == null && node.right == null) return null;
            // Caso 2: un hijo
            if (node.left == null)  return node.right;
            if (node.right == null) return node.left;
            // Caso 3: dos hijos → sucesor inorden
            Node succ = minNode(node.right);
            node.data  = succ.data;
            node.right = delete(succ.data, node.right);
        }
        return node;
    }

    protected Node minNode(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    // ── Recorridos ────────────────────────────────────────
    public void inOrder() {
        System.out.print("Inorden   : ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder() {
        System.out.print("Preorden  : ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // ── Altura ────────────────────────────────────────────
    public int height() {
        return height(root);
    }

    protected int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ── Imprimir árbol en consola ─────────────────────────
    public void printTree() {
        printTree(root, "", true);
    }

    protected void printTree(Node node, String prefix, boolean isRoot) {
        if (node != null) {
            System.out.println(prefix + (isRoot ? "└── " : "├── ") + node);
            if (node.left != null || node.right != null) {
                printTree(node.left,  prefix + "    ", false);
                printTree(node.right, prefix + "    ", false);
            }
        }
    }
}
