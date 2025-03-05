package com.enviro.assessment.grad001.desiregwanzura.service;

import com.enviro.assessment.grad001.desiregwanzura.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.desiregwanzura.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.DisposalGuideline;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import com.enviro.assessment.grad001.desiregwanzura.repository.DisposalGuidelineRepository;
import com.enviro.assessment.grad001.desiregwanzura.repository.WasteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisposalGuidelineService {
    private final DisposalGuidelineRepository disposalGuidelinesRepository;
    private final WasteRepository wasteRepository;

    @Autowired

    public DisposalGuidelineService(DisposalGuidelineRepository disposalGuidelinesRepository, WasteRepository wasteRepository) {
        this.disposalGuidelinesRepository = disposalGuidelinesRepository;
        this.wasteRepository = wasteRepository;
    }

    private DisposalGuideline convertToEntity(DisposalGuidelineDTO dto){
        if (dto.getWaste() == null || dto.getWaste().trim().isEmpty())
        {
            throw new IllegalArgumentException("Waste Name cannot be Null or Empty");
        }

        Waste waste = wasteRepository.findByName(dto.getWaste().trim().toLowerCase())
                .orElseThrow(()-> new ResourceNotFoundException("Waste not found:" +  dto.getWaste()));
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setGuideline(dto.getGuideline());
        guideline.setWaste(waste);
        return guideline;

    }

    private DisposalGuidelineDTO convertToDto(DisposalGuideline guideline){
        return new DisposalGuidelineDTO(guideline.getId(),guideline.getGuideline(),guideline.getWaste().getName().trim().toLowerCase());
    }


    @Transactional
    public DisposalGuidelineDTO createDisposalGuideline(DisposalGuidelineDTO disposalGuidelineDTO){
        DisposalGuideline disposalGuideline = convertToEntity(disposalGuidelineDTO);
        DisposalGuideline savedGuideline = disposalGuidelinesRepository.save(disposalGuideline);
        return convertToDto(savedGuideline);
    }

    public List<DisposalGuidelineDTO> getAllDisposalGuidelines(){
        return disposalGuidelinesRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DisposalGuidelineDTO> getGuidelinesByWasteName(String wasteName){
        Waste waste = wasteRepository.findByName(wasteName)
                .orElseThrow(()-> new ResourceNotFoundException("Waste:" + wasteName + " Not found" ));

        return disposalGuidelinesRepository.findByWasteName(waste.getName()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DisposalGuidelineDTO getDisposalGuidelineById(Long id){
        DisposalGuideline guideline = disposalGuidelinesRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline not found"));
        return convertToDto(guideline);
    }

    @Transactional
    public DisposalGuidelineDTO updateDisposalGuideline(Long id, DisposalGuidelineDTO disposalGuidelineDTO){
        DisposalGuideline existingGuideline = disposalGuidelinesRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline not found"));
        existingGuideline.setGuideline(disposalGuidelineDTO.getGuideline());
        Waste waste = wasteRepository.findByName(disposalGuidelineDTO.getWaste())
                .orElseThrow(() -> new ResourceNotFoundException("Waste not found: " + disposalGuidelineDTO.getWaste()));
        existingGuideline.setWaste(waste);

        DisposalGuideline updatedGuideline = disposalGuidelinesRepository.save(existingGuideline);
        return convertToDto(updatedGuideline);
    }

    @Transactional
    public void deleteGuideline(Long id){
        DisposalGuideline guideline = disposalGuidelinesRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Disposal Guideline not found"));
        disposalGuidelinesRepository.delete(guideline);
    }
}
