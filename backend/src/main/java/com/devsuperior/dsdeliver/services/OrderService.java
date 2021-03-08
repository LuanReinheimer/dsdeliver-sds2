package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

/* COMPONENTE REGISTRADO PARA INJETAR NO CONTROLADOR */

/* O SERVICE NAO VAI RETORNAR O PRODUTO E SIM UM DTO */
@Service
public class OrderService {

	/* RESOLVE A INJECAO DE DEPENDENCIA DE FORMA TRANSPARENTE */
	@Autowired
	private OrderRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true) // GARANTINDO A TRANSACAO DE CONECÇAO COM O DB, USANDO readOnly PARA EVITAR O
									// LOCK-IN DE ESCRITA.
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrdersWithProducts();
		/* TRANSFORMAR A LIST ORDER EM UMA LIST DE ORDERDTO, USANDO LAMBDA */
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	// VAMOS CRIAR UM ENDPOINS [POST]/ORDERS, ONDE VAMOS INSERIR NO DB, UM NOVO
	// PEDIDO COM O METODO (POST)
	// VAMOS ALTERAR O DB, POR ISTO NAO E MAIS (READONLY)

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		/*
		 * ESTANCIANDO UM PEDIDO, DEPOIS VARRER COM O (FOR) TODOS OS PRODUTOD QUE ESTAO
		 * NO (DTO), DEPOIS ESTANCIANDO UM PRODUTO COM BASE DO ID DO (P.ID) E
		 * ADICIONANDO ESTE PRODUTO NA LISTA DE PRODUTOS DO ORDER ESTANCIADO ANTES
		 */
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(),
				OrderStatus.PENDING);
		// ASSOCIANDO O PRODUTO ADICIONADO AO BD
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		// SALVANDO O PRODUTO NO DB
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
// ENDPOINT CRIADO PARA QUANDO O PRODUTO FOR CLICADO COMO ENTREGUE
	
	@Transactional // TODA TRANSACTIONAL SE REFERE A ALTERAÇÃO NO DB
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDTO(order);

	}

}
