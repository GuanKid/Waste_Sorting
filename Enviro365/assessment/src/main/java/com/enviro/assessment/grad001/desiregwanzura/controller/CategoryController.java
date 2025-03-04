package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.CategoryDTO;
import com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse;
import com.enviro.assessment.grad001.desiregwanzura.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategoryById(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> category = categoryService.getAll();
        return ResponseEntity.ok( new ApiResponse<>("Waste Retrieved",category).getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        return ResponseEntity.ok(categoryDTO);
    }
}
