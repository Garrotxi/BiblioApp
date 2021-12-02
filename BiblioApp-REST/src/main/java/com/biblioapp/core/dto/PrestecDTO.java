package com.biblioapp.core.dto;


import javax.validation.constraints.NotBlank;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Clase que es limita a ser un objecte de transferencia
 * entre client i el servidor per a l'entitat Prestec.
 * DTO acronim de Data Transfer Object.
 */
public class PrestecDTO {
    @NotBlank
    private int idUsuari;
    @NotBlank
    private int idLlibre;
    @NotBlank
    private String dataPrestec;

    private String dataDevolucioPrevista;
    private String dataDevolucio;

    public PrestecDTO(){

    }

    public PrestecDTO(int idUsuari, int idLlibre, String dataPrestec,
                      String dataDevolucioPrevista, String dataDevolucio) {
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataDevolucioPrevista = dataDevolucioPrevista;
        this.dataDevolucio = dataDevolucio;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(String dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public String getDataDevolucioPrevista() {
        return dataDevolucioPrevista;
    }

    public void setDataDevolucioPrevista(String dataDevolucioPrevista) {
        this.dataDevolucioPrevista = dataDevolucioPrevista;
    }

    public String getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(String dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }
}
