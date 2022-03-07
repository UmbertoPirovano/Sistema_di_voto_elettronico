-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2022 at 02:10 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistema_di_voto`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `amministratore`
-- (See below for the actual view)
--
CREATE TABLE `amministratore` (
`id` bigint(20) unsigned
,`name` varchar(20)
,`surname` varchar(20)
,`username` varchar(20)
,`password` varchar(512)
);

-- --------------------------------------------------------

--
-- Table structure for table `candidati_partiti`
--

CREATE TABLE `candidati_partiti` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `partito` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `candidati_rappresentanti`
--

CREATE TABLE `candidati_rappresentanti` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `rappresentante` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidati_rappresentanti`
--

INSERT INTO `candidati_rappresentanti` (`votazione`, `rappresentante`) VALUES
(2, 2),
(2, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Stand-in structure for view `elettore`
-- (See below for the actual view)
--
CREATE TABLE `elettore` (
`id` bigint(20) unsigned
,`name` varchar(20)
,`surname` varchar(20)
,`cF` varchar(20)
,`password` varchar(512)
);

-- --------------------------------------------------------

--
-- Table structure for table `ha_votato`
--

CREATE TABLE `ha_votato` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `persona` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ha_votato`
--

INSERT INTO `ha_votato` (`votazione`, `persona`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `partiti`
--

CREATE TABLE `partiti` (
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `partiti`
--

INSERT INTO `partiti` (`nome`) VALUES
('Democratic Party'),
('Lega Nord'),
('Movimento Cinque Stelle'),
('Partito Democratico');

-- --------------------------------------------------------

--
-- Table structure for table `prenotazioni`
--

CREATE TABLE `prenotazioni` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `elettore` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prenotazioni`
--

INSERT INTO `prenotazioni` (`votazione`, `elettore`) VALUES
(2, 'umberto');

-- --------------------------------------------------------

--
-- Table structure for table `rappresentanti`
--

CREATE TABLE `rappresentanti` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `partito` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rappresentanti`
--

INSERT INTO `rappresentanti` (`id`, `nome`, `cognome`, `partito`) VALUES
(1, 'Barack', 'Obama', 'Democratic Party'),
(2, 'Enrico', 'Letta', 'Partito Democratico'),
(3, 'Matteo', 'Salvini', 'Lega Nord'),
(4, 'Giuseppe', 'Conte', 'Movimento Cinque Stelle');

-- --------------------------------------------------------

--
-- Table structure for table `utenti`
--

CREATE TABLE `utenti` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(512) NOT NULL,
  `admin` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utenti`
--

INSERT INTO `utenti` (`id`, `name`, `surname`, `username`, `password`, `admin`) VALUES
(3, 'Admin', 'Admin', 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 1),
(2, 'Mattia', 'Garavaglia', 'mattia', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', 0),
(1, 'Umberto', 'Pirovano', 'umberto', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', 0);

-- --------------------------------------------------------

--
-- Table structure for table `votazioni`
--

CREATE TABLE `votazioni` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `data_inizio` datetime NOT NULL,
  `data_fine` datetime NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `descrizione` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `votazioni`
--

INSERT INTO `votazioni` (`id`, `nome`, `data_inizio`, `data_fine`, `tipo`, `descrizione`) VALUES
(1, 'referendum trivelle', '2022-03-01 08:00:00', '2022-03-03 23:00:00', 'referendum', 'referendum per l\'approvazione degli interventi di estrazione del gas nel mar Adriatico'),
(2, 'Elezioni politiche', '2022-05-13 08:00:00', '2022-05-15 23:00:00', 'votazione ordinale', 'Votazione per l\'elezione dei rappresentanti del parlamento della Repubblica Italiana.');

-- --------------------------------------------------------

--
-- Structure for view `amministratore`
--
DROP TABLE IF EXISTS `amministratore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `amministratore`  AS SELECT `u`.`id` AS `id`, `u`.`name` AS `name`, `u`.`surname` AS `surname`, `u`.`username` AS `username`, `u`.`password` AS `password` FROM `utenti` AS `u` WHERE `u`.`admin` = 1 ;

-- --------------------------------------------------------

--
-- Structure for view `elettore`
--
DROP TABLE IF EXISTS `elettore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `elettore`  AS SELECT `u`.`id` AS `id`, `u`.`name` AS `name`, `u`.`surname` AS `surname`, `u`.`username` AS `cF`, `u`.`password` AS `password` FROM `utenti` AS `u` WHERE `u`.`admin` = 0 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidati_partiti`
--
ALTER TABLE `candidati_partiti`
  ADD PRIMARY KEY (`votazione`,`partito`),
  ADD KEY `p` (`partito`);

--
-- Indexes for table `candidati_rappresentanti`
--
ALTER TABLE `candidati_rappresentanti`
  ADD PRIMARY KEY (`votazione`,`rappresentante`),
  ADD KEY `candidate` (`rappresentante`);

--
-- Indexes for table `ha_votato`
--
ALTER TABLE `ha_votato`
  ADD PRIMARY KEY (`votazione`,`persona`),
  ADD KEY `utente` (`persona`);

--
-- Indexes for table `partiti`
--
ALTER TABLE `partiti`
  ADD PRIMARY KEY (`nome`);

--
-- Indexes for table `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD PRIMARY KEY (`votazione`,`elettore`),
  ADD KEY `elettori` (`elettore`);

--
-- Indexes for table `rappresentanti`
--
ALTER TABLE `rappresentanti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `partito` (`partito`);

--
-- Indexes for table `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `votazioni`
--
ALTER TABLE `votazioni`
  ADD PRIMARY KEY (`id`,`nome`,`data_inizio`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rappresentanti`
--
ALTER TABLE `rappresentanti`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `votazioni`
--
ALTER TABLE `votazioni`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `candidati_partiti`
--
ALTER TABLE `candidati_partiti`
  ADD CONSTRAINT `p` FOREIGN KEY (`partito`) REFERENCES `partiti` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `v` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `candidati_rappresentanti`
--
ALTER TABLE `candidati_rappresentanti`
  ADD CONSTRAINT `candidate` FOREIGN KEY (`rappresentante`) REFERENCES `rappresentanti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `poll` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ha_votato`
--
ALTER TABLE `ha_votato`
  ADD CONSTRAINT `utente` FOREIGN KEY (`persona`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votazione` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD CONSTRAINT `elettori` FOREIGN KEY (`elettore`) REFERENCES `utenti` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votazioni` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rappresentanti`
--
ALTER TABLE `rappresentanti`
  ADD CONSTRAINT `partito` FOREIGN KEY (`partito`) REFERENCES `partiti` (`nome`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
