package to;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.ArrayList;
import java.util.List;

public class DatabaseData {

    List<Student> students;
    List<Subject> przedmioty;
    List<Mark> oceny;
    boolean wczytane=false;

//########################### DLA PROWADZACEGO ###################################
    public void wczytajPrzedmioty(int id) {
        przedmioty=new ArrayList<Subject>();
        Subject przedmiot;
        Subject findSubject=new Subject(0, null,0,0);
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(findSubject);
            while (result.hasNext()) {
                przedmiot=(Subject) result.next();
                for (int i = 0; i <przedmiot.prowadzacy.size() ; i++) {
                    if(przedmiot.prowadzacy.get(i)==id)
                        przedmioty.add(przedmiot);
                }
            }
        }
        finally {
            db.close();
        }
    }

    public void wczytajStudentowPoIdPrzedmiotu(int id) {
        students=new ArrayList<Student>();
        Student student;
        Student findStudent=new Student(null, null);
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(findStudent);
            while (result.hasNext()) {
                student=(Student) result.next();
                for (int i = 0; i <student.przedmioty.size() ; i++) {
                    if(student.przedmioty.get(i)==id) {
                        students.add(student);
                        System.out.println(student.toString(3));
                    }
                }
            }
            //System.out.println("ilosc przedmiotow: "+students.size());
        }
        finally {
            db.close();
        }
    }

    public void wczytajOcenyStudentaZPrzedmiotu(int studentId, int przedmiotId) {
        oceny=new ArrayList<Mark>();
        Mark findMark=new Mark(0, null, 0, 0,studentId,przedmiotId);
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(findMark);
            while (result.hasNext()) {
                        oceny.add((Mark) result.next());
                        System.out.println(oceny.get(oceny.size()-1).toString());
            }
        }
        finally {
            db.close();
        }
    }

    public void podajSredniaStudenta(int studentId, int przedmiotId) {
        float stopnie=0;
        float wagi=0;
        float srednia=0;
        Mark ocena;
        Mark findMark=new Mark(0, null, 0, 0,studentId,przedmiotId);
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(findMark);
            while (result.hasNext()) {
                ocena=(Mark) result.next();
                stopnie+=(ocena.stopien*ocena.waga);
                wagi+=ocena.waga;
            }
            srednia=stopnie/wagi;
            System.out.println("Srednia = "+srednia);
        }
        finally {
            db.close();
        }
    }

    public void dodajOcene(int studentId, int przedmiotId, double stopien, int waga, String nazwa){
        int noweID=0;
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Mark prototype= new Mark(0, null, 0, 0,0,0);
            ObjectSet result=db.queryByExample(prototype);
            while(result.hasNext()) {
                result.next();
                noweID++;
            }
            noweID++;
            prototype = new Mark(noweID, nazwa, stopien, waga,studentId,przedmiotId);
            db.store(prototype);
            Subject prototype2= new Subject(przedmiotId, null, 0, 0);
            result=db.queryByExample(prototype2);
            while (result.hasNext()) {
                prototype2=(Subject) result.next();
                prototype2.oceny.add(noweID);
                przedmioty.get(przedmiotId-1).oceny.add(noweID);
            }
            db.store(prototype2);
            System.out.println("Dodano ocene!");
        } finally {
            db.close();
        }
    }
//########################### DLA STUDENTA ###################################
    public void wczytajPrzedmiotyDlaStudenta(int id) {
        przedmioty=new ArrayList<Subject>();
        Subject przedmiot;
        Subject findSubject=new Subject(0, null,0,0);
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            ObjectSet result = db.queryByExample(findSubject);
            while (result.hasNext()) {
                przedmiot=(Subject) result.next();
                for (int i = 0; i <przedmiot.students.size() ; i++) {
                    if(przedmiot.students.get(i)==id)
                        przedmioty.add(przedmiot);
                }
            }
        }
        finally {
            db.close();
        }
    }

//########################### DLA PRACOWNIKA DZIEKANATU ###################################
    public static void wypiszWszystkichPracownikow() {
        System.out.println("\nPRACOWNICY DZIEKANATU\n");
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Worker pracownik = new Worker(null, null);
            ObjectSet result;
            result = db.queryByExample(pracownik);
            while (result.hasNext()) {
                pracownik = (Worker) result.next();
                System.out.println(pracownik.toString(3));
            }
        } finally {
            db.close();
        }
    }
    public static void wypiszWszystkichProwadzacych() {
        System.out.println("\nPROWADZACY\n");
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Lecturer prowadzacy = new Lecturer(null, null);
            ObjectSet result;
            result = db.queryByExample(prowadzacy);
            while (result.hasNext()) {
                prowadzacy = (Lecturer) result.next();
                System.out.println(prowadzacy.toString(3));
            }
        } finally {
            db.close();
        }
    }
    public static void wypiszWszystkichStudentow() {
        System.out.println("\nSTUDENCI\n");
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Student student = new Student(null, null);
            ObjectSet result;
            result = db.queryByExample(student);
            while (result.hasNext()) {
                student = (Student) result.next();
                System.out.println(student.toString(3));
            }
        } finally {
            db.close();
        }
    }

    public static void wypiszWszystkiePrzedmioty() {
        System.out.println("\nPRZEDMIOTY\n");
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Subject subject = new Subject(0, null, 0, 0);
            ObjectSet result;
            result = db.queryByExample(subject);
            while (result.hasNext()) {
                System.out.println(result.next());
            }
        } finally {
            db.close();
        }
    }

    public static void wypiszWszystkieOceny() {
        System.out.println("\nOCENY\n");
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Mark mark = new Mark(0, null, 0, 0, 0, 0);
            ObjectSet result;
            result = db.queryByExample(mark);
            while (result.hasNext()) {
                System.out.println(result.next());
            }
        } finally {
            db.close();
        }
    }

    public void dodajStudenta(Student student) {
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Student prototype = new Student(student.login, null);
            ObjectSet result = db.queryByExample(prototype);
            if (!result.hasNext()) {
                db.store(student);
                System.out.println("Dodano Studenta!");
            } else
                System.out.println("Login zajęty!");
        } finally {
            db.close();
        }
    }

    public void dodajPrzedmiot(String nazwa, int pkt, int semestr, List<Integer> idProwadzacyh,List<Integer> idStudentow) {
        int noweID=0;
        ObjectContainer db = Db4o.openFile("wirtualny_dziekanat");
        try {
            Subject prototype= new Subject(0, null, 0, 0);
            ObjectSet result=db.queryByExample(prototype);
            while(result.hasNext()) {
                result.next();
                noweID++;
            }
            noweID++;
            prototype = new Subject(noweID, nazwa, pkt, semestr);
            prototype.prowadzacy=idProwadzacyh;
            prototype.students=idStudentow;
            db.store(prototype);
            System.out.println("Dodano przedmiot\n"+prototype.toString());
        } finally {
            db.close();
        }
    }

}
