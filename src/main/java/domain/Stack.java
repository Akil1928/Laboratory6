package domain;

public interface Stack<T> {
    public int size();
    public void clear();
    public boolean isEmpty();
    public T peek() throws StackException;   // Devuelve T
    public T top() throws StackException;    // Devuelve T
    void push(T element) throws StackException;
    public T pop() throws StackException;     // Devuelve T
}
