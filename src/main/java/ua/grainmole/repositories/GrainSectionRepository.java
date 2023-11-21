package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.grainmole.models.GrainSection;
import ua.grainmole.models.Storage;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface GrainSectionRepository extends JpaRepository<GrainSection, BigInteger> {
    List<GrainSection> getGrainSectionsByStorage(Storage storage);
}
