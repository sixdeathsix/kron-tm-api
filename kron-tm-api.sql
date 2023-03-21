-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Мар 21 2023 г., 13:15
-- Версия сервера: 5.7.39
-- Версия PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `kron-tm-api`
--

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`category_id`, `category`) VALUES
(1, '<500');

-- --------------------------------------------------------

--
-- Структура таблицы `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_date` datetime(6) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `event_type_id` int(11) DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `events`
--

INSERT INTO `events` (`event_id`, `event_date`, `category_id`, `event_type_id`, `property_id`) VALUES
(1, '2023-03-01 00:00:00.000000', 1, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `event_types`
--

CREATE TABLE `event_types` (
  `event_type_id` int(11) NOT NULL,
  `event_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `event_types`
--

INSERT INTO `event_types` (`event_type_id`, `event_type`) VALUES
(1, 'Нулевой дебит');

-- --------------------------------------------------------

--
-- Структура таблицы `objects`
--

CREATE TABLE `objects` (
  `object_id` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flange_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `object_type_id` int(11) DEFAULT NULL,
  `object_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `objects`
--

INSERT INTO `objects` (`object_id`, `description`, `flange_no`, `object_type_id`, `object_name`) VALUES
(1, 'Куст-2 Скважина-12', '1', 1, 'Скважина-12'),
(2, 'Куст-2 Скважина-13', '1', 1, 'Скважина-13'),
(3, 'Куст-2 Скважина-14', '1', 1, 'Скважина-14'),
(4, 'Куст-2 Кнс-2', NULL, 2, 'КНС-2');

-- --------------------------------------------------------

--
-- Структура таблицы `object_types`
--

CREATE TABLE `object_types` (
  `object_type_id` int(11) NOT NULL,
  `object_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `object_types`
--

INSERT INTO `object_types` (`object_type_id`, `object_type`) VALUES
(1, 'Скважина добывающая'),
(2, 'Канализационная насосная станция');

-- --------------------------------------------------------

--
-- Структура таблицы `paths`
--

CREATE TABLE `paths` (
  `path_id` int(11) NOT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `paths`
--

INSERT INTO `paths` (`path_id`, `path`) VALUES
(1, 'path_1'),
(2, 'path_2'),
(3, 'path_3'),
(4, 'path_4');

-- --------------------------------------------------------

--
-- Структура таблицы `properties`
--

CREATE TABLE `properties` (
  `property_id` int(11) NOT NULL,
  `object_id` int(11) DEFAULT NULL,
  `path_id` int(11) DEFAULT NULL,
  `property_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `properties`
--

INSERT INTO `properties` (`property_id`, `object_id`, `path_id`, `property_type_id`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 3, 3, 1),
(4, 4, 4, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `property_logs`
--

CREATE TABLE `property_logs` (
  `property_log_id` int(11) NOT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `property_id` int(11) DEFAULT NULL,
  `value_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `property_types`
--

CREATE TABLE `property_types` (
  `property_type_id` int(11) NOT NULL,
  `property_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `property_types`
--

INSERT INTO `property_types` (`property_type_id`, `property_type`) VALUES
(1, 'Количество жидкости');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `patronymic` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` smallint(6) DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`user_id`, `email`, `name`, `password`, `patronymic`, `phone`, `role`, `surname`, `username`) VALUES
(1, 'admin@mai.ru', 'Арслан', '$2a$10$Bwk7dWnZaqp6umF56nZHz.iPWvCUBNz.WeP9tEroWmq4ms.frE4kq', 'Рахмиддинович', '+79999999999', 0, 'Камалов', 'admin'),
(5, 'user@mail.ru', 'Арслан', '$2a$10$YshNdlkQRZQgez1FricgEuaDUrB0Wsa4Bg3zQYAQVyc0g1En2l9Za', 'Рахмиддинович', '+79999999999', 0, 'Камалов', 'user');

-- --------------------------------------------------------

--
-- Структура таблицы `value_types`
--

CREATE TABLE `value_types` (
  `value_type_id` int(11) NOT NULL,
  `value_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `value_types`
--

INSERT INTO `value_types` (`value_type_id`, `value_type`) VALUES
(1, 'м3');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Индексы таблицы `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FKo6mla8j1p5bokt4dxrlmgwc28` (`category_id`),
  ADD KEY `FK2198du56mf1aoaxrdlw3du288` (`event_type_id`),
  ADD KEY `FKqhuovtrt3aiat70819smcrxny` (`property_id`);

--
-- Индексы таблицы `event_types`
--
ALTER TABLE `event_types`
  ADD PRIMARY KEY (`event_type_id`);

--
-- Индексы таблицы `objects`
--
ALTER TABLE `objects`
  ADD PRIMARY KEY (`object_id`),
  ADD KEY `FKfexbuq738xs99ysj5w36hudts` (`object_type_id`);

--
-- Индексы таблицы `object_types`
--
ALTER TABLE `object_types`
  ADD PRIMARY KEY (`object_type_id`);

--
-- Индексы таблицы `paths`
--
ALTER TABLE `paths`
  ADD PRIMARY KEY (`path_id`);

--
-- Индексы таблицы `properties`
--
ALTER TABLE `properties`
  ADD PRIMARY KEY (`property_id`),
  ADD KEY `FKsrvpy3c7e89qy3f0pmtwhmo1f` (`object_id`),
  ADD KEY `FKeeka8ch3idgletcw43nwvelxs` (`path_id`),
  ADD KEY `FK9kogppid3dqet1tptnohat21d` (`property_type_id`);

--
-- Индексы таблицы `property_logs`
--
ALTER TABLE `property_logs`
  ADD PRIMARY KEY (`property_log_id`),
  ADD KEY `FKfv0h39lfhvdubijjkheo2eyay` (`property_id`),
  ADD KEY `FKk3hf9e4kjnpvforh7ki9nxqwp` (`value_type_id`);

--
-- Индексы таблицы `property_types`
--
ALTER TABLE `property_types`
  ADD PRIMARY KEY (`property_type_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Индексы таблицы `value_types`
--
ALTER TABLE `value_types`
  ADD PRIMARY KEY (`value_type_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `event_types`
--
ALTER TABLE `event_types`
  MODIFY `event_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `objects`
--
ALTER TABLE `objects`
  MODIFY `object_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `object_types`
--
ALTER TABLE `object_types`
  MODIFY `object_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `paths`
--
ALTER TABLE `paths`
  MODIFY `path_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `properties`
--
ALTER TABLE `properties`
  MODIFY `property_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `property_logs`
--
ALTER TABLE `property_logs`
  MODIFY `property_log_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `property_types`
--
ALTER TABLE `property_types`
  MODIFY `property_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `value_types`
--
ALTER TABLE `value_types`
  MODIFY `value_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FK2198du56mf1aoaxrdlw3du288` FOREIGN KEY (`event_type_id`) REFERENCES `event_types` (`event_type_id`),
  ADD CONSTRAINT `FKo6mla8j1p5bokt4dxrlmgwc28` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  ADD CONSTRAINT `FKqhuovtrt3aiat70819smcrxny` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`);

--
-- Ограничения внешнего ключа таблицы `objects`
--
ALTER TABLE `objects`
  ADD CONSTRAINT `FKfexbuq738xs99ysj5w36hudts` FOREIGN KEY (`object_type_id`) REFERENCES `object_types` (`object_type_id`);

--
-- Ограничения внешнего ключа таблицы `properties`
--
ALTER TABLE `properties`
  ADD CONSTRAINT `FK9kogppid3dqet1tptnohat21d` FOREIGN KEY (`property_type_id`) REFERENCES `property_types` (`property_type_id`),
  ADD CONSTRAINT `FKeeka8ch3idgletcw43nwvelxs` FOREIGN KEY (`path_id`) REFERENCES `paths` (`path_id`),
  ADD CONSTRAINT `FKsrvpy3c7e89qy3f0pmtwhmo1f` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`);

--
-- Ограничения внешнего ключа таблицы `property_logs`
--
ALTER TABLE `property_logs`
  ADD CONSTRAINT `FKfv0h39lfhvdubijjkheo2eyay` FOREIGN KEY (`property_id`) REFERENCES `properties` (`property_id`),
  ADD CONSTRAINT `FKk3hf9e4kjnpvforh7ki9nxqwp` FOREIGN KEY (`value_type_id`) REFERENCES `value_types` (`value_type_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
