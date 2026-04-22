package com.gabriel.cafeteria.cafeteriainterface.views;

import com.gabriel.cafeteria.cafeteriainterface.components.Card;
import com.gabriel.cafeteria.cafeteriainterface.threads.Cafeteria;
import com.gabriel.cafeteria.cafeteriainterface.threads.Camarero;
import com.gabriel.cafeteria.cafeteriainterface.threads.Cliente;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CafeteriaView {
    private static final Cafeteria CAFETERIA = new Cafeteria();

    @FXML
    private HBox cafeteriaContainer;

    @FXML
    private VBox camarerosContainer;

    @FXML
    private GridPane clientesContainer;


    @FXML
    public void initialize() {
        renderizeCameros();
        renderizeClientes();
        CAFETERIA.start();
    }

    private void renderizeClientes() {
        var clientes = CAFETERIA.CLIENTES;
        int cols = 4;
        for (int i = 0; i < clientes.size(); i++) {
            int col = i % cols;
            int row = i / cols;
            clientesContainer.add(clientes.get(i).card, col , row );
        }
    }

    private void renderizeCameros() {
        for (Camarero cam : CAFETERIA.CAMAREROS) {
            camarerosContainer.getChildren().add(cam.card);
        }
    }



}
