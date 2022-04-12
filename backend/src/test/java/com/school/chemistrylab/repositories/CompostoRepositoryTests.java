package com.school.chemistrylab.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.school.chemistrylab.entities.Composto;
import com.school.chemistrylab.tests.Factory;

@DataJpaTest
public class CompostoRepositoryTests {
	
	@Autowired
	private CompostoRepository repository;

	private long exintingId;
	private long nonExistingId;
	private long countTotalCompostos;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 100L;
		countTotalCompostos = 1L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Composto composto = Factory.createComposto();
		composto.setId(null);
		
		composto = repository.save(composto);
		
		Assertions.assertNotNull(composto.getId());
		Assertions.assertEquals(countTotalCompostos + 1, composto.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalCompostoWhenIdExists() {
		
		Optional<Composto> result = repository.findById(exintingId);
		
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldEmptytOptionalWhenIdDoesNotExist() {
		
		Optional<Composto> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(exintingId);
		
		Optional<Composto> result = repository.findById(exintingId);
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
		
	}	
	
}
