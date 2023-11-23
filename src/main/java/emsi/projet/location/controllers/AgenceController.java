package emsi.projet.location.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emsi.projet.location.entities.Agence;
import emsi.projet.location.repository.AgenceRepository;

@RestController
@RequestMapping("/agences")
public class AgenceController {
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	@GetMapping
	public ResponseEntity<List<Agence>> afficherAgence(){
		List<Agence> agences = agenceRepository.findAll();
		return new ResponseEntity<>(agences, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Agence> ajouterAgence(@RequestBody Agence a){
		Agence assurance = agenceRepository.save(a);
		return new ResponseEntity<>(assurance, HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAgence(@PathVariable int id) {
        Optional<Agence> optionalAgence = agenceRepository.findById(id);

        if (optionalAgence.isPresent()) {
        	agenceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	 @PutMapping("/edit/{id}")
	    public ResponseEntity<Agence> editAgence(@PathVariable int id, @RequestBody Agence a) {
	        Optional<Agence> optionalAgence = agenceRepository.findById(id);

	        if (optionalAgence.isPresent()) {
	            Agence exAgence = optionalAgence.get();
	            // Mettez à jour les propriétés de l'assurance existante avec les nouvelles valeurs
	            exAgence.setVille(a.getVille());
	            exAgence.setAdresse(a.getAdresse());
	            exAgence.setNom(a.getNom());
	            exAgence.setCa(a.getCa());
	            exAgence.setNbrV(a.getNbrV());
	            // Enregistrez les modifications dans la base de données
	            agenceRepository.save(exAgence);
	            return ResponseEntity.ok(exAgence);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

}
