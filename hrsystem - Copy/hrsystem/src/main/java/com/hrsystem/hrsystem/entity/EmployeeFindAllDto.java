package com.hrsystem.hrsystem.entity;

import lombok.Data;

@Data
public class EmployeeFindAllDto {
    private Integer employeeid;
    private String fname;
    private Integer teamid;
    private Integer managerid;

    public EmployeeFindAllDto() {
    }

    public EmployeeFindAllDto(Integer employeeid, String fname, Integer teamid, Integer managerid) {
        this.employeeid = employeeid;
        this.fname = fname;
        this.teamid = teamid;
        this.managerid = managerid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setManagerid(Integer managerid) {
        this.managerid = managerid;
    }

    public Integer getManagerid() {
        return managerid;
    }
}
