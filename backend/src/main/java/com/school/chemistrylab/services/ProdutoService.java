package com.school.chemistrylab.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.chemistrylab.dto.CompostoDTO;
import com.school.chemistrylab.dto.ProdutoDTO;
import com.school.chemistrylab.entities.Composto;
import com.school.chemistrylab.entities.Produto;
import com.school.chemistrylab.repositories.CompostoRepository;
import com.school.chemistrylab.repositories.ProdutoRepository;
import com.school.chemistrylab.services.exceptions.DatabaseException;
import com.school.chemistrylab.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;	
	
	@Autowired
	CompostoRepository compostoRepository;
	
	@Transactional
	public ProdutoDTO insert(ProdutoDTO dto) {
		Produto entity = new Produto();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProdutoDTO(entity);
	}

	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		try {
			Produto entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProdutoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProdutoDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll() {
		List<Produto> produtos = repository.findAll();
		return produtos.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}

	}
	
	private void copyDtoToEntity(ProdutoDTO dto, Produto entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		
		for (CompostoDTO compostoDTO : dto.getCompostos()) {
			Composto composto = compostoRepository.getById(compostoDTO.getId());
			entity.getCompostos().add(composto);
		}
		
	}

}
