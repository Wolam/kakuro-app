package Kakuro;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorInicio {

    @FXML
    private Button botonIniciarJuego;

    @FXML
    private Button botonSalirJuego;

    /* Entradas: Un evento
       Salidas: No devuelve nada
       Crea la nueva ventana que contiene el juego
     */

    @FXML
    void iniciarJuego(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/juego1.fxml"));
            Parent root = fxmlLoader.load();
            //Cargar nueva ventana de juego
            ControladorJuego controladorJuego = fxmlLoader.getController();
            controladorJuego.timer.start();
            Stage secondaryStage = new Stage();
            secondaryStage.setResizable(false);
            secondaryStage.setTitle("Juego 9x9");
            secondaryStage.setScene(new Scene(root, 1280, 780));
            secondaryStage.show();
            // Ocultar ventana de inicio
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Entradas: Un evento
       Salidas: No devuelve nada
       Termina el sistema
     */

    @FXML
    void finalizarJuego(MouseEvent e) {
        Stage stage = (Stage) botonSalirJuego.getScene().getWindow();
        stage.close();

    }

}
