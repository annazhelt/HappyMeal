xSET FOREIGN_KEY_CHECKS=0;
drop table if exists users cascade;
drop table if exists admin;
drop table if exists customer cascade;
drop table if exists member cascade;
drop table if exists placeorder cascade;
drop table if exists dish cascade;
drop table if exists orderdish cascade;
drop table if exists ingredient cascade;
drop table if exists dishingredient cascade;
drop table if exists exclude cascade;
drop table if exists restaurant cascade;
drop table if exists review cascade;
drop table if exists tag cascade;
drop table if exists likes cascade;
drop table if exists dishtag cascade;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE users (
    uname VARCHAR(30) PRIMARY KEY,
    password VARCHAR(15),
    isAdminFlag CHAR(1)
);
insert into users values
	('Anna-a', 'Anna111', 'Y'),
	('Valeria-a', 'Valeria222', 'Y'),
	('Vittoria-a', 'Vittoria333', 'Y'),
	('Yvonne-a', 'Yvonne777', 'Y'),
	('Joe-a', 'Joe999', 'Y'),
    ('Anna-c', 'Anna111', 'N'),
	('Valeria-c', 'Valeria222', 'N'),
	('Vittoria-c', 'Vittoria333', 'N'),
	('Yvonne-c', 'Yvonne777', 'N'),
	('Joe-c', 'Joe999', 'N'),
    ('Alice', 'Alice', 'N');
CREATE TABLE admin (
    aname VARCHAR(30) PRIMARY KEY,
    FOREIGN KEY (aname)
        REFERENCES users (uname)
        ON DELETE CASCADE
);
insert into admin values
	('Anna-a'),
	('Valeria-a'),
	('Vittoria-a'),
	('Yvonne-a'),
	('Joe-a');
CREATE TABLE customer (
    cname VARCHAR(30) PRIMARY KEY,
    phone DECIMAL(10) NOT NULL,
    FOREIGN KEY (cname)
        REFERENCES users (uname)
        ON DELETE CASCADE
);
insert into customer values
    ('Anna-c', '7782232373'),
	('Valeria-c', '6047159378'),
	('Vittoria-c', '6044459166'),
	('Yvonne-c', '7782326523'),
	('Joe-c', '7780000000'),
    ('Alice', '6040000000');
CREATE TABLE member (
    mname VARCHAR(30) PRIMARY KEY,
    points DECIMAL(10) NULL,
    date_joined DATE NOT NULL,
    FOREIGN KEY (mname)
        REFERENCES users (uname)
        ON DELETE CASCADE
);
insert into member values
    ('Anna-c', '50', '2016-05-31'),
	('Valeria-c', '50', '2016-05-31'),
	('Vittoria-c', '50', '2016-05-31'),
	('Yvonne-c', '50', '2016-05-31'),
	('Joe-c', '500', '2016-05-31');
CREATE TABLE placeorder (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(30) NOT NULL,
    delivery_address VARCHAR(40) NULL,
    delivery_time DATETIME,
    delivery_method VARCHAR(20) NOT NULL,
    current_status VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_name)
        REFERENCES users (uname)
        ON DELETE CASCADE
);
insert into placeorder (user_name, delivery_address, delivery_time, delivery_method, current_status)values
    ('Anna-c', '0101 CS building, Vancouver', '2016-06-02 18:00:00', 'deliver', 'delivered'),
    ('Yvonne-c', '0202 Garden City Rd, Richmond', '2016-06-01 19:30:00', 'deliver', 'reviewed'),
    ('Joe-c', '0303 McKim Way, Richmond', '2016-06-01 17:30:00', 'deliver', 'reviewed'),
    ('Joe-c', '', '2016-06-02 18:15:00', 'pick up', 'ready to pick up'),
    ('Vittoria-c', '0505 3rd Rd, Richmond ', '2016-06-02 20:30:00', 'deliver', 'preparing');
CREATE TABLE restaurant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rname VARCHAR(20) NOT NULL,
    admin_name VARCHAR(30) NOT NULL,
    phone DECIMAL(10) NULL,
    address VARCHAR(200) NOT NULL,
    FOREIGN KEY (admin_name)
        REFERENCES admin (aname)
        ON DELETE CASCADE
);
insert into Restaurant (rname, admin_name, phone, address) values 
	('U&I Thai Cusine', 'Anna-a', '6044431234', '172 sunshine st, vancouver, v6t5f1'),
	('La Catrina Tacos', 'Valeria-a','7789912566', '1187 Denman st, vancouver, v7u5d2'),
	('Loade Cafe', 'Vittoria-a', '7786779652', '6488 university st, vancouver, c9y2t3'),
	('Point Grill', 'Joe-a', '6048229503', '2205 lower mall, vancouver, v6t1z4'),
	('Tim Hortons', 'Yvonne-a', '6048220002', '2205 ubc forestry building, vancouver, v6t1z4'),
  ('Old Spaghetti', 'Vittoria-a', '7786999652', '7654 discovery way, victoria, v5b3j1'),
  ('Lamplighter', 'Joe-a', '6048229345', '3456 king edward avenue, ottawa, o3d6g4'),
  ('Flying Pig', 'Yvonne-a', '6042560002', '1234 2nd avenue, calgary, c5g3d7');

