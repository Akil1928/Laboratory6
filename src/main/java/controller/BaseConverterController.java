package controller;

import domain.StackException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import domain.LinkedStack;
import javafx.scene.layout.HBox;

public class BaseConverterController {

    @FXML
    private BorderPane bp;

    @FXML
    private TextField textFieldDecimalValue;
    private LinkedStack<Integer> stack = new LinkedStack<>();

    @FXML
    private Button AddButton;

    @FXML
    private Button ClearButton;

    @FXML
    private HBox HboxOptions;

    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton PrefixButton;

    @FXML
    private RadioButton binaryRadioButton;

    @FXML
    private RadioButton octalRadioButton;

    @FXML
    private RadioButton hexadecimalRadioButton;
    @FXML
    private TextField TextFieldPrefix;
    @FXML
    private TextField TextFieldExpression;
    @FXML
    private RadioButton PostfixButton;
    @FXML
    private RadioButton InfixButton;
    @FXML
    private TextField TextFieldSufix;

    public BaseConverterController() {
    }

    @FXML
    private TextField TextFieldResult;

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        assert binaryRadioButton != null : "fx:id=\"binaryRadioButton\" was not injected: check your FXML file 'BaseConverter.fxml'.";
        assert octalRadioButton != null : "fx:id=\"octalRadioButton\" was not injected: check your FXML file 'BaseConverter.fxml'.";
        assert hexadecimalRadioButton != null : "fx:id=\"hexadecimalRadioButton\" was not injected: check your FXML file 'BaseConverter.fxml'.";

        binaryRadioButton.setToggleGroup(toggleGroup);
        octalRadioButton.setToggleGroup(toggleGroup);
        hexadecimalRadioButton.setToggleGroup(toggleGroup);

        binaryRadioButton.setSelected(true);
    }

    @FXML
    public void clearOnAction(ActionEvent event) {
        textFieldDecimalValue.clear();
        TextFieldResult.clear();
    }

    @FXML
    public void addOnAction(ActionEvent event) {
        try {
            int decimalValue = Integer.parseInt(textFieldDecimalValue.getText());
            String result = "";

            if (binaryRadioButton.isSelected()) {
                result = decimalToBinary(decimalValue);
            } else if (octalRadioButton.isSelected()) {
                result = decimalToOctal(decimalValue);
            } else if (hexadecimalRadioButton.isSelected()) {
                result = decimalToHexadecimal(decimalValue);
            } else {
                showAlert("Error", "Por favor selecciona una opción de conversión.");
                return;
            }

            TextFieldResult.setText(result);

        } catch (NumberFormatException e) {
            showAlert("Entrada Inválida", "Por favor, ingresa un número decimal válido.");
            textFieldDecimalValue.clear();
        } catch (StackException e) {
            throw new RuntimeException(e);
        }
    }

    private String decimalToBinary(int decimal) throws StackException {
        stack.clear();
        if (decimal == 0) {
            return "0";
        }
        while (decimal > 0) {
            stack.push(decimal % 2);
            decimal /= 2;
        }
        return getStackResult();
    }

    private String decimalToOctal(int decimal) throws StackException {
        stack.clear();
        if (decimal == 0) {
            return "0";
        }
        while (decimal > 0) {
            stack.push(decimal % 8);
            decimal /= 8;
        }
        return getStackResult();
    }

    private String decimalToHexadecimal(int decimal) throws StackException {
        stack.clear();
        if (decimal == 0) {
            return "0";
        }
        while (decimal > 0) {
            int remainder = decimal % 16;
            stack.push(remainder);
            decimal /= 16;
        }
        return getHexadecimalResult();
    }

    private String getStackResult() throws StackException {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    private String getHexadecimalResult() throws StackException {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            int digit = stack.pop();
            if (digit < 10) {
                result.append((char) (digit + '0'));
            } else {
                result.append((char) (digit - 10 + 'A'));
            }
        }
        return result.toString();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Deprecated
    public void employeeIdOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void titleOnAction(ActionEvent actionEvent) {
    }
}