import java.util.Stack;

public class PilaDemo {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();

        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(150);

        System.out.println(pila.pop()); // 30
    }
}