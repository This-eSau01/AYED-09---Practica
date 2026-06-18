package Ejercicio2;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // ─── INSERT ───────────────────────────────────────────────────────────────

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado: " + cl);
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<>(this.orden);
        for (int i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // ─── TOSTRING ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(current.toString()).append("\n");
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null)
                sb.append(writeTree(current.childs.get(i)));
        }
        return sb.toString();
    }

    // ─── EJERCICIO 01: search ─────────────────────────────────────────────────

    public boolean search(E cl) {
        return searchRec(this.root, cl);
    }

    private boolean searchRec(BNode<E> current, E cl) {
        if (current == null) return false;
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println(cl + " se encuentra en el nodo " + current.idNode
                    + " en la posicion " + pos[0]);
            return true;
        }
        return searchRec(current.childs.get(pos[0]), cl);
    }

    // ─── EJERCICIO 02: searchRange ────────────────────────────────────────────

    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Rango invalido: min > max");
            return;
        }
        System.out.print("Claves en rango [" + min + ", " + max + "]: ");
        StringBuilder sb = new StringBuilder();
        searchRangeRec(this.root, min, max, sb);
        if (sb.length() == 0)
            System.out.println("(ninguna clave encontrada en ese rango)");
        else
            System.out.println(sb.toString().trim());
    }

    private void searchRangeRec(BNode<E> current, E min, E max, StringBuilder sb) {
        if (current == null) return;
        int i = 0;
        while (i < current.count) {
            E key = current.keys.get(i);
            // Bajar por hijo izquierdo solo si puede haber claves >= min
            if (key.compareTo(min) > 0)
                searchRangeRec(current.childs.get(i), min, max, sb);
            // Agregar clave si está dentro del rango
            if (key.compareTo(min) >= 0 && key.compareTo(max) <= 0)
                sb.append(key).append(" ");
            // Si la clave ya supera el max, no tiene sentido seguir
            if (key.compareTo(max) > 0) return;
            i++;
        }
        // Bajar por el último hijo
        searchRangeRec(current.childs.get(current.count), min, max, sb);
    }

    // ─── EJERCICIO 03: remove ─────────────────────────────────────────────────

    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        removeRec(null, this.root, cl, 0);
        // Si la raiz quedo vacia tras una fusion, bajar un nivel
        if (this.root.count == 0) {
            this.root = this.root.childs.get(0);
        }
    }

    private void removeRec(BNode<E> parent, BNode<E> current, E cl, int parentIndex) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            return;
        }
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);

        if (found) {
            // Caso: la clave está en un nodo interno → reemplazar por sucesor
            if (current.childs.get(pos[0] + 1) != null) {
                E successor = getMin(current.childs.get(pos[0] + 1));
                current.keys.set(pos[0], successor);
                removeRec(current, current.childs.get(pos[0] + 1), successor, pos[0] + 1);
            } else {
                // Está en hoja: eliminar directamente
                deleteFromNode(current, pos[0]);
            }
        } else {
            // No encontrada aquí, bajar
            removeRec(current, current.childs.get(pos[0]), cl, pos[0]);
        }

        // Revisar si el hijo visitado quedó con underflow
        if (parent != null) {
            BNode<E> child = parent.childs.get(parentIndex);
            if (child != null && child.count < (this.orden / 2) - 1 + 1) {
                fixUnderflow(parent, parentIndex);
            }
        }
    }

    private E getMin(BNode<E> node) {
        if (node.childs.get(0) == null) return node.keys.get(0);
        return getMin(node.childs.get(0));
    }

    private void deleteFromNode(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
            node.childs.set(i + 1, node.childs.get(i + 2));
        }
        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);
        node.count--;
    }

    private void fixUnderflow(BNode<E> parent, int childIndex) {
        BNode<E> child = parent.childs.get(childIndex);
        int minKeys = (this.orden / 2) - 1 + 1; // ceil(orden/2) - 1

        BNode<E> leftSibling  = (childIndex > 0) ? parent.childs.get(childIndex - 1) : null;
        BNode<E> rightSibling = (childIndex < parent.count) ? parent.childs.get(childIndex + 1) : null;

        if (rightSibling != null && rightSibling.count > minKeys) {
            // Redistribuir desde hermano derecho
            child.keys.set(child.count, parent.keys.get(childIndex));
            child.childs.set(child.count + 1, rightSibling.childs.get(0));
            child.count++;
            parent.keys.set(childIndex, rightSibling.keys.get(0));
            deleteFromNode(rightSibling, 0);
            rightSibling.childs.set(0, rightSibling.childs.get(0));
        } else if (leftSibling != null && leftSibling.count > minKeys) {
            // Redistribuir desde hermano izquierdo
            for (int i = child.count; i > 0; i--) {
                child.keys.set(i, child.keys.get(i - 1));
                child.childs.set(i + 1, child.childs.get(i));
            }
            child.childs.set(1, child.childs.get(0));
            child.keys.set(0, parent.keys.get(childIndex - 1));
            child.childs.set(0, leftSibling.childs.get(leftSibling.count));
            child.count++;
            parent.keys.set(childIndex - 1, leftSibling.keys.get(leftSibling.count - 1));
            leftSibling.keys.set(leftSibling.count - 1, null);
            leftSibling.childs.set(leftSibling.count, null);
            leftSibling.count--;
        } else {
            // Fusionar
            if (rightSibling != null) {
                mergeNodes(parent, child, rightSibling, childIndex);
            } else {
                mergeNodes(parent, leftSibling, child, childIndex - 1);
            }
        }
    }

    private void mergeNodes(BNode<E> parent, BNode<E> left, BNode<E> right, int sepIndex) {
        // Bajar la clave separadora del padre al nodo izquierdo
        left.keys.set(left.count, parent.keys.get(sepIndex));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;
        // Copiar todo el nodo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }
        // Eliminar la clave separadora y puntero al nodo derecho del padre
        for (int i = sepIndex; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
}