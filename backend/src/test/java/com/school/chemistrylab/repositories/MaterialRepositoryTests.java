package com.school.chemistrylab.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.school.chemistrylab.entities.Material;
import com.school.chemistrylab.tests.Factory;

@DataJpaTest
public class MaterialRepositoryTests {
	
	@Autowired
	private MaterialRepository repository;

	private long exintingId;
	private long nonExistingId;
	private long countTotalMaterials;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 100L;
		countTotalMaterials = 6L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Material material = Factory.createMaterial();
		material.setId(null);
		
		material = repository.save(material);
		
		Assertions.assertNotNull(material.getId());
		Assertions.assertEquals(countTotalMaterials + 1, material.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalMaterialWhenIdExists() {
		
		Optional<Material> result = repository.findById(exintingId);
		
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldEmptytOptionalWhenIdDoesNotExist() {
		
		Optional<Material> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(exintingId);
		
		Optional<Material> result = repository.findById(exintingId);
		Assertions.assertTrue(result.isEmpty());
		
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
		
	}	
	
}
