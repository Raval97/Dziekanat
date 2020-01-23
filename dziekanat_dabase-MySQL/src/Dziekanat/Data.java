package Dziekanat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public List<Uzytkownik> users = new ArrayList<Uzytkownik>();
    public List<Student> students = new ArrayList<Student>();
    public List<Prowadzacy> lecturers = new ArrayList<Prowadzacy>();
    public List<Przedmiot> subjects = new ArrayList<Przedmiot>();
    public List<Ocena> marks = new ArrayList<Ocena>();

    private Connection conn = null;
    Statement stmt = null;
    ResultSet srs = null;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "dziekanat";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private  String password = "";

    public Data(){
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            readUsers();
            readStudents();
            readLecturers();
            readSubjects();
            readMarks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newUser() throws SQLException {
        int id=1114;
        String pwd="stu4";
        String type="student";
        String q1 = "insert into dziekanat.uzytkownicy values('" +id+ "', '" +pwd+ "', '" +type+ "')";
        //CALL dodaj_ocene(1111, 1, 4.5, 1, "lab3")
        int x = stmt.executeUpdate(q1);
        if (x > 0)
            System.out.println("Successfully Inserted");
        else
            System.out.println("Insert Failed");
    }

    public Uzytkownik logIn(int log, String pass) throws SQLException {
        Uzytkownik user = new Uzytkownik();
        try {
            srs = stmt.executeQuery("SELECT * FROM uzytkownicy where login=" + log + " and haslo=\'" + pass + "\'");
            srs.next();
            user.setLogin(srs.getInt( "login"));
            user.setHaslo(srs.getString( "haslo"));
            user.setTyp(srs.getString( "typ"));
        }
        catch(Exception e){
            System.out.println("Błedne dane do logowania");
        }
        return user;
    }

//####################################################################################################################
    public String showNameSubjectById(int id) throws SQLException{
        srs = stmt.executeQuery("select nazwa from przedmiot where id=\'" + id + "\'");
        srs.next();
        return "\n  "+srs.getString(1);
    }

    public String showNameSubjectAndSurnameStudent(int id_sub, int id_stu)throws SQLException{
        srs = stmt.executeQuery("select s.nazwisko, p.nazwa from student as s left join przedmiot_student " +
                        "as ps on s.id=ps.id_studenta left join przedmiot as p on p.id=ps.id_przedmiotu " +
                        "where ps.id_przedmiotu=\'" + id_sub + "\' and s.id=\'" + id_stu + "\'");
        srs.next();
        String str="\n  Przedmiot: "+srs.getString(2)+", Student: "+srs.getString(1);
        return str;
    }

//####################################################################################################################
    public void showStudentById(int id) throws SQLException {
    System.out.print("\n  Dane Studenta:");
    srs = stmt.executeQuery("select * from student where id=\'" + id + "\'");
    srs.next();
    System.out.println("\n  id= "+srs.getInt(1) + "\n  "+
            "nazwisko= "+srs.getString(2) + "\n  "+
            "imie= "+srs.getString(3) + "\n  "+
            "wydzial= "+srs.getString(4) + "\n  "+
            "kierunek= "+srs.getString(5) + "\n  "+
            "stopień= "+srs.getString(6) + "\n  "+
            "nr semestru= "+srs.getString(7) + "\n");
}

    public void showSubjectForStudent(int id) throws SQLException {
        System.out.print("\n  Przedmioty Studenta:\n");
        srs = stmt.executeQuery("select p.* from przedmiot as p left join przedmiot_student as ps on" +
                " p.id=ps.id_przedmiotu where ps.id_studenta=\'" + id + "\'");
        while (srs.next()) {
            System.out.println("  id=" + srs.getString(1) + ",  " +
                    "nazwa=" + srs.getString(2) + ",  " +
                    "pkt ECTS=" + srs.getString(3) + ",  " +
                    "semestr=" + srs.getString(4));
        }
        System.out.println();
    }

    public void showMarksFromSubjectToStudent(int id_sub, int id_stu) throws SQLException {
        //System.out.print("  Oceny z przedmiotu "+id_sub+"\n");
        //showNameSubjectById(id_sub);
        srs = stmt.executeQuery("select * from ocena where id_studenta=\'"+id_stu+"\' && id in " +
                "(select id_oceny from przedmiot_ocena where id_przedmiotu=\'"+id_sub+"\' && id_przedmiotu " +
                "in (select id_przedmiotu from przedmiot_student where id_studenta=\'"+id_stu+"\'))");
        while (srs.next()) {
            System.out.print("  nazwa= " + srs.getString(2) + ",  " +
                    "stopeiń= " + srs.getInt(3) + ",  " +
                    "waga= " + srs.getInt(4) + "\n");
        }
        System.out.println();
    }

    public void showAverageFromSubjectToStudent(int id_sub, int id_stu) throws SQLException {
        srs = stmt.executeQuery("select (sum(stopien*waga)/sum(waga)) from ocena where " +
                "id_studenta=\'"+id_stu+"\' && id in (select id_oceny from przedmiot_ocena " +
                "where id_przedmiotu=\'"+id_sub+"\' && id_przedmiotu in (select id_przedmiotu" +
                " from przedmiot_student where id_studenta=\'"+id_stu+"\'))");
        srs.next();
        System.out.println("  Srednia= " + srs.getString(1) + "\n");
    }

//####################################################################################################################
    public void showLecturerById(int id) throws SQLException {
        System.out.print("\n  Dane Prowadzącego:");
        srs = stmt.executeQuery("select * from prowadzacy where id=\'" + id + "\'");
        srs.next();
        System.out.println("\n  id= "+srs.getInt(1) + "\n  "+
                "nazwisko= "+srs.getString(2) + "\n  "+
                "imie= "+srs.getString(3) + "\n  ");
    }

    public void showSubjectForLecturer(int id) throws SQLException {
        System.out.print("\n  Przedmioty Prowadzącego:\n");
        srs = stmt.executeQuery("select p.* from przedmiot as p left join przedmiot_prowadzacy as pp on" +
                " p.id=pp.id_przedmiotu where pp.id_prowadzacego=\'" + id + "\'");
        while (srs.next()) {
            System.out.println("  id=" + srs.getString(1) + ",  " +
                    "nazwa=" + srs.getString(2) + ",  " +
                    "pkt ECTS=" + srs.getString(3) + ",  " +
                    "semestr=" + srs.getString(4));
        }
        System.out.println();
    }

    public void showStudentFromSubjectToLecturer(int id_sub) throws SQLException {
        srs = stmt.executeQuery("select s.* from student as s left join przedmiot_student as ps on " +
                "s.id=ps.id_studenta where ps.id_przedmiotu=\'"+id_sub+"\'");
        while (srs.next()) {
            System.out.print(" id= " + srs.getString(1) + ",  " +
                    "nazwisko=" + srs.getString(2) + ",  " +
                    "imie=" + srs.getString(3) + ",  " +
                    "wydzial=" + srs.getString(4) + ",  " +
                    "kierunek=" + srs.getString(5) + ",  " +
                    "stopień=" + srs.getString(6) + ",  " +
                    "semestr=" + srs.getString(7) + "\n");
        }
        System.out.println();
    }

    public void newMark(int id_stu, int id_przed, float stopien, int waga, String nazwa) throws SQLException {
        String q1 = "CALL dodaj_ocene(\'"+id_stu+"\', \'"+id_przed+"\', \'"+stopien+"\', \'"+waga+"\', \'"+nazwa+"\')";
        int x = stmt.executeUpdate(q1);
        if (x > 0)
            System.out.println("Successfully Inserted");
        else
            System.out.println("Insert Failed");
    }

//####################################################################################################################
    private void readUsers() throws SQLException {
        srs = stmt.executeQuery("SELECT * FROM uzytkownicy");
        int i=0;
        while (srs.next()) {
            users.add(new Uzytkownik());
            users.get(i).setLogin(srs.getInt( "login"));
            users.get(i).setHaslo(srs.getString( "haslo"));
            users.get(i).setTyp(srs.getString( "typ"));
            i++;
        }
    }

    public void writeUsers() {
        try{
            System.out.println("  Użytkownicy:");
            for(int i=0; i<users.size(); i++){
                System.out.println("  "+(i+1)+". "+users.get(i).toString());
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }

    private void readStudents() throws SQLException {
        srs = stmt.executeQuery("SELECT * FROM student");
        int i=0;
        while (srs.next()) {
            students.add(new Student());
            students.get(i).setIdLegitymacji(srs.getInt( "id"));
            students.get(i).setImie(srs.getString( "imie"));
            students.get(i).setNazwisko(srs.getString( "nazwisko"));
            students.get(i).setWydzial(srs.getString( "wydzial"));
            students.get(i).setKierunek(srs.getString( "kierunek"));
            students.get(i).setStopienStudiow(srs.getInt( "stopien_studiow"));
            students.get(i).setNrSemestru(srs.getInt( "nr_semestru"));
            i++;
        }
    }

    public void writeStudents() {
        try{
            System.out.println("  Studenci:");
            for(int i=0; i<students.size(); i++){
                System.out.println("  "+(i+1)+". "+students.get(i).toString());
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }

    private void readLecturers() throws SQLException {
        srs = stmt.executeQuery("SELECT * FROM prowadzacy");
        int i=0;
        while (srs.next()) {
            lecturers.add(new Prowadzacy());
            lecturers.get(i).setIdProwadzaceo(srs.getInt( "id"));
            lecturers.get(i).setImie(srs.getString( "imie"));
            lecturers.get(i).setNazwisko(srs.getString( "nazwisko"));
            i++;
        }
    }

    public void writeLecturers() {
        try{
            System.out.println("  Prowadzący:");
            for(int i=0; i<lecturers.size(); i++){
                System.out.println("  "+(i+1)+". "+lecturers.get(i).toString());
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }

    private void readSubjects() throws SQLException {
        srs = stmt.executeQuery("SELECT * FROM przedmiot");
        int i=0;
        while (srs.next()) {
            subjects.add(new Przedmiot());
            subjects.get(i).setIdPrzedmiotu(srs.getInt( "id"));
            subjects.get(i).setSemestr(srs.getInt( "semestr"));
            subjects.get(i).setNazwa(srs.getString( "nazwa"));
            subjects.get(i).setPktECTS(srs.getInt( "pktECTS"));
            i++;
        }
    }

    public void writeSubjects() {
        try{
            System.out.println("  Przedmioty");
            for(int i=0; i<subjects.size(); i++){
                System.out.println("  "+(i+1)+". "+subjects.get(i).toString());
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }

    private void readMarks() throws SQLException {
        srs = stmt.executeQuery("SELECT * FROM ocena");
        int i=0;
        while (srs.next()) {
            marks.add(new Ocena());
            marks.get(i).setIdOceny(srs.getInt( "id"));
            marks.get(i).setNazwa(srs.getString( "nazwa"));
            marks.get(i).setStopien(srs.getInt( "stopien"));
            marks.get(i).setWaga(srs.getFloat( "waga"));
            i++;
        }
    }

    public void writeMarks() {
        try{
            System.out.println("  Oceny:");
            for(int i=0; i<marks.size(); i++){
                System.out.println("  "+(i+1)+". "+marks.get(i).toString());
            }
            System.out.println();
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Error witch index, probably index out of bounds");
        }
    }
}

