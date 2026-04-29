package com.gabriel.cafeteria.cafeteriainterface.threads;

import com.gabriel.cafeteria.cafeteriainterface.utils.ScreenUtils;
import com.gabriel.cafeteria.cafeteriainterface.views.CafeteriaView;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cafeteria extends Thread {
    public List<Cliente> CLIENTES = new CopyOnWriteArrayList<>();
    public List<Camarero> CAMAREROS = new CopyOnWriteArrayList<>();

    @Override
    public void run() {

        for (Cliente c : CLIENTES) c.start();

       for (Camarero c : CAMAREROS) c.start();

        try {
            for (Cliente c : CLIENTES) c.join();
            for (Camarero c : CAMAREROS) c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        removeCamarero();
        removeCliente();

        System.out.println("___ Fin del día en la cafetería ___");
    }

    public void AddCamarero(Camarero c) {
        CAMAREROS.add(c);
        c.start();
        removeCamarero();
    }

    public void AddCliente(Cliente c) {
        CLIENTES.add(c);
        c.start();
        updatedListas();
        removeCliente();
    }

    private void removeCamarero() {
        for (Camarero c : CAMAREROS) {
            c.card.setDeleteEvent(event -> {
                CAMAREROS.remove(c);
                Platform.runLater(() -> {
                    CafeteriaView.instace.renderizeCameros();
                });
                try {
                    c.dead();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

    private void removeCliente() {
        for (Cliente cc : CLIENTES) {
            cc.card.setDeleteEvent(event -> {
                try {
                    if (cc.isInterrupted()) System.out.println("Cliente interrupted");
                    CLIENTES.remove(cc);
                    updatedListas();
                    cc.dead();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    CafeteriaView.instace.renderizeClientes();
                });
            });
        }

    }

    private void updatedListas() {
        for (Camarero c : CAMAREROS) {
            c.setClientes(CLIENTES);
        }
    }



}
