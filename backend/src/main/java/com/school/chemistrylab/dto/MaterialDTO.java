package com.school.chemistrylab.dto;

import com.school.chemistrylab.entities.Material;

public class MaterialDTO {

	private Long id;
	private String name;
	private String description;

	public MaterialDTO() {
	}

	public MaterialDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public MaterialDTO(Material material) {
		id = material.getId();
		name = material.getName();
		description = material.getDescription();
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
