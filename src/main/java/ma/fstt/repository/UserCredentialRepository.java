package ma.fstt.repository;

import ma.fstt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String username);
}