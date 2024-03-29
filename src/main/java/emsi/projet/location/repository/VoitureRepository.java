package emsi.projet.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import emsi.projet.location.entities.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Integer> {
	
	@Query(value = "SELECT COUNT(*) FROM voiture", nativeQuery = true)
    long countTotalCars();

}
