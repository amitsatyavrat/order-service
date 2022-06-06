package com.javabhakt.os.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabhakt.os.api.dto.PaymentDTO;
import com.javabhakt.os.api.dto.TransactionRequest;
import com.javabhakt.os.api.dto.TransactionResponse;
import com.javabhakt.os.api.entity.Order;
import com.javabhakt.os.api.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String PAYMENT_ENDPOINT_URL;
	
	String message = null;
	
	private Logger log = LoggerFactory.getLogger(OrderService.class);
	
	public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
		log.info("OrderService request : {}",new ObjectMapper().writeValueAsString(transactionRequest));
		Order order = transactionRequest.getOrder();
		PaymentDTO payment = transactionRequest.getPaymentDto();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		//rest call
		PaymentDTO paymentResponse = restTemplate.postForObject(PAYMENT_ENDPOINT_URL, payment, PaymentDTO.class);
		log.info("PaymentService response from OrderService rest call : {}",new ObjectMapper().writeValueAsString(paymentResponse));
		message = paymentResponse.getPaymentStatus().equals("success")?"Payment Successful":"Payment Failed";
		orderRepository.save(order);
		TransactionResponse txResponse = new TransactionResponse();
		txResponse.setAmount(paymentResponse.getAmount());
		txResponse.setOrder(order);
		txResponse.setTransactionId(paymentResponse.getTransactionId());
		txResponse.setMessage(message);
		return txResponse;
	}
}
