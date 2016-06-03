SET FOREIGN_KEY_CHECKS=0;
drop table if exists users cascade;
drop table if exists admin cascade;
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
create table users (
	uname varchar(30) primary key,
	password  varchar(15),
	isAdminFlag decimal(1));
insert into users values
	('Anna-a', 'Anna111', '1'),
	('Valeria-a', 'Valeria222', '1'),
	('Vittoria-a', 'Vittoria333', '1'),
	('Yvonne-a', 'Yvonne777', '1'),
	('Joe-a', 'Joe999', '1'),
    ('Anna-c', 'Anna111', '0'),
	('Valeria-c', 'Valeria222', '0'),
	('Vittoria-c', 'Vittoria333', '0'),
	('Yvonne-c', 'Yvonne777', '0'),
	('Joe-c', 'Joe999', '0'),
    ('Alice', 'Alice', '0');
create table admin (
	aname varchar(30) primary key,
	foreign key (aname) references users(uname) ON DELETE CASCADE);
insert into admin values
	('Anna-a'),
	('Valeria-a'),
	('Vittoria-a'),
	('Yvonne-a'),
	('Joe-a');
create table customer (
	cname varchar(30) primary key,
	phone decimal(10) not null,
	foreign key (cname) references users(uname) ON DELETE CASCADE);
create table member (
	mname varchar(30) primary key,
	points decimal(10) null,
	date_joined date not null,
	foreign key (mname) references users(uname) ON DELETE CASCADE);
create table placeorder (
	id int primary key auto_increment,
    user_name varchar(30) not null,
	delivery_address varchar(40) null,
	delivery_time timestamp,
	delivery_method varchar(20) not null,
	current_status varchar(20) not null,
	foreign key (user_name) references users(uname) ON DELETE CASCADE);
create table restaurant (
	id int primary key auto_increment,
	tname varchar(20) not null,
	admin_name varchar(30) not null, /* assume user.username varchar(30)*/
	phone decimal(10) null,
    foreign key (admin_name) references admin(aname) on delete cascade);
create table dish (
	restaurant_id int,
	dname varchar(100) not null,
	price decimal(6,2) not null,
    primary key (restaurant_id, dname),
    foreign key (restaurant_id) references restaurant(id) on delete cascade);
create table orderdish (
	order_id int not null,
    restaurant_id int,
	dish_name varchar(100),
	primary key (restaurant_id, dish_name, order_id),
    foreign key(order_id) references placeorder(id) ON DELETE CASCADE,
	foreign key(restaurant_id, dish_name) references dish(restaurant_id, dname) ON DELETE CASCADE);    
create table dishingredient (
	restaurant_id int,
	dish_name varchar(100),
	ingre_name varchar(20),
	primary key (restaurant_id, dish_name, ingre_name),
	foreign key(restaurant_id, dish_name) references dish(restaurant_id, dname) ON DELETE CASCADE); 
create table exclude (
	restaurant_id int,
    order_id int,
    dish_name varchar(100),
    ingre_name varchar(20),
    primary key (restaurant_id, order_id, dish_name, ingre_name),
	foreign key (order_id) references placeorder(id) ON DELETE CASCADE,
    foreign key (restaurant_id, dish_name, ingre_name) references dishingredient(restaurant_id, dish_name, ingre_name) ON DELETE CASCADE);
create table review (
	id int primary key auto_increment,
	rating int not null,
	comments varchar(3000) null,
	user_name varchar(30) not null,
	restaurant_id int not null,
    foreign key (user_name) references users(uname) on delete cascade,
    foreign key (restaurant_id) references restaurant(id) on delete cascade);
create table tag (
	tname varchar(20) primary key);
create table likes (
	tag_name varchar(20) not null,
    user_name varchar(30) not null,
    primary key (tag_name, user_name),
    foreign key (user_name) references users(uname) on delete cascade,
    foreign key (tag_name) references tag (tname) on delete cascade);    
create table dishtag (
	restaurant_id int not null,
    dish_name varchar(100) not null,
    tag_name varchar(20), 
    primary key (tag_name, dish_name, restaurant_id),
    foreign key (restaurant_id, dish_name) references dish(restaurant_id, dname) on delete cascade,
    foreign key (tag_name) references tag (tname) on delete cascade);
    
