package emsi.projet.location.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.dto.CredentialsDto;
import emsi.projet.location.dto.UserDto;
import emsi.projet.location.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	
	private final UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
		UserDto user = userService.login(credentialsDto);
		return ResponseEntity.ok(user);
	}
	

}
