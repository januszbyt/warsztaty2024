-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2024 at 09:10 PM
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
(1, 123, '2023-01-01', '2026-01-01', 1),
(2, 456, '2023-02-01', '2025-02-01', 2),
(3, 789, '2022-12-01', '2024-12-01', 3);

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
(1, 'Jan', 'Kowalski', 'jan.kowalski@example.com', 'ul. Kwiatowa 5, 00-001 Warszawa', '+48 123 456 789', 'Polska', 'Rekrutacja', 'Aktywny', 'e:/dane'),
(2, 'Anna', 'Nowak', 'anna.nowak@example.com', 'ul. Miodowa 10, 00-002 Warszawa', '+48 987 654 321', 'Polska', 'Rekrutacja', 'Aktywny', 'e:/dane'),
(3, 'Adam', 'Wisniewski', 'adam.wisniewski@example.com', 'ul. Sloneczna 15, 00-003 Warszawa', '+48 111 222 333', 'Polska', 'Rekrutacja', 'Aktywny', 'e:/dane');

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
  `pracownik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prawa_jazdy`
--

INSERT INTO `prawa_jazdy` (`id_prawa_jazdy`, `kategorie`, `data_wydania`, `data_waznosci`, `kraj_pochodzenia`, `pracownik_id`) VALUES
(1, 'A, B', '2022-01-01', '2027-01-01', 'Polska', 1),
(2, 'C', '2023-03-01', '2028-03-01', 'Polska', 2),
(3, 'B', '2021-12-01', '2026-12-01', 'Polska', 3);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
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
  ADD KEY `pracownik_id` (`pracownik_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
  MODIFY `id_karty` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `pracownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
  ADD CONSTRAINT `karty_kierowcy_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`);

--
-- Constraints for table `prawa_jazdy`
--
ALTER TABLE `prawa_jazdy`
  ADD CONSTRAINT `prawa_jazdy_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2024 at 02:06 PM
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
(1, 'Jan', 'Kowalski', 'jan.kowalski@example.com', 'ul. Kwiatowa 5, 00-001 Warszawa', '+48 123 456 789', 'Polska', 'Rekrutacja', 'Aktywny', 'e:/dane');

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
  `pracownik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `prawa_jazdy`
--

INSERT INTO `prawa_jazdy` (`id_prawa_jazdy`, `kategorie`, `data_wydania`, `data_waznosci`, `kraj_pochodzenia`, `pracownik_id`) VALUES
(1, 'A, B', '2022-01-01', '2027-01-01', 'Polska', 1);

--
-- Indeksy dla zrzutĂłw tabel
--

--
-- Indeksy dla tabeli `karty_kierowcy`
--
ALTER TABLE `karty_kierowcy`
  ADD PRIMARY KEY (`id_karty`),
  ADD KEY `karty_kierowcy_ibfk_1` (`pracownik_id`);

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
-- AUTO_INCREMENT for table `pracownik`
--
ALTER TABLE `pracownik`
  MODIFY `pracownik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

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
-- Constraints for table `prawa_jazdy`
--
ALTER TABLE `prawa_jazdy`
  ADD CONSTRAINT `prawa_jazdy_ibfk_1` FOREIGN KEY (`pracownik_id`) REFERENCES `pracownik` (`pracownik_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

