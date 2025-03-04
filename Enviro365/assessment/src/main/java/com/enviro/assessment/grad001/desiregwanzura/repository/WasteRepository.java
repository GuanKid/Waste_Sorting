package com.enviro.assessment.grad001.desiregwanzura.repository;

import com.enviro.assessment.grad001.desiregwanzura.model.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasteRepository extends JpaRepository<Waste, Long> {
    Optional<Waste> findByName(String name);
}
