-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 03, 2025 at 09:51 PM
-- Server version: 8.0.40
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toursaoviet`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `registered_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `code`, `email`, `full_name`, `password`, `phone`, `registered_time`) VALUES
('22982e77-6ba9-4a8e-b099-3e3fd96a1584', 'AD250001', 'admin@gmail.com', 'Trần Thành', '$2a$10$yYVQKMqgrDRGu87pLJnkxOurMBL8RvV9gHdnnZiE8dvuGSKxSiNH2', '0399999999', '2025-03-30 09:47:20.096481');

-- --------------------------------------------------------

--
-- Table structure for table `admin_roles`
--

CREATE TABLE `admin_roles` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `admin_roles`
--

INSERT INTO `admin_roles` (`id`, `role`) VALUES
('22982e77-6ba9-4a8e-b099-3e3fd96a1584', 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `adult_price` double DEFAULT NULL,
  `assignment_code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `assignment_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `booking_time` datetime(6) DEFAULT NULL,
  `children_price` double DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `customer_code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `tour_code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `tour_name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `quantity_adult` int DEFAULT NULL,
  `quantity_children` int DEFAULT NULL,
  `quantity_day` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int NOT NULL,
  `description` longtext COLLATE utf8mb4_vietnamese_ci,
  `image` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `description`, `image`, `name`) VALUES
(1, 'Khám phá những bãi biển tuyệt đẹp và các đảo hoang sơ', 'biendao.jpg', 'Tours Biển Đảo'),
(2, 'Tìm hiểu về lịch sử và văn hóa đặc sắc của các vùng miền', 'vanhoa.jpg', 'Tours Văn Hóa Lịch Sử'),
(3, 'Thư giãn và tận hưởng những dịch vụ nghỉ dưỡng hàng đầu', 'nghiduong.jpg', 'Tours Nghỉ Dưỡng'),
(4, 'Thử thách bản thân với những hoạt động mạo hiểm và thú vị', 'maohiem.jpg', 'Tours Mạo Hiểm'),
(5, 'Thưởng thức những món ăn đặc sản và tìm hiểu văn hóa ẩm thực', 'amthuc.jpg', 'Tours Ẩm Thực');

-- --------------------------------------------------------

--
-- Table structure for table `checkout`
--

CREATE TABLE `checkout` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `amount` double DEFAULT NULL,
  `checkout_time` datetime(6) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `method` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `order_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `registered_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `code`, `email`, `full_name`, `password`, `phone`, `registered_time`, `status`) VALUES
('0371691d-4050-438b-ba33-f3ac1207b793', 'KH2025000001', 'thanhhkh3@gmail.com', 'Trần Thành', '$2a$10$XEoBS09iLESDFzmvN1aXA.RTbnoje9DkwddBhJMelEAvPwrtJJs0u', '0825702210', '2025-04-02 23:03:45.926337', 'Đang hoạt động');

-- --------------------------------------------------------

--
-- Table structure for table `customer_roles`
--

CREATE TABLE `customer_roles` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `customer_roles`
--

INSERT INTO `customer_roles` (`id`, `role`) VALUES
('0371691d-4050-438b-ba33-f3ac1207b793', 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `amount` double DEFAULT NULL,
  `booking_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `details` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `time_issued` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `comment` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `adult_price` double DEFAULT NULL,
  `children_price` double DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `quantity_people` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `total_people` int DEFAULT NULL,
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE `sequence` (
  `id` bigint NOT NULL,
  `last_number` int DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `year` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`id`, `last_number`, `type`, `year`) VALUES
(1, 1, 'customer', 2025),
(2, 1, 'tour', 2025);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `expiry_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`id`, `expiry_time`) VALUES
('1fe094d6-63ec-489c-8045-52fa9f1d3a05', '2025-04-01 17:09:28'),
('5cc5c9de-8661-48d0-bdbe-16b351ca6312', '2025-04-01 18:44:12'),
('a5f5e5ee-9957-4330-824d-d4cfd549052b', '2025-04-01 18:21:12'),
('ae6a55e7-11b6-4c58-a16d-4c94ac2fd8f6', '2025-04-01 18:28:30'),
('b33acd3d-2b3e-489e-a60a-30a3d08d9b14', '2025-04-01 16:14:05'),
('c14d7fd7-2577-4cdc-874f-3b0d028288a6', '2025-04-01 18:31:35');

-- --------------------------------------------------------

--
-- Table structure for table `tour`
--

CREATE TABLE `tour` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_vietnamese_ci,
  `destination` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `quantity_day` int DEFAULT NULL,
  `quantity_order` int DEFAULT NULL,
  `area` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour`
--

INSERT INTO `tour` (`id`, `code`, `created_time`, `description`, `destination`, `name`, `quantity_day`, `quantity_order`, `area`) VALUES
('54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'T2025000001', '2025-04-04 01:30:51.200075', 'Tour khám phá Hà Giang 3 ngày 2 đêm với cảnh đẹp thiên nhiên và trải nghiệm văn hóa dân tộc.', 'Hà Giang, Việt Nam', 'Tour khám phá Hà Giang', 3, 0, 'Vùng núi phía Bắc');

-- --------------------------------------------------------

--
-- Table structure for table `tour_images`
--

CREATE TABLE `tour_images` (
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour_images`
--

INSERT INTO `tour_images` (`tour_id`, `image`) VALUES
('54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tour_itineraries`
--

CREATE TABLE `tour_itineraries` (
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `itinerary` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour_itineraries`
--

INSERT INTO `tour_itineraries` (`tour_id`, `itinerary`) VALUES
('54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Ngày 1: Hà Nội - Hà Giang'),
('54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Ngày 2: Hà Giang - Đồng Văn');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKcn2sefc6soqs1gqo4jwvhtbb7` (`code`),
  ADD UNIQUE KEY `UKc0r9atamxvbhjjvy5j8da1kam` (`email`),
  ADD UNIQUE KEY `UK3wx6cyhxqnmir7sde90jkhp1k` (`phone`);

--
-- Indexes for table `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD KEY `FKgp47bc0lu5j4h6tcaeosqwytp` (`id`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKtirjn0ldjn2xdqne9laduomc6` (`code`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrm1bp9bhtiih5foj17t8l500j` (`code`),
  ADD UNIQUE KEY `UKdwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD UNIQUE KEY `UKo3uty20c6csmx5y4uk2tc5r4m` (`phone`);

--
-- Indexes for table `customer_roles`
--
ALTER TABLE `customer_roles`
  ADD KEY `FK6vg2k9wl18ly4cp6x8ouddvug` (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKmfoqwlcsgf6qa1v6lauq33taq` (`code`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKmug8vys7rqgcallm0fl9mbx0g` (`type`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1or90qldxxh4mt7h7peijufnf` (`code`);

--
-- Indexes for table `tour_images`
--
ALTER TABLE `tour_images`
  ADD KEY `FKsr8u6cqvu0fxqst45pkhjnljw` (`tour_id`);

--
-- Indexes for table `tour_itineraries`
--
ALTER TABLE `tour_itineraries`
  ADD KEY `FKoye3f7cqsvt40pt94k9y2ikb9` (`tour_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sequence`
--
ALTER TABLE `sequence`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_roles`
--
ALTER TABLE `admin_roles`
  ADD CONSTRAINT `FKgp47bc0lu5j4h6tcaeosqwytp` FOREIGN KEY (`id`) REFERENCES `admin` (`id`);

--
-- Constraints for table `customer_roles`
--
ALTER TABLE `customer_roles`
  ADD CONSTRAINT `FK6vg2k9wl18ly4cp6x8ouddvug` FOREIGN KEY (`id`) REFERENCES `customer` (`id`);

--
-- Constraints for table `tour_images`
--
ALTER TABLE `tour_images`
  ADD CONSTRAINT `FKsr8u6cqvu0fxqst45pkhjnljw` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`);

--
-- Constraints for table `tour_itineraries`
--
ALTER TABLE `tour_itineraries`
  ADD CONSTRAINT `FKoye3f7cqsvt40pt94k9y2ikb9` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`id`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`%` EVENT `delete_expired_tokens` ON SCHEDULE EVERY 1 DAY STARTS '2025-02-15 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM token WHERE expiry_time < NOW()$$

CREATE DEFINER=`root`@`%` EVENT `delete_orders_unpay` ON SCHEDULE EVERY 1 DAY STARTS '2025-02-15 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
    DELETE FROM payment 
    WHERE order_id IN (
        SELECT id FROM orders WHERE status = 'Đang xử lý'
    ) 
    AND status = 'Chưa thanh toán';

    DELETE FROM orders 
    WHERE status = 'Đang xử lý' 
    AND id NOT IN (SELECT order_id FROM payment);
END$$

CREATE DEFINER=`root`@`%` EVENT `update_status_assignment` ON SCHEDULE EVERY 1 DAY STARTS '2025-02-15 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE assignment SET status = 'Đã kết thúc' WHERE end_date < NOW()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
