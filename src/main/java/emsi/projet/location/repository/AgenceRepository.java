package emsi.projet.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emsi.projet.location.entities.Agence;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer> {

}
