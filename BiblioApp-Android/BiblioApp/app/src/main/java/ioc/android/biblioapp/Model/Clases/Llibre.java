/**
  @Autor Saúl López Díez
  Clase Libros con datos del libro
 */

package ioc.android.biblioapp.Model.Clases;

public class Llibre {

    private int idLlibre;
    private String titulLlibre;
    private int idCategoria;
    private String dataPublicacio;
    private String copiesDisponibles;
    private String foto;
    private Autor autor;

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getTitulLlibre() {
        return titulLlibre;
    }

    public void setTitulLlibre(String titulLlibre) {
        this.titulLlibre = titulLlibre;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }


    public String getDataPublicacio() {
        return dataPublicacio;
    }

    public void setDataPublicacio(String dataPublicació) {
        this.dataPublicacio = dataPublicació;
    }

    public String getCopiesDisponibles() {
        return copiesDisponibles;
    }

    public void setCopiesDisponibles(String copiesDisponibles) {
        this.copiesDisponibles = copiesDisponibles;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
