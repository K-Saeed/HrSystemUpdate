package com.hrsystem.hrsystem.entity.dto;

import com.hrsystem.hrsystem.entity.EmployeeExperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDto {
    private String fname ;
    private String lname ;
    private Integer grossSallary ;
    private Integer departmentId;
    private Integer nationalId;
    private Integer teamId;
    private Integer managerId;
    private List <EmployeeExperties> employeeExperties;
    private LocalDate birthDate ;
    private LocalDate graduationDate ;
    private LocalDate startWorkDate ;

    public EmployeeDto(String fname, String lname, int grossSallary, Integer departmentId, Integer nationalId,
                       Integer teamId, Integer managerId, List <EmployeeExperties> employeeExperties,
                       LocalDate birthDate, LocalDate graduationDate, LocalDate startWorkDate) {
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
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setGrossSallary(Integer grossSallary) {
        this.grossSallary = grossSallary;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setTeamId(Integer teamId) {this.teamId = teamId;}
    public void setManagerId(Integer managerId) {this.managerId = managerId;}
    public List<EmployeeExperties> getEmployeeExperties() {
        return employeeExperties;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public Integer getGrossSallary() {
        return grossSallary;
    }

    public Integer getTeamId() {return teamId;}
    public Integer getManagerId() {return managerId;}

    public Integer getNationalId() {
        return nationalId;
    }

    public void setNationalId(Integer nationalId) {
        this.nationalId = nationalId;
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
