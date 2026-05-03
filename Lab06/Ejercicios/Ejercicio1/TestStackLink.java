package Ejercicios.Ejercicio1;
import Actividad01.*;

public class TestStackLink {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("========================================");
        System.out.println("   TEST PILA CON LISTA - EJERCICIO 1");
        System.out.println("========================================\n");

        StackLink<Integer> stack = new StackLink<>();

        System.out.println("--- push() ---");
        stack.push(10);
        System.out.println("push(10) → " + stack);
        stack.push(20);
        System.out.println("push(20) → " + stack);
        stack.push(30);
        System.out.println("push(30) → " + stack);
        stack.push(40);
        System.out.println("push(40) → " + stack);

        System.out.println("\n--- top() y pop() ---");
        System.out.println("top()  → " + stack.top());
        System.out.println("pop()  → " + stack.pop() + "  | pila: " + stack);
        System.out.println("pop()  → " + stack.pop() + "  | pila: " + stack);

        System.out.println("\n--- isEmpty() ---");
        System.out.println("isEmpty() : " + stack.isEmpty());

        System.out.println("\n--- Test ExceptionIsEmpty ---");
        StackLink<Integer> stackVacia = new StackLink<>();
        try {
            stackVacia.pop();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
        try {
            stackVacia.top();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }

        System.out.println("\n--- Test con String ---");
        StackLink<String> ss = new StackLink<>();
        ss.push("primero");
        ss.push("segundo");
        ss.push("tercero");
        System.out.println("Pila  : " + ss);
        System.out.println("top() : " + ss.top());
        System.out.println("pop() : " + ss.pop() + "  | pila: " + ss);
    }
}