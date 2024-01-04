package emsi.projet.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.entities.UserSession;
import emsi.projet.location.services.UserSessionService;

@RestController
@CrossOrigin
@RequestMapping("/session")
public class UserSessionController {
	
	@Autowired
	private UserSessionService userSessionService;
	
	@GetMapping("/userId")
	public List<UserSession> getUserSession(){
		return userSessionService.getAllUsersId();
	}
	
	

}
