package ua.grainmole.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.grainmole.models.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  Optional<Token> findByToken(String token);
}
