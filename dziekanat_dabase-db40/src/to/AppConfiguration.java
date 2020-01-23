package to;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Scanner;

public class AppConfiguration {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Zastosowany silnik obiektowych baz dancyh: db4o!!!");
        while (true){
            System.out.println("\n####################################################\n" +
                    "Wybierz dzialanie\n1.Dodaj\n2.Wypisz wszystko\n3.Usuń wszystko\n4.Zamknij\nOpcja: ");
            int option = Integer.parseInt(in.nextLine());
            switch (option) {
                case 1: AppConfiguration.dodaj(); break;
                case 2: AppConfiguration.wypiszWszystko(); break;
                case 3: AppConfiguration.usunWszystko(); break;
                case 4: System.exit(0);
                default:
                    System.out.println("Zła opcja"); break;
            }
        }
    }

    public static void dodaj(){
        System.out.println("\nDodawane...\n");
        ObjectContainer db= Db4o.openFile("wirtualny_dziekanat");
        try {
            Worker pracownik1 = new Worker("admin", "admin1");
            pracownik1.ustawDane(99,"Chuck", "Norris");
            db.store(pracownik1);
            //##################
            Lecturer prowadzacy1 = new Lecturer("ania", "ania11");
            prowadzacy1.ustawDane(11,"Anna", "Suchenia");
            db.store(prowadzacy1);
            Lecturer prowadzacy2 = new Lecturer("adam", "adam12");
            prowadzacy2.ustawDane(12,"Mieczyslaw", "Drabowski");
            db.store(prowadzacy2);
            //##################
            Student student1 = new Student("rafal", "rafal1");
            student1.ustawDane(1, "Rafal", "Gegotek", "WIEiK",
                    "informatyka", 1, 5);
            student1.dodajPrzedmiot(1);student1.dodajPrzedmiot(2);
            student1.dodajOcene(1);student1.dodajOcene(2);student1.dodajOcene(3);student1.dodajOcene(4);
            db.store(student1);
            //##################
            Student student2 = new Student("adam", "adam1");
            student2.ustawDane(2, "Adam", "Adamowski", "WIEiK",
                    "informatyka", 1, 5);
            student2.dodajPrzedmiot(1);student2.dodajPrzedmiot(2);
            student2.dodajOcene(5);student2.dodajOcene(6);student2.dodajOcene(7);student2.dodajOcene(8);
            db.store(student2);
            //##################
            Subject to = new Subject(1, "TO", 6 , 5);
            to.dodajProwadzacego(11);
            to.dodajStudenta(1); to.dodajStudenta(2);
            to.dodajOcene(1);to.dodajOcene(2);to.dodajOcene(5);to.dodajOcene(6);
            db.store(to);
            //##################
            Subject ip = new Subject(2, "IP", 6 , 5);
            ip.dodajProwadzacego(11); ip.dodajProwadzacego(12);
            ip.dodajStudenta(1); ip.dodajStudenta(2);
            ip.dodajOcene(3); ip.dodajOcene(4); ip.dodajOcene(7); ip.dodajOcene(8);
            db.store(ip);
            //##################
            Mark kol11 = new Mark(1, "kol1", 5 , 1, 1, 1);
            db.store(kol11);
            Mark kol12 = new Mark(2, "kol2", 4 , 1, 1, 1);
            db.store(kol12);
            Mark kol21 = new Mark(3, "kol1", 4.5 , 1, 1, 2);
            db.store(kol21);
            Mark kol22 = new Mark(4, "kol2", 4 , 1, 1, 2);
            db.store(kol22);
            Mark kol13 = new Mark(5, "kol1", 3.5 , 1, 2, 1);
            db.store(kol13);
            Mark kol14 = new Mark(6, "kol2", 4.5 , 1, 2, 1);
            db.store(kol14);
            Mark kol23 = new Mark(7, "kol1", 3.0 , 1, 2, 2);
            db.store(kol23);
            Mark kol24 = new Mark(8, "kol2", 4.5 , 1, 2, 2);
            db.store(kol24);
        }
        finally {
            db.close();
        }
    }

    public static void usunWszystko(){
        System.out.println("\nusuwanie...\n");
        ObjectContainer db= Db4o.openFile("wirtualny_dziekanat");
        try {
            Worker pracownik = new Worker(null, null);
            Lecturer prowadzacy = new Lecturer(null, null);
            Student student = new Student(null, null);
            Subject subject = new Subject(0, null, 0, 0);
            Mark mark = new Mark(0, null, 0, 0, 0, 0);
            ObjectSet result;
            result= db.queryByExample(pracownik);
            while (result.hasNext()) {
                Worker found = (Worker) result.next();
                db.delete(found);
            }
            result= db.queryByExample(prowadzacy);
            while (result.hasNext()) {
                Lecturer found = (Lecturer) result.next();
                db.delete(found);
            }
            result = db.queryByExample(student);
            while (result.hasNext()) {
                Student found = (Student) result.next();
                db.delete(found);
            }
            result = db.queryByExample(subject);
            while (result.hasNext()) {
                Subject found = (Subject) result.next();
                db.delete(found);
            }
            result=db.queryByExample(mark);
            while(result.hasNext()) {
                Mark found = (Mark) result.next();
                db.delete(found);
            }
        }
        finally {
            db.close();
        }
    }

    public static void wypiszWszystko(){
        System.out.println("\nWszystkie dane bazy 'wirtualny_dziekanat'\n");
        ObjectContainer db= Db4o.openFile("wirtualny_dziekanat");
        try {
            Worker pracownik = new Worker(null, null);
            Lecturer prowadzacy = new Lecturer(null, null);
            Student student = new Student(null, null);
            Subject subject = new Subject(0, null, 0, 0);
            Mark mark = new Mark(0, null, 0, 0, 0, 0);
            ObjectSet result;
            result= db.queryByExample(pracownik);
            while (result.hasNext()) {
                pracownik=(Worker) result.next();
                System.out.println(pracownik.toString(1));
            }
            result= db.queryByExample(student);
            while (result.hasNext()) {
                student=(Student) result.next();
                System.out.println(student.toString(1));
            }
            result = db.queryByExample(prowadzacy);
            while (result.hasNext()) {
                prowadzacy=(Lecturer) result.next();
                System.out.println(prowadzacy.toString(1));
            }
            result = db.queryByExample(subject);
            while (result.hasNext()) {
                System.out.println(result.next());
            }
            result = db.queryByExample(mark);
            while (result.hasNext()) {
                System.out.println(result.next());
            }
        }
        finally {
            db.close();
        }
    }
}
