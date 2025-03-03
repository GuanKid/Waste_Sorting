package com.enviro.assessment.grad001.desiregwanzura.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecycleTipDTO {
    private Long id;

    @NotBlank(message = "Disposal Tip is required")
    @Size(min = 3, max = 50, message = "Tip must be between 3 to 50 characters")
    private String tip;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String waste;


    public RecycleTipDTO(){}

    public RecycleTipDTO(Long id, String tip, String waste) {
        this.id = id;
        this.tip = tip;
        this.waste = waste;
    }

    public RecycleTipDTO(Long id, String tip) {
        this.id = id;
        this.tip = tip;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getWaste() {
        return waste;
    }

    public void setWaste(String waste) {
        this.waste = waste;
    }
}
