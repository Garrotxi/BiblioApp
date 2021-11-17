/**
 @Autor Saúl López Díez
  Clase Autor con datos de un Autor
 */

package ioc.android.biblioapp.Model.Clases;

public class Autor {
    private int id;
    private String nom;
    private String cognoms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
