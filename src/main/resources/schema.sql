create table users(
	username varchar(128) not null primary key,
	password varchar(512) not null,
	enabled boolean not null
);

create table authorities(
	username varchar(128) not null,
	authority varchar(128) not null 
);

create unique index idx_auth_username on authorities(username,authority);


create table public.t_zookeeper(
	id bigint not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null
);

create table public.t_vet(
	id bigint not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null
);

create table public.t_animal(
	id bigint not null,
	name varchar(255),
	birth_date date,
	zookeeper_id bigint
);
--  
alter table public.t_zookeeper add constraint public.constraint_1 primary key(id);
alter table public.t_animal add constraint public.constraint_2 primary key(id);
alter table public.t_animal add constraint public.constraint_3 foreign key(zookeeper_id) references public.t_zookeeper(id);
alter table public.t_vet add constraint public.constraint_11 primary key(id);
create sequence public.zoo_sequence start with 100;

/*SQL CONSTRAİNT bir tablodaki veriler için kurallar belirler. Kısıtlamalar, bir tabloya girilecek veri türü gibi değerleri sınırlamak için kullanılabilir. Bu tablodaki verilerin doğruluğunu sağlar.
Kısıtlamalar sütuna yâda tablonun tamamına uygulana bilir.

NOT NULL - Bir sütunun değere sahip olmamasını sağlar.
UNİQUE -bir sütundaki tüm değerlerin farklı olmasını sağlar.
PRIMARY KEY -bir tablodaki her satırı benzersiz şekilde tanımlar. NOT NULL VE UNİQUE ‘ in birleşimidir.
FOREIGN KEY -Başka bir tablodaki bir satırı / kaydı benzersiz şekilde tanımlar.
CHECK - Bir sütundaki tüm verilerin belirli bir koşulu karşılamasını sağlar.
DEFAULT -Değer belirtilmediğinde bir sütun için varsayılan değer atar.
INDEX -veritabanından çok hızlı bir şekilde veri almak için kullanılır.*/