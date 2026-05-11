package prueba;

import bstreelinklistinterfgeneric.LinkedBST;
import exceptions.*;

public class Prueba {

    static void sep(String titulo) {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("  " + titulo);
        System.out.println("=".repeat(55));
    }

    public static void main(String[] args) {

        sep("ACTIVIDAD 6 – insert / search / delete / toString");
        pruebaActividad6();

        sep("ACTIVIDAD 7 – Recorrido InOrder");
        pruebaInOrder();

        sep("ACTIVIDAD 8 – Recorrido PreOrder");
        pruebaPreOrder();

        sep("ACTIVIDAD 9 – Recorrido PostOrder");
        pruebaPostOrder();

        sep("ACTIVIDAD 10 – findMin / findMax");
        pruebaMinMax();
    }
    static void pruebaActividad6() {
        LinkedBST<Integer> bst = new LinkedBST<>();

        System.out.println("isEmpty() = " + bst.isEmpty());

        // Inserción normal
        try {
            for (int v : new int[]{8, 3, 1, 20, 10, 5, 4}) {
                bst.insert(v);
                System.out.println("insert(" + v + ") OK");
            }
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // Intento de duplicado
        System.out.println("\n-- Intento de insertar duplicado (10) --");
        try {
            bst.insert(10);
        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        }

        // Search
        System.out.println("\n-- search --");
        try {
            System.out.println("search(5)  = " + bst.search(5));
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("search(99) = " + bst.search(99));
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n-- toString --\n" + bst);

        System.out.println("\n-- delete --");
        try {
            bst.delete(4);   // Caso 1: hoja
            bst.delete(1);   // Caso 1: hoja
            bst.delete(3);   // Caso 3: dos hijos
        } catch (ExceptionIsEmpty | ItemNotFound e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nÁrbol tras eliminar 4, 1 y 3:\n" + bst);
    }
    static LinkedBST<Integer> crearArbol() {
        LinkedBST<Integer> bst = new LinkedBST<>();
        try {
            for (int v : new int[]{400, 100, 700, 50, 200, 75})
                bst.insert(v);
        } catch (ItemDuplicated ignored) {}
        return bst;
    }

    static void pruebaInOrder() {
        LinkedBST<Integer> bst = crearArbol();
        System.out.println("Árbol: 400, 100, 700, 50, 200, 75");
        System.out.println("InOrder (izq -> raíz -> der): " + bst.inOrder());
        System.out.println("Esperado                    : 50 75 100 200 400 700");
    }
    static void pruebaPreOrder() {
        LinkedBST<Integer> bst = crearArbol();
        System.out.println("Árbol: 400, 100, 700, 50, 200, 75");
        System.out.println("PreOrder (raíz -> izq -> der): " + bst.preOrder());
        System.out.println("Esperado                     : 400 100 50 75 200 700");
    }
    static void pruebaPostOrder() {
        LinkedBST<Integer> bst = crearArbol();
        System.out.println("Árbol: 400, 100, 700, 50, 200, 75");
        System.out.println("PostOrder (izq -> der -> raíz): " + bst.postOrder());
        System.out.println("Esperado                      : 75 50 200 100 700 400");
    }

    static void pruebaMinMax() {
        LinkedBST<Integer> bst = crearArbol();
        System.out.println("Árbol: 400, 100, 700, 50, 200, 75");
        try {
            System.out.println("findMin() = " + bst.findMin() + "  (nodo más a la izquierda)");
            System.out.println("findMax() = " + bst.findMax() + "  (nodo más a la derecha)");
        } catch (ExceptionIsEmpty | ItemNotFound e) {
            System.out.println(e.getMessage());
        }

        LinkedBST<Integer> vacio = new LinkedBST<>();
        System.out.println("\n-- Árbol vacío --");
        try {
            vacio.findMin();
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
