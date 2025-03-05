package com.enviro.assessment.grad001.desiregwanzura.repository;

import com.enviro.assessment.grad001.desiregwanzura.model.entity.RecycleTip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycleTipRepository extends JpaRepository<RecycleTip, Long> {
    List<RecycleTip> findByWasteId(Long wasteId);
}
