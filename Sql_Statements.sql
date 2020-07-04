create database cus;

use cus;


CREATE TABLE `vendor` (
       `name` varchar(30) DEFAULT NULL,
       `category` varchar(30) DEFAULT NULL,
       `address` varchar(50) DEFAULT NULL,
       `contact` varchar(10) DEFAULT NULL,
       `id` int DEFAULT NULL);

CREATE TABLE `stock` (
       `type` varchar(30) DEFAULT NULL,
       `item` varchar(30) DEFAULT NULL,
       `price` bigint DEFAULT NULL,
       `stock` bigint DEFAULT NULL,
       `category` varchar(50) DEFAULT NULL,
       `vendor` varchar(10) DEFAULT NULL
     );

CREATE TABLE `slot` (
       `slot_time_period` bigint DEFAULT NULL,
       `workingdays` varchar(50) DEFAULT NULL,
       `opening_time` varchar(50) DEFAULT NULL,
       `closing_time` varchar(50) DEFAULT NULL,
       `exclusion` varchar(50) DEFAULT NULL,
       `vendor` varchar(20) DEFAULT NULL
     );


CREATE TABLE `orders` (
       `customer_name` varchar(30) DEFAULT NULL,
       `id` varchar(20) DEFAULT NULL,
       `oId` varchar(20) DEFAULT NULL,
       `item` varchar(50) DEFAULT NULL,
       `quantity` bigint DEFAULT NULL,
       `price` bigint DEFAULT NULL,
       `date` date DEFAULT NULL,
       `slot` varchar(30) DEFAULT NULL,
       `item_price` bigint DEFAULT NULL,
       `total_price` bigint DEFAULT NULL,
       `order_status` varchar(30) DEFAULT NULL
     );

