package to;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseData databaseData;
        Scanner in = new Scanner(System.in);
        Student student;
        int idUser=0;
        int idSubject;
        int option;
        boolean back = false;

        while(true){
            back=false;
            System.out.println("\n");
            System.out.println(new String(new char[40]).replace("\0", "@"));
            System.out.println("\t\tWIRTUALNY DZIEKANAT");
            System.out.println(new String(new char[40]).replace("\0", "@"));
            System.out.println("\nLogowanie");
            System.out.println("1. Dla prowadzacyh");
            System.out.println("2. Dla studentow");
            System.out.println("3. Dla pracowników dziekanatu");
            System.out.println("4. Rejestracja nowego studenta");
            System.out.println("0. Wyjście");
            int zmienna = Integer.parseInt(in.nextLine());
//###############################################################################################################
            if(zmienna==1) {
                String login = "";
                String haslo = "";
                while (login.equals("")) {
                    System.out.println("Podaj login");
                    login = in.nextLine();
                }
                while (haslo.equals("")) {
                    System.out.println("Podaj haslo");
                    haslo = in.nextLine();
                }
                Lecturer prowadzacy = new Lecturer(login, haslo);
                idUser = prowadzacy.Zaloguj();
                while ((idUser == 0) && (back==false)) {
                    System.out.println("Naciśnij:\n 1 Jeśli chcesz spróbować ponownie,\n 2. Dla zmiany hasła,\n 0. Wyjście!");
                    zmienna = Integer.parseInt(in.nextLine());
                    if (zmienna == 1) {
                        login = "";
                        haslo = "";
                        while (login.equals("")) {
                            System.out.println("Podaj login");
                            login = in.nextLine();
                        }

                        while (haslo.equals("")) {
                            System.out.println("Podaj haslo");
                            haslo = in.nextLine();
                        }
                        prowadzacy = new Lecturer(login, haslo);
                    }
                    else if (zmienna == 0)
                        back=true;
                    else if (zmienna == 2) {
                        prowadzacy.ZmienHaslo();
                    }
                    else
                        System.out.println("Brak opcji");
                    idUser = prowadzacy.Zaloguj();
                }
                databaseData = new DatabaseData();
                databaseData.wczytajPrzedmioty(idUser);
                while (back == false) {
                    System.out.println();
                    System.out.println(new String(new char[40]).replace("\0", "#"));
                    System.out.println("Prowadzący id=" + idUser + "\n");
                    System.out.println("1. Zobacz swoje dane \n2. Zobacz liste przedmiotów  \n3. Wyloguj");
                    System.out.print("Podaj opcje:  ");
                    option = in.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println(prowadzacy.toString(2));
                            break;
                        case 2:
                            while (!back) {
                                System.out.println();
                                System.out.println(new String(new char[40]).replace("\0", "#"));
                                for (Subject przedmiot: databaseData.przedmioty) {
                                    System.out.println(przedmiot.toString());
                                }
                                databaseData.przedmioty.toString();
                                System.out.println("1. Sprawdź Liste studentów z przedmiotu");
                                System.out.print("2. Powrot \nPodaj opcje:");
                                option = in.nextInt();
                                switch (option) {
                                    case 1:
                                        System.out.print("Podaj id przedmiotu: ");
                                        idSubject = in.nextInt();
                                        databaseData.wczytajStudentowPoIdPrzedmiotu(idSubject);
                                        while (!back) {
                                            System.out.println();
                                            System.out.println(new String(new char[40]).replace("\0", "#"));
                                            System.out.println("1. Sprawdź oceny studenta z przedmiotu");
                                            System.out.println("2. Sprawdź średnia studenta z przedmiotu");
                                            System.out.println("3. Wstaw nową ocene");
                                            System.out.print("4. Powrot \nPodaj opcje:");
                                            option = in.nextInt();
                                            switch (option) {
                                                case 1:
                                                    System.out.print("Podaj id studenta: ");
                                                    option = in.nextInt();
                                                    databaseData.wczytajOcenyStudentaZPrzedmiotu(option, idSubject);
                                                    break;
                                                case 2:
                                                    System.out.print("Podaj id studenta: ");
                                                    option = in.nextInt();
                                                    databaseData.podajSredniaStudenta(option, idSubject);
                                                    break;
                                                case 3:
                                                    double mark;
                                                    int wieght;
                                                    int id;
                                                    String name;
                                                    System.out.print("Podaj id studenta: ");
                                                    id = in.nextInt();
                                                    System.out.print("Podaj stopień: ");
                                                    mark = in.nextDouble();
                                                    System.out.print("Podaj wage: ");
                                                    wieght = in.nextInt();
                                                    in.nextLine();
                                                    System.out.print("Podaj nazwę: ");
                                                    name = in.nextLine();
                                                    databaseData.dodajOcene(id,idSubject,mark,wieght,name);
                                                    break;
                                                case 4:
                                                    back = true;
                                                    break;
                                                default:
                                                    System.out.println("Błędna opcja");
                                            }
                                        }
                                        back = false;
                                        break;
                                    case 2:
                                        back = true;
                                        break;
                                    default:
                                        System.out.println("Błędna opcja");
                                }
                            }
                            back = false;
                            break;
                        case 3:
                            in.nextLine();
                            back = true;
                            break;
                        default:
                            System.out.println("Błędna opcja");
                    }
                }
            }
