CREATE TABLE `pet_supplies`.`category` (
`CAT_ID` INT NOT NULL,
`NAME` VARCHAR(40),
`CODE` VARCHAR(20),
PRIMARY KEY (`CAT_ID`)
);

CREATE TABLE `pet_supplies`.`image` (
`IMG_ID` INT NOT NULL,
`NAME` VARCHAR(20),
`URL` VARCHAR(40),
`product_id` INT(11) NOT NULL,
PRIMARY KEY (`IMG_ID`),
KEY `fk_product` (`product_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
);


CREATE TABLE `pet_supplies`.`product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` varchar(512) NOT NULL,
  `price` float NOT NULL,
  `currency` VARCHAR(10),
  `status` boolean,
  `quantity` INT NOT NULL,
  `cat_id` INT(11) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_category` (`cat_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`)
);

CREATE TABLE `pet_supplies`.`user` (
`USER_ID` INT(10) NOT NULL AUTO_INCREMENT,
`NAME` VARCHAR(30) NOT NULL,
PRIMARY KEY (`USER_ID`)
);


CREATE TABLE `pet_supplies`.`cart` (
`ID` INT NOT NULL AUTO_INCREMENT,
`PRODUCT_ID` INT NOT NULL,
`PRODUCT_NAME` VARCHAR(500),
`QUANTITY` INT NOT NULL,
`USER_ID` INT NOT NULL,
`PRICE` float NOT NULL,
`CURRENCY` VARCHAR(5) NOT NULL,
PRIMARY KEY (`ID`),
KEY `fk_user` (`USER_ID`),
CONSTRAINT `fk_user` FOREIGN KEY (`USER_ID`) references `user` (`USER_ID`)
);

CREATE TABLE `pet_supplies`.`address` (
`ADDRESS_ID` INT NOT NULL AUTO_INCREMENT,
`ADDRESS` VARCHAR(200) NOT NULL,
`CITY` VARCHAR(50) NOT NULL,
`STATE` VARCHAR(50) NOT NULL,
`ZIP_CODE` VARCHAR(50) NOT NULL,
`EMAIL` VARCHAR(50) NOT NULL,
`PHONE` VARCHAR(50) NOT NULL,
`COUNTRY` VARCHAR(20) NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`ADDRESS_ID`),
KEY `fk_user_address` (`USER_ID`),
CONSTRAINT `fk_user_address` FOREIGN KEY (`USER_ID`) references `user` (`USER_ID`)
);


CREATE TABLE `pet_supplies`.`orders` (
`ORDER_ID` INT NOT NULL AUTO_INCREMENT,
`PRODUCT_ID` INT NOT NULL,
`PRODUCT_NAME` VARCHAR(500),
`PRODUCT_PRICE` float,
`CURRENCY` VARCHAR(10),
`QUANTITY` INT(10),
`STATUS` VARCHAR(50),
`SHIPPING_ADDRESS` VARCHAR(10000),
`ORDER_DATE` datetime,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`ORDER_ID`),
KEY `fk_order_user` (`USER_ID`),
CONSTRAINT `fk_order_user` FOREIGN KEY (`USER_ID`) references `user` (`USER_ID`)
);

commit;

CREATE TABLE `pet_supplies`.`wallet` (
`WALLET_ID` INT NOT NULL AUTO_INCREMENT,
`AMOUNT` VARCHAR(10) NOT NULL,
`CURRENCY` VARCHAR(5) NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`WALLET_ID`),
KEY `fk_user_wallet` (`USER_ID`),
CONSTRAINT `fk_user_wallet` FOREIGN KEY (`USER_ID`) references `USER` (`USER_ID`)
);

CREATE TABLE `pet_supplies`.`authenticate_user` (
`ID` INT NOT NULL AUTO_INCREMENT,
`PASSWORD` VARCHAR(50) NOT NULL,
`EMAIL_ID` VARCHAR(100) NOT NULL,USER_ID
`PHONE` VARCHAR(50) NOT NULL,
`ACTIVE` boolean NOT NULL,
`USER_ID` INT NOT NULL,
PRIMARY KEY (`ID`)
);

   
CREATE TABLE `pet_supplies`.`payment` (
`PAY_ID` INT NOT NULL AUTO_INCREMENT,
`CARD_TYPE` VARCHAR(20) NOT NULL,
`AMOUNT` float NOT NULL,
`CURRENCY` VARCHAR(10) NOT NULL,
`CARD_NUMBER` VARCHAR(100) NOT NULL,
`CVV` INT(10) NOT NULL,
`EXPIRY_MONTH` INT(10) NOT NULL,
`EXPIRY_YEAR` INT(20) NOT NULL,
`USER_ID` INT(20) NOT NULL,
`ORDER_ID` INT(20) NOT NULL,
PRIMARY KEY (`PAY_ID`)
);

