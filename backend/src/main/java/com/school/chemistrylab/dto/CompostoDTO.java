package com.school.chemistrylab.dto;

import com.school.chemistrylab.entities.Composto;

public class CompostoDTO {

	private Long id;
	private String name;
	private String description;

	public CompostoDTO() {
	}

	public CompostoDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public CompostoDTO(Composto composto) {
		id = composto.getId();
		name = composto.getName();
		description = composto.getDescription();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
