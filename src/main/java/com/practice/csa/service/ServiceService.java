package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.entity.Service;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface ServiceService {

public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto serviceRequest);
	
	public ResponseEntity<ResponseStructure<ServiceResponseDto>>  deleteByServiceId(int serviceId);
	
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllService();
	
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findByServiceId(int serviceId);
	
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updatedByServiceId(int serviceId, ServiceRequestDto updatedService);

}
