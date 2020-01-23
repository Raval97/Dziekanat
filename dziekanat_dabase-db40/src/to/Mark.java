package to;

public class Mark {
    public int id;
    public String nazwa;
    public double stopien;
    public int waga;
    public int idStudenta;
    public int idPrzedmiotu;

    public Mark(int id, String nazwa, double stopien, int waga, int idStudenta, int idPrzedmiotu) {
        this.id = id;
        this.nazwa = nazwa;
        this.stopien = stopien;
        this.waga = waga;
        this.idStudenta = idStudenta;
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public String toString() {
        return "id: " + id + ", nazwa: " + nazwa + ", stopien: " + stopien + ", waga: " + waga +", id studenta: "
                + idStudenta + ", id przedmiotu: " + idPrzedmiotu;
    }
}
