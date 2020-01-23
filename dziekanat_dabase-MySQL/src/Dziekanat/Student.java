package Dziekanat;

public class Student {
    private int idLegitymacji;
    private String  nazwisko;
    private String imie;
    private String wydzial;
    private String kierunek;
    private int stopienStudiow;
    private int nrSemestru;

    public Student() {
    }

    public Student(int idLegitymacji, String nazwisko, String imie, String wydzial,
                   String kierunek, int stopienStudiow, int nrSemestru) {
        this.idLegitymacji = idLegitymacji;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.wydzial = wydzial;
        this.kierunek = kierunek;
        this.stopienStudiow = stopienStudiow;
        this.nrSemestru = nrSemestru;
    }

    @Override
    public String toString() {
        return "idLegitymacji=" + idLegitymacji +
                ", nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                ", wydzial='" + wydzial + '\'' +
                ", kierunek='" + kierunek + '\'' +
                ", stopienStudiow=" + stopienStudiow +
                ", nrSemestru=" + nrSemestru;
    }

    public int getIdLegitymacji() {
        return idLegitymacji;
    }

    public void setIdLegitymacji(int idLegitymacji) {
        this.idLegitymacji = idLegitymacji;
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

    public String getWydzial() {
        return wydzial;
    }

    public void setWydzial(String wydzial) {
        this.wydzial = wydzial;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public int getStopienStudiow() {
        return stopienStudiow;
    }

    public void setStopienStudiow(int stopienStudiow) {
        this.stopienStudiow = stopienStudiow;
    }

    public int getNrSemestru() {
        return nrSemestru;
    }

    public void setNrSemestru(int nrSemestru) {
        this.nrSemestru = nrSemestru;
    }
}
