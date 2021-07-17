package Kakuro;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jpl7.Query;
import org.jpl7.Term;

import java.text.SimpleDateFormat;
import java.util.*;

import java.io.IOException;

public class ControladorJuego {

    private static final int SOLUCION_INCOMPLETA = 0;

    private static final int SOLUCION_INVALIDA = -1;

    private static final int COORD_INVALIDA = -1;

    private static final int MAXIMO_SOLUCIONES_TABLERO = 44;
    ControladorEstadisticas estadisticas = new ControladorEstadisticas();
    @FXML
    TextField ingresoCoordenadaX;
    @FXML
    TextField ingresoCoordenadaY;
    @FXML
    TextField ingresoNumero;
    @FXML
    Text textoCantidadSugerencias;
    private int contadorVerificaciones = 0;
    private int cantidadErroresVerificacion = 0;
    private int cantidadSugerencias = 1;
    private int numeroTablero = 1;
    private String tipoFinalizacion = "";
    private int[] Soluciones = new int[MAXIMO_SOLUCIONES_TABLERO];
    private Term SolucionKakuro;
    @FXML
    private GridPane tableroKakuro;

    @FXML
    private TextField textoCronometro;
    AnimationTimer timer = new AnimationTimer() {
        private long marcaSegundos;
        private long fraccionSegundos = 0;
        private long segundos = 0;

        @Override
        public void start() {
            marcaSegundos = System.currentTimeMillis() - fraccionSegundos;
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            this.fraccionSegundos = 0;
            this.segundos = 0;
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            if (marcaSegundos + 1000 <= newTime) {
                long deltaT = (newTime - marcaSegundos) / 1000;
                segundos += deltaT;
                marcaSegundos += 1000 * deltaT;
                textoCronometro.setText(formatearSegundos(segundos));
            }
        }
    };

    public void setSolucion(int i, int solucion) {
        Soluciones[i] = solucion;
    }

    public String[] toString(int[] lista) {
        String[] solucionesStr = Arrays.stream(Soluciones).mapToObj(String::valueOf).toArray(String[]::new);
        return solucionesStr;
    }

    /* Entradas: No recibe parametros
       Salidas: No retorna nada
       Actualiza el numero del tablero
     */

    public void actualizarNumeroTablero() {
        if (this.numeroTablero == 4) {
            this.numeroTablero = 1;
        } else {
            this.numeroTablero += 1;
        }
    }

    /* Entradas: No recibe parametros
       Salidas: No devuelve nada
       Se encarga de obtener las soluciones del usuario e introducirlas en una lista
     */

    @FXML
    public void obtenerSoluciones() {
        ObservableList<Node> casillasTablero = tableroKakuro.getChildren();
        int i = 0;
        int solucionActual;
        for (Node casilla : casillasTablero) {
            solucionActual = obtenerSolucion((VBox) casilla);
            if (solucionActual != SOLUCION_INVALIDA) {
                setSolucion(i, solucionActual);
                i++;
            }
        }
    }

    /* Entradas: Recibe una VBox
       Salidas: Retorna un entero
       Obtiene la solucion, si es que la hay, de cada casilla del tablero.
     */

    private int obtenerSolucion(VBox casilla) {
        if (contieneSolucion(casilla)) {
            TextField campoSolucion = (TextField) casilla.getChildren().get(0);
            String strSolucion = campoSolucion.getText();
            return formatearSolucion(strSolucion);
        } else {
            return SOLUCION_INVALIDA;
        }
    }

    /* Entradas: No recibe parametros
       Salidas: No retorna nada
       Reinicia el tablero de juego, es decir limpia las casillas
     */

    @FXML
    public void reiniciarTablero() {
        ObservableList<Node> casillasTablero = tableroKakuro.getChildren();
        for (Node node : casillasTablero) {
            VBox casilla = (VBox) node;
            if (contieneSolucion(casilla)) {
                TextField campoSolucion = (TextField) casilla.getChildren().get(0);
                campoSolucion.setText("");
            }
            Arrays.fill(Soluciones, SOLUCION_INCOMPLETA);
            timer.stop();
            timer.start();
            reiniciarEstadisticas();
        }

    }

