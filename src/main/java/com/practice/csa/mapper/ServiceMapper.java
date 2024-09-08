package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Service;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;

@Component
public class ServiceMapper {
	
	public Service mapToService(ServiceRequestDto request) {
		Service service = new Service();
		service.setServiceType(request.getServiceType());
		service.setServiceDescription(request.getServicedescription());
		service.setServiceCost(request.getServiceCost());
		return service;
	}

	public ServiceResponseDto mapToServiceResponse(Service service) {
		ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
		serviceResponseDto.setServiceId(service.getServiceId());
		serviceResponseDto.setServiceType(service.getServiceType());
		serviceResponseDto.setServiceDescription(service.getServiceDescription());
		serviceResponseDto.setServiceCost(service.getServiceCost());
		return serviceResponseDto;
	}


}
