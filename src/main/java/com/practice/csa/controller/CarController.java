package com.practice.csa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.csa.entity.Car;
import com.practice.csa.mapper.CarMapper;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
import com.practice.csa.service.CarService;
import com.practice.csa.utility.ResponseStructure;

@Controller
@ResponseBody
public class CarController {

	@Autowired
	private CarService carService;

    //@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/cars")
	public ResponseEntity<ResponseStructure<CarResponseDto>> addCar(@RequestBody CarRequestDto car) {
		return carService.addCar(car);

	}

	@GetMapping(value = "/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> findCarById(@PathVariable int carId) {
		return carService.findCarById(carId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping(value = "/cars/{carId}")
	public ResponseEntity<ResponseStructure<CarResponseDto>> updateById(@PathVariable int carId, @RequestBody Car updatedCar) {
		return carService.updateById(carId, updatedCar);
	}

	@DeleteMapping(value = "/cars/{carId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<CarResponseDto>> deleteById(@PathVariable int carId) {
		return carService.deleteById(carId);
	}

	@GetMapping(value = "/cars")
	public ResponseEntity<ResponseStructure<List<CarResponseDto>>> findAllCar() {
		return carService.findAllCar();
	}

}
