package com.practice.csa.serviceimpl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.practice.csa.entity.Cart;
import com.practice.csa.entity.User;
import com.practice.csa.exception.UsernameNotFoundException;
import com.practice.csa.mapper.UserMapper;
import com.practice.csa.repository.CartRepository;
import com.practice.csa.repository.UserRepository;
import com.practice.csa.requestdto.LoginRequestDto;
import com.practice.csa.requestdto.UserRequest;
import com.practice.csa.responsedto.UserResponse;
import com.practice.csa.security.JWTService;
import com.practice.csa.service.UserService;
import com.practice.csa.utility.ResponseStructure;
import com.practice.csa.utility.UserRole;

@Service
public class UserImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Override
//	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
//        User user = userMapper.mapToUser(userRequest);
//		
//		user = userRepository.save(user);
//		
//		UserResponse userResponse = userMapper.mapToUserResponse(user);
//		
//		ResponseStructure<UserResponse> responseStructure = new ResponseStructure<UserResponse>();
//		responseStructure.setStatuscode(HttpStatus.CREATED.value());
//		responseStructure.setMessage("User Object created successfully!!");
//		responseStructure.setData(userResponse);
//		 
//		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
//	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		User user = userMapper.mapToUser(userRequest);
		user = userRepository.save(user);

		if (user.getUserRole().equals(UserRole.CUSTOMER)) {
			Cart cart = new Cart();
			cart.setUser(user);

			cart = cartRepository.save(cart);
		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<UserResponse>().setStatuscode(HttpStatus.CREATED.value())
						.setMessage("Inserted Successfully").setData(userMapper.mapToUserResponse(user)));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
		return userRepository.findById(userId)
				.map(user -> ResponseEntity.status(HttpStatus.FOUND)
						.body(new ResponseStructure<UserResponse>().setStatuscode(HttpStatus.FOUND.value())
								.setMessage("User Found").setData(userMapper.mapToUserResponse(user))))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(int userId) {

		return userRepository.findById(userId).map(user -> {
			userRepository.delete(user);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<UserResponse>().setStatuscode(HttpStatus.OK.value())
							.setMessage("Service Object deleted successfully!!")
							.setData(userMapper.mapToUserResponse(user)));
		}).orElseThrow(() -> new UsernameNotFoundException("Service object not found"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser() {

		List<UserResponse> responses = userRepository.findAll().stream().map(user -> userMapper.mapToUserResponse(user))
				.toList();

		if (responses.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseStructure<List<UserResponse>>().setStatuscode(HttpStatus.NOT_FOUND.value())
							.setMessage("No User Objects found").setData(responses));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<List<UserResponse>>().setStatuscode(HttpStatus.OK.value())
							.setMessage("User Objects found successfully!!").setData(responses));
		}
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updatedByUserId(int id, UserRequest userRequest) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepository.findByEmail(email).map(existingUser -> {
			// Update the existing user with the values from UserRequest
			existingUser.setUserName(userRequest.getName());
			existingUser.setEmail(userRequest.getEmail());
			existingUser.setPassword(userRequest.getPassword());

			// Save the updated user
			userRepository.save(existingUser);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<UserResponse>().setStatuscode(HttpStatus.OK.value())
							.setMessage("user Object updated successfully!!")
							.setData(userMapper.mapToUserResponse(existingUser)));
		}).orElseThrow(() -> new UsernameNotFoundException("User object not found"));
	}

	public ResponseEntity<ResponseStructure<String>>login(LoginRequestDto loginRequestDto){
		
		String username = loginRequestDto.getUsername();
		String password = loginRequestDto.getPassword();
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(username, password,null);
		Authentication authentication =authenticationManager.authenticate(token);
		if(authentication.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return userRepository.findByEmail(username).map(user ->{ 
				String jwt = jwtService.createJWT(username, password,Duration.ofDays(1));
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<String>()
					.setStatuscode(HttpStatus.OK.value())
					.setMessage("Succesfully Logged")
					.setData(jwt));
			}).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
			
		}else {
			throw new UsernameNotFoundException("User Credential Not Found");
		}
	}
}
