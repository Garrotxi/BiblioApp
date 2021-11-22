package com.biblioapp.core.dto;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Classe per a mostrar missatges per pantalla en client
 */

public class Missatge {
    private String missatge;

    public Missatge(String mensaje) {
        this.missatge = mensaje;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }
}
