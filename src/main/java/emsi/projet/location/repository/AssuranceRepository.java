package emsi.projet.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emsi.projet.location.entities.Assurance;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Integer> {

}
