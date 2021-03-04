package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

/* COMPONENTE REGISTRADO PARA INJETAR NO CONTROLADOR */

/* O SERVICE NAO VAI RETORNAR O PRODUTO E SIM UM DTO */
@Service
public class OrderService {
	
	/* RESOLVE A INJECAO DE DEPENDENCIA DE FORMA TRANSPARENTE*/
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true) // GARANTINDO A TRANSACAO DE CONECÇAO COM O DB, USANDO readOnly PARA EVITAR O LOCK-IN DE ESCRITA.
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts();
		/* TRANSFORMAR A LIST ORDER EM UMA LIST DE ORDERDTO, USANDO LAMBDA */
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

}
