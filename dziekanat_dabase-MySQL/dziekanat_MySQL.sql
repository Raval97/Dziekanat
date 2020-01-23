-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 28 Gru 2019, 00:22
-- Wersja serwera: 10.1.37-MariaDB
-- Wersja PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `dziekanat`
--

DELIMITER $$
--
-- Procedury
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `dodaj_ocene` (IN `id_stu` INT, IN `id_przed` INT, IN `val` FLOAT, IN `weight` INT, IN `name` TEXT)  NO SQL
BEGIN
 set @idOceny=(SELECT MAX(id)+1 FROM ocena);
 INSERT INTO ocena(id, nazwa, stopien, waga, id_studenta) VALUES
 (@idOceny, name, val, weight, id_stu);
 INSERT INTO przedmiot_ocena(id_przedmiotu, id_oceny) VALUES
 (id_przed, @idOceny);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `ocena`
--

CREATE TABLE `ocena` (
  `id` int(11) NOT NULL,
  `nazwa` text NOT NULL,
  `stopien` float NOT NULL,
  `waga` float NOT NULL,
  `id_studenta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `ocena`
--

INSERT INTO `ocena` (`id`, `nazwa`, `stopien`, `waga`, `id_studenta`) VALUES
(1, 'lab6', 5, 1, 1111),
(2, 'lab5', 4.5, 1, 1111),
(3, 'lab6', 5, 1, 1112),
(4, 'lab5', 5, 1, 1112),
(5, 'lab6', 5, 1, 1113),
(6, 'lab5', 4, 1, 1113),
(7, 'lab1', 3, 1, 1111),
(8, 'lab1', 3.5, 1, 1112),
(9, 'kol1', 4, 1, 1111),
(10, 'kol1', 4.5, 1, 1112),
(11, 'kol1', 5, 1, 1113),
(12, 'lab3', 4.5, 1, 1111);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `prowadzacy`
--

CREATE TABLE `prowadzacy` (
  `id` int(11) NOT NULL,
  `nazwisko` text NOT NULL,
  `imie` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `prowadzacy`
--

INSERT INTO `prowadzacy` (`id`, `nazwisko`, `imie`) VALUES
(2222, 'Suchenia', 'Anna'),
(2223, 'Dorota', 'Dariusz'),
(2224, 'Mieczyslaw', 'Drabowski');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmiot`
--

CREATE TABLE `przedmiot` (
  `id` int(11) NOT NULL,
  `nazwa` text NOT NULL,
  `pktECTS` int(11) NOT NULL,
  `semestr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przedmiot`
--

INSERT INTO `przedmiot` (`id`, `nazwa`, `pktECTS`, `semestr`) VALUES
(1, 'Technologie Obiektowe', 6, 5),
(2, 'Systemy Wbudowane', 6, 5),
(3, 'Inzynieria Programowania', 6, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmiot_ocena`
--

CREATE TABLE `przedmiot_ocena` (
  `id_przedmiotu` int(11) NOT NULL,
  `id_oceny` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przedmiot_ocena`
--

INSERT INTO `przedmiot_ocena` (`id_przedmiotu`, `id_oceny`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 9),
(2, 10),
(2, 11),
(3, 7),
(3, 8);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmiot_prowadzacy`
--

CREATE TABLE `przedmiot_prowadzacy` (
  `id_przedmiotu` int(11) NOT NULL,
  `id_prowadzacego` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przedmiot_prowadzacy`
--

INSERT INTO `przedmiot_prowadzacy` (`id_przedmiotu`, `id_prowadzacego`) VALUES
(1, 2222),
(2, 2223),
(3, 2222),
(3, 2224);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmiot_student`
--

CREATE TABLE `przedmiot_student` (
  `id_przedmiotu` int(11) NOT NULL,
  `id_studenta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `przedmiot_student`
--

INSERT INTO `przedmiot_student` (`id_przedmiotu`, `id_studenta`) VALUES
(1, 1111),
(1, 1112),
(1, 1113),
(2, 1111),
(2, 1112),
(3, 1111),
(3, 1112),
(3, 1113);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `nazwisko` text NOT NULL,
  `imie` text NOT NULL,
  `wydzial` text NOT NULL,
  `kierunek` text NOT NULL,
  `stopien_studiow` int(11) NOT NULL,
  `nr_semestru` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `student`
--

INSERT INTO `student` (`id`, `nazwisko`, `imie`, `wydzial`, `kierunek`, `stopien_studiow`, `nr_semestru`) VALUES
(1111, 'Nowak', 'Adam', 'WIEiK', 'Informatyka', 1, 5),
(1112, 'Kowalski', 'Jan', 'WIEiK', 'Informatyka', 1, 5),
(1113, 'Lewandowska', 'Anna', 'WIEiK', 'Informatyka', 1, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE `uzytkownicy` (
  `login` int(11) NOT NULL,
  `haslo` text NOT NULL,
  `typ` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`login`, `haslo`, `typ`) VALUES
(1111, 'stu', 'student'),
(1112, 'stu2', 'student'),
(1113, 'stu3', 'student'),
(2222, 'prof', 'prowadzacy'),
(2223, 'prof3', 'prowadzacy'),
(2224, 'prof4', 'prowadzacy'),
(3333, 'admin', 'pracownik_dziekanatu');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `ocena`
--
ALTER TABLE `ocena`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ID_STUDENTA` (`id_studenta`);

--
-- Indeksy dla tabeli `prowadzacy`
--
ALTER TABLE `prowadzacy`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `przedmiot`
--
ALTER TABLE `przedmiot`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `przedmiot_ocena`
--
ALTER TABLE `przedmiot_ocena`
  ADD PRIMARY KEY (`id_przedmiotu`,`id_oceny`),
  ADD KEY `id_oceny` (`id_oceny`);

--
-- Indeksy dla tabeli `przedmiot_prowadzacy`
--
ALTER TABLE `przedmiot_prowadzacy`
  ADD PRIMARY KEY (`id_przedmiotu`,`id_prowadzacego`),
  ADD KEY `id_prowadzacego` (`id_prowadzacego`);

--
-- Indeksy dla tabeli `przedmiot_student`
--
ALTER TABLE `przedmiot_student`
  ADD PRIMARY KEY (`id_przedmiotu`,`id_studenta`),
  ADD KEY `id_studenta` (`id_studenta`);

--
-- Indeksy dla tabeli `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `uzytkownicy`
--
ALTER TABLE `uzytkownicy`
  ADD PRIMARY KEY (`login`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `ocena`
--
ALTER TABLE `ocena`
  ADD CONSTRAINT `ocena_ibfk_1` FOREIGN KEY (`id_studenta`) REFERENCES `student` (`id`);

--
-- Ograniczenia dla tabeli `prowadzacy`
--
ALTER TABLE `prowadzacy`
  ADD CONSTRAINT `prowadzacy_ibfk_1` FOREIGN KEY (`id`) REFERENCES `uzytkownicy` (`login`);

--
-- Ograniczenia dla tabeli `przedmiot_ocena`
--
ALTER TABLE `przedmiot_ocena`
  ADD CONSTRAINT `przedmiot_ocena_ibfk_1` FOREIGN KEY (`id_oceny`) REFERENCES `ocena` (`id`),
  ADD CONSTRAINT `przedmiot_ocena_ibfk_2` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmiot` (`id`);

--
-- Ograniczenia dla tabeli `przedmiot_prowadzacy`
--
ALTER TABLE `przedmiot_prowadzacy`
  ADD CONSTRAINT `przedmiot_prowadzacy_ibfk_1` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmiot` (`id`),
  ADD CONSTRAINT `przedmiot_prowadzacy_ibfk_2` FOREIGN KEY (`id_prowadzacego`) REFERENCES `prowadzacy` (`id`);

--
-- Ograniczenia dla tabeli `przedmiot_student`
--
ALTER TABLE `przedmiot_student`
  ADD CONSTRAINT `przedmiot_student_ibfk_1` FOREIGN KEY (`id_studenta`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `przedmiot_student_ibfk_2` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmiot` (`id`);

--
-- Ograniczenia dla tabeli `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `uzytkownicy` (`login`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
