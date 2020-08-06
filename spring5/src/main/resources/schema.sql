SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `ingredient`;
DROP TABLE IF EXISTS `taco`;
DROP TABLE IF EXISTS `taco_ingredients`;
DROP TABLE IF EXISTS `taco_order`;
DROP TABLE IF EXISTS `taco_order_tacos`;
DROP TABLE IF EXISTS `users`;

create table `ingredient` (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    PRIMARY KEY (`id`)
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
create table `taco` (
    id int(11) not null AUTO_INCREMENT,
    name varchar(50) not null,
    createdAt timestamp not null,
     PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
create table `taco_ingredients` (
    taco_id int(11) not null,
    ingredients_id varchar(4) not null
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
alter table taco_ingredients add foreign key(taco_id) references taco(id);
alter table taco_ingredients add foreign key(ingredients_id) references ingredient(id);

create table `taco_order` (
    id int(11) not null AUTO_INCREMENT,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null,
    user_id bigint(16) not null,
    PRIMARY KEY (`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;


create table `taco_order_tacos` (
    tacos_id int(11) not null,
    order_id int(11) not null
)ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
alter table taco_order_tacos add foreign key(order_id) references taco_order(id);
alter table taco_order_tacos add foreign key(tacos_id) references taco(id);

CREATE TABLE `users` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `phone_number` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;