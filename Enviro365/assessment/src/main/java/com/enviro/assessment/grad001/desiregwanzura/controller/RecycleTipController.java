package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.RecycleTipDTO;
import com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse;
import com.enviro.assessment.grad001.desiregwanzura.service.RecycleTipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tips")
public class RecycleTipController {
    @Autowired
    private RecycleTipService recycleTipService;

    // Create a new recycle tip
    @PostMapping("/")
    public ResponseEntity<RecycleTipDTO> createRecycleTip(@Valid @RequestBody RecycleTipDTO recycleTipDTO) {
        return ResponseEntity.ok(recycleTipService.createRecycleTip(recycleTipDTO));
    }

    // Update an existing recycle tip
    @PutMapping("/{id}")
    public ResponseEntity<RecycleTipDTO> updateRecycleTip(@PathVariable("id") Long id, @Valid @RequestBody RecycleTipDTO recycleTipDTO) {
        return ResponseEntity.ok(recycleTipService.updateRecycleTip(id, recycleTipDTO));
    }

    // Delete a recycle tip
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecycleTip(@PathVariable("id") Long id) {
        recycleTipService.deleteRecycleTip(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<RecycleTipDTO>> getAllRecycleTips() {
        List<RecycleTipDTO> tips = recycleTipService.getAll();
        return ResponseEntity.ok(new ApiResponse<>("Recycle Tips Retrieved", tips).getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecycleTipDTO> getRecycleTipById(@PathVariable Long id) {
        RecycleTipDTO tip = recycleTipService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>("Recycle Tip Retrieved", tip).getData());
    }
//
//    @GetMapping("/waste/{wasteName}")
//    public ResponseEntity<List<RecycleTipDTO>> getTipsByWasteName(@PathVariable String wasteName) {
//        List<RecycleTipDTO> tips = recycleTipService.getTipsByWasteName(wasteName);
//        return ResponseEntity.ok(new ApiResponse<>("Recycle Tips Retrieved", tips).getData());
//    }
}
