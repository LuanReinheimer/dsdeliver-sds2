package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

/* COMPONENTE REGISTRADO PARA INJETAR NO CONTROLADOR */

/* O SERVICE NAO VAI RETORNAR O PRODUTO E SIM UM DTO */
@Service
public class ProductService {
	
	/* RESOLVE A INJECAO DE DEPENDENCIA DE FORMA TRANSPARENTE*/
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true) // GARANTINDO A TRANSACAO DE CONECÇAO COM O DB, USANDO readOnly PARA EVITAR O LOCK-IN DE ESCRITA.
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		/* TRANSFORMAR A LIST PRODUCT EM UMA LIST DE PRODUCTDTO, USANDO LAMBDA */
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}

}
