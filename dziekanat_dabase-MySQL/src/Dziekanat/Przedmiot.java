package Dziekanat;

public class Przedmiot {
   private int idPrzedmiotu;
   private String nazwa;
   private int semestr;
   private  int pktECTS;

    public Przedmiot() {
    }

    public Przedmiot(int idPrzedmiotu, String nazwa, int idProwadzadzacego, int pktECTS) {
        this.idPrzedmiotu = idPrzedmiotu;
        this.nazwa = nazwa;
        this.semestr = idProwadzadzacego;
        this.pktECTS = pktECTS;
    }

    @Override
    public String toString() {
        return "idPrzedmiotu=" + idPrzedmiotu +
                ", nazwa='" + nazwa + '\'' +
                ", idProwadzadzacego=" + semestr +
                ", pktECTS=" + pktECTS;
    }

    public int getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(int idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public int getPktECTS() {
        return pktECTS;
    }

    public void setPktECTS(int pktECTS) {
        this.pktECTS = pktECTS;
    }
}
