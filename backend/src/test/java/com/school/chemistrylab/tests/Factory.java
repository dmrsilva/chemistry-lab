package com.school.chemistrylab.tests;

import com.school.chemistrylab.dto.CompostoDTO;
import com.school.chemistrylab.dto.ProdutoDTO;
import com.school.chemistrylab.entities.Categoria;
import com.school.chemistrylab.entities.Composto;
import com.school.chemistrylab.entities.Material;
import com.school.chemistrylab.entities.Produto;

public class Factory {
	
	public static Composto createComposto() {
		Composto composto = new Composto(1L, "composto", "descricao");
		return composto;
	}
	
	public static Produto createProduto() {
		Produto produto = new Produto(1L, "produto", "descricao");
		produto.getCompostos().add(createComposto());
		return produto;
	}
	
	public static Material createMaterial() {
		Material material = new Material(1L, "material", "descricao");
		return material;
	}
	
	public static Categoria createCategoria() {
		Categoria categoria = new Categoria(1L, "categoria");
		categoria.getMateriais().add(createMaterial());
		categoria.getProdutos().add(createProduto());
		return categoria;
	}
	
	public static CompostoDTO createCompostoDTO() {
		return new CompostoDTO(createComposto());
	}	
	
	public static ProdutoDTO createProdutoDTO() {
		return new ProdutoDTO(createProduto());
	}

}
