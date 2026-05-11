package exceptions;

public class ItemDuplicated extends Exception {
    public ItemDuplicated(String msg) { super(msg); }
    public ItemDuplicated()           { super("Error: el elemento ya existe en la estructura de datos."); }
}
