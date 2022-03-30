-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 30, 2022 alle 10:50
-- Versione del server: 10.4.22-MariaDB
-- Versione PHP: 8.0.13

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
-- Struttura stand-in per le viste `amministratore`
-- (Vedi sotto per la vista effettiva)
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
-- Struttura della tabella `candidati_partiti`
--

CREATE TABLE `candidati_partiti` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `partito` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `candidati_rappresentanti`
--

CREATE TABLE `candidati_rappresentanti` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `rappresentante` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `candidati_rappresentanti`
--

INSERT INTO `candidati_rappresentanti` (`votazione`, `rappresentante`) VALUES
(2, 2),
(2, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `elettore`
-- (Vedi sotto per la vista effettiva)
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
-- Struttura della tabella `ha_votato`
--

CREATE TABLE `ha_votato` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `persona` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ha_votato`
--

INSERT INTO `ha_votato` (`votazione`, `persona`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `partiti`
--

CREATE TABLE `partiti` (
  `nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `partiti`
--

INSERT INTO `partiti` (`nome`) VALUES
('Democratic Party'),
('Lega Nord'),
('Movimento Cinque Stelle'),
('Partito Democratico');

-- --------------------------------------------------------

--
-- Struttura della tabella `preferenze_voto_categorico`
--

CREATE TABLE `preferenze_voto_categorico` (
  `voto_categorico` int(11) NOT NULL,
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `rappresentante` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazioni`
--

CREATE TABLE `prenotazioni` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `elettore` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `prenotazioni`
--

INSERT INTO `prenotazioni` (`votazione`, `elettore`) VALUES
(2, 'umberto');

-- --------------------------------------------------------

--
-- Struttura della tabella `rappresentanti`
--

CREATE TABLE `rappresentanti` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `partito` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rappresentanti`
--

INSERT INTO `rappresentanti` (`id`, `nome`, `cognome`, `partito`) VALUES
(1, 'Barack', 'Obama', 'Democratic Party'),
(2, 'Enrico', 'Letta', 'Partito Democratico'),
(3, 'Matteo', 'Salvini', 'Lega Nord'),
(4, 'Giuseppe', 'Conte', 'Movimento Cinque Stelle');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
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
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `name`, `surname`, `username`, `password`, `admin`) VALUES
(3, 'Admin', 'Admin', 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 1),
(2, 'Mattia', 'Garavaglia', 'mattia', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', 0),
(1, 'Umberto', 'Pirovano', 'umberto', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `votazioni`
--

CREATE TABLE `votazioni` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nome` varchar(100) NOT NULL,
  `data_inizio` datetime NOT NULL,
  `data_fine` datetime NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  `maggioranzaAssoluta` tinyint(1) DEFAULT NULL,
  `votoAPartiti` tinyint(1) DEFAULT NULL,
  `quorum` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `votazioni`
--

INSERT INTO `votazioni` (`id`, `nome`, `data_inizio`, `data_fine`, `tipo`, `descrizione`, `maggioranzaAssoluta`, `votoAPartiti`, `quorum`) VALUES
(1, 'referendum trivelle', '2022-03-01 08:00:00', '2022-03-03 23:00:00', 'referendum', 'referendum per l\'approvazione degli interventi di estrazione del gas nel mar Adriatico', NULL, NULL, NULL),
(2, 'Elezioni politiche', '2022-05-13 08:00:00', '2022-05-15 23:00:00', 'votazione ordinale', 'Votazione per l\'elezione dei rappresentanti del parlamento della Repubblica Italiana.', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `voti_categorici_partiti`
--

CREATE TABLE `voti_categorici_partiti` (
  `id` int(11) NOT NULL,
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `partito` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `voti_categorici_rappresentanti`
--

CREATE TABLE `voti_categorici_rappresentanti` (
  `id` int(11) NOT NULL,
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `rappresentanti` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `voti_ordinali_partiti`
--

CREATE TABLE `voti_ordinali_partiti` (
  `partito` varchar(50) DEFAULT NULL,
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `posizione` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `voti_ordinali_rappresentanti`
--

CREATE TABLE `voti_ordinali_rappresentanti` (
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `rappresentante` bigint(20) UNSIGNED DEFAULT NULL,
  `posizione` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `voti_referendum`
--

CREATE TABLE `voti_referendum` (
  `id` int(10) UNSIGNED NOT NULL,
  `votazione` bigint(20) UNSIGNED NOT NULL,
  `scelta` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura per vista `amministratore`
--
DROP TABLE IF EXISTS `amministratore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `amministratore`  AS SELECT `u`.`id` AS `id`, `u`.`name` AS `name`, `u`.`surname` AS `surname`, `u`.`username` AS `username`, `u`.`password` AS `password` FROM `utenti` AS `u` WHERE `u`.`admin` = 1 ;

-- --------------------------------------------------------

--
-- Struttura per vista `elettore`
--
DROP TABLE IF EXISTS `elettore`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `elettore`  AS SELECT `u`.`id` AS `id`, `u`.`name` AS `name`, `u`.`surname` AS `surname`, `u`.`username` AS `cF`, `u`.`password` AS `password` FROM `utenti` AS `u` WHERE `u`.`admin` = 0 ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `candidati_partiti`
--
ALTER TABLE `candidati_partiti`
  ADD PRIMARY KEY (`votazione`,`partito`),
  ADD KEY `p` (`partito`);

--
-- Indici per le tabelle `candidati_rappresentanti`
--
ALTER TABLE `candidati_rappresentanti`
  ADD PRIMARY KEY (`votazione`,`rappresentante`),
  ADD KEY `candidate` (`rappresentante`);

--
-- Indici per le tabelle `ha_votato`
--
ALTER TABLE `ha_votato`
  ADD PRIMARY KEY (`votazione`,`persona`),
  ADD KEY `utente` (`persona`);

--
-- Indici per le tabelle `partiti`
--
ALTER TABLE `partiti`
  ADD PRIMARY KEY (`nome`);

--
-- Indici per le tabelle `preferenze_voto_categorico`
--
ALTER TABLE `preferenze_voto_categorico`
  ADD PRIMARY KEY (`voto_categorico`,`rappresentante`),
  ADD KEY `preferenza_rappresentante` (`rappresentante`,`votazione`);

--
-- Indici per le tabelle `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD PRIMARY KEY (`votazione`,`elettore`),
  ADD KEY `elettori` (`elettore`);

--
-- Indici per le tabelle `rappresentanti`
--
ALTER TABLE `rappresentanti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `partito` (`partito`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `votazioni`
--
ALTER TABLE `votazioni`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `voti_categorici_partiti`
--
ALTER TABLE `voti_categorici_partiti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categorico_partito` (`partito`,`votazione`);

--
-- Indici per le tabelle `voti_categorici_rappresentanti`
--
ALTER TABLE `voti_categorici_rappresentanti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categorico_rappresentante` (`rappresentanti`,`votazione`);

--
-- Indici per le tabelle `voti_ordinali_partiti`
--
ALTER TABLE `voti_ordinali_partiti`
  ADD KEY `candidato` (`partito`,`votazione`);

--
-- Indici per le tabelle `voti_ordinali_rappresentanti`
--
ALTER TABLE `voti_ordinali_rappresentanti`
  ADD KEY `candidato_rappresentante` (`rappresentante`,`votazione`);

--
-- Indici per le tabelle `voti_referendum`
--
ALTER TABLE `voti_referendum`
  ADD PRIMARY KEY (`id`),
  ADD KEY `voto_ref_votazione` (`votazione`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `rappresentanti`
--
ALTER TABLE `rappresentanti`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `votazioni`
--
ALTER TABLE `votazioni`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `voti_categorici_partiti`
--
ALTER TABLE `voti_categorici_partiti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `voti_categorici_rappresentanti`
--
ALTER TABLE `voti_categorici_rappresentanti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `voti_referendum`
--
ALTER TABLE `voti_referendum`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `candidati_partiti`
--
ALTER TABLE `candidati_partiti`
  ADD CONSTRAINT `p` FOREIGN KEY (`partito`) REFERENCES `partiti` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `v` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `candidati_rappresentanti`
--
ALTER TABLE `candidati_rappresentanti`
  ADD CONSTRAINT `candidate` FOREIGN KEY (`rappresentante`) REFERENCES `rappresentanti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `poll` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ha_votato`
--
ALTER TABLE `ha_votato`
  ADD CONSTRAINT `utente` FOREIGN KEY (`persona`) REFERENCES `utenti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votazione` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `preferenze_voto_categorico`
--
ALTER TABLE `preferenze_voto_categorico`
  ADD CONSTRAINT `preferenza_categorico` FOREIGN KEY (`voto_categorico`) REFERENCES `voti_categorici_partiti` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `preferenza_rappresentante` FOREIGN KEY (`rappresentante`,`votazione`) REFERENCES `candidati_rappresentanti` (`rappresentante`, `votazione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD CONSTRAINT `elettori` FOREIGN KEY (`elettore`) REFERENCES `utenti` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `votazioni` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `rappresentanti`
--
ALTER TABLE `rappresentanti`
  ADD CONSTRAINT `partito` FOREIGN KEY (`partito`) REFERENCES `partiti` (`nome`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `voti_categorici_partiti`
--
ALTER TABLE `voti_categorici_partiti`
  ADD CONSTRAINT `categorico_partito` FOREIGN KEY (`partito`,`votazione`) REFERENCES `candidati_partiti` (`partito`, `votazione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `voti_categorici_rappresentanti`
--
ALTER TABLE `voti_categorici_rappresentanti`
  ADD CONSTRAINT `categorico_rappresentante` FOREIGN KEY (`rappresentanti`,`votazione`) REFERENCES `candidati_rappresentanti` (`rappresentante`, `votazione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `voti_ordinali_partiti`
--
ALTER TABLE `voti_ordinali_partiti`
  ADD CONSTRAINT `candidato` FOREIGN KEY (`partito`,`votazione`) REFERENCES `candidati_partiti` (`partito`, `votazione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `voti_ordinali_rappresentanti`
--
ALTER TABLE `voti_ordinali_rappresentanti`
  ADD CONSTRAINT `candidato_rappresentante` FOREIGN KEY (`rappresentante`,`votazione`) REFERENCES `candidati_rappresentanti` (`rappresentante`, `votazione`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `voti_referendum`
--
ALTER TABLE `voti_referendum`
  ADD CONSTRAINT `voto_ref_votazione` FOREIGN KEY (`votazione`) REFERENCES `votazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
