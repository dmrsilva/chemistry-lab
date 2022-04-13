package com.school.chemistrylab.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.school.chemistrylab.dto.ProdutoDTO;
import com.school.chemistrylab.entities.Produto;
import com.school.chemistrylab.repositories.ProdutoRepository;
import com.school.chemistrylab.services.exceptions.DatabaseException;
import com.school.chemistrylab.services.exceptions.ResourceNotFoundException;
import com.school.chemistrylab.tests.Factory;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

	@InjectMocks
	private ProdutoService service;
	
	@Mock
	private ProdutoRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;
	private Produto produto;
	private ProdutoDTO produtoDto;
	private List<Produto> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		
		produto = Factory.createProduto();
		produtoDto = Factory.createProdutoDTO();
		
		list = new ArrayList<>();
		list.add(produto);
		
		Mockito.when(repository.save(any())).thenReturn(produto);
		
		Mockito.when(repository.getById(existingId)).thenReturn(produto);
		
		Mockito.when(repository.getById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		Mockito.doNothing().when(repository).deleteById(existingId);
		
		Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId);
		
		Mockito.doThrow(DatabaseException.class).when(repository).deleteById(dependentId);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(produto));
		
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.findAll()).thenReturn(list);
	
	}
	
	@Test
	public void insertReturnProdutoDTO() {
		
		ProdutoDTO result = service.insert(produtoDto);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).save(any());
		
	}
	
	@Test
	public void updateShouldReturnProdutoDTOWhenIdDoesExist() {
		
		ProdutoDTO result = service.update(existingId, produtoDto);
		
		Assertions.assertNotNull(result);

		Mockito.verify(repository).getById(existingId);
		Mockito.verify(repository).save(any());
		
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, produtoDto);
		});
		
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository).deleteById(existingId);
		
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
		Mockito.verify(repository).deleteById(nonExistingId);
		
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dependentId);
		});
		
		Mockito.verify(repository).deleteById(dependentId);
		
	}

	@Test
	public void findByIdReturnProdutoDTOWhenIdExists() {
		
		ProdutoDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findById(existingId);
		
	}
	
	@Test
	public void findByIdThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
		
	}

	@Test
	public void findAllShouldReturnListProdutoDTO() {
		
		List<ProdutoDTO> result = service.findAll();
		
		Assertions.assertNotNull(result);
		
		Mockito.verify(repository).findAll();
		
	}
	
	
}