    /* Entradas: No recibe parametros
       Salidas: No retorna nada
       Reinicia las estadisticas, al generar un nuevo kakuro
     */

    public void reiniciarEstadisticas() {
        contadorVerificaciones = 0;
        cantidadErroresVerificacion = 0;
        cantidadSugerencias = 0;
        tipoFinalizacion = "Abandono";
    }

    /* Entradas: Un VBox
       Salidas: Un booleano
       Verifica si la casilla tiene solucion
     */

    public boolean contieneSolucion(VBox casilla) {
        if (casilla == null) return false;
        ObservableList<Node> listaNodos = casilla.getChildren();
        if (!listaNodos.isEmpty()) {
            Node primerNodo = listaNodos.get(0);
            return primerNodo instanceof TextField;
        }
        return false;
    }

    /* Entradas: Recibe un String
       Salidas: Retorna un entero
       Parsea la solucion del usuario a un Entero
     */

    private int formatearSolucion(String solucion) {
        try {
            return Integer.parseInt(solucion);
        } catch (NumberFormatException e) {
            return SOLUCION_INCOMPLETA;
        }
    }

    /* Entradas: Un String
       Salidas: Retorna un entero
       Parsea la coordenada a entero
     */

    private int formatearCoordenada(String coord) {
        try {
            int coordenadaFormateada = Integer.parseInt(coord);
            return coordenadaFormateada;
        } catch (NumberFormatException e) {
            return COORD_INVALIDA;
        }
    }

    /* Entradas: No recibe parametros
       Salidas: No devuelve nada
       Obtiene las coordenadas ingresadas por el usuario
     */

    @FXML
    public void ingresarSolucionCoordenadas() {
        int coordX = formatearCoordenada(ingresoCoordenadaX.getText().toString());
        int coordY = formatearCoordenada(ingresoCoordenadaY.getText().toString());
        String textoNumeroIngresado = ingresoNumero.getText().toString();
        int numeroIngresado = formatearSolucion(textoNumeroIngresado);
        VBox casilla = obtenerCasillaCoordenadas(coordX, coordY);
        if (contieneSolucion(casilla) && numeroIngresado != SOLUCION_INCOMPLETA) {
            TextField campoSolucion = (TextField) casilla.getChildren().get(0);
            campoSolucion.setText(textoNumeroIngresado);
        }
    }

    /* Entradas: Dos enteros
       Salidas: Un VBox
       Obtiene la casilla del tablero, ubicada en dichas coordenadas
     */
    private VBox obtenerCasillaCoordenadas(int coordX, int coordY) {
        int x = 0;
        int y = 0;
        ObservableList<Node> casillasTablero = tableroKakuro.getChildren();
        for (Node nodo : casillasTablero) {
            if (x == coordX && y == coordY) {
                VBox casilla = (VBox) nodo;
                return casilla;
            }
            if (y == 8) {
                y = 0;
                x++;
            } else {
                y++;
            }
        }
        return null;
    }

    /* Entradas: Un evento
       Salidas: No retorna nada
       Genera una nueva escena con un nuevo tablero
     */

