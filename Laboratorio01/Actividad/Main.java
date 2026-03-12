import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        System.out.println("Esquina x1: ");
        double x1 = sc.nextDouble();
        System.out.println("Esquina x2: ");
        double x2 = sc.nextDouble();
        System.out.println("Esquina y1: ");
        double y1 = sc.nextDouble();
        System.out.println("Esquina y2: ");
        double y2 = sc.nextDouble();
        Coordenada c1 = new Coordenada(x1,y1);
        Coordenada c2 = new Coordenada(x2,y2);

        Rectangulo rec1 = new Rectangulo(c1,c2);

        System.out.println("Esquina x3: ");
        double x3 = sc.nextDouble();
        System.out.println("Esquina y3: ");
        double y3 = sc.nextDouble();
        System.out.println("Esquina x4: ");
        double x4 = sc.nextDouble();
        System.out.println("Esquina y4:");
        double y4 = sc.nextDouble();

        Coordenada c3 = new Coordenada(x3,y3);
        Coordenada c4 = new Coordenada(x4,y4);

        Rectangulo rec2 = new Rectangulo(c3,c4);

        Graficador.graficar(rec1, rec2);
        
        if (Verificador.seSobreponen(rec1,rec2)) {
            System.out.println("Sobreponen");
        }
        else if (Verificador.seJuntan(rec1,rec2)) {
            System.out.println("Juntan");
        } else {
            System.out.println("Disjuntos");
        }

        sc.close();

        }
    }
