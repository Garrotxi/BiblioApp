/**
 * @Autor Saúl López Díez
 * Clase Libros con datos del libro
 */

package ioc.android.biblioapp.Model.Clases;

public class Llibre {

    private String isbn;
    private String titulLlibre;
    private String idCategoria;
    private String dataPublicacio;
    private String copiesDisponibles;
    private String foto;
    private Autor autor;
    private String descripcio;
    private String idLlibre;

    public String getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(String idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getTitulLlibre() {
        return titulLlibre;
    }

    public void setTitulLlibre(String titulLlibre) {
        this.titulLlibre = titulLlibre;
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