//###############################################################################################################
            else if(zmienna==2) {
                back=false;
                String login="";
                String haslo="";
                while (login.equals("")){
                    System.out.println("Podaj login");
                    login = in.nextLine();
                }
                while (haslo.equals("")){
                    System.out.println("Podaj haslo");
                    haslo = in.nextLine();
                }
                student=new Student(login,haslo);
                idUser = student.Zaloguj();
                while((idUser == 0) && (back==false)) {
                    System.out.println("Naciśnij:\n 1 Jeśli chcesz spróbować ponownie,\n 2. Dla zmiany hasła,\n 0. Wyjście!");
                    zmienna = Integer.parseInt(in.nextLine());
                    if (zmienna == 1) {
                        login = "";
                        haslo = "";
                        while (login.equals("")) {
                            System.out.println("Podaj login");
                            login = in.nextLine();
                        }
                        while (haslo.equals("")) {
                            System.out.println("Podaj haslo");
                            haslo = in.nextLine();
                        }
                        student = new Student(login, haslo);
                    } else if (zmienna == 0)
                        back=true;
                    else if (zmienna == 2) {
                        student.ZmienHaslo();
                    }
                    else
                        System.out.println("Brak opcji");
                    idUser = student.Zaloguj();
                }
                databaseData = new DatabaseData();
                databaseData.wczytajPrzedmiotyDlaStudenta(idUser);
                while (back == false) {
                    System.out.println();
                    System.out.println(new String(new char[40]).replace("\0", "#"));
                    System.out.println("STUDENT id=" + idUser + "\n");
                    System.out.println("1. Zobacz swoje dane \n2. Zobacz liste przedmiotów \n3. Wyloguj");
                    System.out.print("Podaj opcje: ");
                    option = in.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println(student.toString(2));
                            break;
                        case 2:
                            while(!back) {
                                System.out.println();
                                System.out.println(new String(new char[40]).replace("\0", "#"));
                                for (Subject przedmiot: databaseData.przedmioty) {
                                    System.out.println(przedmiot.toString());
                                }
                                System.out.println("1. Sprawdź oceny z przedmiotu");
                                System.out.println("2. Sprawdź srednią z przedmiotu");
                                System.out.print("3. Powrot \nPodaj opcje:");
                                option = in.nextInt();
                                switch (option) {
                                    case 1:
                                        System.out.print("Podaj id przedmiotu: ");
                                        idSubject = in.nextInt();
                                        databaseData.wczytajOcenyStudentaZPrzedmiotu(idUser, idSubject);
                                        break;
                                    case 2:
                                        System.out.print("Podaj id przedmiotu: ");
                                        idSubject = in.nextInt();
                                        databaseData.podajSredniaStudenta(idUser, idSubject);
                                        break;
                                    case 3:
                                        back=true;
                                        break;
                                    default:
                                        System.out.println("Błędna opcja");
                                }
                            }
                            back=false;
                            break;
                        case 3:
                            in.nextLine();
                            back = true;
                            break;
                        default:
                            System.out.println("Błędna opcja");
                    }
                }
            }
