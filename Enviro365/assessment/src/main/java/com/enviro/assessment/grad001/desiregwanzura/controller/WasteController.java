package com.enviro.assessment.grad001.desiregwanzura.controller;

import com.enviro.assessment.grad001.desiregwanzura.dto.WasteDTO;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import com.enviro.assessment.grad001.desiregwanzura.model.response.ApiResponse;
import com.enviro.assessment.grad001.desiregwanzura.service.WasteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/waste")
public class WasteController {
    @Autowired
    private WasteService wasteService;


    @PostMapping("/")
    public ResponseEntity<WasteDTO> createWaste(@Valid @RequestBody WasteDTO wasteDTO) {
        return ResponseEntity.ok(wasteService.createWaste(wasteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteDTO> updateById(@PathVariable("id") Long id, @Valid @RequestBody WasteDTO wasteDTO) {
        return ResponseEntity.ok(wasteService.updateById(id, wasteDTO));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<WasteDTO> deleteWaste(@PathVariable("id") Long id) {
        wasteService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/")
    public ResponseEntity<List<WasteDTO>> getAllWaste() {
        List<WasteDTO> waste = wasteService.getAll();
        return ResponseEntity.ok( new ApiResponse<>("Waste Retrieved",waste).getData());
    }
    @GetMapping("/{id}")
    public ResponseEntity<WasteDTO> getWasteById(@PathVariable Long id) {
        WasteDTO wasteDTO = wasteService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>("Waste Retrieved",wasteDTO).getData());
    }
}
