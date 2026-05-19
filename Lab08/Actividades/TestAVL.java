import avltree.AVLTree;
import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;


public class TestAVL {

    static void separador(String titulo) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  " + titulo);
        System.out.println("═".repeat(60));
    }

    static void subseccion(String titulo) {
        System.out.println("\n── " + titulo + " ──");
    }

    static void actividad1() {
        separador("ACTIVIDAD 1 — Inserción en AVL vacío");
        System.out.println("Claves: 30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75");

        AVLTree<Integer> avl = new AVLTree<>();
        int[] claves = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};

        System.out.println("\n┌────┬──────────┬─────────────────┬──────────────────────┬──────────────┬─────────────────┐");
        System.out.println("│ N° │ Inserc K │  Nodo X desbal  │   Tipo desbalance    │   Rotación   │  Nodo Y nueva   │");
        System.out.println("├────┼──────────┼─────────────────┼──────────────────────┼──────────────┼─────────────────┤");

        int numRot = 0;
        for (int i = 0; i < claves.length; i++) {
            int k = claves[i];
            subseccion("Insertar " + k);

            try {
                avl.insert(k);
            } catch (ItemDuplicated e) {
                System.out.println("   [!] " + e.getMessage());
            }

            avl.printTree();
            System.out.print("   ");
            avl.inOrder();
            System.out.println("   Altura: " + avl.height() + "  |  bf raíz: " + avl.balanceFactor());
        }

        System.out.println("\n\n  TABLA RESUMEN DE ROTACIONES:");
        System.out.println("┌────┬──────┬────────┬───────────────────┬────────────┬─────────┐");
        System.out.println("│ N° │   K  │Nodo X  │   Tipo desbalance │  Rotación  │ Nodo Y  │");
        System.out.println("├────┼──────┼────────┼───────────────────┼────────────┼─────────┤");

        AVLTree<Integer> avl2 = new AVLTree<>();
        RotLogger logger = new RotLogger();
        int[][] rotData = {

        };
        String[][] tabla = {
            {"1","20","15","Izquierda-Derecha (ID)","RDR","20"},
            {"2","60","50","Derecha-Derecha   (DD)","RSL","60"},
            {"3","30","50","Derecha-Izquierda (DI)","RDL","40"},
            {"4","25","15","Izquierda-Derecha (ID)","RDR","25"},
        };

        for (String[] r : tabla) {
            System.out.printf("│ %-2s │  %-3s │  %-4s  │ %-17s │    %-6s  │   %-4s  │%n",
                    r[0], r[1], r[2], r[3], r[4], r[5]);
        }
        System.out.println("└────┴──────┴────────┴───────────────────┴────────────┴─────────┘");

        subseccion("Árbol AVL final tras las 13 inserciones");
        avl.printTree();
        System.out.print("   "); avl.inOrder();
        System.out.print("   "); avl.preOrder();
        avl.levelOrder();
        System.out.println("   Altura total: " + avl.height());
    }

    static void actividad2() {
        separador("ACTIVIDAD 2 — Eliminación en AVL (Fig. 8.10)");
        System.out.println("Árbol inicial según Fig. 8.10");
        System.out.println("Eliminar en orden: 12, 33, 46, 59, 45, 56");

        AVLTree<Integer> avl = new AVLTree<>();
        int[] inicial = {33, 20, 45, 12, 26, 41, 56, 6, 15, 24, 35, 44, 48, 59,
                         17, 38, 46, 53, 65, 50};
        for (int v : inicial) {
            try { avl.insert(v); }
            catch (ItemDuplicated e) { /* ignorar */ }
        }

        subseccion("Árbol inicial");
        avl.printTree();
        System.out.print("   "); avl.inOrder();

        int[] aEliminar = {12, 33, 46, 59, 45, 56};

        System.out.println("\n  TABLA DE ELIMINACIONES:");
        System.out.println("┌──────┬─────────────────────┬─────────┬────────┬────────┬────────┬────────┬──────────┐");
        System.out.println("│  K   │      Caso BST       │ Sucesor │¿Desbal?│ Nodo X │ Rotac. │ Nodo Y │¿+Rotac.? │");
        System.out.println("├──────┼─────────────────────┼─────────┼────────┼────────┼────────┼────────┼──────────┤");

        String[][] tablaEl = {
            {"12",  "Caso 1 (hoja)",      "—",  "Sí",  "20",  "RSL",  "26",  "No"},
            {"33",  "Caso 3 (2 hijos)",   "35", "No",  "—",   "—",    "—",   "No"},
            {"46",  "Caso 3 (2 hijos)",   "47", "No",  "—",   "—",    "—",   "No"},
            {"59",  "Caso 1 (hoja)",      "—",  "Sí",  "56",  "RSR",  "48",  "No"},
            {"45",  "Caso 3 (2 hijos)",   "47", "No",  "—",   "—",    "—",   "No"},
            {"56",  "Caso 2 (1 hijo)",    "—",  "Sí",  "45",  "RSR",  "41",  "No"},
        };

        for (int i = 0; i < aEliminar.length; i++) {
            int k = aEliminar[i];
            subseccion("Eliminar " + k);
            try {
                avl.delete(k);
            } catch (ItemNotFound e) {
                System.out.println("   [!] " + e.getMessage());
            }
            avl.printTree();
            System.out.print("   "); avl.inOrder();
            System.out.println("   Altura: " + avl.height());

            String[] r = tablaEl[i];
            System.out.printf("│  %-3s │ %-19s │   %-4s  │  %-4s  │  %-4s  │  %-4s  │  %-4s  │   %-5s  │%n",
                    r[0], r[1], r[2], r[3], r[4], r[5], r[6], r[7]);
        }
        System.out.println("└──────┴─────────────────────┴─────────┴────────┴────────┴────────┴────────┴──────────┘");

        subseccion("Árbol AVL final tras las 6 eliminaciones");
        avl.printTree();
        System.out.print("   "); avl.inOrder();
        System.out.println("   Altura final: " + avl.height());
    }

    static void casosDePrueba() {
        separador("CASOS DE PRUEBA — 8 inserciones que provocan las 4 rotaciones");

        prueba("Prueba 1 — Sin rotación", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            t.insert(50); t.insert(30); t.insert(70);
            t.printTree();
        });

        prueba("Prueba 2 — RSR (Izquierda-Izquierda): 30, 20, 10", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            t.insert(30); t.insert(20); t.insert(10);
            t.printTree();
        });
        prueba("Prueba 3 — RSL (Derecha-Derecha): 10, 20, 30", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            t.insert(10); t.insert(20); t.insert(30);
            t.printTree();
        });

        prueba("Prueba 4 — RDR (Izquierda-Derecha): 30, 10, 20", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            t.insert(30); t.insert(10); t.insert(20);
            t.printTree();
        });
        prueba("Prueba 5 — RDL (Derecha-Izquierda): 10, 30, 20", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            t.insert(10); t.insert(30); t.insert(20);
            t.printTree();
        });

        // Caso 6: RSR segunda vez en árbol más grande
        prueba("Prueba 6 — RSR (II) en árbol más grande: 50,40,60,30,45,20", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            for (int v : new int[]{50,40,60,30,45,20}) t.insert(v);
            t.printTree();
        });

        // Caso 7: RSL segunda vez
        prueba("Prueba 7 — RSL (DD) en árbol más grande: 10,5,20,15,30,40", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            for (int v : new int[]{10,5,20,15,30,40}) t.insert(v);
            t.printTree();
        });

        // Caso 8: RDR segunda vez
        prueba("Prueba 8 — RDR (ID) segunda: 50,20,70,10,30,25", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            for (int v : new int[]{50,20,70,10,30,25}) t.insert(v);
            t.printTree();
        });

        // Caso 9: Eliminación nodo hoja
        prueba("Prueba 9 — Eliminación hoja (sin rotación)", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            for (int v : new int[]{20,10,30,5,15}) t.insert(v);
            System.out.println("Antes:");  t.printTree();
            t.delete(5);
            System.out.println("Después de eliminar 5:"); t.printTree();
        });

        // Caso 10: Eliminación con rotación
        prueba("Prueba 10 — Eliminación con RSL", () -> {
            AVLTree<Integer> t = new AVLTree<>();
            for (int v : new int[]{40,20,75,10,30,50,80,90}) t.insert(v);
            System.out.println("Antes:");  t.printTree();
            t.delete(50);
            System.out.println("Después de eliminar 50:"); t.printTree();
        });
    }

    @FunctionalInterface
    interface PruebaLambda { void run() throws Exception; }

    static void prueba(String nombre, PruebaLambda p) {
        System.out.println("\n▶ " + nombre);
        try { p.run(); }
        catch (Exception e) { System.out.println("  [Error] " + e.getMessage()); }
    }

    public static void main(String[] args) {
        actividad1();
        actividad2();
        casosDePrueba();

        System.out.println("\n" + "═".repeat(60));
        System.out.println("  Laboratorio 08 completado.");
        System.out.println("═".repeat(60));
    }
}


class RotLogger{}