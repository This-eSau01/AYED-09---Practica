import java.util.ArrayList;

public class Cajoneria<T> {
    private ArrayList<Caja<T>> cajas;

    public Cajoneria() {
        cajas = new ArrayList<>();
    }

    public void addCaja(Caja<T> caja) {
        cajas.add(caja);
    }

    public ArrayList<Caja<T>> getCajas() {
        return cajas;
    }

    // 4.1 search: retorna posición y color
    public String search(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            T contenido = cajas.get(i).getContenido();
            if (contenido != null && contenido.equals(elemento)) {
                return "Elemento encontrado en la posición " + i +
                        ", caja de color " + cajas.get(i).getColor();
            }
        }
        return "Elemento no encontrado";
    }

    // 4.2 delete: elimina y retorna el objeto; null si no existe
    public T delete(T elemento) {
        for (int i = 0; i < cajas.size(); i++) {
            T contenido = cajas.get(i).getContenido();
            if (contenido != null && contenido.equals(elemento)) {
                cajas.remove(i);
                return contenido;
            }
        }
        return null;
    }

    // 6. contar ocurrencias
    public int contarOcurrencias(T elemento) {
        int contador = 0;
        for (Caja<T> caja : cajas) {
            T contenido = caja.getContenido();
            if (contenido != null && contenido.equals(elemento)) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Posición\tColor Caja\tObjeto\n");

        for (int i = 0; i < cajas.size(); i++) {
            sb.append(i).append("\t\t")
                    .append(cajas.get(i).getColor()).append("\t\t")
                    .append(cajas.get(i).getContenido()).append("\n");
        }

        return sb.toString();
    }
}