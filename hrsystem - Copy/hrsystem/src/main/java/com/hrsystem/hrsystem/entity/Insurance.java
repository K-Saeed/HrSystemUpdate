package com.hrsystem.hrsystem.entity;

import javax.persistence.*;

@Entity
public class Insurance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer leavesYear;
    private Integer insuranceYears ;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    public Insurance() {
    }

    public Insurance( Integer leavesYear, Integer insuranceYears) {
        this.leavesYear = leavesYear;
        this.insuranceYears = insuranceYears;
    }

    public Integer getLeavesYear() {
        return leavesYear;
    }

    public void setLeavesYear(Integer leavesYear) {
        this.leavesYear = leavesYear;
    }

    public Integer getInsuranceYears() {
        return insuranceYears;
    }

    public void setInsuranceYears(Integer insuranceYears) {
        this.insuranceYears = insuranceYears;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
