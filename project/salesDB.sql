#MGG Sales system database design
#Authors nzetocha2 and jbargen3

#If tables exist, they are dropped
drop table if exists SaleItem;
drop table if exists Sale;
drop table if exists Store;
drop table if exists Item;
drop table if exists PersonEmail;
drop table if exists Email;
drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;


#Table for countries
create table Country (
	countryId int not null primary key auto_increment,
    name varchar(20)
)engine=InnoDB,collate=latin1_general_cs;

#Table for states
create table State (
	stateId int not null primary key auto_increment,
    name varchar(20)
)engine=InnoDB,collate=latin1_general_cs;

#Table for addresses for a person/store address
 create table Address (
	addressId int not null primary key auto_increment,
    street varchar(255),
    city varchar(100),
    stateId int not null,
    zipCode varchar(5),
    countryId int not null,
    foreign key (stateId) references State(stateId),
    foreign key (countryId) references Country(countryId)
)engine=InnoDB,collate=latin1_general_cs;

#Table for people, customer or employee
create table Person (
	personId int not null primary key auto_increment,
    personCode varchar(6) not null unique,
    type varchar(1),
	firstName varchar(50) not null,
	lastName varchar(50) not null,
    addressId int not null,
    foreign key (addressId) references Address(addressId),
    constraint check (type = 'E' or type = 'P' or type = 'G' or type = 'C' and type is not null)
)engine=InnoDB,collate=latin1_general_cs;

#Table for people's emails
create table Email (
	emailId int not null primary key auto_increment,
	email varchar(255)
)engine=InnoDB,collate=latin1_general_cs;

#Table linking people to their emails
create table PersonEmail (
	personEmailId int not null primary key auto_increment,
    emailId int not null,
    personId int not null,
    foreign key (emailId) references Email(emailId),
    foreign key (personId) references Person(personId),
    #Prevents an email from being used for more than one person
    constraint uniquePair unique index(emailId,personId)
)engine=InnoDB,collate=latin1_general_cs;

#Table for items such as products, services, or subscriptions
create table Item (
	itemId int not null primary key auto_increment,
	itemCode varchar(6) not null unique,
    type varchar(2) not null,
    name varchar(255) not null,
    basePrice double default null,
    constraint check (type = 'PN' or type = 'PG' or type = 'PU' or type = 'SV' or type = 'SB' and type is not null)
)engine=InnoDB,collate=latin1_general_cs;

#Table for stores
create table Store (
	storeId int not null primary key auto_increment,
    storeCode varchar(8) not null unique,
    managerId int not null,
    addressId int not null,
    foreign key (managerId) references Person(personId),
    foreign key (addressId) references Address(addressId)
)engine=InnoDB,collate=latin1_general_cs;

