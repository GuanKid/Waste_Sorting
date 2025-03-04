package com.enviro.assessment.grad001.desiregwanzura.service;


import com.enviro.assessment.grad001.desiregwanzura.dto.CategoryDTO;
import com.enviro.assessment.grad001.desiregwanzura.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.desiregwanzura.dto.RecycleTipDTO;
import com.enviro.assessment.grad001.desiregwanzura.dto.WasteDTO;
import com.enviro.assessment.grad001.desiregwanzura.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Category;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.DisposalGuideline;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.RecycleTip;
import com.enviro.assessment.grad001.desiregwanzura.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private List<RecycleTipDTO> mapRecycleTipsToDTOs(List<RecycleTip> recycleTips) {
        return (recycleTips == null) ? new ArrayList<>() :
                recycleTips.stream()
                        .map(tip -> new RecycleTipDTO(tip.getId(), tip.getTip()))
                        .collect(Collectors.toList());
    }

    private List<DisposalGuidelineDTO> mapDisposalGuidelinesToDTOs(List<DisposalGuideline> guidelines) {
        return (guidelines == null) ? new ArrayList<>() :
                guidelines.stream()
                        .map(guide -> new DisposalGuidelineDTO(guide.getId(), guide.getGuideline()))
                        .collect(Collectors.toList());
    }



    private Category convertToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    private CategoryDTO convertToDto(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getWaste() != null) {
            List<WasteDTO> wasteDTOs = entity.getWaste().stream()
                    .map(waste -> new WasteDTO(
                            waste.getId(),
                            waste.getName(),
//                            waste.getCategory().getName(),
                            mapRecycleTipsToDTOs(waste.getRecycleTip()),  // Convert RecycleTip entities to DTOs
                            mapDisposalGuidelinesToDTOs(waste.getDisposalGuideline()) // Convert DisposalGuideline entities to DTOs
                    ))
                    .collect(Collectors.toList());
            dto.setWasteList(wasteDTOs);
        }

        return dto;
    }


    public CategoryDTO getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        return convertToDto(category);
    }

    public List<CategoryDTO> getAll() {

        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategoryById(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existingCategory.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(existingCategory);
        return convertToDto(savedCategory);
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        categoryRepository.deleteById(id);
    }
}
