package Dziekanat;

public class Prowadzacy {
    private int idProwadzaceo;
    private String nazwisko;
    private String imie;

    public Prowadzacy() {
    }

    public Prowadzacy(int idProwadzaceo, String nazwisko, String imie) {
        this.idProwadzaceo = idProwadzaceo;
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "idProwadzaceo=" + idProwadzaceo +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' ;
    }

    public int getIdProwadzaceo() {
        return idProwadzaceo;
    }

    public void setIdProwadzaceo(int idProwadzaceo) {
        this.idProwadzaceo = idProwadzaceo;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

}
