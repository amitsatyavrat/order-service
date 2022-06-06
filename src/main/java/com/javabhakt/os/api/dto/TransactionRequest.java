package com.javabhakt.os.api.dto;

import com.javabhakt.os.api.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

	private Order order;
	private PaymentDTO paymentDto;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public PaymentDTO getPaymentDto() {
		return paymentDto;
	}
	public void setPaymentDto(PaymentDTO paymentDto) {
		this.paymentDto = paymentDto;
	}
	
	

}
