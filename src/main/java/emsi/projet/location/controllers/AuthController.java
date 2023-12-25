package emsi.projet.location.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.config.UserAuthProvider;
import emsi.projet.location.entities.User;
import emsi.projet.location.entities.Voiture;
import emsi.projet.location.services.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	
	private final UserService userService;
	private final UserAuthProvider userAuthProvider;
	
	
	
	/*
	 * @PostMapping("/login")
	 
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}
	*/
	
	@GetMapping("/")
	public ResponseEntity<List<User>> afficher(){
		List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		//UserDto user = userService.register(user);
		userService.register(user);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.created(URI.create("/users/"+ user.getId())).body(user);
	}
	

}
