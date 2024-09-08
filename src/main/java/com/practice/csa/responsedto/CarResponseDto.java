package com.practice.csa.responsedto;

public class CarResponseDto {

	private int id;
	private String brand;
	private String model;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return "CarResponseDto [id=" + id + ", brand=" + brand + ", model=" + model + "]";
	}

}
