create table recursos_alocados_db(
	id bigint not null auto_increment,
    allocated_cores int not null,
    start_date timestamp,
    end_date timestamp,
    quantidade_mensagens int not null,
    service_account_id int not null references user_services_db(id),
    primary key(id)
);