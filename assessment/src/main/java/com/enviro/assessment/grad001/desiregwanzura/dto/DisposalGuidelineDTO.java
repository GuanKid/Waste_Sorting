package com.enviro.assessment.grad001.desiregwanzura.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisposalGuidelineDTO {
    private Long id;

    @NotBlank(message = "Disposal Guideline is required")
    @Size(min = 3, max = 50, message = "Guideline must be between 3 to 50 characters")
    private String guideline;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String waste;

    public DisposalGuidelineDTO() {}

    public DisposalGuidelineDTO(Long id, String guideline, String waste) {
        this.id = id;
        this.guideline = guideline;
        this.waste = waste;
    }

    public DisposalGuidelineDTO(Long id, String guideline) {
        this.id = id;
        this.guideline = guideline;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }
}
