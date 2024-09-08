package com.practice.csa.requestdto;

public class ServiceRequestDto {

	private String serviceType;
	private String servicedescription;
	private int serviceCost;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServicedescription() {
		return servicedescription;
	}

	public void setServicedescription(String servicedescription) {
		this.servicedescription = servicedescription;
	}

	public int getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(int serviceCost) {
		this.serviceCost = serviceCost;
	}

	@Override
	public String toString() {
		return "ServiceRequestDto [serviceType=" + serviceType + ", servicedescription=" + servicedescription
				+ ", serviceCost=" + serviceCost + "]";
	}

}
