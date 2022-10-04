package com.hrsystem.hrsystem.entity.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LeavesCommand {
    private Integer leaves ;
    private LocalDate date ;

    public LeavesCommand(Integer leaves , LocalDate date) {
        this.leaves = leaves;
        this.date = date;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
