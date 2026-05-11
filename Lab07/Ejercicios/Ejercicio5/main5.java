package Ejercicios.Ejercicio5;

public class main5 {
    public static void main(String[] args) {

        LinkedBST<Integer> inventario = new LinkedBST<>();

        // a) Insertar productos por código
        System.out.println("=== a) INSERTAR PRODUCTOS ===");
        int[] codigos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 45, 65, 90};
        for (int codigo : codigos) {
            try {
                inventario.insert(codigo);
                System.out.println("Producto insertado, código: " + codigo);
            } catch (ItemDuplicated e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nInventario InOrden: " + inventario.toString());

        // Insertar código duplicado
        try {
            inventario.insert(50);
        } catch (ItemDuplicated e) {
            System.out.println("Duplicado capturado: " + e.getMessage());
        }

        // b) searchRange
        System.out.println("\n=== b) searchRange(25, 65) ===");
        System.out.println("Productos en rango [25, 65]: "
                + inventario.searchRange(25, 65));

        System.out.println("Productos en rango [10, 30]: "
                + inventario.searchRange(10, 30));

        // c) countLeaves
        System.out.println("\n=== c) countLeaves ===");
        System.out.println("Productos en nodos hoja: " + inventario.countLeaves());

        // d) printDescending
        System.out.println("\n=== d) printDescending ===");
        inventario.printDescending();

        // Prueba de delete y búsqueda
        System.out.println("\n=== EXTRA: delete y search ===");
        try {
            inventario.delete(50);
            System.out.println("Eliminado código 50.");
            System.out.println("InOrden tras eliminar 50: " + inventario.toString());
        } catch (ExceptionIsEmpty | ItemNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Buscar código 40: " + inventario.search(40));
            System.out.println("Buscar código 50: " + inventario.search(50));
        } catch (ItemNotFound e) {
            System.out.println("No encontrado: " + e.getMessage());
        }
    }
}