package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.desiregwanzura.service.DisposalGuidelineService;
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
@RequestMapping("/api/guidelines")
public class DisposalGuidelineController {
    @Autowired
    private DisposalGuidelineService disposalGuidelinesService;

    @Operation(summary = "Create a new Disposal Guideline entry", description = "Add a new Disposal Guideline record to the system", tags = { "Disposal Guideline", "post" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Disposal Guideline entry created successfully", content = @Content(schema = @Schema(implementation = DisposalGuidelineDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<DisposalGuidelineDTO> createGuideline(@Valid @RequestBody DisposalGuidelineDTO disposalGuidelineDTO){
        return ResponseEntity.ok(disposalGuidelinesService.createDisposalGuideline(disposalGuidelineDTO));
    }


    @Operation(summary = "Update an existing Disposal Guideline entry", description = "Modify Disposal Guideline record by ID", tags = { "Disposal Guideline", "put" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Disposal Guideline entry updated successfully", content = @Content(schema = @Schema(implementation = DisposalGuidelineDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Disposal Guideline entry not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> updateGuideline(@PathVariable("id") Long id, @Valid @RequestBody DisposalGuidelineDTO disposalGuidelineDTO){
        return ResponseEntity.ok(disposalGuidelinesService.updateDisposalGuideline(id,disposalGuidelineDTO));
    }


    @Operation(summary = "Delete a Disposal Guideline entry", description = "Remove a Disposal Guideline record by ID", tags = { "Disposal Guideline", "delete" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Disposal Guideline entry deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Disposal Guideline entry not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable("id") Long id) {
        disposalGuidelinesService.deleteGuideline(id);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Get all Disposal Guideline entries", description = "Retrieve a list of all Disposal Guideline records", tags = { "Disposal Guideline", "get" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of Disposal Guideline records retrieved successfully", content = @Content(schema = @Schema(implementation = DisposalGuidelineDTO.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<DisposalGuidelineDTO>> getAllGuidelines(){
        List<DisposalGuidelineDTO> guidelines = disposalGuidelinesService.getAllDisposalGuidelines();
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Disposal Guidelines Retrieved", guidelines).getData());
    }

    @Operation(summary = "Get Disposal Guideline entry by ID", description = "Retrieve details of a specific Disposal Guideline record by its ID", tags = { "Disposal Guideline", "get" })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Disposal Guideline entry retrieved successfully", content = @Content(schema = @Schema(implementation = DisposalGuidelineDTO.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Disposal Guideline entry not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> getGuidelineById(@PathVariable Long id){
        DisposalGuidelineDTO guideline = disposalGuidelinesService.getDisposalGuidelineById(id);
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Disposal Guideline retrieved",guideline).getData());
    }
}
