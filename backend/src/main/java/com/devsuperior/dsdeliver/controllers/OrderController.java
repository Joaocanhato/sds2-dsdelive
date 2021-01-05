package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.OrderService;
import com.devsuperior.dsdeliver.services.ProductService;

@RestController // Seta que o controlador utilizará Rest
@RequestMapping(value = "/orders") // Mapeia o caminho do recurso
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll(){ // ResponseEntity é um tipo especial do Spring que encapsula uma reposta HTTP
		
		List<OrderDTO> list = service.findAll();
		
		// Cria resposta com o código HTTP 200, mostrando que a requisição deu certo.
		return ResponseEntity.ok().body(list);
	}
	
}