        @FXML
        void generarKakuro (MouseEvent event){
            try {
                actualizarNumeroTablero();
                int tmp = numeroTablero;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/juego" + numeroTablero + ".fxml"));
                Parent root = fxmlLoader.load();
                //Cargar nueva ventana de juego
                Stage secondaryStage = new Stage();
                secondaryStage.setResizable(false);
                secondaryStage.setTitle("Juego 9x9");
                secondaryStage.setScene(new Scene(root, 1280, 780));
                secondaryStage.show();
                ControladorJuego controladorJuego = fxmlLoader.getController();
                controladorJuego.timer.start();
                controladorJuego.numeroTablero = tmp;
                // Ocultar ventana de inicio
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    /* Entradas: Un evento
       Salidas: No retorna nada
       Crea la nueva escena de las estadisticas
    */

        @FXML
        void mostrarEstadisticas (MouseEvent event){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/estadisticas.fxml"));
                Parent root = fxmlLoader.load();

                estadisticas = fxmlLoader.getController();
                estadisticas.setCeldasIngresoDigitos(String.valueOf(Soluciones.length));
                estadisticas.setContadorVerificaciones(contadorVerificaciones);
                estadisticas.setCantidadErroresVerificacion(cantidadErroresVerificacion);
                estadisticas.setCantidadSugerenciasUtilizadas(cantidadSugerencias);
                tipoFinalizacionEstadisticas();
                estadisticas.setTextFields();

                Stage stage = new Stage();
                stage.setTitle("Estadisticas");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* Entradas: No recibe parametros
           Salidas: No retorna nada
           Coloca el tipo de finalizacion, dependiendo del juego
         */

        public void tipoFinalizacionEstadisticas () {
            if (tipoFinalizacion.equals("Autosolucion")) {
                estadisticas.setFinalizacion("Autosolucion");
            } else if (verificarSolucion().equals("Juego finalizado exitosamente, felicidades!!!")) {
                estadisticas.setFinalizacion("Exitosa");
            } else {
                estadisticas.setFinalizacion("Abandono");
            }
        }

        /* Entradas: No recibe parametros
           Salidas: No devuelve nada
           Obtiene la solucion del kakuro desde prolog
       */

        public void obtenerSolucionKakuro () {
            String solucionProlog = "solucionador" + numeroTablero + "(Vars)";
            Query q2 = new Query(solucionProlog);
            SolucionKakuro = q2.oneSolution().get("Vars");
        }

        /* Entradas: Un evento
           Salidas: No devuelve nada
           Le muestra la solucion del kakuro al usuario
        */

        @FXML
        void verSolucion (MouseEvent event){
            tipoFinalizacion = "Autosolucion";
            realizarConsulta();
            obtenerSolucionKakuro();
            Term SolucionKakuroAux = SolucionKakuro;

            ObservableList<Node> casillasTablero = tableroKakuro.getChildren();
            for (Node node : casillasTablero) {
                VBox casilla = (VBox) node;
                if (contieneSolucion(casilla)) {
                    TextField campoSolucion = (TextField) casilla.getChildren().get(0);
                    String solucionKakuroActual = SolucionKakuroAux.arg(1).toString();
                    campoSolucion.setText(solucionKakuroActual);
                    SolucionKakuroAux = SolucionKakuroAux.arg(2);
                }
            }
            Arrays.fill(Soluciones, SOLUCION_INCOMPLETA);
        }

        /* Entradas: Un evento
           Salidas: No devuelve nada
           Realiza las sugerencias del juego, cuando el usuario presiona el boton
        */

        @FXML
        void sugerir (MouseEvent event){
            if (cantidadSugerencias >= 6) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Limite de sugerencias");
                alert.setContentText("Solo puede realizar 5 sugerencias");
                alert.showAndWait();
            } else {
                int sugerenciasRestantes = 5 - cantidadSugerencias;
                textoCantidadSugerencias.setText("("+sugerenciasRestantes+")");
                realizarConsulta();
                obtenerSolucionKakuro();
                obtenerSoluciones();
                ingresarSugerencia();
                cantidadSugerencias++;
            }
        }


        /* Entradas: No recibe parametros
           Salidas: Np devuelve nada
           Busca una solucion aleatoria, y se la muestra al usuario,
           cumpliendo las validaciones del caso
        */

        public void ingresarSugerencia () {
            boolean noHaySugerencia = true;
            Random random = new Random();

            while (noHaySugerencia) {
                int indiceAleatorio = random.nextInt(MAXIMO_SOLUCIONES_TABLERO);
                String SolucionUsuario = Integer.toString(Soluciones[indiceAleatorio]);

                realizarConsulta();
                String obtenerIndiceSolucion = "obtenerPorIndice(" + SolucionKakuro + "," + indiceAleatorio + "," + "X)";
                Query q7 = new Query(obtenerIndiceSolucion);
                Term solucionAleatoriaKakuro = q7.oneSolution().get("X");

                VBox casillaAleatoria = getCasilla(indiceAleatorio);
                int solucionActual = obtenerSolucion(casillaAleatoria);
                if (solucionActual != SOLUCION_INVALIDA && !SolucionUsuario.equals(solucionAleatoriaKakuro.toString())) {
                    TextField campoSolucion = (TextField) casillaAleatoria.getChildren().get(0);
                    campoSolucion.setText(solucionAleatoriaKakuro.toString());
                    setSolucion(indiceAleatorio, solucionActual);
                    noHaySugerencia = false;
                }

            }

        }

        /* Entradas: Un entero
           Salidas: Retorna un VBox
           Obtiene una casilla aleatoria del tablero, para realizar la sugerencia
        */

        public VBox getCasilla ( int n){
            ObservableList<Node> nodos = tableroKakuro.getChildren();
            VBox casilla;
            int i = 0;
            for (Node nodo : nodos) {
                casilla = (VBox) nodo;
                int solucionActual = obtenerSolucion(casilla);
                if (solucionActual != SOLUCION_INVALIDA) {
                    if (i == n) {
                        return casilla;
                    }
                    i++;
                }
            }
            return null;
        }

        /* Entradas: Un evento
           Salidas: No devuelve nada
           Le muesta una ventana al usuario con el mensaje de la verificacion,
           cuando presiona el boton
        */

        @FXML
        void verificar (MouseEvent event){
            contadorVerificaciones++;
            String mensajeVerificacion = verificarSolucion();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Verificacion");
            alert.setContentText(mensajeVerificacion);
            alert.showAndWait();
        }

        /* Entradas: No recibe nada
           Salidas: Retorna un String
           Verifica la solucion del usuario, e imprime el mensaje dependiendo de esta
        */

        public String verificarSolucion () {
            if (realizarConsulta()) {
                obtenerSolucionKakuro();
                obtenerSoluciones();
                String solucionesFormateadas = "[" + String.join(",", toString(Soluciones)) + "]";
                String t3 = "esSolucion(" + SolucionKakuro + "," + solucionesFormateadas + ")"; //se debe obtener la solucion de la matriz
                Query q3 = new Query(t3);

                if (q3.hasSolution()) {
                    return "Juego finalizado exitosamente, felicidades!!!";
                } else {
                    return "Hay " + obtenerErrores() + " errores " + "y hay " + obtenerCeldasVacias() + " celdas vacias de " + obtenerCantidadCasillas();
                }
            }
            return "Error en el sistema";
        }

        /* Entradas: No recibe parametros
           Salidas: Retorna un String
           Obtiene la cantidad de errores que presenta el usuario
        */

        public String obtenerErrores () {
            String solucionesFormateadas = "[" + String.join(",", toString(Soluciones)) + "]";
            String queryIncorrectas = "obtenerErrores(" + SolucionKakuro + "," + solucionesFormateadas + ",R)";
            Query q4 = new Query(queryIncorrectas);

            Map<String, Term> map = q4.next();
            String errores = map.get("R").toString();

            int numeroErrores = Integer.parseInt(errores);
            cantidadErroresVerificacion += numeroErrores;

            return errores;
        }

        /* Entradas: No recibe parametros
           Salidas: Retorna un String
           Obtiene la cantidad de celdas vacias del tablero
        */

        public String obtenerCeldasVacias () {
            String solucionesFormateadas = "[" + String.join(",", toString(Soluciones)) + "]";
            String queryCeldasVacias = "celdasVacias(" + solucionesFormateadas + ",N)";
            Query q5 = new Query(queryCeldasVacias);

            Map<String, Term> map = q5.next();
            return map.get("N").toString();
        }

        /* Entradas: No recibe parametros
           Salidas: Retorna un String
           Obtiene la cantidad de casillas del tablero
        */

        public String obtenerCantidadCasillas () {
            String QueryLenLista = "length(" + SolucionKakuro + "," + "N)";
            Query q6 = new Query(QueryLenLista);

            Map<String, Term> map = q6.next();
            return map.get("N").toString();
        }

        /* Entradas: No recibe parametros
           Salidas: Un booleano
           Realiza la consulta a Prolog
        */

        public boolean realizarConsulta () {
            String t1 = "consult('src/Kakuro/kakuroSolver.pl')";
            Query q1 = new Query(t1);
            return q1.hasSolution();
        }

        /* Entradas: Un long
           Salidas: Un String
           Formatea el tiempo del cronometro con horas, minutos y segundos
         */

        public String formatearSegundos ( long segundos){
            Date d = new Date(segundos * 1000L);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); // HH for 0-23
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            String segundosFormateados = df.format(d);
            return segundosFormateados;
        }

    }
