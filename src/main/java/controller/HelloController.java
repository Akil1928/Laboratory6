package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.scene.layout.BorderPane;

public class HelloController {
    @FXML
    private AnchorPane contentPane;

    @FXML
    private Text txtMessage;

    @FXML
    private void baseConverterOnAction(ActionEvent event) {
        try {
            System.out.println("Intentando cargar Base Converter...");
            BorderPane view = FXMLLoader.load(getClass().getResource("/baseConverter.fxml"));
            contentPane.getChildren().setAll(view);
            System.out.println("Base Converter cargado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al cargar Base Converter:");
            e.printStackTrace();
        }
    }

    @FXML
    private void Home(ActionEvent event) {
        txtMessage.setText("Laboratory No. 6");
        contentPane.getChildren().clear();
        contentPane.getChildren().add(txtMessage);
    }

    @FXML
    private void arithmeticConverterOnAction(ActionEvent event) {
        try {
            System.out.println("Arithmetic Converter button clicked!");

            BorderPane view = FXMLLoader.load(getClass().getResource("/arithmeticConverter.fxml"));
            contentPane.getChildren().setAll(view);
            System.out.println("Arithmetic Converter cargado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al cargar Arithmetic Converter:");
            e.printStackTrace();
        }
    }

    @FXML
    private void exitOnAction(ActionEvent event) {
        System.exit(0);
    }
}






