-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 30 mai 2019 à 22:04
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
-- Base de données :  `challengec`
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
(3, 'admin', 'chahrazedbouchra@outlook.com', '$2y$12$/uI8lQHglam9d5vEj35s6OmdOHKTkWLVntH/ICAWJbMdqu47HYnL.');

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
(1, 'clothes that can be turned into airbags', 'they cover the person body', 'to protect it during earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(2, 'a table that changes its shape into a solid box', 'the box is made of steel.', 'poeple go into the box when there is an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(3, 'protection helmet', 'that is transformed into airbags. used to protect the head from serious injuries', 'used when there is an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(4, 'pillows with sensory chips', 'that can shack up anyone who is asleep', 'when there is a leak in gaz', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(5, 'smart shoes', 'those shoes can track and guide the person through the escape path to evacuate the building using a laser', 'when there is a danger', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(6, 'smart watch', 'that predicts danger situations, launches an alarm, and tracks the escape path to evacuate the building safely.', 'if a dangerous situation is about to happen.', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(7, 'emergency TV or radio', 'that gives instructions for evacuation, guides people through exit doors and calms them down', 'emergencies', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(8, 'oxygen masks placed in the ceiling of each room', 'they are dropped down automatically', 'when there is a smoke or a gas leakage', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(9, 'stairs that change its structure', 'it turns into unbreakable solid tunnel to help people slide into it safely', 'if there is an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(10, 'retractable stairs', 'placed under windows and balconies, they are used to escape from the building', 'if there is an emergency situation like a fire or an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(11, 'rescue-robots, with their own power supply\r\n', 'that are in the different floors of the building and are activated, they manage the evacuation plan and guide people through the exit path', 'emergencies', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(12, 'rescue pipelines', 'hanging on building facade, they serve as as an escape route', 'in case of emergency situations', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(13, 'The building has a variable structure', 'the variable structure helps it completely descents to the ground, it is supplied with Ventilation system and heat control system', 'emergency situations', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(14, 'The building has a variable structure', 'the building completely descents to the ground', 'emergency situations', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(15, 'flying drones holding capsules', 'put those capsules over a fire to extinguish it by deoxygenation', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(16, 'waterproof carpet', 'that absorbs water', 'in case of a flood', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(17, 'fire extinguisher with sensory chips', 'placed on the roof, it detects fire and spray automatically carbon dioxide to stop it', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(18, 'fireproof furniture', 'furniture made of a substance resistant to fire', 'all the time', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(19, 'Flying Drones', 'holding water bags, that track the fire location and poor water to stop it', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(20, 'Rescue Indication floor', 'that lights up to show the exit path', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(21, 'waterproof floor', 'that absorbs water', 'in case of a flood', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(22, 'Sensory walls', 'that raises an alarm for evacuation, stimulates downpour of water and absorbs smoke', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(23, 'rescue elivator', 'that normally functions as a balcony and is refunctionalized into an elevator that takes people to the ground safely', 'in case of emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(24, 'mini rescue drones', 'that light up and guide people through exit path', 'in case of emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(25, 'rescue parachutes', 'attached to the facade of the building that helps poeple escape', 'in case of emergency like a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(26, 'evacuation system', 'that identifies emergency cases in building for example a fire, trigger an alert, shows how to evacuate the building, indicates the exit paths and the precautions to be taken', 'in case of emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(27, 'air bag suits', 'that saves poeple', 'in case of an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(28, 'carbon monoxide sensitive cells', 'supplying each light switch with carbon monoxide sensitive cells, that cuts electricity if there is a gas leak', 'in case of gas leak', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(29, 'carbon monoxide detector', 'in each appartement, that detects and absorbs carbon monoxide', 'gas leak', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(30, 'emergency application', 'via a computer in each appartement, that contains information about the escape roots, first aid, police and ambulance phone number etc. in different languages', 'in case of emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(31, 'fireproof suits', 'that are supplied with oxygen bags', 'people wear them in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(32, 'fireproof walls', 'walls of the building are painted with fireproof substance', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(33, 'rescue closets', 'that normally functions as a closet, it turns into an escape elevator that supplies people with oxygen', 'in case of fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(34, 'rescue closets in each apartment', 'that can be used as an escape elevator, it supplies people with oxygen', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(35, 'heat wave mitigation', 'for many people heat is dangerous. therefore a buildng should intelligently regulate the inside temperature depending on time of day, number and location of people in the building,..', 'constantly', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(36, 'threat monitoring', '	constant feed of information regarding e.g., air quality warnings, heat waves, dangerous traffic incidents or crimes in the vicinity. delivered to people, e.g., by infodisplays, smartphones or other means', 'always', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(37, 'Water Triggers', 'Avoiding the fire propagation', 'In an emergency case', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(38, 'intelligent system', 'early detection of threats and rapid evacuation', 'all the time', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(39, 'thermal insulation doors', 'close empty areas to contain fire', 'in case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(40, 'system of detecting scale in the elevator', 'that blocks it from working if the weight is higher than the weight threshold', 'all the time', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(41, 'robots with weapons, like robocap', 'neutralizes terrorist with weapons', 'in case of a terrorist attack', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(42, 'steel doors', 'solid doors that can be closed automatically and rapidly', 'in case of a terrorist attack', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(43, 'Absorption device', 'A sensitive gas leakage system that absorbs the leaking gas and filters the air', 'in case of gas leakage', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(44, 'an intelligent system equipped with cameras and sensors placed all over the building', 'detect and evaluate emergencies, then propose solutions (call the police/firemen, trigger an alarm...)', 'all the time', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(45, 'big capsules with airbags and parachutes', 'people enter into the capsules and jump out of the building', 'in case of an emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(46, 'Fireproof clothing hangs with fire extinguishers', 'Wear fireproof clothing', 'In case of a fire', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(47, 'Underground solid basements and toboggans', 'Evacuate the building rapidly using toboggans to the basements', 'In case of an earthquake', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(48, 'Evacuation alert', 'Developing an effective security system to which phones and laptops are connected. It shows how to evacuate the building and the precautions to be taken', 'Once a fire, no matter how big or small, starts', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(49, 'Sensory chips/walls', 'raises a loud alarm for evacuation or stimulates immediate down pour of water for the fire', 'In case of an emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(50, 'Stairs in the back of a building', 'It helps as an escape route', 'In case of an emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(51, 'self-destructing material self-destructs, making the hammer redundant', 'Emergency Hammer with integrated emergency message, gps-localization, display with emergency informations like first-aid-instructions', '	In case of an emergency', 0, '00:00:00', 0, '0000-00-00 00:00:00'),
(52, 'Position information', 'display of evacuation routes, display of visible and invisible dangers (e.g. radiation). sending and receiving. thermal image', 'in case of a catastrophe', 0, '00:00:00', 0, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL,
  `Vsession` int(11) NOT NULL,
  `Complete` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `relationships`
--

CREATE TABLE `relationships` (
  `id` int(11) NOT NULL,
  `idea1` int(11) NOT NULL,
  `idea2` int(11) NOT NULL,
  `relationship` varchar(50) NOT NULL,
  `Vsession` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `relationships`
--

INSERT INTO `relationships` (`id`, `idea1`, `idea2`, `relationship`, `Vsession`) VALUES
(1, 15, 19, 'disjoint', 1),
(2, 15, 17, 'disjoint', 1),
(3, 15, 18, 'disjoint', 1),
(4, 15, 45, 'disjoint', 1),
(5, 15, 39, 'disjoint', 1),
(6, 17, 19, 'disjoint', 1),
(7, 17, 13, 'disjoint', 1),
(8, 17, 48, 'disjoint', 1),
(9, 17, 26, 'disjoint', 1),
(10, 17, 37, 'disjoint', 1),
(11, 18, 32, 'disjoint', 1),
(12, 18, 2, 'disjoint', 1),
(14, 18, 32, 'alternative solution', 1),
(15, 18, 49, 'disjoint', 1),
(16, 18, 39, 'disjoint', 1),
(17, 19, 37, 'disjoint', 1),
(18, 19, 21, 'alternative solution', 1),
(19, 19, 48, 'disjoint', 1),
(20, 19, 26, 'disjoint', 1),
(21, 19, 22, 'disjoint', 1),
(22, 20, 24, 'alternative solution', 1),
(23, 20, 25, 'disjoint', 1),
(24, 20, 11, 'disjoint', 1),
(25, 20, 26, 'disjoint', 1),
(26, 20, 6, 'disjoint', 1),
(27, 22, 49, 'similar', 1),
(28, 22, 38, 'disjoint', 1),
(29, 22, 44, 'disjoint', 1),
(30, 22, 7, 'disjoint', 1),
(31, 22, 6, 'disjoint', 1),
(32, 24, 11, 'disjoint', 1),
(33, 24, 7, 'disjoint', 1),
(34, 24, 33, 'disjoint', 1),
(35, 24, 5, 'disjoint', 1),
(36, 24, 5, 'disjoint', 1),
(37, 24, 34, 'disjoint', 1),
(38, 25, 14, 'disjoint', 1),
(39, 25, 11, 'alternative solution', 1),
(40, 25, 6, 'disjoint', 1),
(41, 25, 26, 'disjoint', 1),
(42, 25, 5, 'disjoint', 2),
(43, 26, 48, 'similar', 2),
(44, 26, 14, 'alternative solution', 2),
(45, 26, 6, 'disjoint', 2),
(46, 26, 5, 'disjoint', 2),
(47, 26, 11, 'disjoint', 2),
(48, 27, 31, 'disjoint', 2),
(49, 27, 43, 'disjoint', 2),
(50, 27, 33, 'disjoint', 2),
(51, 27, 1, 'disjoint', 2),
(52, 27, 4, 'disjoint', 2),
(53, 28, 29, 'disjoint', 2),
(54, 28, 43, 'disjoint', 2),
(55, 28, 2, 'disjoint', 2),
(56, 28, 33, 'disjoint', 2),
(57, 28, 4, 'disjoint', 2),
(58, 29, 30, 'disjoint', 2),
(59, 29, 33, 'disjoint', 2),
(60, 29, 43, 'disjoint', 2),
(61, 29, 11, 'disjoint', 2),
(62, 29, 42, 'disjoint', 2),
(63, 30, 11, 'disjoint', 2),
(64, 1, 50, 'disjoint', 2),
(65, 30, 34, 'disjoint', 2),
(66, 30, 35, 'disjoint', 2),
(67, 30, 43, 'disjoint', 2),
(68, 31, 32, 'disjoint', 2),
(69, 31, 46, 'disjoint', 2),
(70, 31, 33, 'disjoint', 2),
(71, 31, 11, 'disjoint', 2),
(73, 32, 11, 'disjoint', 2),
(74, 32, 14, 'disjoint', 2),
(76, 32, 46, 'alternative solution', 2),
(77, 32, 45, 'disjoint', 2),
(78, 32, 49, 'disjoint', 2),
(79, 33, 34, 'similar', 2),
(80, 33, 9, 'disjoint', 2),
(81, 33, 50, 'disjoint', 2),
(82, 33, 12, 'disjoint', 2),
(83, 33, 2, 'disjoint', 2),
(84, 34, 50, 'disjoint', 3),
(85, 34, 12, 'alternative solution', 3),
(86, 34, 1, 'disjoint', 3),
(87, 34, 4, 'disjoint', 3),
(88, 34, 9, 'disjoint', 3),
(89, 35, 11, 'disjoint', 3),
(90, 35, 45, 'disjoint', 3),
(91, 35, 14, 'disjoint', 3),
(92, 35, 50, 'disjoint', 3),
(93, 35, 13, 'disjoint', 3),
(94, 36, 11, 'disjoint', 3),
(95, 36, 40, 'disjoint', 3),
(96, 36, 49, 'disjoint', 3),
(97, 36, 14, 'disjoint', 3),
(98, 36, 45, 'disjoint', 3),
(99, 37, 49, 'disjoint', 3),
(100, 37, 14, 'disjoint', 3),
(101, 37, 11, 'disjoint', 3),
(102, 37, 5, 'disjoint', 3),
(103, 37, 40, 'disjoint', 3),
(105, 38, 44, 'disjoint', 3),
(106, 38, 44, 'disjoint', 3),
(107, 38, 48, 'disjoint', 3),
(108, 38, 45, 'disjoint', 3),
(109, 38, 11, 'disjoint', 3),
(110, 38, 13, 'disjoint', 3),
(111, 40, 13, 'disjoint', 3),
(112, 40, 11, 'disjoint', 3),
(113, 40, 13, 'disjoint', 3),
(114, 40, 43, 'disjoint', 3),
(116, 40, 5, 'disjoint', 3),
(117, 41, 4, 'disjoint', 3),
(118, 41, 46, 'disjoint', 3),
(119, 41, 45, 'disjoint', 3),
(120, 41, 44, 'disjoint', 3),
(121, 42, 7, 'disjoint', 3),
(122, 42, 1, 'disjoint', 3),
(123, 42, 47, 'disjoint', 3),
(124, 42, 4, 'disjoint', 4),
(126, 42, 2, 'disjoint', 4),
(127, 43, 11, 'disjoint', 4),
(128, 43, 13, 'disjoint', 4),
(129, 43, 14, 'disjoint', 4),
(130, 43, 5, 'disjoint', 4),
(131, 43, 6, 'disjoint', 4),
(132, 44, 6, 'disjoint', 4),
(133, 44, 45, 'disjoint', 4),
(134, 44, 11, 'disjoint', 4),
(135, 44, 11, 'disjoint', 4),
(136, 44, 48, 'disjoint', 4),
(137, 44, 13, 'disjoint', 4),
(138, 16, 21, 'alternative solution', 4),
(139, 16, 22, 'alternative solution', 4),
(140, 16, 19, 'alternative solution', 4),
(141, 16, 29, 'alternative solution', 4),
(142, 16, 43, 'disjoint', 4),
(143, 21, 22, 'alternative solution', 4),
(144, 21, 28, 'alternative solution', 4),
(145, 21, 43, 'disjoint', 4),
(146, 21, 33, 'disjoint', 4),
(147, 21, 37, 'disjoint', 4),
(148, 23, 33, 'disjoint', 4),
(149, 23, 34, 'disjoint', 4),
(150, 23, 6, 'disjoint', 4),
(151, 23, 2, 'disjoint', 4),
(152, 23, 2, 'disjoint', 4),
(153, 23, 26, 'disjoint', 4),
(154, 39, 42, 'disjoint', 4),
(155, 39, 48, 'disjoint', 4),
(156, 39, 3, 'disjoint', 4),
(157, 39, 46, 'disjoint', 4),
(158, 39, 10, 'disjoint', 4),
(159, 45, 11, 'disjoint', 4),
(160, 45, 14, 'disjoint', 4),
(161, 45, 5, 'disjoint', 4),
(162, 45, 47, 'disjoint', 4),
(163, 46, 4, 'disjoint', 4),
(164, 46, 49, 'disjoint', 5),
(165, 46, 13, 'disjoint', 5),
(166, 46, 11, 'disjoint', 5),
(168, 46, 14, 'disjoint', 5),
(169, 47, 5, 'disjoint', 5),
(170, 47, 14, 'alternative solution', 5),
(171, 47, 48, 'disjoint', 5),
(172, 47, 6, 'disjoint', 5),
(173, 47, 11, 'alternative solution', 5),
(174, 48, 6, 'disjoint', 5),
(175, 48, 13, 'disjoint', 5),
(176, 48, 5, 'disjoint', 5),
(177, 48, 14, 'disjoint', 5),
(178, 48, 11, 'disjoint', 5),
(179, 49, 7, 'disjoint', 5),
(180, 49, 2, 'disjoint', 5),
(181, 49, 11, 'disjoint', 5),
(182, 49, 2, 'disjoint', 5),
(183, 49, 14, 'disjoint', 5),
(184, 49, 50, 'disjoint', 5),
(185, 8, 11, 'disjoint', 5),
(187, 8, 10, 'disjoint', 5),
(188, 50, 12, 'disjoint', 5),
(189, 50, 13, 'disjoint', 5),
(190, 50, 14, 'disjoint', 5),
(191, 50, 5, 'disjoint', 5),
(192, 50, 6, 'disjoint', 5),
(193, 8, 13, 'disjoint', 5),
(194, 8, 1, 'disjoint', 5),
(196, 8, 14, 'disjoint', 5),
(198, 9, 2, 'disjoint', 5),
(200, 50, 13, 'alternative solution', 5),
(201, 9, 3, 'disjoint', 5),
(202, 9, 3, 'disjoint', 5),
(203, 9, 1, 'disjoint', 5),
(204, 9, 10, 'disjoint', 5),
(205, 10, 11, 'alternative solution', 5),
(206, 10, 14, 'similar', 5),
(207, 10, 3, 'disjoint', 5),
(208, 10, 13, 'disjoint', 5),
(209, 10, 12, 'disjoint', 5),
(210, 4, 1, 'disjoint', 5),
(211, 4, 3, 'disjoint', 5),
(212, 4, 2, 'disjoint', 5),
(213, 4, 13, 'disjoint', 5),
(214, 4, 11, 'disjoint', 6),
(215, 5, 13, 'disjoint', 6),
(216, 5, 6, 'disjoint', 6),
(217, 5, 11, 'disjoint', 6),
(218, 5, 14, 'disjoint', 6),
(219, 5, 10, 'disjoint', 6),
(220, 6, 13, 'disjoint', 6),
(221, 6, 11, 'disjoint', 6),
(222, 6, 10, 'disjoint', 6),
(224, 6, 3, 'disjoint', 6),
(227, 11, 14, 'disjoint', 6),
(228, 11, 13, 'disjoint', 6),
(229, 11, 1, 'disjoint', 6),
(230, 11, 2, 'disjoint', 6),
(231, 11, 3, 'disjoint', 6),
(232, 12, 13, 'disjoint', 6),
(233, 12, 1, 'disjoint', 6),
(234, 12, 14, 'disjoint', 6),
(235, 12, 15, 'disjoint', 6),
(236, 6, 14, 'disjoint', 6),
(237, 7, 11, 'disjoint', 6),
(238, 7, 9, 'disjoint', 6),
(239, 7, 1, 'disjoint', 6),
(240, 7, 8, 'disjoint', 6),
(241, 7, 3, 'disjoint', 6),
(242, 13, 14, 'alternative solution', 6),
(243, 13, 2, 'disjoint', 6),
(244, 13, 3, 'disjoint', 6),
(245, 13, 1, 'disjoint', 6),
(246, 13, 15, 'disjoint', 6),
(247, 14, 2, 'disjoint', 6),
(248, 14, 3, 'disjoint', 6),
(249, 14, 3, 'disjoint', 6),
(250, 3, 2, 'disjoint', 6),
(251, 2, 15, 'disjoint', 6),
(252, 14, 1, 'disjoint', 6),
(253, 14, 15, 'disjoint', 6),
(254, 1, 3, 'disjoint', 6),
(255, 1, 2, 'disjoint', 6),
(256, 1, 15, 'disjoint', 6),
(257, 3, 15, 'disjoint', 6);

-- --------------------------------------------------------

--
-- Structure de la table `validation`
--

CREATE TABLE `validation` (
  `id` int(11) NOT NULL,
  `r_id` int(11) NOT NULL,
  `val` text NOT NULL,
  `member_id` int(11) NOT NULL,
  `Vsession` int(11) NOT NULL
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
-- Index pour la table `relationships`
--
ALTER TABLE `relationships`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idea1` (`idea1`),
  ADD KEY `idea2` (`idea2`) USING BTREE;

--
-- Index pour la table `validation`
--
ALTER TABLE `validation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `r_id` (`r_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `idea`
--
ALTER TABLE `idea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `relationships`
--
ALTER TABLE `relationships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=258;

--
-- AUTO_INCREMENT pour la table `validation`
--
ALTER TABLE `validation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `relationships`
--
ALTER TABLE `relationships`
  ADD CONSTRAINT `fk_idea1` FOREIGN KEY (`idea1`) REFERENCES `idea` (`id`),
  ADD CONSTRAINT `fk_idea2` FOREIGN KEY (`idea2`) REFERENCES `idea` (`id`);

--
-- Contraintes pour la table `validation`
--
ALTER TABLE `validation`
  ADD CONSTRAINT `fk_rel` FOREIGN KEY (`r_id`) REFERENCES `relationships` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
