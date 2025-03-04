package com.enviro.assessment.grad001.desiregwanzura.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name ="waste")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @JsonIgnoreProperties("waste")
    @ManyToOne
    @JoinColumn(name = "category_id") // Category cannot be null
    private Category category;

    @JsonIgnoreProperties("waste")
    @OneToMany(mappedBy = "waste")
    private List<RecycleTip> recycleTip;

    @JsonIgnoreProperties("waste")
    @OneToMany(mappedBy = "waste")
    private List<DisposalGuideline> disposalGuideline;

    public Waste() {}


    public Waste(Long id, String name, Category category, List<RecycleTip> recycleTip, List<DisposalGuideline> disposalGuideline) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.recycleTip = recycleTip;
        this.disposalGuideline = disposalGuideline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<RecycleTip> getRecycleTip() {
        return recycleTip;
    }

    public void setRecycleTip(List<RecycleTip> recycleTip) {
        this.recycleTip = recycleTip;
    }

    public List<DisposalGuideline> getDisposalGuideline() {
        return disposalGuideline;
    }

    public void setDisposalGuideline(List<DisposalGuideline> disposalGuideline) {
        this.disposalGuideline = disposalGuideline;
    }
}
