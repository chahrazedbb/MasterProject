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
-- Base de données :  `challengeb`
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
(1, 'admin', 'chahrazedbouchra@outlook.com', '$2y$12$uNP4wWvT.k2ZnXjaWubAren60E.TLExYcLFFCr91kHs8GK7l1mopm');

-- --------------------------------------------------------

--
-- Structure de la table `idea`
--

CREATE TABLE `idea` (
  `id` int(11) NOT NULL,
  `ideaText` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `member_id` int(11) NOT NULL,
  `time` time NOT NULL,
  `session` int(11) NOT NULL,
  `idea_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `idea`
--

INSERT INTO `idea` (`id`, `ideaText`, `member_id`, `time`, `session`, `idea_date`) VALUES
(1, 'Flight-Capsule\r\nThe building consists of a variable structure. In case of an emergency the structure opens, encloses the person and descents to the ground\r\n', 1, '00:09:52', 1, '0000-00-00 00:00:00'),
(2, 'Signal-Window\r\nWindow with a heat-sensitive material, that lights-up if a person is in the room during a fire\r\n', 1, '00:09:38', 1, '0000-00-00 00:00:00'),
(3, 'Rescue Indication facade\r\nThe facade indicates in which floor there are people that need to be rescued\r\n', 1, '00:09:24', 1, '0000-00-00 00:00:00'),
(4, 'Anastrophe\r\nchangeable building structure that changes its structure in case of an emergency, for example during a terrorist warning, to a party zone\r\n', 1, '00:09:12', 1, '0000-00-00 00:00:00'),
(5, 'Rescuenet\r\nnet attached to the facade of a building, that works as a slide and therefore as an escape route\r\n', 1, '00:09:01', 1, '0000-00-00 00:00:00'),
(6, 'air-cushion plants\r\nlandscape architecture that changes into an air bed in case of an emergency and secures people jumping out of a window\r\n', 1, '00:08:18', 1, '0000-00-00 00:00:00'),
(7, 'reflectorpixelfacade for urban light-control (sunlight-bundling)\r\nreflectors on buildings for efficient light usage\r\nreflectorfacade for targeted brightness, multiple reflectors as screen/pixel\r\n', 1, '00:07:44', 1, '0000-00-00 00:00:00'),
(8, 'soft building\r\nbuilding from soft materials\r\n', 1, '00:07:31', 1, '0000-00-00 00:00:00'),
(9, 'non-flammable building\r\nwhole building including furnishing is made from non-flammable materials\r\n', 1, '00:07:21', 1, '0000-00-00 00:00:00'),
(10, 'autonomous rescue system\r\nrescue-robots, that are in the different floors of the building and are activated in an emergency. Have their own power supply\r\n', 1, '00:07:11', 1, '0000-00-00 00:00:00'),
(11, 'magnetic boots\r\nmagnet panels are in the shoes and in the floor. The shoes (and therefore the persons) are guided along the rescure-route automatically\r\n', 1, '00:07:00', 1, '0000-00-00 00:00:00'),
(12, 'emergency radio\r\nin case of emergency information of hotel guest over loudspeakers. Instructions about necessary behavior. Calming of the guests\r\n', 1, '00:09:47', 2, '0000-00-00 00:00:00'),
(13, 'Rescue-Cloud\r\nPeople jump out of the window on rescue clouds. Clouds then hover to the ground\r\n', 1, '00:09:33', 2, '0000-00-00 00:00:00'),
(14, 'emergency navigation\r\nprotective system that identifies the seat of fire and detects smoke development. Leads people through the hotel. Save navigation to the exit. Navigation of the fire department to the seat of fire\r\n', 1, '00:09:23', 2, '0000-00-00 00:00:00'),
(15, 'rescue tunnel\r\nin case of a fire, a tunnel system is unfolded, that can be used as an escape route and supplies people with oxygen.\r\n', 1, '00:09:06', 2, '0000-00-00 00:00:00'),
(16, 'airthight room\r\nThe room is sealed airtight, so that in case of a fire no smoke can get in. On the outside there is a signal, indicating that there are people in the room. A ventilation system supplies oxygen\r\n', 1, '00:08:48', 2, '0000-00-00 00:00:00'),
(17, 'rescue parachutes\r\nparachutes in the upper levels of high-rise buildings as an emergency escape route\r\n', 1, '00:07:17', 2, '0000-00-00 00:00:00'),
(18, 'extinguishing capsule\r\nExtinguishing of a fire by deoxygenation. Capsule puts itself over the fire\r\n', 1, '00:07:06', 2, '0000-00-00 00:00:00'),
(19, 'emergency TV\r\ninformations for hotel guests via a TV/Computer in the room. Individual using the language of the guests\r\n', 1, '00:06:56', 2, '0000-00-00 00:00:00'),
(20, 'luminous info-drones\r\nflying robots/drones, that generate light and send information to the rescue teams\r\n', 1, '00:06:45', 2, '0000-00-00 00:00:00'),
(21, 'Transport-Drones\r\nFlying Drones, that place rescue robots on higher levels of high-rise buildings\r\n', 1, '00:06:35', 2, '0000-00-00 00:00:00'),
(22, 'Hight-reducing Exit\r\nSafety-Zones on different levels of the building. Exit/Communication to the outside\r\n', 1, '00:06:22', 2, '0000-00-00 00:00:00'),
(23, 'retractable building\r\nIn case of an emergency the building retracts into the ground, creating an exit in every level. in case of a terrorist warning, the building completely retracts into the ground\r\n', 1, '00:05:54', 2, '0000-00-00 00:00:00'),
(24, 'rescue cloud\r\nrescue from great heights by changing the information and formation of specific particles in the atmosphere. formation of a rescue cloud\r\n', 1, '00:05:43', 2, '0000-00-00 00:00:00'),
(25, 'flight-capsule\r\nmobile rescue-drone that docks to buildings and serves as an escape route. Pipe/Airlift as a safe passage between building an helicopter. alternative: solid exterior space, that normally functions as a balcony and in case of an emergency is refunctionalized into a panic-room and is grounded using ropes or helicopters.\r\n', 1, '00:05:32', 2, '0000-00-00 00:00:00'),
(26, 'EvacuApp\r\nintelligent rescue system, that analyses dangerous situations and forwards informations to smartphones via an App. Individualized emergency escape routes by exact determination in the object and knowledge about the danger source\r\n', 1, '00:05:21', 2, '0000-00-00 00:00:00'),
(27, 'Emergency-Navigation\r\nintelligent system, that identifies accidents in buildings and assumes emergency navigation. Transferable to traffic systems, major events etc\r\n', 1, '00:05:10', 2, '0000-00-00 00:00:00'),
(28, 'Smart Fire Extinguisher, Extinguish-detector\r\nsmart fire extinguisher with integrated fire detector. Shows escape routes and absorbes smoke\r\n', 1, '00:04:59', 2, '0000-00-00 00:00:00'),
(29, 'KatNavi\r\nPosition information in case of a catastrophe, display of evacuation routes, display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image\r\n', 1, '00:04:49', 2, '0000-00-00 00:00:00'),
(30, 'Selfdestructive danger-sensing material\r\nEmergency Hammer with integrated emergency message, gps-localization, display with emergency informations like first-aid-instructions. self-destructing material self-destructs, making the hammer redundant', 1, '00:04:12', 2, '0000-00-00 00:00:00');

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
(1, 'user1', 'user1@email.com', '$2y$12$Hyp1MgAKFUw0BCTcFcJx4u2ZjxLdi/UoCmF7h5cHST74xJadtDgcm');

-- --------------------------------------------------------

--
-- Structure de la table `removed_idea`
--

CREATE TABLE `removed_idea` (
  `removed_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `ideaText` text NOT NULL,
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT pour la table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `removed_idea`
--
ALTER TABLE `removed_idea`
  MODIFY `removed_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
