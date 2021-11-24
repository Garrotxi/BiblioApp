/**
  @Autor Saúl López Díez
  Clase Libros con datos del libro
 */

package ioc.android.biblioapp.Model.Clases;

public class Llibre {

    private int idLlibre;
    private String titulLlibre;
    private int idCategoria;
    private String dataPublicació;
    private int copiesDisponibles;
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

    public String getDataPublicació() {
        return dataPublicació;
    }

    public void setDataPublicació(String dataPublicació) {
        this.dataPublicació = dataPublicació;
    }

    public int getCopiesDisponibles() {
        return copiesDisponibles;
    }

    public void setCopiesDisponibles(int copiesDisponibles) {
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
