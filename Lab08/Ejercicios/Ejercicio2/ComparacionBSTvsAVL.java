package Ejercicios.Ejercicio2;

public class ComparacionBSTvsAVL {

    static class NodeBST {
        int data;
        NodeBST left, right;
        NodeBST(int d) { data = d; }
    }

    static class NodeAVL {
        int data, height;
        NodeAVL left, right;
        NodeAVL(int d) { data = d; height = 1; }
    }

    static NodeBST insertBST(NodeBST node, int data) {
        if (node == null) return new NodeBST(data);
        if      (data < node.data) node.left  = insertBST(node.left,  data);
        else if (data > node.data) node.right = insertBST(node.right, data);
        return node;
    }

    static int heightBST(NodeBST n) {
        if (n == null) return 0;
        return 1 + Math.max(heightBST(n.left), heightBST(n.right));
    }

    static boolean searchBST(NodeBST n, int d) {
        if (n == null) return false;
        if (d == n.data) return true;
        return d < n.data ? searchBST(n.left, d) : searchBST(n.right, d);
    }

    static void inorderBST(NodeBST n) {
        if (n == null) return;
        inorderBST(n.left); System.out.print(n.data + " "); inorderBST(n.right);
    }

    static void printBST(NodeBST n, String prefix, boolean isLeft) {
        if (n == null) return;
        System.out.println(prefix + (isLeft ? "|-- " : "L-- ") + n.data);
        printBST(n.left,  prefix + (isLeft ? "|   " : "    "), true);
        printBST(n.right, prefix + (isLeft ? "|   " : "    "), false);
    }

    static int heightAVL(NodeAVL n) { return n == null ? 0 : n.height; }

    static int bfAVL(NodeAVL n) { return n == null ? 0 : heightAVL(n.right) - heightAVL(n.left); }

    static void updateAVL(NodeAVL n) { n.height = 1 + Math.max(heightAVL(n.left), heightAVL(n.right)); }

    static NodeAVL rotateLeftAVL(NodeAVL x) {
        NodeAVL y = x.right; x.right = y.left; y.left = x;
        updateAVL(x); updateAVL(y); return y;
    }

    static NodeAVL rotateRightAVL(NodeAVL y) {
        NodeAVL x = y.left; y.left = x.right; x.right = y;
        updateAVL(y); updateAVL(x); return x;
    }

    static NodeAVL balanceAVL(NodeAVL n) {
        updateAVL(n);
        int b = bfAVL(n);
        if (b > 1) {
            if (bfAVL(n.right) < 0) n.right = rotateRightAVL(n.right);
            return rotateLeftAVL(n);
        }
        if (b < -1) {
            if (bfAVL(n.left) > 0) n.left = rotateLeftAVL(n.left);
            return rotateRightAVL(n);
        }
        return n;
    }

    static NodeAVL insertAVL(NodeAVL node, int data) {
        if (node == null) return new NodeAVL(data);
        if      (data < node.data) node.left  = insertAVL(node.left,  data);
        else if (data > node.data) node.right = insertAVL(node.right, data);
        else return node;
        return balanceAVL(node);
    }

    static boolean searchAVL(NodeAVL n, int d) {
        if (n == null) return false;
        if (d == n.data) return true;
        return d < n.data ? searchAVL(n.left, d) : searchAVL(n.right, d);
    }

    static void inorderAVL(NodeAVL n) {
        if (n == null) return;
        inorderAVL(n.left); System.out.print(n.data + " "); inorderAVL(n.right);
    }

    static void printAVL(NodeAVL n, String prefix, boolean isLeft) {
        if (n == null) return;
        System.out.println(prefix + (isLeft ? "|-- " : "L-- ") + n.data + "(bf=" + bfAVL(n) + ")");
        printAVL(n.left,  prefix + (isLeft ? "|   " : "    "), true);
        printAVL(n.right, prefix + (isLeft ? "|   " : "    "), false);
    }

    static void comparar(String titulo, int[] datos) {
        System.out.println("\n--- " + titulo + " ---");
        NodeBST bstRoot = null;
        NodeAVL avlRoot = null;
        for (int d : datos) { bstRoot = insertBST(bstRoot, d); avlRoot = insertAVL(avlRoot, d); }

        System.out.println("\n[BST] Altura: " + heightBST(bstRoot));
        printBST(bstRoot, "  ", false);
        System.out.print("Inorden BST: "); inorderBST(bstRoot); System.out.println();

        System.out.println("\n[AVL] Altura: " + heightAVL(avlRoot));
        printAVL(avlRoot, "  ", false);
        System.out.print("Inorden AVL: "); inorderAVL(avlRoot); System.out.println();

        System.out.println("\nBusquedas:");
        int[] buscar = {datos[0], datos[datos.length - 1], 999};
        for (int b : buscar)
            System.out.println("  Buscar " + b + " -> BST: " + searchBST(bstRoot, b) + " | AVL: " + searchAVL(avlRoot, b));
    }
}