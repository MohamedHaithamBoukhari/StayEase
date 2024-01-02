-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:4000
-- Generation Time: Jan 02, 2024 at 08:56 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `fullName` varchar(40) DEFAULT NULL,
  `cin` varchar(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `account_status` varchar(30) DEFAULT NULL COMMENT 'Active,Blocked'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `fullName`, `cin`, `phone`, `email`, `password`, `address`, `account_status`) VALUES
(1, 'first customer', 'LL45782', '0645789888', 'customer1@gmail.com', '11111111', 'Bassatine,Meknes', 'Active'),
(2, 'second customer', 'HJ45123', '0758963214', 'customer2@gmail.com', '11111111', 'HAY Salam, Meknes', 'Active'),
(3, 'third customer', 'GT14523', '0615243678', 'customer3@gmail.com', '11111111', 'Hamriya, Meknes', 'Blocked');

-- --------------------------------------------------------

--
-- Table structure for table `declaration`
--

CREATE TABLE `declaration` (
  `declarationId` int(11) NOT NULL,
  `declarantId` int(11) DEFAULT NULL,
  `declarantStatus` varchar(30) DEFAULT '' COMMENT '''Cleaner'', ''Maintenance Staff'',''Customer''',
  `declarationObject` varchar(50) DEFAULT NULL,
  `declaration` text DEFAULT NULL,
  `declarationDate` varchar(30) DEFAULT NULL,
  `response` text DEFAULT NULL,
  `responseDate` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `declaration`
--

INSERT INTO `declaration` (`declarationId`, `declarantId`, `declarantStatus`, `declarationObject`, `declaration`, `declarationDate`, `response`, `responseDate`) VALUES
(1, 10, 'Maintenance Staff', 'Device malfunction', 'the tv of room number 5 is brokken', '2023-12-30', NULL, NULL),
(3, 11, 'Cleaner', 'The need for cleaning supplies', 'we need some cleanning supplies for mirrors', '2023-12-30', 'Ok, tommorow the cleaning supplies come', '2023-12-30'),
(5, 1, 'Customer', 'Wi-Fi Connectivity', 'The Wi-Fi signal in my room was weak and intermittent\n', '2023-12-30', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeId` int(11) NOT NULL,
  `fullName` varchar(40) DEFAULT NULL,
  `cin` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `position` varchar(30) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `workingHours` varchar(40) DEFAULT NULL,
  `workingDays` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeId`, `fullName`, `cin`, `phone`, `email`, `password`, `position`, `salary`, `workingHours`, `workingDays`) VALUES
(8, 'Mohamed Haitham Boukhari', 'Di3192', '0616184687', 'admin@gmail.com', '11111111', 'Admin', 25000, NULL, NULL),
(9, 'managerName', 'SD12453', '0645785214', 'manager@gmail.com', '11111111', 'Manager', 15000, '08:00 -> 16:00', 'Monday, Wednesday, Friday'),
(10, 'maintenanceStaff name', 'DZ1452', '0785451263', 'maintenance@gmail.com', '11111111', 'Maintenance Staff', 10000, '00:00 -> 08:00', 'Monday, Tuesday, Thursday'),
(11, 'cleaner name', 'GH457', '0645789652', 'cleaner@gmail.com', '11111111', 'Cleaner', 8000, '16:00 -> 00:00', 'Wednesday, Thursday, Saturday, Sunday'),
(13, 'security name', 'OS4517', '0625417896', 'securityPersonnel@gmail.com', '11111111', 'Security Personnel', 6000, '00:00 -> 08:00', 'Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackId` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  `visibility` varchar(30) DEFAULT 'Invisible',
  `priority` int(11) DEFAULT NULL,
  `customerService_rate` int(11) DEFAULT NULL,
  `cleanliness_rate` int(11) DEFAULT NULL,
  `roomComfort_rate` int(11) DEFAULT NULL,
  `location_rate` int(11) DEFAULT NULL,
  `safety_rate` int(11) DEFAULT NULL,
  `environnement_rate` int(11) DEFAULT NULL,
  `view_rate` int(11) DEFAULT NULL,
  `serviceVSprice_rate` int(11) DEFAULT NULL,
  `totalRate` int(11) DEFAULT NULL,
  `review_rate` text DEFAULT NULL,
  `feedback_date` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedbackId`, `customerId`, `visibility`, `priority`, `customerService_rate`, `cleanliness_rate`, `roomComfort_rate`, `location_rate`, `safety_rate`, `environnement_rate`, `view_rate`, `serviceVSprice_rate`, `totalRate`, `review_rate`, `feedback_date`) VALUES
(1, 1, 'Invisible', 100, 8, 9, 9, 10, 10, 7, 9, 6, 8, 'only the price is so  expensive', '2023-12-30'),
(3, 2, 'Visible', 100, 8, 10, 9, 7, 10, 10, 10, 8, 9, 'the location of this room is not good', '2023-12-30');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoiceId` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  `reservationId` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `invoiceDate` varchar(30) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceId`, `customerId`, `reservationId`, `status`, `invoiceDate`, `amount`) VALUES
(1, 1, 13, 'Unpaid', '2023-12-30', 840),
(2, 2, 14, 'Paid', '2023-12-30', 145),
(3, 2, 15, 'Cancelled', '2023-12-30', 645);

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE `position` (
  `positionId` int(11) NOT NULL,
  `empPosition` varchar(30) DEFAULT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`positionId`, `empPosition`, `description`) VALUES
(1, 'Manager', 'manager position allow to this employee manage reservations, rooms, affecte rooms to cleaner and maintenance staff'),
(2, 'Maintenance Staff', 'Maintenance Staff is the employee who repairs and take care of all room\'s equipements'),
(3, 'Cleaner', 'Cleaner is the employee who clean rooms'),
(6, 'Security Personnel', 'ensure the safety and security of guests and hotel property.');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservationId` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `reservationDate` varchar(30) DEFAULT NULL,
  `check_inDate` varchar(30) DEFAULT NULL,
  `check_outDate` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL COMMENT '(''Upcoming'',''In Progress'',''Completed Stay'',''Cancelled'')'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservationId`, `customerId`, `roomId`, `reservationDate`, `check_inDate`, `check_outDate`, `status`) VALUES
(13, 1, 15, '2023-12-30', '2023-12-29', '2024-01-06', 'In Progresss'),
(14, 2, 17, '2023-12-30', '2023-12-29', '2023-12-30', 'Completed Stay'),
(15, 2, 14, '2023-12-30', '2024-01-02', '2024-01-05', 'Cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomId` int(11) NOT NULL,
  `numRoom` int(11) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT 'enum(''Available'', ''Occupied'', ''Under Cleaning'',''Needs Cleaning'',''Under Maintenance'', ''Needs Maintenance'', ''Out of Service'', ''Checked Out'');	'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomId`, `numRoom`, `type`, `capacity`, `status`) VALUES
(14, 1, 'simple', 4, 'Available'),
(15, 2, 'lux', 3, 'Occupied'),
(16, 3, 'lux', 5, 'Available'),
(17, 4, 'simple', 2, 'Needs Maintenance'),
(19, 5, 'simple', 3, 'Available'),
(20, 6, 'lux', 1, 'Needs Cleaning'),
(21, 7, 'simple', 1, 'Needs Cleaning'),
(22, 8, 'simple', 2, 'Out of Service'),
(23, 9, 'simple', 4, 'Under Maintenance');

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

CREATE TABLE `roomtype` (
  `typeId` int(11) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price_day` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`typeId`, `type`, `description`, `price_day`) VALUES
(1, 'simple', 'simple room with ......', 75),
(2, 'lux', 'lux room is better than simple room', 100);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `serviceId` int(11) NOT NULL,
  `serviceName` varchar(30) DEFAULT NULL,
  `descreption` text DEFAULT NULL,
  `correspondingTable` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL COMMENT '''available'', ''Unavailable'''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`serviceId`, `serviceName`, `descreption`, `correspondingTable`, `status`) VALUES
