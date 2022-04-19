package com.school.chemistrylab.dto;

import com.school.chemistrylab.entities.Categoria;

public class CategoriaDTO {

	private Long id;
	private String name;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		name = categoria.getName();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
