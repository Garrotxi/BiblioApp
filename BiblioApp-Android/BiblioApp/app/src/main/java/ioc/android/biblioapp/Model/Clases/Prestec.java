/**
 @Autor Saúl López Díez
  Clase Autor con datos de un Autor
 */

package ioc.android.biblioapp.Model.Clases;

public class Prestec {
    private String idPrestec;
    private String dataPrestec;
    private String dataDevolucio;
    private String dataDevolucioPrevista;
    private String idLlibre;
    private String idUsuari;

    public String getIdPrestec() {
        return idPrestec;
    }

    public void setIdPrestec(String idPrestec) {
        this.idPrestec = idPrestec;
    }

    public String getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(String dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public String getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(String dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }

    public String getDataDevolucioPrevista() {
        return dataDevolucioPrevista;
    }

    public void setDataDevolucioPrevista(String dataDevolucioPrevista) {
        this.dataDevolucioPrevista = dataDevolucioPrevista;
    }

    public String getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(String idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }
}
