package to;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Scanner;

public class Worker extends User{

    int id;
    public Worker(String login, String haslo){
        super(login,haslo);
    }

    public void ustawDane(int id, String imie, String nazwisko){
        super.ustawDaneProwadzacego(imie, nazwisko);
        this.id=id;
    }

    public int Zaloguj() {
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(this);
            if(result.hasNext()) {
                Worker prowadzacy=(Worker) result.next();
                this.ustawDane(prowadzacy.id,prowadzacy.imie,prowadzacy.nazwisko);
                // System.out.println(result.next());
                zalogowany=prowadzacy.id;
            }
        }
        finally {
            db.close();
        }
        if(zalogowany!=0)
            System.out.println("Zalogowano " + login);
        else
            System.out.println("Nieprawidłowa nazwa użytkownika i/lub haslo ");
        return zalogowany;
    }

    public void ZmienHaslo() {
        System.out.println("Podaj ID");
        Scanner in = new Scanner(System.in);
        int id_zmiana_hasla = Integer.parseInt(in.nextLine());
        System.out.println("Podaj nowe hasło");
        String nowe_haslo = in.nextLine();
        ObjectContainer db= Db4o.openFile("wirtualny_dziekanat");
        try {
            Worker zmiana =new Worker( login, null);
            zmiana.ustawDane(id_zmiana_hasla, null, null);
            ObjectSet result=db.queryByExample(zmiana);
            if(result.hasNext()) {
                Worker found = (Worker) result.next();
                found.haslo = nowe_haslo;
                db.store(found);
                System.out.println("Hasło zmienione dla "+ login);
            }
            else
                System.out.println("Nieprawidłowy login lub ID");
        }
        finally {
            db.close();
        }
    }

    public String toString(int opcja) {
        if(opcja==1)
            return "pracownik, login: " + login + " haslo: " + haslo;
        else if(opcja==2)
            return "\npracownik,y::\npesel: " + login + ",\nhaslo: " + haslo+ ", \nimie: " + imie +",\nnazwisko: "+ nazwisko;
        else
            return "pracownik,:: id: " + login + ", imie: " + imie +",nazwisko: "+ nazwisko;
    }

}
