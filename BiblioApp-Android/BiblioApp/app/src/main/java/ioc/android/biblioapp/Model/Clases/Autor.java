/**
 @Autor Saúl López Díez
  Clase Autor con datos de un Autor
 */

package ioc.android.biblioapp.Model.Clases;

public class Autor {
    private String idAutor;
    private String nom;
    private String cognoms;

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
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
}
