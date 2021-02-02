package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.ProductService;

/* CONTROLADOR REST PARA TRABALHAR COM O PRODUTO */
@RestController

/* DEFININDO O CAMINHO DO RECURSO */
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	/* CRIANDO UM END-POINT COM METODO GET PARA O HTTP*/
	@GetMapping
	// PARAMETRIZANDO O TIPO DO CORPO DA RESPOSTA
	public ResponseEntity<List<ProductDTO>> findAll(){
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
