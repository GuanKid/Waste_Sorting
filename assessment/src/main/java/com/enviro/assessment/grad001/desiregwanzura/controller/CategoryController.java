package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.CategoryDTO;
import com.enviro.assessment.grad001.desiregwanzura.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Waste Sorting", description = "APIs for managing waste records")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create a new Category entry", description = "Add a new Category record to the system",tags = { "Category", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category entry created successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }


    @Operation(summary = "Update an existing Category entry", description = "Modify Category record by ID",tags = { "Category", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category entry updated successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category entry not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategoryById(id, categoryDTO));
    }


    @Operation(summary = "Delete a Category entry", description = "Remove a Category record by ID",tags = { "Category", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category entry deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category entry not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public  ResponseEntity<CategoryDTO> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get all Category entries", description = "Retrieve a list of all Category records",tags = { "Category", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of Category records retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> category = categoryService.getAll();
        return ResponseEntity.ok( new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Waste Retrieved",category).getData());
    }


    @Operation(summary = "Get Category entry by ID", description = "Retrieve details of a specific Category record by its ID",tags = { "Category", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category entry retrieved successfully", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category entry not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        return ResponseEntity.ok(categoryDTO);
    }

}
