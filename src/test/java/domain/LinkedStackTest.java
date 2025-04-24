package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedStackTest {

    @Test
    void test() {
        LinkedStack linkedStack = new LinkedStack();

        try {
            for (int i = 0; i < 10; i++) {
                int value = util.Utility.random(30);
                System.out.println("Value ["+ value+"] pushed");
                linkedStack.push(value);
            }
            System.out.println(linkedStack);
            System.out.println(linkedStack);
        } catch (StackException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static boolean isBalanced(String expression) throws StackException {
        LinkedStack stack = new LinkedStack();

        for (char c : expression.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = (char) stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
            // Ignorar otros caracteres (como '0')
        }

        return stack.isEmpty();
    }
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }
    @Test
    void infixtest(){
        try {
            System.out.println("infix: (2*(1+((4*(2+1))+3))) to postFixt: "+util.Utility.infixToPostfixConverter("(2*(1+((4*(2+1))+3)))"));
        } catch (StackException e) {
            throw new RuntimeException(e);
        }
    }
}

