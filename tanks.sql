-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Апр 14 2023 г., 14:30
-- Версия сервера: 5.7.17-log
-- Версия PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `tanks`
--

-- --------------------------------------------------------

--
-- Структура таблицы `avangar`
--

CREATE TABLE `avangar` (
  `id` int(11) NOT NULL,
  `tanks` text NOT NULL,
  `weapons` text NOT NULL,
  `inventar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `avangar`
--

INSERT INTO `avangar` (`id`, `tanks`, `weapons`, `inventar`) VALUES
(1, '{\"hulls\":[\"BTR_m3\",\"T34_m3\",\"T15_m3\",\"CONCAT_m3\"] ,\"hull\":0}', '{}', '{}'),
(2, '{\"hulls\":[\"BTR_m2\",\"T34_m3\",\"T15_m3\",\"CONCAT_m3\"] ,\"hull\":2}', '{}', '{}'),
(3, '{\"hulls\":[\"BTR_m3\",\"T34_m3\",\"T15_m3\",\"CONCAT_m3\"] ,\"hull\":2}', '{}', '{}');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `UserType` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `UserType`, `money`, `score`) VALUES
(1, 'admin', '1', 4, 50000, 0),
(2, 'anton', '1', 4, 50000, 0),
(3, 'An', '1', 4, 50000, 0);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `avangar`
--
ALTER TABLE `avangar`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `avangar`
--
ALTER TABLE `avangar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
