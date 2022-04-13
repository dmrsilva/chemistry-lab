package com.school.chemistrylab.tests;

import com.school.chemistrylab.dto.CompostoDTO;
import com.school.chemistrylab.dto.ProdutoDTO;
import com.school.chemistrylab.entities.Composto;
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
	
	public static CompostoDTO createCompostoDTO() {
		return new CompostoDTO(createComposto());
	}	
	
	public static ProdutoDTO createProdutoDTO() {
		return new ProdutoDTO(createProduto());
	}

}
