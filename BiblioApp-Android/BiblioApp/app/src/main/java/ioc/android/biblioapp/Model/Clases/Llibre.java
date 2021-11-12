/*
  Autor Saúl López
  Clase Libros con datos del libro
 */

package ioc.android.biblioapp.Model.Clases;

public class Llibre {

    private int id;
    private String titol;
    private int idCategoria;
    private String dataPublicació;
    private int copiesDisponibles;
    private String foto;
    private Autor autor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
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
