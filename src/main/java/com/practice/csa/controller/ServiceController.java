package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.csa.entity.Service;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@Controller
@ResponseBody
public class ServiceController {
	@Autowired
	private ServiceService service;
	
	@PostMapping(value = "/services")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(@RequestBody ServiceRequestDto request) {
		return service.addService(request);

	}
	
	@GetMapping(value = "/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findById(@PathVariable int serviceId) {
		return service.findByServiceId(serviceId);
	}
	
	@PutMapping(value = "/service/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updatedByServiceId(@PathVariable int serviceId, @RequestBody ServiceRequestDto updatedService) {
		return service.updatedByServiceId(serviceId, updatedService);
	}
	
	@DeleteMapping(value = "/services/{serviceId}")
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteById(@PathVariable int serviceId) {
		return service.deleteByServiceId(serviceId);
	}

	@GetMapping(value = "/services")
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAll() {
		return service.findAllService();
	}
}
