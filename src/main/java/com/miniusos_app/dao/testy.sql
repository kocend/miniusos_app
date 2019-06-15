-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 15 Cze 2019, 14:42
-- Wersja serwera: 10.1.38-MariaDB
-- Wersja PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `testy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `authorities`
--

CREATE TABLE `authorities` (
  `username` int(9) NOT NULL,
  `authority` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Struktura tabeli dla tabeli `grupy`
--

CREATE TABLE `grupy` (
  `id_gr` int(11) NOT NULL,
  `nazwa_grupy` varchar(30) NOT NULL,
  `dzien_tygodnia` varchar(20) NOT NULL,
  `godz_rozpoczecia` varchar(10) NOT NULL,
  `godz_zakonczenia` varchar(10) NOT NULL,
  `limit_miejsc` int(11) NOT NULL,
  `id_k` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struktura tabeli dla tabeli `koordynatorzy`
--

CREATE TABLE `koordynatorzy` (
  `id_k` int(6) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struktura tabeli dla tabeli `pracownicy_dziekanatu`
--

CREATE TABLE `pracownicy_dziekanatu` (
  `id_dz` int(11) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struktura tabeli dla tabeli `przynaleznosc`
--

CREATE TABLE `przynaleznosc` (
  `nrUSOS` int(6) NOT NULL,
  `id_gr` int(11) NOT NULL,
  `ocena_koncowa` double DEFAULT NULL,
  `punkty_kolokwium1` int(11) DEFAULT NULL,
  `punkty_kolokwium2` int(11) DEFAULT NULL,
  `suma_punktow` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struktura tabeli dla tabeli `studenci`
--

CREATE TABLE `studenci` (
  `nrUSOS` int(6) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `username` int(9) NOT NULL,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `auth_uniq_idx` (`username`,`authority`);

--
-- Indeksy dla tabeli `grupy`
--
ALTER TABLE `grupy`
  ADD PRIMARY KEY (`id_gr`);

--
-- Indeksy dla tabeli `koordynatorzy`
--
ALTER TABLE `koordynatorzy`
  ADD PRIMARY KEY (`id_k`);

--
-- Indeksy dla tabeli `pracownicy_dziekanatu`
--
ALTER TABLE `pracownicy_dziekanatu`
  ADD PRIMARY KEY (`id_dz`);

--
-- Indeksy dla tabeli `studenci`
--
ALTER TABLE `studenci`
  ADD PRIMARY KEY (`nrUSOS`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `grupy`
--
ALTER TABLE `grupy`
  MODIFY `id_gr` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `username` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10027;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `auth_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