//###############################################################################################################
            else if (zmienna == 3){
                databaseData= new DatabaseData();
                String login = "";
                String haslo = "";
                while (login.equals("")) {
                    System.out.println("Podaj login");
                    login = in.nextLine();
                }
                while (haslo.equals("")) {
                    System.out.println("Podaj haslo");
                    haslo = in.nextLine();
                }
                Worker pracowinik = new Worker(login, haslo);
                idUser = pracowinik.Zaloguj();
                while ((idUser == 0) && (back==false)) {
                    System.out.println("Naciśnij:\n 1 Jeśli chcesz spróbować ponownie,\n 2. Dla zmiany hasła,\n 0. Wyjście!");
                    zmienna = Integer.parseInt(in.nextLine());
                    if (zmienna == 1) {
                        login = "";
                        haslo = "";
                        while (login.equals("")) {
                            System.out.println("Podaj login");
                            login = in.nextLine();
                        }

                        while (haslo.equals("")) {
                            System.out.println("Podaj haslo");
                            haslo = in.nextLine();
                        }
                        pracowinik = new Worker(login, haslo);
                    }
                    else if (zmienna == 0)
                        back=true;
                    else if (zmienna == 2) {
                        pracowinik.ZmienHaslo();
                    }
                    else
                        System.out.println("Brak opcji");
                    idUser = pracowinik.Zaloguj();
                }
                while (back == false) {
                    System.out.println();
                    System.out.println(new String(new char[40]).replace("\0", "#"));
                    System.out.println("Pracownik dziekantu\n");
                    System.out.println("1. Pokaż liste użytkowników");
                    System.out.println("2. Pokaż liste prowadzących");
                    System.out.println("3. Pokaż liste studentów");
                    System.out.println("4. Pokaż liste przedmitów");
                    System.out.println("5. Pokaż liste ocen");
                    System.out.println("6. Dodaj przedmiot");
                    System.out.println("0. Wyloguj");
                    System.out.print("Podaj opcje:  ");
                    option = in.nextInt();
                    switch (option) {
                        case 1:
                            databaseData.wypiszWszystkichProwadzacych();
                            databaseData.wypiszWszystkichStudentow();
                            databaseData.wypiszWszystkichPracownikow();
                            break;
                        case 2:
                            databaseData.wypiszWszystkichProwadzacych();
                            break;
                        case 3:
                            databaseData.wypiszWszystkichStudentow();
                            break;
                        case 4:
                            databaseData.wypiszWszystkiePrzedmioty();
                            break;
                        case 5:
                           databaseData.wypiszWszystkieOceny();
                            break;
                        case 6:
                            System.out.println();
                            System.out.println(new String(new char[40]).replace("\0", "#"));
                            String nazwa;
                            int pkt;
                            int semestr;
                            int ilosc;
                            List<Integer> idProwadzacych = new ArrayList<Integer>();
                            List<Integer> idStudentow = new ArrayList<Integer>();
                            System.out.println("Podaj nazwe");
                            in.nextLine();
                            nazwa=in.nextLine();
                            System.out.println("Podaj ilosc pkt ECTS");
                            pkt= in.nextInt();
                            System.out.println("Podaj semestr w jakim bedzie sie odbywal");
                            semestr = in.nextInt();
                            System.out.println("Podaj ilosc prowadzacyh");
                            ilosc = in.nextInt();
                            for (int i = 0; i < ilosc; i++) {
                                System.out.print("  Podaj id prowadzacego: ");
                                idProwadzacych.add(in.nextInt());
                            }
                            databaseData.wypiszWszystkichStudentow();
                            System.out.println("Podaj ilosc studentow");
                            ilosc = in.nextInt();
                            for (int i = 0; i < ilosc; i++) {
                                System.out.print("  Podaj id studenta: ");
                                idStudentow.add(in.nextInt());
                            }
                            databaseData.dodajPrzedmiot(nazwa,  pkt, semestr,  idProwadzacych, idStudentow);
                            break;
                        case 0:
                            back = true;
                            in.nextLine();
                            break;
                        default:
                            System.out.println("Błędna opcja");
                    }
                }
            }
            else if (zmienna == 4) {
                List<String> dane = new ArrayList<String>();
                System.out.println("Podaj login");
                dane.add(in.nextLine());
                System.out.println("Podaj haslo");
                dane.add(in.nextLine());
                System.out.println("Podaj pesel");
                dane.add(in.nextLine());
                System.out.println("Podaj imie");
                dane.add(in.nextLine());
                System.out.println("Podaj nazwisko");
                dane.add(in.nextLine());
                System.out.println("Podaj wydzial");
                dane.add(in.nextLine());
                System.out.println("Podaj kierunek");
                dane.add(in.nextLine());
                System.out.println("Podaj stopien studiow");
                dane.add(in.nextLine());
                System.out.println("Podaj nr semestru");
                dane.add(in.nextLine());
                student= new Student(dane.get(0), dane.get(1));
                student.ustawDane(Integer.parseInt(dane.get(2)), dane.get(3), dane.get(4), dane.get(5),
                        dane.get(6), Integer.parseInt(dane.get(7)), Integer.parseInt(dane.get(8)));
                databaseData = new DatabaseData();
                databaseData.dodajStudenta(student);
            }
            else
                System.exit(0);
        }
    }
}
