package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

/* CONTROLADOR REST PARA TRABALHAR COM AS ORDERS */
@RestController

/* DEFININDO O CAMINHO DO RECURSO */
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService service;
	
	/* CRIANDO UM END-POINT COM METODO GET PARA O HTTP*/
	@GetMapping
	// PARAMETRIZANDO O TIPO DO CORPO DA RESPOSTA
	public ResponseEntity<List<OrderDTO>> findAll(){
		List<OrderDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
