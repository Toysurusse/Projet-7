create table if not exists usertable
(
	userid integer not null
		constraint usertable_pk
			primary key,
	pseudo varchar(200) not null,
	password varchar(300) not null,
	gender boolean not null,
	mail varchar(200) not null,
	role integer,
	salt varchar(250)
)
;

create table if not exists book
(
	bookid serial not null
		constraint book_pk
			primary key,
	bookname varchar(200) not null,
	author varchar(100) not null,
	editeurs varchar(100) not null,
	nbpage integer,
	dispo boolean default true not null
)
;

create table if not exists rentbook
(
	rentid integer not null,
	user_id integer not null
		constraint usertable_rentbook_fk
			references usertable,
	book_id integer not null
		constraint book_rentbook_fk
			references book,
	reload boolean not null,
	returnbook boolean not null,
	create_at date,
	end_at date,
	constraint rentbook_pk
		primary key (rentid, user_id, book_id)
)
;