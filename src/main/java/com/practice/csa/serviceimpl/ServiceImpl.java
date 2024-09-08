package com.practice.csa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.practice.csa.entity.Service;
import com.practice.csa.exception.ServiceNotFoundByIdException;
import com.practice.csa.mapper.ServiceMapper;
import com.practice.csa.repository.ServiceRepository;
import com.practice.csa.requestdto.ServiceRequestDto;
import com.practice.csa.responsedto.ServiceResponseDto;
import com.practice.csa.service.ServiceService;
import com.practice.csa.utility.ResponseStructure;

@org.springframework.stereotype.Service
public class ServiceImpl implements ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> addService(ServiceRequestDto serviceRequest) {
		Service service = serviceMapper.mapToService(serviceRequest);
		
		service = serviceRepository.save(service);
		
		ServiceResponseDto serviceResponse = serviceMapper.mapToServiceResponse(service);
		
		ResponseStructure<ServiceResponseDto> responseStructure = new ResponseStructure<ServiceResponseDto>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Service Object created successfully!!");
		responseStructure.setData(serviceResponse);
		 
		return new ResponseEntity<ResponseStructure<ServiceResponseDto>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> deleteByServiceId(int serviceId) {
	
		return serviceRepository.findById(serviceId)
		        .map(service -> {
		            serviceRepository.delete(service);
		                return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<ServiceResponseDto>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Service Object deleted successfully!!")
		                    .setData(serviceMapper.mapToServiceResponse(service)));
		        })
		        .orElseThrow(() -> new ServiceNotFoundByIdException("Service object not found"));
	
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ServiceResponseDto>>> findAllService() {
		List<ServiceResponseDto> responses = serviceRepository.findAll()
				.stream()
				.map(service -> serviceMapper.mapToServiceResponse(service))
				.toList();
		
		if (responses.isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ResponseStructure<List<ServiceResponseDto>>()
	                        .setStatuscode(HttpStatus.NOT_FOUND.value())
	                        .setMessage("No Service Objects found")
	                        .setData(responses));
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseStructure<List<ServiceResponseDto>>()
	                        .setStatuscode(HttpStatus.OK.value())
	                        .setMessage("Car Objects found successfully!!")
	                        .setData(responses));
	    }
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> findByServiceId(int serviceId) {

		return serviceRepository.findById(serviceId)
		.map(service -> ResponseEntity
				 .status(HttpStatus.FOUND)
				 .body(new ResponseStructure<ServiceResponseDto>()
						 .setStatuscode(HttpStatus.FOUND.value())
						 .setMessage("Service Object found successfully!!")
						 .setData(serviceMapper.mapToServiceResponse(service))))
	.orElseThrow(() -> new ServiceNotFoundByIdException("Service object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<ServiceResponseDto>> updatedByServiceId(int id,ServiceRequestDto serviceRequest) {
	
		return serviceRepository.findById(id)
		        .map(existingService -> {
		            // Update the existing car with the values from CarRequest
		            existingService.setServiceType(serviceRequest.getServiceType());
		            existingService.setServiceCost(serviceRequest.getServiceCost());
		            existingService.setServiceDescription(serviceRequest.getServicedescription());
		            

		            // Save the updated car
		            serviceRepository.save(existingService);

		            return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<ServiceResponseDto>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Service Object updated successfully!!")
		                    .setData(serviceMapper.mapToServiceResponse(existingService)));
		        })
		        .orElseThrow(() -> new ServiceNotFoundByIdException("Car object not found"));
	
	}

}
