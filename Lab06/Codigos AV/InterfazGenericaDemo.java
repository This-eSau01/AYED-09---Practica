// 🔹 Interfaz genérica
interface Contenedor<T> {
    void guardar(T dato);

    T obtener();
}

class Caja<T> implements Contenedor<T> {

    private T dato;

    public void guardar(T dato) {
        this.dato = dato;
    }

    public T obtener() {
        return dato;
    }
}

public class InterfazGenericaDemo {
    public static void main(String[] args) {

        Caja<String> cajaTexto = new Caja<>();
        cajaTexto.guardar("Hola");

        System.out.println(cajaTexto.obtener()); // Hola

        Caja<Integer> cajaNumero = new Caja<>();
        cajaNumero.guardar(100);

        System.out.println(cajaNumero.obtener()); // 100
    }
}