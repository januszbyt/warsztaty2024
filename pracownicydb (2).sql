-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Maj 11, 2024 at 05:03 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pracownicydb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `karty_kierowcy`
--

CREATE TABLE `karty_kierowcy` (
  `id_karty` int(11) NOT NULL,
  `numer` int(11) NOT NULL,
  `data_wydania` date NOT NULL,
  `data_waznosci` date NOT NULL,
  `pracownik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `karty_kierowcy`
--

INSERT INTO `karty_kierowcy` (`id_karty`, `numer`, `data_wydania`, `data_waznosci`, `pracownik_id`) VALUES
(1, 123, '2023-01-01', '2026-01-01', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `paszport`
--

CREATE TABLE `paszport` (
  `id_karty` int(11) NOT NULL,
  `numer` int(20) DEFAULT NULL,
  `data_wydania` date DEFAULT NULL,
  `data_waznosci` date DEFAULT NULL,
  `pracownik_id` int(11) DEFAULT NULL,
  `kraj_pochodzenia` varchar(55) DEFAULT NULL,
  `oddzial_wydajacy` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `paszport`
--

INSERT INTO `paszport` (`id_karty`, `numer`, `data_wydania`, `data_waznosci`, `pracownik_id`, `kraj_pochodzenia`, `oddzial_wydajacy`) VALUES
(1, 214, '2024-05-01', '2027-05-01', 1, 'Polska', 'Król');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownik`
--

CREATE TABLE `pracownik` (
  `pracownik_id` int(11) NOT NULL,
  `imie` varchar(45) NOT NULL,
  `nazwisko` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `adres` varchar(100) NOT NULL,
  `telefon` varchar(45) NOT NULL,
  `narodowosc` varchar(45) NOT NULL,
  `zrodlo` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `link` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `pracownik`
--

INSERT INTO `pracownik` (`pracownik_id`, `imie`, `nazwisko`, `email`, `adres`, `telefon`, `narodowosc`, `zrodlo`, `status`, `link`) VALUES
(1, 'Jan', 'Kowalski', 'jan.kowalski@example.com', 'ul. Kwiatowa 5, 00-001 Warszawa', '+48 123 456 789', 'Polska', 'Rekrutacja', 'Aktywny', 'C:\\\\skany\\\\1'),
(31, 'Bogdan', 'Lis', 'lis.bogdan@wp.pl', 'ul.LIsa Jarosław', '222222222', 'Polska', 'Aplikacja', 'Aktywny', 'e:/dane'),
(37, 'Kowal', 'Marek', 'mk@wp.pl', 'Rzeszów', '111111111', 'Ukraina', 'Polecenie', 'W trakcie', 'C:\\skany\\MK');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `prawa_jazdy`
--

CREATE TABLE `prawa_jazdy` (
  `id_prawa_jazdy` int(11) NOT NULL,
  `kategorie` varchar(100) NOT NULL,
  `data_wydania` date NOT NULL,
  `data_waznosci` date NOT NULL,
  `kraj_pochodzenia` varchar(100) NOT NULL,
  `pracownik_id` int(11) NOT NULL,
  `Numer_pj` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prawa_jazdy`
--

INSERT INTO `prawa_jazdy` (`id_prawa_jazdy`, `kategorie`, `data_wydania`, `data_waznosci`, `kraj_pochodzenia`, `pracownik_id`, `Numer_pj`) VALUES
(1, 'A, B', '2022-01-01', '2027-01-01', 'Polska', 1, '412');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('user1', 'password1'),
('user2', 'password2'),
('user3', 'password3'),
('1', '1'),
('1', '1');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
  ADD PRIMARY KEY (`id_karty`),
  ADD KEY `karty_kierowcy_ibfk_1` (`pracownik_id`);

--
-- Indeksy dla tabeli `paszport`
--
ALTER TABLE `paszport`
  ADD PRIMARY KEY (`id_karty`),
  ADD KEY `pracownik_id` (`pracownik_id`);

--
-- Indeksy dla tabeli `pracownik`
--
ALTER TABLE `pracownik`
  ADD PRIMARY KEY (`pracownik_id`);

--
-- Indeksy dla tabeli `prawa_jazdy`
--
ALTER TABLE `prawa_jazdy`
  ADD PRIMARY KEY (`id_prawa_jazdy`),
  ADD KEY `prawa_jazdy_ibfk_1` (`pracownik_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
  MODIFY `id_karty` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `paszport`
--
ALTER TABLE `paszport`
  MODIFY `id_karty` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `pracownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `prawa_jazdy`
--
ALTER TABLE `prawa_jazdy`
  MODIFY `id_prawa_jazdy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
  ADD CONSTRAINT `karty_kierowcy_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`) ON DELETE CASCADE;

--
-- Constraints for table `paszport`
--
ALTER TABLE `paszport`
  ADD CONSTRAINT `paszport_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`);

--
-- Constraints for table `prawa_jazdy`
--
ALTER TABLE `prawa_jazdy`
  ADD CONSTRAINT `prawa_jazdy_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