CREATE TABLE dish (
    restaurant_id INT,
    dname VARCHAR(100) NOT NULL,
    price DECIMAL(6 , 2 ) NOT NULL,
    PRIMARY KEY (restaurant_id , dname),
    FOREIGN KEY (restaurant_id)
        REFERENCES restaurant (id)
        ON DELETE CASCADE
);
insert into dish (restaurant_id, dname, price)values 
	('2', 'fish taco','8.60'),
    ('2', 'chicken salad', '3.25'),
	('3', 'Toufu', '4.00'),
	('3', 'green tee icecream', '2.00'),
    ('4', 'Roast ribs', '18.30'),
	('4', 'veggie burger', '12.00'),
	('5', 'iced lemonade', '2.00'),
	('5', 'crispy chicken sandwich', '5.50'),
	('5', 'sausage sandwich', '5.50'),
	('5', 'white chocolate cookie', '5.50'),
  ('6', 'chicken salad', '5.00'),
  ('6', 'club sandwich', '6.00'),
  ('7', 'dark chocolate cookie', '2.50'),
  ('7', 'cheesecake', '5.50'),
  ('8', 'spaghetti with meat balls', '11.50'),
  ('8', 'black forest cake', '5.50');

CREATE TABLE orderdish (
    order_id INT NOT NULL,
    restaurant_id INT,
    dish_name VARCHAR(100),
    PRIMARY KEY (restaurant_id , dish_name , order_id),
    FOREIGN KEY (order_id)
        REFERENCES placeorder (id)
        ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id , dish_name)
        REFERENCES dish (restaurant_id , dname)
        ON DELETE CASCADE
);
insert into orderdish values
    ('1', '4', 'veggie burger'),
    ('1', '3', 'Toufu'),
    ('1', '3', 'green tee icecream'),
    ('2', '3', 'green tee icecream'),
    ('2', '2', 'chicken salad'),
    ('2', '2', 'fish taco'),
    ('3', '5', 'iced lemonade'),
    ('3', '5', 'crispy chicken sandwich'),
    ('3', '5', 'white chocolate cookie'),
    ('4', '2', 'fish taco'),
    ('4', '2', 'chicken salad'),
    ('4', '3', 'green tee icecream'),
    ('5', '3', 'green tee icecream'),
    ('5', '4', 'veggie burger');
CREATE TABLE dishingredient (
    restaurant_id INT,
    dish_name VARCHAR(100),
    ingre_name VARCHAR(20),
    PRIMARY KEY (restaurant_id , dish_name , ingre_name),
    FOREIGN KEY (restaurant_id , dish_name)
        REFERENCES dish (restaurant_id , dname)
        ON DELETE CASCADE
);
insert into dishingredient values 
	('2', 'fish taco','fish'),
	('2', 'fish taco','carrot'),
	('2', 'fish taco','onion'),
	('2', 'fish taco','ketchup'),
    ('2', 'chicken salad', 'chicken'),
    ('2', 'chicken salad', 'black pepper'),
    ('2', 'chicken salad', 'almond'),
    ('2', 'chicken salad', 'lemon'),
	('3', 'Toufu', 'toufu'),
	('3', 'Toufu', 'green onion'),
	('3', 'green tee icecream', 'milk'),
	('3', 'green tee icecream', 'green tea powder'),
	('3', 'green tee icecream', 'sugar'),
	('3', 'green tee icecream', 'egg'),
    ('4', 'Roast ribs', 'pork baby back rib'),
    ('4', 'Roast ribs', 'black pepper'),
    ('4', 'Roast ribs', 'red pepper'),
    ('4', 'Roast ribs', 'beer'),
    ('4', 'Roast ribs', 'barbecue sauce'),
	('4', 'veggie burger', 'onion'),
	('4', 'veggie burger', 'mushroom'),
	('4', 'veggie burger', 'garlic'),
	('4', 'veggie burger', 'carrot'),
	('4', 'veggie burger', 'tomato'),
	('4', 'veggie burger', 'bread'),
	('5', 'iced lemonade', 'lemonade'),
	('5', 'iced lemonade', 'ice'),
	('5', 'iced lemonade', 'sugar'),
	('5', 'crispy chicken sandwich', 'chicken'),
	('5', 'crispy chicken sandwich', 'lettuce'),
	('5', 'crispy chicken sandwich', 'tomato'),
	('5', 'crispy chicken sandwich', 'cheese'),
	('5', 'crispy chicken sandwich', 'bread'),
	('5', 'sausage sandwich', 'sausage'),
	('5', 'sausage sandwich', 'egg'),
	('5', 'sausage sandwich', 'cheese'),
	('5', 'sausage sandwich', 'bread'),
	('5', 'white chocolate cookie', 'white chocolate'),
	('5', 'white chocolate cookie', 'milk'),
	('5', 'white chocolate cookie', 'egg'),
	('5', 'white chocolate cookie', 'cake flour'),
	('5', 'white chocolate cookie', 'brown sugar');