#Table for sales made by stores and employees to customers
create table Sale (
	saleId int not null primary key auto_increment,
    saleCode varchar(20) not null unique,
    storeId int default null,
    customerId int default null,
    salepersonId int default null,
    foreign key (storeId) references Store(storeId),
    foreign key (customerId) references Person(personId),
    foreign key (salepersonId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

#Table linking sales to items
create table SaleItem (
	saleItemId int not null primary key auto_increment,
    saleId int not null,
    itemId int not null,
    #Products
    productQuantity int default null,
    giftCardPrice double default null,
    #Services
    employeeId int default null,
    numberOfHours double default null,
    #Subscriptions
    beginDate varchar(10) default null,
    endDate varchar(10) default null,
    foreign key (saleId) references Sale(saleId),
	foreign key (itemId) references Item(itemId),
    foreign key (employeeId) references Person(personId),
    #Prevent duplicate entries
    constraint uniquePair unique index(saleId,itemId)
)engine=InnoDB,collate=latin1_general_cs;

#All address countries used
insert into Country (countryId,name) values (1,'US');

#All address states used
insert into State (stateId,name) values (1,'NY');
insert into State (stateId,name) values (2,'TX');
insert into State (stateId,name) values (3,'GA');
insert into State (stateId,name) values (4,'MO');
insert into State (stateId,name) values (5,'NE');
insert into State (stateId,name) values (6,'FL');
insert into State (stateId,name) values (7,'AZ');
insert into State (stateId,name) values (8,'OK');
insert into State (stateId,name) values (9,'OR');
insert into State (stateId,name) values (10,'MI');
insert into State (stateId,name) values (11,'MN');
insert into State (stateId,name) values (12,'OH');
insert into State (stateId,name) values (13,'IN');
insert into State (stateId,name) values (14,'NC');
insert into State (stateId,name) values (15,'WV');

#Persons Addresses 
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(1,'494 Dovetail Crossing','Hicksville',1,'58062',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(2,'2588 Sauthoff Park','Pasadena',2,'29164',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(3,'6 Hollow Ridge Hill','Augusta',3,'72094',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(4,'1 Mccormick Center','Saint Louis',4,'35241',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(5,'8527 Green Place','Omaha',5,'42460',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(6,'6 Ohio Point','Temple',2,'59587',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(7,'92 Scoville Hill','New York City',1,'92224',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(8,'673 Armistice Drive','Pensavola',6,'54725',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(9,'865 Killdeer Circle','Phoenix',7,'25793',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(10,'0613 Stephen Court','Dallas',2,'18865',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(11,'10 Westport Street','Tulsa',8,'26856',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(12,'3 Del Mar Alley','Portland',9,'17688',1);

#Store addresses
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(13,'528 Dottie Crossing','Troy',10,'12774',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(14,'8 Wayridge Alley','Saint Paul',11,'74597',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(15,'Blaine Center','Cincinnati',12,'13859',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(16,'7062 Swallow Road','Evansville',13,'89041',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values 
	(17,'Fremont Hill','Oklahoma City',8,'47770',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values
	(18,'01 Golf View Lane','Raleigh',14,'31984',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values
	(19,'3 Kim Court','El Paso',2,'84429',1);
insert into Address (addressId,street,city,stateId,zipCode,countryId) values
	(20,'8923 David Center','Huntington',15,'16888',1);

#person insertion
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (1,'rgre8z','P','Ellard','Trever',1);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (2,'pdup6v','E','Titcomb','Sallyanne',2);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (3,'7tir51','P','McLorinan','Jud',3);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (4,'raa8nk','P','Pagram','Katlin',4);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (5,'rt9vii','P','Blanpein','Finley',5);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (6,'t8857x','E','Smaile','Klement',6);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (7,'c6mhfs','C','Elies','Hugibert',7);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (8,'lcjeu4','E','Atwell','Yorke',8);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (9,'503jw7','P','Pigott','Ravi',9);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (10,'tqyt85','G','Fitzpayn','Traci',10);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (11,'mjq0rt','C','Goodee','Dolli',11);
insert into Person (personId,personCode,type,lastName,firstName,addressId) values (12,'82afor','G','Pavy','Leshia',12);

#All emails
insert into Email (emailId,email) values (1, 'tellard0@sphinn.com');
insert into Email (emailId,email) values (2, 'tellard0@go.com');
insert into Email (emailId,email) values (3, 'stitcomb5@howstuffowrks.com');
insert into Email (emailId,email) values (4, 'stitcomb5@4shared.com');
insert into Email (emailId,email) values (5, 'jmclorinan2@cragslist.org');
insert into Email (emailId,email) values (6, 'jmclorinan2@rediff.com');
insert into Email (emailId,email) values (7, 'jmclorinan2@hexun.com');
insert into Email (emailId,email) values (8, 'jmclorinan2@ed.gov');
insert into Email (emailId,email) values (9, 'kpagram0@aboutads.info');
insert into Email (emailId,email) values (10, 'kpagram0@pinterest.com');
insert into Email (emailId,email) values (11, 'fblanpein3@topsy.com');
insert into Email (emailId,email) values (12, 'fblanpein3@furl.net');
insert into Email (emailId,email) values (13, 'fblanpein3@addthis.com');
insert into Email (emailId,email) values (14, 'fblanpein3@creativecommons.org');
insert into Email (emailId,email) values (15, 'ksmaile6@ow.ly');
insert into Email (emailId,email) values (16, 'ksmaile6@sciencedirect.com');
insert into Email (emailId,email) values (17, 'ksmaile6@squidoo.com');
insert into Email (emailId,email) values (18, 'helies1@amazon.de');
insert into Email (emailId,email) values (19, 'helies1@telegraph.co.uk');
insert into Email (emailId,email) values (20, 'helies1@skype.com');
insert into Email (emailId,email) values (21, 'yatwell1@yahoo.co.jp');
insert into Email (emailId,email) values (22, 'rpigott4@fastcompany.com');
insert into Email (emailId,email) values (23, 'rpigott4@umn.edu');
insert into Email (emailId,email) values (24, 'rpigott4@marketwatch.com');
insert into Email (emailId,email) values (25, 'tfitzpayn2@mediafire.com');
insert into Email (emailId,email) values (26, 'tfitzpayn2@odnoklassniki.ru');
insert into Email (emailId,email) values (27, 'tfitzpayn2@linkedin.com');
insert into Email (emailId,email) values (28, 'dgoodee7@unesco.org');
insert into Email (emailId,email) values (29, 'dgoodee7@gmpg.org');
insert into Email (emailId,email) values (30, 'dgoodee7@usatoday.com');
insert into Email (emailId,email) values (31, 'dgoodee7@sitemeter.com');
insert into Email (emailId,email) values (32, 'lpavy3@mayoclinic.com');
insert into Email (emailId,email) values (33, 'lpavy3@merriam-webster.com');
insert into Email (emailId,email) values (34, 'lpavy3@gizmodo.com');

#PersonEmail insertion
insert into PersonEmail (personEmailId,emailId,personId) values (1,1,1);
insert into PersonEmail (personEmailId,emailId,personId) values (2,2,1);
insert into PersonEmail (personEmailId,emailId,personId) values (3,3,2);
insert into PersonEmail (personEmailId,emailId,personId) values (4,4,2);
insert into PersonEmail (personEmailId,emailId,personId) values (5,5,3);
insert into PersonEmail (personEmailId,emailId,personId) values (6,6,3);
insert into PersonEmail (personEmailId,emailId,personId) values (7,7,3);
insert into PersonEmail (personEmailId,emailId,personId) values (8,8,3);
insert into PersonEmail (personEmailId,emailId,personId) values (9,9,4);
insert into PersonEmail (personEmailId,emailId,personId) values (10,10,4);
insert into PersonEmail (personEmailId,emailId,personId) values (11,11,5);
insert into PersonEmail (personEmailId,emailId,personId) values (12,12,5);
insert into PersonEmail (personEmailId,emailId,personId) values (13,13,5);
insert into PersonEmail (personEmailId,emailId,personId) values (14,14,5);
insert into PersonEmail (personEmailId,emailId,personId) values (15,15,6);
insert into PersonEmail (personEmailId,emailId,personId) values (16,16,6);
insert into PersonEmail (personEmailId,emailId,personId) values (17,17,6);
insert into PersonEmail (personEmailId,emailId,personId) values (18,18,7);
insert into PersonEmail (personEmailId,emailId,personId) values (19,19,7);
insert into PersonEmail (personEmailId,emailId,personId) values (20,20,7);
insert into PersonEmail (personEmailId,emailId,personId) values (21,21,8);
insert into PersonEmail (personEmailId,emailId,personId) values (22,22,9);
insert into PersonEmail (personEmailId,emailId,personId) values (23,23,9);
insert into PersonEmail (personEmailId,emailId,personId) values (24,24,9);
insert into PersonEmail (personEmailId,emailId,personId) values (25,25,10);
insert into PersonEmail (personEmailId,emailId,personId) values (26,26,10);
insert into PersonEmail (personEmailId,emailId,personId) values (27,27,10);
insert into PersonEmail (personEmailId,emailId,personId) values (28,28,11);
insert into PersonEmail (personEmailId,emailId,personId) values (29,29,11);
insert into PersonEmail (personEmailId,emailId,personId) values (30,30,11);
insert into PersonEmail (personEmailId,emailId,personId) values (31,31,11);
insert into PersonEmail (personEmailId,emailId,personId) values (32,32,12);
insert into PersonEmail (personEmailId,emailId,personId) values (33,33,12);
insert into PersonEmail (personEmailId,emailId,personId) values (34,34,12);

#item insertion
insert into Item (itemId,itemCode,type,name,baseprice) values (1,'7rznw0','PN','Xbox One','116.08');
insert into Item (itemId,itemCode,type,name,baseprice) values (2,'ermnfb','PN','Fortnite','42.08');
insert into Item (itemId,itemCode,type,name,baseprice) values (3,'ije92s','SV','Console Installation','102.68');
insert into Item (itemId,itemCode,type,name) values (4,'nxyhbw','PG','Call of Duty');
insert into Item (itemId,itemCode,type,name,baseprice) values (5,'rgx0gk','PU','Playstation 4','208.51');
insert into Item (itemId,itemCode,type,name) values (6,'wxrd98','PG','Apple Store');
insert into Item (itemId,itemCode,type,name,baseprice) values (7,'4wp5en','PU','Nintendo DS','58.07');
insert into Item (itemId,itemCode,type,name,baseprice) values (8,'n5pk7d','SV','Console Repair','43.91');
insert into Item (itemId,itemCode,type,name,baseprice) values (9,'belcej','SB','Xbox Live','15.67');
insert into Item (itemId,itemCode,type,name,baseprice) values (10,'qby66m','SB','Netflix','11.81');
insert into Item (itemId,itemCode,type,name,baseprice) values (11,'zhubfe','SV','Raging Gamer Consuling','8.89');
insert into Item (itemId,itemCode,type,name) values (12,'he09x9','PG','Assasians Creed');

#Store insertion
insert into Store (storeId,storeCode,managerId,addressId) values (1,'ltko4q',2,13);
insert into Store (storeId,storeCode,managerId,addressId) values (2,'xe2nly',8,14);
insert into Store (storeId,storeCode,managerId,addressId) values (3,'mf0jam',2,15);
insert into Store (storeId,storeCode,managerId,addressId) values (4,'s31b2u',2,16);
insert into Store (storeId,storeCode,managerId,addressId) values (5,'zbm90b',2,17);
insert into Store (storeId,storeCode,managerId,addressId) values (6,'1zey1q',2,18);
insert into Store (storeId,storeCode,managerId,addressId) values (7,'y9r9xz',8,19);
insert into Store (storeId,storeCode,managerId,addressId) values (8,'1w12g8',8,20);

#sales insertion
insert into Sale (saleId,saleCode,storeId,customerId,salepersonId) values (1,'SalE23',7,1,8);
insert into Sale (saleId,saleCode,storeId,customerId,salepersonId) values (2,'saLE43',6,7,2);
insert into Sale (saleId,saleCode,storeId,customerId,salepersonId) values (3,'je789s',3,10,6);
insert into Sale (saleId,saleCode,storeId,customerId,salepersonId) values (4,'l900sf',8,11,8);
insert into Sale (saleId,saleCode,storeId,customerId,salepersonId) values (5,'394dj3',3,3,6);

#Sale 1
insert into SaleItem (saleItemId,saleId,itemId,employeeId,numberOfHours) values (2,1,11,6,3.0);
insert into SaleItem (saleItemId,saleId,itemId,productQuantity) values (1,1,1,1);

#Sale 2
insert into SaleItem (saleItemId,saleId,itemId,productQuantity) values (4,2,2,2);
insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (6,2,4,25.25);
insert into SaleItem (saleItemId,saleId,itemId,productQuantity) values (5,2,5,1);

#Sale 3
insert into SaleItem (saleItemId,saleId,itemId,beginDate,endDate) values (8,3,10,'2017-01-22','2018-01-24');

#Sale 4
insert into SaleItem (saleItemId,saleId,itemId,productQuantity) values (11,4,7,1);
insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (12,4,12,20.00);

#Sale 5
insert into SaleItem (saleItemId,saleId,itemId,employeeId,numberOfHours) values (14,5,8,2,2);
insert into SaleItem (saleItemId,saleId,itemId,beginDate,endDate) values (13,5,9,'2012-03-23','2018-08-21');

#Test Data
#Sale 6 test
#insert into Sale (saleId,saleCode,storeId,customerId,employeeId) values (6,'SaLe006',1,2,2);
#insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (15,6,4,200.33);

#Sale 7 test
#insert into Sale (saleId,saleCode,storeId,customerId,employeeId) values (7,'SaLe007',1,6,6);
#insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (16,7,4,252.22);

#Sale 8 test
#insert into Sale (saleId,saleCode,storeId,customerId,employeeId) values (8,'SaLe008',1,2,2);
#insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (17,8,4,87.34);

#Sale 9 test
#insert into Sale (saleId,saleCode,storeId,customerId,employeeId) values (9,'SaLe009',1,8,8);
#insert into SaleItem (saleItemId,saleId,itemId,giftCardPrice) values (18,9,4,249.87);



