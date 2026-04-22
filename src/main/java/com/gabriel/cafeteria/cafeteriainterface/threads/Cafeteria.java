package com.gabriel.cafeteria.cafeteriainterface.threads;

import java.util.Arrays;
import java.util.List;

public class Cafeteria extends Thread {
    public List<Cliente> CLIENTES = Arrays.asList(
            new Cliente("Gabriel"),
            new Cliente("Alba"),
            new Cliente("Pablo"),
            new Cliente("Elena")
    );

    public List<Camarero> CAMAREROS = Arrays.asList(
            new Camarero("Lara", CLIENTES),
            new Camarero("Sergio", CLIENTES)
    );
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

        System.out.println("___ Fin del día en la cafetería ___");
    }
}
