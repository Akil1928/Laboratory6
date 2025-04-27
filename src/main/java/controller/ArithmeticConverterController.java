package controller;

import domain.LinkedStack;
import domain.Stack;
import domain.StackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ArithmeticConverterController implements Initializable {
    @FXML
    private RadioButton PrefixButton;
    @FXML
    private TextField TextFieldPrefix;
    @FXML
    private Button AddButton;
    @FXML
    private TextField TextFieldExpression;
    @FXML
    private RadioButton PostfixButton;
    @FXML
    private Button ClearButton;
    @FXML
    private RadioButton InfixButton;
    @FXML
    private HBox HboxOptions;
    @FXML
    private BorderPane bp;
    @FXML
    private TextField TextFieldSufix;

    @FXML
    private Label labelExpression;
    @FXML
    private Label labelPostfix;
    @FXML
    private Label labelPrefix;

    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        InfixButton.setToggleGroup(toggleGroup);
        PrefixButton.setToggleGroup(toggleGroup);
        PostfixButton.setToggleGroup(toggleGroup);

        InfixButton.setSelected(true);

        labelExpression = (Label) TextFieldExpression.getParent().getChildrenUnmodifiable().stream()
                .filter(node -> node instanceof Label && ((Label) node).getText().contains("Expression"))
                .findFirst().orElse(null);

        labelPostfix = (Label) TextFieldSufix.getParent().getChildrenUnmodifiable().stream()
                .filter(node -> node instanceof Label && ((Label) node).getText().contains("Postfix"))
                .findFirst().orElse(null);

        labelPrefix = (Label) TextFieldPrefix.getParent().getChildrenUnmodifiable().stream()
                .filter(node -> node instanceof Label && ((Label) node).getText().contains("Prefix"))
                .findFirst().orElse(null);

        updateTextFieldStates();

        InfixButton.setOnAction(e -> updateTextFieldStates());
        PrefixButton.setOnAction(e -> updateTextFieldStates());
        PostfixButton.setOnAction(e -> updateTextFieldStates());
    }

    private void updateTextFieldStates() {
        TextFieldExpression.setEditable(true);
        TextFieldPrefix.setEditable(false);
        TextFieldSufix.setEditable(false);

        TextFieldExpression.clear();
        TextFieldPrefix.clear();
        TextFieldSufix.clear();

        TextFieldExpression.setStyle("-fx-background-color: white;");
        TextFieldPrefix.setStyle("-fx-background-color: lightgray;");
        TextFieldSufix.setStyle("-fx-background-color: lightgray;");

        if (InfixButton.isSelected()) {
            if (labelExpression != null) labelExpression.setText("Expression (Infix):");
            if (labelPrefix != null) labelPrefix.setText("Prefix:");
            if (labelPostfix != null) labelPostfix.setText("Postfix:");
        } else if (PrefixButton.isSelected()) {
            if (labelExpression != null) labelExpression.setText("Expression (Prefix):");
            if (labelPrefix != null) labelPrefix.setText("Infix:");
            if (labelPostfix != null) labelPostfix.setText("Postfix:");
        } else if (PostfixButton.isSelected()) {
            if (labelExpression != null) labelExpression.setText("Expression (Postfix):");
            if (labelPrefix != null) labelPrefix.setText("Prefix:");
            if (labelPostfix != null) labelPostfix.setText("Infix:");
        }
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        TextFieldExpression.clear();
        TextFieldPrefix.clear();
        TextFieldSufix.clear();
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        try {

            String expression = TextFieldExpression.getText().trim();
            if (expression.isEmpty()) {
                return;
            }


            if (InfixButton.isSelected()) {

                TextFieldPrefix.setText(infixToPrefixConverter(expression));
                TextFieldSufix.setText(infixToPostfixConverter(expression));
            } else if (PrefixButton.isSelected()) {

                TextFieldPrefix.setText(prefixToInfixConverter(expression));
                TextFieldSufix.setText(prefixToPostfixConverter(expression));
            } else if (PostfixButton.isSelected()) {

                TextFieldPrefix.setText(postfixToPrefixConverter(expression));
                TextFieldSufix.setText(postfixToInfixConverter(expression));
            }
        } catch (StackException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }



    private String infixToPostfixConverter(String infix) throws StackException {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new LinkedStack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);


            if (c == ' ') continue;


            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            }

            else if (c == '(') {
                stack.push(c);
            }

            else if (c == ')') {
                while (!stack.isEmpty() && stack.top() != '(') {
                    postfix.append(stack.pop());
                }

                if (!stack.isEmpty() && stack.top() == '(') {
                    stack.pop();
                }
            }

            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.top())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }


        while (!stack.isEmpty()) {
            if (stack.top() == '(') {
                return "Expresión infija inválida";
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private String infixToPrefixConverter(String infix) throws StackException {

        StringBuilder reversedInfix = new StringBuilder(infix).reverse();


        for (int i = 0; i < reversedInfix.length(); i++) {
            if (reversedInfix.charAt(i) == '(') {
                reversedInfix.setCharAt(i, ')');
            } else if (reversedInfix.charAt(i) == ')') {
                reversedInfix.setCharAt(i, '(');
            }
        }


        String postfix = infixToPostfixConverter(reversedInfix.toString());


        return new StringBuilder(postfix).reverse().toString();
    }

    private String postfixToInfixConverter(String postfix) throws StackException {
        Stack<String> stack = new LinkedStack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);


            if (c == ' ') continue;


            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            }

            else {
                if (stack.size() < 2) {
                    return "Expresión postfija inválida";
                }

                String operand2 = stack.pop();
                String operand1 = stack.pop();


                String infixExpr = "(" + operand1 + c + operand2 + ")";
                stack.push(infixExpr);
            }
        }

        if (stack.size() != 1) {
            return "Expresión postfija inválida";
        }

        return stack.pop();
    }

    private String prefixToInfixConverter(String prefix) throws StackException {
        Stack<String> stack = new LinkedStack<>();


        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);


            if (c == ' ') continue;


            if (Character.isLetterOrDigit(c)) {
                stack.push(String.valueOf(c));
            }

            else {
                if (stack.size() < 2) {
                    return "Expresión prefija inválida";
                }

                String operand1 = stack.pop();
                String operand2 = stack.pop();


                String infixExpr = "(" + operand1 + c + operand2 + ")";
                stack.push(infixExpr);
            }
        }

        if (stack.size() != 1) {
            return "Expresión prefija inválida";
        }

        return stack.pop();
    }

    private String prefixToPostfixConverter(String prefix) throws StackException {

        String infix = prefixToInfixConverter(prefix);
        return infixToPostfixConverter(infix);
    }

    private String postfixToPrefixConverter(String postfix) throws StackException {

        String infix = postfixToInfixConverter(postfix);
        return infixToPrefixConverter(infix);
    }

    private int precedence(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }
}