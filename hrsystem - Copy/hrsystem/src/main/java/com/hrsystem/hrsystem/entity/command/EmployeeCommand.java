package com.hrsystem.hrsystem.entity.command;

import com.hrsystem.hrsystem.entity.EmployeeExperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeCommand {
    private String fname ;
    private String lname ;
    private Integer grossSallary ;
    private Integer nationalId;
    private Integer departmentId;
    private Integer teamId;
    private Integer managerId;
    private List<EmployeeExperties> employeeExperties;
    private LocalDate birthDate ;
    private LocalDate graduationDate ;
    private LocalDate startWorkDate ;
    private Integer leaves ;
   // private Gender gender;
   // private Degree degree ;

    public EmployeeCommand(String fname, String lname, int grossSallary, Integer departmentId, Integer nationalId,
                           Integer teamId, Integer managerId, List <EmployeeExperties> employeeExperties,
                           LocalDate birthDate, LocalDate graduationDate, LocalDate startWorkDate , Integer leaves  ) {
        this.fname=fname;
        this.lname=lname;
        this.grossSallary=grossSallary;
        this.nationalId = nationalId;
        this.departmentId = departmentId;
        this.teamId = teamId;
        this.managerId = managerId;
        this.employeeExperties = employeeExperties;
        this.birthDate = birthDate;
        this.graduationDate = graduationDate;
        this.startWorkDate = startWorkDate;
       // this.gender = gender;
        //this.degree = degree;
        this.leaves = leaves;
    }

//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }

//    public Degree getDegree() {
//        return degree;
//    }
//
//    public void setDegree(Degree degree) {
//        this.degree = degree;
//    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
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

    public void setGrossSallary(Integer grossSallary) {
        this.grossSallary = grossSallary;
    }

    public Integer getGrossSallary() {
        return grossSallary;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getManagerId() {
        return managerId;
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
