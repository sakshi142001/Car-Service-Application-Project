package com.practice.csa.responsedto;

import java.util.List;

public class CartResponse {

private int cartId;
	
	List<ServiceResponseDto> serviceResponse;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<ServiceResponseDto> getServiceResponse() {
		return serviceResponse;
	}

	public void setServiceResponse(List<ServiceResponseDto> serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	
	
}
