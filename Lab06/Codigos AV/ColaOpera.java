class Stack {
    private int[] pila;
    private int tope;
    private int capacidad;

    // Constructor
    public Stack(int tamaño) {
        capacidad = tamaño;
        pila = new int[capacidad];
        tope = -1; // pila vacía
    }

    // 🔹 push(x): apilar
    public void push(int x) {
        if (isFull()) {
            System.out.println("❌ Pila llena");
            return;
        }
        pila[++tope] = x;
        System.out.println("✔ Apilado: " + x);
    }

    // 🔹 pop(): desapilar
    public int pop() {
        if (isEmpty()) {
            System.out.println("❌ Pila vacía");
            return -1;
        }
        int eliminado = pila[tope--];
        return eliminado;
    }

    // 🔹 top(): ver cima
    public int top() {
        if (isEmpty()) {
            System.out.println("❌ Pila vacía");
            return -1;
        }
        return pila[tope];
    }

    // 🔹 destroyStack(): vaciar pila
    public void destroyStack() {
        tope = -1;
        System.out.println("💣 Pila destruida (vacía)");
    }

    // 🔹 isEmpty()
    public boolean isEmpty() {
        return tope == -1;
    }

    // 🔹 isFull()
    public boolean isFull() {
        return tope == capacidad - 1;
    }

    // Mostrar pila
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Pila vacía");
            return;
        }
        for (int i = tope; i >= 0; i--) {
            System.out.println("| " + pila[i] + " |");
        }
    }
}
