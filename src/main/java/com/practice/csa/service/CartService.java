package com.practice.csa.service;

import org.springframework.http.ResponseEntity;

import com.practice.csa.responsedto.CartResponse;
import com.practice.csa.utility.ResponseStructure;

public interface CartService {
	public ResponseEntity<ResponseStructure<CartResponse>> addServiceToCart(int serviceId);
}
