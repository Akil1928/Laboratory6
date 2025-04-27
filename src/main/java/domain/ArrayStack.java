package domain;

public class ArrayStack<T> implements Stack<T> {
    private final int n; // el tam max de la pila (ahora final)
    private int top; // para llevar el control del tope de la pila
    private T[] dataStack;

    // Constructor
    public ArrayStack(int n) {
        if (n <= 0) System.exit(1); // se sale
        this.n = n;
        this.top = -1; // indica q la pila esta vacia
        this.dataStack = (T[]) new Object[n];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void clear() {
        this.top = -1; // indica q la pila esta vacia
        this.dataStack = (T[]) new Object[n];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public T peek() throws StackException {
        if (isEmpty())
            throw new StackException("Array Stack is empty");
        return this.dataStack[top];
    }

    @Override
    public T top() throws StackException {
        if (isEmpty())
            throw new StackException("Array Stack is empty");
        return this.dataStack[top];
    }

    @Override
    public void push(T element) throws StackException {
        if (top == dataStack.length - 1)
            throw new StackException("Array Stack is full");
        dataStack[++top] = element;
    }

    @Override
    public T pop() throws StackException {
        if (isEmpty())
            throw new StackException("Array Stack is empty");
        return dataStack[top--];
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Array Stack is Empty";
        StringBuilder result = new StringBuilder("Array Stack Content:\n");
        try {
            ArrayStack<T> aux = new ArrayStack<>(size());
            while (!isEmpty()) {
                result.append(peek()).append("\n"); // Usar append()
                aux.push(pop());
            }
            while (!aux.isEmpty()) {
                push(aux.pop());
            }
        } catch (StackException ex) {
            System.out.println(ex.getMessage());
        }
        return result.toString();
    }
}
