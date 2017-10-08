create table user(
	
    pesel		decimal(11) not null,
    name		varchar(20) not null,
    second_name	varchar(30) not null,
    
    primary key(pesel)
);

create table exam(

	id 			int not null auto_increment,
    points		decimal(3) not null,
    date		date not null,
    user_pesel	decimal(11) not null,

	primary key(id),
	constraint fk_exam_user foreign key (user_pesel) references user(pesel)
);

drop table exam;
drop table user;
