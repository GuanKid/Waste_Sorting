package com.enviro.assessment.grad001.desiregwanzura.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Category name is required.")
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Waste> waste;


    public Category(){}

    public Category(Long id, String name, List<Waste> waste) {
        this.id = id;
        this.name = name;
        this.waste = waste;
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

    public List<Waste> getWaste() {
        return waste;
    }

    public void setWaste(List<Waste> waste) {
        this.waste = waste;
    }
}
