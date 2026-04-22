package com.gabriel.cafeteria.cafeteriainterface.utils;

public class ServiceUtils {

    /**
     * @param {}
     * Esta funcion retorna un numero aleatorio de segundos entre 10 y 30 secs
     * @return [ Integer ]
     */
    public Integer randomTime() {
        return (int) (Math.random() * 30) * 1000;
    }

    /**
     * @param {int : Numero}
     * Esta funcion retorna un numero aleatorio entre 1 y el nuemro insertado en segundos
     * @return [ Integer ]
     */
    public Integer randomTime(int num) {
        return (int) (Math.random() * num) * 1000 ;
    }
}
