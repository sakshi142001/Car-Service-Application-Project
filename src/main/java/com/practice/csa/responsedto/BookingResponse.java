package com.practice.csa.responsedto;

import java.util.List;

public class BookingResponse {
	
	private int id;

	private CarResponseDto car;

	private List <ServiceResponseDto> services;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarResponseDto getCar() {
		return car;
	}

	public void setCar(CarResponseDto car) {
		this.car = car;
	}

	public List<ServiceResponseDto> getServices() {
		return services;
	}

	public void setServices(List<ServiceResponseDto> services) {
		this.services = services;
	}

}
