create table salary(
id int not null auto_increment primary key ,
s_date date not null ,
exceeded_leaves int ,
taxes int ,
insurance int ,
raises int ,
bonus int ,
net_salary int ,
employee_id int not null ,
foreign key (employee_id) references employee (id)
);