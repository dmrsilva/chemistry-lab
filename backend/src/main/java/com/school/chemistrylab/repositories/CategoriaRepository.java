package com.school.chemistrylab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.chemistrylab.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
