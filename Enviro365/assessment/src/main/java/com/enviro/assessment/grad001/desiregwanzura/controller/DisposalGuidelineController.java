package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse;
import com.enviro.assessment.grad001.desiregwanzura.service.DisposalGuidelineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidelines")
public class DisposalGuidelineController {
    @Autowired
    private DisposalGuidelineService disposalGuidelinesService;

    @PostMapping("/")
    public ResponseEntity<DisposalGuidelineDTO> createGuideline(@Valid @RequestBody DisposalGuidelineDTO disposalGuidelineDTO){
        return ResponseEntity.ok(disposalGuidelinesService.createDisposalGuideline(disposalGuidelineDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> updateGuideline(@PathVariable("id") Long id, @Valid @RequestBody DisposalGuidelineDTO disposalGuidelineDTO){
        return ResponseEntity.ok(disposalGuidelinesService.updateDisposalGuideline(id,disposalGuidelineDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuideline(@PathVariable("id") Long id) {
        disposalGuidelinesService.deleteGuideline(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<DisposalGuidelineDTO>> getAllGuidelines(){
        List<DisposalGuidelineDTO> guidelines = disposalGuidelinesService.getAllDisposalGuidelines();
        return ResponseEntity.ok(new ApiResponse<>("Disposal Guidelines Retrieved", guidelines).getData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuidelineDTO> getGuidelineById(@PathVariable Long id){
        DisposalGuidelineDTO guideline = disposalGuidelinesService.getDisposalGuidelineById(id);
        return ResponseEntity.ok(new ApiResponse<>("Disposal Guideline retrieved",guideline).getData());
    }

    @GetMapping("/{wasteName}")
    public ResponseEntity<List<DisposalGuidelineDTO>> getGuidelinesByWasteName(@PathVariable String wasteName){
        List<DisposalGuidelineDTO> guideline = disposalGuidelinesService.getGuidelinesByWasteName(wasteName);
        return ResponseEntity.ok((new ApiResponse<>("Disposal Guidelines Retrieved", guideline)).getData());
    }
}
