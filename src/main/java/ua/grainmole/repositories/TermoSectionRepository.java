package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.grainmole.models.GrainSection;
import ua.grainmole.models.TermoSection;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TermoSectionRepository extends JpaRepository<TermoSection, Integer> {

    @Query("SELECT t FROM TermoSection t WHERE t.grainSection = :grainSection AND t.time <= :timestamp")
    List<TermoSection> getTermoSectionByTimestampAAndGrainSection(
            @Param("timestamp") Timestamp timestamp,
            @Param("grainSection") GrainSection grainSection);
}
