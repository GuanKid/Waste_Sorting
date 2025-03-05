package com.enviro.assessment.grad001.desiregwanzura.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "disposal_guideline")
public class DisposalGuideline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Disposal Guideline is required")
    private String guideline;

    @JsonIgnoreProperties("disposalGuideline")
    @ManyToOne
    @JoinColumn(name = "waste_id")
    private Waste waste;

    public DisposalGuideline(){}

    public DisposalGuideline(Long id, String guideline, Waste waste) {
        this.id = id;
        this.guideline = guideline;
        this.waste = waste;
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

    public Waste getWaste() {
        return waste;
    }

    public void setWaste(Waste waste) {
        this.waste = waste;
    }
}
