package com.hrsystem.hrsystem.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Salary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate sDate;
    private Integer exceededLeaves;
    private Double taxes ;
    private Double insurance;
    private Double raises;
    private Double bonus ;
    private Double netSalary;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee ;

    public Salary( LocalDate sDate, Integer exceededLeaves, Double taxes,
                  Double insurance, Double raises, Double bonus, Double netSalary, Employee employee) {
        this.sDate = sDate;
        this.exceededLeaves = exceededLeaves;
        this.taxes = taxes;
        this.insurance = insurance;
        this.raises = raises;
        this.bonus = bonus;
        this.netSalary = netSalary;
        this.employee = employee;
    }

    public LocalDate getsDate() {
        return sDate;
    }

    public void setsDate(LocalDate sDate) {
        this.sDate = sDate;
    }

    public Integer getExceededLeaves() {
        return exceededLeaves;
    }

    public void setExceededLeaves(Integer exceededLeaves) {
        this.exceededLeaves = exceededLeaves;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public Double getInsurance() {
        return insurance;
    }

    public void setInsurance(Double insurance) {
        this.insurance = insurance;
    }

    public Double getRaises() {
        return raises;
    }

    public void setRaises(Double raises) {
        this.raises = raises;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
