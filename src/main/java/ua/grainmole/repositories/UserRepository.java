package ua.grainmole.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.grainmole.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}