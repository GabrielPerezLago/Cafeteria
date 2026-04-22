package com.gabriel.cafeteria.cafeteriainterface.threads;

import com.gabriel.cafeteria.cafeteriainterface.components.Card;
import com.gabriel.cafeteria.cafeteriainterface.utils.ServiceUtils;
import javafx.application.Platform;

import java.util.List;

public class Camarero extends Thread {
    private String nombre;
    private String estado = "esperando"; /* esperando, atendiendo, preparado cafe, sirviendo*/
    private List<Cliente> clientes;
    private ServiceUtils utils = new ServiceUtils();

    public Card card;

    public Camarero(String nombre, List<Cliente> clientes) {
        this.nombre = nombre;
        this.clientes = clientes;
        this.card = new Card(nombre, estado);
    }

    @Override
    public void run() {
        try {
            for (Cliente cli : clientes) {
                this.estado = "atendiendo";
                update();
                if (!cli.getEstado().equals("esperando")) {
                    continue;
                }
                prepararCafe(cli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepararCafe(Cliente cliente) throws InterruptedException {
        cliente.setEstado("atendido");
        cliente.updated(cliente.getEstado());
        this.estado = "preparando cafe";
        update();
        System.out.println(this.nombre + " esta preparando café para " + cliente.getNombre() + "...");
        this.sleep(utils.randomTime(20));
        serivir(cliente);
    }

    private void serivir(Cliente cliente) {
        cliente.recivirCafe();
        this.estado = "sirviendo";
        update();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void update() {
        Platform.runLater( () -> {
            card.actualizar(estado);
        });
    }
}
