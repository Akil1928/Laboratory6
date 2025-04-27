package domain;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int counter;

    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedStack() {
        this.top = null;
        this.counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        top = null;
        counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public T peek() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        return top.data;
    }

    @Override
    public T top() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        return top.data;
    }

    @Override
    public void push(T element) throws StackException {
        Node<T> newNode = new Node<>(element);
        if (!isEmpty())
            newNode.next = top;
        top = newNode;
        this.counter++;
    }

    @Override
    public T pop() throws StackException {
        if (isEmpty())
            throw new StackException("Linked Stack is empty");
        T topData = top.data;
        top = top.next;
        counter--;
        return topData;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Linked Stack is Empty";
        StringBuilder result = new StringBuilder("Linked Stack Content:\n");
        LinkedStack<T> aux = new LinkedStack<>();
        try {
            while (!isEmpty()) {
                result.append(peek()).append("\n");
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