INSERT INTO `pet_supplies`.`category` (`CAT_ID`, `NAME`, `CODE`) VALUES ('100', 'Cats', 'CAT');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1000', 'BENGAL', 'Bengal cat', '2500', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('2', 'Cats', '/images/cats/himalayan.jpg', '1000');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1001', 'HIMALAYAN', 'Himalayan cat', '1500', 'EUR', '1', '1', '100');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1002', 'Bombay', 'Bombay', '2000', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('8', 'Cats', '/images/cats/bombay.jpg', '1002');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1003', 'Persian', 'Persian', '2500', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('9', 'Cats', '/images/cats/persian.jpg', '1003');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1004', 'Scottish Fold', 'Scottish Fold', '3500', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('10', 'Cats', '/images/cats/scottish.jpg', '1004');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1005', 'Farmina Matisse Salmon', 'Farmina Matisse Salmon', '100', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('11', 'Cats', '/images/cats/FarminaMatisseSalmon.jpg', '1005');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1006', 'Whiskas Kitten Cat Food', 'Whiskas Kitten Cat Food Junior Ocean Fish', '300', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('12', 'Cats', '/images/cats/WhiskasOceanFish.jpg', '1006');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('1007', 'Drools Adult Cat Food', 'Drools Adult Cat Food Ocean Fish', '500', 'EUR', '1', '1', '100');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('13', 'Cats', '/images/cats/DroolsOceanFish.jpg', '1007');


INSERT INTO `pet_supplies`.`category` (`CAT_ID`, `NAME`, `CODE`) VALUES ('101', 'Dogs', 'DOG');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2000', 'Dachshund', 'Dachshund', '4500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('3', 'Dogs', '/images/dachshund.jpg', '2000');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2001', 'Airedale Terrier', 'Airedale Terrier', '3500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('4', 'Dogs', '/images/airedale.jpg', '2001');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2002', 'Barbet', 'Barbet', '5500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('5', 'Dogs', '/images/barbet.jpg', '2002');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2003', 'Boxer', 'Boxer', '3500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('6', 'Dogs', '/images/boxer.jpg', '2003');


INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2004', 'Cockapoo', 'Cockapoo', '6500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('7', 'Dogs', '/images/cockapoo.jpg', '2004');


INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2005', 'Pedigree Adult Dog Food', 'Pedigree Adult Dog Food Chicken', '500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('14', 'Dogs', '/images/PedigreeAdultDogFoodChicken.jpg', '2005');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2006', 'Choostix Dog Feeding Bowl Steel', 'Choostix Dog Feeding Bowl Steel', '500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('15', 'Dogs', '/images/ChoostixDogFeedingBowlSteel.jpg', '2006');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2007', 'Royal Canin Giant Puppy', 'Royal Canin Giant Puppy, 4 kg', '500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('16', 'Dogs', '/images/RoyalCaninGiantPuppy.jpg', '2007');

INSERT INTO `pet_supplies`.`product` (`product_id`, `name`, `description`, `price`, `currency`, `status`, `quantity`, `cat_id`) VALUES ('2008', 'Choostix Dog Rope', 'Choostix Dog Rope Chain Synthetic Yarn, Medium', '500', 'EUR', '1', '1', '101');
INSERT INTO `pet_supplies`.`image` (`IMG_ID`, `NAME`, `URL`, `product_id`) VALUES ('17', 'Dogs', '/images/ChoostixDogRope.jpg', '2008');


INSERT INTO `pet_supplies`.`user` (`USER_ID`, `PHONE`, `EMAIL_ID`, `NAME`) VALUES ('1000', '9886162877', 'nageswararao.janjyala@capgemini.com', 'Nageswararao');

INSERT INTO `pet_supplies`.`category` (`CAT_ID`, `NAME`, `CODE`) VALUES ('102', 'Birds', 'BIRDS');
INSERT INTO `pet_supplies`.`category` (`CAT_ID`, `NAME`, `CODE`) VALUES ('104', 'Birds', 'BIRDS');
