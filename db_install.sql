-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (arm64)
--
-- Host: localhost    Database: dataset_tagger
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `dataset_tagger`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `dataset_tagger` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `dataset_tagger`;

--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_set` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pub_time` datetime NOT NULL,
  `desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据集描述',
  `sample_type` varchar(16) NOT NULL,
  `tag_type` varchar(16) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `publisher_id` int NOT NULL,
  `dataset_id` varchar(64) NOT NULL COMMENT '使用UUID生成的全局唯一的id',
  `name` varchar(32) DEFAULT '默认数据集',
  `publisher_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `data_set_pk` (`dataset_id`),
  KEY `dataset_publisher_id_combined_index` (`publisher_id`,`dataset_id`,`desc`,`pub_time`,`tag_type`,`sample_type`),
  CONSTRAINT `data_set_user_id_fk` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据集';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--

LOCK TABLES `data_set` WRITE;
/*!40000 ALTER TABLE `data_set` DISABLE KEYS */;
INSERT INTO `data_set` VALUES (13,'2023-04-18 23:57:09','这是数据集2','picture','class_tag','2023-04-18 23:57:09',20,'273f97bf-d70b-4877-910a-649777f6a2d9','数据集2','user1'),(14,'2023-04-19 00:17:43','这是数据集3','text','text_tag','2023-04-19 00:17:43',20,'5a5c92cc-f241-4589-bc73-57f1ecf11ee9','数据集3','user1'),(18,'2023-04-19 10:39:56','这是数据集4','text','text_tag','2023-04-19 10:39:56',20,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','默认数据集','user1'),(19,'2023-04-19 21:43:36','更新后的内容','text','text_tag','2023-04-19 21:43:36',20,'77ef0e60-94ca-4fc5-8827-39c818042e21','数据集5','user1'),(20,'2023-04-26 12:46:45','新键数据集','picture','class_tag','2023-04-26 12:46:45',2,'bf4df5ce-1ddd-48db-aa61-9c56b9ebf353','认数据集','p1'),(25,'2023-06-02 19:22:30','新键数据集','picture','class_tag','2023-06-02 19:22:30',2,'89b0a3e3-8141-4e2a-9e1e-76e394f334f5','测试','p1'),(26,'2023-06-02 19:25:40','更改一下描述','picture','text_tag','2023-06-02 19:25:40',2,'6806b157-4291-48f1-834e-197326b45649','测试标题2','p1'),(27,'2023-06-02 22:43:06','文本文本','text','text_tag','2023-06-02 22:43:06',2,'90a0f202-c321-4f24-8077-456f5c351608','文本','p1'),(28,'2023-06-02 22:43:17','音频音频','audio','class_tag','2023-06-02 22:43:17',2,'9921159f-9ad9-4056-a528-4e039bfcb502','音频','p1');
/*!40000 ALTER TABLE `data_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dataset_auth`
--

DROP TABLE IF EXISTS `dataset_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dataset_auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int DEFAULT NULL,
  `dataset_id` varchar(64) DEFAULT NULL,
  `authority_type` varchar(16) NOT NULL COMMENT '权限类型，目前支持数据集owner和tagger两种类型',
  `username` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dataset_auth_data_set_dataset_id_fk` (`dataset_id`),
  KEY `dataset_auth_user_id_fk` (`uid`),
  CONSTRAINT `dataset_auth_data_set_dataset_id_fk` FOREIGN KEY (`dataset_id`) REFERENCES `data_set` (`dataset_id`),
  CONSTRAINT `dataset_auth_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  CONSTRAINT `check_authority_type` CHECK ((`authority_type` in (_utf8mb4'owner',_utf8mb4'tagger')))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dataset_auth`
--

LOCK TABLES `dataset_auth` WRITE;
/*!40000 ALTER TABLE `dataset_auth` DISABLE KEYS */;
INSERT INTO `dataset_auth` VALUES (1,20,'273f97bf-d70b-4877-910a-649777f6a2d9','owner','user1'),(2,20,'5a5c92cc-f241-4589-bc73-57f1ecf11ee9','owner','user1'),(3,20,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','owner','user1'),(4,20,'77ef0e60-94ca-4fc5-8827-39c818042e21','owner','user1'),(5,2,'bf4df5ce-1ddd-48db-aa61-9c56b9ebf353','owner','p1'),(6,2,'89b0a3e3-8141-4e2a-9e1e-76e394f334f5','owner','p1'),(7,2,'6806b157-4291-48f1-834e-197326b45649','owner','p1'),(8,2,'90a0f202-c321-4f24-8077-456f5c351608','owner','p1'),(9,2,'9921159f-9ad9-4056-a528-4e039bfcb502','owner','p1'),(10,20,'bf4df5ce-1ddd-48db-aa61-9c56b9ebf353','tagger','user1');
/*!40000 ALTER TABLE `dataset_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL COMMENT '权限名',
  `name_zh` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文权限名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_TAGGER','标记人员'),(2,'ROLE_PUBLISHER','发布人员');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sample` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dataset_id` varchar(64) NOT NULL,
  `content` text,
  `sample_type` varchar(16) DEFAULT NULL,
  `tag_type` varchar(16) DEFAULT NULL,
  `sample_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sample_uk_sample_id` (`sample_id`),
  KEY `sample_data_set_dataset_id_fk` (`dataset_id`),
  CONSTRAINT `sample_data_set_dataset_id_fk` FOREIGN KEY (`dataset_id`) REFERENCES `data_set` (`dataset_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='样本';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES (2,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','这是一段样本文本','text','text_tag','c0a6dd19-fad3-4fc5-b3f1-d67f4432b034'),(5,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','通过添加多个雪花元素，可以创建更多的雪花效果。你还可以通过调整 CSS 属性来自定义雪花的大小、颜色、速度等。\n\n请注意，这只是一个简单的示例，实际效果可能需要更复杂的 CSS 和动画来实现更逼真的雪花效果。','text','text_tag','5687806e-3b6a-430d-bf56-e4809526931d'),(6,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','在 React 中，`<Component editable={{onChange: function()}}>` 中的两个大括号表示 JavaScript 对象的字面量语法和 JSX 表达式的花括号。\n\n1. 大括号 `{}` 表示 JSX 表达式，用于在 JSX 中嵌入 JavaScript 表达式或变量。在这个例子中，`{}` 用于将对象作为属性值传递给组件 `<Component>`，它将解析为一个 JavaScript 表达式。\n\n2. 第二对大括号 `{{}}` 是 JavaScript 对象的字面量语法。它用于创建一个 JavaScript 对象，并将其作为 `editable` 属性的值传递给组件 `<Component>`。其中，内部的大括号 `{}` 表示 JavaScript 对象的字面量。\n\n在这个例子中，`editable` 是一个属性名，它的值是一个对象字面量，具有一个属性 `onChange`，它的值是一个函数。\n\n总结起来，`<Component editable={{onChange: function()}}>` 中的两对大括号的作用如下：\n\n- 外部的大括号 `{}` 表示 JSX 表达式，用于在 JSX 中嵌入 JavaScript 表达式或变量。\n- 内部的大括号 `{}` 是 JavaScript 对象的字面量语法，用于创建一个 JavaScript 对象。','text','text_tag','e340f84b-4f0e-4d4d-892f-3e4521fadd1f'),(7,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','上述代码中，我们通过状态 list 来存储列表的数据。当点击删除按钮时，我们调用 handleDelete 函数来处理删除逻辑。该函数首先向后端发送请求删除相应项，然后更新状态中的列表数据。最后，在组件的渲染方法中，我们使用 list.map 方法来遍历列表，并为每一项创建一个带有删除按钮的 <li> 元素。\n\n这样，当你点击按钮删除列表中的一项时，后端会执行相应的删除操作，同时状态中的列表数据也会更新，触发组件的重新渲染，从而更新页面上的列表内容。','text','text_tag','1134e259-19d3-4e43-bdcc-94d294c80ee8'),(8,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','《肿瘤的基因治疗》期刊的撤稿说明称，主编撤回了前述论文。\n涉事论文发表后被发现文中图6的尺子图像，似乎与其他不相关作者的其他论文，比如论文“1”“2”“3”里相关图片中的尺子图像相同。因此，人们对涉事研究数据的完整性产生了担忧。涉事论文的作者们没有应期刊要求进行回应以便澄清。\n他们也没有提供相关基础研究数据或伦理批准的证据。因此，期刊主编对该论文中的研究发现的完整性不再抱有信心。涉事论文的作者们没有回复编辑关于这次撤稿的任何信件。','text','text_tag','4ac71ab4-25a7-4322-b142-590d1bcd46d8'),(9,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','钢尺作为一种常用的测量工具，本身并未引起人们的疑虑。但事实上，一把钢尺却成为了破解多篇论文是否涉嫌抄袭的关键。\n\n事实上，文献抄袭并非学术界的“罕见病”。在此次事件背后，也提醒我们认真对待学术诚信，以及如何在研究过程中保障科研诚信。但不可否认的是，钢尺成为了论文抄袭的“神器”，这也让我们想到，如何保护研究数据，甚至技术设备，使其不被不法分子利用，以维护学术界的诚信。\n\n总之，这一事件提醒了我们在研究过程中严格遵守道德和诚信原则，保护科研成果的真实性和准确性，并加强诚信教育，共同维护良好的学术环境。','text','text_tag','4e4a6f86-3dab-4bec-bcf1-f2bafa137c3c'),(10,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','当目标元素有进一步的描述和相关操作时，可以收纳到卡片中，根据用户的操作行为进行展现。\n\n和 Tooltip 的区别是，用户可以对浮层上的元素进行操作，因此它可以承载更复杂的内容，比如链接或按钮等。','text','text_tag','5bcebf61-af83-487c-81db-f983aea68b3d'),(11,'77ef0e60-94ca-4fc5-8827-39c818042e21','测试一下试试','text','text_tag','f27a9adf-17f9-4156-8cec-731aa9168aeb'),(12,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','在这个函数中，我们使用 for 循环遍历 labels 数组的每一项，将每一项的起始位置和结束位置分别赋值给 labelStart 和 labelEnd。然后，我们通过比较 startIndex 和 endIndex 与当前 labels 数组项的位置关系来判断是否存在重叠范围。如果 startIndex 小于等于当前标签的结束位置且 endIndex 大于等于当前标签的起始位置，则说明存在重叠范围，此时函数返回 true。如果遍历完所有的 labels 项都没有找到重叠范围，则返回 false。','text','text_tag','9de02d89-ebb9-4f89-a7f6-0bf651d76990'),(13,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','你可以将需要检查的 startIndex 和 endIndex 以及 labels 数组作为参数传递给 checkOverlap 函数，并根据返回的结果进行后续操作。\n\n希望这个解决方案对你有帮助。如果还有其他问题，请随时提问。','text','text_tag','e275b2aa-8e7b-4f16-8081-cd640c1ed420'),(14,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','9村庄修路挖出大石龟？官方通报\n10莫迪视察事故现场 这两幕激怒印度人\n11跑男打错范丞丞名字\n12女子考科三系错安全带考官面如死灰\n13男子路遇纸片鸟一查竟是国保动物\n14印度布地毯上浇沥青当新路\n15孩子随母姓被拒绝分配村集体收入','text','text_tag','3d7be3f4-d4ed-4f84-90bf-e9d187caf15d'),(15,'60e6ac4f-f859-4bf7-8bca-78eb670d6c9e','js string转int的方法 JavaScript 中能够将字符串转换为数值类型,这种操作被称为 \"JS string to int\",也就是将字符串转换为整数。 JavaScript 提供了几种不同的方法来实现字...','text','text_tag','12253ac1-7f6f-4186-8440-1563a85af6b9');
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tag_id` varchar(64) NOT NULL,
  `sample_id` varchar(64) DEFAULT NULL,
  `tagger_id` varchar(64) DEFAULT NULL,
  `tag_time` datetime DEFAULT NULL,
  `begin_pos` varchar(128) NOT NULL,
  `end_pos` varchar(128) DEFAULT NULL,
  `tag` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tag_sample_sample_id_fk` (`sample_id`),
  CONSTRAINT `tag_sample_sample_id_fk` FOREIGN KEY (`sample_id`) REFERENCES `sample` (`sample_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='样本的标记';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (7,'85bd70fa-2524-4847-8897-34378ac36308','c0a6dd19-fad3-4fc5-b3f1-d67f4432b034','20','2023-06-04 16:00:49','bchar : 4','bchar : 6','category : 名词'),(8,'9c804f05-fe4d-44aa-82c5-9ac79880fdd7','5687806e-3b6a-430d-bf56-e4809526931d','20','2023-06-04 16:01:07','bchar : 6','bchar : 8','category : 物品'),(9,'21f8ba47-59cc-4c4f-9280-70a5b22f9737','5687806e-3b6a-430d-bf56-e4809526931d','20','2023-06-04 16:01:18','bchar : 93','bchar : 95','category : 动词'),(10,'47e6bf4e-f6f4-4131-9edc-847e7ccb77cc','4e4a6f86-3dab-4bec-bcf1-f2bafa137c3c','20','2023-06-04 17:28:19','bchar : 57','bchar : 60','category : 状词'),(12,'40b2c626-9df1-4f51-b612-519b9e177ce3','f27a9adf-17f9-4156-8cec-731aa9168aeb','20','2023-06-04 17:41:09','bchar : 0','bchar : 2','category : 111'),(13,'77feed4f-da72-443a-9d95-a71de48e7ab6','f27a9adf-17f9-4156-8cec-731aa9168aeb','20','2023-06-04 17:41:26','bchar : 4','bchar : 6','category : 22'),(15,'2714cbd3-6336-4982-9717-e0a336bafc23','12253ac1-7f6f-4186-8440-1563a85af6b9','20','2023-06-04 21:51:09','bchar : 74','bchar : 82','category : 测试翻页'),(17,'ce592fbc-f3b0-46ae-9da1-92660017a2aa','1134e259-19d3-4e43-bdcc-94d294c80ee8','20','2023-06-04 22:32:54','bchar : 2','bchar : 4','category : 尝试动态');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `enabled` tinyint(1) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_uk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户登录信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'t1','{noop}123',1,NULL,NULL,NULL,'2023-03-24 19:43:33'),(2,'p1','{noop}123',1,NULL,'133',NULL,'2023-03-24 19:43:35'),(4,'p2','{noop}123',1,NULL,NULL,NULL,'2023-03-24 19:43:37'),(13,'t2','{noop}123',1,NULL,'',NULL,'2023-03-24 19:43:38'),(20,'user1','{noop}123',1,NULL,NULL,NULL,'2023-04-10 16:45:08'),(24,'p3','{noop}123',1,NULL,NULL,NULL,'2023-04-18 13:36:01');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `rid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_role_id_fk` (`rid`),
  KEY `user_role_user_id_fk` (`uid`),
  CONSTRAINT `user_role_role_id_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,2),(4,4,2),(5,4,2),(6,4,2),(7,13,1),(11,20,1),(12,24,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-09 11:47:25
