public class Golosina {
    private String Nombre;
    private double peso;

    public Golosina(String Nombre, double peso){
        this.Nombre = Nombre;
        this.peso = peso;
    }

    public String getNombre(){
        return Nombre;
    }

    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }

    public double getPeso(){
        return peso;
    }

    public void setPeso (double peso){
        this.peso = peso;
    }


}
