public class TestGen {
    public static void main(String[] args) {

        System.out.println("===== PRUEBA CON GOLOSINAS =====");
        Cajoneria<Golosina> cajoneriaGolosinas = new Cajoneria<>();

        cajoneriaGolosinas.addCaja(new Caja<>("Rojo", new Golosina("Caramelo", 10)));
        cajoneriaGolosinas.addCaja(new Caja<>("Amarillo", new Golosina("Chicle", 5)));
        cajoneriaGolosinas.addCaja(new Caja<>("Verde", new Golosina("Gomita", 15)));
        cajoneriaGolosinas.addCaja(new Caja<>("Azul", new Golosina("Caramelo", 10)));
        cajoneriaGolosinas.addCaja(new Caja<>("Morado", new Golosina("Paleta", 20)));

        System.out.println(cajoneriaGolosinas);

        Golosina buscada = new Golosina("Caramelo", 10);
        System.out.println(cajoneriaGolosinas.search(buscada));

        System.out.println("Ocurrencias de Caramelo(10): " +
                cajoneriaGolosinas.contarOcurrencias(buscada));

        Golosina eliminada = cajoneriaGolosinas.delete(buscada);
        System.out.println("Eliminado: " + eliminada);

        System.out.println("Después de eliminar:");
        System.out.println(cajoneriaGolosinas);

        System.out.println("Ocurrencias ahora: " +
                cajoneriaGolosinas.contarOcurrencias(buscada));


        System.out.println("===== PRUEBA CON CHOCOLATINAS =====");
        Cajoneria<Chocolatina> cajoneriaChocolatinas = new Cajoneria<>();

        cajoneriaChocolatinas.addCaja(new Caja<>("Negro", new Chocolatina("Sublime", 25)));
        cajoneriaChocolatinas.addCaja(new Caja<>("Blanco", new Chocolatina("Triangulo", 30)));
        cajoneriaChocolatinas.addCaja(new Caja<>("Marrón", new Chocolatina("Sublime", 25)));
        cajoneriaChocolatinas.addCaja(new Caja<>("Gris", new Chocolatina("Princesa", 18)));
        cajoneriaChocolatinas.addCaja(new Caja<>("Naranja", new Chocolatina("Cua Cua", 22)));

        System.out.println(cajoneriaChocolatinas);

        Chocolatina chocoBuscada = new Chocolatina("Sublime", 25);
        System.out.println(cajoneriaChocolatinas.search(chocoBuscada));

        System.out.println("Ocurrencias de Sublime(25): " +
                cajoneriaChocolatinas.contarOcurrencias(chocoBuscada));

        Chocolatina chocoEliminada = cajoneriaChocolatinas.delete(chocoBuscada);
        System.out.println("Eliminado: " + chocoEliminada);

        System.out.println("Después de eliminar:");
        System.out.println(cajoneriaChocolatinas);
    }
}