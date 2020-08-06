SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
delete from taco_order_tacos;
delete from taco_ingredients;
delete from taco;
delete from taco_order;
delete from ingredient;
insert into ingredient values('FLTO','Flour Tortilla','WRAP');
insert into ingredient values('COTO', 'Corn Tortilla', 'WRAP');
insert into ingredient values('GRBF', 'Ground Beef', 'PROTEIN');
insert into ingredient values('CARN', 'Carnitas', 'PROTEIN');
insert into ingredient values('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into ingredient values('LETC', 'Lettuce', 'VEGGIES');
insert into ingredient values('CHED', 'Cheddar', 'CHEESE');
insert into ingredient values('JACK', 'Monterrey Jack', 'CHEESE');
insert into ingredient values('SLSA', 'Salsa', 'SAUCE');
insert into ingredient values('SRCR', 'Sour Cream', 'SAUCE');
INSERT INTO tacocloud.users(id, username, password, fullname, street, city, state, zip, phone_number)
VALUES(1, 'zhai', '$2a$10$b1sI8OP8zOJY2iqisgVx1exay0S./dZm/AxKrGL.5QcmAoevwLEBq', 'zhaidg', 'beijing', 'beijing', '1', '2', 18909898999);
SET FOREIGN_KEY_CHECKS = 1;