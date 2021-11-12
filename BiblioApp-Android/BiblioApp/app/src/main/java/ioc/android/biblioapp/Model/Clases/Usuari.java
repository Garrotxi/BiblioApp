/*
  autor Saúl López
  Clase Usuario con datos de usuario
 */

package ioc.android.biblioapp.Model.Clases;

public class Usuari {

    private int id;
    private int idRol;
    private String nomUsuari;
    private String nom;
    private String cognoms;
    private String email;
    private int telefon;
    private String dataRegistre;
    private int status;
    private String contraseña;
    private String foto;
    private String accessToken;

    public Usuari() {

    }
    public Usuari(Usuari usuari) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String loginKey) {
        this.accessToken = loginKey;
    }
}
