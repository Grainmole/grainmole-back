package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.grainmole.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
