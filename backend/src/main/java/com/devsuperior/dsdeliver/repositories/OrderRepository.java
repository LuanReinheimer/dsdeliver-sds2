package com.devsuperior.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Order;

/*JPA REPOSITORI E UMA INTERFACE JA DO SPRING, ELA TRAS ALGUMAS IMPLEMENTACOES
 * PADRAO BUSCAR, SALVAR, DELETAR E ATUALIZAR DADOS CONFORME A ENTIDADE QUE VOCE PRECISAR, DEFININDO
 * DENTRO DOS <> TIPOS PARAMETRIZADOS. */

/*ASSIM NOSSO OBJETO DO TIPO PRODUCTREPOSITORY, ELE VAI TER TODAS OPERACOES BASICAS PARA ACESSAR O DB
 * RELACIONADO A ENTIDADE PRODUCT. */
public interface OrderRepository extends JpaRepository<Order, Long>{

}
