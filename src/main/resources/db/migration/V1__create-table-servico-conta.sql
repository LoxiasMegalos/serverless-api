create table user_services_db(
	id bigint not null auto_increment,
    user_account varchar(100) not null,
    service_name varchar(100) not null,
    primary key(id)
);