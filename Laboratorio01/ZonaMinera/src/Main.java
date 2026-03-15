import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    
    public static TerrenoMinero leerTerreno(String ruta) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String[] dims = br.readLine().trim().split("\\s+");
        int filas = Integer.parseInt(dims[0]);
        int columnas = Integer.parseInt(dims[1]);

        TerrenoMinero terreno = new TerrenoMinero(filas, columnas);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String[] p = br.readLine().trim().split("\\s+");
                terreno.setZona(i, j, new Zona(p[0], Double.parseDouble(p[1]), Double.parseDouble(p[2])));
            }
        }
        br.close();
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