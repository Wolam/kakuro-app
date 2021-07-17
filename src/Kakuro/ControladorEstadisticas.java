package Kakuro;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorEstadisticas {

    @FXML
    private TextField ingresoDigitos;

    @FXML
    private TextField verificacionesRealizadas;

    @FXML
    private TextField erroresVerificacion;

    @FXML
    private TextField sugerenciasUtilizadas;

    @FXML
    private TextField tipoFinalizacion;

    private String celdasIngresoDigitos;

    private int contadorVerificaciones = 0;

    private int cantidadErroresVerifcacion = 0;

    private int cantidadSugerenciasUtilizadas = 0;

    private String finalizacion;

    /* Entradas: No recibe parametros
       Salidas: No devuelve nada
       Coloca las estadisticas del juego en los TextFields
     */

    public void setTextFields() {
        ingresoDigitos.setText(celdasIngresoDigitos);
        verificacionesRealizadas.setText(contadorVerificaciones + "");
        erroresVerificacion.setText(cantidadErroresVerifcacion + "");
        sugerenciasUtilizadas.setText(cantidadSugerenciasUtilizadas + "");
        tipoFinalizacion.setText(finalizacion);
    }

    /*
        Setters de los atributos
     */

    public void setCeldasIngresoDigitos(String celdasIngresoDigitos) {
        this.celdasIngresoDigitos = celdasIngresoDigitos;
    }

    public void setContadorVerificaciones(int contadorVerificaciones) {
        this.contadorVerificaciones = contadorVerificaciones;
    }


    public void setCantidadErroresVerificacion(int cantidadErroresVerifcacion) {
        this.cantidadErroresVerifcacion = cantidadErroresVerifcacion;
    }

    public void setCantidadSugerenciasUtilizadas(int cantidadSugerenciasUtilizadas) {
        this.cantidadSugerenciasUtilizadas = cantidadSugerenciasUtilizadas;
    }

    public void setFinalizacion(String finalizacion) {
        this.finalizacion = finalizacion;
    }

}
