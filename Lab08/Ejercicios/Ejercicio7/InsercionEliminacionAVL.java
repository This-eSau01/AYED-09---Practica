package Ejercicios.Ejercicio7;
public class InsercionEliminacionAVL {

    static class Node {
        int data, height;
        Node left, right;
        Node(int d) { data = d; height = 1; }
    }

    static int totalRotaciones = 0;

    static int height(Node n) { return n == null ? 0 : n.height; }

    static int bf(Node n) { return n == null ? 0 : height(n.right) - height(n.left); }

    static void updateHeight(Node n) { n.height = 1 + Math.max(height(n.left), height(n.right)); }

    static Node rotateLeft(Node x) {
        totalRotaciones++;
        Node y = x.right; x.right = y.left; y.left = x;
        updateHeight(x); updateHeight(y); return y;
    }

    static Node rotateRight(Node y) {
        totalRotaciones++;
        Node x = y.left; y.left = x.right; x.right = y;
        updateHeight(y); updateHeight(x); return x;
    }

    static Node balance(Node n) {
        updateHeight(n);
        int b = bf(n);
        if (b > 1) {
            if (bf(n.right) < 0) { System.out.println("  RDI: RSR(" + n.right.data + ") + RSL(" + n.data + ")"); n.right = rotateRight(n.right); }
            else                    System.out.println("  RSL en nodo " + n.data);
            return rotateLeft(n);
        }
        if (b < -1) {
            if (bf(n.left) > 0) { System.out.println("  RDD: RSL(" + n.left.data + ") + RSR(" + n.data + ")"); n.left = rotateLeft(n.left); }
            else                   System.out.println("  RSR en nodo " + n.data);
            return rotateRight(n);
        }
        return n;
    }

    static Node insert(Node node, int data) {
        if (node == null) return new Node(data);
        if      (data < node.data) node.left  = insert(node.left,  data);
        else if (data > node.data) node.right = insert(node.right, data);
        else return node;
        return balance(node);
    }

    static Node minNode(Node n) { while (n.left != null) n = n.left; return n; }

    static Node delete(Node node, int data) {
        if (node == null) { System.out.println("  Clave " + data + " no encontrada."); return null; }
        if      (data < node.data) node.left  = delete(node.left,  data);
        else if (data > node.data) node.right = delete(node.right, data);
        else {
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            Node s = minNode(node.right);
            System.out.println("  Sucesor inorden: " + s.data);
            node.data  = s.data;
            node.right = delete(node.right, s.data);
        }
        return balance(node);
    }

    static void inorder(Node n) {
        if (n == null) return;
        inorder(n.left); System.out.print(n.data + " "); inorder(n.right);
    }

    static void printTree(Node n, String prefix, boolean isLeft) {
        if (n == null) return;
        System.out.println(prefix + (isLeft ? "|-- " : "L-- ") + n.data + "(bf=" + bf(n) + ")");
        printTree(n.left,  prefix + (isLeft ? "|   " : "    "), true);
        printTree(n.right, prefix + (isLeft ? "|   " : "    "), false);
    }
}