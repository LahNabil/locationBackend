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

import emsi.projet.location.entities.Voiture;
import emsi.projet.location.repository.VoitureRepository;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

	@Autowired
	private VoitureRepository voitureRepository;
	
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
    public ResponseEntity<Voiture> createVoiture(@RequestBody Voiture voiture) {
        Voiture v = voitureRepository.save(voiture);
        return new ResponseEntity<>(v, HttpStatus.CREATED);
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
    public ResponseEntity<Voiture> editVoiture(@PathVariable int id, @RequestBody Voiture updatedVoiture) {
        Optional<Voiture> optionalVoiture = voitureRepository.findById(id);

        if (optionalVoiture.isPresent()) {
            Voiture existingVoiture = optionalVoiture.get();
            // Mettez à jour les propriétés de la voiture existante avec les nouvelles valeurs
            existingVoiture.setMatricule(updatedVoiture.getMatricule());
            existingVoiture.setMarque(updatedVoiture.getMarque());
            existingVoiture.setModele(updatedVoiture.getModele());
            existingVoiture.setPrix(updatedVoiture.getPrix());
            existingVoiture.setDispo(updatedVoiture.isDispo());
            existingVoiture.setCouleur(updatedVoiture.getCouleur());
            existingVoiture.setCarburant(updatedVoiture.getCarburant());
            existingVoiture.setDate(updatedVoiture.getDate());
            // Enregistrez les modifications dans la base de données
            voitureRepository.save(existingVoiture);
            return ResponseEntity.ok(existingVoiture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}