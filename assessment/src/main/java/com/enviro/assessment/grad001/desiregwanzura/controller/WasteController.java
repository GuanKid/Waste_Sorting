package com.enviro.assessment.grad001.desiregwanzura.controller;


import com.enviro.assessment.grad001.desiregwanzura.dto.WasteDTO;
import com.enviro.assessment.grad001.desiregwanzura.service.WasteService;
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
@RequestMapping("api/waste")
public class WasteController {

    @Autowired
    private WasteService wasteService;

    @Operation(summary = "Create a new waste entry", description = "Add a new waste record to the system",tags = { "Waste", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waste entry created successfully", content = @Content(schema = @Schema(implementation = WasteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<WasteDTO> createWaste(@Valid @RequestBody WasteDTO wasteDTO) {
        return ResponseEntity.ok(wasteService.createWaste(wasteDTO));
    }

    @Operation(summary = "Update an existing waste entry", description = "Modify waste record by ID",tags = { "Waste", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waste entry updated successfully", content = @Content(schema = @Schema(implementation = WasteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Waste entry not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<WasteDTO> updateById(@PathVariable("id") Long id, @Valid @RequestBody WasteDTO wasteDTO) {
        return ResponseEntity.ok(wasteService.updateById(id, wasteDTO));
    }

    @Operation(summary = "Delete a waste entry", description = "Remove a waste record by ID",tags = { "Waste", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waste entry deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Waste entry not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaste(@PathVariable("id") Long id) {
        wasteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all waste entries", description = "Retrieve a list of all waste records",tags = { "Waste", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of waste records retrieved successfully", content = @Content(schema = @Schema(implementation = WasteDTO.class)))
    })
    @GetMapping("/")
    public ResponseEntity<List<WasteDTO>> getAllWaste() {
        List<WasteDTO> waste = wasteService.getAll();
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Waste Retrieved", waste).getData());
    }

    @Operation(summary = "Get waste entry by ID", description = "Retrieve details of a specific waste record by its ID",tags = { "Waste", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Waste entry retrieved successfully", content = @Content(schema = @Schema(implementation = WasteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Waste entry not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<WasteDTO> getWasteById(@PathVariable Long id) {
        WasteDTO wasteDTO = wasteService.getById(id);
        return ResponseEntity.ok(new com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse<>("Waste Retrieved", wasteDTO).getData());
    }
}