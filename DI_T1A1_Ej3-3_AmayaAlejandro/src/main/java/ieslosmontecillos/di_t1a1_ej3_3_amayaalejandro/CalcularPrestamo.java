package ieslosmontecillos.di_t1a1_ej3_3_amayaalejandro;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CalcularPrestamo extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Crear el contenedor
        GridPane root = new GridPane();

        //Componentes
        TextField tf_interes = new TextField();
        TextField tf_anos = new TextField();
        TextField tf_cantidad = new TextField();
        TextField tf_pagoMensual = new TextField();
        TextField tf_total = new TextField();
        Button bt = new Button("Calcular");
        //Etiqueta para mostrar error
        Label lb_err = new Label();

        bt.setOnAction(e -> {
            //Comprueba que no falta informacion en alguno de los campos obligatorios
            if (tf_interes.getText().isEmpty() || tf_anos.getText().isEmpty() || tf_cantidad.getText().isEmpty()){
                lb_err.setText("ERROR. Faltan por rellenar parametros.");
            }else{
                //Limpia la etiqueta error
                lb_err.setText("");

                calcular(Double.parseDouble(tf_interes.getText()), Integer.parseInt(tf_anos.getText()),
                        Double.parseDouble(tf_cantidad.getText()), tf_pagoMensual, tf_total);
            }
        });

        //Añadir elementos
        root.add(new Label("Interes: "), 0, 0);
        root.add(tf_interes, 1, 0);
        root.add(new Label("Numero de años: "), 0, 1);
        root.add(tf_anos, 1, 1);
        root.add(new Label("Cantidad: "), 0, 2);
        root.add(tf_cantidad, 1, 2);
        root.add(new Label("Pago mensual: "), 0, 3);
        root.add(tf_pagoMensual, 1, 3);
        root.add(new Label("Total: "), 0, 4);
        root.add(tf_total, 1, 4);
        root.add(bt, 1, 5);
        root.add(lb_err, 0, 6);

        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 520, 340);
        stage.setTitle("Calculadora Hipoteca");
        stage.setScene(scene);
        stage.show();
    }

    public void calcular(double interes, int anos, double cantidad, TextField tf_pagoMensual, TextField tf_total) {
        double redito;
        double pago_mensual;
        double cant_redito;
        double pago_total;

        //Calcular redito
        redito = interes / (100 * 12);

        //Calcular pago mensual
        cant_redito = cantidad * redito;
        pago_mensual = cant_redito / (1 -  Math.pow((1 + redito), -12 * anos));

        //Lo añadimos al textfield para mostrar el resultado con dos decimales
        tf_pagoMensual.setText(Math.round(pago_mensual * 100.0) / 100.0 + "€");

        //Calculamos el pago total y añadimos
        pago_total = pago_mensual * (anos * 12);
        tf_total.setText(Math.round(pago_total * 100.0) / 100.0 + "€");
    }

    public static void main(String[] args) {
        launch();
    }
}