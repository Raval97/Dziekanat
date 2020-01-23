package Dziekanat;

public class Uzytkownik {
    private int login;
    private String haslo;
    private String typ;

    public Uzytkownik() {
    }

    public Uzytkownik(int login, String haslo, String typ) {
        this.login = login;
        this.haslo = haslo;
        this.typ = typ;
    }

    public int getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "login=" + login +
                ", haslo='" + haslo + '\'' +
                ", typ='" + typ + '\'';
    }


    public void setLogin(int login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

}
