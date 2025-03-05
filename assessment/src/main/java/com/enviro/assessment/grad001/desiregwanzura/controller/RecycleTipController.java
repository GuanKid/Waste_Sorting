package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.RecycleTipDTO;
import com.enviro.assessment.grad001.desiregwanzura.service.RecycleTipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Waste Sorting", description = "APIs for managing waste records")
@RestController
@RequestMapping("/api/tips")
public class RecycleTipController {
    @Autowired
    private RecycleTipService recycleTipService;

    @Operation(summary = "Create a new Recycle Tip entry", description = "Add a new waste record to the system",tags = { "Recycle Tip", "post" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recycle Tip entry created successfully", content = @Content(schema = @Schema(implementation = RecycleTipDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<RecycleTipDTO> createRecycleTip(@Valid @RequestBody RecycleTipDTO recycleTipDTO) {
        return ResponseEntity.ok(recycleTipService.createRecycleTip(recycleTipDTO));
    }

    @Operation(summary = "Update an existing Recycle Tip entry", description = "Modify waste record by ID",tags = { "Recycle Tip", "put" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recycle Tip entry updated successfully", content = @Content(schema = @Schema(implementation = RecycleTipDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Recycle Tip entry not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecycleTipDTO> updateRecycleTip(@PathVariable("id") Long id, @Valid @RequestBody RecycleTipDTO recycleTipDTO) {
        return ResponseEntity.ok(recycleTipService.updateRecycleTip(id, recycleTipDTO));
    }

    @Operation(summary = "Delete a Recycle Tip entry", description = "Remove a Recycle Tip record by ID",tags = { "Recycle Tip", "delete" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recycle Tip entry deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Recycle Tip entry not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecycleTip(@PathVariable("id") Long id) {
        recycleTipService.deleteRecycleTip(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all Recycle Tip entries", description = "Retrieve a list of all Recycle Tip records",tags = { "Recycle Tip", "get" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of Recycle Tip records retrieved successfully", content = @Content(schema = @Schema(implementation = RecycleTipDTO.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<RecycleTipDTO>> getAllRecycleTips() {
        List<RecycleTipDTO> tips = recycleTipService.getAll();
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Recycle Tips Retrieved", tips).getData());
    }

    @Operation(summary = "Get Recycle Tip entry by ID", description = "Retrieve details of a specific Recycle Tip record by its ID",tags = { "Recycle Tip", "get" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Recycle Tip entry retrieved successfully", content = @Content(schema = @Schema(implementation = RecycleTipDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Recycle Tip entry not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RecycleTipDTO> getRecycleTipById(@PathVariable Long id) {
        RecycleTipDTO tip = recycleTipService.getById(id);
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Recycle Tip Retrieved", tip).getData());
    }
}
