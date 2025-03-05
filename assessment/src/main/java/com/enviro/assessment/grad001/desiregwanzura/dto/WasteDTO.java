package com.enviro.assessment.grad001.desiregwanzura.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WasteDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 to 50 characters")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Category is required")
    private String category;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<RecycleTipDTO> recycleTips;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DisposalGuidelineDTO> disposalGuidelines;


    public WasteDTO(){}


    public WasteDTO(Long id, String name, String category, List<RecycleTipDTO> recycleTips, List<DisposalGuidelineDTO> disposalGuidelines) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.recycleTips = recycleTips;
        this.disposalGuidelines = disposalGuidelines;
    }

    public WasteDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public WasteDTO(Long id, String name, List<RecycleTipDTO> recycleTips, List<DisposalGuidelineDTO> disposalGuidelines) {
        this.id = id;
        this.name = name;
        this.recycleTips = recycleTips;
        this.disposalGuidelines = disposalGuidelines;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<RecycleTipDTO> getRecycleTips() {
        return recycleTips;
    }

    public void setRecycleTips(List<RecycleTipDTO> recycleTips) {
        this.recycleTips = recycleTips;
    }

    public List<DisposalGuidelineDTO> getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public void setDisposalGuidelines(List<DisposalGuidelineDTO> disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }
}
