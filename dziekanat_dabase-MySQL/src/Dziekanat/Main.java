package Dziekanat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main{

    private static Data data = new Data();

    public static void main(String[] args) throws SQLException {

        String aa="dssd";
        if (aa.equals("dssd"))
            System.out.println("tak");
       if(aa=="dssd")
            System.out.println("tez tak");
        else
            System.out.println("nie");

        Scanner scan = new Scanner(System.in);
        int log;
        String pass;
        int option;
        boolean back = false;
        Uzytkownik currentUser = new Uzytkownik();

        while (back == false) {
            System.out.println(new String(new char[40]).replace("\0", "@"));
            System.out.println("\t\tWIRTUALNY DZIEKANAT");
            System.out.println(new String(new char[40]).replace("\0", "@"));
            System.out.print("Zaloguj sie \nlogin: ");
            log = scan.nextInt(); scan.nextLine();
            System.out.print("haslo: ");
            pass=scan.nextLine();
            currentUser = data.logIn(log, pass);

            switch (currentUser.getTyp()) {
                case "student":
                    while (back == false) {
                        System.out.println(new String(new char[40]).replace("\0", "#"));
                        System.out.println("STUDENT id=" + currentUser.getLogin() + "\n");
                        System.out.println("1. Zobacz swoje dane \n2. Zobacz liste przedmiotów \n3. Wyloguj");
                        System.out.print("Podaj opcje: ");
                        option = scan.nextInt();
                        switch (option) {
                            case 1:
                                data.showStudentById(currentUser.getLogin());
                                break;
                            case 2:
                                while(!back) {
                                    System.out.println(new String(new char[40]).replace("\0", "#"));
                                    data.showSubjectForStudent(currentUser.getLogin());
                                    System.out.println("1. Sprawdź oceny z przedmiotu");
                                    System.out.println("2. Sprawdź srednią z przedmiotu");
                                    System.out.print("3. Powrot \nPodaj opcje:");
                                    option = scan.nextInt();
                                    switch (option) {
                                        case 1:
                                            System.out.print("Podaj id przedmiotu: ");
                                            option = scan.nextInt();
                                            System.out.println(data.showNameSubjectById(option));
                                            data.showMarksFromSubjectToStudent(option, currentUser.getLogin());
                                            break;
                                        case 2:
                                            System.out.print("Podaj id przedmiotu: ");
                                            option = scan.nextInt();
                                            System.out.println(data.showNameSubjectById(option));
                                            data.showAverageFromSubjectToStudent(option, currentUser.getLogin());
                                            break;
                                        case 3:
                                            System.out.println("\n");
                                            back=true;
                                            break;
                                        default:
                                            System.out.println("Błędna opcja");
                                    }
                                }
                                back=false;
                                break;
                            case 3:
                                back = true;
                                break;
                            default:
                                System.out.println("Błędna opcja");
                        }
                    }
                    break;
                case "prowadzacy":
                    while (back == false) {
                        System.out.println(new String(new char[40]).replace("\0", "#"));
                        System.out.println("Prowadzący id=" + currentUser.getLogin() + "\n");
                        System.out.println("1. Zobacz swoje dane \n2. Zobacz liste przedmiotów  \n3. Wyloguj");
                        System.out.print("Podaj opcje:  ");
                        option = scan.nextInt();
                        switch (option) {
                            case 1:
                                data.showLecturerById(currentUser.getLogin());
                                break;
                            case 2:
                                while(!back) {
                                    System.out.println(new String(new char[40]).replace("\0", "#"));
                                    data.showSubjectForLecturer(currentUser.getLogin());
                                    System.out.println("1. Sprawdź Liste studentów z przedmiotu");
                                    System.out.print("2. Powrot \nPodaj opcje:");
                                    option = scan.nextInt();
                                    switch (option) {
                                        case 1:
                                            int subject;
                                            System.out.print("Podaj id przedmiotu: ");
                                            subject = scan.nextInt();
                                            System.out.println(data.showNameSubjectById(subject));
                                            data.showStudentFromSubjectToLecturer(subject);
                                            while(!back) {
                                                System.out.println(new String(new char[40]).replace("\0", "#"));
                                                System.out.println("1. Sprawdź oceny studenta z przedmiotu");
                                                System.out.println("2. Sprawdź średnia studenta z przedmiotu");
                                                System.out.println("3. Wstaw nową ocene");
                                                System.out.print("4. Powrot \nPodaj opcje:");
                                                option = scan.nextInt();
                                                switch (option) {
                                                    case 1:
                                                        System.out.print("Podaj id studenta: ");
                                                        option = scan.nextInt();
                                                        System.out.println(data.showNameSubjectAndSurnameStudent(subject, option));
                                                        data.showMarksFromSubjectToStudent(subject, option);
                                                        break;
                                                    case 2:
                                                        System.out.print("Podaj id studenta: ");
                                                        option = scan.nextInt();
                                                        System.out.println(data.showNameSubjectAndSurnameStudent(subject, option));
                                                        data.showAverageFromSubjectToStudent(subject, option);
                                                        break;
                                                    case 3:
                                                        float mark;
                                                        int wieght;
                                                        int id;
                                                        String name;
                                                        System.out.print("Podaj id studenta: "); id = scan.nextInt();
                                                        System.out.print("Podaj stopień: "); mark = scan.nextFloat();
                                                        System.out.print("Podaj wage: "); wieght = scan.nextInt();
                                                        scan.nextLine();
                                                        System.out.print("Podaj nazwę: "); name = scan.nextLine();
                                                        data.newMark(id,subject, mark,wieght,name);
                                                        data.showMarksFromSubjectToStudent(subject, id);
                                                        break;
                                                    case 4:
                                                        System.out.println("\n");
                                                        back = true;
                                                        break;
                                                    default:
                                                        System.out.println("Błędna opcja");
                                                }
                                            }
                                            back = false;
                                            break;
                                        case 2:
                                            System.out.println("\n");
                                            back=true;
                                            break;
                                        default:
                                            System.out.println("Błędna opcja");
                                    }
                                }
                                back=false;
                                break;
                            case 3:
                                back = true;
                                break;
                            default:
                                System.out.println("Błędna opcja");
                        }
                    }
                    break;
                case "pracownik_dziekanatu":
                    while (back == false) {
                        System.out.println(new String(new char[40]).replace("\0", "#"));
                        System.out.println("Pracownik dziekantu id=" + currentUser.getLogin() + "\n");
                        System.out.println("1. Pokaż liste użytkowników");
                        System.out.println("2. Pokaż liste prowadzących");
                        System.out.println("3. Pokaż liste studentów");
                        System.out.println("4. Pokaż liste przedmitów");
                        System.out.println("5. Pokaż liste ocen");
                        System.out.println("6. Wyloguj");
                        System.out.print("Podaj opcje:  ");
                        option = scan.nextInt();
                        switch (option) {
                            case 1:
                                data.writeUsers();
                                break;
                            case 2:
                                data.writeLecturers();
                                break;
                            case 3:
                                data.writeStudents();
                                break;
                            case 4:
                                data.writeSubjects();
                                break;
                            case 5:
                                data.writeMarks();
                                break;
                            case 6:
                                back = true;
                                break;
                            default:
                                System.out.println("Błędna opcja");
                        }
                    }
                    break;
                default:
                    System.out.println("Brak danych");
            }
            back=false;
        }
    }

}

/*shurtcat:
 ctrl + F9 -debug;   shift + F10 -run   shift + F9 -debug
 sout ; psvm
 alt + enter -podpowiedz
 alt + Fn + insert -getter, setter, constructor
 ctrl +d -duplikuj linie kodu
 shift + tab -usuwa tabulacje
 shift + F6 -zmiana nazw
 ctr + / -zakomentowanie
 alt + strzałka  lubb  ctrl + tab -poruszanie sie pomiedzy plikami(klasami)
 ctrl + alt + m -zaznacz kod i zamien go na metode
 ctrl + alt + b -przenosi do implementacji zaznaczonej klasy/metody
*/