package com.enviro.assessment.grad001.desiregwanzura.service;


import com.enviro.assessment.grad001.desiregwanzura.dto.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.desiregwanzura.dto.RecycleTipDTO;
import com.enviro.assessment.grad001.desiregwanzura.dto.WasteDTO;
import com.enviro.assessment.grad001.desiregwanzura.exception.ResourceNotFoundException;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Category;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.DisposalGuideline;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.RecycleTip;
import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import com.enviro.assessment.grad001.desiregwanzura.repository.CategoryRepository;
import com.enviro.assessment.grad001.desiregwanzura.repository.WasteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteService {
    @Autowired

    private final WasteRepository wasteRepository;
    private final CategoryRepository categoryRepository;

    public WasteService(WasteRepository wasteRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.wasteRepository = wasteRepository;
    }


    private Waste convertToEntity(WasteDTO dto){
        Waste waste = new Waste();
        waste.setName(dto.getName().trim().toLowerCase());

        if (dto.getCategory() == null || dto.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Category must not be Null or Empty");
        }

        Category category = categoryRepository.findByName(dto.getCategory().trim().toLowerCase())
                .orElseThrow(()-> new ResourceNotFoundException("Category:" + dto.getCategory()+ " Not found"));
        waste.setCategory(category);

        if (dto.getRecycleTips() !=null){
            waste.setRecycleTip(dto.getRecycleTips().stream()
                    .map(recycleTipDTO -> {
                        RecycleTip tip = new RecycleTip();
                        tip.setTip(recycleTipDTO.getTip());
                        tip.setWaste(waste);
                        return tip;
                    }).collect(Collectors.toList())
            );

        }

        if (dto.getDisposalGuidelines() != null) {
            waste.setDisposalGuideline(dto.getDisposalGuidelines().stream()
                    .map(disposalGuidelineDTO -> {
                        DisposalGuideline guideline = new DisposalGuideline();
                        guideline.setGuideline(disposalGuidelineDTO.getGuideline());
                        guideline.setWaste(waste);
                        return guideline;
                    }).collect(Collectors.toList())
            );

        }

        return waste;
    }

    private WasteDTO convertToDto(Waste waste){
        WasteDTO wasteDTO = new WasteDTO();
        wasteDTO.setId(waste.getId());
        wasteDTO.setName(waste.getName().trim().toLowerCase());
        wasteDTO.setCategory(waste.getCategory().getName().trim().toLowerCase());

        if (waste.getRecycleTip() != null){
            wasteDTO.setRecycleTips(waste.getRecycleTip().stream()
                    .map(tip-> new RecycleTipDTO(tip.getId(),tip.getTip()))
                    .collect(Collectors.toList()));
        }

        if (waste.getDisposalGuideline() != null){
            wasteDTO.setDisposalGuidelines(waste.getDisposalGuideline().stream()
                    .map(guideline-> new DisposalGuidelineDTO(guideline.getId(),guideline.getGuideline()))
                    .collect(Collectors.toList())
            );
        }

        return wasteDTO;
    }


    @Transactional
    public WasteDTO createWaste(WasteDTO wasteDTO) {
        Waste waste = convertToEntity(wasteDTO);
        Waste savedWaste = wasteRepository.save(waste);
        return convertToDto(savedWaste);
    }

    public List<WasteDTO> getAll() {

        return wasteRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public WasteDTO getById(Long id) {
        Waste waste = wasteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste not found"));

        return convertToDto(waste);
    }

    @Transactional
    public WasteDTO updateById(Long id, WasteDTO wasteDTO) {
        Waste existingWaste = wasteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste with ID: " + id + " not found"));

        existingWaste.setName(wasteDTO.getName());

        if (wasteDTO.getCategory() == null || wasteDTO.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }

        Category category = categoryRepository.findByName(wasteDTO.getCategory().trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + wasteDTO.getCategory()));
        existingWaste.setCategory(category);
        existingWaste.getRecycleTip().clear();

        if (wasteDTO.getRecycleTips() != null) {
            existingWaste.getRecycleTip().addAll(wasteDTO.getRecycleTips().stream()
                    .map(tipDto -> {
                        RecycleTip tip = new RecycleTip();
                        tip.setTip(tipDto.getTip());
                        tip.setWaste(existingWaste);
                        return tip;
                    }).collect(Collectors.toList()));
        }

        if (wasteDTO.getDisposalGuidelines() != null) {
            existingWaste.getDisposalGuideline().addAll(wasteDTO.getDisposalGuidelines().stream()
                    .map(guidelineDto -> {
                        DisposalGuideline guideline = new DisposalGuideline();
                        guideline.setGuideline(guidelineDto.getGuideline());
                        guideline.setWaste(existingWaste);
                        return guideline;
                    }).toList());
        }

        Waste updatedWaste = wasteRepository.save(existingWaste);
        return convertToDto(updatedWaste);
    }

    @Transactional
    public void delete(Long id) {
        Waste waste = wasteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste not found"));

        wasteRepository.delete(waste);

    }
}
