--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES 
(7,'22','22',NULL,0,'2025-06-08 02:28:05'),(8,'','22',NULL,0,'2025-06-08 00:11:50'),(9,'33','33',NULL,0,'2025-06-08 00:22:59'),(10,'55','55',NULL,0,'2025-06-08 00:24:19'),(11,'44','44',NULL,0,'2025-06-08 00:29:46'),(12,'66','66',NULL,0,'2025-06-08 00:39:07'),(13,'77','77',NULL,0,'2025-06-08 00:52:24'),(14,'99','99',NULL,0,'2025-06-08 00:53:52'),(15,'222','222',NULL,0,'2025-06-08 00:54:40'),(16,'333','333',NULL,0,'2025-06-08 03:06:22'),(17,'444','444',NULL,0,'2025-06-08 00:58:47'),(18,'3333','3333',NULL,0,'2025-06-08 01:05:13'),(19,'777','777',NULL,0,'2025-06-08 01:05:17'),(20,'888','888',NULL,0,'2025-06-08 01:06:02'),(21,'12','12',NULL,0,'2025-06-08 01:06:58'),(22,'123','123',NULL,0,'2025-06-08 01:08:53'),(23,'1234','1234',NULL,0,'2025-06-08 01:08:59'),(24,'234','234',NULL,0,'2025-06-08 01:09:46'),(25,'4444','4444',NULL,0,'2025-06-08 02:35:15'),(26,'44444','44444',NULL,0,'2025-06-08 01:24:28'),(27,'555555','555555',NULL,1,'2025-06-08 03:27:30'),(28,'444433','444433',NULL,0,'2025-06-08 02:24:58'),(29,'44443','4444',NULL,0,'2025-06-08 02:38:27'),(30,'3334','333',NULL,0,'2025-06-08 02:43:46'),(31,'3334445','333',NULL,0,'2025-06-08 03:27:20'),(32,'123456','123456',NULL,0,'2025-06-08 03:25:24'),(33,'666','666',NULL,0,'2025-06-08 03:08:00'),(34,'poster','poster',NULL,0,'2025-06-09 20:00:54'),(35,'applicant','applicant',NULL,1,'2025-06-09 20:01:03'),(36,'ccc','ccc',NULL,0,'2025-06-09 19:56:54'),(37,'wxy','wxy',NULL,1,'2025-06-09 00:50:39'),(38,'owner','72122ce96bfec66e2396d2e25225d70a',NULL,0,'2025-06-11 16:26:35'),(39,'22222','3d2172418ce305c7d16d4b05597c6a59',NULL,0,'2025-06-10 00:21:38'),(40,'wxynim','wxynim',NULL,1,'2025-06-10 00:22:42'),(41,'wxywxy','c59d5ee0ff7fdf99dba8cd6ed0a1200e',NULL,0,'2025-06-11 14:23:14'),(42,'4444444','dcb64c94e1b81cd1cd3eb4a73ad27d99',NULL,1,'2025-06-11 11:57:39'),(43,'applicants','aaea8e805a118b90e86f7044ecfb6d59',NULL,1,'2025-06-11 16:26:42');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES 
(3,1,'旺财','金毛',1,NULL,NULL),
(4,2,'咪咪','美短',0,NULL,NULL),
(6,24,NULL,NULL,NULL,'sh',NULL),
(7,24,NULL,NULL,NULL,'上海',NULL),
(8,24,NULL,NULL,NULL,'上海',NULL),
(9,26,NULL,NULL,NULL,'北京',NULL),
(10,26,'lucky','dog',1,'上海',NULL),
(11,27,'Mary','dog',1,'上海',NULL),
(12,27,'Sindy','cat',0,'上海',NULL),
(13,27,'Mily','cat',1,'上海',NULL),
(14,27,'ketty','cat',1,'上海',NULL),
(15,27,'33','cat',1,'3333',NULL),
(16,34,'Jerry','dog',0,'杭州',NULL),
(17,34,'Apple','cat',1,'南通',NULL),
(30,34,'Miya','cat',1,'上海',NULL),
(31,38,'Baby','dog',0,'南通',NULL),
(32,38,'cute','cat',1,'长沙',NULL),
(33,38,'Gary','cat',1,'芜湖',NULL),
(34,42,'111111','cat',1,'11',NULL),
(36,41,'111111','1111',2,'111',NULL),
(37,41,'Ball','英短',2,'上海',NULL),
(38,41,'Cherry','狸花猫',2,'上海',NULL),
(39,41,'111111111','11111111',1,'111',NULL),
(40,41,'','',1,'',NULL),
(41,41,'33','33',1,'333',NULL),
(42,41,'33','33',1,'333',NULL),
(43,41,'','',1,'',NULL),
(44,41,'','',1,'',NULL),
(45,41,'KKI','金毛',1,'北京',NULL),
(46,41,'','',1,'',NULL),
(47,41,'UU','狸花',1,'苏州',NULL),
(48,38,'Betty','狸花',2,'上海',NULL),
(49,38,'May','银渐层',1,'重庆',NULL),
(50,38,'AA','cat',1,'上海',NULL),
(51,38,'BB','cat',1,'上海',NULL),
(52,38,'CC','狸花',2,'上海',NULL),
(53,38,'DD','金毛',1,'上海',NULL),
(54,38,'AAAA','cat',0,'上海',NULL),
(55,38,'BBBB','dog',1,'上海',NULL),
(56,38,'CCCC','银渐层',1,'上海',NULL),
(57,38,'DDDD','金毛',1,'北京',NULL);
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `adoption_listing`
--

