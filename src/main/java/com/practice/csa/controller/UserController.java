package com.practice.csa.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.csa.requestdto.LoginRequestDto;
import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.security.JWTService;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
		return userService.registerUser(userRequest);
	}

	@GetMapping("/users/{userId}")
	private ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId) {
		return userService.findUserById(userId);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(@PathVariable int userId) {
		return userService.deleteByUserId(userId);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {
		return userService.findAllUser();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(@PathVariable int userId,
			@RequestBody UserRequest userRequest) {
		return userService.updatedByUserId(userId, userRequest);
	}

	/*
	 * @PostMapping("/user-login") public String login() { return
	 * jwtService.createJWT("p@gmail.com", "CUSTOMER", Duration.ofDays(1)); }
	 */

	@PostMapping("/user-login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestBody LoginRequestDto loginRequestDto) {
		return userService.login(loginRequestDto);
	}
}
