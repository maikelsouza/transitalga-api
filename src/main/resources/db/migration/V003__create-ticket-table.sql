create table ticket  (

	id bigint not null auto_increment,
	vehicle_id bigint not null,
	description text not null,
	fine_amount decimal(10,2) not null,
	occurrence_date datetime not null,


	primary key (id)
);

alter table ticket add constraint fk_ticket_vehicle foreign key(vehicle_id) references vehicle (id);
