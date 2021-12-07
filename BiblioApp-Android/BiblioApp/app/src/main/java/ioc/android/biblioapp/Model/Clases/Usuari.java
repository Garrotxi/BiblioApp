/**
  @autor Saúl López Díez
  Clase Usuario con datos de usuario
 */

package ioc.android.biblioapp.Model.Clases;

import java.util.Collection;

public class Usuari {

    private String idUsuari;
    private int idRol;
    private String nomUsuari;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String dataRegistre;
    private int status;
    private String contrasenya;
    private String foto;
    private String token;
    private Collection authorities;
    private Collection rols;

    public Usuari() {

    }
    public Usuari(Usuari usuari) {

    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String id) {
        this.idUsuari = id;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDataRegistre() {
        return dataRegistre;
    }

    public void setDataRegistre(String dataRegistre) {
        this.dataRegistre = dataRegistre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection authorities) {
        this.authorities = authorities;
    }

    public Collection getRols() {
        return rols;
    }

    public void setRols(Collection rols) {
        this.rols = rols;
    }

    @Override
    public String toString() {
        return "Usuari: "+
                "\n id: "+idUsuari+
                "\n idRol: "+idRol+
                "\n nom: "+nom+
                "\n nom usuari: "+nomUsuari+
                "\n cognoms: "+cognoms+
                "\n email: "+email+
                "\n telefon: "+telefon+
                "\n dataRegistre:"+dataRegistre+
                "\n status: "+status+
                "\n contrasenya:"+contrasenya+
                "\n foto: "+foto+
                "\n Token: "+token+
                "\n roles:"+authorities;
    }
}
