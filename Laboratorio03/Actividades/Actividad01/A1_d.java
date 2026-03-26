public class A1_d {
    public void ordenar(int[]vector, int tamanio){
        int aux;
        for (int i = 0; i < tamanio; i++){
            for (int j = 0; j < tamanio-1; j++){
                if(vector[j] > vector[j+1]){
                    aux = vector[j];
                    vector[j]= vector[j+1];
                    vector[j+1] = aux;
                }
            }
        }

    }
}