LOCK TABLES `adoption_listing` WRITE;
/*!40000 ALTER TABLE `adoption_listing` DISABLE KEYS */;
INSERT INTO `adoption_listing` VALUES (1,2,1,'寻找有爱心的主人领养小白，性格温顺，已绝育','available','2025-06-08 00:15:44',NULL,'cat1.jpg'),(2,3,1,'金毛旺财等待新家，活泼开朗，适合有院子的家庭','available','2025-06-08 00:15:44',NULL,'dog1.jpg'),(3,4,2,'美短咪咪求领养，已打疫苗，性格独立','available','2025-06-08 00:15:44',NULL,'cat2.jpg'),(13,1,5,'111','available','2025-05-13 08:38:12',NULL,NULL),(14,1,5,'123123','available','2025-05-14 08:38:12',NULL,NULL),(15,8,24,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 01:12:47',NULL,'/default-pet.jpg'),(16,9,26,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 01:25:33',NULL,'/default-pet.jpg'),(17,10,26,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 01:31:32',NULL,'/default-pet.jpg'),(18,11,27,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 01:32:20',NULL,'/default-pet.jpg'),(20,13,27,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 01:55:46',NULL,'http://localhost:8080/pet/97509b88-ea35-4e94-b6a5-d44799c1437b.jpg'),(21,14,27,'可爱的宝宝我亲你你你你你你你','available','2025-06-08 03:19:09',NULL,'http://localhost:8080/pet/6a4c3cde-6af3-41e2-a4c9-84794a65a0cf.jpg'),(23,16,34,'那一天的忧郁忧郁起来。','adopted','2025-06-08 22:08:53','2025-06-08 22:23:40','http://localhost:8080/pet/7302fdf9-ee68-4ab6-9afd-3b77e7d83bb1.jpg'),(24,17,34,'那一天的忧郁忧郁起来。','available','2025-06-08 22:09:24',NULL,'http://localhost:8080/pet/8b34832a-49b6-408d-b9ad-1310f3279b15.jpg'),(25,30,34,'可爱可爱可爱可爱可爱可爱','adopted','2025-06-09 19:59:09','2025-06-09 20:00:47','http://localhost:8080/pet/1775ed9d-a184-4562-8de3-39efd378877f.jpg'),(27,32,38,'喜欢你喜欢你喜欢你喜欢你喜欢你','available','2025-06-09 23:46:46',NULL,'http://localhost:8080/pet/7ebd759d-827d-4d29-b8ca-e98c4b522446.jpg'),(29,36,41,'1111111111111','available','2025-06-11 13:28:17',NULL,NULL),(30,37,41,'1111111111111111','available','2025-06-11 13:29:16',NULL,NULL),(31,38,41,'宝宝宝宝宝宝宝宝宝宝宝宝','available','2025-06-11 13:35:02',NULL,NULL),(32,39,41,'11111111111','available','2025-06-11 13:48:06',NULL,NULL),(33,40,41,'','available','2025-06-11 13:49:38',NULL,NULL),(34,41,41,'33333333333333333','available','2025-06-11 13:49:59',NULL,NULL),(35,42,41,'33333333333333333','available','2025-06-11 13:50:00',NULL,NULL),(36,43,41,'','available','2025-06-11 13:53:10',NULL,NULL),(37,44,41,'','available','2025-06-11 13:55:32',NULL,'http://localhost:8080/pet/e7e5239e-a703-48e4-b061-0dcbb1fafc1d.jpg'),(38,45,41,'好呀好呀好呀好呀好呀好呀','available','2025-06-11 13:57:48',NULL,'http://localhost:8080/pet/45f3572d-1c6e-44b3-b185-7e054d286691.jpg'),(39,46,41,'','available','2025-06-11 14:02:42',NULL,'http://localhost:8080/pet/f6e2dc20-058f-46d2-afad-0743986990d2.jpg'),(40,47,41,'宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝宝','available','2025-06-11 14:04:46',NULL,'http://localhost:8080/pet/88ac33f0-8720-4b58-90a6-5453d008dbd1.jpg'),(42,49,38,'这是一只可爱的小猫咪的朋友','adopted','2025-06-11 14:54:44','2025-06-11 15:16:13','http://localhost:8080/pet/77b73019-9c62-4612-95a8-790c93862caf.jpg'),(43,50,38,'AAAAAAAAAAAAAAAAAAAAC','adopted','2025-06-11 15:27:31','2025-06-11 15:29:23','http://localhost:8080/pet/027a38b5-f464-4f58-9364-c982de0fb87b.jpg'),(45,52,38,'CCCCCCCCCCCC','available','2025-06-11 15:30:55',NULL,'http://localhost:8080/pet/0b03d8e0-9328-474e-9461-fbc0fced079c.jpg'),(47,54,38,'aaaaaaaaaaaaaa','adopted','2025-06-11 16:21:03','2025-06-11 16:22:44','http://localhost:8080/pet/db8b3e08-de64-4560-b7d0-1a4a0c5761b1.jpg'),(48,55,38,'BBBBBBBBBBBBBddddkkk','available','2025-06-11 16:21:25',NULL,'http://localhost:8080/pet/5afc6ddb-df9f-4a5a-983d-b7d3bb602a33.jpg'),(49,56,38,'CCCCCCCCCCCCC','available','2025-06-11 16:24:47',NULL,'http://localhost:8080/pet/ff794dae-9611-40d6-a9db-65d04244578d.jpg'),(50,57,38,'DDDDDDDDDDDDD','adopted','2025-06-11 16:25:12','2025-06-11 16:26:23','http://localhost:8080/pet/0d893744-323f-46f8-aac1-45c4cf14e6e0.jpg');
/*!40000 ALTER TABLE `adoption_listing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `adoption_application`
--

LOCK TABLES `adoption_application` WRITE;
/*!40000 ALTER TABLE `adoption_application` DISABLE KEYS */;
INSERT INTO `adoption_application` VALUES (1,1,3,'我很喜欢猫咪，家里有养猫经验，有稳定的工作和收入','pending','2025-06-08 00:15:55',NULL,NULL),(2,2,4,'家里有大院子，适合养狗，之前养过金毛，有经验','pending','2025-06-08 00:15:55',NULL,NULL),(5,13,5,'123123123','pending','2025-05-14 16:39:25',NULL,NULL),(6,13,5,'123123','pending','2025-05-14 16:42:23',NULL,NULL),(7,13,5,'123123','pending','2025-05-16 16:42:28',NULL,NULL),(8,14,5,'123123','pending','2025-05-23 16:43:40',NULL,NULL),(9,20,16,'无','pending','2025-06-08 03:01:44',NULL,NULL),(10,18,27,'无','rejected','2025-06-08 03:01:48',NULL,27),(11,17,16,'无','pending','2025-06-08 03:02:00',NULL,NULL),(12,17,32,'可爱的宝宝我亲你你你你你你你','pending','2025-06-08 03:06:44',NULL,NULL),(13,18,27,'可爱的宝宝我亲你你你你你你你','approved','2025-06-08 03:06:58',NULL,27),(14,20,32,'可爱的宝宝我亲你你你你你你你','pending','2025-06-08 03:07:01',NULL,NULL),(15,1,27,'可爱的宝宝我亲你你你你你你你','pending','2025-06-08 03:11:13',NULL,NULL),(17,1,34,'那一天的忧郁忧郁起来。','pending','2025-06-08 22:10:58',NULL,NULL),(18,23,35,'那一天的寂寞寂寞起来。','approved','2025-06-08 22:23:07','2025-06-08 22:23:40',34),(19,24,35,'那一天的寂寞寂寞起来。','rejected','2025-06-08 22:23:11',NULL,34),(20,2,34,'可爱可爱可爱可爱可爱可爱可爱','pending','2025-06-09 19:59:29',NULL,NULL),(21,25,35,'可爱可爱可爱可爱可爱可爱','approved','2025-06-09 20:00:03','2025-06-09 20:00:47',34),(25,23,38,'无','pending','2025-06-11 14:42:46',NULL,NULL),(29,42,43,'好喜欢你啵啵啵啵啵啵宝宝','approved','2025-06-11 15:03:24','2025-06-11 15:16:13',38),(31,43,43,'AAAAAAAAAAA','approved','2025-06-11 15:28:43','2025-06-11 15:29:23',38),(34,45,43,'CCCCCCCCCC','rejected','2025-06-11 15:32:10',NULL,38),(37,47,43,'AAAAAAAAAA','approved','2025-06-11 16:22:13','2025-06-11 16:22:44',38),(38,48,43,'BBBBBBBBBBB','rejected','2025-06-11 16:22:19',NULL,38),(39,49,43,'CCCCCCCCCCCC','rejected','2025-06-11 16:25:47',NULL,38),(40,50,43,'DDDDDDDDDDDD','approved','2025-06-11 16:25:53','2025-06-11 16:26:23',38);
/*!40000 ALTER TABLE `adoption_application` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;