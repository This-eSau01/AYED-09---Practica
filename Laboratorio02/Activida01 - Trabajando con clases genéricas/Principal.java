public class Principal {
    public static void main(String[] args){
        Bolsa <Chocolatina> bolsaCho = new Bolsa <Chocolatina>(5);
        Chocolatina c = new Chocolatina("Sublime");
        Chocolatina c1 = new Chocolatina("Iberica");
        Chocolatina c2 = new Chocolatina("Ferrero");
        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);

        Bolsa <Golosina> golosinaBolsa = new Bolsa<Golosina>(3);
        Golosina g1 = new Golosina("Alfajor", 25);
        Golosina g2 = new Golosina("Caramelo", 10);
        golosinaBolsa.add(g1);
        golosinaBolsa.add(g2);


        for (Chocolatina chocolatina: bolsaCho){
            System.out.println(chocolatina.getMarca());
        }

        for(Golosina golosina : golosinaBolsa){
            System.out.println(golosina.getNombre());
            System.out.println(golosina.getPeso());
        }

    }



}