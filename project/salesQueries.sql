#File to test our database, salesDB
#Authors: jbargen and nzetocha

#1. A query to retrieve the main attributes of each person (their code, and last/first name).
select personId, personCode, lastName, firstName from Person;

#2. A query to retrieve the major fields for every person including their address (excluding emails).
select personId, personCode, lastName, firstName, a.street as street, a.city as city,
		s.name as state, a.zipCode, c.name as country
			from Person p
	left join Address a on p.addressId = a.addressId
    join State s on a.stateId = s.stateId
    join Country c on a.countryId = c.countryId;

#3. A query to get the email addresses of a specific person.
select e.emailId as emailId, email from Email e 
	join PersonEmail pe on e.emailId = pe.emailId
    join Person p on pe.personId = p.personId
    where p.firstName = 'Jud';

#4. A query to change the email address of a specific email record.
select emailId, email from Email where emailId = 10;
update Email set email = 'kpagram0@unl.edu' where emailId = 10;
select emailId, email from Email where emailId = 10;

#5. A query (or series of queries) to remove a specific person record.
delete from PersonEmail where personId = 11;
delete from SaleItem where saleId = (select saleId from Sale where customerId = 11);
delete from Sale where customerId = 11;
delete from Person where personId = 11;

#6. A query to get all the items on a specific sales record.
select s.saleId, i.itemId, i.itemCode, i.type, i.name, i.basePrice, si.productQuantity,
		si.giftCardPrice, si.numberOfHours, si.beginDate, si.endDate
			from Item i 
	join SaleItem si on i.itemId = si.itemId
    join Sale s on si.saleId = s.saleId
    where s.saleId = 2;

#7. A query to get all the items purchased by a specific person.
select s.saleId, i.itemId, itemCode, i.type, i.name, i.basePrice, si.employeeId, si.numberOfHours, si.beginDate, si.endDate,
		p.personCode, p.lastName, p.firstName
			from Item i
	join SaleItem si on i.itemId = si.itemId
    join Sale s on si.saleId = s.saleId
    join Person p on s.customerId = p.personId
    where personId = 3;

#8. A query to find the total number of sales made at each store.
select st.storeId as storeId, count(saleId) as salesMade from Sale s
	right join Store st on s.storeId = st.storeId
    group by st.storeId;

#9. A query to find the total number of sales made by each employee.
select p.personId, p.lastName as lastName, p.firstName as firstName, count(saleId) as numSales
		from Sale s
	right join Person p on s.employeeId = p.personId
    where p.type = 'E'
    group by p.personId;

#10. A query to find the total charge of all services in each sale 
#	 (hint: you can take an aggregate of a mathematical expression).
select s.saleId, round(sum(i.basePrice * si.numberOfHours), 2) as newProductCost from Sale s
	join SaleItem si on s.saleId = si.saleId
    join Item i on si.itemId = i.itemId
	#where i.type = 'PN'
    group by saleId;

#11. A query to detect invalid data in sales as follows. In a single sale, a particular product should
# 	 only appear once (since any number of units can be consolidated to a single record).
# 	 Write a query to find any sale that includes multiple instances of the same product.
select s.saleId, i.itemId, i.name, count(i.itemId) as numOccurences from Sale s
	join SaleItem si on s.saleId = si.saleId
    join Item i on si.itemId = i.itemId
    group by s.saleId
    having count(i.itemId) > count(si.saleItemId);   

#12. Write a query to detect a potential instance of fraud where an employee makes a sale to
#	 themselves (the same person is the sales person as well as the customer).
select p.personId, p.lastName, p.firstName, s.saleId from Sale s
	join Person p on s.employeeId = p.personId
    where s.employeeId = s.customerId;

#13. Write a query to detect possible fraud where an employee is using their employee discount to make
#	 a lot of gift card purchases. This would include only sales made by an employee to themselves and
#	 an amount of over $250 or more (totaled over all sales, not just one so they can't "hide" the
#	 fraud by making many small purchases).
select p.personId, p.lastName, p.firstName, count(s.saleId) as fraudSales, round(sum(si.giftCardPrice),2) as amount
	from Sale s
	join Person p on s.employeeId = p.personId
	join SaleItem si on s.saleId = si.saleId
	join Item i on si.itemId = i.itemId
	where s.employeeId = s.customerId and i.type = 'PG'
	group by p.personId
	having round(sum(si.giftCardPrice),2) > 250.0;

#Store
select s.saleCode, st.storeCode, st.addressId from Sale s
    join Store st on s.storeId = st.storeId
    join Address address on st.addressId = address.addressId
    join State state on address.stateId = state.stateId
    join Country c on address.countryId = c.countryId
    group by s.saleId;

#Person
#select s.saleCode, st.storeCode, st.addressId from Sale s
 #   join Person emp on s.employeeId = emp.employeeId
 #   join Address address on st.addressId = address.addressId
 #   join State state on address.stateId = state.stateId
 #   join Country c on address.countryId = c.countryId
  #  join Person cust on s.customerId = cust.customerId
   # join Address address on st.addressId = address.addressId
    #join State state on address.stateId = state.stateId
    #join Country c on address.countryId = c.countryId
    
    select itemCode, type, name, basePrice from Item;
    select name from State where stateId = 1;
    select name from Country where countryId = 1;
    select street, city, stateId, zipCode, countryId from Address where addressId = 1;
    SELECT saleId, saleCode, customerId, employeeId FROM Sale;
    
    SELECT it.itemCode, it.type, it.name, it.basePrice, si.productQuantity,
		si.numberOfHours, si.employeeId, si.beginDate, si.endDate, si.giftCardPrice
			from Item it
		JOIN SaleItem si on it.itemId = si.itemId
		JOIN Sale s on si.saleId = s.saleId
		WHERE s.saleId = 1;
	
    delete from SaleItem where saleId = (select saleId from Sale where saleCode = 'SalE23');
	delete from Sale where saleCode = 'SalE23';
    drop table if exists SaleItem,Sale,Store,Item,PersonEmail,Email,Person,Address,State,Country;