package com.school.chemistrylab.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.chemistrylab.entities.Produto;

public class ProdutoDTO {

	private Long id;
	private String name;
	private String description;
	
	private List<CompostoDTO> list = new ArrayList<>();

	public ProdutoDTO() {
	}

	public ProdutoDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		name = produto.getName();
		description = produto.getDescription();
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

	public List<CompostoDTO> getList() {
		return list;
	}
	
}
