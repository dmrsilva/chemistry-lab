package com.school.chemistrylab.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.chemistrylab.dto.ProdutoDTO;
import com.school.chemistrylab.services.ProdutoService;
import com.school.chemistrylab.services.exceptions.DatabaseException;
import com.school.chemistrylab.services.exceptions.ResourceNotFoundException;
import com.school.chemistrylab.tests.Factory;

@WebMvcTest(ProdutoResource.class)
public class ProdutoResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private long existingId;
	private long nonExistingId;
	private long dependentId;
	private ProdutoDTO produtoDto;
	private List<ProdutoDTO> list;
	
	@BeforeEach
	void setUp() throws Exception {
		
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;
		
		produtoDto = Factory.createProdutoDTO();
		
		when(service.insert(any())).thenReturn(produtoDto);
		
		when(service.update(eq(existingId), any())).thenReturn(produtoDto);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		when(service.findById(existingId)).thenReturn(produtoDto);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.findAll()).thenReturn(list);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);	
	
	}
	
	@Test
	public void insertShouldReturnCreateAndProdutoDTO() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(produtoDto);
		
		ResultActions result = 
				mockMvc.perform(post("/produtos")
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.description").exists());
		
	}
	
	@Test
	public void updateShouldReturnProdutoDTOWhenIdExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(produtoDto);

		ResultActions result =
				mockMvc.perform(put("/produtos/{id}", existingId)
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.description").exists());
		
	}

	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNoExists() throws Exception {
		
		String jsonBody = objectMapper.writeValueAsString(produtoDto);
		
		ResultActions result = 
				mockMvc.perform(put("/produtos/{id}", nonExistingId)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void findByIdShouldReturnProdutoWhenIdExists() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/produtos/{id}", existingId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
		result.andExpect(jsonPath("$.description").exists());
		
	}
	
	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/produtos/{id}", nonExistingId)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void findAllShouldReturnList() throws Exception {
		
		ResultActions result = 
				mockMvc.perform(get("/produtos")
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {

		ResultActions result =
				mockMvc.perform(delete("/produtos/{id}", existingId));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result =
				mockMvc.perform(delete("/produtos/{id}", nonExistingId));
		
		result.andExpect(status().isNotFound());
	}

	
}
