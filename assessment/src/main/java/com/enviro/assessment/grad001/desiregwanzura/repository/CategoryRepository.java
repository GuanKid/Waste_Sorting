package com.enviro.assessment.grad001.desiregwanzura.repository;

import com.enviro.assessment.grad001.desiregwanzura.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
