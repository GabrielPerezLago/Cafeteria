package com.gabriel.cafeteria.cafeteriainterface.threads;

import com.gabriel.cafeteria.cafeteriainterface.components.Card;
import com.gabriel.cafeteria.cafeteriainterface.utils.ServiceUtils;
import javafx.application.Platform;

public class Cliente extends Thread{
    private String nombre;
    private String estado = "esperando"; /* esperando , servido, no servido, atendido*/
    private int tiempoEspera = new ServiceUtils().randomTime();
    public Card card;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.card = new Card(nombre, estado);
    }

    @Override
    public void run() {
        System.out.println(this.nombre + " ha llegado y ha pedido un cafe , esta esperando ...");
        try {
            this.sleep(tiempoEspera);
            if(!estado.equals("servido")){
                estado = "no servido";
                updated(estado);
                System.out.println("El cliente " + nombre + " no ha sido servido a tiempo y se ha ido");
            } else {
                System.out.println("El cliente " + nombre + " ha sido serivdo correctamente y se ha ido ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void recivirCafe() {
        if (!estado.equals("no servido")) {
            estado = "servido";
            updated(estado);
            System.out.println(this.nombre + " recivio el café");
        }
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
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void updated(String estado) {
        Platform.runLater(() -> {
            card.actualizar(estado);
        });
    }
}
