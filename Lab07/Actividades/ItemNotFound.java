package exceptions;

public class ItemNotFound extends Exception {
    public ItemNotFound(String msg) { super(msg); }
    public ItemNotFound()           { super("Error: el elemento no se encuentra en la estructura de datos."); }
}
