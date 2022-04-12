package com.school.chemistrylab.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.school.chemistrylab.entities.Produto;
import com.school.chemistrylab.tests.Factory;

@DataJpaTest
public class ProdutoRepositoryTests {

	@Autowired
	private ProdutoRepository repository;
	
	private long exintingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		exintingId = 1L;
		nonExistingId = 10L;
		countTotalProducts = 1L;
	}

	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Produto produto = Factory.createProduto();
		produto.setId(null);
		
		produto = repository.save(produto);
		
		Assertions.assertNotNull(produto.getId());
		Assertions.assertEquals(countTotalProducts + 1, produto.getId());
		
	}
	
	@Test
	public void findByIdShouldReturnNonEmptyOptionalProdutoWhenIdExists() {
		
		Optional<Produto> result = repository.findById(exintingId);
		
		Assertions.assertTrue(result.isPresent());
		
	}
	
	@Test
	public void findByIdShouldEmptytOptionalWhenIdDoesNotExist() {
		
		Optional<Produto> result = repository.findById(nonExistingId);

		Assertions.assertTrue(result.isEmpty());
		
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		repository.deleteById(exintingId);
		
		Optional<Produto> result = repository.findById(exintingId);
		Assertions.assertTrue(result.isEmpty());
		
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});		
	}
	
}
