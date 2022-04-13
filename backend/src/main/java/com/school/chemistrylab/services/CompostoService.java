package com.school.chemistrylab.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.chemistrylab.dto.CompostoDTO;
import com.school.chemistrylab.entities.Composto;
import com.school.chemistrylab.repositories.CompostoRepository;
import com.school.chemistrylab.services.exceptions.ResourceNotFoundException;

@Service
public class CompostoService {

	@Autowired
	CompostoRepository repository;	
	
	@Transactional
	public CompostoDTO insert(CompostoDTO dto) {
		Composto entity = new Composto();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity = repository.save(entity);
		return new CompostoDTO(entity);
	}

	@Transactional
	public CompostoDTO update(Long id, CompostoDTO dto) {
		try {
			Composto entity = repository.getById(id);
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity = repository.save(entity);
			return new CompostoDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		
	}

	@Transactional(readOnly = true)
	public CompostoDTO findById(Long id) {
		Optional<Composto> obj = repository.findById(id);
		Composto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CompostoDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<CompostoDTO> findAll() {
		List<Composto> list = repository.findAll();
		return list.stream().map(x -> new CompostoDTO(x)).collect(Collectors.toList());
	}

}
