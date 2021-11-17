package com.biblioapp.core.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuari")
public class Usuari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuari;

    @NotNull
    @Column(name="nom_usuari")
    private String nomUsuari;

    @NotNull
    @Column(name="contrasenya")
    private String contrasenya;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="nom")
    private String nom;

    @NotNull
    @ManyToMany
    @JoinTable(name = "usuari_rol", joinColumns = @JoinColumn(name = "id_usuari"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> rols = new ArrayList();

    public Usuari(){

    }

    public Usuari(@NotNull String nomUsuari, @NotNull String contrasenya, @NotNull String email,
                  @NotNull String nom) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.email = email;
        this.nom = nom;

    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public List<Rol> getRols() {
        return rols;
    }

    public void setRols(List<Rol> rols) {
        this.rols = rols;
    }

}
