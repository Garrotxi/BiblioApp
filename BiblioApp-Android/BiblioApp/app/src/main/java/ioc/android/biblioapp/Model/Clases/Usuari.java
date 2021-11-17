/**
  @autor Saúl López Díez
  Clase Usuario con datos de usuario
 */

package ioc.android.biblioapp.Model.Clases;

public class Usuari {

    private int id;
    private int idRol;
    private String username;
    private String nom;
    private String cognoms;
    private String email;
    private String telefon;
    private String dataRegistre;
    private int status;
    private String password;
    private String foto;
    private String accessToken;
    private String [] roles;

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

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
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

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
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

    public String[] getRoles() { return roles; }

   public void setRoles(String[] roles) { this.roles = roles; }

    @Override
    public String toString() {
        return "Usuari: "+
                "\n id: "+id+
                "\n idRol: "+idRol+
                "\n nom: "+nom+
                "\n username: "+username+
                "\n cognoms: "+cognoms+
                "\n email: "+email+
                "\n telefon: "+telefon+
                "\n dataRegistre:"+dataRegistre+
                "\n status: "+status+
                "\n password:"+password+
                "\n foto: "+foto+
                "\n accessToken: "+accessToken+
                "\n roles:"+roles;
    }
}
