package emsi.projet.location.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import emsi.projet.location.entities.Agence;
import emsi.projet.location.entities.Assurance;
import emsi.projet.location.entities.Voiture;
import emsi.projet.location.repository.AgenceRepository;
import emsi.projet.location.repository.AssuranceRepository;
import emsi.projet.location.repository.VoitureRepository;

@Service
public class VoitureService {
	
	@Autowired
	private VoitureRepository voitureRepository;
	
	@Autowired
	private AssuranceRepository assuranceRepository;
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	 /*@PostMapping("/add")
	    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
	    	Optional<Assurance> assurance = assuranceRepository.findById(voiture.getAssurance().getId());
	    	if(assurance.isEmpty()) {
	    		throw new RuntimeException("Assurance not found.");
	    	}
	    	Optional<Agence> agence = agenceRepository.findById(voiture.getAgence().getId());
	    	if(agence.isEmpty()) {
	    		throw new RuntimeException("Agence not FOund");
	    	}
	    	voiture.setAgence(agence.get());
	    	voiture.setAssurance(assurance.get());
	    	
	        Voiture v = voitureRepository.save(voiture);
	        return new ResponseEntity<>(v, HttpStatus.CREATED);
	    }
	    */
	
	    public Voiture createVoiture(@RequestBody Voiture voiture) {
	    	Optional<Assurance> assurance = assuranceRepository.findById(voiture.getAssurance().getId());
	    	if(assurance.isEmpty()) {
	    		throw new RuntimeException("Assurance not found.");
	    	}
	    	Optional<Agence> agence = agenceRepository.findById(voiture.getAgence().getId());
	    	if(agence.isEmpty()) {
	    		throw new RuntimeException("Agence not FOund");
	    	}
	    	voiture.setAgence(agence.get());
	    	voiture.setAssurance(assurance.get());
	    	
	        return voitureRepository.save(voiture);
	        
	    }
}
