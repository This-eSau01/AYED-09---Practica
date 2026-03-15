import java.io.File;
import java.util.Scanner;

public class Main {

    public static TerrenoMinero leerTerreno(String ruta) throws Exception {
        Scanner sc = new Scanner(new File(ruta));
        int filas = sc.nextInt();
        int columnas = sc.nextInt();

        TerrenoMinero terreno = new TerrenoMinero(filas, columnas);
        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                terreno.setZona(i, j, new Zona(sc.next(), sc.nextDouble(), sc.nextDouble()));

        sc.close();
        return terreno;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ruta del archivo: ");
        String ruta = sc.nextLine().trim();

        System.out.print("Tamanio k de la subregion: ");
        int k = sc.nextInt();

        TerrenoMinero terreno = leerTerreno(ruta);
        terreno.analizarRegion(k);

        sc.close();
    }
}