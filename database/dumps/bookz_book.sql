-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bookz
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `isbn` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `rating` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `supplier` int(11) NOT NULL,
  `threshold` int(11) NOT NULL,
  `edition` int(11) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `publicationyear` int(11) DEFAULT NULL,
  `buyingprice` double DEFAULT NULL,
  `sellingprice` double DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  UNIQUE KEY `isbn_UNIQUE` (`isbn`),
  KEY `supplierid_idx` (`supplier`),
  CONSTRAINT `supplierid` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`supplierid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1234,'NotNull','A GUY',123,'null','aurl.com/image.png','fantasy',0,123,0,0,NULL,NULL,NULL,NULL,NULL),(10637,'Red Queen','Victoria Aveyard',24.15,'This is a world divided by blood – red or silver.\n\nThe Reds are commoners, ruled by a Silver elite in possession of god-like superpowers. And to Mare Barrow, a seventeen-year-old Red girl from the poverty-stricken Stilts, it seems like nothing will ever change.\n\nThat is, until she finds herself working in the Silver Palace. Here, surrounded by the people she hates the most, Mare discovers that, despite her red blood, she possesses a deadly power of her own. One that threatens to destroy the balance of power.\n\nFearful of Mare’s potential, the Silvers hide her in plain view, declaring her a long-lost Silver princess, now engaged to a Silver prince. Despite knowing that one misstep would mean her death, Mare works silently to help the Red Guard, a militant resistance group, and bring down the Silver regime.\n\nBut this is a world of betrayal and lies, and Mare has entered a dangerous dance – Reds against Silvers, prince against prince, and Mare against her own heart.','https://images.gr-assets.com/books/1449778912l/22328546.jpg','fantasy',4,32,3,20,1,'HarperTeen',2015,12.4,24.15),(12313,'Judy Moody (Judy Moody #1)\n','Megan McDonald',16.6,'The first day of third grade puts Judy Moody in a mad-face mood. She just knows everyone will come back from summer vacation with word T-shirts, like \"Disney World\" or \"Jamestown: Home of Pocahontas.\" All Judy has is a plain old no-words T-shirt. She\'ll have to go to a new classroom, with a new desk, and she won\'t have an armadillo sticker with her name on it like she did last year. And knowing her luck, she\'ll end up sitting next to Frank, the boy who eats paste. For breakfast her dad makes eggs with the yellow middle broken, and her younger \"bother,\" Stink, thinks he knows everything now that he\'s starting second grade. But bad moods never last long with the irrepressible Judy Moody, and before long, her day--and year--look brighter. Mr. Todd assigns the class a \"Me\" collage, which sets Judy on a lively and hilarious self-exploration over the next few action-packed weeks.','https://images.gr-assets.com/books/1320540601l/930612.jpg\n','kids',3,32,4,15,2,'Candlewick Press',2002,11.6,16.6),(12637,'Runebinder','Alex R. Kahler',14.99,'Magic is risen.\n\nWhen magic returned to the world, it could have saved humanity, but greed and thirst for power caused mankind\'s downfall instead. Now once-human monsters called Howls prowl abandoned streets, their hunger guided by corrupt necromancers and the all-powerful Kin. Only Hunters have the power to fight back in the unending war, using the same magic that ended civilization in the first place.\n\nBut they are losing.\n\nTenn is a Hunter, resigned to fight even though hope is nearly lost. When he is singled out by a seductive Kin named Tomás and the enigmatic Hunter Jarrett, Tenn realizes he\'s become a pawn in a bigger game. One that could turn the tides of war. But if his mutinous magic and wayward heart get in the way, his power might not be used in favor of mankind.','https://images.gr-assets.com/books/1496958664l/32766049.jpg','young adult',3,15,2,5,1,'Harlequin Teen',2017,10.52,14.99),(13120,'Arthur\'s Family Vacation','Marc Brown',9.45,'Arthur is unhappy about going on vacation with his family, but he shows them how to make the best of a bad situation when they end up stuck in a motel because of rain.','https://images.gr-assets.com/books/1344264302l/491940.jpg\n','kids',4,54,1,20,1,'Little, Brown Books for Young Readers\n',1993,5.59,9.45),(39154,'Leonardo da Vinci','Walter Isaacson',25.99,'Based on thousands of pages from Leonardo’s astonishing notebooks and new discoveries about his life and work, Walter Isaacson weaves a narrative that connects his art to his science. He shows how Leonardo’s genius was based on skills we can improve in ourselves, such as passionate curiosity, careful observation, and an imagination so playful that it flirted with fantasy.\n\nHe produced the two most famous paintings in history, The Last Supper and the Mona Lisa. But in his own mind, he was just as much a man of science and technology. With a passion that sometimes became obsessive, he pursued innovative studies of anatomy, fossils, birds, the heart, flying machines, botany, geology, and weaponry. His ability to stand at the crossroads of the humanities and the sciences, made iconic by his drawing of Vitruvian Man, made him history’s most creative genius.','https://images.gr-assets.com/books/1508059671l/34684622.jpg','biography',4,24,4,10,1,'Simon Schuster',2017,14.88,25.99),(39480,'The Cat in the Hat','Dr.Seuss',10.48,'Poor Sally and her brother. It\'s cold and wet and they\'re stuck in the house with nothing to do . . . until a giant cat in a hat shows up, transforming the dull day into a madcap adventure and almost wrecking the place in the process! Written by Dr. Seuss in 1957 in response to the concern that \"pallid primers [with] abnormally courteous, unnaturally clean boys and girls\' were leading to growing illiteracy among children, The Cat in the Hat (the first Random House Beginner Book) changed the way our children learn how to read.','https://images.gr-assets.com/books/1468890477l/233093.jpg\n','kids',4,12,2,5,1,'Random House',1957,6.99,10.48),(41894,'Cinder ','Marissa Meyer',18.29,'Humans and androids crowd the raucous streets of New Beijing. A deadly plague ravages the population. From space, a ruthless lunar people watch, waiting to make their move. No one knows that Earth’s fate hinges on one girl.\n\nCinder, a gifted mechanic, is a cyborg. She’s a second-class citizen with a mysterious past, reviled by her stepmother and blamed for her stepsister’s illness. But when her life becomes intertwined with the handsome Prince Kai’s, she suddenly finds herself at the center of an intergalactic struggle, and a forbidden attraction. Caught between duty and freedom, loyalty and betrayal, she must uncover secrets about her past in order to protect her world’s future.','https://images.gr-assets.com/books/1507557775l/36381037.jpg','fantasy',4,10,2,7,1,'Feiwel and Friends',2012,13.08,18.29),(42911,'Insurgent (Divergent #2)\n','Veronica Roth',14.99,'One choice can transform you—or it can destroy you. But every choice has consequences, and as unrest surges in the factions all around her, Tris Prior must continue trying to save those she loves—and herself—while grappling with haunting questions of grief and forgiveness, identity and loyalty, politics and love.','https://images.gr-assets.com/books/1325667729l/11735983.jpg\n','science fiction',3,34,1,10,1,'HarperCollins Pubishers',2012,9.99,14.99),(46605,'World War Z','Max brooks',24.58,'The Zombie War came unthinkably close to eradicating humanity. Max Brooks, driven by the urgency of preserving the acid-etched first-hand experiences of the survivors from those apocalyptic years, traveled across the United States of America and throughout the world, from decimated cities that once teemed with upwards of thirty million souls to the most remote and inhospitable areas of the planet. He recorded the testimony of men, women, and sometimes children who came face-to-face with the living, or at least the undead, hell of that dreadful time. \"\"World War Z\"\" is the result. Never before have we had access to a document that so powerfully conveys the depth of fear and horror, and also the ineradicable spirit of resistance, that gripped human society through the plague years.','https://images.gr-assets.com/books/1386328204l/8908.jpg','science fiction',4,32,4,10,1,'Crown',2006,16.48,24.58),(59960,'Anne Frank : The Biography','Melissa Müller',25.99,'The first biography of the girl whose fate has touched the lives of millions. For people all over the world, Anne Frank, the vivacious, intelligent Jewish girl with a crooked smile and huge dark eyes, has become the \"human face of the Holocaust.\" Her diary of twenty-five months in hiding, a precious record of her struggle to keep hope alive through the darkest days of this century, has touched the hearts of millions. Here, after five decades, is the first biography of this remarkable figure. Drawing on exclusive interviews with family and friends, on previously unavailable correspondence, and on documents long kept secret, Melissa Muller creates a nuanced portrait of her famous subject. This is the flesh-and-blood Anne Frank, unsentimentalized and so all the more affecting--Anne Frank restored to history. ','https://images.gr-assets.com/books/1316739030l/905382.jpg','biography',4,105,2,50,1,'Metropolitan Books',1998,11.28,25.99),(63401,'Sleeping Beauties','Stephen King',12.42,'In a future so real and near it might be now, something happens when women go to sleep; they become shrouded in a cocoon-like gauze. If they are awakened, if the gauze wrapping their bodies is disturbed or violated, the women become feral and spectacularly violent; and while they sleep they go to another place. The men of our world are abandoned, left to their increasingly primal devices. One woman, however, the mysterious Evie, is immune to the blessing or curse of the sleeping disease. Is Evie a medical anomaly to be studied, or is she a demon who must be slain?','https://images.gr-assets.com/books/1510335748l/34466922.jpg','fantasy',4,10,2,7,1,'Scribner',2017,5.42,12.42),(65355,'Harry Potter #8','J.K. Rowling',15.99,'It was always difficult being Harry Potter and it isn\'t much easier now that he is an overworked employee of the Ministry of Magic, a husband, and father of three school-age children.\n\nWhile Harry grapples with a past that refuses to stay where it belongs, his youngest son Albus must struggle with the weight of a family legacy he never wanted. As past and present fuse ominously, both father and son learn the uncomfortable truth: sometimes, darkness comes from unexpected places.','https://images.gr-assets.com/books/1470082995l/29056083.jpg','young adult',4,25,2,10,1,'Little Brown',2016,8.98,15.99),(73288,'Children of Time','Adrian Tchaikovsky',15,'A race for survival among the stars... Humanity\'s last survivors escaped earth\'s ruins to find a new home. But when they find it, can their desperation overcome its dangers?\n\nWHO WILL INHERIT THIS NEW EARTH?\n\nThe last remnants of the human race left a dying Earth, desperate to find a new home among the stars. Following in the footsteps of their ancestors, they discover the greatest treasure of the past age - a world terraformed and prepared for human life.\n\nBut all is not right in this new Eden. In the long years since the planet was abandoned, the work of its architects has borne disastrous fruit. The planet is not waiting for them, pristine and unoccupied. New masters have turned it from a refuge into mankind\'s worst nightmare.\n\nNow two civilizations are on a collision course, both testing the boundaries of what they will do to survive. As the fate of humanity hangs in the balance, who are the true heirs of this new Earth?','https://images.gr-assets.com/books/1431014197l/25499718.jpg\n','science fiction',4,12,1,5,1,'PanMacmillan ',2015,11.14,15),(78812,'The Fault in Our Stars','John Green',15,'Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group, Hazel\'s story is about to be completely rewritten.\n\nInsightful, bold, irreverent, and raw, The Fault in Our Stars is award-winning author John Green\'s most ambitious and heartbreaking work yet, brilliantly exploring the funny, thrilling, and tragic business of being alive and in love.','https://images.gr-assets.com/books/1360206420l/11870085.jpg','young adult',4,35,3,10,1,'Dutton Books',2012,12.37,15),(82024,'A Feast for Crows (A Song of Ice and Fire #4)','George R.R Martin',25,'With A Feast for Crows, Martin delivers the long-awaited fourth volume of the landmark series that has redefined imaginative fiction and stands as a modern masterpiece in the making.\n\nAfter centuries of bitter strife, the seven powers dividing the land have beaten one another into an uneasy truce. But it\'s not long before the survivors, outlaws, renegades, and carrion eaters of the Seven Kingdoms gather. Now, as the human crows assemble over a banquet of ashes, daring new plots and dangerous new alliances are formed while surprising faces—some familiar, others only just appearing—emerge from an ominous twilight of past struggles and chaos to take up the challenges of the terrible times ahead. Nobles and commoners, soldiers and sorcerers, assassins and sages, are coming together to stake their fortunes...and their lives. For at a feast for crows, many are the guests—but only a few are the survivors.','https://images.gr-assets.com/books/1429538615l/13497.jpg','fantasy',4,23,4,5,1,'Bantam Books',2006,8.39,25),(95443,'Captain Underpants (#5)\n','Dav Pilkey\n',12.99,'George and Harold are up to their usual tricks. Last time they hypnotized Principal Krupp into believing he was Captain Underpants. Now, by mistake, they bring to life the most disgusting, life-threatening monster -- the terrible Turbo Toilet 2000. Can anyone save the school from the terrifying attack of the Talking Toilets? Watch out world, big briefs are back!','https://images.gr-assets.com/books/1348349083l/153865.jpg\n','kids',4,23,2,10,1,'Scholastic\n',2000,5.96,12.99);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-03 17:56:07
