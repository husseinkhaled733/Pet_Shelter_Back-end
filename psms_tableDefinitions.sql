use psms;

select * from shelter;

create table user(
	userID int primary key not null auto_increment,
	name varchar(20),
    phone varchar(20),
    email varchar(30),
    password varchar(30)
);

alter table user
add constraint uniqueEmailConstraint
unique(email);

alter table user
add constraint uniquePhoneConstraint
unique(phone);

create table shelter(
	shelterID int primary key not null auto_increment,
    name varchar(20),
    country varchar(20),
    city varchar(20),
    phone varchar(20),
    email varchar(30),
    detailedAddress varchar(50),
    managerID int
);

alter table shelter
add constraint uniqueEmailConstraint
unique(email);

alter table shelter
add constraint uniquePhoneConstraint
unique(phone);

create table staff(
	staffID int primary key not null auto_increment,
    name varchar(20),
    role varchar(20),
    phone varchar(20),
    email varchar(30),
    password varchar(30),
    shelterID int
);

alter table shelter
add constraint shelterManagerConstraint
foreign key(managerID) references staff(staffID);

alter table staff
add constraint shelterOfStaffConstraint
foreign key(shelterID) references shelter(shelterID);

alter table staff
add constraint uniqueEmailConstraint
unique(email);

alter table staff
add constraint uniquePhoneConstraint
unique(phone);

create table pet(
	petID int primary key not null auto_increment,
    name varchar(20),
    dateOfBirth date,
    gender boolean,
    healthStatus varchar(30),
    species varchar(20),
    breed varchar(20),
    behavior varchar(50),
    description varchar(1000),
    shelterID int
);

alter table pet
add constraint shelterOfPetConstraint
foreign key(shelterID) references shelter(shelterID);

create table adoptionRecord(
	petID int primary key not null auto_increment,
    userID int,
    dateofAdoption date
);

alter table adoptionRecord
add constraint userAdoptedPetConstraint
foreign key(userID) references user(userID);

create table application(
	userID int,
    petID int,
    status varchar(20)
);

alter table application
add constraint userInApplicationConstraint
foreign key(userID) references user(userID);

alter table application
add constraint petInApplicationConstraint
foreign key(petID) references pet(petID);

alter table application
add constraint applicationPrimaryKey
primary key(userID, petID);

create table petImages(
	image varchar(100) primary key not null,
    petID int
);

alter table petImages
add constraint petOwnerOfImageConstraint
foreign key(petID) references pet(petID);

create table petDoc(
	document varchar(100) primary key not null,
    petID int
);

alter table petDoc
add constraint petOwnerOfDocumentConstraint
foreign key(petID) references pet(petID);



-- set foreign_key_checks = 0;
-- drop table shelter, staff;
-- set foreign_key_checks = 1;