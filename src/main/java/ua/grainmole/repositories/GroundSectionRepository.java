package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.grainmole.models.GroundSection;
import ua.grainmole.models.User;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface GroundSectionRepository extends JpaRepository<GroundSection, BigInteger> {
    List<GroundSection> getGroundSectionByUser(User user);
}
