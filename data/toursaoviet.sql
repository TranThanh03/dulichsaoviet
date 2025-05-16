-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 16, 2025 at 10:30 AM
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
('012ecef1-febb-4611-b2b2-5ef53b93f6b4', 4000000, '2025-05-16 01:42:45.024767', 3200000, '1747334545827587', 'KH2025000002', '32a36d69-4760-4a34-a36d-fe90255f875d', 0, '2025-05-21', '2025-05-19', 'Đã xác nhận', 11200000, 'T2025000001', '2a851424-9745-4742-9c1e-60eca98c398e', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 2, 1, 3, 'LT2025000000001', 'f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', b'1', b'1', ''),
('358d5681-9396-4dbc-95be-02126da36f90', 4000000, '2025-05-16 13:53:22.252914', 3200000, '1747378335288306', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-21', '2025-05-19', 'Đã xác nhận', 26400000, 'T2025000001', '2a851424-9745-4742-9c1e-60eca98c398e', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 5, 2, 3, 'LT2025000000001', 'f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', b'1', b'1', ''),
('ae2222fd-49d2-4489-89c3-bcea40bf4724', 4000000, '2025-05-16 01:38:58.365183', 3200000, '1747334309928697', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-21', '2025-05-19', 'Đã xác nhận', 14400000, 'T2025000001', '2a851424-9745-4742-9c1e-60eca98c398e', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 2, 2, 3, 'LT2025000000001', 'f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', b'1', b'1', ''),
('bc8726d2-6515-4d8b-b9d3-9e8b3478d50a', 4000000, '2025-05-16 01:45:29.650869', 3200000, '1747334729557845', 'KH2025000001', '1347bc6c-0568-48b3-8a2c-f3c453618348', 0, '2025-05-21', '2025-05-19', 'Đã hủy', 26400000, 'T2025000001', '2a851424-9745-4742-9c1e-60eca98c398e', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 5, 2, 3, 'LT2025000000001', 'f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', b'1', b'0', ''),
('fe6341d0-e958-44d6-a238-7121614d84aa', 4000000, '2025-05-16 13:55:38.218300', 3200000, '1747378496396759', 'KH2025000002', '32a36d69-4760-4a34-a36d-fe90255f875d', 0, '2025-05-21', '2025-05-19', 'Đã hủy', 7200000, 'T2025000001', '2a851424-9745-4742-9c1e-60eca98c398e', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 1, 1, 3, 'LT2025000000001', 'f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', b'1', b'0', '');

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
('7d13f47a-3670-4158-a497-6b76f4350c57', '2025-05-16 01:38:58.314932', '14959847', 'VNPay', 'Đã thanh toán', 'ae2222fd-49d2-4489-89c3-bcea40bf4724'),
('910b024d-d265-425f-a8ab-e56fa67178e2', '2025-05-16 13:55:38.176323', '4429329779', 'MoMo', 'Đã thanh toán', 'fe6341d0-e958-44d6-a238-7121614d84aa'),
('b423c9c6-c3e1-4660-bb48-9000f0a27b6a', NULL, '', 'Tiền mặt', 'Chưa thanh toán', 'bc8726d2-6515-4d8b-b9d3-9e8b3478d50a'),
('bee63f33-1209-483d-874e-342d5e5d7ea2', '2025-05-16 01:42:44.974921', '14959849', 'VNPay', 'Đã thanh toán', '012ecef1-febb-4611-b2b2-5ef53b93f6b4'),
('c74e5e07-6c1f-4fc8-bac6-8ed85d500703', '2025-05-16 13:53:22.201163', '4429329762', 'MoMo', 'Đã thanh toán', '358d5681-9396-4dbc-95be-02126da36f90');

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
('c81ee2c8-ca1c-4b4f-987c-97bd56ed34ec', 'N20250001', '<h2><strong>1. Du lịch Đ&agrave; Lạt tự t&uacute;c th&aacute;ng mấy đẹp?</strong></h2>\n\n<p>Bạn đang thắc mắc th&aacute;ng mấy l&agrave; thời điểm tuyệt vời để bắt đầu h&agrave;nh tr&igrave;nh du lịch Đ&agrave; Lạt tự t&uacute;c? Đặc trưng bởi vị tr&iacute; địa l&yacute; thuộc v&ugrave;ng cao của T&acirc;y Nguy&ecirc;n,&nbsp;<strong><a href=\"https://vinpearl.com/vi/thoi-tiet-da-lat\" target=\"_blank\">thời tiết Đ&agrave; Lạt</a></strong>&nbsp;quanh năm m&aacute;t mẻ n&ecirc;n bạn c&oacute; thể gh&eacute; thăm nơi đ&acirc;y bất kỳ m&ugrave;a n&agrave;o trong năm.&nbsp;</p>\n\n<ul>\n	<li>Từ th&aacute;ng 1 - 4 l&agrave; m&ugrave;a xu&acirc;n. L&uacute;c n&agrave;y, phố n&uacute;i rực rỡ sắc m&agrave;u với những lo&agrave;i hoa như mai anh đ&agrave;o, ban trắng, phượng t&iacute;m&hellip; tạo n&ecirc;n bức tranh thi&ecirc;n nhi&ecirc;n tuyệt đẹp.</li>\n	<li>M&ugrave;a h&egrave; k&eacute;o d&agrave;i từ th&aacute;ng 5 - 9, l&agrave; m&ugrave;a cao điểm du lịch ở đ&acirc;y. Tuy nhi&ecirc;n, đ&acirc;y cũng l&agrave; m&ugrave;a mưa tại Đ&agrave; Lạt, n&ecirc;n nếu du lịch m&ugrave;a n&agrave;y, bạn h&atilde;y nhớ mang theo &aacute;o mưa, &ocirc;.</li>\n	<li>V&agrave;o khoảng th&aacute;ng 10 - 12,&nbsp;<strong><a href=\"https://vinpearl.com/vi/gioi-thieu-ve-thanh-pho-da-lat\" target=\"_blank\">th&agrave;nh phố Đ&agrave; Lạt</a></strong>&nbsp;chuyển sang m&ugrave;a kh&ocirc; v&agrave; lạnh. Du kh&aacute;ch tới đ&acirc;y v&agrave;o m&ugrave;a n&agrave;y sẽ c&oacute; cơ hội ngắm hoa d&atilde; quỳ nở rực v&agrave;ng, khung cảnh m&acirc;y trắng phủ k&iacute;n n&uacute;i đồi&hellip;</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-2_1700543275.jpg\" /></p>\n\n<h2><strong>2. Review đi Đ&agrave; Lạt tự t&uacute;c bằng phương tiện g&igrave;?&nbsp;</strong></h2>\n\n<p>Hầu hết du kh&aacute;ch từ c&aacute;c tỉnh miền Bắc thường chọn m&aacute;y bay để di chuyển thẳng tới Đ&agrave; Lạt. Trong khi đ&oacute;, c&aacute;c du kh&aacute;ch ở khu vực ph&iacute;a Nam c&oacute; thể lựa chọn đi m&aacute;y bay hoặc &ocirc; t&ocirc; để tới Đ&agrave; Lạt.</p>\n\n<p>Tại th&agrave;nh phố ng&agrave;n hoa, để di chuyển giữa c&aacute;c điểm tham quan, du kh&aacute;ch c&oacute; thể&nbsp;<strong><a href=\"https://vinpearl.com/vi/thue-xe-may-da-lat\" target=\"_blank\">thu&ecirc; xe m&aacute;y Đ&agrave; Lạt</a></strong>, &ocirc; t&ocirc;,&nbsp;<strong><a href=\"https://vinpearl.com/vi/taxi-da-lat\" target=\"_blank\">taxi Đ&agrave; Lạt</a></strong>&nbsp;hoặc sử dụng c&aacute;c ứng dụng gọi xe c&ocirc;ng nghệ. Gi&aacute; thu&ecirc; xe m&aacute;y kh&aacute; phải chăng, khoảng từ 120.000 VNĐ/ng&agrave;y v&agrave; gi&aacute; thu&ecirc; &ocirc; t&ocirc; tự l&aacute;i l&agrave; từ 700.000 VNĐ/ng&agrave;y.</p>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-3_1700543224.jpg\" /></p>\n\n<h2><strong>3. Kh&aacute;ch sạn, nh&agrave; nghỉ, homestay Đ&agrave; Lạt n&ecirc;n chọn&nbsp;</strong></h2>\n\n<p>V&igrave; l&agrave; điểm đến du lịch nổi tiếng n&ecirc;n Đ&agrave; Lạt c&oacute; v&ocirc; v&agrave;n địa điểm lưu tr&uacute;, từ b&igrave;nh d&acirc;n, tầm trung 2 - 3 sao, đến những khu resort sang trọng. Tham khảo kinh nghiệm du lịch Đ&agrave; Lạt tự t&uacute;c, một số kh&aacute;ch sạn, resort nổi tiếng m&agrave; bạn c&oacute; thể lựa chọn như SAM Tuyền L&acirc;m Resort, Kings Hotel Dalat, B&igrave;nh An Village Resort Đ&agrave; Lạt...&nbsp;</p>\n\n<p>C&aacute;c kh&aacute;ch sạn, resort n&agrave;y đều được đ&aacute;nh gi&aacute; từ 4, 5 sao trở l&ecirc;n, mang đến cho du kh&aacute;ch kh&ocirc;ng gian lưu tr&uacute; hiện đại, tiện nghi, thoải m&aacute;i. Gi&aacute; cả của c&aacute;c kh&aacute;ch sạn, resort sẽ t&ugrave;y thuộc v&agrave;o từng hạng ph&ograve;ng bạn chọn.</p>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-4_1700543208.jpg\" /></p>\n\n<h2><strong>4. Bản đồ du lịch Đ&agrave; Lạt tự t&uacute;c theo cung đường</strong></h2>\n\n<p>Trong chuyến du lịch Đ&agrave; Lạt tự t&uacute;c ngắn ng&agrave;y, l&agrave;m thế n&agrave;o để kh&aacute;m ph&aacute; tất tần tật c&aacute;c&nbsp;<strong><a href=\"https://vinpearl.com/vi/dia-diem-du-lich-da-lat\" target=\"_blank\">địa điểm du lịch Đ&agrave; Lạt</a></strong>? Dưới đ&acirc;y l&agrave;&nbsp;<strong><a href=\"https://vinpearl.com/vi/ban-do-da-lat\" target=\"_blank\">bản đồ Đ&agrave; Lạt</a></strong>&nbsp;chi tiết theo từng cung đường để bạn c&oacute; thể sắp xếp lịch tr&igrave;nh hợp l&yacute;, tiết kiệm thời gian.</p>\n\n<h3><strong>4.1. C&aacute;c địa điểm du lịch quanh hồ Xu&acirc;n Hương</strong></h3>\n\n<p>Nếu muốn tham quan c&aacute;c địa điểm quanh hồ Xu&acirc;n Hương, bạn c&oacute; thể sắp xếp lịch tr&igrave;nh du lịch Đ&agrave; Lạt tự t&uacute;c như sau: Chợ Đ&agrave; Lạt - Hồ Xu&acirc;n Hương - Vườn hoa th&agrave;nh phố - Quảng Trường L&acirc;m Vi&ecirc;n.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/ho-xuan-huong-da-lat\" target=\"_blank\">Hồ Xu&acirc;n Hương</a></strong>: Đ&acirc;y l&agrave; hồ tự nhi&ecirc;n tại trung t&acirc;m th&agrave;nh phố Đ&agrave; Lạt. Đến đ&acirc;y, bạn c&oacute; thể dạo quanh hồ, thư gi&atilde;n, tận hưởng kh&ocirc;ng gian y&ecirc;n b&igrave;nh, cảnh quan tuyệt đẹp.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/cho-da-lat\" target=\"_blank\">Chợ Đ&agrave; Lạt</a></strong>: Tới đ&acirc;y, bạn c&oacute; thể thoải m&aacute;i mua sắm quần &aacute;o, đồ lưu niệm, thưởng thức c&aacute;c m&oacute;n ăn vặt như b&aacute;nh tr&aacute;ng nướng, sữa đậu n&agrave;nh&hellip;</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/vuon-hoa-thanh-pho-da-lat\" target=\"_blank\">Vườn hoa th&agrave;nh phố</a></strong>: Đ&acirc;y l&agrave; nơi quy tụ nhiều lo&agrave;i hoa xinh đẹp, đặc trưng của Đ&agrave; Lạt như hoa cẩm t&uacute; cầu, hoa mimosa, hoa hồng, đỗ quy&ecirc;n&hellip;</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/quang-truong-lam-vien\" target=\"_blank\">Quảng trường L&acirc;m Vi&ecirc;n</a></strong>: Nơi đ&acirc;y c&oacute; kh&ocirc;ng gian rộng c&ugrave;ng c&aacute;c c&ocirc;ng tr&igrave;nh kiến tr&uacute;c độc đ&aacute;o, ấn tượng như Nụ hoa Atiso, B&ocirc;ng hoa d&atilde; quỳ&hellip;</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-5_1700543192.jpg\" /></p>\n\n<h3><strong>4.2. C&aacute;c điểm đến theo hướng đi hồ Tuyền L&acirc;m</strong></h3>\n\n<p>Theo hướng đi hồ Tuyền L&acirc;m, bạn c&oacute; thể l&ecirc;n lịch tr&igrave;nh du lịch Đ&agrave; Lạt tự t&uacute;c như sau: Biệt thự Hằng Nga - Thiền viện Tr&uacute;c L&acirc;m - Hồ Tuyền L&acirc;m - Đường Hầm Đi&ecirc;u Khắc.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/biet-thu-hang-nga\" target=\"_blank\">Biệt thự Hằng Nga</a></strong>: Nơi đ&acirc;y c&ograve;n được biết đến với c&aacute;i t&ecirc;n &ldquo;ng&ocirc;i nh&agrave; đi&ecirc;n&rdquo;. C&ocirc;ng tr&igrave;nh n&agrave;y c&oacute; kiến tr&uacute;c kỳ qu&aacute;i, độc đ&aacute;o, v&ocirc; c&ugrave;ng ấn tượng.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/thien-vien-truc-lam-da-lat\" target=\"_blank\">Thiền viện Tr&uacute;c L&acirc;m Đ&agrave; Lạt</a></strong>: Đ&acirc;y l&agrave; thiền viện lớn nhất L&acirc;m Đồng. Nơi n&agrave;y c&oacute; kh&ocirc;ng gian y&ecirc;n tĩnh, cảnh quan tuyệt đẹp với hồ Tuyền L&acirc;m v&agrave; n&uacute;i non bao quanh.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/ho-tuyen-lam-da-lat\" target=\"_blank\">Hồ Tuyền L&acirc;m</a></strong>: Hồ rộng 350ha, xung quanh l&agrave; cảnh quan rừng th&ocirc;ng cực đẹp, nước hồ xanh ngọc b&iacute;ch. Nơi đ&acirc;y được nhiều bạn y&ecirc;u th&iacute;ch gh&eacute; đến tham quan, chụp ảnh.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/duong-ham-dieu-khac\" target=\"_blank\">Đường Hầm Đi&ecirc;u Khắc</a></strong>: Đ&acirc;y l&agrave; khu du lịch độc đ&aacute;o tại Đ&agrave; Lạt với nhiều c&ocirc;ng tr&igrave;nh đi&ecirc;u khắc, m&ocirc; phỏng ga Đ&agrave; Lạt, nh&agrave; thờ Con G&agrave;&hellip;</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-6_1700543169.jpg\" /></p>\n\n<h3><strong>4.3. C&aacute;c điểm đến theo hướng đi Suối V&agrave;ng - L&agrave;ng C&ugrave; Lần</strong></h3>\n\n<p>C&ograve;n đi theo hướng Suối V&agrave;ng - L&agrave;ng C&ugrave; Lần, du kh&aacute;ch c&oacute; thể sắp xếp lịch tr&igrave;nh tham quan như sau: Đỉnh Langbiang - Thung Lũng V&agrave;ng - Ma Rừng Lữ Qu&aacute;n - L&agrave;ng C&ugrave; Lần - Đồi cỏ hồng.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/dinh-langbiang-da-lat\" target=\"_blank\">Đỉnh Langbiang</a></strong>: Đ&acirc;y l&agrave; nơi bạn c&oacute; thể ngắm nh&igrave;n to&agrave;n cảnh th&agrave;nh phố, chi&ecirc;m ngưỡng cao nguy&ecirc;n h&ugrave;ng vĩ, tham gia nhiều hoạt động th&uacute; vị như cưỡi ngựa, đi xe jeep&hellip;</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/thung-lung-vang-da-lat\" target=\"_blank\">Thung Lũng V&agrave;ng</a></strong>: Nơi đ&acirc;y nổi tiếng bởi khung cảnh thi&ecirc;n nhi&ecirc;n tươi đẹp với những vườn hoa tươi thắm, d&ograve;ng suối trong xanh&hellip;</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/ma-rung-lu-quan\" target=\"_blank\">Ma Rừng Lữ Qu&aacute;n</a></strong>: Đ&acirc;y l&agrave; một điểm dừng ch&acirc;n c&oacute; cảnh quan thi&ecirc;n nhi&ecirc;n non nước hữu t&igrave;nh, vừa y&ecirc;n b&igrave;nh vừa pha ch&uacute;t ma mị, heo h&uacute;t giữa n&uacute;i rừng.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/lang-cu-lan-da-lat\" target=\"_blank\">L&agrave;ng C&ugrave; Lần</a></strong>: Ng&ocirc;i l&agrave;ng xinh đẹp của người d&acirc;n tộc K&#39;Ho n&agrave;y nằm ở giữa rừng gi&agrave; nguy&ecirc;n sinh. L&agrave;ng rộng khoảng 30ha, được c&ocirc;ng nhận l&agrave; 1 trong 6 ng&ocirc;i l&agrave;ng cổ đẹp nhất Việt Nam.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/doi-co-hong-da-lat\" target=\"_blank\">Đồi cỏ hồng</a></strong>: M&agrave;u hồng của ở đ&acirc;y được tạo n&ecirc;n bởi loại cỏ tuyết. Cỏ mọc dại ven b&igrave;a rừng hay hồ nước tạo n&ecirc;n một khung cảnh thi&ecirc;n nhi&ecirc;n hữu t&igrave;nh đẹp tựa tranh vẽ.</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-7_1700543144.jpg\" /><em>&nbsp;</em></p>\n\n<h3><strong>4.4. C&aacute;c điểm đến theo hướng đi đ&egrave;o Prenn</strong></h3>\n\n<p>Nếu muốn tham quan đ&egrave;o Prenn, bạn c&oacute; thể sắp xếp lịch tr&igrave;nh đến c&aacute;c địa điểm l&acirc;n cận như sau: Th&aacute;c Prenn - Trang trại Dalat Milk - Th&aacute;c Pongour.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/thac-prenn-da-lat\" target=\"_blank\">Th&aacute;c Prenn</a></strong>: Nơi n&agrave;y sở hữu phong cảnh vừa h&ugrave;ng vĩ, vừa y&ecirc;n b&igrave;nh, thơ mộng c&ugrave;ng nhiều hoạt động hấp dẫn như: ch&egrave;o thuyền, cưỡi đ&agrave; điểu&hellip;</li>\n	<li><strong>Dalat Milk</strong>: Nơi đ&acirc;y sở hữu lối kiến tr&uacute;c cổ điển của v&ugrave;ng qu&ecirc; H&agrave; Lan, c&ugrave;ng với đồng cỏ, b&ograve; sữa, cối xay gi&oacute;&hellip; tạo n&ecirc;n khung cảnh miền qu&ecirc; ch&acirc;u &Acirc;u l&atilde;ng mạn.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/thac-pongour-da-lat\" target=\"_blank\">Th&aacute;c Pongour</a></strong>: Đ&acirc;y l&agrave; một d&ograve;ng th&aacute;c 7 tầng cực kỳ hot tại Đ&agrave; Lạt. Nơi đ&acirc;y sở hữu vẻ đẹp hoang sơ, kỳ vĩ v&agrave; được mệnh danh l&agrave; &ldquo;Nam thi&ecirc;n đệ nhất th&aacute;c&rdquo;.</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-8_1700543126.jpg\" /></p>\n\n<h3><strong>4.5. C&aacute;c điểm đến theo hướng đi đ&egrave;o T&agrave; Nung - ngoại th&agrave;nh Đ&agrave; Lạt</strong></h3>\n\n<p>Theo hướng đi đ&egrave;o T&agrave; Nung - ngoại th&agrave;nh Đ&agrave; Lạt, bạn c&oacute; thể l&ecirc;n lịch tr&igrave;nh du lịch Đ&agrave; Lạt tự t&uacute;c như sau: L&agrave;ng hoa Vạn Th&agrave;nh - Th&aacute;c Voi - Ch&ugrave;a Linh Ẩn.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/lang-hoa-van-thanh-da-lat\" target=\"_blank\">L&agrave;ng hoa Vạn Th&agrave;nh</a></strong>: Nơi n&agrave;y nổi tiếng với vẻ đẹp rực rỡ mu&ocirc;n hoa khoe sắc, tựa như một bức tranh cổ t&iacute;ch ngo&agrave;i đời thực.</li>\n	<li><strong>Th&aacute;c Voi</strong>: Th&aacute;c Voi hay c&ograve;n được gọi l&agrave; th&aacute;c Li&ecirc;ng Rơwoa cao hơn 30m, rộng khoảng 15m. Con th&aacute;c l&agrave; một trong những th&aacute;c nước đẹp của n&uacute;i rừng T&acirc;y Nguy&ecirc;n h&ugrave;ng vĩ.&nbsp;</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/chua-linh-an-da-lat\" target=\"_blank\">Ch&ugrave;a Linh Ẩn</a></strong>: Nơi đ&acirc;y được biết đến l&agrave; chốn cầu nguyện linh thi&ecirc;ng bậc nhất tại Đ&agrave; Lạt. Ng&ocirc;i ch&ugrave;a n&agrave;y sở hữu n&eacute;t kiến tr&uacute;c truyền thống độc đ&aacute;o, uy nghi&ecirc;m, thanh tịnh.</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-9_1700543095.jpg\" /></p>\n\n<h3><strong>4.6. C&aacute;c điểm đến theo hướng đi đồi ch&egrave; Đầu Đất - Trại M&aacute;t</strong></h3>\n\n<p>Nếu đi theo hướng đồi ch&egrave; Đầu Đất - Trại M&aacute;t, du kh&aacute;ch c&oacute; thể l&ecirc;n lịch tr&igrave;nh kh&aacute;m ph&aacute; như sau: Ga Đ&agrave; Lạt - Dinh 1 Đ&agrave; Lạt - Ch&ugrave;a Linh Phước - Đồi ch&egrave; Cầu Đất.</p>\n\n<ul>\n	<li><strong><a href=\"https://vinpearl.com/vi/ga-xe-lua-da-lat-toa-do-check-in-sieu-xin\" target=\"_blank\">Ga xe lửa Đ&agrave; Lạt</a></strong>: Đ&acirc;y l&agrave; một trong những nh&agrave; ga xe lửa cổ nhất Đ&agrave; Lạt. T&ograve;a nh&agrave; c&oacute; m&agrave;u v&agrave;ng rực rỡ, in đậm n&eacute;t kiến tr&uacute;c ch&acirc;u &Acirc;u.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/ghe-tham-dinh-1-da-lat-biet-thu-xa-hoa-co-kinh\" target=\"_blank\">Dinh 1 Đ&agrave; Lạt</a></strong>: Đ&acirc;y l&agrave; c&ocirc;ng tr&igrave;nh nổi tiếng gắn liền với vị vua Bảo Đại. C&ocirc;ng tr&igrave;nh c&oacute; tổng diện t&iacute;ch khoảng 60ha, l&agrave; một trong những c&ocirc;ng tr&igrave;nh đồ sộ nhất thời bấy giờ.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/chua-linh-phuoc-da-lat\" target=\"_blank\">Ch&ugrave;a Linh Phước</a></strong>: Do to&agrave;n bộ kiến tr&uacute;c được l&agrave;m bằng s&agrave;nh sứ n&ecirc;n ch&ugrave;a c&ograve;n được gọi l&agrave; &ldquo;ch&ugrave;a Ve Chai&rdquo;. Đ&acirc;y l&agrave; một địa điểm du lịch t&ocirc;n gi&aacute;o, văn h&oacute;a nổi tiếng tại Đ&agrave; Lạt.</li>\n	<li><strong><a href=\"https://vinpearl.com/vi/doi-che-cau-dat\" target=\"_blank\">Đồi ch&egrave; Cầu Đất</a></strong>: Nơi n&agrave;y sở hữu khung cảnh thi&ecirc;n nhi&ecirc;n bao la b&aacute;t ng&aacute;t. Đứng từ tr&ecirc;n đồi, bạn c&oacute; thể chi&ecirc;m ngưỡng cảnh sắc h&ugrave;ng vĩ của n&uacute;i đồi, săn m&acirc;y, đ&oacute;n b&igrave;nh minh tuyệt đẹp.</li>\n</ul>\n\n<p><img alt=\"Du lịch Đà Lạt tự túc\" src=\"https://statics.vinpearl.com/du-lich-da-lat-tu-tuc-10_1700543054.jpg\" /></p>\n\n<p>&nbsp;</p>\n', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747381956/saoviet/qo2vsd6ezgjkpj8t4czq.jpg', 'Bạn đang có kế hoạch đi du lịch Đà Lạt tự túc? Hãy bỏ túi ngay thông tin chi tiết về lịch trình, các địa điểm thú vị, chi phí cũng như kinh nghiệm di chuyển hữu ích nhất được Vinpearl tổng hợp trong bài viết sau.', '2025-05-16 14:52:26.607912', 'Du lịch Đà Lạt tự túc - review TẤT TẦN TẬT cho người đi lần đầu', 'Thường', 5);

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
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `comment` text COLLATE utf8mb4_vietnamese_ci
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
  `tour_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `adult_price`, `children_price`, `code`, `end_date`, `quantity_people`, `start_date`, `status`, `total_people`, `tour_id`, `created_time`) VALUES
('f93bdfd8-e42a-4e0a-91ba-dcfd960876bc', 4000000, 3200000, 'LT2025000000001', '2025-05-21', 14, '2025-05-19', 'Chưa diễn ra', 20, '2a851424-9745-4742-9c1e-60eca98c398e', '2025-05-16 00:09:13.831903');

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
(2, 8, 'tour', 2025),
(3, 1, 'news', 2025),
(4, 1, 'schedule', 2025);

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
('7f88ec2a-e8ac-4435-a2c1-0cb5b4f50663', '2025-05-16 02:39:33'),
('842e18c4-a10b-4d7a-8b48-8a826c98a2e7', '2025-05-09 16:43:08'),
('8759723c-6f70-4c71-a20d-0ded3b7e1c11', '2025-05-11 18:32:22'),
('8e92ec9e-6dc1-4b28-9f1e-b1286fd1fac0', '2025-05-09 16:47:26'),
('921248f9-6c5c-4ea5-991c-04a193b8b251', '2025-05-16 14:51:27'),
('97f66e24-0609-4e99-9437-ae9205da4e03', '2025-05-09 16:06:38'),
('a411c967-a5db-4c03-9028-2488fcd07890', '2025-05-06 12:59:40'),
('a7e385ba-11d3-4942-9d3d-65b37c70f540', '2025-05-12 14:57:37'),
('bbe42be1-8b75-4044-91a1-d9f01b5c4dc8', '2025-05-09 15:53:34'),
('bde1218d-90e2-4610-b257-057467f2fe1c', '2025-05-06 13:35:10'),
('c63ac57b-0678-4b4a-b744-a2307bd436e8', '2025-05-16 14:42:45'),
('cd311cbf-f870-40e8-b173-853685e6550b', '2025-05-16 02:38:07'),
('ecddda99-921a-4d05-b433-0515800e39b9', '2025-05-16 14:51:52');

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
('05788742-5379-4587-a915-8d8ddf501a91', 'T2025000004', '<p><strong>ƯU Đ&Atilde;I</strong></p>\n\n<ul>\n	<li><strong>Tặng mỗi kh&aacute;ch 01 v&eacute; foot massage.</strong></li>\n	<li><strong>Tặng v&eacute; c&aacute;p treo Y&ecirc;n Tử</strong>&nbsp;(chặng Hoa Y&ecirc;n).</li>\n</ul>\n\n<p><em>Số lượng qu&agrave; tặng c&oacute; giới hạn v&agrave; c&aacute;c khuyến m&atilde;i c&oacute; điều kiện &aacute;p dụng.</em></p>\n\n<p><strong>ĐIỂM NHẤN</strong></p>\n\n<ul>\n	<li><strong>Tham quan</strong>: Lăng Chủ Tịch Hồ Ch&iacute; Minh, thăm Phủ Chủ Tịch, ch&ugrave;a Một Cột, nh&agrave; s&agrave;n, ao c&aacute; B&aacute;c Hồ, Quần thể danh thắng Tr&agrave;ng An, ch&ugrave;a B&aacute;i Đ&iacute;nh nổi tiếng,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Kh&aacute;ch sạn chuẩn 4*.&nbsp;</li>\n	<li><strong>Ăn uống</strong>: B&aacute;nh cốm H&agrave; Nội, đặc sản d&ecirc; n&uacute;i Ninh B&igrave;nh, b&aacute;nh đậu xanh Hải Dương v&agrave; c&aacute;c m&oacute;n ăn đặc sản của v&ugrave;ng n&uacute;i T&acirc;y Bắc: Thắng Cố, cơm lam, lợn cắp n&aacute;ch&hellip;</li>\n	<li><strong>Hoạt động kh&aacute;c</strong>: Chinh phục đỉnh Fansipan với hệ thống c&aacute;p treo hiện đại; kh&aacute;m ph&aacute; bản C&aacute;t C&aacute;t với đặc trưng văn h&oacute;a v&ugrave;ng T&acirc;y Bắc,&hellip;</li>\n</ul>\n', 'HÀ NỘI – NINH BÌNH – HẠ LONG – YÊN TỬ – SAPA', 'MIỀN BẮC 6N5Đ | HÀ NỘI – NINH BÌNH – HẠ LONG – YÊN TỬ – SAPA', 6, 0, 'b', '2025-05-16 00:33:07.060733'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 'T2025000008', '<p><strong>H&agrave;nh tr&igrave;nh kh&aacute;m ph&aacute; vẻ đẹp h&ugrave;ng vĩ v&agrave; hoang sơ nơi cực Bắc Tổ quốc</strong></p>\n\n<p>&nbsp; &nbsp; &nbsp;C&ugrave;ng ch&uacute;ng t&ocirc;i chinh phục những cung đường đ&egrave;o uốn lượn, kh&aacute;m ph&aacute; n&eacute;t đẹp nguy&ecirc;n sơ của n&uacute;i rừng Đ&ocirc;ng Bắc trong <strong>Tour H&agrave; Giang &ndash; Lũng C&uacute; &ndash; Cao nguy&ecirc;n đ&aacute; Đồng Văn</strong>. Đ&acirc;y l&agrave; h&agrave;nh tr&igrave;nh đưa bạn đến với miền đất địa đầu Tổ quốc, nơi c&oacute; cột cờ Lũng C&uacute; thi&ecirc;ng li&ecirc;ng, những bản l&agrave;ng người M&ocirc;ng mộc mạc v&agrave; di sản đ&aacute; độc đ&aacute;o được UNESCO c&ocirc;ng nhận.</p>\n\n<p>🌄 <strong>Điểm nổi bật của tour:</strong></p>\n\n<ul>\n	<li>\n	<p>Check-in <strong>Cột cờ Lũng C&uacute;</strong> &ndash; nơi đ&aacute;nh dấu điểm cực Bắc Việt Nam</p>\n	</li>\n	<li>\n	<p>Tham quan <strong>Phố cổ Đồng Văn</strong> cổ k&iacute;nh, thưởng thức c&agrave; ph&ecirc; phố n&uacute;i</p>\n	</li>\n	<li>\n	<p>Chinh phục <strong>Đ&egrave;o M&atilde; P&igrave; L&egrave;ng</strong> &ndash; một trong &ldquo;tứ đại đỉnh đ&egrave;o&rdquo; h&ugrave;ng vĩ bậc nhất Việt Nam</p>\n	</li>\n	<li>\n	<p>Gh&eacute; thăm <strong>Dinh thự vua M&egrave;o</strong> &ndash; c&ocirc;ng tr&igrave;nh kiến tr&uacute;c độc đ&aacute;o giữa l&ograve;ng cao nguy&ecirc;n đ&aacute;</p>\n	</li>\n	<li>\n	<p>Trải nghiệm chợ phi&ecirc;n v&ugrave;ng cao đầy m&agrave;u sắc văn h&oacute;a</p>\n	</li>\n</ul>\n\n<p>Đ&acirc;y kh&ocirc;ng chỉ l&agrave; một chuyến đi, m&agrave; c&ograve;n l&agrave; h&agrave;nh tr&igrave;nh cảm nhận vẻ đẹp thi&ecirc;ng li&ecirc;ng của Tổ quốc v&agrave; những c&acirc;u chuyện đậm chất bản địa.</p>\n', 'Hà Giang - Lũng Cú - Đồng Văn', 'Hà Giang - Lũng Cú - Cao Nguyên Đá Đồng Văn', 3, 0, 'b', '2025-05-16 14:17:20.951055'),
('2a851424-9745-4742-9c1e-60eca98c398e', 'T2025000001', '<p><strong>ĐIỂM NHẤN CHƯƠNG TR&Igrave;NH</strong></p>\n\n<ul>\n	<li><strong>Tham quan</strong>:&nbsp;Cơ sở sản xuất Rượu Sim, Thị Trấn Ho&agrave;ng H&ocirc;n, Vườn Ti&ecirc;u Ph&uacute; Quốc, Dinh Cậu, Dinh B&agrave;, Tượng Đ&agrave;i B&aacute;c Hồ, Chương tr&igrave;nh T&agrave;u c&acirc;u c&aacute; &ndash; lặn ngắm san h&ocirc;,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Resort 4 sao.&nbsp;</li>\n	<li><strong>Hoạt động kh&aacute;c</strong>: tắm biển, lặn biển, ngắm san h&ocirc;, c&acirc;u c&aacute; v&agrave; thử sức với c&aacute;c tr&ograve; chơi biển.<br />\n	&nbsp;</li>\n</ul>\n\n<p><strong>QU&Agrave; TẶNG</strong></p>\n\n<ul>\n	<li><strong>N&oacute;n du lịch Lửa Việt.&nbsp;</strong></li>\n</ul>\n\n<p><em>Số lượng qu&agrave; tặng c&oacute; giới hạn v&agrave; c&aacute;c khuyến m&atilde;i c&oacute; điều kiện &aacute;p dụng.</em></p>\n', 'TP. HỒ CHÍ MINH - PHÚ QUỐC', 'BIỂN ĐẢO 3N2Đ | PHÚ QUỐC', 3, 3, 'n', '2025-05-16 00:06:26.586500'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 'T2025000005', '<p>ĐIỂM NHẤN CHƯƠNG TR&Igrave;NH&nbsp;</p>\n\n<ul>\n	<li><strong>Tham quan</strong>: Dinh B&agrave; &ndash; Dinh Cậu, Ch&ugrave;a Hộ Quốc, Khu di t&iacute;ch lịch sử Nh&agrave; T&ugrave; Ph&uacute; Quốc, Check-in Địa Trung Hải Ph&uacute; Quốc, Đấu Trường La M&atilde;, Cầu H&ocirc;n Kiss Brridge tại thị trấn Ho&agrave;ng h&ocirc;n,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Kh&aacute;ch sạn 4 sao.&nbsp;</li>\n</ul>\n', 'HÀ NỘI – PHÚ QUỐC', 'BIỂN ĐẢO 4N3Đ | PHÚ QUỐC', 4, 0, 'n', '2025-05-16 00:39:40.546731'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 'T2025000006', '<p>ĐIỂM NHẤN CHƯƠNG TR&Igrave;NH</p>\n\n<ul>\n	<li><strong>Tham quan:&nbsp;</strong>Th&aacute;c Dasara, Nh&agrave; thờ Domain, Ch&ugrave;a Linh Phước, Th&aacute;c Datanla, N&ocirc;ng trại c&uacute;n &ndash; Puppy Farm, Thung lũng t&igrave;nh y&ecirc;u, Quảng trường L&acirc;m Vi&ecirc;n,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;:&nbsp;</strong>Kh&aacute;ch sạn chuẩn 4 sao.</li>\n	<li><strong>Hoạt động kh&aacute;c</strong>: Thưởng thức show cồng chi&ecirc;ng T&acirc;y Nguy&ecirc;n.</li>\n</ul>\n', 'ĐÀ LẠT – BẢO LỘC', 'MIỀN TRUNG 3N2Đ | ĐÀ LẠT – BẢO LỘC', 3, 0, 't', '2025-05-16 01:37:12.631992'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 'T2025000003', '<p>ĐIỂM NHẤN CHƯƠNG TR&Igrave;NH</p>\n\n<ul>\n	<li><strong>Tham quan</strong>: Lăng Khải Định, Đại Nội, Ch&ugrave;a Thi&ecirc;n Mụ, L&agrave;ng hương trầm Thủy Xu&acirc;n, biển Mỹ Kh&ecirc;, Phố Cổ Hội An, khu vui chơi<br />\n	Fantasy Park,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Kh&aacute;ch sạn chuẩn 4 sao.&nbsp;</li>\n	<li><strong>Hoạt động kh&aacute;c</strong>: Thưởng thức ca Huế tr&ecirc;n s&ocirc;ng Hương.&nbsp;</li>\n</ul>\n', 'CỐ ĐÔ HUẾ – ĐÀ NẴNG – BÀ NÀ HILL – PHỐ CỔ HỘI AN', 'MIỀN TRUNG 4N3Đ | CỐ ĐÔ HUẾ – ĐÀ NẴNG – BÀ NÀ HILL – PHỐ CỔ HỘI AN', 4, 0, 't', '2025-05-16 00:25:09.623849'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 'T2025000002', '<p><strong>ĐIỂM NHẤN</strong></p>\n\n<ul>\n	<li><strong>Tham quan</strong>: Khu du lịch B&agrave; N&agrave; Hills, Ch&ugrave;a Linh Ứng, Cầu T&igrave;nh Y&ecirc;u, C&aacute; Ch&eacute;p H&oacute;a Rồng, Hội Qu&aacute;n Phước Kiến, chợ hải sản T&acirc;n Hiệp,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Kh&aacute;ch sạn chuẩn 4 sao.&nbsp;</li>\n	<li><strong>Ăn uống</strong>: B&aacute;nh tr&aacute;ng thịt heo 2 đầu da v&agrave; m&igrave; Quảng Đ&agrave; Nẵng.&nbsp;</li>\n	<li><strong>Hoạt động kh&aacute;c:</strong>&nbsp;Tắm biển Mỹ Kh&ecirc;, Trải nghiệm cano cao tốc.&nbsp;<br />\n	&nbsp;</li>\n</ul>\n\n<p><strong>QU&Agrave; TẶNG</strong></p>\n\n<ul>\n	<li><strong>N&oacute;n du lịch Lửa Việt.</strong></li>\n</ul>\n', 'ĐÀ NẴNG – CÙ LAO CHÀM – HỘI AN – BÀ NÀ', 'MIỀN TRUNG 3N2Đ | ĐÀ NẴNG – CÙ LAO CHÀM – HỘI AN – BÀ NÀ', 3, 0, 't', '2025-05-16 00:18:57.263996'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 'T2025000007', '<p>QU&Agrave; TẶNG &ndash; KHUYẾN M&Atilde;I</p>\n\n<ul>\n	<li><strong>N&oacute;n du lịch Lửa Việt.</strong></li>\n	<li><strong>Tặng 01 v&eacute; foot massage.</strong></li>\n	<li><strong>Tặng 01 b&aacute;nh cốm H&agrave;ng Than + 01 que kem Tr&agrave;ng Tiền.</strong><br />\n	&nbsp;</li>\n</ul>\n\n<p>ĐIỂM NHẤN CHƯƠNG TR&Igrave;NH&nbsp;</p>\n\n<ul>\n	<li><strong>Tham quan</strong>: Hồ T&acirc;y, Ch&ugrave;a Trấn Quốc, đền Ngọc Sơn, cầu Th&ecirc; H&uacute;c, hồ Ho&agrave;n Kiếm, Bản C&aacute;t C&aacute;t,&hellip;&nbsp;</li>\n	<li><strong>Lưu tr&uacute;</strong>: Kh&aacute;ch sạn chuẩn 4 sao.&nbsp;</li>\n	<li><strong>Ăn uống</strong>: Thưởng thức đặc sản Miền Bắc: b&aacute;nh cốm H&agrave; Nội, kem Tr&agrave;ng Tiền v&agrave; c&aacute;c m&oacute;n ăn đặc sản của n&uacute;i rừng T&acirc;y Bắc&hellip;</li>\n	<li><strong>Hoạt động kh&aacute;c:</strong>&nbsp;Chinh phục đỉnh Fansipan với hệ thống c&aacute;p treo hiện đại.&nbsp;</li>\n</ul>\n', 'HÀ NỘI – LÀO CAI – SA PA', 'MIỀN BẮC 4N3Đ | HÀ NỘI – LÀO CAI – SA PA', 4, 0, 'b', '2025-05-16 01:36:48.697925');

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
('2a851424-9745-4742-9c1e-60eca98c398e', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747328676/saoviet/ykqgf4oxbhffuo4uxblj.jpg'),
('2a851424-9745-4742-9c1e-60eca98c398e', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747328685/saoviet/xei1wt4trcetzqotawlj.jpg'),
('2a851424-9745-4742-9c1e-60eca98c398e', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747328679/saoviet/vghbglabqx4i5q9wm4yb.jpg'),
('2a851424-9745-4742-9c1e-60eca98c398e', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747328676/saoviet/nplh8q6ldhl0gih5cuqy.jpg'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329424/saoviet/pzukbcxa4m48xqrhvgth.png'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329424/saoviet/glnkbmlfp1luldluuwld.png'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329424/saoviet/svjnkrhll8x3ybihbfvo.png'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329424/saoviet/ibxzo38pq862cp3aynra.png'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329803/saoviet/lkvzge2gitxmly76x5bw.jpg'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329803/saoviet/guygqgqdazdr9vg7uhex.jpg'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329803/saoviet/b5tnsyhwbbmgubzn20l0.jpg'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747329804/saoviet/fc0jn8y9qlaf0yscjiev.png'),
('05788742-5379-4587-a915-8d8ddf501a91', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330097/saoviet/fumfvxulw0riooex92gd.png'),
('05788742-5379-4587-a915-8d8ddf501a91', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330097/saoviet/ns9khevq9idkgjp1nfx5.png'),
('05788742-5379-4587-a915-8d8ddf501a91', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330097/saoviet/lwoqzpreqypmukb4wfa8.png'),
('05788742-5379-4587-a915-8d8ddf501a91', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330098/saoviet/elcpl8tbw2ysgak1i7iz.png'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330662/saoviet/fpxpjnqc5wko3se3vhwo.jpg'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330662/saoviet/ye0cregircj2r9rrmiyo.jpg'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330662/saoviet/c1wlbpu6tqmbhz8cbtbv.jpg'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747330662/saoviet/agm5ep2qynwvluvmuorc.jpg'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747334212/saoviet/pgllbt3yr73nw7hyetwm.jpg'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747334210/saoviet/ilanc5ffafq4v9oauol1.png'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747334209/saoviet/rabzlku8u14o8ubhyq03.jpg'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747334210/saoviet/qoqxbtf2mbohritthxeo.png'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747333870/saoviet/bs4e9yvyows6k4zggesw.jpg'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747333871/saoviet/hfewhuswfseq36vixkte.jpg'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747333870/saoviet/hrpz4ble6ypyoufq5nit.jpg'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747333871/saoviet/osgr8q3ylm9f3tzqgacb.jpg'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747379821/saoviet/yzsal6esvtr8l4v9zlfd.jpg'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747379821/saoviet/nlfgnhyazihkw1efyzjs.jpg'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747379820/saoviet/vvszvqdxi2zo3ihbtelx.jpg'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 'https://res.cloudinary.com/doie0qiiq/image/upload/v1747379832/saoviet/qlikfh8flodsylgrdjyk.webp');

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
('2a851424-9745-4742-9c1e-60eca98c398e', 1, '<p>Tập trung tại s&acirc;n bay T&acirc;n Sơn Nhất, đ&aacute;p chuyến bay TP. HCM &ndash; Ph&uacute; Quốc.&nbsp;<em>(C&aacute;c chuyến bay dự kiến từ 09:45 &ndash; 11:00)</em>.&nbsp;Đến s&acirc;n bay Ph&uacute; Quốc, xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n kh&aacute;ch khởi h&agrave;nh tham quan:&nbsp;</p>\n\n<ul>\n	<li><strong>Cơ sở sản xuất Rượu Sim</strong><em>&nbsp;(loại vang ri&ecirc;ng của đảo):</em>&nbsp;Kh&aacute;m ph&aacute; đặc sản vang Sim với hương vị độc đ&aacute;o v&agrave; t&igrave;m hiểu quy tr&igrave;nh sản xuất.</li>\n	<li><strong>Vườn Ti&ecirc;u Ph&uacute; Quốc</strong>:&nbsp;Ti&ecirc;u Ph&uacute; Quốc nổi tiếng với hương vị ti&ecirc;u cay nồng, nổi tiếng.</li>\n</ul>\n\n<p>Ăn trưa với ẩm thực địa phương. Nhận ph&ograve;ng kh&aacute;ch sạn v&agrave; nghỉ ngơi (<em>Nhận ph&ograve;ng từ 14h00, hỗ trợ sớm nếu c&oacute; ph&ograve;ng trống</em>). Chiều, tiếp tục tham quan:</p>\n\n<ul>\n	<li><strong>Trại rắn Đồng T&acirc;m 2</strong>&nbsp;&ndash; xứ sở c&aacute;c lo&agrave;i rắn: Trải nghiệm thế giới rắn với hơn 100 lo&agrave;i, từ rắn hiền l&agrave;nh đến những lo&agrave;i cực độc.</li>\n	<li><strong>Dinh Cậu, Dinh B&agrave;</strong>&nbsp;&ndash; nơi gửi gắm đức tin của người d&acirc;n xứ đảo.</li>\n	<li><strong>Tượng Đ&agrave;i B&aacute;c Hồ:</strong>&nbsp;Với chủ đề &ldquo;Miền Nam trong tr&aacute;i tim t&ocirc;i&rdquo; cao 20,7 m&eacute;t, nặng hơn 93 tấn v&agrave; l&agrave; biểu tượng về văn h&oacute;a, g&oacute;p phần khẳng định chủ quyền biển, đảo của Tổ quốc.</li>\n</ul>\n\n<p>Ăn tối tại nh&agrave; h&agrave;ng. Tiếp tục chương tr&igrave;nh:</p>\n\n<ul>\n	<li><strong>Tham quan Tơ Lụa Việt</strong>&nbsp;thương hiệu chuy&ecirc;n cung cấp c&aacute;c sản phẩm: chăn, ga gối nệm&hellip;</li>\n	<li><strong>Kh&aacute;m ph&aacute; Grand World</strong>&nbsp;&ndash; Th&agrave;nh phố kh&ocirc;ng ngủ.&nbsp;<em>(Kh&ocirc;ng t&iacute;nh ph&iacute; v&agrave;o cổng &ndash; Chi ph&iacute; vui chơi tự t&uacute;c)</em>&nbsp;Tọa lạc tại vị tr&iacute; trung t&acirc;m Ph&uacute; Quốc United Center, diện t&iacute;ch l&ecirc;n đến 85ha với kiến tr&uacute;c cảnh quan rực rỡ lấy cảm hứng từ ch&acirc;u &Acirc;u. Được x&acirc;y dựng lộng lẫy tại 4 tiểu khu: Quảng trường, Shanghai, Indochine, Mallorca.</li>\n	<li>\n	<ul>\n		<li><em>Show diễn&nbsp;<strong>&ldquo;Tinh Hoa Việt Nam&rdquo;</strong>&nbsp;&ndash; h&agrave;ng ng&agrave;y l&uacute;c 20:15&nbsp;<strong>(chi ph&iacute; v&eacute; tự t&uacute;c).</strong></em></li>\n		<li><em>Tận hưởng cảm hứng Ch&acirc;u &Acirc;u qua show diễn<strong>&nbsp;Sắc m&agrave;u Venice</strong>: Show diễn miễn ph&iacute;, t&aacute;i hiện c&acirc;u chuyện t&igrave;nh y&ecirc;u l&atilde;ng mạn trong kh&ocirc;ng gian Ch&acirc;u &Acirc;u.</em></li>\n	</ul>\n	</li>\n</ul>\n\n<p>Trở về kh&aacute;ch sạn, nghỉ đ&ecirc;m.</p>\n', 'TP. HỒ CHÍ MINH - PHÚ QUỐC'),
('2a851424-9745-4742-9c1e-60eca98c398e', 2, '<p>Ăn s&aacute;ng. Khởi h&agrave;nh tham quan Nam Đảo:</p>\n\n<ul>\n	<li><strong>Cơ sở nu&ocirc;i cấy Ngọc Trai</strong>: Kh&aacute;m ph&aacute; qu&aacute; tr&igrave;nh nu&ocirc;i trai lấy ngọc v&agrave; quan s&aacute;t trực tiếp quy tr&igrave;nh mổ trai. Qu&yacute; kh&aacute;ch c&oacute; thể mua trang sức ngọc trai l&agrave;m qu&agrave; lưu niệm.</li>\n	<li><strong>Chương tr&igrave;nh T&agrave;u c&acirc;u c&aacute; &ndash; lặn ngắm san h&ocirc;:</strong>&nbsp;Khởi h&agrave;nh từ cảng biển, trải nghiệm c&acirc;u c&aacute; v&agrave; lặn ngắm san h&ocirc; với đầy đủ trang thiết bị.</li>\n</ul>\n\n<p>Trải nghiệm ăn trưa tr&ecirc;n t&agrave;u với &ldquo;chiến lợi phẩm&rdquo; hải sản biển c&acirc;u được.</p>\n\n<p>T&agrave;u đưa qu&yacute; kh&aacute;ch trở lại đất liền, tiếp tục tham quan:</p>\n\n<ul>\n	<li><strong>Nh&agrave; t&ugrave; Ph&uacute; Quốc:</strong>&nbsp;Di t&iacute;ch quốc gia đặc biệt.</li>\n	<li><strong>Ch&ugrave;a Hộ Quốc</strong>: Ng&ocirc;i ch&ugrave;a đẹp nhất đảo ngọc với vị tr&iacute;&nbsp;<em>&ldquo;tọa sơn hướng thủy&rdquo;.</em></li>\n	<li><strong>Cơ sở sản xuất nước mắm</strong>: Kh&aacute;m ph&aacute; l&agrave;ng nghề truyền thống l&agrave;m n&ecirc;n thương hiệu nước mắm Ph&uacute; Quốc nổi tiếng thế giới.</li>\n	<li><strong>Thị Trấn Ho&agrave;ng H&ocirc;n</strong>: Địa điểm du lịch h&agrave;ng đầu tại Ph&uacute; Quốc, mang đậm phong c&aacute;ch Địa Trung Hải với c&aacute;c biểu tượng nổi bật:&nbsp;<em>C&aacute;p treo H&ograve;n Thơm, Cầu H&ocirc;n, Th&aacute;p Đồng Hồ&hellip;</em></li>\n</ul>\n\n<p>Ăn tối buffet tại nh&agrave; h&agrave;ng. Xe đưa kh&aacute;ch về lại kh&aacute;ch sạn. Nghỉ đ&ecirc;m.&nbsp;</p>\n\n<p><em>***Nếu qu&yacute; kh&aacute;ch kh&ocirc;ng tham quan theo lịch tr&ecirc;n c&oacute; thể tự t&uacute;c với option tham quan sau:</em></p>\n\n<p><strong>Option tour: Tour Cano Tham quan 4 Đảo (chi ph&iacute; tự t&uacute;c từ: ~ 690,000VNĐ/kh&aacute;ch)</strong></p>\n\n<p>8h00: Đ&oacute;n kh&aacute;ch tại kh&aacute;ch sạn, tham quan cơ sở nu&ocirc;i cấy ngọc trai.</p>\n\n<ul>\n	<li><strong>H&ograve;n M&acirc;y R&uacute;t:</strong>&nbsp;Check-in v&agrave; tham quan.</li>\n	<li><strong>H&ograve;n Gầm Gh&igrave;</strong>: Bơi ngắm san h&ocirc; miễn ph&iacute;.</li>\n	<li><strong>H&ograve;n R&otilde;i</strong>: Trải nghiệm c&ocirc;ng vi&ecirc;n san h&ocirc; đi bộ dưới đ&aacute;y biển&nbsp;<em>(chi ph&iacute; tự t&uacute;c).</em></li>\n	<li><strong>H&ograve;n M&oacute;ng Tay</strong>: Tắm biển, nghỉ ngơi, chụp ảnh, quay flycam (miễn ph&iacute;)</li>\n</ul>\n\n<p>Ăn trưa. Trở về kh&aacute;ch sạn.</p>\n\n<p>(<em>Nếu qu&yacute; kh&aacute;ch chọn g&oacute;i bao gồm th&ecirc;m C&aacute;p treo H&ograve;n Thơm th&igrave; chương tr&igrave;nh vẫn tiếp tục. Nếu qu&yacute; kh&aacute;ch kh&ocirc;ng đi C&aacute;p treo, cano đưa qu&yacute; kh&aacute;ch về lại cảng. Tuỳ v&agrave;o t&igrave;nh h&igrave;nh thời tiết c&aacute;c h&ograve;n đảo sẽ được linh động nhưng vẫn đảm bảo đủ 4 đảo)</em></p>\n', 'PHÚ QUỐC - KHÁM PHÁ ĐẢO NGỌC'),
('2a851424-9745-4742-9c1e-60eca98c398e', 3, '<p>Ăn s&aacute;ng. L&agrave;m thủ tục trả ph&ograve;ng. Ra s&acirc;n bay l&agrave;m thủ tục đ&aacute;p chuyến bay trở lại TP. HCM.</p>\n\n<p><em>(C&aacute;c chuyến bay dự kiến sau 12:00)</em>. Đến s&acirc;n bay T&acirc;n Sơn Nhất, kết th&uacute;c chương tr&igrave;nh tham quan!</p>\n\n<p><em>*** Nếu qu&yacute; kh&aacute;ch book v&eacute; m&aacute;y bay chặng về c&aacute;c chuyến tối c&oacute; thể tự t&uacute;c lựa chọn th&ecirc;m c&aacute;c option tour sau:</em></p>\n\n<p><strong>1. Vinpearl Safari Ph&uacute; Quốc</strong>&nbsp;<em>(chi ph&iacute; tự t&uacute;c &ndash; gi&aacute; tham khảo: 650,000VNĐ)</em>&nbsp;&ndash; c&ocirc;ng vi&ecirc;n bảo tồn động vật với m&ocirc; h&igrave;nh b&aacute;n hoang d&atilde; đầu ti&ecirc;n của Việt Nam c&oacute; diện t&iacute;ch 380 ha với nhiều lo&agrave;i động vật qu&yacute; hiếm.</p>\n\n<p><strong>2. Vinwonders</strong>&nbsp;<em>(chi ph&iacute; tự t&uacute;c &ndash; gi&aacute; tham khảo: 950,000VNĐ/kh&aacute;ch)</em>&nbsp;&ndash; l&agrave; một khu giải tr&iacute; tổng hợp với nhiều tr&ograve; chơi trong nh&agrave; v&agrave; ngo&agrave;i trời như:&nbsp;<em>tàu lượn si&ecirc;u tốc, đu quay v&ograve;ng xoay, đĩa quay si&ecirc;u tốc, biểu diễn c&aacute; heo, c&ocirc;ng vi&ecirc;n nước, thủy cung, lễ hội đường phố&hellip;</em></p>\n\n<p><em><strong>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</strong></em></p>\n', 'PHÚ QUỐC - TP.HỒ CHÍ MINH'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 1, '<p>Đ&oacute;n qu&yacute; kh&aacute;ch tại&nbsp;<strong>s&acirc;n bay T&acirc;n Sơn Nhất</strong>, đ&aacute;p chuyến bay khởi h&agrave;nh đi Đ&agrave; Nẵng&nbsp;<em>(c&aacute;c chuyến bay dự kiến từ 07:00 &ndash; 09:30).&nbsp;</em>Đến Đ&agrave; Nẵng, xe v&agrave; HDV đ&oacute;n kh&aacute;ch.</p>\n\n<p>Ăn trưa đặc sản Đ&agrave; Nẵng&nbsp;<em>&ldquo;B&aacute;nh tr&aacute;ng thịt heo 2 đầu da &amp; m&igrave; Quảng&rdquo;</em>. Nhận ph&ograve;ng, nghỉ ngơi.</p>\n\n<p>Buổi chiều, khởi h&agrave;nh tham quan&nbsp;<strong>b&aacute;n đảo Sơn Tr&agrave;</strong>&nbsp;ngắm phố biển từ tr&ecirc;n cao.</p>\n\n<ul>\n	<li>Viếng<strong>&nbsp;Linh Ứng Tự</strong>&nbsp;&ndash; nơi c&oacute; tượng Phật B&agrave; cao 67 m&eacute;t cao nhất Việt Nam.</li>\n	<li><strong>C&ocirc;ng vi&ecirc;n kỳ quan thế giới Đ&agrave; Nẵng</strong>&nbsp;&ndash; c&ocirc;ng tr&igrave;nh thu nhỏ m&ocirc; phỏng 9 kỳ quan thế giới v&agrave; c&aacute;c địa danh nổi tiếng tại Việt Nam.</li>\n	<li>Tắm biển<strong>&nbsp;Mỹ Kh&ecirc;</strong>&nbsp;&ndash; từng được tạp ch&iacute; Forbes b&igrave;nh chọn l&agrave;&nbsp;b&atilde;i biển quyến rũ nhất h&agrave;nh tinh.</li>\n</ul>\n\n<p>Ăn tối. Tự do thưởng ngoạn du thuyền s&ocirc;ng H&agrave;n ngắm cảnh Đ&agrave; Th&agrave;nh về đ&ecirc;m.&nbsp;<em>(chi ph&iacute; tự t&uacute;c)</em></p>\n', 'TP. HCM – ĐÀ NẴNG – SƠN TRÀ'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 2, '<p>Ăn s&aacute;ng buffet tại kh&aacute;ch sạn. Di chuyển v&agrave;o Hội An, đến biển Cửa Đại, ngồi t&agrave;u cao tốc khởi h&agrave;nh đi&nbsp;<strong>C&ugrave; Lao Ch&agrave;m</strong>&nbsp;&ndash; khu dự trữ sinh quyển thế giới.</p>\n\n<ul>\n	<li><strong>Trải nghiệm cano cao tốc</strong>&nbsp;lướt tr&ecirc;n biển xanh, cano cập cảng B&atilde;i L&agrave;ng C&ugrave; Lao.</li>\n	<li><strong>Khu trưng b&agrave;y nền văn h&oacute;a Sa Huỳnh, khu bảo tồn biển C&ugrave; Lao Ch&agrave;m, ch&ugrave;a Hải Tạng cổ, chợ hải sản T&acirc;n Hiệp</strong>&hellip;</li>\n	<li>Tham gia c&aacute;c hoạt động lặn biển&nbsp;<em>(Snorkeling)&nbsp;</em>ngắm san h&ocirc;.</li>\n</ul>\n\n<p>Ăn trưa tại C&ugrave; Lao Ch&agrave;m. Cano đưa kh&aacute;ch trở về đất liền. Tham quan:</p>\n\n<ul>\n	<li><strong>Phố cổ Hội An</strong>&nbsp;&ndash; du kh&aacute;ch tản bộ kh&aacute;m ph&aacute; c&aacute;c c&ocirc;ng tr&igrave;nh kiến tr&uacute;c nổi tiếng:&nbsp;<em>Ch&ugrave;a Cầu Nhật Bản, c&aacute;c ng&ocirc;i nh&agrave; cổ h&agrave;ng trăm tuổi, Hội Qu&aacute;n Phước Kiến, Xưởng thủ c&ocirc;ng mỹ nghệ</em>. Phố cổ Hội An l&agrave; qu&aacute; khứ v&agrave;ng son của một thương cảng sầm uất thời phong kiến.</li>\n</ul>\n\n<p>Ăn tối. Khởi h&agrave;nh về lại Đ&agrave; Nẵng. Tự do kh&aacute;m ph&aacute; Đ&agrave; Nẵng về đ&ecirc;m. Nghỉ đ&ecirc;m tại&nbsp;<strong>Đ&agrave; Nẵng</strong>.</p>\n', 'ĐÀ NẴNG – CÙ LAO CHÀM – HỘI AN'),
('68f11a1b-7f72-4e63-8b8a-c21261c37d72', 3, '<p>Ăn s&aacute;ng buffet tại kh&aacute;ch sạn. Trả ph&ograve;ng. Khởi h&agrave;nh tham quan:</p>\n\n<ul>\n	<li><strong>Khu du lịch B&agrave; N&agrave; Hills</strong>&nbsp;&ndash; chinh phục B&agrave; N&agrave; bằng hệ thống c&aacute;p treo một d&acirc;y d&agrave;i nhất thế giới 5.801m v&agrave; l&agrave; một trong 10 tuyến c&aacute;p treo ấn tượng nhất thế giới. B&agrave; N&agrave; l&agrave; nơi c&oacute; những khoảnh khoắc giao m&ugrave;a bất ngờ&nbsp;<em>Xu&acirc;n &ndash; Hạ &ndash; Thu &ndash; Đ&ocirc;ng</em>&nbsp;trong một ng&agrave;y.</li>\n</ul>\n\n<p><strong><em>(chi ph&iacute; c&aacute;p treo B&agrave; N&agrave; tự t&uacute;c)</em></strong>.</p>\n\n<ul>\n	<li><strong>Ch&ugrave;a Linh Ứng</strong>&nbsp;với tượng Phật Th&iacute;ch Ca cao 27m, đền thờ&nbsp;<strong>B&agrave; Ch&uacute;a Mẫu Thượng Ng&agrave;n</strong>.</li>\n	<li><strong>Cầu V&agrave;ng</strong>&nbsp;&ndash; c&acirc;y cầu độc đ&aacute;o nằm trong vườn Thi&ecirc;n Thai, được x&acirc;y dựng tr&ecirc;n độ cao 1.400m so với mặt nước biển, được n&acirc;ng đỡ bởi kiến tr&uacute;c h&igrave;nh đ&ocirc;i b&agrave;n tay khổng lồ độc đ&aacute;o.</li>\n	<li>Vui chơi tại&nbsp;<strong>C&ocirc;ng vi&ecirc;n Fantasy Park</strong>&nbsp;với hơn 100 tr&ograve; chơi phi&ecirc;u lưu hấp dẫn, mang đến cho qu&yacute; kh&aacute;ch nhiều cung bậc cảm x&uacute;c bất ngờ v&agrave; th&uacute; vị.</li>\n</ul>\n\n<p><strong>Ăn trưa buffet tại B&agrave;</strong>&nbsp;<strong>N&agrave;&nbsp;<em>(chi ph&iacute; tự t&uacute;c)</em></strong>. Tiếp tục vui chơi tại&nbsp;<strong>B&agrave; N&agrave; Hills</strong>.</p>\n\n<p><em>*Gi&aacute; combo v&eacute; c&aacute;p treo B&agrave; N&agrave; Hills + v&eacute; buffet trưa tham khảo:&nbsp;<strong>1,250,000VNĐ</strong>/ người.</em></p>\n\n<p><em>(Nếu qu&yacute; kh&aacute;ch kh&ocirc;ng tham quan B&agrave; N&agrave; th&igrave; tự t&uacute;c ăn trưa v&agrave; tự t&uacute;c chi ph&iacute; nhập đo&agrave;n tại điểm hẹn).</em></p>\n\n<p>Di chuyển ra s&acirc;n bay Đ&agrave; Nẵng đ&aacute;p chuyến bay về lại TP. HCM.&nbsp;<em>(c&aacute;c chuyến bay dự kiến sau 19:00)</em>.&nbsp;</p>\n\n<p>Chia tay đo&agrave;n v&agrave; hẹn gặp lại. Kết th&uacute;c chương tr&igrave;nh tham quan.</p>\n\n<p><strong><em>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</em></strong></p>\n', 'ĐÀ NẴNG – BÀ NÀ – TP. HCM'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 1, '<p><strong>03h30:</strong>&nbsp;Hướng dẫn vi&ecirc;n v&agrave; xe đ&oacute;n đo&agrave;n tại Rạp xiếc Trung Ương H&agrave; Nội, khởi h&agrave;nh đi s&acirc;n bay Nội B&agrave;i, khởi h&agrave;nh đi Huế chuyến bay&nbsp;<strong>VN 1541</strong>&nbsp;<strong>(6:00 &ndash; 7:15)</strong></p>\n\n<p><strong>07h15:</strong>&nbsp;Đến s&acirc;n bay Ph&uacute; B&agrave;i, xe v&agrave; hướng dẫn vi&ecirc;n địa phương đ&oacute;n đo&agrave;n đi ăn s&aacute;ng tại nh&agrave; h&agrave;ng (chi ph&iacute; tự t&uacute;c). Sau đ&oacute;, đo&agrave;n đi tham quan:</p>\n\n<ul>\n	<li><strong>Lăng Khải Định</strong>: l&agrave; một trong những c&ocirc;ng tr&igrave;nh kiến tr&uacute;c nổi bật v&agrave; đỉnh cao của lăng tẩm thời Nguyễn. Lăng Khải Định được x&acirc;y dựng l&agrave; để tưởng nhớ v&agrave; thờ c&uacute;ng vua Nguyễn Khải Định, vị ho&agrave;ng đế thứ 12 của triều Nguyễn. Với sự kết hợp giữa kiến tr&uacute;c truyền thống v&agrave; những yếu tố nghệ thuật phương T&acirc;y, Lăng Khải Định mang đậm dấu ấn của sự h&ograve;a quyện giữa hai nền văn h&oacute;a.</li>\n</ul>\n\n<p><strong>Trưa:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn trưa tại nh&agrave; h&agrave;ng. Q&uacute;y kh&aacute;ch nhận ph&ograve;ng kh&aacute;ch sạn nghỉ ngơi, giờ nhận ph&ograve;ng l&uacute;c<strong>&nbsp;14h00</strong></p>\n\n<p><strong>Chiều:</strong>&nbsp;Xe v&agrave; HDV đ&oacute;n đo&agrave;n đi tham quan:</p>\n\n<ul>\n	<li><strong>Đại Nội</strong>&nbsp;(Ho&agrave;ng Cung của 13 vị vua triều Nguyễn, triều đại phong kiến cuối c&ugrave;ng của Việt Nam: Ngọ M&ocirc;n, Điện Th&aacute;i H&ograve;a, Tử Cấm Th&agrave;nh, Thế Miếu, Hiển L&acirc;m C&aacute;c, Cửu Đỉnh).</li>\n	<li><strong>Ch&ugrave;a Thi&ecirc;n Mụ</strong>&nbsp;hay c&ograve;n gọi l&agrave; ch&ugrave;a Linh Mụ l&agrave; một ng&ocirc;i ch&ugrave;a cổ nằm tr&ecirc;n đồi H&agrave; Kh&ecirc;, tả ngạn s&ocirc;ng Hương, c&aacute;ch trung t&acirc;m th&agrave;nh phố Huế khoảng 5 km về ph&iacute;a t&acirc;y.</li>\n</ul>\n\n<p><strong>Tối:</strong>&nbsp;Đo&agrave;n d&ugrave;ng bữa tối tại nh&agrave; h&agrave;ng. Xe v&agrave; Hướng dẫn vi&ecirc;n đưa qu&yacute; kh&aacute;ch ra s&ocirc;ng Hương, đo&agrave;n l&ecirc;n thuyền rồng nghe Ca Huế. Sau đ&oacute; đo&agrave;n tự do tham quan th&agrave;nh phố Huế thơ mộng về đ&ecirc;m. Qu&yacute; kh&aacute;ch nghỉ đ&ecirc;m kh&aacute;ch sạn 4 sao tại Huế.</p>\n', 'HÀ NỘI – HUẾ - LĂNG KHẢI ĐỊNH'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 2, '<p><strong>S&aacute;ng:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn s&aacute;ng tại kh&aacute;ch sạn.</p>\n\n<p><strong>08h30:</strong>&nbsp;Xe v&agrave; HDV đ&oacute;n đo&agrave;n đi tham quan:</p>\n\n<ul>\n	<li><strong>L&agrave;ng hương trầm Thủy Xu&acirc;n</strong>&nbsp;l&agrave; một trong những địa điểm du lịch Huế nổi tiếng m&agrave; hầu như mọi du kh&aacute;ch đều dừng ch&acirc;n gh&eacute; thăm</li>\n	<li>Đo&agrave;n tham quan v&agrave; mua sắm tại&nbsp;<strong>chợ Đ&ocirc;ng Ba</strong>, tại đ&acirc;y du kh&aacute;ch tự do mua sắm c&aacute;c đặc sản xứ Huế như mè xửng, mắm t&ocirc;m chua, tr&agrave; cung đình&hellip;</li>\n	<li>Tiếp theo, hướng dẫn vi&ecirc;n đưa đo&agrave;n thăm quan&nbsp;<strong>Chùa Từ Hi&ecirc;́u</strong>&nbsp;&ndash; một ng&ocirc;i ch&ugrave;a cổ k&iacute;nh, mang đậm n&eacute;t kiến tr&uacute;c xưa cũ của cố đ&ocirc; Huế.</li>\n</ul>\n\n<p><strong>11h00</strong>: Đo&agrave;n l&agrave;m thủ tục trả ph&ograve;ng kh&aacute;ch sạn.</p>\n\n<p><strong>Trưa:</strong>&nbsp;Đo&agrave;n ăn trưa tại nh&agrave; h&agrave;ng.</p>\n\n<p><strong>13h00:</strong>&nbsp;Qu&yacute; kh&aacute;ch l&ecirc;n xe di chuyển tới th&agrave;nh phố Đ&agrave; Nẵng, tr&ecirc;n đường qu&yacute; kh&aacute;ch dừng ch&acirc;n thăm quan, chụp h&igrave;nh tại b&atilde;i biển Lăng C&ocirc; &ndash; Thừa Thi&ecirc;n Hu&ecirc;́.</p>\n\n<p>15h00: Qu&yacute; kh&aacute;ch l&agrave;m thủ tục nhận ph&ograve;ng kh&aacute;ch sạn tại&nbsp;<strong>Đ&agrave; Nẵng</strong>. Q&uacute;y kh&aacute;ch tự do nghỉ ngơi, tự do tắm biển&nbsp;<strong>Mỹ Kh&ecirc;</strong>.</p>\n\n<p><strong>15h30:</strong>&nbsp;Xe đ&oacute;n qu&yacute; kh&aacute;ch đi thăm quan Phố cổ Hội An &ndash; nằm c&aacute;ch trung t&acirc;m th&agrave;nh phố Đ&agrave; Nẵng 35 km về ph&iacute;a nam, Hội An l&agrave; 1 thương cảng sầm uất bậc nhất của xứ Đ&agrave;ng Trong từ thời Trịnh Nguyễn ph&acirc;n tranh.</p>\n\n<ul>\n	<li>Đo&agrave;n b&aacute;ch bộ thưởng ngoạn vẻ đẹp&nbsp;<strong>Phố Cổ Hội An</strong>, rực rỡ soi b&oacute;ng b&ecirc;n d&ograve;ng s&ocirc;ng Ho&agrave;i, từng l&agrave; thương cảng sầm uất của người Chăm thế kỉ thứ II v&agrave; Việt Nam từ thế kỉ XVI.</li>\n	<li>Hướng dẫn vi&ecirc;n gi&uacute;p Qu&yacute; kh&aacute;ch t&igrave;m hiểu v&agrave; kh&aacute;m ph&aacute; những&nbsp;<strong>khu phố b&agrave;n cờ, Ch&ugrave;a Cầu Nhật Bản, Hội qu&aacute;n Phước Ki&ecirc;́n, Quảng Đ&ocirc;ng, nh&agrave; cổ T&acirc;n K&yacute;, nh&agrave; thờ Tộc Trần&hellip;</strong></li>\n</ul>\n\n<p><strong>Tối:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn tối tại nh&agrave; h&agrave;ng. Sau bữa tối, qu&yacute; kh&aacute;ch dạo chơi Hội An, chụp ảnh phố Hội về đ&ecirc;m.</p>\n\n<p>Xe đưa đo&agrave;n về kh&aacute;ch sạn tại Đ&agrave; Nẵng, qu&yacute; kh&aacute;ch tự do kh&aacute;m ph&aacute; th&agrave;nh phố Đ&agrave; Nẵng.</p>\n\n<p>Đo&agrave;n nghỉ đ&ecirc;m tại kh&aacute;ch sạn 4 sao tại Đ&agrave; Nẵng.</p>\n', 'LÀNG HƯƠNG THỦY XUÂN – CHÙA TỪ HIẾU – ĐÀ NẴNG'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 3, '<p><strong>S&aacute;ng</strong>: Qu&yacute; kh&aacute;ch ăn s&aacute;ng tại kh&aacute;ch sạn. Sau bữa s&aacute;ng, qu&yacute; kh&aacute;ch c&oacute; thể lựa chọn c&aacute;c option sau:</p>\n\n<p><strong>LỰA CHỌN 1:</strong>&nbsp;<strong>B&Agrave; N&Agrave; HILLS</strong></p>\n\n<p><strong>08h00:</strong>&nbsp;Xe đưa qu&yacute; kh&aacute;ch khởi h&agrave;nh đi&nbsp;<strong>Khu du lịch B&agrave; N&agrave; &ndash; N&uacute;i Ch&uacute;a</strong>&nbsp;(chi ph&iacute; v&eacute; tham quan kh&aacute;ch tự t&uacute;c 1.250.000đ bao gồm v&eacute; v&agrave; buffet), nơi m&agrave; qu&yacute; kh&aacute;ch kh&aacute;m ph&aacute; những khoảnh khắc giao m&ugrave;a bất ngờ Xu&acirc;n &ndash; Hạ &ndash; Thu &ndash; Đ&ocirc;ng trong 1 ng&agrave;y.</p>\n\n<p>Qu&yacute; kh&aacute;ch Đến ga&nbsp;<strong>c&aacute;p treo Suối Mơ</strong>, l&ecirc;n tuyến c&aacute;p treo đạt 2 kỷ lục thế giới, (d&agrave;i gần 6.000m), qu&yacute; kh&aacute;ch tham quan:</p>\n\n<ul>\n	<li>Khu Le Jardin, tham quan Hầm Rượu Debay của Ph&aacute;p.</li>\n	<li>Viếng Ch&ugrave;a Linh Ứng B&agrave; N&agrave;, chi&ecirc;m ngưỡng tượng Phật Th&iacute;ch Ca cao 27m</li>\n	<li>Vườn Lộc Uyển, Quan &Acirc;m C&aacute;c.</li>\n</ul>\n\n<p>Tiếp tục đến Gare Debay đi tuyến c&aacute;p thứ 2 l&ecirc;n đỉnh B&agrave; N&agrave;.</p>\n\n<p><strong>Trưa:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn trưa tại nh&agrave; h&agrave;ng tr&ecirc;n đỉnh B&agrave; N&agrave;.</p>\n\n<p>Tiếp tục chinh phục&nbsp;<strong>đỉnh n&uacute;i Ch&uacute;a</strong>&nbsp;ở độ cao 1.487m so với mực nước biển để thưởng thức quang cảnh n&uacute;i rừng B&agrave; N&agrave; v&agrave; to&agrave;n cảnh Đ&agrave; Nẵng v&agrave; Quảng Nam tr&ecirc;n cao. Tham quan&nbsp;<strong>khu vui chơi Fantasy Park</strong>, qu&yacute; kh&aacute;ch tự do tham gia c&aacute;c tr&ograve; chơi phi&ecirc;u lưu mới lạ, hấp dẫn, hiện đại như v&ograve;ng quay tình y&ecirc;u, phi c&ocirc;ng Skiver, đường đua lửa, xe điện đụng, ng&ocirc;i nh&agrave; ma&hellip;</p>\n\n<p><strong>14h30:</strong>&nbsp;Qu&yacute; kh&aacute;ch xuống c&aacute;p treo về lại Đ&agrave; Nẵng. Xe đưa qu&yacute; kh&aacute;ch về kh&aacute;ch sạn, qu&yacute; kh&aacute;ch tự do nghỉ ngơi, tắm biển.</p>\n\n<p><strong>Tối:</strong>&nbsp;Đo&agrave;n ăn tối tại nh&agrave; h&agrave;ng, sau đ&oacute; l&agrave; thời gian tự do kh&aacute;m ph&aacute; th&agrave;nh phố Đ&agrave; Nẵng về đ&ecirc;m, thưởng thức caf&eacute;, n&eacute;t văn h&oacute;a người d&acirc;n Đ&agrave; Nẵng. Qu&yacute; kh&aacute;ch nghỉ đ&ecirc;m tại Kh&aacute;ch sạn 4 sao ở Đ&agrave; Nẵng.</p>\n\n<p><strong>LỰA CHỌN 2:</strong>&nbsp;Qu&yacute; kh&aacute;ch nghỉ ngơi tại kh&aacute;ch sạn, tự do vui chơi kh&aacute;m ph&aacute; Đ&agrave; Nẵng. Q&uacute;y kh&aacute;ch tự t&uacute;c ăn trưa.</p>\n\n<p><strong>Tối:</strong>&nbsp;Đo&agrave;n ăn tối tại nh&agrave; h&agrave;ng, sau đ&oacute; l&agrave; thời gian tự do kh&aacute;m ph&aacute; th&agrave;nh phố Đ&agrave; Nẵng về đ&ecirc;m, thưởng thức caf&eacute;, n&eacute;t văn h&oacute;a người d&acirc;n Đ&agrave; Nẵng.</p>\n\n<p>Qu&yacute; kh&aacute;ch nghỉ đ&ecirc;m tại Kh&aacute;ch sạn 4 sao ở Đ&agrave; Nẵng.</p>\n', 'ĐÀ NẴNG – BÀ NÀ HILLS'),
('5033e660-c773-4c3f-8878-52d6b66e3f50', 4, '<p><strong>S&aacute;ng:</strong>&nbsp;Đo&agrave;n d&ugrave;ng bữa s&aacute;ng tại kh&aacute;ch sạn</p>\n\n<p>Xe đưa đo&agrave;n đi&nbsp;<strong>B&aacute;n Đảo Sơn Tr&agrave;</strong>&nbsp;&ndash; thưởng ngoạn to&agrave;n cảnh phố biển Đ&agrave; Nẵng tr&ecirc;n cao. Xe đưa qu&yacute; kh&aacute;ch dọc theo triền n&uacute;i Đ&ocirc;ng Nam để chi&ecirc;m ngưỡng vẻ đẹp tuyệt mỹ của biển Đ&agrave; Nẵng, viếng&nbsp;<strong>Linh Ứng Tự</strong>&nbsp;&ndash; nơi c&oacute; tượng Phật B&agrave; 65m cao nhất Việt Nam.</p>\n\n<p>Qu&yacute; kh&aacute;ch tự do đi chợ, đi mua sắm qu&agrave; về cho người th&acirc;n v&agrave; gia đình</p>\n\n<p><strong>10h30:</strong>&nbsp;Qu&yacute; kh&aacute;ch l&agrave;m thủ tục trả ph&ograve;ng kh&aacute;ch sạn. Xe v&agrave; HDV đưa đo&agrave;n đi d&ugrave;ng bữa trưa</p>\n\n<p>Sau đ&oacute;: Xe đưa đo&agrave;n ra s&acirc;n bay đ&aacute;p chuyến bay&nbsp;<strong>VN170</strong>&nbsp;l&uacute;c&nbsp;<strong>13h45</strong>&nbsp;về H&agrave; Nội.</p>\n\n<p><strong>15h10:</strong>&nbsp;Đến s&acirc;n bay Nội B&agrave;i, Xe v&agrave; HDV đ&oacute;n đo&agrave;n v&agrave; đưa đo&agrave;n về lại điểm đ&oacute;n ban đầu. Kết th&uacute;c chương trình tham quan!</p>\n\n<p><em><strong>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</strong></em></p>\n', 'BÁN ĐẢO SƠN TRÀ – HÀ NỘI'),
('05788742-5379-4587-a915-8d8ddf501a91', 1, '<p><em><strong>TOUR KHỞI H&Agrave;NH MỖI THỨ 5 TRONG TUẦN</strong></em></p>\n\n<p>Tập trung tại s&acirc;n bay T&acirc;n Sơn Nhất đ&aacute;p chuyến bay TP. HCM &ndash; H&Agrave; NỘI.&nbsp;<em>(C&aacute;c chuyến bay dự kiến từ 06:00 &ndash; 09:00)</em>. Xe v&agrave; HDV đ&oacute;n kh&aacute;ch tại s&acirc;n bay Nội B&agrave;i, đo&agrave;n di chuyển về trung t&acirc;m H&agrave; Nội.</p>\n\n<p>Ăn trưa. Nhận ph&ograve;ng kh&aacute;ch sạn. Khởi h&agrave;nh tham quan:</p>\n\n<ul>\n	<li><strong>Hồ T&acirc;y</strong>&nbsp;&ndash; Hồ lớn nhất H&agrave; Nội,&nbsp;<strong>ch&ugrave;a Trấn Quốc, Hồ Gươm</strong>,&nbsp;<strong>đền Ngọc Sơn, cầu Th&ecirc; H&uacute;c</strong>, chụp h&igrave;nh lưu niệm tại&nbsp;<strong>Nh&agrave; Thờ Lớn, Nh&agrave; H&aacute;t Lớn Th&agrave;nh phố</strong>.</li>\n	<li>Thưởng thức&nbsp;<strong>b&aacute;nh cốm H&agrave;ng Than, kem Tr&agrave;ng Tiền</strong>&nbsp;&ndash; N&eacute;t văn h&oacute;a ẩm thực đặc trưng rất ri&ecirc;ng của H&agrave; Nội.</li>\n</ul>\n\n<p>Ăn tối với buffet. Tự do kh&aacute;m ph&aacute; H&agrave; Nội về đ&ecirc;m. Nghỉ đ&ecirc;m tại H&agrave; Nội.</p>\n', 'TP. HỒ CHÍ MINH – HÀ NỘI'),
('05788742-5379-4587-a915-8d8ddf501a91', 2, '<p>Ăn s&aacute;ng. L&agrave;m thủ tục trả ph&ograve;ng. Khởi h&agrave;nh tham quan:&nbsp;</p>\n\n<ul>\n	<li><strong>Khu du lịch Tr&agrave;ng An &ndash; Ninh B&igrave;nh</strong>&nbsp;đ&atilde; được UNESCO c&ocirc;ng nhận l&agrave; Di sản Văn h&oacute;a v&agrave; Thi&ecirc;n nhi&ecirc;n Thế giới v&agrave;o năm 2014. Qu&yacute; kh&aacute;ch ngồi tr&ecirc;n thuyền du ngoạn kh&aacute;m ph&aacute; vẻ đẹp hoang sơ được v&iacute; như &ldquo;Vịnh Hạ Long tr&ecirc;n cạn&rdquo; với v&ocirc; v&agrave;n c&aacute;c hạng động, hệ thống s&ocirc;ng, suối chảy tr&agrave;n trong c&aacute;c thung lũng, c&aacute;c hang xuy&ecirc;n thủy động, c&aacute;c d&atilde;y n&uacute;i đ&aacute; v&ocirc;i tr&ugrave;ng điệp.</li>\n</ul>\n\n<p>Ăn trưa với đặc sản&nbsp;<strong>cơm ch&aacute;y</strong>&nbsp;<strong>D&ecirc; N&uacute;i</strong>.</p>\n\n<ul>\n	<li>Tham quan&nbsp;<strong>Ch&ugrave;a B&aacute;i Đ&iacute;nh</strong>,&nbsp;ng&ocirc;i ch&ugrave;a lớn nổi tiếng miền Bắc, l&agrave;m lễ cầu ph&uacute;c l&agrave;nh. Rời Ninh B&igrave;nh, theo quốc lộ 10 đi Hạ Long ngang qua c&aacute;c tỉnh Nam Định, Th&aacute;i B&igrave;nh, Hải Ph&ograve;ng.</li>\n</ul>\n\n<p>Rời&nbsp;<strong>Ninh B&igrave;nh</strong>, theo quốc lộ 10 đi Hạ Long. Tr&ecirc;n đường đi qu&yacute; kh&aacute;ch sẽ được cảm nhận cuộc sống, phong cảnh đặc trưng của đồng bằng Bắc Bộ với cảnh l&agrave;ng qu&ecirc; thanh b&igrave;nh qua c&aacute;c tỉnh Nam Định, Th&aacute;i B&igrave;nh, Hải Ph&ograve;ng.</p>\n\n<p>Ăn tối tại Hạ Long. Tự do kh&aacute;m ph&aacute;&nbsp;<strong>chợ đ&ecirc;m Hạ Long</strong>. Nghỉ đ&ecirc;m tại Hạ Long.</p>\n', 'HÀ NỘI – TRÀNG AN – BÁI ĐÍNH – HẠ LONG'),
('05788742-5379-4587-a915-8d8ddf501a91', 3, '<p>Ăn s&aacute;ng. Trả ph&ograve;ng kh&aacute;ch sạn. Khởi h&agrave;nh tham quan:</p>\n\n<ul>\n	<li><strong>Vịnh Hạ Long</strong>&nbsp;&ndash; Di sản thi&ecirc;n nhi&ecirc;n Thế giới được UNESCO c&ocirc;ng nhận. Chi&ecirc;m ngưỡng vẻ đẹp huyền b&iacute; của h&agrave;ng ng&agrave;n đảo đ&aacute; v&agrave; c&aacute;c hang động kỳ th&uacute;. Tham quan động Thi&ecirc;n Cung, ngắm cảnh L&agrave;ng Ch&agrave;i, h&ograve;n Ấm, h&ograve;n R&ugrave;a, h&ograve;n Đỉnh Hương, h&ograve;n Ch&oacute; Đ&aacute;, h&ograve;n G&agrave; Chọi,&hellip;</li>\n</ul>\n\n<p>Ăn trưa. Chiều tham quan:&nbsp;</p>\n\n<ul>\n	<li><strong>Khu danh thắng đất Phật Y&ecirc;n Tử</strong>&nbsp;&ndash; đất tổ của Thiền Ph&aacute;i Tr&uacute;c L&acirc;m. Qu&yacute; kh&aacute;ch đi c&aacute;p treo, tham quan Vườn Th&aacute;p Tổ, Ch&ugrave;a Hoa Y&ecirc;n&nbsp;<em><strong>(đ&atilde; bao gồm v&eacute; c&aacute;p treo Y&ecirc;n Tử chặng Hoa Y&ecirc;n)</strong></em>.</li>\n</ul>\n\n<p>Sau đ&oacute; khởi h&agrave;nh về H&agrave; Nội, tr&ecirc;n đường dừng ch&acirc;n thưởng thức đặc sản&nbsp;<strong>b&aacute;nh đậu xanh Hải Dương</strong>.</p>\n\n<p>Di chuyển về H&agrave; Nội, tr&ecirc;n đường dừng ch&acirc;n thưởng thức đặc sản&nbsp;<strong>b&aacute;nh đậu xanh Hải Dương.</strong></p>\n\n<p>Ăn tối với đặc sản<strong>&nbsp;b&uacute;n chả H&agrave; Nội</strong>. Nhận ph&ograve;ng, nghỉ ngơi.</p>\n\n<p>Tự do tham gia kh&ocirc;ng gian văn h&oacute;a H&agrave; Th&agrave;nh, kh&aacute;m ph&aacute; ẩm thực phố cổ (<em><strong>chi ph&iacute; tự t&uacute;c</strong></em>,&nbsp;<em>chợ diễn ra v&agrave;o tối thứ 6,7,CN h&agrave;ng tuần</em>). Nghỉ đ&ecirc;m tại H&agrave; Nội.</p>\n', 'HẠ LONG – YÊN TỬ - HÀ NỘI'),
('05788742-5379-4587-a915-8d8ddf501a91', 4, '<p>Ăn s&aacute;ng. Trả ph&ograve;ng kh&aacute;ch sạn. Khởi h&agrave;nh tham quan:</p>\n\n<ul>\n	<li><strong>Lăng Chủ Tịch Hồ Ch&iacute; Minh, Phủ Chủ Tịch, ch&ugrave;a Một Cột, nh&agrave; s&agrave;n, ao c&aacute; B&aacute;c Hồ&hellip;</strong></li>\n	<li><strong>Văn Miếu &ndash; Quốc Tử Gi&aacute;m</strong>&nbsp;&ndash; trường đại học đầu ti&ecirc;n của Việt Nam</li>\n</ul>\n\n<p>Ăn trưa. Khởi h&agrave;nh đi&nbsp;<strong>SaPa</strong>&nbsp;&ndash; được người Ph&aacute;p xưa gọi l&agrave; &ldquo;Kinh đ&ocirc; m&ugrave;a h&egrave; của xứ Bắc Kỳ&rdquo;, chạy theo đường cao tốc Nội B&agrave;i &ndash; L&agrave;o Cai d&agrave;i 245 km.</p>\n\n<p>Ngắm phong cảnh tuyệt đẹp tr&ecirc;n cung đường T&acirc;y Bắc, dừng ch&acirc;n nghỉ ngơi. Đến SaPa, nhận ph&ograve;ng.</p>\n\n<p>Ăn tối. Thư gi&atilde;n với liệu tr&igrave;nh massage ch&acirc;n,&nbsp;<strong>mỗi kh&aacute;ch được tặng 01 v&eacute; foot massage</strong>.</p>\n\n<p>Tự do kh&aacute;m ph&aacute; SaPa về đ&ecirc;m hoặc thưởng thức c&aacute;c m&oacute;n ăn đặc trưng của đồng b&agrave;o d&acirc;n tộc v&ugrave;ng T&acirc;y Bắc:&nbsp;<em>Thắng Cố, cơm lam, lợn cắp n&aacute;ch, khoai nướng, bắp nướng&hellip;</em></p>\n', 'HÀ NỘI – LÀO CAI – SAPA'),
('05788742-5379-4587-a915-8d8ddf501a91', 5, '<p>Ăn s&aacute;ng. Khởi h&agrave;nh tham quan&nbsp;<strong>bản C&aacute;t C&aacute;t</strong>. Tản bộ kh&aacute;m ph&aacute;&nbsp;<strong>bản C&aacute;t C&aacute;t</strong>&nbsp;của người H&rsquo;M&ocirc;ng, được người Ph&aacute;p ph&aacute;t hiện v&agrave; chọn l&agrave;m khu nghỉ dưỡng cho c&aacute;c quan chức thuộc địa từ đầu thế kỷ XX. Đến với bản C&aacute;t C&aacute;t, du kh&aacute;ch tham quan:&nbsp;<em>trạm thủy điện C&aacute;t C&aacute;t, th&aacute;c nước C&aacute;t C&aacute;t&hellip;</em></p>\n\n<p>Ăn trưa. Di chuyển đến ga c&aacute;p treo Fansipan bắt đầu h&agrave;nh tr&igrave;nh&nbsp;<strong>chinh phục Fansipan</strong>&nbsp;bằng hệ thống c&aacute;p treo 3 d&acirc;y hiện đại nhất thế giới với cabin c&oacute; sức chứa tới 35 kh&aacute;ch. Vượt qua 639 bậc thang chinh phục đỉnh Fansipan tr&ecirc;n độ cao 3.143m &ndash;&nbsp;<em>n&oacute;c nh&agrave; của Đ&ocirc;ng Dương.</em>&nbsp;<strong><em>(chi ph&iacute; c&aacute;p treo tự t&uacute;c)</em></strong></p>\n\n<p>Ăn tối. Tự do kh&aacute;m ph&aacute; SaPa về đ&ecirc;m. Nghỉ đ&ecirc;m tại SaPa.</p>\n', 'SAPA – BẢN CÁT CÁT – FANSIPAN'),
('05788742-5379-4587-a915-8d8ddf501a91', 6, '<p>Ăn s&aacute;ng. Trả ph&ograve;ng kh&aacute;ch sạn. Khởi h&agrave;nh về lại H&agrave; Nội.</p>\n\n<p>Ăn trưa. Di chuyển ra s&acirc;n bay Nội B&agrave;i đ&aacute;p chuyến bay H&Agrave; NỘI &ndash; TP. HCM.</p>\n\n<p><em>(C&aacute;c chuyến bay dự kiến từ 16:30 &ndash; 17:30).</em></p>\n\n<p>Kết th&uacute;c chương tr&igrave;nh tham quan!</p>\n\n<p><strong><em>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</em></strong></p>\n', 'SAPA - HÀ NỘI – TP.HCM'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 1, '<p><strong>04h50:</strong>&nbsp;Xe đ&oacute;n Qu&yacute; kh&aacute;ch tại Cổng Rạp Xiếc Trung Ương &ndash; Đường Trần Nh&acirc;n T&ocirc;ng &ndash; Quận Hai B&agrave; Trưng &ndash; Tp H&agrave; Nội, khởi h&agrave;nh l&ecirc;n s&acirc;n bay Nội B&agrave;i l&agrave;m thủ tục chuyến bay&nbsp;<strong>VN1237</strong><strong>&nbsp;(07:25 &ndash; 09:30)&nbsp;</strong>đi Ph&uacute; Quốc. (Qu&yacute; kh&aacute;ch tự t&uacute;c ăn s&aacute;ng)</p>\n\n<p><strong>09h30:</strong>&nbsp;Đến S&acirc;n bay Ph&uacute; Quốc, xe v&agrave; HDV đ&oacute;n du kh&aacute;ch tại S&acirc;n bay Ph&uacute; Quốc, Xe v&agrave; Hướng dẫn vi&ecirc;n đưa Qu&yacute; kh&aacute;ch gh&eacute; thăm Ch&ugrave;a Sư Mu&ocirc;n v&agrave; khu du lịch Suối Tranh &ndash; đo&agrave;n tự do tham quan, lễ Ch&ugrave;a v&agrave; chụp ảnh.</p>\n\n<p><strong>Trưa:</strong>&nbsp;Đo&agrave;n ăn trưa tại nh&agrave; h&agrave;ng, sau đ&oacute; về kh&aacute;ch sạn nhận ph&ograve;ng, nghỉ ngơi.</p>\n\n<p><strong>Chiều</strong>: Khởi h&agrave;nh tham quan ph&iacute;a Đ&ocirc;ng đảo:</p>\n\n<ul>\n	<li><strong>Dinh B&agrave; &ndash; Dinh Cậu</strong>: L&agrave; nơi thờ người c&oacute; c&ocirc;ng khai hoang Ph&uacute; Quốc &ndash; Thủy n&ocirc;ng Th&aacute;nh mẫu c&ugrave;ng hai người con trai l&agrave; Cậu T&agrave;i, Cậu Qu&yacute;.</li>\n	<li><strong>Vườn ti&ecirc;u</strong>: với những nọc ti&ecirc;u thẳng tắp, xanh mơn mỡn, nổi tiếng chắc hạt, thơm ngon.</li>\n	<li>Tham quan&nbsp;<strong>trại nu&ocirc;i mật ong</strong>: t&igrave;m hiểu cuộc sống cần c&ugrave; của những ch&uacute; ong b&eacute; nhỏ v&agrave; học c&aacute;ch lấy mật ong (quay mật) của người d&acirc;n. Đến m&ugrave;a tr&aacute;i c&acirc;y, Qu&yacute; kh&aacute;ch c&ograve;n được chi&ecirc;m ngưỡng v&agrave; tận tay h&aacute;i c&aacute;c loại sầu ri&ecirc;ng, ch&ocirc;m ch&ocirc;m &hellip;.trĩu quả tr&ecirc;n c&agrave;nh</li>\n	<li><strong>Ngọc trai cao cấp Ph&uacute; Quốc</strong>: Qu&yacute; kh&aacute;ch được trực tiếp tham gia quy tr&igrave;nh nu&ocirc;i trai lấy ngọc tại c&aacute;c cơ sở nu&ocirc;i cấy ngọc trai chuy&ecirc;n nghiệp tại Ph&uacute; Quốc.</li>\n</ul>\n\n<p>Sau đ&oacute; qu&yacute; kh&aacute;ch c&oacute; thể chọn th&ecirc;m chương tr&igrave;nh tham quan khu check in nổi tiếng&nbsp;<strong>Sunset Sanato</strong>&nbsp;tại Ph&uacute; Quốc (chi ph&iacute; tự t&uacute;c &ndash; gi&aacute; xe tham khảo: 400.000đ/xe 16 chỗ, v&eacute; tham khảo 100,000/v&eacute;). Ngắm ho&agrave;ng h&ocirc;n tuyệt đẹp tr&ecirc;n Th&agrave;nh phố biển xinh đẹp ắt hẳn sẽ l&agrave; một trải nghiệm tuyệt vời cho du kh&aacute;ch đến với Ph&uacute; Quốc.</p>\n\n<p><strong>Tối:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn tối tại nh&agrave; h&agrave;ng, về kh&aacute;ch sạn nghỉ ngơi. Tự do dạo b&atilde;i biển, thưởng thức kh&ocirc;ng kh&iacute; y&ecirc;n tĩnh tuyệt vời của huyện đảo hoặc đăng k&yacute; tour gh&eacute;p c&acirc;u mực đ&ecirc;m (Chi ph&iacute; tự t&uacute;c).</p>\n\n<p><em><strong>Nghỉ đ&ecirc;m tại kh&aacute;ch sạn 4 sao.</strong></em></p>\n', 'HÀ NỘI – PHÚ QUỐC – ĐÔNG ĐẢO'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 2, '<p><strong>S&aacute;ng:</strong>&nbsp;Qu&yacute; kh&aacute;ch dậy sớm ngắm cảnh b&igrave;nh minh tr&ecirc;n Đảo Ngọc, d&ugrave;ng điểm t&acirc;m s&aacute;ng tại kh&aacute;ch sạn. Xe v&agrave;</p>\n\n<p>Hướng dẫn vi&ecirc;n đ&oacute;n Q&uacute;y kh&aacute;ch bắt đầu kh&aacute;m ph&aacute; Nam đảo:</p>\n\n<ul>\n	<li><strong>Khu di t&iacute;ch lịch sử Nh&agrave; T&ugrave; Ph&uacute; Quốc</strong>: Được mệnh danh l&agrave; địa ngục trần gian, nơi đ&atilde; từng trải qua hai thời kỳ thực d&acirc;n Ph&aacute;p v&agrave; đế quốc Mỹ.</li>\n	<li>Xe đưa Qu&yacute; kh&aacute;ch đến cảng biển, l&ecirc;n t&agrave;u v&agrave; bắt đầu cuộc trải nghiệm h&agrave;nh tr&igrave;nh C&acirc;u c&aacute; lặn ngắm san h&ocirc; tr&ecirc;n t&agrave;u. Q&uacute;y kh&aacute;ch được cung cấp thiết bị c&acirc;u c&aacute; (d&acirc;y c&acirc;u, mồi) v&agrave; được trang bị &aacute;o phao, k&iacute;nh lặn, ống thở khi tham gia lặn biển ngắm san h&ocirc;.</li>\n</ul>\n\n<p><strong>11h30:</strong>&nbsp;Qu&yacute; kh&aacute;ch được trải nghiệm bữa ăn tr&ecirc;n t&agrave;u.</p>\n\n<p><strong>Chiều:</strong>&nbsp;Qu&yacute; kh&aacute;ch trở lại cảng biển v&agrave; tiếp tục h&agrave;nh tr&igrave;nh:</p>\n\n<ul>\n	<li><strong>B&atilde;i Sao:</strong>&nbsp;một trong những b&atilde;i biển đẹp nhất của Ph&uacute; Quốc với l&agrave;n nước xanh biếc, bờ c&aacute;t trắng mịn nổi tiếng tại Ph&uacute; Quốc. Đến B&atilde;i Sao, Qu&yacute; kh&aacute;ch tự do nghỉ ngơi, vui chơi v&agrave; tắm biển (ph&iacute; tắm nước ngọt + v&otilde;ng nằm: chi ph&iacute; tự t&uacute;c).</li>\n	<li><strong>Ch&ugrave;a Hộ Quốc (Thiền Viện Tr&uacute;c L&acirc;m Hộ Quốc)</strong>: một địa điểm tham quan t&acirc;m linh mới của Ph&uacute; Quốc.</li>\n	<li><strong>Check-in Địa Trung Hải Ph&uacute; Quốc, Đấu Trường La M&atilde;, Cầu H&ocirc;n Kiss Brridge tại thị trấn Ho&agrave;ng h&ocirc;n.</strong>&nbsp;Đ&acirc;y l&agrave; điểm check in mới được thiết kế với nguồn cảm hứng từ chuyện t&igrave;nh Ngưu Lang Chức Nữ kết hợp văn h&oacute;a Việt Nam v&agrave; Italia ngay tại đảo Ngọc Ph&uacute; Quốc. (Chi ph&iacute; v&eacute; tham quan tự t&uacute;c)</li>\n</ul>\n\n<p><strong>* Option th&ecirc;m:</strong>&nbsp;Xem show diễn mang t&ecirc;n&nbsp;<strong>&ldquo;Kiss the Stars&rdquo;&nbsp;</strong>&ndash; Nụ H&ocirc;n giữa Ng&agrave;n Sao sẽ đưa 5.000 kh&aacute;n giả đi xuy&ecirc;n qua dải ng&acirc;n h&agrave; để theo ch&acirc;n cuộc t&igrave;nh của Mộc V&agrave; Kim c&ugrave;ng những người bạn chiến đấu chống lại mối đe dọa từ vũ trụ. (Chi ph&iacute; tự t&uacute;c)</p>\n\n<p><strong>Tối:</strong>&nbsp;Qu&yacute; kh&aacute;ch d&ugrave;ng cơm tối tại nh&agrave; h&agrave;ng. Tự do tham quan Ph&uacute; Quốc về đ&ecirc;m.</p>\n\n<p><em><strong>Nghỉ đ&ecirc;m tại kh&aacute;ch sạn 4 sao.</strong></em></p>\n', 'KHÁM PHÁ NAM ĐẢO'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 3, '<p><strong>07h00:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn s&aacute;ng tại kh&aacute;ch sạn.&nbsp;</p>\n\n<p><strong>08h00:&nbsp;</strong>Xe v&agrave; HDV đưa qu&yacute; kh&aacute;ch khởi h&agrave;nh đi Bắc Đảo.</p>\n\n<ul>\n	<li><strong>Đền thờ anh h&ugrave;ng d&acirc;n tộc Nguyễn Trung Trực</strong>: để tưởng nhớ vị anh h&ugrave;ng đ&atilde; xả th&acirc;n v&igrave; nước với tinh thần bất khuất, ki&ecirc;n cường.</li>\n	<li><strong>Đến Mũi G&agrave;nh Dầu</strong>: tận hưởng vẻ đẹp thuần khiết của B&atilde;i D&agrave;i, b&atilde;i biển được xem l&agrave; hoang sơ nhất Ph&uacute; Quốc. Ngắm nh&igrave;n nơi xa xa h&ograve;n đảo của đất nước l&aacute;ng giềng Campuchia.</li>\n	<li><strong>Kh&aacute;m ph&aacute; Th&agrave;nh phố kh&ocirc;ng ngủ &ndash; Grand World Ph&uacute; Quốc</strong>&nbsp;(miễn ph&iacute; v&eacute; v&agrave;o cổng): một trong những điểm hot nhất Ph&uacute; Quốc hiện nay như:</li>\n	<li><em>Kiến tr&uacute;c nh&agrave; Tre độc đ&aacute;o: C&ocirc;ng tr&igrave;nh nghệ thuật l&agrave;m từ 32.000 c&acirc;y tre, đậm n&eacute;t Quốc Hồn Quốc T&uacute;y.</em></li>\n	<li><em>Show diễn Tinh Hoa Việt Nam: Mini show diễn ra từ 9h00 &ndash; 18h30, t&aacute;i hiện tinh hoa d&acirc;n tộc.</em></li>\n	<li><em>Du ngoạn d&ograve;ng s&ocirc;ng Venice (&Yacute;).</em></li>\n	<li><em>Khu phố đ&egrave;n lồng Shanghai (Trung Quốc).</em></li>\n	<li><em>Khu m&aacute;i v&ograve;m &aacute;nh s&aacute;ng đổi m&agrave;u độc đ&aacute;o phong c&aacute;ch Clarke Quay (Singapore).</em></li>\n	<li><em>Tham quan c&ocirc;ng vi&ecirc;n T&igrave;nh Y&ecirc;u.</em></li>\n	<li><em>C&ocirc;ng vi&ecirc;n nghệ thuật đương đại Urban Park.</em></li>\n	<li><em>Th&aacute;p Hẹn h&ograve;.</em></li>\n	<li><em>Bảo t&agrave;ng Gấu Teddy.</em></li>\n</ul>\n\n<p><em>(Qu&yacute; kh&aacute;ch tự t&uacute;c c&aacute;c chi ph&iacute; tham quan tại Grand Word theo quy định của Vin Group như: Show diễn Tinh Hoa Việt Nam: 300.000đ/kh&aacute;ch; Bảo t&agrave;ng gấu Teddy: 200.000đ/kh&aacute;ch; Du thuyền tr&ecirc;n s&ocirc;ng Venice: 200.000đ/kh&aacute;ch)</em></p>\n\n<p><strong>Trưa:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn trưa tại nh&agrave; h&agrave;ng</p>\n\n<p><strong>Chiều:</strong>&nbsp;Sau bữa trưa đo&agrave;n khởi h&agrave;nh về lại kh&aacute;ch sạn, chiều tự do nghỉ ngơi. Hoặc tr&ecirc;n đường về qu&yacute; kh&aacute;ch c&oacute; thể lựa chọn đi:</p>\n\n<ul>\n	<li><strong>Vinpearl Wonder Ph&uacute; Quốc</strong>&nbsp;(chi ph&iacute; tự t&uacute;c): l&agrave; c&ocirc;ng vi&ecirc;n chủ đề với hơn 100 tr&ograve; chơi hấp dẫn với những trải nghiệm hấp dẫn với diện t&iacute;ch si&ecirc;u khủng l&ecirc;n tới gần 50ha với 6 ph&acirc;n khu chủ đề đặc sắc lần đầu ti&ecirc;n xuất hiện tại Việt Nam.</li>\n	<li><em>Khu cảm gi&aacute;c mạnh &ndash; Thế giới phi&ecirc;u lưu</em></li>\n	<li><em>Khu c&ocirc;ng vi&ecirc;n nước &ndash; Thế giới lốc xo&aacute;y</em></li>\n	<li><em>Khu l&acirc;u đ&agrave;i trung t&acirc;m &ndash; Ch&acirc;u &Acirc;u trung cổ</em></li>\n	<li><em>Khu cổ t&iacute;ch &ndash; Thế giới diệu kỳ</em></li>\n	<li><em>Khu Viking &ndash; Ng&ocirc;i l&agrave;ng b&iacute; mật</em></li>\n	<li><em>Cung điện hải vương</em></li>\n</ul>\n\n<p>Qu&yacute; kh&aacute;ch thưởng thức s&acirc;n khấu nhạc nước ho&agrave;nh tr&aacute;ng nhất Đ&ocirc;ng Nam Á, biểu diễn c&aacute; heo, chương tr&igrave;nh n&agrave;ng ti&ecirc;n c&aacute;, lễ hội đường phố, hoạt n&aacute;o đường phố.</p>\n\n<ul>\n	<li><strong>Vinpearl Safari</strong>&nbsp;vườn th&uacute; lớn thứ 2 Đ&ocirc;ng Nam &Aacute; (chi ph&iacute; tự t&uacute;c): l&agrave; c&ocirc;ng vi&ecirc;n sở th&uacute; hoang d&atilde; đầu ti&ecirc;n tại Việt Nam. Tại đ&acirc;y, du kh&aacute;ch sẽ được trải nghiệm m&ocirc; h&igrave;nh sở th&uacute; c&oacute; 1-0-2, check-in sống ảo c&ugrave;ng c&aacute;c lo&agrave;i động vật qu&yacute; hiếm hay chi&ecirc;m ngưỡng những m&agrave;n biểu diễn đỉnh cao.</li>\n</ul>\n\n<p><strong>Tối:</strong>&nbsp;Qu&yacute; kh&aacute;ch&nbsp;<strong>tự t&uacute;c</strong>&nbsp;bữa tối, thưởng thức c&aacute;c đặc sản của Ph&uacute; Quốc.</p>\n\n<p>Tự do tham quan Chợ Đ&ecirc;m Ph&uacute; Quốc<br />\nvề đ&ecirc;m.</p>\n\n<p><em><strong>Nghỉ ngơi tại kh&aacute;ch sạn.</strong></em></p>\n', 'KHÁM PHÁ BẮC ĐẢO PHÚ QUỐC'),
('3babf353-d8f0-41f7-bd95-e3c956f75a18', 4, '<p><strong>S&aacute;ng</strong>: Qu&yacute; kh&aacute;ch ăn s&aacute;ng tại kh&aacute;ch sạn sau đ&oacute; tự do tắm biển, nghỉ ngơi v&agrave; dọn đồ, trả ph&ograve;ng kh&aacute;ch sạn.</p>\n\n<p><strong>07h30:</strong>&nbsp;Đo&agrave;n l&ecirc;n xe đi s&acirc;n bay Dương Đ&ocirc;ng, đ&aacute;p chuyến bay khởi h&agrave;nh&nbsp;<strong>VN1236</strong>&nbsp;<strong>(10:05 &ndash; 12:05)</strong>&nbsp;về H&agrave; Nội (Qu&yacute; kh&aacute;ch tự t&uacute;c ăn trưa)</p>\n\n<p><strong>12h05</strong>: Đến&nbsp;<strong>S&acirc;n bay Nội B&agrave;i</strong>, xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n qu&yacute; kh&aacute;ch về lại trung t&acirc;m th&agrave;nh phố. Chia tay qu&yacute; kh&aacute;ch v&agrave; hẹn gặp lại Qu&yacute; kh&aacute;ch!</p>\n\n<p><em><strong>Ghi ch&uacute;: Lịch tr&igrave;nh thăm quan c&oacute; thể thay đổi linh hoạt theo giờ bay v&agrave; theo thực tế nhưng vẫn đảm bảo đầy đủ c&aacute;c điểm theo chương tr&igrave;nh.</strong></em></p>\n\n<p><em><strong>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</strong></em></p>\n', 'TẠM BIỆT PHÚ QUỐC'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 1, '<p>Tập trung tại s&acirc;n bay T&acirc;n Sơn Nhất đ&aacute;p chuyến bay TP. HCM &ndash; H&Agrave; NỘI.&nbsp;<em>(C&aacute;c chuyến bay dự kiến từ 06:00 &ndash; 09:00)</em>. Xe v&agrave; HDV đ&oacute;n kh&aacute;ch tại s&acirc;n bay Nội B&agrave;i, đo&agrave;n di chuyển về trung t&acirc;m H&agrave; Nội.</p>\n\n<p>Ăn trưa. Nhận ph&ograve;ng kh&aacute;ch sạn. Khởi h&agrave;nh tham quan:</p>\n\n<ul>\n	<li><strong>Hồ T&acirc;y</strong>&nbsp;&ndash; Hồ lớn nhất H&agrave; Nội,&nbsp;<strong>ch&ugrave;a Trấn Quốc, Hồ Gươm, đền Ngọc Sơn, cầu Th&ecirc; H&uacute;c</strong>, chụp h&igrave;nh lưu niệm tại&nbsp;<strong>Nh&agrave; Thờ Lớn, Nh&agrave; H&aacute;t Lớn</strong>&nbsp;th&agrave;nh phố.</li>\n	<li>Thưởng thức<strong>&nbsp;b&aacute;nh cốm H&agrave;ng Than, kem Tr&agrave;ng Tiền</strong>&nbsp;&ndash; N&eacute;t văn h&oacute;a ẩm thực đặc trưng rất ri&ecirc;ng của H&agrave; Nội.</li>\n</ul>\n\n<p>Ăn tối. Tự do kh&aacute;m ph&aacute; H&agrave; Nội về đ&ecirc;m. Nghỉ đ&ecirc;m tại H&agrave; Nội.</p>\n', 'TP. HCM – HÀ NỘI'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 2, '<p>Ăn s&aacute;ng. Trả ph&ograve;ng. Tham quan&nbsp;<strong>Lăng B&aacute;c, thăm Phủ Chủ Tịch, nh&agrave; s&agrave;n, ao c&aacute; B&aacute;c Hồ, Ch&ugrave;a&nbsp;Một Cột, Văn Miếu &ndash; Quốc Tử Gi&aacute;m</strong>&nbsp;&ndash; trường đại học đầu ti&ecirc;n của Việt Nam.</p>\n\n<p>Ăn trưa. Khởi h&agrave;nh đi&nbsp;<strong>SaPa</strong>&nbsp;&ndash; nơi m&agrave; người Ph&aacute;p xưa gọi l&agrave; &ldquo;Kinh đ&ocirc; m&ugrave;a h&egrave; của xứ Bắc Kỳ&rdquo; theo tuyến đường cao tốc d&agrave;i nhất Việt Nam 250km. Đến L&agrave;o Cai, dừng ch&acirc;n chụp ảnh lưu niệm tại&nbsp;<strong>cột mốc bi&ecirc;n giới 102</strong>&nbsp;&ndash; cửa khẩu Quốc tế L&agrave;o Cai. Đến SaPa, nhận ph&ograve;ng kh&aacute;ch sạn.</p>\n\n<p>Ăn tối. Thư gi&atilde;n với liệu tr&igrave;nh massage ch&acirc;n,&nbsp;<em><strong>mỗi kh&aacute;ch được tặng 01 v&eacute; foot massage.</strong></em></p>\n\n<p>Tự do kh&aacute;m ph&aacute; phố n&uacute;i về đ&ecirc;m, tự do thưởng thức: Thắng Cố, cơm lam, lợn cắp n&aacute;ch, khoai nướng, bắp nướng&hellip; Nghỉ đ&ecirc;m tại thị trấn SaPa.&nbsp;</p>\n', 'HÀ NỘI – LÀO CAI – SAPA'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 3, '<p>Ăn s&aacute;ng. Tham quan&nbsp;<strong>Bản C&aacute;t C&aacute;t</strong>&nbsp;&ndash; địa b&agrave;n cư tr&uacute; của người H&rsquo;M&ocirc;ng, ngắm cảnh h&ugrave;ng vĩ của n&uacute;i rừng T&acirc;y Bắc, tham quan th&aacute;c thuỷ điện C&aacute;t C&aacute;t do người Ph&aacute;p x&acirc;y dựng.</p>\n\n<p>Ăn trưa. Di chuyển đến ga c&aacute;p treo Fansipan bắt đầu h&agrave;nh tr&igrave;nh&nbsp;<strong>chinh phục Fansipan</strong>&nbsp;bằng hệ thống c&aacute;p treo 3 d&acirc;y hiện đại nhất thế giới với cabin c&oacute; sức chứa tới 35 kh&aacute;ch. Vượt qua 639 bậc thang chinh phục đỉnh Fansipan tr&ecirc;n độ cao 3.143m &ndash; n&oacute;c nh&agrave; của Đ&ocirc;ng Dương.&nbsp;<em><strong>(chi ph&iacute; c&aacute;p treo tự t&uacute;c)</strong></em></p>\n\n<p>Ăn tối. Tự do kh&aacute;m ph&aacute; SaPa về đ&ecirc;m. Nghỉ đ&ecirc;m tại thị trấn SaPa.</p>\n', 'SAPA – BẢN CÁT CÁT – FANSIPAN'),
('f5fbffc4-86f3-4553-914b-393722c310ad', 4, '<p>Ăn s&aacute;ng. Trả ph&ograve;ng. Khởi h&agrave;nh về lại H&agrave; Nội.</p>\n\n<p>Ăn trưa. Di chuyển ra s&acirc;n bay Nội B&agrave;i đ&aacute;p chuyến bay H&agrave; Nội &ndash; TP. HCM.</p>\n\n<p><em>(C&aacute;c chuyến bay dự kiến từ 16:30 &ndash; 17:30)</em>. Kết th&uacute;c chương tr&igrave;nh tham quan!</p>\n\n<p><em><strong>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</strong></em></p>\n', 'SAPA – HÀ NỘI – TP. HCM'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 1, '<p><strong>09h30:</strong>&nbsp;Xe v&agrave; Hướng dẫn vi&ecirc;n (HDV) đ&oacute;n Qu&yacute; kh&aacute;ch tại&nbsp;<strong>Cổng C&ocirc;ng Vi&ecirc;n Thống Nhất &ndash; Đường Trần Nh&acirc;n T&ocirc;ng &ndash; Quận Hai B&agrave; Trưng &ndash; Tp H&agrave; Nội</strong>&nbsp;khởi h&agrave;nh l&ecirc;n S&acirc;n bay Nội B&agrave;i, đo&agrave;n l&agrave;m thủ tục đ&aacute;p chuyến bay<strong>&nbsp;VN1575 (12:00 &ndash; 13:50)</strong>&nbsp;khởi h&agrave;nh đi Đ&agrave; Lạt.&nbsp;<strong><em>(Qu&yacute; kh&aacute;ch tự t&uacute;c ăn trưa tr&ecirc;n m&aacute;y bay)</em></strong></p>\n\n<p><strong>13h50:</strong>&nbsp;Đến S&acirc;n bay Li&ecirc;n Khương, xe v&agrave; HDV địa phương đ&oacute;n đo&agrave;n khởi h&agrave;nh về Bảo Lộc.</p>\n\n<p><strong>16h30:</strong>&nbsp;Đo&agrave;n đến Bảo Lộc, đo&agrave;n đi thăm quan ng&ocirc;i ch&ugrave;a nổi tiếng Linh Quy Ph&aacute;p Ấn. Nằm c&aacute;ch th&agrave;nh phố Bảo Lộc khoảng 17km về ph&iacute;a Đ&ocirc;ng Nam, Linh Quy Ph&aacute;p Ấn được bầu chọn l&agrave; 1 trong 20 địa danh du lịch nổi tiếng nhất của Đ&agrave; Lạt. L&agrave; cảnh thực trong MV Ca nhạc nổi tiếng Lạc Tr&ocirc;i của Sơn T&ugrave;ng MTP, ch&ugrave;a Linh Quy Ph&aacute;p Ấn c&agrave;ng mờ ảo hơn khi b&igrave;nh minh hoặc l&uacute;c xế chiều. Qu&yacute; kh&aacute;ch thắp hương v&atilde;n cảnh ch&ugrave;a, chụp ảnh c&ugrave;ng với Cổng Trời độc đ&aacute;o với khung cảnh ho&agrave;ng h&ocirc;n mờ ảo. Về lại trung t&acirc;m th&agrave;nh phố nhận ph&ograve;ng kh&aacute;ch sạn nghỉ ngơi.</p>\n\n<p><strong>Tối</strong>: Đo&agrave;n d&ugrave;ng bữa tối tại nh&agrave; h&agrave;ng, thưởng thức những m&oacute;n ngon miền đất cao nguy&ecirc;n Di Linh nổi tiếng. Sau bữa tối, qu&yacute; kh&aacute;ch tự do dạo chơi Tp Bảo Lộc, tận hưởng cảm gi&aacute;c se lạnh ở độ cao 900m của Th&agrave;nh phố cao nguy&ecirc;n n&agrave;y.</p>\n\n<p><em><strong>Đo&agrave;n nghỉ đ&ecirc;m tại kh&aacute;ch sạn 4 sao ở Bảo Lộc.</strong></em></p>\n', 'HÀ NỘI – ĐÀ LẠT – BẢO LỘC');
INSERT INTO `tour_itineraries` (`tour_id`, `day_number`, `description`, `title`) VALUES
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 2, '<p><strong>S&aacute;ng:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn s&aacute;ng tại kh&aacute;ch sạn, qu&yacute; kh&aacute;ch trả ph&ograve;ng kh&aacute;ch sạn, khởi h&agrave;nh đi tham quan KDL Th&aacute;c Đam B&rsquo;ri. Đam B&rsquo;ri Một ngọn th&aacute;c nổi tiếng bậc nhất của L&acirc;m Đồng, với chiều cao l&ecirc;n đến 57m. Qu&yacute; kh&aacute;ch thưởng ngoạn phong cảnh, lắng nghe &acirc;m vang của rừng n&uacute;i, với tiếng chim, tiếng mu&ocirc;n th&uacute;, h&ograve;a tiếng th&aacute;c đổ từ&nbsp;<em><strong>ngọn DamB&rsquo;ri</strong></em>&nbsp;h&ugrave;ng vỹ.</p>\n\n<ul>\n	<li>Tham quan&nbsp;<strong>th&aacute;c Dasara, vườn th&uacute;</strong>: xem ảo thuật, xiếc th&uacute; (khỉ đạp x&iacute;ch l&ocirc;, voi đ&aacute; banh, ch&oacute; l&agrave;m to&aacute;n&hellip;)</li>\n	<li>Tham quan&nbsp;<strong>L&agrave;ng d&acirc;n tộc Ch&acirc;u Mạ</strong>.</li>\n	<li>Đo&agrave;n d&ugrave;ng bữa trưa trong khu du lịch Th&aacute;c Dambri.</li>\n</ul>\n\n<p><strong>12h00:</strong>&nbsp;Đo&agrave;n khởi h&agrave;nh về lại Th&agrave;nh phố Đ&agrave; Lạt. Qu&yacute; kh&aacute;ch nghỉ ngơi tr&ecirc;n xe.</p>\n\n<p><strong>15h00</strong>: Đến Đ&agrave; Lạt, Qu&yacute; kh&aacute;ch thăm quan:</p>\n\n<ul>\n	<li><strong>Nh&agrave; thờ Domain, Biệt điện Bảo Đại (Dinh III),</strong>&nbsp;tự do tham quan chụp ảnh.</li>\n	<li><strong>Ch&ugrave;a Linh Phước:</strong>&nbsp;ng&ocirc;i ch&ugrave;a c&ograve;n c&oacute; t&ecirc;n gọi kh&aacute;c l&agrave;&nbsp;<strong>ch&ugrave;a &ldquo;Ve Chai&rdquo;</strong>&nbsp;bởi trong s&acirc;n ch&ugrave;a c&oacute; con rồng d&agrave;i 49 m&eacute;t được trang tr&iacute; bề mặt bằng cả chục ng&agrave;n vỏ chai. Đ&acirc;y l&agrave; c&ocirc;ng tr&igrave;nh kiến tr&uacute;c khảm s&agrave;nh đặc sắc của th&agrave;nh phố Đ&agrave; Lạt.</li>\n</ul>\n\n<p>Thăm quan xong, đo&agrave;n về lại Kh&aacute;ch sạn 4 sao nhận ph&ograve;ng, nghỉ ngơi.</p>\n\n<p><strong>Tối:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn tối tại nh&agrave; h&agrave;ng. Qu&yacute; kh&aacute;ch tự do kh&aacute;m ph&aacute; Đ&agrave; Lạt, thưởng thức hương vị caf&eacute; Đ&agrave; Lạt, dạo chợ mua sắm, thưởng thức sữa đậu n&agrave;nh n&oacute;ng, khoai nướng,.. hoặc đi xe đạp, xe ngưa dạo quanh<br />\nHồ Xu&acirc;n Hương.</p>\n\n<p><em><strong>Nghỉ đ&ecirc;m tại kh&aacute;ch sạn 4 sao ở Đ&agrave; Lạt.</strong></em></p>\n', 'BẢO LỘC – TP ĐÀ LẠT'),
('3bbdf626-1ba7-4549-a4e4-cde096ea04fc', 3, '<p>S&aacute;ng: Ăn s&aacute;ng v&agrave; trả ph&ograve;ng kh&aacute;ch sạn. Xe đưa đo&agrave;n đi tham quan:</p>\n\n<ul>\n	<li>KDL Th&aacute;c Datanla: một trong những th&aacute;c nước đẹp nhất Đ&agrave; Lạt với cảnh đẹp non nước hữu t&igrave;nh. Q&uacute;y kh&aacute;ch trải nghiệm đi bộ ngắm th&aacute;c nước hoặc tham gia c&aacute;c tr&ograve; chơi th&uacute; vị tại đ&acirc;y. (chi ph&iacute; tự t&uacute;c)</li>\n</ul>\n\n<p>Trưa : Qu&yacute; kh&aacute;ch ăn trưa tại nh&agrave; h&agrave;ng.</p>\n\n<p><strong>12h00:</strong>&nbsp;Qu&yacute; kh&aacute;ch l&ecirc;n &ocirc; t&ocirc; đi S&acirc;n bay Li&ecirc;n Khương đ&aacute;p chuyến bay&nbsp;<strong>VN1574</strong>&nbsp;<strong>(14:25 &ndash; 16:20)</strong>&nbsp;về H&agrave; Nội.</p>\n\n<p><strong>16h20:</strong>&nbsp;Đến S&acirc;n bay Nội B&agrave;i, xe v&agrave; hướng dẫn vi&ecirc;n đưa đo&agrave;n về điểm đ&oacute;n ban đầu. Kết th&uacute;c chương tr&igrave;nh.</p>\n\n<p>Hẹn gặp lại qu&yacute; kh&aacute;ch.</p>\n\n<p><strong><em>C&aacute;c mốc thời gian c&oacute; gi&aacute; trị tham khảo, t&ugrave;y theo điều kiện thực tế m&agrave; lịch tr&igrave;nh c&oacute; thể thay đổi cho ph&ugrave; hợp.</em></strong></p>\n', 'ĐÀ LẠT – HÀ NỘI'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 1, '<p><strong>5:30:</strong>&nbsp;Xe v&agrave; Hướng dẫn vi&ecirc;n đ&oacute;n Qu&yacute; kh&aacute;ch tại c&ocirc;ng ty Vietravel (Số 3, Hai B&agrave; Trưng, Ho&agrave;n Kiếm, H&agrave; Nội) (Qu&yacute; kh&aacute;ch tự t&uacute;c ăn s&aacute;ng). Xe khởi h&agrave;nh đi&nbsp;<strong>H&agrave; Giang</strong>&nbsp;- v&ugrave;ng đất c&oacute; ch&egrave; shan, rượu mật ong v&agrave; thắng cố, xứ sở của đ&agrave;o phai, hoa l&ecirc;, truyền thống v&agrave; n&aacute;o nhiệt trong buổi chợ phi&ecirc;n&hellip; Tr&ecirc;n đường, Qu&yacute; kh&aacute;ch c&oacute; thể tranh thủ ngắm cảnh rừng n&uacute;i Đ&ocirc;ng bắc v&ocirc; c&ugrave;ng h&ugrave;ng vĩ v&agrave; hoang sơ. V&agrave; dọc đường đi, xe sẽ dừng nghỉ, Qu&yacute; kh&aacute;ch c&oacute; thể xuống sẽ thư gi&atilde;n v&agrave; chụp h&igrave;nh lưu niệm. Qu&yacute; kh&aacute;ch dừng ch&acirc;n tại&nbsp;<strong>th&agrave;nh phố H&agrave; Giang</strong>&nbsp;để d&ugrave;ng bữa trưa tại nh&agrave; h&agrave;ng - thưởng thức c&aacute;c m&oacute;n ăn đặc sản địa phương như:&nbsp;<em>c&aacute; suối, x&ocirc;i gạo, nếp nương ngũ sắc,...</em></p>\n\n<p>Sau đ&oacute;, Qu&yacute; kh&aacute;ch sẽ đi qua rừng th&ocirc;ng bạt ng&agrave;n đẹp nhất Việt Nam trải d&agrave;i tr&ecirc;n c&aacute;c sườn n&uacute;i cao để đến với đến<strong>&nbsp;thị trấn Y&ecirc;n Minh.</strong>&nbsp;Qu&yacute; kh&aacute;ch di chuyển đến địa phận<strong>&nbsp;Huyện Đồng Văn</strong>&nbsp;chi&ecirc;m ngưỡng vẻ đẹp Cao nguy&ecirc;n đ&aacute; được UNESCO ghi nhận l&agrave; c&ocirc;ng vi&ecirc;n địa chất To&agrave;n Cầu v&agrave;o năm 2010. Qu&yacute; kh&aacute;ch c&oacute; thể dừng ch&acirc;n thăm quan:</p>\n\n<ul>\n	<li><strong>Dốc Thẩm M&atilde;</strong>&nbsp;&ndash; những con đường đ&egrave;o kh&uacute;c khuỷu, quanh co tựa như một dải lụa uốn lượn mềm mại trải d&agrave;i theo triền n&uacute;i.</li>\n	<li><strong>Phố C&aacute;o, Sủng L&agrave;</strong>, v&agrave;o l&agrave;ng văn h&oacute;a Lũng Cẩm nơi lấy bối cảnh những thước phim nổi tiếng &ldquo;Chuyện của Pao&rdquo; - v&agrave;o m&ugrave;a đ&ocirc;ng, m&ugrave;a xu&acirc;n nở rộ những đồng hoa cải v&agrave;ng hoặc hoa đ&agrave;o Tết, qu&aacute; th&iacute;ch hợp cho những thước h&igrave;nh l&atilde;ng mạn m&agrave; đậm n&eacute;t văn h&oacute;a. Chụp h&igrave;nh giữa vườn hoa mùa xu&acirc;n b&aacute;t ng&aacute;t v&agrave; thơ mộng.</li>\n</ul>\n\n<p>Sau đ&oacute; đo&agrave;n l&ecirc;n xe tiếp tục di chuyển về<strong>&nbsp;Phố Cổ Đồng Văn/ M&egrave;o Vạc</strong>. Qu&yacute; nhận ph&ograve;ng kh&aacute;ch sạn ăn tối tự do kh&aacute;m ph&aacute; phố cổ Đồng Văn/ M&egrave;o Vạc về đ&ecirc;m.</p>\n\n<p><strong>Ngủ đ&ecirc;m tại Đồng Văn/M&egrave;o Vạc</strong></p>\n', 'Hà Nội - Quản Bạ - Yên Minh - Đồng Văn/Mèo Vạc'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 2, '<p>Qu&yacute; kh&aacute;ch thức dậy sớm ăn s&aacute;ng v&agrave; l&agrave;m thủ tục trả ph&ograve;ng. Xe đưa Qu&yacute; kh&aacute;ch theo hướng M&egrave;o Vạc dọc theo d&ograve;ng s&ocirc;ng Nho Quế, Qu&yacute; kh&aacute;ch sẽ được tham quan:</p>\n\n<p>Bức tranh sơn thủy h&ugrave;ng vĩ của một trong tứ đại đỉnh đ&egrave;o v&ugrave;ng n&uacute;i ph&iacute;a Bắc -&nbsp;<strong>M&atilde; P&iacute; L&egrave;ng</strong>&nbsp;nằm dọc theo d&ograve;ng s&ocirc;ng xanh Nho Quế chảy qua khe vực n&uacute;i Tu Sản. Tr&ecirc;n đường dừng chụp h&igrave;nh tại tượng đ&agrave;i Thanh Ni&ecirc;n trước bảo t&agrave;ng Con Đường Hạnh Ph&uacute;c, ngắm to&agrave;n cảnh d&ograve;ng<strong>&nbsp;s&ocirc;ng Nho Quế</strong>&nbsp;từ tr&ecirc;n cao.</p>\n\n<p><strong><em>Qu&yacute; kh&aacute;ch c&oacute; thể lựa chọn (chi ph&iacute; tự t&uacute;c)</em></strong><em>:&nbsp;</em><strong><em>M&atilde; P&iacute; L&egrave;ng Panorama caf&eacute;, l&ecirc;n thuyền tham quan s&ocirc;ng Nho Quế ngắm hẻm vực Tu Sản.</em></strong></p>\n\n<p>Sau khi ăn trưa, Qu&yacute; kh&aacute;ch gh&eacute; thăm:</p>\n\n<ul>\n	<li><strong>Dinh thự vua M&egrave;o Vương Ch&iacute; S&igrave;nh</strong>&nbsp;với kiến tr&uacute;c độc đ&aacute;o v&agrave; những c&acirc;u chuyện đặc sắc.</li>\n	<li><strong>Lũng C&uacute;</strong>&nbsp;- Nơi địa đầu Tổ Quốc, hay c&ograve;n được mi&ecirc;u tả l&agrave;: &ldquo;Nơi c&uacute;i mặt s&aacute;t đất, ngẩng mặt đụng trời&rdquo;. Qu&yacute; kh&aacute;ch thăm quan Cột cờ Tổ Quốc v&agrave; chụp h&igrave;nh lưu niệm. Từ cột cờ Lũng C&uacute;, Qu&yacute; kh&aacute;ch c&oacute; thể ngắm phong cảnh ruộng bậc thang đẹp mắt xen kẽ những nh&agrave; tr&igrave;nh tường của d&acirc;n tộc L&ocirc; L&ocirc; trong bản S&eacute;o Lủng b&ecirc;n dưới.</li>\n	<li>Dừng chụp ảnh<strong>&nbsp;N&uacute;i Đ&ocirc;i C&ocirc; Ti&ecirc;n</strong>&nbsp;- &ldquo;t&aacute;c phẩm nghệ thuật&rdquo; của tạo ho&aacute; ban tặng cho v&ugrave;ng đất n&agrave;y, ngo&agrave;i ra Qu&yacute; kh&aacute;ch c&ograve;n c&oacute; dịp nghe kể về truyền thuyết v&ocirc; v&ugrave;ng hấp dẫn v&agrave; th&uacute; vị về nơi đ&acirc;y.</li>\n</ul>\n\n<p>Qu&yacute; kh&aacute;ch d&ugrave;ng bữa tối tại nh&agrave; h&agrave;ng v&agrave; nhận ph&ograve;ng nghỉ ngơi.</p>\n\n<p><strong>Ngủ đ&ecirc;m tại Th&agrave;nh phố H&agrave; Giang</strong></p>\n', 'Mã Pí Lèng - Cột cờ Lũng Cú - Tp Hà Giang'),
('1f48bfc9-17cf-49be-916d-9b5de6c782ac', 3, '<p>Qu&yacute; kh&aacute;ch thức dậy sớm ăn s&aacute;ng v&agrave; l&agrave;m thủ tục trả ph&ograve;ng. Xe khởi h&agrave;nh đưa Qu&yacute; kh&aacute;ch tham quan:</p>\n\n<ul>\n	<li><strong>Cột mốc số 0:</strong>&nbsp;đ&aacute;nh dấu điểm khởi c&ocirc;ng của con đường Hạnh Ph&uacute;c nối&nbsp;<strong>H&agrave; Giang</strong>&nbsp;v&agrave; 4 huyện&nbsp;<strong>v&ugrave;ng cao nguy&ecirc;n đ&aacute;.</strong></li>\n	<li><strong>Th&ocirc;n Hạ Th&agrave;nh</strong>&nbsp;c&aacute;ch trung t&acirc;m th&agrave;nh phố khoảng 6km. Đ&acirc;y l&agrave; th&ocirc;n của người d&acirc;n tộc T&agrave;y, Qu&yacute; kh&aacute;ch chi&ecirc;m ngưỡng vẻ đẹp kiến tr&uacute;c của những ng&ocirc;i nh&agrave; S&agrave;n rất độc đ&aacute;o t&igrave;m hiểu đời sống văn h&oacute;a của bản T&agrave;y. Sau đ&oacute; đo&agrave;n l&ecirc;n xe di chuyển về&nbsp;<strong>th&agrave;nh phố Tuy&ecirc;n Quang</strong>&nbsp;d&ugrave;ng bữa trưa tại nh&agrave; h&agrave;ng.</li>\n	<li>Qu&yacute; kh&aacute;ch dừng nghỉ ch&acirc;n mua sắm đặc sản miền n&uacute;i&nbsp;<strong>Tr&agrave; Shan Tuyết, mật ong Bạc H&agrave;, tr&acirc;u kh&ocirc; g&aacute;c bếp, nấm Hương, măng kh&ocirc;.</strong></li>\n</ul>\n\n<p>Xe v&agrave; Hướng dẫn vi&ecirc;n đưa Qu&yacute; kh&aacute;ch trở về H&agrave; Nội.</p>\n\n<p>Đo&agrave;n về tới điểm đ&oacute;n ban đầu, chia tay Qu&yacute; kh&aacute;ch, kết th&uacute;c chương tr&igrave;nh.</p>\n\n<p>&nbsp;</p>\n\n<p><strong><em>(*) Thứ tự c&aacute;c điểm thăm quan c&oacute; thể thay đổi theo t&igrave;nh h&igrave;nh thực tế tại thời điểm xuất ph&aacute;t nhưng vẫn đảm bảo đầy đủ c&aacute;c quyền lợi v&agrave; c&aacute;c điểm thăm quan theo chương tr&igrave;nh.</em></strong></p>\n', 'Hà Giang - Hà Nội');

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
