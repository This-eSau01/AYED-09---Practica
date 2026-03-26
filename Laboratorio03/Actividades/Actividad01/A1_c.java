public class A1_c {
    public int suma(int[] vector, int tamanio){
        int result = 0;
        for (int i = 0; i < tamanio; i++){
            result = result + vector[i];
        }
        return result;
    }
}
