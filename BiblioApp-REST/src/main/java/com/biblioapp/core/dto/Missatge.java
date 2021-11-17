package com.biblioapp.core.dto;

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
