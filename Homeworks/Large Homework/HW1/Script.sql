create database if not exists hse_bank;

use hse_bank;

drop table if exists BankAccounts;
drop table if exists Categories;
drop table if exists Operations;


create table if not exists BankAccounts (
	id int,
	name text,
	balance float
);

create table if not exists Categories (
	id int,
	name text,
	type enum('income', 'expense')
);

create table if not exists Operations (
	id int,
	type enum('income', 'expense'),
	bank_account_id int,
	category_id int,
	amount float,
	date datetime,
	description text
);


insert into BankAccounts(id, name, balance) values 
(1, "Main Account", 1000),
(2, "Sicret Account", 100000),
(3, "Account With Float", 123.456);

insert into Categories(id, name, type) values
(1, 'Coffee', 'expense'),
(2, 'Grant', 'income'),
(3, 'Transaction_from', 'expense'),
(4, 'Transaction_to', 'income');


insert into Operations(id, type, bank_account_id, category_id, amount, date, description) values
(1, 'expense', 1, 1, 330, '2025-03-17 09:10:36', 'ice cherry bumble coffee'),
(2, 'expense', 1, 1, 330, '2025-03-18 10:40:10', 'hot orange bumble coffee'),
(3, 'expense', 1, 1, 330, '2025-03-20 11:32:16', 'hot cherry bumble coffee'),
(4, 'income', 1, 4, 10000, '2025-03-21 07:10:36', 'some money for coffee'),
(5, 'income', 1, 2, 2100, '2025-03-17 07:10:36', 'little present from university'),
(6, 'expense', 1, 1, 330, '2025-03-21 09:12:18', 'hot cherry bumble coffee');




