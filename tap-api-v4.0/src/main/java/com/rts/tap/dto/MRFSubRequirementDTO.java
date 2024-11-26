package com.rts.tap.dto;

import java.util.List;

public class MRFSubRequirementDTO {
    
    private List<Long> subRequirementIds;

    public MRFSubRequirementDTO(List<Long> subRequirementIds) {
        this.subRequirementIds = subRequirementIds;
    }

    public List<Long> getSubRequirementIds() {
        return subRequirementIds;
    }

    public void setSubRequirementIds(List<Long> subRequirementIds) {
        this.subRequirementIds = subRequirementIds;
    }
}