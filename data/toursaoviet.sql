-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 15, 2025 at 04:01 PM
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
('22982e77-6ba9-4a8e-b099-3e3fd96a1584', 'AD250001', 'admin@gmail.com', 'Trần Thành', '$2a$10$DZk3853CNzQOdy871zYSY.ZpcoOiVidF2gPkMCLTwqXwJHp4.u4GS', '0399999999', '2025-03-30 09:47:20.096481');

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
  `is_reviewed` bit(1) DEFAULT NULL,
  `promotion_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `adult_price`, `booking_time`, `children_price`, `code`, `customer_code`, `customer_id`, `discount`, `end_date`, `start_date`, `status`, `total_price`, `tour_code`, `tour_id`, `tour_name`, `quantity_adult`, `quantity_children`, `quantity_day`, `schedule_code`, `schedule_id`, `is_reserved`, `is_reviewed`, `promotion_id`) VALUES
('068a1cb8-6421-42bb-b712-ed6929abdd09', 4500000, '2025-05-11 17:33:06.705868', 3000000, '1746959568822172', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 50000, '2025-05-12', '2025-05-15', 'Đã hủy', 7450000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', 'cdc313'),
('0893c616-f8be-468a-959c-fbf30bcfac0e', 4500000, '2025-05-12 04:44:19.090479', 3000000, '1746999859024229', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', ''),
('09b7b719-e0af-45fc-b939-de6309eda32d', 4500000, '2025-05-11 18:15:52.614812', 3000000, '1746962152576311', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('0d5c25e9-c024-4cea-8849-c5b425ce574e', 4500000, '2025-05-12 03:28:53.697481', 3000000, '1746995333643682', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 4500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 0, 2, 'PC20250002', 'fgsrs', b'1', b'1', ''),
('2462594b-ef52-4c77-bebc-94520c9c073d', 4500000, '2025-05-12 17:45:44.633791', 3000000, '1747046712690439', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 25000, '2025-05-05', '2025-05-07', 'Đã hủy', 7475000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC4935', 'sfbfad', b'1', b'0', 'promo-67890'),
('2f594046-3ae2-4055-8fbc-87a0cf1a1741', 4500000, '2025-05-11 18:08:37.014719', 3000000, '1746961716978668', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 7500000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', ''),
('3781e768-22b5-4b15-8e40-e8d7512c9bf4', 2500000, '2025-05-14 01:29:00.345214', 2000000, '1747160907928714', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 90000, '2025-05-19', '2025-05-17', 'Đang xử lý', 4410000, 'T2025000027', '366023a3-0e66-4509-b9d7-a12020b1e8ea', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'LT2025000000002', 'ac3ced90-8136-48c4-97f3-d7de764d5f7c', b'1', b'0', 'ea337371-3976-4c78-b458-663ce51df0fc'),
('3def5ff3-3c32-4c56-8e5b-afbbf0cb8dd8', 4500000, '2025-05-12 15:12:15.421096', 3000000, '1747037497052531', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-05', '2025-05-07', 'Đã hủy', 7500000, 'T2025000026', '1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 4, 'PC250003', 'sgfdh', b'1', b'0', ''),
('3e75a32c-2e96-4361-9f7b-80fff3f1f066', 4500000, '2025-05-12 12:25:31.854161', 3000000, '1747027475048883', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 25000, '2025-05-05', '2025-05-07', 'Đã hủy', 7475000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC4935', 'sfbfad', b'1', b'0', 'promo-67890'),
('481dd7f7-7291-4633-bfda-429329244719', 4500000, '2025-05-12 12:26:06.832316', 3000000, '1747027566787151', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('5317be13-e5c2-4667-8be4-6988040189e2', 2500000, '2025-05-14 00:23:35.172573', 2000000, '1747156997304659', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 90000, '2025-05-14', '2025-05-17', 'Đã xác nhận', 4410000, 'T2025000027', '366023a3-0e66-4509-b9d7-a12020b1e8ea', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'LT2025000000002', 'ac3ced90-8136-48c4-97f3-d7de764d5f7c', b'1', b'0', 'ea337371-3976-4c78-b458-663ce51df0fc'),
('5ce60fc0-198f-418f-a0ea-8b13abb6a4bf', 4500000, '2025-05-12 12:26:38.835306', 3000000, '1747027598788196', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('5f9b7eaa-7402-466d-b6b3-c502de455dd8', 4500000, '2025-05-11 17:37:07.413971', 3000000, '1746959827377259', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 15000000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 2, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('6ed535da-69f2-4918-bb0e-917baee0bfdd', 4500000, '2025-05-11 17:35:02.249024', 3000000, '1746959651423815', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 25000, '2025-05-12', '2025-05-15', 'Đã xác nhận', 8975000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 0, 2, 'PC20250002', 'fgsrs', b'1', b'1', 'promo-67890'),
('730e72e4-9001-4524-a57c-6f587135224b', 4500000, '2025-05-13 19:09:35.388725', 3000000, '1747138155621184', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 90000, '2025-05-16', '2025-05-14', 'Đã xác nhận', 7410000, 'T2025000026', '1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 4, 'PC250003', 'sgfdh', b'1', b'1', 'ea337371-3976-4c78-b458-663ce51df0fc'),
('7807c70b-9e42-4e95-b039-c2a81b0c09e5', 4500000, '2025-05-11 17:36:39.019745', 3000000, '1746959780477677', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 6000000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 0, 2, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('787947d9-89c6-48e3-98d4-4e96e19f9882', 4500000, '2025-05-12 12:24:08.659905', 3000000, '1747027429715327', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 50000, '2025-05-12', '2025-05-15', 'Đã xác nhận', 7450000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', 'cdc313'),
('84b42a47-cca1-4e25-a79e-823508c699f5', 4500000, '2025-05-12 02:57:00.597559', 3000000, '1746993420542858', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('9c13ce3d-fd19-4e5c-8e68-35825896ea19', 4500000, '2025-05-12 03:06:44.814120', 3000000, '1746994004761974', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('9ed6254e-6424-4a23-892a-dc34a72c768a', 4500000, '2025-05-12 02:58:51.099847', 3000000, '1746993531036263', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('a163e915-eb0a-4541-83d4-811de2185cdc', 4500000, '2025-05-12 14:57:29.506008', 3000000, '174703663170155', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 50000, '2025-05-05', '2025-05-07', 'Đã xác nhận', 7450000, 'T2025000026', '1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 4, 'PC250003', 'sgfdh', b'1', b'1', 'cdc313'),
('aab20f4d-acf6-4894-8382-b19af092dbb6', 4500000, '2025-05-11 18:37:50.970887', 3000000, '1746963451264474', 'KH2025000002', '32a36d69-4760-4a34-a36d-fe90255f875d', 50000, '2025-05-12', '2025-05-15', 'Đã xác nhận', 14950000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 2, 2, 'PC20250002', 'fgsrs', b'1', b'1', 'cdc313'),
('ab6be4e4-d4bc-46a4-ab65-47122d5ede13', 4500000, '2025-05-11 18:04:59.799232', 3000000, '1746961499753240', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã hủy', 7500000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'0', ''),
('ad354890-d279-476b-92e1-e216b9dc078a', 4500000, '2025-05-12 13:01:58.779046', 3000000, '1747029718717878', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 7500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', ''),
('c4d57ab2-f113-4939-945c-f6730561bd8c', 4500000, '2025-05-12 02:19:38.501378', 3000000, '1746991156258323', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-05', '2025-05-07', 'Đã hủy', 7500000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC4935', 'sfbfad', b'1', b'0', ''),
('c85428af-0f4b-4ba1-83ff-7e30fef88c9b', 4500000, '2025-05-12 17:47:46.219285', 3000000, '1747046821044651', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 25000, '2025-05-05', '2025-05-07', 'Đã hủy', 8975000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 0, 2, 'PC4935', 'sfbfad', b'1', b'0', 'promo-67890'),
('dfe2444f-692b-4668-9096-56d27251a0c4', 4500000, '2025-05-12 03:06:30.065689', 3000000, '1746993990016560', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 4500000, 'T2025000018', '3e925c58-6a2a-484b-9474-948495a191a0', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 1, 0, 2, 'PC20250002', 'fgsrs', b'1', b'1', ''),
('f31ad6d5-b3d2-4621-a59c-5955ab937245', 4500000, '2025-05-11 18:14:21.133526', 3000000, '1746962061092833', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-12', '2025-05-15', 'Đã xác nhận', 7500000, 'T2025000007', '4791d516-fc35-4e65-805e-0002bc8968c5', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 1, 1, 2, 'PC20250002', 'fgsrs', b'1', b'1', '');

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
('02906d9f-9aba-4693-81d9-6c4110a21818', '2025-05-13 19:09:35.294816', '14954563', 'VNPay', 'Đã thanh toán', '730e72e4-9001-4524-a57c-6f587135224b'),
('07b2a6d4-291e-402a-bc2a-d6306ec62d90', '2025-05-12 02:07:50.203822', '', 'Tiền mặt', 'Đã thanh toán', '09b7b719-e0af-45fc-b939-de6309eda32d'),
('11654426-0d2d-4874-b5db-6f433c1281aa', '2025-05-11 17:35:02.178188', '4421458228', 'MoMo', 'Đã thanh toán', '6ed535da-69f2-4918-bb0e-917baee0bfdd'),
('13bd4c38-11fc-438e-af4e-864c1089a170', NULL, '', 'Tiền mặt', 'Chưa thanh toán', '9ed6254e-6424-4a23-892a-dc34a72c768a'),
('13d1b336-7822-4109-ab44-d3f446300c2e', '2025-05-14 01:29:00.159771', '14955166', 'VNPay', 'Đã thanh toán', '3781e768-22b5-4b15-8e40-e8d7512c9bf4'),
('2c3ee42d-6577-4f63-8d62-8aedd013e53e', '2025-05-12 12:25:31.765332', '4422306733', 'MoMo', 'Đã thanh toán', '3e75a32c-2e96-4361-9f7b-80fff3f1f066'),
('302c646c-365f-4cad-a81f-e4583931c153', '2025-05-12 12:58:46.691620', '', 'Tiền mặt', 'Đã thanh toán', '481dd7f7-7291-4633-bfda-429329244719'),
('4d496a97-5aa5-40cc-822d-1c4b39795bd5', '2025-05-12 15:12:15.376938', '14951911', 'VNPay', 'Đã thanh toán', '3def5ff3-3c32-4c56-8e5b-afbbf0cb8dd8'),
('5d782f8c-8785-45e7-bc6f-60919d3ac0aa', '2025-05-11 18:15:40.204408', '', 'Tiền mặt', 'Đã thanh toán', 'f31ad6d5-b3d2-4621-a59c-5955ab937245'),
('6cfc00ee-8f10-4b25-994d-3c00e328c47b', '2025-05-12 03:12:18.963417', '', 'Tiền mặt', 'Đã thanh toán', '9c13ce3d-fd19-4e5c-8e68-35825896ea19'),
('6f051a84-37b1-400e-b4b3-0529aaf137f2', '2025-05-11 18:05:14.997922', '', 'Tiền mặt', 'Đã thanh toán', 'ab6be4e4-d4bc-46a4-ab65-47122d5ede13'),
('7382f87f-bb08-48ed-a7a4-88364a51ce36', NULL, '', 'Tiền mặt', 'Chưa thanh toán', '84b42a47-cca1-4e25-a79e-823508c699f5'),
('8a0958d0-e7fc-4a40-9ca3-9d4ed9fa1ae2', '2025-05-12 14:57:29.393353', '14951852', 'VNPay', 'Đã thanh toán', 'a163e915-eb0a-4541-83d4-811de2185cdc'),
('8a52e673-5ce5-462e-850c-dad7f23d5c03', '2025-05-12 12:18:42.454058', '', 'Tiền mặt', 'Đã thanh toán', '0893c616-f8be-468a-959c-fbf30bcfac0e'),
('8f0dd296-bad8-442f-a5a4-b0d492404c58', '2025-05-11 18:10:39.189089', '', 'Tiền mặt', 'Đã thanh toán', '2f594046-3ae2-4055-8fbc-87a0cf1a1741'),
('98273e38-acb2-4795-bba5-e2db55dd59e7', '2025-05-12 13:02:28.752338', '', 'Tiền mặt', 'Đã thanh toán', 'ad354890-d279-476b-92e1-e216b9dc078a'),
('9d1cb43f-bea3-4a61-b2c7-4f86f5c2dd36', '2025-05-11 17:36:38.977304', '14950330', 'VNPay', 'Đã thanh toán', '7807c70b-9e42-4e95-b039-c2a81b0c09e5'),
('b34e65b5-61b8-49c9-b215-da7be6335cc4', '2025-05-14 00:23:35.076177', '14955097', 'VNPay', 'Đã thanh toán', '5317be13-e5c2-4667-8be4-6988040189e2'),
('be4f5f5b-e3ad-4236-a7d7-32a73d467782', '2025-05-12 12:24:08.572286', '14951491', 'VNPay', 'Đã thanh toán', '787947d9-89c6-48e3-98d4-4e96e19f9882'),
('cfa9964b-5b57-4567-b0a5-0472b7ae1d35', '2025-05-12 02:19:38.121810', '14950895', 'VNPay', 'Đã thanh toán', 'c4d57ab2-f113-4939-945c-f6730561bd8c'),
('d36a3e99-6a37-4774-bee7-bcfadc8f4c06', '2025-05-12 03:42:34.658038', '', 'Tiền mặt', 'Đã thanh toán', '0d5c25e9-c024-4cea-8849-c5b425ce574e'),
('d6996987-cccb-4404-bcfa-d2b94a7ac8f8', NULL, '', 'Tiền mặt', 'Chưa thanh toán', '5ce60fc0-198f-418f-a0ea-8b13abb6a4bf'),
('dd2ebe00-dfe4-4caa-8292-c166b1a28abf', '2025-05-11 18:37:50.905340', '14950398', 'VNPay', 'Đã thanh toán', 'aab20f4d-acf6-4894-8382-b19af092dbb6'),
('e4e272b3-641b-4748-aca9-821e978ec288', '2025-05-12 03:07:28.068333', '', 'Tiền mặt', 'Đã thanh toán', 'dfe2444f-692b-4668-9096-56d27251a0c4'),
('fa48298c-a627-408f-a2cb-b57f35b9fe6d', NULL, '', 'Tiền mặt', 'Chưa thanh toán', '5f9b7eaa-7402-466d-b6b3-c502de455dd8'),
('fad99581-4a99-40c1-8388-ded34774f6aa', '2025-05-12 17:45:44.545854', '14952454', 'VNPay', 'Đã thanh toán', '2462594b-ef52-4c77-bebc-94520c9c073d'),
('fd4a0f69-3983-42ad-b5ae-fff8ffd75c72', '2025-05-12 17:47:46.147203', '4422047910', 'MoMo', 'Đã thanh toán', 'c85428af-0f4b-4ba1-83ff-7e30fef88c9b'),
('fdd76ffb-c60f-4c4c-90f1-9bc26383a0b1', '2025-05-11 17:33:06.612889', '14950326', 'VNPay', 'Đã thanh toán', '068a1cb8-6421-42bb-b712-ed6929abdd09');

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
('1347bc6c-0568-48b3-8a2c-f3c453618348', 'KH2025000001', 'thanhhkh3@gmail.com', 'Trần Thành', '$2a$10$LXwiUvNGPEqAtWZ0V3Bfs.P5Z7xAMLsHqwxKtywoQCtu7TfRAEZ2.', '0825702210', '2025-04-15 01:49:25.033616', 'Đang hoạt động'),
('32a36d69-4760-4a34-a36d-fe90255f875d', 'KH2025000002', 'tranthanh200322@gmail.com', 'Trần Thành', '$2a$10$FYyp7qYo71uCMoDQJ8do1.y1GPOOi05FwwM56e/ANn2iWpLwObQ7m', '0825702211', '2025-04-22 01:51:41.077804', 'Đang hoạt động');

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
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `content` longtext COLLATE utf8mb4_vietnamese_ci,
  `image` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `summary` text COLLATE utf8mb4_vietnamese_ci,
  `time_stamp` datetime(6) DEFAULT NULL,
  `title` varchar(1000) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `view_count` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`id`, `code`, `content`, `image`, `summary`, `time_stamp`, `title`, `type`, `view_count`) VALUES
('12167344-849e-4b80-9fef-6a021029f377', 'N20250009', '<p>🛳️ <strong>Vịnh Hạ Long</strong> &ndash; với hơn 1.600 h&ograve;n đảo lớn nhỏ, những hang động kỳ b&iacute;, v&agrave; mặt nước xanh ngọc b&iacute;ch &ndash; l&agrave; điểm đến l&yacute; tưởng cho mọi du kh&aacute;ch y&ecirc;u thi&ecirc;n nhi&ecirc;n, đam m&ecirc; kh&aacute;m ph&aacute;.</p>\n\n<h3><strong>Th&ocirc;ng tin tour:</strong></h3>\n\n<ul>\n	<li>\n	<p><strong>Thời gian:</strong> 2 ng&agrave;y 1 đ&ecirc;m / 3 ng&agrave;y 2 đ&ecirc;m</p>\n	</li>\n	<li>\n	<p><strong>Phương tiện:</strong> Xe du lịch đời mới &amp; du thuyền sang trọng</p>\n	</li>\n	<li>\n	<p><strong>Lịch tr&igrave;nh nổi bật:</strong></p>\n\n	<ul>\n		<li>\n		<p>Tham quan Động Thi&ecirc;n Cung, hang Sửng Sốt</p>\n		</li>\n		<li>\n		<p>Ch&egrave;o kayak kh&aacute;m ph&aacute; l&agrave;ng ch&agrave;i Vung Vi&ecirc;ng</p>\n		</li>\n		<li>\n		<p>Ngắm ho&agrave;ng h&ocirc;n tr&ecirc;n boong t&agrave;u, thưởng thức tiệc BBQ hải sản</p>\n		</li>\n		<li>\n		<p>Tự do tắm biển tại đảo Titop, check-in với cảnh sắc tuyệt đẹp</p>\n		</li>\n	</ul>\n	</li>\n</ul>\n\n<h3><strong>Ưu đ&atilde;i đặc biệt:</strong></h3>\n\n<ul>\n	<li>\n	<p>Miễn ph&iacute; v&eacute; tham quan vịnh</p>\n	</li>\n	<li>\n	<p>Tặng 01 suất massage thư gi&atilde;n tr&ecirc;n du thuyền</p>\n	</li>\n	<li>\n	<p>Giảm 10% cho nh&oacute;m từ 4 người trở l&ecirc;n</p>\n	</li>\n</ul>\n\n<p>👉 <strong>Đặt tour ngay h&ocirc;m nay</strong> để c&oacute; cơ hội trải nghiệm kỳ nghỉ trong mơ giữa l&ograve;ng di sản thi&ecirc;n nhi&ecirc;n thế giới!</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747306253/saoviet/tg9fyd1gvnznprqgzh8u.webp', 'Bạn đang tìm kiếm một chuyến du lịch thư giãn nhưng vẫn đầy trải nghiệm đáng nhớ? Hãy cùng chúng tôi tham gia Tour du lịch Hạ Long – hành trình đưa bạn đến với một trong những kỳ quan thiên nhiên nổi tiếng thế giới.', '2025-05-15 17:50:44.735823', 'KHÁM PHÁ VẺ ĐẸP KỲ QUAN THIÊN NHIÊN THẾ GIỚI – TOUR DU LỊCH HẠ LONG', 'Thường', 5),
('4106d489-416d-43bc-a086-35983280cf6f', 'N20250006', '<p>&nbsp;</p>\n\n<h2>1. Ph&uacute; Quốc (Ki&ecirc;n Giang)</h2>\n\n<p><img alt=\"Phú Quốc (Kiên Giang)\" src=\"http://localhost:3000/static/media/content-img-1.1364f7051ab1f762b0b9.jpg\" /></p>\n\n<p>Ph&uacute; Quốc &ndash; một trong những địa phương sở hữu nhiều b&atilde;i biển đẹp nhất Việt Nam với khung cảnh thi&ecirc;n nhi&ecirc;n hoang sơ h&ugrave;ng vĩ. Khi đến đ&acirc;y, du kh&aacute;ch kh&ocirc;ng chỉ được chi&ecirc;m ngưỡng những bờ biển đẹp h&uacute;t hồn m&agrave; c&ograve;n xao xuyến bởi sự mến kh&aacute;ch của người d&acirc;n địa phương. M&ugrave;a h&egrave; n&agrave;y ch&iacute;nh l&agrave; thời điểm th&iacute;ch hợp nhất để du kh&aacute;ch c&oacute; thể trải nghiệm được gần như tất cả c&aacute;c dịch vụ du lịch khi đến Ph&uacute; Quốc như tắm biển, m&ograve; san h&ocirc;, cắm trại&hellip;</p>\n\n<h2>2. &quot;Mũi N&eacute; (B&igrave;nh Thuận)</h2>\n\n<p><img alt=\"Mũi Né (Bình Thuận)\" src=\"data:image/jpeg;base64,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\" /></p>\n\n<p>Mũi N&eacute; như được thi&ecirc;n nhi&ecirc;n ưu đ&atilde;i ban tặng cho dải bờ biển d&agrave;i gần 10km. Nơi n&agrave;y nổi tiếng với cồn c&aacute;t trắng mịn, nước biển xanh như ngọc v&agrave; những khu nghỉ dưỡng sang trọng. Mũi N&eacute; l&agrave; địa điểm du lịch nổi tiếng với những hoạt động ngo&agrave;i trời như bơi lội, lướt v&aacute;n diều, đặc biệt l&agrave; trượt c&aacute;t. Nếu du kh&aacute;ch l&agrave; t&iacute;n đồ th&iacute;ch sống ảo, th&iacute;ch check-in th&igrave; Phan Thiết l&agrave; địa điểm kh&ocirc;ng thể bỏ lỡ trong m&ugrave;a h&egrave; n&agrave;y v&igrave; nơi đ&acirc;y c&ograve;n nhiều khu giữ được n&eacute;t hoang sơ, h&ugrave;ng vĩ.</p>\n\n<h2>Lời kết</h2>\n\n<p>Việt Nam tự h&agrave;o c&oacute; nhiều b&atilde;i biển tuyệt đẹp, mỗi nơi đều mang một vẻ đẹp ri&ecirc;ng...</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747290590/saoviet/ezwqlazyp5i5xptitsxy.webp', 'Mùa du lịch đang đến gần, nếu bạn còn đang phân vân không biết nên đi đâu, hãy cùng điểm qua top 5 tour du lịch đang \"gây sốt\" với khung cảnh tuyệt đẹp, lịch trình hấp dẫn và chi phí cực kỳ hợp lý.', '2025-05-15 19:02:09.289245', 'Khám phá 5 bãi biển đẹp nhất Việt Nam năm 2024', 'Nổi bật', 33),
('41c3702b-cecf-4d27-8e70-9c84a5d2e296', 'N20250011', '<p>regerregre</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747306688/saoviet/vyzunn4fbvegwmw6isw5.webp', 'ẻger', '2025-05-15 17:57:59.502174', 'fdhdf', 'Nổi bật', 2),
('5c119645-7058-47b7-8f3d-a71f6103e194', 'N20250002', '<p>H&agrave; Giang từ l&acirc;u đ&atilde; l&agrave; điểm đến y&ecirc;u th&iacute;ch của d&acirc;n phượt v&agrave; những người đam m&ecirc; kh&aacute;m ph&aacute; vẻ đẹp hoang sơ, h&ugrave;ng vĩ. Sau chuyến đi 4 ng&agrave;y 3 đ&ecirc;m, m&igrave;nh muốn chia sẻ lại một số kinh nghiệm để bạn c&oacute; thể chuẩn bị tốt hơn.</p>\n\n<p><strong>1. Thời gian l&yacute; tưởng để đi H&agrave; Giang:</strong></p>\n\n<ul>\n	<li>\n	<p>Th&aacute;ng 10 &ndash; 11: m&ugrave;a hoa tam gi&aacute;c mạch nở rộ.</p>\n	</li>\n	<li>\n	<p>Th&aacute;ng 3 &ndash; 4: m&ugrave;a hoa mận, hoa đ&agrave;o nở trắng rừng.</p>\n	</li>\n	<li>\n	<p>Th&aacute;ng 5 &ndash; 6: m&ugrave;a nước đổ ruộng bậc thang rất đẹp.</p>\n	</li>\n</ul>\n\n<p><strong>2. Phương tiện di chuyển:</strong><br />\nM&igrave;nh đi xe giường nằm đ&ecirc;m từ H&agrave; Nội (bến Mỹ Đ&igrave;nh) l&ecirc;n TP H&agrave; Giang (khoảng 7 tiếng).<br />\nTới nơi, m&igrave;nh thu&ecirc; xe m&aacute;y để đi c&aacute;c điểm, gi&aacute; thu&ecirc; khoảng 150.000 &ndash; 200.000đ/ng&agrave;y.</p>\n\n<p><strong>3. Lịch tr&igrave;nh gợi &yacute; 4 ng&agrave;y 3 đ&ecirc;m:</strong></p>\n\n<ul>\n	<li>\n	<p>Ng&agrave;y 1: TP H&agrave; Giang &ndash; Quản Bạ &ndash; Y&ecirc;n Minh</p>\n	</li>\n	<li>\n	<p>Ng&agrave;y 2: Y&ecirc;n Minh &ndash; Đồng Văn &ndash; M&atilde; P&iacute; L&egrave;ng &ndash; M&egrave;o Vạc</p>\n	</li>\n	<li>\n	<p>Ng&agrave;y 3: M&egrave;o Vạc &ndash; Du Gi&agrave; &ndash; TP H&agrave; Giang</p>\n	</li>\n	<li>\n	<p>Ng&agrave;y 4: Tham quan TP H&agrave; Giang &ndash; về lại H&agrave; Nội</p>\n	</li>\n</ul>\n\n<p><strong>4. Một số lưu &yacute;:</strong></p>\n\n<ul>\n	<li>\n	<p>Mang theo giấy tờ c&aacute; nh&acirc;n đầy đủ: CCCD, GPLX nếu thu&ecirc; xe m&aacute;y.</p>\n	</li>\n	<li>\n	<p>Trời đ&ecirc;m kh&aacute; lạnh, kể cả m&ugrave;a h&egrave;, n&ecirc;n chuẩn bị &aacute;o ấm.</p>\n	</li>\n	<li>\n	<p>N&ecirc;n đổ đầy b&igrave;nh xăng trước khi đi, v&igrave; c&oacute; những đoạn đường d&agrave;i kh&ocirc;ng c&oacute; c&acirc;y xăng.</p>\n	</li>\n	<li>\n	<p>Đường đ&egrave;o kh&aacute; nhiều, n&ecirc;n nếu tay l&aacute;i yếu th&igrave; n&ecirc;n c&acirc;n nhắc đi tour c&oacute; người dẫn.</p>\n	</li>\n</ul>\n\n<p><strong>5. M&oacute;n ăn n&ecirc;n thử:</strong></p>\n\n<ul>\n	<li>\n	<p>Ch&aacute;o ấu tẩu</p>\n	</li>\n	<li>\n	<p>Thắng cố</p>\n	</li>\n	<li>\n	<p>Phở chua</p>\n	</li>\n	<li>\n	<p>Rượu ng&ocirc; người M&ocirc;ng</p>\n	</li>\n</ul>\n\n<p>H&agrave; Giang l&agrave; nơi rất đ&aacute;ng để đi một lần trong đời, kh&ocirc;ng chỉ bởi cảnh đẹp m&agrave; c&ograve;n bởi con người th&acirc;n thiện, văn h&oacute;a độc đ&aacute;o. Ch&uacute;c bạn c&oacute; một chuyến đi an to&agrave;n v&agrave; đầy trải nghiệm!</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747113668/saoviet/ulgyds4nod4mancdbrc9.png', 'Hà Giang – vùng đất địa đầu Tổ quốc không chỉ nổi tiếng với những cung đường đèo uốn lượn mà còn là nơi lưu giữ văn hóa đặc sắc của các dân tộc thiểu số. Bài viết chia sẻ kinh nghiệm thực tế đi Hà Giang tự túc giúp bạn có một hành trình đáng nhớ...', '2025-05-13 12:21:02.091378', 'Kinh Nghiệm Du Lịch Hà Giang – Hành Trình Khám Phá Cao Nguyên Đá', 'Thường', 5),
('ac353ef2-7d2e-48cb-936b-334dc6623d8a', 'N20250010', '<p>safsafASCXSA</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747306590/saoviet/p3vbmumhjb2ujjeaaj0s.webp', 'safas', '2025-05-15 17:58:33.773849', 'asacsa', 'Thường', 9);

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
('172ef318-5e9a-4640-ab28-00f3e0329584', 'Voucher tri ân khách hàng của Sao Việt. Số lượng có hạn và chỉ áp dụng với ngày 16/05 và 18/05. Áp dụng với mọi tour và mọi khách hàng đặt tour tại website Sao Việt.\n', 100000, '2025-05-18', 45, '2025-05-16', 'SAOVIET2025', '2025-05-12 17:26:18.448275', 'Chưa diễn ra', 'Giảm giá 100K'),
('3bfc0b27-fe5a-4dc4-8528-6bae0d8733c5', 'Áp dụng mọi tour.', 20000, '2025-05-12', 9, '2025-05-09', 'KM052025', '2025-05-09 18:29:24.825581', 'Đã kết thúc', 'Giảm giá 25K'),
('ea337371-3976-4c78-b458-663ce51df0fc', 'Dùng nhanh kẻo hết, số lượng có hạn. Áp dụng mọi khách hàng đã đăng ký tài khoản tại Sao Việt và áp dụng với mọi tours.\n', 90000, '2025-05-19', 32, '2025-05-12', 'HEHOT2025', '2025-05-12 17:22:23.801550', 'Đang diễn ra', 'Giảm giá 90K');

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
('d693235d-1bf0-4f7e-88d3-aaf7ba86fba8', '32a36d69-4760-4a34-a36d-fe90255f875d', 4, '2025-04-12 19:48:52.395462', '4791d516-fc35-4e65-805e-0002bc8968c5', 'Tuyệt vời, tôi sẽ đặt tour tại Sao Việt nhiều hơn.'),
('dda3db17-6840-45ff-b554-7bb217174a05', '1347bc6c-0568-48b3-8a2c-f3c453618348', 5, '2025-05-05 09:19:59.978747', '4791d516-fc35-4e65-805e-0002bc8968c5', 'Tuyet voi'),
('wdvftni', 'vgr bh', 5, '2025-04-24 17:14:24.000000', '4791d516-fc35-4e65-805e-0002bc8968c51', 'vgjrgb');

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
('0cf2e10c-2166-4a80-9d40-69d00b1b3a2c', 3000000, 280000, 'LT2025000000007', '2025-05-20', 0, '2025-05-18', 'Chưa diễn ra', 30, '366023a3-0e66-4509-b9d7-a12020b1e8ea', '2025-05-14 02:04:45.988994'),
('ac3ced90-8136-48c4-97f3-d7de764d5f7c', 2500000, 2000000, 'LT2025000000002', '2025-05-19', 4, '2025-05-17', 'Chưa diễn ra', 25, '366023a3-0e66-4509-b9d7-a12020b1e8ea', '2025-05-14 00:19:31.345785'),
('bhjhv', 2500000, 1000000, 'PC20250001', '2025-05-15', 10, '2025-05-13', 'Đang diễn ra', 20, '4791d516-fc35-4e65-805e-0002bc8968c5', '2025-04-23 01:10:27.244000'),
('ee790678-23e0-44f1-af8c-22f50b5ced2c', 4500000, 3800000, 'LT2025000000010', '2025-05-21', 0, '2025-05-18', 'Chưa diễn ra', 20, 'e27515f2-cb63-4836-91a8-1dd3dc9050ba', '2025-05-15 00:05:22.749943'),
('sgfdh', 4500000, 3000000, 'PC250003', '2025-05-16', 14, '2025-05-14', 'Đang diễn ra', 20, '1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', '2025-05-04 18:37:27.000000');

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
(1, 2, 'customer', 2025),
(2, 47, 'tour', 2025),
(3, 11, 'news', 2025),
(4, 12, 'schedule', 2025);

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
('024c3ba2-275b-43d3-83f9-446cea473df0', '2025-05-11 21:09:14'),
('1b64f359-e605-48e2-ac56-0db9e0956502', '2025-05-11 19:17:24'),
('842e18c4-a10b-4d7a-8b48-8a826c98a2e7', '2025-05-09 16:43:08'),
('8759723c-6f70-4c71-a20d-0ded3b7e1c11', '2025-05-11 18:32:22'),
('8e92ec9e-6dc1-4b28-9f1e-b1286fd1fac0', '2025-05-09 16:47:26'),
('97f66e24-0609-4e99-9437-ae9205da4e03', '2025-05-09 16:06:38'),
('a411c967-a5db-4c03-9028-2488fcd07890', '2025-05-06 12:59:40'),
('a7e385ba-11d3-4942-9d3d-65b37c70f540', '2025-05-12 14:57:37'),
('bbe42be1-8b75-4044-91a1-d9f01b5c4dc8', '2025-05-09 15:53:34'),
('bde1218d-90e2-4610-b257-057467f2fe1c', '2025-05-06 13:35:10');

-- --------------------------------------------------------

--
-- Table structure for table `tour`
--

CREATE TABLE `tour` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `description` longtext COLLATE utf8mb4_vietnamese_ci,
  `destination` varchar(500) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(500) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `quantity_day` int DEFAULT NULL,
  `quantity_order` int DEFAULT NULL,
  `area` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `tour`
--

INSERT INTO `tour` (`id`, `code`, `description`, `destination`, `name`, `quantity_day`, `quantity_order`, `area`, `time_stamp`) VALUES
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'T2025000026', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Hà Nội - Quảng Ninh - Hạ Long', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 4, 2, 'b', '2025-05-15 11:11:43.048101'),
('366023a3-0e66-4509-b9d7-a12020b1e8ea', 'T2025000027', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Huế - Nha Trang', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 2, 1, 't', '2025-05-09 10:08:42.252212'),
('3e925c58-6a2a-484b-9474-948495a191a0', 'T2025000018', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Quảng Ninh - Hà Giang', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 2, 5, 'b', '2025-05-09 10:00:20.748616'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 'T2025000007', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Hà Nội - Quảng Ninh', 'DU LỊCH HẠ LONG 2 NGÀY - 1 ĐÊM', 2, 6, 'b', '2025-05-15 13:25:03.797787'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 'T2025000016', 'Hành trình 3 ngày khám phá vùng núi và biển đẹp nhất miền Bắc Việt Nam.', 'Hà Nội - Nha Trang', 'DU LỊCH NHA TRANG 6 NGÀY - 5 ĐÊM', 4, 0, 't', '2025-05-15 13:25:26.610234'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 'T2025000029', '<p><strong>&nbsp; &nbsp; &nbsp;Tour Đ&agrave; Nẵng &ndash; KDL B&agrave; N&agrave; &ndash; Phố Cổ Hội An &ndash; B&aacute;n Đảo Sơn Tr&agrave;</strong> l&agrave; h&agrave;nh tr&igrave;nh l&yacute; tưởng đưa du kh&aacute;ch kh&aacute;m ph&aacute; những điểm đến nổi bật nhất của miền Trung.</p>\n\n<p>&nbsp; &nbsp; &nbsp;Khởi h&agrave;nh từ th&agrave;nh phố biển <strong>Đ&agrave; Nẵng</strong> hiện đại, tour đưa bạn l&ecirc;n đỉnh <strong>B&agrave; N&agrave; Hills</strong> để chi&ecirc;m ngưỡng <strong>Cầu V&agrave;ng</strong> độc đ&aacute;o giữa m&acirc;y trời, vui chơi thỏa th&iacute;ch tại <strong>Fantasy Park</strong> &ndash; khu vui chơi trong nh&agrave; lớn nhất Việt Nam.</p>\n\n<p>&nbsp; &nbsp; &nbsp;Tiếp theo l&agrave; chuyến tham quan <strong>phố cổ Hội An</strong> &ndash; di sản văn h&oacute;a thế giới với những ng&ocirc;i nh&agrave; cổ k&iacute;nh, đ&egrave;n lồng rực rỡ v&agrave; nền ẩm thực đặc sắc, n&iacute;u ch&acirc;n biết bao du kh&aacute;ch.</p>\n\n<p>&nbsp; &nbsp; &nbsp;Cuối h&agrave;nh tr&igrave;nh, bạn sẽ được tận hưởng kh&ocirc;ng kh&iacute; trong l&agrave;nh tr&ecirc;n <strong>B&aacute;n Đảo Sơn Tr&agrave;</strong>, gh&eacute; thăm <strong>ch&ugrave;a Linh Ứng</strong> &ndash; nơi c&oacute; tượng <strong>Phật B&agrave; Quan &Acirc;m</strong> cao nhất Việt Nam, đồng thời ngắm to&agrave;n cảnh th&agrave;nh phố Đ&agrave; Nẵng từ tr&ecirc;n cao.</p>\n\n<p>&nbsp; &nbsp; &nbsp;Với <strong>lịch tr&igrave;nh hấp dẫn</strong>, <strong>dịch vụ chất lượng</strong> v&agrave; <strong>mức gi&aacute; hợp l&yacute;</strong>, đ&acirc;y chắc chắn l&agrave; tour kh&ocirc;ng thể bỏ lỡ cho kỳ nghỉ sắp tới!</p>\n', 'Đà Nẵng - Hội An – Sơn Trà', 'Đà Nẵng – KDL Bà Nà - Phố Cổ Hội An – Bán Đảo Sơn Trà', 3, 0, 't', '2025-05-15 10:20:33.689785');

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
('3e925c58-6a2a-484b-9474-948495a191a0', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('3e925c58-6a2a-484b-9474-948495a191a0', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('366023a3-0e66-4509-b9d7-a12020b1e8ea', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('366023a3-0e66-4509-b9d7-a12020b1e8ea', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747242126/saoviet/wz2ycfuynvbb3k7y1hcc.webp'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747242126/saoviet/xmakzxdntv7yfgsqmakc.webp'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747279220/saoviet/vxye71xdyaxecm2wjhi2.webp'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747279220/saoviet/qwhni1zim05ykh6k2o6l.webp'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747290300/saoviet/yg3d01bitzgixvsca9t5.jpg'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854199/saoviet/o3tufqj6ama66vekmdpy.jpg'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg');

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
('3e925c58-6a2a-484b-9474-948495a191a0', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('3e925c58-6a2a-484b-9474-948495a191a0', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('366023a3-0e66-4509-b9d7-a12020b1e8ea', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('366023a3-0e66-4509-b9d7-a12020b1e8ea', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 1, '<p><strong>Qu&yacute; kh&aacute;ch tự t&uacute;c tập trung tại S&acirc;n bay Cần Thơ</strong>&nbsp;- Ga đi trong nước. Hướng dẫn vi&ecirc;n hỗ trợ l&agrave;m thủ tục cho Qu&yacute; kh&aacute;ch đ&aacute;p chuyến bay đi&nbsp;<strong>Đ&agrave; Nẵng.</strong>&nbsp;Tại s&acirc;n bay&nbsp;<strong>Đ&agrave; Nẵng</strong>&nbsp;xe v&agrave; HDV Vietravel&nbsp;đ&oacute;n đo&agrave;n&nbsp;đi ăn chiều, sau đ&oacute; đưa đo&agrave;n&nbsp;về nhận ph&ograve;ng kh&aacute;ch sạn nghỉ ngơi.</p>\n\n<p>Buổi tối qu&yacute; kh&aacute;ch tự do dạo phố ngắm nh&igrave;n sự lung linh v&agrave; ph&aacute;t triển của th&agrave;nh phố.</p>\n\n<p>*&nbsp;<strong>Hoặc Qu&yacute; kh&aacute;ch c&oacute; thể trải nghiệm du thuyền Poseidon sang trọng bậc nhất s&ocirc;ng H&agrave;n&nbsp;</strong>(chi phi tự t&uacute;c)</p>\n\n<p><strong>- Chi&ecirc;m ngưỡng khung cảnh m&ecirc;</strong>&nbsp;hoặc của H&agrave;n Giang về đ&ecirc;m c&ugrave;ng những c&acirc;y cầu mang t&iacute;nh biểu tượng, m&atilde;n nh&atilde;n với&nbsp;<strong>m&agrave;n tr&igrave;nh diễn rồng phun lửa</strong>&nbsp;từ g&oacute;c nh&igrave;n độc đ&aacute;o v&agrave;o dịp cuối tuần.</p>\n\n<p>-&nbsp;<strong>Đặc biệt</strong>, thưởng thức những&nbsp;<strong>m&agrave;n tr&igrave;nh diễn ph&aacute;o hoa rực rỡ v&agrave; bữa tiệc &acirc;m nhạc</strong>&nbsp;- ẩm thực đỉnh cao v&agrave;o những dịp sự kiện lớn.</p>\n', 'Đà Nẵng - Chùa Linh Ứng - Cần Thơ'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 2, '<p>D&ugrave;ng bữa s&aacute;ng tại kh&aacute;ch sạn, Qu&yacute; kh&aacute;ch tự do<strong>&nbsp;tắm biển Mỹ Kh&ecirc;&nbsp;</strong>một trong những b&atilde;i biển quyến rũ nhất h&agrave;nh tinh, qu&yacute; kh&aacute;ch tự do dạo biển, chụp h&igrave;nh....</p>\n\n<p>Sau khi tắm biến, xe v&agrave; HDV sẽ đưa đo&agrave;n đi tham quan:</p>\n\n<p>-&nbsp;<strong>Khu du lịch B&agrave; N&agrave;&nbsp;</strong>(<strong>chi ph&iacute; c&aacute;p treo &amp; Ăn trưa tự t&uacute;c</strong>): tận hưởng kh&ocirc;ng kh&iacute; se lạnh của Đ&agrave; Lạt tại miền Trung, đo&agrave;n tự do tham quan Ch&ugrave;a Linh Ứng, Hầm Rượu Debay, vườn hoa Le Jardin D&rsquo;Amour, Khu T&acirc;m linh mới của B&agrave; N&agrave; viếng Đền Lĩnh Ch&uacute;a Linh Từ, khu vui chơi Fantasy Park, tự do chụp h&igrave;nh tại&nbsp;<strong>Cầu V&agrave;ng điểm tham quan mới si&ecirc;u hot tại B&agrave; N&agrave;</strong>&hellip;Ăn trưa tại B&agrave; N&agrave; tự t&uacute;c. Sau đ&oacute; đo&agrave;n tiếp tục tham quan vui chơi đến giờ xuống c&aacute;p.</p>\n\n<p>-&nbsp;<strong>Phố Cổ Hội An</strong>: Ch&ugrave;a Cầu, Nh&agrave; Cổ Ph&ugrave;ng Hưng, Hội Qu&aacute;n Phước Kiến, Cơ sở Thủ C&ocirc;ng Mỹ Nghệ,&hellip;Qu&yacute; kh&aacute;ch tự do dạo phố đ&egrave;n lồng đầy m&agrave;u sắc, cảm nhận sự y&ecirc;n b&igrave;nh cổ k&iacute;nh v&agrave; l&atilde;ng mạn Phố Cổ về đ&ecirc;m,...</p>\n\n<p>Buổi tối Qu&yacute; kh&aacute;ch tự t&uacute;c đi dạo phố, thưởng thức đặc sản địa phương; thưởng ngoạn cảnh đẹp của Đ&agrave; Nẵng về đ&ecirc;m, ngắm nh&igrave;n Cầu Rồng, Cầu T&igrave;nh Y&ecirc;u, Cầu Trần Thị L&yacute;, Trung T&acirc;m Thương Mại, Khu phố ẩm thực, Caf&eacute; - Bar - Disco&hellip;</p>\n', 'KDL Bà Nà – Phố Cổ Hội An – Đà Nẵng'),
('e27515f2-cb63-4836-91a8-1dd3dc9050ba', 3, '<p>D&ugrave;ng bữa s&aacute;ng tại kh&aacute;ch sạn. Qu&yacute; kh&aacute;ch tự do dạo phố cảm nhận kh&ocirc;ng kh&iacute; trong l&agrave;nh v&agrave; khung cảnh y&ecirc;n b&igrave;nh tại&nbsp;<strong>Đ&agrave; Nẵng</strong>&nbsp;v&agrave;o s&aacute;ng sớm. Xe đưa đo&agrave;n đi tham quan:</p>\n\n<p>-&nbsp;<strong>B&aacute;n đảo Sơn Tr&agrave; v&agrave; Viếng Ch&ugrave;a Linh Ứng:</strong>&nbsp;Nơi đ&acirc;y c&oacute; tượng&nbsp;<strong>Phật Quan Thế &Acirc;m</strong>&nbsp;cao nhất Việt Nam, đứng nơi đ&acirc;y, Qu&yacute; kh&aacute;ch sẽ được chi&ecirc;m ngưỡng to&agrave;n cảnh th&agrave;nh phố, n&uacute;i rừng v&agrave; biển đảo Sơn Tr&agrave; một c&aacute;ch ho&agrave;n hảo nhất.</p>\n\n<p>-&nbsp;<strong>Ngũ H&agrave;nh Sơn:</strong>&nbsp;Động T&agrave;ng Chơn, Động Hoa Nghi&ecirc;m, Ch&ugrave;a Non Nước, L&agrave;ng Đ&aacute; Mỹ Nghệ,..</p>\n\n<p><strong>-&nbsp;</strong>Mua sắm đặc sản phố biển<strong>&nbsp;Qu&agrave; Miền Trung</strong>&nbsp;với hơn 2000 sản phẩm đặc sản&nbsp;<strong>Đ&agrave; Nẵng, Miền Trung</strong>&nbsp;v&agrave; c&aacute;c v&ugrave;ng miền l&acirc;n cận, nổi bật nhất l&agrave; chả b&ograve;, b&ograve; kh&ocirc;, mực rim me, mực kh&ocirc;, ghẹ sữa sốt v&agrave; đầy đủ c&aacute;c loại kh&ocirc; c&aacute;,...</p>\n\n<p>Tới giờ bay, HDV v&agrave; xe đưa Qu&yacute; kh&aacute;ch ra s&acirc;n bay Đ&agrave; Nẵng đ&oacute;n chuyến bay<strong>&nbsp;</strong>trở về Cần Thơ. Chia tay Qu&yacute; kh&aacute;ch v&agrave; kết th&uacute;c chương tr&igrave;nh du lịch tại s&acirc;n bay&nbsp;<strong>Cần Thơ.</strong></p>\n\n<p>&nbsp;</p>\n\n<p><strong>KẾT TH&Uacute;C CHƯƠNG TR&Igrave;NH, TẠM BIỆT QU&Yacute; KH&Aacute;CH!</strong></p>\n\n<p>------------------o0o0o0o-----------------</p>\n\n<p>*<strong>Lưu &yacute;:</strong></p>\n\n<p>- Giờ bay phụ thuộc v&agrave;o h&atilde;ng h&agrave;ng kh&ocirc;ng, số bữa ăn phụ thuộc v&agrave;o giờ bay. C&aacute;c bữa ăn c&oacute; thể ho&aacute;n đổi để ph&ugrave; hợp với chương tr&igrave;nh.</p>\n\n<p>- H&agrave;nh tr&igrave;nh c&oacute; thể thay đổi thứ tự điểm đến t&ugrave;y v&agrave;o điều kiện thực tế</p>\n\n<p>- Lịch tr&igrave;nh tham quan (tắm biển, ngắm hoa, trải nghiệm,...) rất dễ bị ảnh hưởng bởi thời tiết. Đ&acirc;y l&agrave; trường hợp bất khả kh&aacute;ng mong Qu&yacute; kh&aacute;ch hiểu v&agrave; th&ocirc;ng cảm.</p>\n\n<p>- Kh&aacute;ch Sạn c&oacute; thể ở xa trung t&acirc;m th&agrave;nh phố v&agrave;o c&aacute;c m&ugrave;a Cao Điểm</p>\n', 'Cần Thơ - Đà Nẵng - Tự Do Khám Phá Đà Nẵng Về Đêm'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 3, '<p>vcsdvsd</p>\n', 'xzvcxz'),
('1cd1856a-e0f2-439d-ad1b-0e9aae2d055c', 4, '<p>svdv</p>\n', 'sdvds'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('4791d516-fc35-4e65-805e-0002bc8968c5', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 1, 'Tham quan Lăng Bác, Hồ Gươm, Văn Miếu Quốc Tử Giám.', 'Khám phá Hà Nội'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 2, 'Du thuyền tham quan vịnh, hang Sửng Sốt, Titop.', 'Vịnh Hạ Long'),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 3, '', ''),
('b0cd93db-5560-440a-a0b9-40b7b62acc32', 4, '', '');

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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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

CREATE DEFINER=`root`@`%` EVENT `delete_expired_tokens` ON SCHEDULE EVERY 1 DAY STARTS '2025-05-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM token WHERE expiry_time < NOW()$$

CREATE DEFINER=`root`@`%` EVENT `update_status_promotion` ON SCHEDULE EVERY 1 DAY STARTS '2025-05-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE promotion
SET status = CASE
    WHEN start_date <= NOW() AND end_date >= NOW() THEN 'Đang diễn ra'
    WHEN end_date < NOW() THEN 'Đã kết thúc'
    ELSE 'Chưa diễn ra'
END$$

CREATE DEFINER=`root`@`%` EVENT `update_status_schedule` ON SCHEDULE EVERY 1 DAY STARTS '2025-05-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE schedule
SET status = CASE
    WHEN start_date <= NOW() AND end_date >= NOW() THEN 'Đang diễn ra'
    WHEN end_date < NOW() THEN 'Đã kết thúc'
    ELSE 'Chưa diễn ra'
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
