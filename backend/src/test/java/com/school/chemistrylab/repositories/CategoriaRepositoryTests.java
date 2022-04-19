package com.school.chemistrylab.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.school.chemistrylab.entities.Categoria;
import com.school.chemistrylab.tests.Factory;

@DataJpaTest
public class CategoriaRepositoryTests {
	
	@Autowired
	private CategoriaRepository repository;

	private long exintingId;
	private long nonExistingId;
	private long countTotalCategorias;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 100L;
		countTotalCategorias = 6L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Categoria categoria = Factory.createCategoria();
		categoria.setId(null);
		
		categoria = repository.save(categoria);
		
		Assertions.assertNotNull(categoria.getId());
		Assertions.assertEquals(countTotalCategorias + 1, categoria.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalCategoriaWhenIdExists() {
		
		Optional<Categoria> result = repository.findById(exintingId);
		
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldEmptytOptionalWhenIdDoesNotExist() {
		
		Optional<Categoria> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(exintingId);
		
		Optional<Categoria> result = repository.findById(exintingId);
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
		
	}	
	
}
