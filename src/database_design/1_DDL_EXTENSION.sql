drop database training_center;
create database training_center;
use training_center;

create table account(
	account_id		int			auto_increment,
    role			varchar(20),
    username		varchar(200)	unique not null,
    password		varchar(200),
    
    primary key (account_id)
);

create table manager(
	manager_id 		int 		auto_increment,
    account_id		int,
    first_name 		varchar(50),
    middle_name 	varchar(50),
    last_name 		varchar(50),
    email 			varchar(100),
    phone 			varchar(20),
    
    primary key (manager_id),
    foreign key (account_id) references account(account_id)
		on delete cascade
        on update cascade
);

create table student(
	student_id 		int 		auto_increment,
    account_id		int,
    first_name 		varchar(50),
    middle_name 	varchar(50),
    last_name 		varchar(50),
    email 			varchar(100),
    phone 			varchar(20),
    isBlocked		tinyint(1) not null default 0,
    
    primary key (student_id),
    foreign key (account_id) references account(account_id)
		on delete cascade
        on update cascade
);

create table instructor(
	instructor_id 	int 		auto_increment,
    account_id		int,
    first_name 		varchar(50),
    middle_name 	varchar(50),
    last_name 		varchar(50),
    level			int  	    check (level > 0),
    email 			varchar(100),
    phone 			varchar(20),
    isBlocked		tinyint(1) not null default 0,
    
    primary key (instructor_id),
    foreign key (account_id) references account(account_id)
		on delete cascade
        on update cascade
);

create table course(
	course_id 		varchar(10),
    name 			varchar(100),
    description 	varchar(1000),
    rate			double		check(rate > 0),
    capacity 		int			check(capacity > 0),	
    
    primary key (course_id)
);

create table session(
	session_id 		int			auto_increment,
    course_id		varchar(10),
    title 			varchar(100),
    content			varchar(1000),
    
    primary key (session_id),
    foreign key (course_id) references course(course_id)
			on delete cascade
            on update cascade
);

create table assignment(
	assignment_id	int 		auto_increment,
    session_id		int,
    description		varchar(1000),
    
    primary key (assignment_id),
    foreign key (session_id) references session(session_id)
			on delete cascade
            on update cascade
);

create table teaches(
	instructor_id 	int,
    course_id		varchar(10),
    
    primary key (instructor_id, course_id),
    foreign key (instructor_id) references instructor(instructor_id)
			on delete cascade
            on update cascade,
	foreign key (course_id) references course(course_id)
			on delete cascade
            on update cascade
);

create table ins_attend(
	session_id		int,
    instructor_id	int,
    check_attend	boolean,
    
    primary key (session_id, instructor_id),
    foreign key (session_id) references session(session_id)
			on delete cascade
            on update cascade,
	foreign key (instructor_id) references instructor(instructor_id)
			on delete cascade
            on update cascade		
);

create table registers(
	course_id		varchar(10),
    student_id		int,
    
    primary key (course_id, student_id),
    foreign key (course_id) references course(course_id)
			on delete cascade
            on update cascade,
	foreign key (student_id) references student(student_id)
			on delete cascade
            on update cascade		
);

create table stu_attend(
	session_id		int,
    student_id		int,
    check_attend	boolean,
    
    primary key (session_id, student_id),
    foreign key (session_id) references session(session_id)
			on delete cascade
            on update cascade,
	foreign key (student_id) references student(student_id)
			on delete cascade
            on update cascade			
);

create table submits(
	assignment_id	int,
	student_id		int,
    solution		varchar(1000),
    score			double		check(score >= 0.00 and score <= 10.00),
    feedback		varchar(1000),
    
    primary key (assignment_id, student_id),
    foreign key (assignment_id) references assignment(assignment_id)
			on delete cascade
            on update cascade,
	foreign key (student_id) references student(student_id)
			on delete cascade
            on update cascade
);
