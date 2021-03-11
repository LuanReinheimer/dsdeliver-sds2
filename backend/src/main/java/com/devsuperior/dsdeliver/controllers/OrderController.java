package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

/* CONTROLADOR REST PARA TRABALHAR COM AS ORDERS */
@RestController

/* DEFININDO O CAMINHO DO RECURSO */
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	/* CRIANDO UM END-POINT COM METODO GET PARA O HTTP */
	@GetMapping
	// PARAMETRIZANDO O TIPO DO CORPO DA RESPOSTA
	public ResponseEntity<List<OrderDTO>> findAll() {
		List<OrderDTO> list = service.findAll();
		return ResponseEntity.ok().body(list); // RETORNANDO A RESPOSTA NO CORPO
	}

	// CRIAR UM ENDPOINT PARA SALVAR
	@PostMapping
	public ResponseEntity<OrderDTO> insert(
			@RequestBody OrderDTO dto) {/*
										 * VAI CONVERTER O JSON RECEBIDO EM UM OBJETO JAVA (OrderDTO)
										 */
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri(); /*
							 * CHAMADA CRIADA PARA CHAMR A URI, QUE CORRSPONDE AO / RECURSO CRIADO
							 */
		return ResponseEntity.created(uri).body(dto);

	}

	// ENDPOINT CRIADO PARA QUANDO O PRODUTO FOR CLICADO COMO ENTREGUE
	@PutMapping("/{id}/delivered")
	public ResponseEntity<OrderDTO> SetDelivered(
			@PathVariable Long id) { /*
										 * ANOTATION USADA PARA QUE O ID PASSADO NO PARAMETRO CASE COM ID PASSADO NA URL
										 */
		OrderDTO dto = service.setDelivered(id);
		return ResponseEntity.ok().body(dto);
	}

}
