public class Zona {
    public String Mineral;
    public double Cantidad;
    public double Pureza;

    public Zona(String Mineral, double Cantidad, double Pureza) {
        this.Mineral = Mineral;
        this.Cantidad = Cantidad;
        this.Pureza = Pureza;
    }
    public String getMineral() {
        return Mineral;
    }
    public double getCantidad() {
        return Cantidad;
    }
    public double getPureza() {
        return Pureza;
    }
    public double getvalortotal() {
        return Cantidad*Pureza;
    }

    public String toString(){
        return " Mineral:" + Mineral + " Cantidad:" + Cantidad + " Pureza:" + Pureza;
    }
}
