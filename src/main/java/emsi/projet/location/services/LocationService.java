package emsi.projet.location.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import emsi.projet.location.entities.Location;
import emsi.projet.location.entities.User;
import emsi.projet.location.entities.Voiture;
import emsi.projet.location.repository.LocationRepository;
import emsi.projet.location.repository.UserRepository;
import emsi.projet.location.repository.VoitureRepository;

@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	@Autowired 
	private VoitureRepository voitureRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public long nombreLocation() {
		return this.locationRepository.countTotalLocation();
	}
	
	 
	    public Location createLocation(Location location) {
	    	Optional<Voiture> voiture = voitureRepository.findById(location.getVoiture().getId());
	    	if(voiture.isEmpty()) {
	    		throw new RuntimeException("Voiture not found.");
	    	}
	    	Optional<User> user = userRepository.findById(location.getUser().getId());
	    	if(user.isEmpty()) {
	    		throw new RuntimeException("User not FOund");
	    	}
	    	location.setUser(user.get());
	    	location.setVoiture(voiture.get());
	    	
	        return locationRepository.save(location);
	        
	    }
	    
	    public List<Location> getLocation(){
	    	return locationRepository.findAll();
	    }
	    
	    public void deleteByid(int id) {
	    	 locationRepository.deleteById(id);
	    }
}
	


