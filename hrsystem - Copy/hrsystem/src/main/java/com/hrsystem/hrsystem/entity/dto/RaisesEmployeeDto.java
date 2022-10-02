package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RaisesEmployeeDto {
    private Double raises ;
    private Integer employeeId ;

    public RaisesEmployeeDto(Double raises, Integer employeeId) {
        this.raises = raises;
        this.employeeId = employeeId;
    }

    public Double getRaises() {
        return raises;
    }

    public void setRaises(Double raises) {
        this.raises = raises;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
