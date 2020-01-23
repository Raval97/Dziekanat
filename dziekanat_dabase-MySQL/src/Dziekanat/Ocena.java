package Dziekanat;

public class Ocena {
    private int idOceny;
    private String nazwa;
    private int stopien;
    private float waga;

    public Ocena() {
    }

    public Ocena(int idOceny, String nazwa, int stopien, float waga) {
        this.idOceny = idOceny;
        this.nazwa = nazwa;
        this.stopien = stopien;
        this.waga = waga;
    }

    @Override
    public String toString() {
        return "idOceny=" + idOceny +
                ", nazwa='" + nazwa + '\'' +
                ", stopien=" + stopien +
                ", waga=" + waga;
    }

    public int getIdOceny() {
        return idOceny;
    }

    public void setIdOceny(int idOceny) {
        this.idOceny = idOceny;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getStopien() {
        return stopien;
    }

    public void setStopien(int stopien) {
        this.stopien = stopien;
    }

    public float getWaga() {
        return waga;
    }

    public void setWaga(float waga) {
        this.waga = waga;
    }
}
