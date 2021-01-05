package com.devsuperior.dsdeliver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.services.ProductService;

@RestController // Seta que o controlador utilizará Rest
@RequestMapping(value = "/products") // Mapeia o caminho do recurso
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll(){ // ResponseEntity é um tipo especial do Spring que encapsula uma reposta HTTP
		
		List<ProductDTO> list = service.findAll();
		
		// Cria resposta com o código HTTP 200, mostrando que a requisição deu certo.
		return ResponseEntity.ok().body(list);
	}
	
}
