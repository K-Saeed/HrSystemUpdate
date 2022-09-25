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
    private Integer grossSallary;
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

    public Employee() {
    }

    public Employee(String fname, String lname, Integer nationalid, Integer grossSallary,
                    Team team, Department department, Employee managerId,
                    List<EmployeeExperties> employeeExperties, LocalDate birthDate, LocalDate graduationDate, LocalDate startWorkDate) {
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

    public void setGrossSallary(Integer grossSallary) {
        this.grossSallary = grossSallary;
    }

    public Integer getGrossSallary() {
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
}
