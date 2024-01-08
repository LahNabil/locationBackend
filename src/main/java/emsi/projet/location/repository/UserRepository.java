package emsi.projet.location.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import emsi.projet.location.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByLogin(String login);
	
	@Query(value = "SELECT COUNT(*) FROM user", nativeQuery = true)
    long countTotalUser();

}
