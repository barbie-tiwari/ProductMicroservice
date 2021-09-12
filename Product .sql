drop database productdb;
create schema `productdb`;
use `productdb`;

create table product(prodid varchar(50) primary key, productname varchar(50),price double,stock integer,description varchar(100),image varchar(200),sellerid varchar(10),category varchar(50),subcategory varchar(50),productrating integer);

insert into product values('P101','HP 15 10th Gen',500.0,2,'Laptop','https://m.media-amazon.com/images/I/81Ne5qKmE8L._SL1500_.jpg','S101','Electronics','smart phones',4);
insert into product values('P102','apple',450.0,10,'earphones','https://m.media-amazon.com/images/I/71zny7BTRlL._AC_SX522_.jpg','S102','accesories','air pods',5);
insert into product values('P103','realme',300.0,8,'watch','https://images.indianexpress.com/2020/06/smartwatch-1200-1.jpg','S103','gadgets','smart watch',4);



create table subscribedproduct(buyerid varchar(50),prodid varchar(50), primary key(buyerid,prodid),quantity integer);

insert into subscribedproduct values('B101','P101',1);
insert into subscribedproduct values('B102','P102',2);
insert into subscribedproduct values('B103','P103',3);

use productdb;
select * from product;
select * from subscribedproduct;


