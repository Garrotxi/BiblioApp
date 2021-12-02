package com.biblioapp.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author: Lluis Antoni Roigé Higueras
 */
@Entity
@Table(name="prestecs")
public class Prestec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPrestec;

    @NotNull
    @Column(name="idUsuari")
    private int idUsuari;

    @NotNull
    @Column(name="idLlibre")
    private int idLlibre;

    @NotNull
    @Column(name="data_prestec")
    private String dataPrestec;


    @Column(name="data_devolucio_prevista")
    private String dataDevolucioPrevista;
    @Column(name="data_devolucio")
    private String dataDevolucio;

    public Prestec() {

    }

    public Prestec(int idUsuari, int idLlibre, String dataPrestec,
                   String dataDevolucioPrevista,String dataDevolucio) {
        this.idUsuari = idUsuari;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataDevolucioPrevista = dataDevolucioPrevista;
        this.dataDevolucio = dataDevolucio;
    }

    public int getIdPrestec() {
        return idPrestec;
    }

    public void setIdPrestec(int idPrestec) {
        this.idPrestec = idPrestec;
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
