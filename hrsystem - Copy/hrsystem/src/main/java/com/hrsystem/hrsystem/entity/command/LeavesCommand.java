package com.hrsystem.hrsystem.entity.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LeavesCommand {
    private Integer leaves ;

    public LeavesCommand(Integer leaves) {
        this.leaves = leaves;
    }

    public Integer getLeaves() {
        return leaves;
    }

    public void setLeaves(Integer leaves) {
        this.leaves = leaves;
    }
}
