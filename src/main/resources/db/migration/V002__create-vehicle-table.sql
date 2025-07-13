create table vehicle (

	id bigint not null auto_increment,
	owner_id bigint not null,
	brand varchar(20) not null,
	model varchar(60) not null,
	plate varchar(7) not null,
	status varchar(20) not null,
	create_date datetime not null,
	seizure_date datetime,

	primary key (id)
);

alter table vehicle add constraint fk_vehicle_owner foreign key(owner_id) references owner(id);
alter table vehicle add constraint uk_plate unique (plate);
