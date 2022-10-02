create table department (
id int not null auto_increment unique primary key ,
dname varchar (250) not null
);
create table team (
id int not null auto_increment unique primary key ,
tname varchar (250) not null
);

create table employee (
id int not null auto_increment unique primary key ,
fName varchar(250) not null ,
lName varchar (250) not null ,
nationalId int not null unique,
birth_Date date not null ,
graduation_Date date not null ,
start_Work_Date date not null,
gross_Sallary int not null ,
department_Id int not null ,
team_Id int not null ,
manager_Id int ,
foreign key (department_Id) references department (id),
foreign key (team_Id) references team (id) ,
foreign key (manager_Id) references employee (id)
);
create table employee_experties (
id int not null auto_increment primary key ,
job varchar (250) not null ,
jobDescription varchar (250) not null ,
experties varchar (250) not null ,
employee_Id int not null ,
foreign key (employee_Id) references employee (id)
);
alter table employee
  add leaves int
  after gross_Sallary;

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

create table insurance (
id int not null auto_increment primary key ,
leaves_year int not null ,
insurance_years int not null,
employee_id int not null ,
foreign key (employee_id) references employee (id)
);
create table leaves_history (
id int not null auto_increment primary key ,
l_date date not null ,
leaves_total int not null ,
employee_id int not null ,
foreign key (employee_id) references employee (id)
);