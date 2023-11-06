package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.grainmole.models.SeedType;

import java.util.Optional;

@Repository
public interface SeedTypeRepository extends JpaRepository<SeedType,Integer> {

    Optional<SeedType> findSeedTypeByName(String name);
}
