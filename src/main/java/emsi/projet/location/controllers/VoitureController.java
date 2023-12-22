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

import emsi.projet.location.entities.Agence;
import emsi.projet.location.entities.Assurance;
import emsi.projet.location.entities.Voiture;
import emsi.projet.location.repository.AgenceRepository;
import emsi.projet.location.repository.AssuranceRepository;
import emsi.projet.location.repository.VoitureRepository;
import emsi.projet.location.services.VoitureService;

@RestController
@RequestMapping("/voitures")
@CrossOrigin(origins = "http://localhost:4200")
public class VoitureController {

	@Autowired
	private VoitureRepository voitureRepository;
	
	@Autowired
    private AgenceRepository agenceRepository;
	
	@Autowired
	private AssuranceRepository assuranceRepository;
	
	@Autowired
	private VoitureService voitureService;
	
	@GetMapping
    public ResponseEntity<List<Voiture>> getAllVoitures() {
        List<Voiture> voitures = voitureRepository.findAll();
        return new ResponseEntity<>(voitures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable int id) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

        if (optionalVoiture.isPresent()) {
            Voiture voiture = optionalVoiture.get();
            return ResponseEntity.ok(voiture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public Voiture createVoiture(@RequestBody Voiture voiture) {
    	return voitureService.createVoiture(voiture);
    	
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable int id) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

        if (optionalVoiture.isPresent()) {
            voitureRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Voiture> editVoiture(@PathVariable int id, @RequestBody Voiture updatedVoiture) throws Exception {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

        if (optionalVoiture.isPresent()) {
            Voiture existingVoiture = optionalVoiture.get();
            existingVoiture.setMatricule(updatedVoiture.getMatricule());
            existingVoiture.setMarque(updatedVoiture.getMarque());
            existingVoiture.setModele(updatedVoiture.getModele());
            existingVoiture.setPrix(updatedVoiture.getPrix());
            existingVoiture.setDispo(updatedVoiture.isDispo());
            existingVoiture.setCouleur(updatedVoiture.getCouleur());
            existingVoiture.setCarburant(updatedVoiture.getCarburant());
            existingVoiture.setDate(updatedVoiture.getDate());
            existingVoiture.setPhoto(updatedVoiture.getPhoto());
            Assurance assurance = assuranceRepository.findById(updatedVoiture.getAssurance().getId()).orElseThrow(()-> new Exception("invalid Id"));
        	Agence agence = agenceRepository.findById(updatedVoiture.getAgence().getId()).orElseThrow(()-> new Exception("Invalid Id"));
            existingVoiture.setAgence(agence);
            existingVoiture.setAssurance(assurance);
            // Enregistrez les modifications dans la base de données
            voitureRepository.save(existingVoiture);
            return ResponseEntity.ok(existingVoiture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/affecterAg/{voitureId}/{agenceId}")
    public ResponseEntity<String> affecterVoitureAgence(@PathVariable("voitureId") int voitureId, @PathVariable("agenceId") int agenceId) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(voitureId);
        Optional<Agence> optionalAgence = agenceRepository.findById(agenceId);

        if (optionalVoiture.isPresent() && optionalAgence.isPresent()) {
            Agence agence = optionalAgence.get();
            Voiture voiture = optionalVoiture.get();

            // Set the agence on the voiture
            voiture.setAgence(agence);

            // Save the updated voiture back to the database (if needed)
            agence.setNbrV(agence.getNbrV()+1);
            voitureRepository.save(voiture);

            return new ResponseEntity<>("Voiture affectée à l'agence avec succès", HttpStatus.OK);
        }

        return new ResponseEntity<>("Voiture ou Agence non trouvée", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/affecterAss/{voitureId}/{assuranceId}")
    public ResponseEntity<String> affecterVoitureAssurance(@PathVariable("voitureId") int voitureId, @PathVariable("assuranceId") int assuranceId) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(voitureId);
        Optional<Assurance> optionalAssurance = assuranceRepository.findById(assuranceId);

        if (optionalVoiture.isPresent() && optionalAssurance.isPresent()) {
            Assurance assurance = optionalAssurance.get();
            Voiture voiture = optionalVoiture.get();

            // Set the agence on the voiture
            voiture.setAssurance(assurance);
         
            voitureRepository.save(voiture);

            return new ResponseEntity<>("Voiture affectée à l'assurance avec succès", HttpStatus.OK);
        }

        return new ResponseEntity<>("Voiture ou Assurance non trouvée", HttpStatus.NOT_FOUND);
    }

    
}
