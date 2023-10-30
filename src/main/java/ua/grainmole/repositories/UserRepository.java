package ua.grainmole.repositories;

import java.util.Optional;

import ua.grainmole.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
