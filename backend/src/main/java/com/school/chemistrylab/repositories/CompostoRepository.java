package com.school.chemistrylab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.chemistrylab.entities.Composto;

public interface CompostoRepository extends JpaRepository<Composto, Long> {

}
