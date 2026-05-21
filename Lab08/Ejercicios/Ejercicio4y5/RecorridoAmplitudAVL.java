package Ejercicios.Ejercicio4y5;

public class RecorridoAmplitudAVL {

    static class Node {
        int data, height;
        Node left, right;
        Node(int d) { data = d; height = 1; }
    }

    static int height(Node n) { return n == null ? 0 : n.height; }

    static int bf(Node n) { return n == null ? 0 : height(n.right) - height(n.left); }

    static void updateHeight(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }

    static Node rotateLeft(Node x) {
        Node y = x.right; x.right = y.left; y.left = x;
        updateHeight(x); updateHeight(y); return y;
    }

    static Node rotateRight(Node y) {
        Node x = y.left; y.left = x.right; x.right = y;
        updateHeight(y); updateHeight(x); return x;
    }

    static Node balance(Node n) {
        updateHeight(n);
        int b = bf(n);
        if (b > 1) { if (bf(n.right) < 0) n.right = rotateRight(n.right); return rotateLeft(n); }
        if (b < -1) { if (bf(n.left) > 0) n.left = rotateLeft(n.left);    return rotateRight(n); }
        return n;
    }

    static Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if      (data < node.data) node.left  = insert(node.left,  data);
        else if (data > node.data) node.right = insert(node.right, data);
        else return node;
        return balance(node);
    }

    static void printLevel(Node node, int level) {
        if (node == null) return;
        if (level == 0) { System.out.print(node.data + " "); return; }
        printLevel(node.left,  level - 1);
        printLevel(node.right, level - 1);
    }

    static void bfs(Node root) {
        int h = height(root);
        for (int level = 0; level < h; level++) {
            System.out.print("  Nivel " + level + ": ");
            printLevel(root, level);
            System.out.println();
        }
    }

    static void inorder(Node n) {
        if (n == null) return;
        inorder(n.left); System.out.print(n.data + " "); inorder(n.right);
    }

    static void preorder(Node n) {
        if (n == null) return;
        System.out.print(n.data + " "); preorder(n.left); preorder(n.right);
    }

    static void printTree(Node n, String prefix, boolean isLeft) {
        if (n == null) return;
        System.out.println(prefix + (isLeft ? "|-- " : "L-- ") + n.data + "(bf=" + bf(n) + ")");
        printTree(n.left,  prefix + (isLeft ? "|   " : "    "), true);
        printTree(n.right, prefix + (isLeft ? "|   " : "    "), false);
    }
} 