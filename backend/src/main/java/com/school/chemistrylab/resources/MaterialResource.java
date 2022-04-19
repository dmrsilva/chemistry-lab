package com.school.chemistrylab.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.chemistrylab.dto.MaterialDTO;
import com.school.chemistrylab.services.MaterialService;

@RestController
@RequestMapping(value = "/materiais")
public class MaterialResource {

	@Autowired
	private MaterialService service;
	
	@GetMapping
	public ResponseEntity<List<MaterialDTO>> findAll() {
		List<MaterialDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MaterialDTO> findById(@PathVariable Long id) {
		MaterialDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<MaterialDTO> insert(@RequestBody MaterialDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MaterialDTO> update(@PathVariable Long id, @RequestBody MaterialDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
		
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
