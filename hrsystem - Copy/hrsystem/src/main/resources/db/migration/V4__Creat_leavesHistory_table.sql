create table leaves_history (
id int not null auto_increment primary key ,
l_date date not null ,
leaves int not null ,
employee_id int not null ,
foreign key (employee_id) references employee (id)
);