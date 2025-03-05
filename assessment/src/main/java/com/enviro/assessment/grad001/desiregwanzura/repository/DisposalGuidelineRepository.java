package com.enviro.assessment.grad001.desiregwanzura.repository;

import com.enviro.assessment.grad001.desiregwanzura.model.entity.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findByWasteId(Long wasyeId);
    List<DisposalGuideline> findByWasteName(String wasteName);
}
