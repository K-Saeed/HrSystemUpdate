package com.hrsystem.hrsystem.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BonusEmployeeDto {
    private Double bonus ;

    public BonusEmployeeDto(Double bonus) {
        this.bonus = bonus;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
