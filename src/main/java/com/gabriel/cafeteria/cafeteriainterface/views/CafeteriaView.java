package com.gabriel.cafeteria.cafeteriainterface.views;

import com.gabriel.cafeteria.cafeteriainterface.components.CafButton;
import com.gabriel.cafeteria.cafeteriainterface.components.Card;
import com.gabriel.cafeteria.cafeteriainterface.threads.Cafeteria;
import com.gabriel.cafeteria.cafeteriainterface.threads.Camarero;
import com.gabriel.cafeteria.cafeteriainterface.threads.Cliente;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class CafeteriaView {

    public static CafeteriaView instace;

    public static CafeteriaView getInstace() {
        if (instace == null) {
            instace = new CafeteriaView();
        }
        return instace;
    }

    private static final Cafeteria CAFETERIA = new Cafeteria();
    private AtomicInteger idCamarero = new AtomicInteger(1);
    private AtomicInteger idCliente = new AtomicInteger(1);
    @FXML
    private HBox cafeteriaContainer;

    @FXML
    private VBox camarerosContainer;

    @FXML
    private GridPane clientesContainer;

    @FXML
    private HBox btnContainer;


    @FXML
    public void initialize() {
        instace = this;
        renderizeCameros();
        renderizeClientes();
        renderizeButtons();
        CAFETERIA.start();
    }

    public  void renderizeClientes() {
        clientesContainer.getChildren().clear();
        if (CAFETERIA.CLIENTES == null ||  CAFETERIA.CLIENTES.isEmpty()) return;

        var clientes = CAFETERIA.CLIENTES;
        int cols = 4;
        for (int i = 0; i < clientes.size(); i++) {
            int col = i % cols;
            int row = i / cols;
            clientesContainer.add(clientes.get(i).card, col , row );
        }
    }

    public void renderizeCameros() {
        camarerosContainer.getChildren().clear();
        if (CAFETERIA.CAMAREROS == null || CAFETERIA.CAMAREROS.isEmpty()) return;
        for (Camarero cam : CAFETERIA.CAMAREROS) {
            camarerosContainer.getChildren().add(cam.card);
        }
    }

    private void renderizeButtons() {
        this.btnContainer.getChildren().addAll(
                new CafButton("Add Camarero", event -> {
                    var id = idCamarero.getAndIncrement();
                    addCamarero(id, "Camarero " + id);
                    Platform.runLater(() -> {
                        renderizeCameros();
                    });
                } ),
                new CafButton("Add Cliente", event -> {
                    var id = idCliente.getAndIncrement();
                    addCliente(id, "Cliente " + id);
                    Platform.runLater(() -> {
                        renderizeClientes();
                    });
                } )
        );
    }

    private void addCamarero (Integer id, String nombre) {
        CAFETERIA.AddCamarero(new Camarero(id, nombre, CAFETERIA.CLIENTES));
    }

    private void addCliente(Integer id, String nombre) {
        CAFETERIA.AddCliente(new Cliente(id , nombre));
    }






}