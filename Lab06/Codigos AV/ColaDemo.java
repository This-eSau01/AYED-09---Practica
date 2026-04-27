import java.util.Queue;
import java.util.LinkedList;

public class ColaDemo {
    public static void main(String[] args) {
        Queue<Integer> cola = new LinkedList<>();

        cola.add(10);
        cola.add(20);
        cola.add(30);

        System.out.println(cola.poll()); // 10
    }
}
