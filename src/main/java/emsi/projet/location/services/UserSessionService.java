package emsi.projet.location.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emsi.projet.location.entities.UserSession;
import emsi.projet.location.repository.UserSessionRepository;

@Service
public class UserSessionService {
	
	@Autowired
	private UserSessionRepository userSessionRpository;
	
	public List<UserSession> getAllUsersId(){
		return userSessionRpository.findAllByUserIdNotNull();
	}
	
	public UserSession createSession(UserSession userSession) {
		return userSessionRpository.save(userSession);
	}
	 
	

}
