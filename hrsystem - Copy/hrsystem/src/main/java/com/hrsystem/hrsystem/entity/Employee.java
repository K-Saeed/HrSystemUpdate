package com.hrsystem.hrsystem.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fname;
    private String lname;
    private Integer nationalid;
    private LocalDate birthDate;
    private LocalDate graduationDate;
    private LocalDate startWorkDate;
    private Double grossSallary;
    private Integer leaves;
    //private Gender gender;
  //  private Degree degree ;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
    @ManyToOne()
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Employee manager;
    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private List<EmployeeExperties> employeeExperties;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private List<Salary>salary;


    public Employee() {
    }

    public Employee(String fname, String lname, Integer nationalid, Double grossSallary,
                    Team team, Department department, Employee managerId, List<EmployeeExperties> employeeExperties,
                    LocalDate birthDate, LocalDate graduationDate, LocalDate startWorkDate, Integer leaves) {
        this.fname = fname;
        this.lname = lname;
        this.nationalid = nationalid;
        this.grossSallary = grossSallary;
        this.team = team;
        this.department = department;
        this.manager = managerId;
        this.employeeExperties = employeeExperties;
        this.birthDate = birthDate;
        this.graduationDate = graduationDate;
        this.startWorkDate = startWorkDate;
      //  this.gender = gender;
       // this.degree = degree;
        this.leaves = leaves;
    }

    public Integer getNationalid() {
        return nationalid;
    }

    public void setNationalid(Integer nationalid) {
        this.nationalid = nationalid;
    }

    public Integer getId() {
        return id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setGrossSallary(Double grossSallary) {
        this.grossSallary = grossSallary;
    }

    public Double getGrossSallary() {
        return grossSallary;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee getManager() {
        return manager;
    }

    public List<EmployeeExperties> getEmployeeExperties() {
        return employeeExperties;
    }

    public void setEmployeeExperties(List<EmployeeExperties> employeeExperties) {
        this.employeeExperties = employeeExperties;
    }

//    public Gender getGender() {
//        return gender;
//    }
//
//    public void se
//    tGender(Gender gender) {
//        this.gender = gender;
//    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public LocalDate getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(LocalDate startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public List<Salary> getSalary() {
        return salary;
    }

    public void setSalary(List<Salary> salary) {
        this.salary = salary;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }

//    public Degree getDegree() {
//        return degree;
//    }
//
//    public void setDegree(Degree degree) {
//        this.degree = degree;
//    }
}
