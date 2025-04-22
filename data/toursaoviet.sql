-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 22, 2025 at 11:32 PM
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
  `quantity_day` int DEFAULT NULL,
  `schedule_code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `schedule_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `is_reserved` bit(1) DEFAULT NULL,
  `is_reviewed` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `adult_price`, `booking_time`, `children_price`, `code`, `customer_code`, `customer_id`, `discount`, `end_date`, `start_date`, `status`, `total_price`, `tour_code`, `tour_id`, `tour_name`, `quantity_adult`, `quantity_children`, `quantity_day`, `schedule_code`, `schedule_id`, `is_reserved`, `is_reviewed`) VALUES
('0eab6f9d-603c-4ea5-82f3-14f740863e4b', 250000, '2025-04-11 14:16:13.588664', 180000, '1744355675345583', 'KH2025000001', 'a2d942d9-9934-419d-96b2-05a11d0a80a9', 25000, '2025-04-16', '2025-04-13', 'Đã xác nhận', 655000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'1'),
('2adf2d0a-5ec0-4e15-b1c8-7eacc6e1d0b8', 250000, '2025-04-11 01:02:45.550880', 180000, '1744308165437325', 'KH2025000001', 'a2d942d9-9934-419d-96b2-05a11d0a80a9', 0, '2025-04-16', '2025-04-13', 'Đã hủy', 680000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'0'),
('470c9cb0-ffa0-4eae-9dcf-48a9a8b61e51', 250000, '2025-04-10 17:46:21.070031', 180000, '1744281486261308', 'KH2025000001', 'a2d942d9-9934-419d-96b2-05a11d0a80a9', 0, '2025-04-15', '2025-04-12', 'Đã hủy', 680000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'0'),
('76a6a6b1-7e04-409b-bf8b-6efce5ffe51d', 250000, '2025-04-09 17:41:57.239985', 180000, '1744195263066452', 'KH2025000001', 'a2d942d9-9934-419d-96b2-05a11d0a80a9', 0, '2025-04-11', '2025-04-09', 'Đang xử lý', 680000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'0'),
('8020ca2c-246d-4c45-b641-415e339d9a9e', 250000, '2025-04-10 19:11:06.107042', 180000, '1744287016648206', 'KH2025000001', '0371691d-4050-438b-ba33-f3ac1207b793', 0, '2025-04-15', '2025-04-12', 'Đang xử lý', 680000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'0'),
('89abb866-dde0-485f-87bc-32a9fb6df344', 250000, '2025-04-10 19:14:21.062799', 180000, '1744287204330945', 'KH2025000001', '0371691d-4050-438b-ba33-f3ac1207b793', 25000, '2025-04-15', '2025-04-12', 'Đã xác nhận', 655000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'1', b'0'),
('d390d250-e2a7-4b8b-b823-ef987e2ac190', 250000, '2025-04-09 17:44:26.396835', 180000, '1744195466353631', 'KH2025000001', '0371691d-4050-438b-ba33-f3ac1207b793', 0, '2025-04-15', '2025-04-12', 'Đã hủy', 680000, 'T2025000001', '54f9acd8-de2d-49a7-87ca-4aee9daba4b2', 'Tour khám phá Hà Giang', 2, 1, 3, 'LT20250001', 'sche-12345', b'0', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `checkout`
--

CREATE TABLE `checkout` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `checkout_time` datetime(6) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `method` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `booking_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `checkout`
--

INSERT INTO `checkout` (`id`, `checkout_time`, `code`, `method`, `status`, `booking_id`) VALUES
('3edc8f0f-242d-45f4-a3da-ae0f013000c0', '2025-04-10 19:14:20.967673', '14900468', 'VNPay', 'Đã thanh toán', '89abb866-dde0-485f-87bc-32a9fb6df344'),
('450b2817-b988-446d-ae7c-f0622e6db99d', '2025-04-10 17:46:20.733314', '14900218', 'VNPay', 'Đã thanh toán', '470c9cb0-ffa0-4eae-9dcf-48a9a8b61e51'),
('456e90ab-b914-4284-9dbf-353be5b8db19', '2025-04-10 19:11:06.062260', '14900463', 'VNPay', 'Đã thanh toán', '8020ca2c-246d-4c45-b641-415e339d9a9e'),
('72e02425-aff6-4f8a-b3a7-4f9b3be103ed', '2025-04-09 17:41:57.166930', '4392589639', 'MoMo', 'Đã thanh toán', '76a6a6b1-7e04-409b-bf8b-6efce5ffe51d'),
('933e2b9b-c104-4c8d-94ff-d8bcf9cf7504', '2025-04-11 14:16:13.403567', '4395053906', 'MoMo', 'Đã thanh toán', '0eab6f9d-603c-4ea5-82f3-14f740863e4b'),
('a41e1b1f-5093-4778-90c8-837c40116d22', NULL, '', 'Tiền mặt', 'Chưa thanh toán', 'd390d250-e2a7-4b8b-b823-ef987e2ac190'),
('ba86a91e-190f-4eaa-b56c-fa623175915a', '2025-04-11 15:58:30.561957', '', 'Tiền mặt', 'Chưa thanh toán', '2adf2d0a-5ec0-4e15-b1c8-7eacc6e1d0b8');

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
('1347bc6c-0568-48b3-8a2c-f3c453618348', 'KH2025000002', 'thanhhkh3@gmail.com', 'Trần Thành', '$2a$10$71E0JRplj5Rlnuu1gKZwx.COcZ8LmmQ3IE7IGcRKNoDN7JDV0/qoa', '0825702210', '2025-04-15 01:49:25.033616', 'Đang hoạt động'),
('32a36d69-4760-4a34-a36d-fe90255f875d', 'KH2025000003', 'tranthanh200322@gmail.com', 'Trần Thành', '$2a$10$FYyp7qYo71uCMoDQJ8do1.y1GPOOi05FwwM56e/ANn2iWpLwObQ7m', '0825702211', '2025-04-22 01:51:41.077804', 'Đang hoạt động');

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
('1347bc6c-0568-48b3-8a2c-f3c453618348', 'USER'),
('32a36d69-4760-4a34-a36d-fe90255f875d', 'USER');

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
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `summary` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `view_count` int DEFAULT NULL
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

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `description`, `discount`, `end_date`, `quantity`, `start_date`, `code`, `created_time`, `status`, `title`) VALUES
('promo-67890', 'Dùng nhanh kẻo hết', 25000, '2025-04-16', 3, '2025-04-12', 'HE2025-01', '2025-04-05 03:42:07.000000', 'Đang diễn ra', 'Giảm giá 25K');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `comment` text COLLATE utf8mb4_vietnamese_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `customer_id`, `rating`, `time_stamp`, `tour_id`, `comment`) VALUES
('d693235d-1bf0-4f7e-88d3-aaf7ba86fba8', '0371691d-4050-438b-ba33-f3ac1207b793', 4, '2025-04-12 19:48:52.395462', 'a2d942d9-9934-419d-96b2-05a11d0a80a9', 'Tuyệt vời, tôi sẽ đặt tour tại Sao Việt nhiều hơn.');

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
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `adult_price`, `children_price`, `code`, `end_date`, `quantity_people`, `start_date`, `status`, `total_people`, `tour_id`, `created_time`) VALUES
('4791d516-fc35-4e6sdv', 5000000, 4000000, 'PC-202500003', '2025-04-24', 0, '2025-04-25', 'Chưa diễn ra', 20, '4791d516-fc35-4e65-805e-0002bc8968c5', '2025-04-23 04:10:23.000000'),
('bhjhv', 2500000, 1000000, 'PC20250001', '2025-04-24', 10, '2025-04-23', 'Chưa diễn ra', 20, '4791d516-fc35-4e65-805e-0002bc8968c5', '2025-04-23 01:10:27.244000'),
('dsfvds', 30000000, 2000000, 'PC250002', '2025-04-24', 5, '2025-04-23', 'Chưa diễn ra', 20, 'c9d72b78-e452-4f2c-a22b-dc48902c239d', '2025-04-23 02:10:41.524000');

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
(1, 3, 'customer', 2025),
(2, 9, 'tour', 2025);

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
('1f270cb9-5eeb-4ded-83a3-99165aa27ef9', '2025-04-15 02:50:34'),
('1fe094d6-63ec-489c-8045-52fa9f1d3a05', '2025-04-01 17:09:28'),
('38aea9a9-94be-4739-b757-04de809a040c', '2025-04-22 01:52:22'),
('5cc5c9de-8661-48d0-bdbe-16b351ca6312', '2025-04-01 18:44:12'),
('7f56da9a-1249-455f-8da8-bfbc369a0edc', '2025-04-14 01:23:23'),
('a5f5e5ee-9957-4330-824d-d4cfd549052b', '2025-04-01 18:21:12'),
('ae6a55e7-11b6-4c58-a16d-4c94ac2fd8f6', '2025-04-01 18:28:30'),
('b33acd3d-2b3e-489e-a60a-30a3d08d9b14', '2025-04-01 16:14:05'),
('c14d7fd7-2577-4cdc-874f-3b0d028288a6', '2025-04-01 18:31:35'),
('db9cfb77-e483-4d53-997b-6c66dae281e1', '2025-04-22 02:50:25');

-- --------------------------------------------------------

--
-- Table structure for table `tour`
--

CREATE TABLE `tour` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_vietnamese_ci,
  `destination` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `quantity_day` int DEFAULT NULL,
  `quantity_order` int DEFAULT NULL,
  `area` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour`
--

INSERT INTO `tour` (`id`, `code`, `description`, `destination`, `name`, `quantity_day`, `quantity_order`, `area`, `time_stamp`) VALUES
('4791d516-fc35-4e65-805e-0002bc8968c5', 'T2025000007', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Hà Nội - Hạ Long', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 0, 't', '2025-04-22 23:07:20.683688'),
('c9d72b78-e452-4f2c-a22b-dc48902c239d', 'T2025000009', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Hà Nội - Hạ Long', 'DU LỊCH HẠ LONG 3 NGÀY - 2 ĐÊM', 3, 0, 'b', '2025-04-23 03:42:57.940150');

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
('4791d516-fc35-4e65-805e-0002bc8968c5', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('c9d72b78-e452-4f2c-a22b-dc48902c239d', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('c9d72b78-e452-4f2c-a22b-dc48902c239d', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tour_itineraries`
--

CREATE TABLE `tour_itineraries` (
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `day_number` int DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_vietnamese_ci,
  `title` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour_itineraries`
--

INSERT INTO `tour_itineraries` (`tour_id`, `day_number`, `description`, `title`) VALUES
('4791d516-fc35-4e65-805e-0002bc8968c5', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('c9d72b78-e452-4f2c-a22b-dc48902c239d', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('c9d72b78-e452-4f2c-a22b-dc48902c239d', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long');

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
-- Indexes for table `news`
--
ALTER TABLE `news`
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
