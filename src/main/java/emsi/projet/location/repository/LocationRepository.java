package emsi.projet.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import emsi.projet.location.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query(value = "SELECT COUNT(*) FROM location", nativeQuery = true)
    long countTotalLocation();
	
}
