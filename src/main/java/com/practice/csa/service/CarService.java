package com.practice.csa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.utility.ResponseStructure;

public interface CarService {

	ResponseEntity<ResponseStructure<CarResponseDto>> addCar(CarRequestDto car);

	ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(int carId);

	ResponseEntity<ResponseStructure<CarResponseDto>> updateById(int carId, Car updatedCar);

	ResponseEntity<ResponseStructure<CarResponseDto>> deleteById(int carId);

	ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar();

}
