import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Capacidad máxima del contenedor: ");
        int capacidad = sc.nextInt();
        ContainerRect contenedor = new ContainerRect(capacidad);

        System.out.println("\n=== Rectángulo 1 ===");
        System.out.print("Esquina x1: "); double x1 = sc.nextDouble();
        System.out.print("Esquina y1: "); double y1 = sc.nextDouble();
        System.out.print("Esquina x2: "); double x2 = sc.nextDouble();
        System.out.print("Esquina y2: "); double y2 = sc.nextDouble();
        Rectangulo rec1 = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        contenedor.addRectangulo(rec1);

        System.out.println("\n=== Rectángulo 2 ===");
        System.out.print("Esquina x3: "); double x3 = sc.nextDouble();
        System.out.print("Esquina y3: "); double y3 = sc.nextDouble();
        System.out.print("Esquina x4: "); double x4 = sc.nextDouble();
        System.out.print("Esquina y4: "); double y4 = sc.nextDouble();
        Rectangulo rec2 = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));
        contenedor.addRectangulo(rec2);

        // Gráfico
        Graficador.graficar(rec1, rec2);

        // Relación espacial
        if (Verificador.seSobreponen(rec1, rec2))
            System.out.println("Resultado: los rectángulos SE SOBREPONEN");
        else if (Verificador.seJuntan(rec1, rec2))
            System.out.println("Resultado: los rectángulos SE JUNTAN");
        else
            System.out.println("Resultado: los rectángulos son DISJUNTOS");

        // Mostrar contenedor
        System.out.println();
        System.out.println(contenedor);

        sc.close();
    }
}