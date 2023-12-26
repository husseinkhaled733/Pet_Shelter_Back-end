DELIMITER $$
create trigger setShelterIDForManager
after insert on shelter
for each row
begin
	update staff set staff.shelterID = new.shelterID
    where staff.staffID = new.managerID;
end $$
DELIMITER ;

-- drop trigger setShelterIDForManager;

-- insert into staff (name, role, phone, email, password) values("mgr", "manager", "32904832094", "mgr0@shelter.com", "000000000");
-- select * from staff;

-- delete from shelter where managerID = 1;

-- insert into shelter (name, country, city, phone, email, detailedAddress, managerID) values("newShelter", "Egypt", "Alexandria", "23234", "newShelter@shelter.com", "kkk", 1);
select * from shelter;