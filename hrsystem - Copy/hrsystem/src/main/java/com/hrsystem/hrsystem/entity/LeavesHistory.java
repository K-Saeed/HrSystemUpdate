package com.hrsystem.hrsystem.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LeavesHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer leaves;
    private LocalDate lDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public LeavesHistory() {
    }

    public LeavesHistory(Integer leaves, LocalDate lDate, Employee employee) {
        this.leaves = leaves;
        this.lDate = lDate;
        this.employee = employee;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
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
