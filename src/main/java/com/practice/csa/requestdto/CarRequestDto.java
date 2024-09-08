package com.practice.csa.requestdto;

public class CarRequestDto {
	private String brand;
	private String model;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "CarRequestDto [brand=" + brand + ", model=" + model + "]";
	}

}
