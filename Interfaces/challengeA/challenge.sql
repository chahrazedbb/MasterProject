-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 11 mars 2019 à 23:52
-- Version du serveur :  10.1.32-MariaDB
-- Version de PHP :  7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `challenge`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id`, `username`, `email`, `password`) VALUES
(1, 'admin', 'chahrazedbouchra@outlook.com', '$2y$12$7roGTy7R9QqD4K7kBFR9L.zXhB8zzht8h0tLklOC4oesFd.nuxqsO');

-- --------------------------------------------------------

--
-- Structure de la table `idea`
--

CREATE TABLE `idea` (
  `id` int(11) NOT NULL,
  `what_about` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `how_it_works` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `when_it_works` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(11) NOT NULL,
  `time` time NOT NULL,
  `session` int(11) NOT NULL,
  `idea_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `idea`
--

INSERT INTO `idea` (`id`, `what_about`, `how_it_works`, `when_it_works`, `member_id`, `time`, `session`, `idea_date`) VALUES
(15, 'The building consists of a variable structure', 'the structure opens, encloses the person and descents to the ground', 'In case of an emergency ', 6, '00:08:57', 1, '2018-11-30 21:28:09'),
(16, 'Window with a heat-sensitive material', 'that lights-up  ', 'if a person is in the room during a fire', 6, '00:08:20', 1, '2018-11-30 21:28:46'),
(17, 'Rescue Indication facade', 'The facade indicates in which floor', 'people that need to be rescued', 6, '00:07:42', 1, '2018-11-30 21:29:24'),
(18, 'changeable building structure', 'that changes its structure to a party zone', 'in case of an emergency, for example during a terrorist warning ', 6, '00:06:50', 1, '2018-11-30 21:30:16'),
(19, 'net attached to the facade of a building', 'that works as a slide and therefore as an escape route', 'in case of an emergency	', 6, '00:06:02', 1, '2018-11-30 21:31:04'),
(20, 'landscape architecture that changes into an air bed', 'secures people jumping out of a window	', 'in an emergency			', 6, '00:04:57', 1, '2018-11-30 21:32:09'),
(21, 'rescue-robots, Have their own power supply', 'that are in the different floors of the building and are activated', 'in case of emergency', 6, '00:04:25', 1, '2018-11-30 21:32:41'),
(22, 'magnet panels are in the shoes and in the floor ', 'The shoes (and therefore the persons) are guided along the rescure-route automatically ', 'in case of emergency', 6, '00:03:56', 1, '2018-11-30 21:33:10'),
(23, 'emergency radio ', 'Instructions about necessary behavior. Calming of the guests', 'in case of emergency', 6, '00:03:21', 1, '2018-11-30 21:33:45'),
(24, 'rescue clouds', 'Clouds then hover to the ground	', 'People jump out of the window', 6, '00:02:47', 1, '2018-11-30 21:34:19'),
(25, 'Navigation of the fire department to the seat of fire', ' protective system that identifies the seat of fire and detects smoke development', 'the seat of fire and detects smoke', 6, '00:01:22', 1, '2018-11-30 21:35:44'),
(26, 'tunnel system is unfolded', 'Leads people through the hotel. Save navigation to the exit', ' in case of a fire', 6, '00:09:10', 2, '2018-11-30 21:37:57'),
(27, 'The room is sealed airtight ', ' that can be used as an escape route and supplies people with oxygen no smoke can get in . A ventilation system supplies oxygen', 'so that in case of a fire', 6, '00:08:37', 2, '2018-11-30 21:38:30'),
(28, 'rescue parachutes ', ' parachutes in the upper levels of high-rise buildings for escape route', 'in case of a fire ', 6, '00:07:40', 2, '2018-11-30 21:39:27'),
(29, ' Capsule puts itself over the fire', ' Extinguishing of a fire by deoxygenation  ', 'in case of a fire ', 6, '00:06:32', 2, '2018-11-30 21:40:35'),
(30, 'emergency TV	', ' informations for hotel guests via a TV/Computer in the room, Individual using the language of the guests', ' In case of an emergency', 6, '00:05:54', 2, '2018-11-30 21:41:13'),
(31, 'flying robots/drones', ' that generate light and send information to the rescue teams', ' In case of an emergency', 6, '00:09:18', 3, '2018-11-30 21:42:08'),
(32, 'Flying Drones	', ' that place rescue robots on higher levels of high-rise buildings', ' In case of an emergency	', 6, '00:08:36', 3, '2018-11-30 21:42:50'),
(33, ' Safety-Zones on different levels of the building', ' Exit/Communication to the outside ', ' In case of an emergency	', 6, '00:08:11', 3, '2018-11-30 21:43:15'),
(34, 'retractable building', 'the building completely retracts into the ground ', 'in case of a terrorist warning	', 6, '00:07:40', 3, '2018-11-30 21:43:46'),
(35, 'formation of a rescue cloud', ' rescue from great heights by changing the information and formation of specific particles in the atmosphere ', 'in case of an emergency ', 6, '00:09:09', 4, '2018-11-30 21:45:11'),
(36, 'Pipe/Airlift', ' safe passage between building an helicopter', 'In case of an emergency', 6, '00:08:18', 4, '2018-11-30 21:46:02'),
(37, 'mobile rescue-drone', 'that docks to buildings and serves as an escape route', 'In case of an emergency', 6, '00:07:53', 4, '2018-11-30 21:46:27'),
(38, 'solid exterior space', 'that normally functions as a balcony and is refunctionalized into a panic-room and is grounded using ropes or helicopters', 'In case of an emergency', 6, '00:07:22', 4, '2018-11-30 21:46:58'),
(39, 'Evacuation Application ', 'that analyses dangerous situations and forwards informations to smartphones via an App. Individualized emergency escape routes by exact determination in the object and knowledge about the danger source', 'In case of an emergency', 6, '00:06:50', 4, '2018-11-30 21:47:30'),
(40, 'intelligent rescue system', ' that identifies accidents in buildings and assumes emergency navigation. Transferable to traffic systems, major events etc ', 'people that need to be rescued', 6, '00:06:07', 4, '2018-11-30 21:48:13'),
(41, 'smart fire extinguisher with integrated fire detector', 'Shows escape routes and absorbes smoke', 'in case of a fire ', 6, '00:05:35', 4, '2018-11-30 21:48:45'),
(42, ' Position information', ' display of evacuation routes, display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image', ' in case of a catastrophe', 6, '00:04:38', 4, '2018-11-30 21:49:42'),
(43, ' self-destructing material self-destructs, making the hammer redundant ', ' Emergency Hammer with integrated emergency message, gps-localization, display with emergency informations like first-aid-instructions', 'In case of an emergency', 6, '00:09:35', 5, '2018-11-30 21:50:17');

-- --------------------------------------------------------

--
-- Structure de la table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `member`
--

INSERT INTO `member` (`id`, `username`, `email`, `password`) VALUES
(6, 'user1', 'user1@email.com', '$2y$12$z/mWxmF.JHm0oNoCpuMcYe3ND67E.WscgOlm0yCQBR2i5saAc8ylu');

-- --------------------------------------------------------

--
-- Structure de la table `removed_idea`
--

CREATE TABLE `removed_idea` (
  `removed_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `what_about` text NOT NULL,
  `how_it_works` text NOT NULL,
  `when_it_works` text NOT NULL,
  `member_id` int(11) NOT NULL,
  `time` time NOT NULL,
  `session` int(11) NOT NULL,
  `idea_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `idea`
--
ALTER TABLE `idea`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `member_id` (`member_id`);

--
-- Index pour la table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `removed_idea`
--
ALTER TABLE `removed_idea`
  ADD PRIMARY KEY (`removed_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `idea`
--
ALTER TABLE `idea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `removed_idea`
--
ALTER TABLE `removed_idea`
  MODIFY `removed_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `idea`
--
ALTER TABLE `idea`
  ADD CONSTRAINT `idea_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
