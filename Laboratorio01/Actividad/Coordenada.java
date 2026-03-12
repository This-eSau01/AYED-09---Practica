public class Coordenada {
    public double x;
    public double y;

    public Coordenada(){
        x =0;
        y =0;
    }
    public Coordenada(double x,double y){
        this.x=x;
        this.y=y;
    }
    public Coordenada(Coordenada c) {
        this.x=c.x;
        this.y=c.y;
    }
    public void setX() {
        this.x= x;
    }
    public void setY(){
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double distancia(Coordenada c){
        Math.sqrt(Math.pow(c.x-this.x,2)+Math.pow(c.y-this.y,2));
        return 0;
    }
    public double distancia(double x,double y){
        Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
        return x;
    }
    public String ToString(){
        return "Coordenada [x=" + x + ", y=" + y + "]";
    }
}
