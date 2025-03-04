package com.enviro.assessment.grad001.desiregwanzura.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "recycle_tip")
public class RecycleTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tip is required")
    private String tip;

    @JsonIgnoreProperties("recyclingTips")
    @ManyToOne
    @JoinColumn(name = "waste_id")
    private Waste waste;

    public RecycleTip() {}


    public RecycleTip(Long id, String tip, Waste waste) {
        this.id = id;
        this.tip = tip;
        this.waste = waste;
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

    public Waste getWaste() {
        return waste;
    }

    public void setWaste(Waste waste) {
        this.waste = waste;
    }
}
