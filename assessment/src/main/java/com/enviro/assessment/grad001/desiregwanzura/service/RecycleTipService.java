package com.enviro.assessment.grad001.desiregwanzura.service;

import com.enviro.assessment.grad001.desiregwanzura.dto.RecycleTipDTO;
import com.enviro.assessment.grad001.desiregwanzura.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.RecycleTip;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import com.enviro.assessment.grad001.desiregwanzura.repository.RecycleTipRepository;
import com.enviro.assessment.grad001.desiregwanzura.repository.WasteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecycleTipService {
    private final RecycleTipRepository recycleTipRepository;
    private final WasteRepository wasteRepository;

    @Autowired
    public RecycleTipService(RecycleTipRepository recycleTipRepository, WasteRepository wasteRepository) {
        this.recycleTipRepository = recycleTipRepository;
        this.wasteRepository = wasteRepository;
    }


    private RecycleTip convertToEntity(RecycleTipDTO dto) {
        if (dto.getWaste() == null || dto.getWaste().trim().isEmpty()) {
            throw new IllegalArgumentException("Waste name cannot be null or empty");
        }
        Waste waste = wasteRepository.findByName(dto.getWaste().trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Waste not found: " + dto.getWaste()));
        RecycleTip tip = new RecycleTip();
        tip.setTip(dto.getTip());
        tip.setWaste(waste);
        return tip;
    }

    private RecycleTipDTO convertToDto(RecycleTip tip) {
        return new RecycleTipDTO(tip.getId(), tip.getTip(), tip.getWaste().getName().trim().toLowerCase());
    }

    public List<RecycleTipDTO> getAll(){
        return recycleTipRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RecycleTipDTO getById(Long id) {
        RecycleTip tip = recycleTipRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recycle Tip not found: " + id));
        return convertToDto(tip);

    }

    @Transactional
    public RecycleTipDTO createRecycleTip(RecycleTipDTO recycleTipDTO) {
        RecycleTip recycleTip = convertToEntity(recycleTipDTO);
        RecycleTip savedTip = recycleTipRepository.save(recycleTip);
        return convertToDto(savedTip);
    }

    @Transactional
    public RecycleTipDTO updateRecycleTip(Long id, RecycleTipDTO recycleTipDTO) {
        RecycleTip existingTip = recycleTipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recycle tip not found"));
        existingTip.setTip(recycleTipDTO.getTip());
        Waste waste = wasteRepository.findByName(recycleTipDTO.getWaste())
                .orElseThrow(() -> new ResourceNotFoundException("Waste not found: " + recycleTipDTO.getWaste()));
        existingTip.setWaste(waste);
        RecycleTip updatedTip = recycleTipRepository.save(existingTip);
        return convertToDto(updatedTip);
    }

    @Transactional
    public void deleteRecycleTip(Long id) {
        RecycleTip tip = recycleTipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recycle tip not found"));
        recycleTipRepository.delete(tip);
    }
}