CREATE TABLE exclude (
    restaurant_id INT,
    order_id INT,
    dish_name VARCHAR(100),
    ingre_name VARCHAR(20),
    PRIMARY KEY (restaurant_id , order_id , dish_name , ingre_name),
    FOREIGN KEY (order_id)
        REFERENCES placeorder (id)
        ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id , dish_name , ingre_name)
        REFERENCES dishingredient (restaurant_id , dish_name , ingre_name)
        ON DELETE CASCADE
);
insert into exclude (order_id, restaurant_id, dish_name, ingre_name)values
    ('1', '4', 'veggie burger', 'carrot'),
    ('2', '2', 'chicken salad', 'black pepper'),
    ('3', '5', 'iced lemonade', 'ice'),
    ('3', '5', 'crispy chicken sandwich', 'cheese'),
    ('5', '4', 'veggie burger', 'mushroom');
CREATE TABLE review (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rating INT NOT NULL,
    comments VARCHAR(3000) NULL,
    user_name VARCHAR(30) NOT NULL,
    restaurant_id INT NOT NULL,
    FOREIGN KEY (user_name)
        REFERENCES users (uname)
        ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id)
        REFERENCES restaurant (id)
        ON DELETE CASCADE
);
insert into review (user_name, restaurant_id, rating, comments) values 
    ('Anna-c', '2', '8', 'nice and delicious'),
    ('Anna-c', '3', '5', 'too spicy'),
    ('Yvonne-c', '3', '8', 'icecream is good'),
    ('Yvonne-c', '2', '5', 'not spicy enough'),
    ('Joe-c', '5', '7', 'too sweet'),
    ('Joe-c', '2', '8', 'salad is good'),
    ('Vittoria-c', '4', '6', 'just soso');
CREATE TABLE tag (
    tname VARCHAR(20) PRIMARY KEY
);
insert into tag values 
	('spicy'),
	('sweet'),
	('sour'),
	('salty'),
	('cold'),
	('veggie'),
	('hot');
CREATE TABLE likes (
    user_name VARCHAR(30) NOT NULL,
    tag_name VARCHAR(20) NOT NULL,
    PRIMARY KEY (tag_name , user_name),
    FOREIGN KEY (user_name)
        REFERENCES users (uname)
        ON DELETE CASCADE,
    FOREIGN KEY (tag_name)
        REFERENCES tag (tname)
        ON DELETE CASCADE
);
insert into likes values 
	('Joe-c','spicy'),
	('Yvonne-c','sweet'),
	('Vittoria-c','sweet'),
	('Yvonne-c','sour'),
	('Joe-c','salty'),
	('Yvonne-c','cold'),
	('Anna-c','veggie');
CREATE TABLE dishtag (
    restaurant_id INT NOT NULL,
    dish_name VARCHAR(100) NOT NULL,
    tag_name VARCHAR(20),
    PRIMARY KEY (tag_name , dish_name , restaurant_id),
    FOREIGN KEY (restaurant_id , dish_name)
        REFERENCES dish (restaurant_id , dname)
        ON DELETE CASCADE,
    FOREIGN KEY (tag_name)
        REFERENCES tag (tname)
        ON DELETE CASCADE
);
insert into dishtag values 
	('2', 'fish taco','hot'),
	('2', 'fish taco','spicy'),
    ('2', 'chicken salad', 'sweet'),
	('3', 'Toufu', 'veggie'),
	('3', 'Toufu', 'spicy'),
	('3', 'green tee icecream', 'cold'),
	('3', 'green tee icecream', 'sweet'),
    ('4', 'Roast ribs', 'hot'),
    ('4', 'Roast ribs', 'salty'),
	('4', 'veggie burger', 'veggie'),
	('5', 'iced lemonade', 'cold'),
	('5', 'iced lemonade', 'sour'),
	('5', 'crispy chicken sandwich', 'hot'),
	('5', 'sausage sandwich', 'hot'),
	('5', 'white chocolate cookie', 'sweet');
    
