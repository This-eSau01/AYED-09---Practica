import java.util.*;

public class Rectangulo {

    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    public void setEsquina1(Coordenada coo) {
        esquina1 = new Coordenada(coo);
    }

    public void setEsquina2(Coordenada coo) {
        esquina2 = new Coordenada(coo);
    }

    public Coordenada getEsquina1() {
        return esquina1;
    }

    public Coordenada getEsquina2() {
        return esquina2;
    }
    @Override
    public String toString() {
        return "Rectangulo: " + esquina1 + " , " + esquina2;
    }
}