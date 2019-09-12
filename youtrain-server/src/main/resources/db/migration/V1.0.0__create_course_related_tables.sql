CREATE TABLE course (
    id INT primary key auto_increment,
    title VARCHAR(256) not null,
    description VARCHAR(2048) not null,
    teacher VARCHAR(128) not null,
    price DECIMAL(6,2) not null
);

CREATE TABLE course_date (
    id INT primary key auto_increment,
    course_id INT not null,
    `begin` DATETIME not null,
    `end` DATETIME not null,
    amount INT not null,
    CONSTRAINT `fk_course_date_course`
		FOREIGN KEY (course_id) REFERENCES course(id)
		ON DELETE CASCADE
);
