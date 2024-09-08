package com.practice.csa.responsedto;

public class ServiceResponseDto {
	private int serviceId;
	private String serviceType;
	private String serviceDescription;
	private double serviceCost;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double d) {
		this.serviceCost = d;
	}

	@Override
	public String toString() {
		return "ServiceResponseDto [serviceId=" + serviceId + ", serviceType=" + serviceType + ", serviceDescription="
				+ serviceDescription + ", serviceCost=" + serviceCost + "]";
	}

}
