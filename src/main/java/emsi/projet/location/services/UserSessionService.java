package emsi.projet.location.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emsi.projet.location.entities.User;
import emsi.projet.location.entities.UserSession;
import emsi.projet.location.repository.UserRepository;
import emsi.projet.location.repository.UserSessionRepository;

@Service
public class UserSessionService {
	
	@Autowired
	private UserSessionRepository userSessionRpository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserSession> getAllUsersId(){
		return userSessionRpository.findAllByUserIdNotNull();
	}
	
	public UserSession createSession(UserSession userSession) {
		return userSessionRpository.save(userSession);
	}
	
	public void supprimerSession() {
		 userSessionRpository.deleteAllByUserIdIsNotNull();
	}
	
	public String getUsernameBySessionId() {
		Long userId = userSessionRpository.findStoredUserId();
		if(userId != null) {
			User LoggedUser = userRepository.findById(userId).orElse(null);
			if(LoggedUser != null) {
				return LoggedUser.getLastName();
			}
			else {
				return "user not found";
			}
		}else {
			return "user Session not found";
		}
		
	}
	

}
