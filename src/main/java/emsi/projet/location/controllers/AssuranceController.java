package emsi.projet.location.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.entities.Assurance;
import emsi.projet.location.repository.AssuranceRepository;

@RestController
@RequestMapping("/assurances")
@CrossOrigin(origins = "http://localhost:4200")
public class AssuranceController {
	
	@Autowired
	private AssuranceRepository assuranceRepository;
	
	@GetMapping
	public ResponseEntity<List<Assurance>> getAllAssurances() {
        List<Assurance> assurances = assuranceRepository.findAll();
        return new ResponseEntity<>(assurances, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Assurance> getBatterieById(@PathVariable int id) {
	    Optional<Assurance> optionalAssurance = assuranceRepository.findById(id);

	    if (optionalAssurance.isPresent()) {
	        Assurance a = optionalAssurance.get();
	        return ResponseEntity.ok(a);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/add")
	public ResponseEntity<Assurance> createAssurance(@RequestBody Assurance assurance){
		Assurance a = assuranceRepository.save(assurance);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAssurance(@PathVariable int id) {
        Optional<Assurance> optionalAssurance = assuranceRepository.findById(id);

        if (optionalAssurance.isPresent()) {
            assuranceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	 @PutMapping("/edit/{id}")
	    public ResponseEntity<Assurance> editAssurance(@PathVariable int id, @RequestBody Assurance updatedAssurance) {
	        Optional<Assurance> optionalAssurance = assuranceRepository.findById(id);

	        if (optionalAssurance.isPresent()) {
	            Assurance existingAssurance = optionalAssurance.get();
	            // Mettez à jour les propriétés de l'assurance existante avec les nouvelles valeurs
	            existingAssurance.setNom(updatedAssurance.getNom());
	            existingAssurance.setPrix(updatedAssurance.getPrix());
	            existingAssurance.setType(updatedAssurance.getType());
	            existingAssurance.setDate_debut(updatedAssurance.getDate_debut());
	            existingAssurance.setDate_fin(updatedAssurance.getDate_fin());
	            // Enregistrez les modifications dans la base de données
	            assuranceRepository.save(existingAssurance);
	            return ResponseEntity.ok(existingAssurance);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
