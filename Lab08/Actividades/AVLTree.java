package avltree;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;


public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    protected class NodeAVL extends Node {
        protected int bf;

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data + "(bf=" + bf + ")";
        }
    }

    private boolean height;

    public AVLTree() {
        super();
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root   = insert(x, (NodeAVL) this.root);
    }

    protected Node insert(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;

        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);

        } else {
            int resC = node.data.compareTo(x);

            if (resC == 0)
                throw new ItemDuplicated(x + " ya se encuentra en el árbol.");

            // ── Insertar por la DERECHA (x > node.data) ──
            if (resC < 0) {
                fat.right = insert(x, (NodeAVL) node.right);
                if (this.height) {
                    switch (fat.bf) {
                        case -1:
                            fat.bf     = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf     = 1;
                            this.height = true;
                            break;
                        case 1:             // bf se vuelve 2 → desbalance derecha
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                    }
                }

            // ── Insertar por la IZQUIERDA (x < node.data) ──
            } else {
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) {
                    switch (fat.bf) {
                        case 1:
                            fat.bf     = 0;
                            this.height = false;
                            break;
                        case 0:
                            fat.bf     = -1;
                            this.height = true;
                            break;
                        case -1:            // bf se vuelve -2 → desbalance izquierda
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                    }
                }
            }
        }
        return fat;
    }

    @Override
    public void delete(E x) throws ItemNotFound {
        this.height = false;
        this.root   = delete(x, (NodeAVL) this.root);
    }

    protected Node delete(E x, NodeAVL node) throws ItemNotFound {
        if (node == null)
            throw new ItemNotFound(x + " no encontrado en el árbol.");

        NodeAVL fat = node;
        int resC = node.data.compareTo(x);

        if (resC < 0) {
            // Buscar por la derecha
            fat.right = delete(x, (NodeAVL) node.right);
            if (this.height) {
                fat = rebalanceLeft(fat);
            }

        } else if (resC > 0) {
            // Buscar por la izquierda
            fat.left = delete(x, (NodeAVL) node.left);
            if (this.height) {
                fat = rebalanceRight(fat);
            }

        } else {
            // Encontrado: aplicar casos BST
            if (node.left == null && node.right == null) {
                // Caso 1: hoja
                this.height = true;
                return null;

            } else if (node.left == null) {
                // Caso 2: solo hijo derecho
                this.height = true;
                return node.right;

            } else if (node.right == null) {
                // Caso 2: solo hijo izquierdo
                this.height = true;
                return node.left;

            } else {
                // Caso 3: dos hijos → sucesor inorden (mínimo del subárbol derecho)
                NodeAVL succ = (NodeAVL) minNode(node.right);
                System.out.println("   [AVL] Sucesor inorden de " + x + " → " + succ.data);
                fat.data  = succ.data;
                fat.right = delete(succ.data, (NodeAVL) node.right);
                if (this.height) {
                    fat = rebalanceLeft(fat);
                }
            }
        }
        return fat;
    }

    private NodeAVL rebalanceRight(NodeAVL node) {
        switch (node.bf) {
            case 1:  node.bf = 0;  this.height = true;  break;
            case 0:  node.bf = -1; this.height = false; break;
            case -1:
                node = balanceToRight(node);
                break;
        }
        return node;
    }
    private NodeAVL rebalanceLeft(NodeAVL node) {
        switch (node.bf) {
            case -1: node.bf = 0;  this.height = true;  break;
            case 0:  node.bf = 1;  this.height = false; break;
            case 1:
                node = balanceToLeft(node);
                break;
        }
        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1: // Caso Derecha-Derecha → RSL
                System.out.println("   [AVL] Caso DD: RSL en " + node.data
                        + " → nueva raíz: " + hijo.data);
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSL(node);
                break;

            case -1: // Caso Derecha-Izquierda → RDL (RSR en hijo + RSL en node)
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1: node.bf = 0; hijo.bf =  1; break;
                    case  0: node.bf = 0; hijo.bf =  0; break;
                    case  1: node.bf = -1; hijo.bf = 0; break;
                }
                nieto.bf  = 0;
                System.out.println("   [AVL] Caso DI: RDL en " + node.data
                        + " → RSR en " + hijo.data + ", RSL en " + node.data
                        + " → nueva raíz: " + nieto.data);
                node.right = rotateSR(hijo);
                node       = rotateSL(node);
                break;

            case 0: // ocurre en eliminación
                node.bf = 1;
                hijo.bf = -1;
                node    = rotateSL(node);
                this.height = false;
                break;
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        switch (hijo.bf) {
            case -1: // Caso Izquierda-Izquierda → RSR
                System.out.println("   [AVL] Caso II: RSR en " + node.data
                        + " → nueva raíz: " + hijo.data);
                node.bf = 0;
                hijo.bf = 0;
                node    = rotateSR(node);
                break;

            case 1: // Caso Izquierda-Derecha → RDR (RSL en hijo + RSR en node)
                NodeAVL nieto = (NodeAVL) hijo.right;
                switch (nieto.bf) {
                    case  1: node.bf = 0; hijo.bf = -1; break;
                    case  0: node.bf = 0; hijo.bf =  0; break;
                    case -1: node.bf = 1; hijo.bf =  0; break;
                }
                nieto.bf  = 0;
                System.out.println("   [AVL] Caso ID: RDR en " + node.data
                        + " → RSL en " + hijo.data + ", RSR en " + node.data
                        + " → nueva raíz: " + nieto.data);
                node.left = rotateSL(hijo);
                node      = rotateSR(node);
                break;

            case 0: // ocurre en eliminación
                node.bf = -1;
                hijo.bf =  1;
                node    = rotateSR(node);
                this.height = false;
                break;
        }
        return node;
    }

    /** Rotación Simple Izquierda (RSL) */
    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.right;
        node.right = p.left;
        p.left     = node;
        node       = p;
        return node;
    }

    /** Rotación Simple Derecha (RSR) */
    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.left;
        node.left = p.right;
        p.right   = node;
        node      = p;
        return node;
    }
    public int balanceFactor() {
        return balanceFactor(root);
    }

    private int balanceFactor(Node node) {
        if (node == null) return 0;
        return height(node.right) - height(node.left);
    }

    public void levelOrder() {
        System.out.print("Por niveles: ");
        int h = height();
        for (int i = 1; i <= h; i++) {
            printLevel(root, i);
        }
        System.out.println();
    }

    private void printLevel(Node node, int level) {
        if (node == null) return;
        if (level == 1) {
            System.out.print(node.data + " ");
        } else {
            printLevel(node.left,  level - 1);
            printLevel(node.right, level - 1);
        }
    }

    @Override
    public void printTree() {
        printTree(root, "", true);
    }

    @Override
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
