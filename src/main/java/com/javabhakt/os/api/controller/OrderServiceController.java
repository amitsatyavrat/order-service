package com.javabhakt.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javabhakt.os.api.dto.TransactionRequest;
import com.javabhakt.os.api.dto.TransactionResponse;
import com.javabhakt.os.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderServiceController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder (@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {
		
		
		return orderService.saveOrder(transactionRequest);
	}
	
}
