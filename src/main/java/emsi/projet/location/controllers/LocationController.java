package emsi.projet.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> supprimerLocation(@RequestBody int id){
		locationService.deleteByid(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("sumlocations")
	public ResponseEntity<Long>getNombreLocation(){
		long num = this.locationService.nombreLocation();
		return new ResponseEntity<>(num, HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Location> modifierLocation(@RequestParam int id, @RequestBody Location updatedLocation) {
	    try {
	        // Assuming you have a service method to update the location
	        Location modifiedLocation = locationService.modifierLocation(id, updatedLocation);

	        if (modifiedLocation != null) {
	            return ResponseEntity.ok(modifiedLocation);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        // Handle exceptions or validation errors
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


	
	
}
