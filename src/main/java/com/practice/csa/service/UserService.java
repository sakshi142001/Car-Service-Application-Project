package com.practice.csa.service;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.practice.csa.requestdto.LoginRequestDto;
import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.utility.ResponseStructure;

public interface UserService {

	// public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest
	// userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest);

	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int userId);

	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser();

	//public ResponseEntity<ResponseStructure<UserResponse>> findByUserId(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(int id, UserRequest userRequest);
	
	public ResponseEntity<ResponseStructure<String>> login(LoginRequestDto loginRequestDto);
}
