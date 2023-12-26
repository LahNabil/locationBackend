package emsi.projet.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.entities.Location;
import emsi.projet.location.services.LocationService;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@GetMapping("/")
	public ResponseEntity<List<Location>> getLocation(){
		List<Location> locations = locationService.getLocation();
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Location> ajouterLocation(@RequestBody Location location){
		Location l = locationService.createLocation(location);
		return new ResponseEntity<>(l, HttpStatus.CREATED);
	}
	
}
