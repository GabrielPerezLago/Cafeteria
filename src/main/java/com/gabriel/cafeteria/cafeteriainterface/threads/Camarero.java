package com.gabriel.cafeteria.cafeteriainterface.threads;

import com.gabriel.cafeteria.cafeteriainterface.components.Card;
import com.gabriel.cafeteria.cafeteriainterface.utils.ServiceUtils;
import javafx.application.Platform;

import java.util.List;

public class Camarero extends Thread {
    private Integer id;
    private String nombre;
    private String estado = "esperando"; /* esperando, atendiendo, preparado cafe, sirviendo*/
    private List<Cliente> clientes;
    private ServiceUtils utils = new ServiceUtils();
    public Card card;
    private boolean isRunning = true;

    public Camarero(Integer id, String nombre, List<Cliente> clientes) {
        this.id = id;
        this.nombre = nombre;
        this.clientes = clientes;
        this.card = new Card(id, nombre, estado);
    }

    @Override
    public void run() {
        while(isRunning) {
            try {
                if (clientes == null || clientes.isEmpty()) {
                    sleep(2000);
                } else {
                    for (Cliente cli : clientes) {
                        this.estado = "atendiendo";
                        update();
                        if (cli.getEstado() ==  "servido" || cli.getEstado() ==  "no servido") {
                            continue;
                        }
                        this.updateCliente(cli.getNombre());
                        prepararCafe(cli);

                        //Vuelve a cambiar el estado cuando acaba
                        this.estado = "trabajo finalizado";
                        update();
                    }
                }
            } catch (Exception e) {
                System.out.println("Cliente " + nombre + " interrumpido");
                isRunning = false;
                return;
            }
        }
    }

    private synchronized void prepararCafe(Cliente cliente) throws InterruptedException {
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

    public void updateCliente(String cliente) {
        Platform.runLater( () -> {
            card.actualizarCliente(cliente);
        });
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void dead() throws InterruptedException {
        System.out.println(nombre + " a sido parado");
        isRunning = false;
        interrupt();
    }
}