(1, 'Book room', 'Book room service offer to custumer reserve room without needing to go to hotel ....', 'reservations', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `taskId` int(11) NOT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL COMMENT 'Completed,In Progress,On Hold',
  `taskDate` varchar(50) DEFAULT NULL,
  `taskType` varchar(30) DEFAULT NULL COMMENT '''Maintenance'', ''Cleaning'''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`taskId`, `employeeId`, `roomId`, `status`, `taskDate`, `taskType`) VALUES
(1, 11, 19, 'Completed', '2023-12-31 11:26:27', 'Cleaning'),
(2, 10, 21, 'Completed', '2023-12-31 11:28:05', 'Maintenance'),
(3, 10, 23, 'On Hold', '2023-12-31 11:28:20', 'Maintenance');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`);

--
-- Indexes for table `declaration`
--
ALTER TABLE `declaration`
  ADD PRIMARY KEY (`declarationId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackId`),
  ADD KEY `customerId` (`customerId`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceId`),
  ADD KEY `custumer_const` (`customerId`),
  ADD KEY `res_const` (`reservationId`);

--
-- Indexes for table `position`
--
ALTER TABLE `position`
  ADD PRIMARY KEY (`positionId`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservationId`),
  ADD KEY `customer_const` (`customerId`),
  ADD KEY `room_const` (`roomId`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`);

--
-- Indexes for table `roomtype`
--
ALTER TABLE `roomtype`
  ADD PRIMARY KEY (`typeId`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`serviceId`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`taskId`),
  ADD KEY `emp_const` (`employeeId`),
  ADD KEY `room__const` (`roomId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `declaration`
--
ALTER TABLE `declaration`
  MODIFY `declarationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoiceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `position`
--
ALTER TABLE `position`
  MODIFY `positionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `roomtype`
--
ALTER TABLE `roomtype`
  MODIFY `typeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `serviceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `taskId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `custumer_const` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `res_const` FOREIGN KEY (`reservationId`) REFERENCES `reservation` (`reservationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `customer_const` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `room_const` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `emp_const` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `room__const` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
