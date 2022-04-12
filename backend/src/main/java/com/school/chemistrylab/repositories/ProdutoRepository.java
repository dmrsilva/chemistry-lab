package com.school.chemistrylab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.chemistrylab.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
