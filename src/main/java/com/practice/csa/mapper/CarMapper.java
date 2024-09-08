package com.practice.csa.mapper;

import org.springframework.stereotype.Component;

import com.practice.csa.entity.Car;
import com.practice.csa.requestdto.CarRequestDto;
import com.practice.csa.responsedto.CarResponseDto;
@Component
public class CarMapper {

	public Car mapToCar(CarRequestDto request) {
		Car car = new Car();
		car.setCarBrand(request.getBrand());
		car.setCarModel(request.getModel());
		return car;
	}

	public CarResponseDto mapToCarResponse(Car car) {
		CarResponseDto carResponseDto = new CarResponseDto();
		carResponseDto.setId( car.getCarId());
		carResponseDto.setBrand( car.getCarBrand());
		carResponseDto.setModel( car.getCarModel());
		return carResponseDto;
	}

}
