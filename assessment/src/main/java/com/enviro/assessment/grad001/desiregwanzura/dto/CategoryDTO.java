package com.enviro.assessment.grad001.desiregwanzura.dto;

import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
    private Long id;


    @NotNull(message = "Category Name is required")
    @NotBlank(message = "Category Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 to 50 characters")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<WasteDTO> wasteList = new ArrayList<>();

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Long id, String name, List<WasteDTO> wasteList) {
        this.id = id;
        this.name = name;
        this.wasteList = wasteList;
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

    public List<WasteDTO> getWasteList() {
        return wasteList;
    }

    public void setWasteList(List<WasteDTO> wasteList) {
        this.wasteList = wasteList;
    }
}
