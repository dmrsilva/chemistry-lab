package com.school.chemistrylab.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.chemistrylab.dto.MaterialDTO;
import com.school.chemistrylab.entities.Material;
import com.school.chemistrylab.repositories.MaterialRepository;
import com.school.chemistrylab.services.exceptions.ResourceNotFoundException;

@Service
public class MaterialService {

	@Autowired
	MaterialRepository repository;	
	
	@Transactional
	public MaterialDTO insert(MaterialDTO dto) {
		Material entity = new Material();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity = repository.save(entity);
		return new MaterialDTO(entity);
	}

	@Transactional
	public MaterialDTO update(Long id, MaterialDTO dto) {
		try {
			Material entity = repository.getById(id);
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity = repository.save(entity);
			return new MaterialDTO(entity);
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
	public MaterialDTO findById(Long id) {
		Optional<Material> obj = repository.findById(id);
		Material entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MaterialDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<MaterialDTO> findAll() {
		List<Material> list = repository.findAll();
		return list.stream().map(x -> new MaterialDTO(x)).collect(Collectors.toList());
	}

}
