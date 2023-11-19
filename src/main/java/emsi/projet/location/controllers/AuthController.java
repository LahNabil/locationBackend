package emsi.projet.location.controllers;


import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.config.UserAuthProvider;
import emsi.projet.location.dto.CredentialsDto;
import emsi.projet.location.dto.SignUpDto;
import emsi.projet.location.dto.UserDto;
import emsi.projet.location.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
		UserDto user = userService.register(signUpDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.created(URI.create("/users/"+ user.getId())).body(user);
	}
	

}
