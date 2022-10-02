package com.hrsystem.hrsystem.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LeavesHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer leavesTotal;
    private LocalDate lDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public LeavesHistory() {
    }

    public LeavesHistory(Integer leavesTotal, LocalDate lDate, Employee employee) {
        this.leavesTotal = leavesTotal;
        this.lDate = lDate;
        this.employee = employee;
    }

    public Integer getLeavesTotal() {
        return leavesTotal;
    }

    public void setLeavesTotal(Integer leavesTotal) {
        this.leavesTotal = leavesTotal;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getlDate() {
        return lDate;
    }

    public void setlDate(LocalDate lDate) {
        this.lDate = lDate;
    }
}
