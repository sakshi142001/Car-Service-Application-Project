package com.practice.csa.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.csa.entity.Car;
import com.practice.csa.exception.CarNotFoundByIdException;
import com.practice.csa.mapper.CarMapper;
import com.practice.csa.repository.CarRepository;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@Service
public class Car_Service_Impl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarMapper carMapper;
	

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> addCar(CarRequestDto carRequestDto) {
		Car car = carMapper.mapToCar(carRequestDto);
		
car = carRepository.save(car);
		
		CarResponseDto carResponse = carMapper.mapToCarResponse(car);
		
		ResponseStructure<CarResponseDto> responseStructure = new ResponseStructure<CarResponseDto>();
		responseStructure.setStatuscode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Car Object created successfully!!");
		responseStructure.setData(carResponse);
		 
		return new ResponseEntity<ResponseStructure<CarResponseDto>>(responseStructure, HttpStatus.CREATED);
		}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId) {
		
		return carRepository.findById(carId)
				.map(car -> ResponseEntity
							 .status(HttpStatus.FOUND)
							 .body(new ResponseStructure<CarResponseDto>()
									 .setStatuscode(HttpStatus.FOUND.value())
									 .setMessage("Car Object found successfully!!")
									 .setData(carMapper.mapToCarResponse(car))))
				.orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateById(int carId, Car carRequestDto) {
		 return carRepository.findById(carId)
			        .map(existingCar -> {
			            // Update the existing car with the values from CarRequest
			            existingCar.setCarModel(carRequestDto.getCarModel());
			            existingCar.setCarBrand(carRequestDto.getCarBrand());
			            

			            // Save the updated car
			            carRepository.save(existingCar);

			            return ResponseEntity
			                .status(HttpStatus.OK)
			                .body(new ResponseStructure<CarResponseDto>()
			                    .setStatuscode(HttpStatus.OK.value())
			                    .setMessage("Car Object updated successfully!!")
			                    .setData(carMapper.mapToCarResponse(existingCar)));
			        })
			        .orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	} 
	

	@Override
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteById(int carId) {
		
		return carRepository.findById(carId)
		        .map(car -> {
		            carRepository.delete(car);
		                return ResponseEntity
		                .status(HttpStatus.OK)
		                .body(new ResponseStructure<CarResponseDto>()
		                    .setStatuscode(HttpStatus.OK.value())
		                    .setMessage("Car Object deleted successfully!!")
		                    .setData(carMapper.mapToCarResponse(car)));
		        })
		        .orElseThrow(() -> new CarNotFoundByIdException("Car object not found"));
	
	}

	@Override
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar() {
		List<CarResponseDto> responses = carRepository.findAll()
	            .stream()
	            .map(car -> carMapper.mapToCarResponse(car))
	            .toList();

	    if (responses.isEmpty()) {
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(new ResponseStructure<List<CarResponseDto>>()
	                        .setStatuscode(HttpStatus.NOT_FOUND.value())
	                        .setMessage("No Car Objects found")
	                        .setData(responses));
	    } else {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ResponseStructure<List<CarResponseDto>>()
	                        .setStatuscode(HttpStatus.OK.value())
	                        .setMessage("Car Objects found successfully!!")
	                        .setData(responses));
	    }
		
	}
}
