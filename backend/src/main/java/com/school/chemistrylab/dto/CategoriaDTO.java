package com.school.chemistrylab.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.chemistrylab.entities.Categoria;
import com.school.chemistrylab.entities.Material;
import com.school.chemistrylab.entities.Produto;

public class CategoriaDTO {

	private Long id;
	private String name;
	
	private List<MaterialDTO> materiais = new ArrayList<>();
	
	private List<ProdutoDTO> produtos = new ArrayList<>();

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
	
	public CategoriaDTO(Categoria categoria, List<?> list) {
		this(categoria);
		
		if (list.getClass().getTypeName() == "Material") {
			list.forEach(material -> this.materiais.add(new MaterialDTO((Material)material)));
		}
		else
			list.forEach(produto -> this.produtos.add(new ProdutoDTO((Produto)produto)));
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

	public List<MaterialDTO> getMateriais() {
		return materiais;
	}

	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}
	
}
