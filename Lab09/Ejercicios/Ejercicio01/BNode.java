package Ejercicio01;

import java.util.ArrayList;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;
    private static int counter = 0;

    public BNode(int n) {
        this.idNode = ++counter;
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    public static void resetCounter() {
        counter = 0;
    }

    public boolean nodeFull(int max) {
        return this.count >= max;
    }

    public boolean nodeEmpty() {
        return this.count == 0;
    }

    public boolean searchNode(E cl, int[] pos) {
        int i = 0;
        Comparable<E> cmp = (Comparable<E>) cl;
        while (i < this.count && cmp.compareTo(this.keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        return i < this.count && cmp.compareTo(this.keys.get(i)) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Node ").append(idNode).append(": ");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}