package com.hrsystem.hrsystem.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(name = "id")
    private Integer id;
    private String tname;

    @OneToMany(mappedBy = "team"
            , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    public Team() {
    }

    public Team(Integer id, String tname) {
        this.id = id;
        this.tname = tname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
