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

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    private static boolean isBalanced(String expression) throws StackException {
        LinkedStack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    void testIsBalanced() throws StackException {
        System.out.println("isBalanced(\"({[]})\"): " + isBalanced("({[]})"));
        assertTrue(isBalanced("({[]})"));

        System.out.println("isBalanced(\"([])\"): " + isBalanced("([])"));
        assertTrue(isBalanced("([])"));

        System.out.println("isBalanced(\"([)]\"): " + isBalanced("([)]"));
        assertFalse(isBalanced("([)]"));

        System.out.println("isBalanced(\"((()))\"): " + isBalanced("((()))"));
        assertTrue(isBalanced("((()))"));

        System.out.println("isBalanced(\"{[}\"): " + isBalanced("{[}"));
        assertFalse(isBalanced("{[}"));

        System.out.println("isBalanced(\"]\"): " + isBalanced("]"));
        assertFalse(isBalanced("]"));

        System.out.println("isBalanced(\"\"): " + isBalanced(""));
        assertTrue(isBalanced(""));
    }


    private static String decimalToBinary(int number) throws StackException {
        if (number == 0) {
            return "0";
        }

        LinkedStack<Integer> stack = new LinkedStack<>();

        while (number > 0) {
            stack.push(number % 2);
            number /= 2;
        }

        StringBuilder binary = new StringBuilder();
        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }
        return binary.toString();
    }

    @Test
    void testDecimalToBinary() throws StackException {
        int number1 = 5;
        System.out.println("Decimal: " + number1 + " → Binario: " + decimalToBinary(number1));
        assertEquals("101", decimalToBinary(number1));

        int number2 = 10;
        System.out.println("Decimal: " + number2 + " → Binario: " + decimalToBinary(number2));
        assertEquals("1010", decimalToBinary(number2));
    }

    @Test
    void infixtest() {
        try {
            System.out.println("infix: (2*(1+((4*(2+1))+3))) to postFixt: "+util.Utility.infixToPostfixConverter("(2*(1+((4*(2+1))+3)))"));
        } catch (StackException e) {
            throw new RuntimeException(e);
        }
    }
}

