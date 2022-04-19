package com.school.chemistrylab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.chemistrylab.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
