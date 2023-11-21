package ua.grainmole.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.dto.TermoSectionDto;
import ua.grainmole.exceptions.PermissionDeniedException;
import ua.grainmole.mappers.TermoSectionDtoMapper;
import ua.grainmole.models.GrainSection;
import ua.grainmole.models.TermoSection;
import ua.grainmole.models.User;
import ua.grainmole.repositories.GrainSectionRepository;
import ua.grainmole.repositories.TermoSectionRepository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TermoSectionService {
    private final TermoSectionDtoMapper termoSectionDtoMapper;
    private final TermoSectionRepository termoSectionRepository;
    private final GrainSectionRepository grainSectionRepository;
    private final ApplicationAuditAware auditAware;

    public List<List<TermoSectionDto>> getAllTermoSectionsByGrainSection(
            BigInteger grainSectionId,
            Timestamp timestamp) {
        User currentUser = auditAware.returnCurrentAuthenticatedUser();
        GrainSection grainSection = grainSectionRepository.getReferenceById(grainSectionId);
        if (!grainSection.getStorage().getUser().equals(currentUser)) {
            throw new PermissionDeniedException("Current user have not permission to this grain section");
        }
        List<List<TermoSectionDto>> listsOfTermosections = new LinkedList<>();
        List<TermoSection> termoSections =
                termoSectionRepository
                        .getTermoSectionByTimestampAAndGrainSection(timestamp, grainSection);
        Set<Integer> heightLevels = new HashSet<>();
        for (TermoSection level : termoSections) {
            heightLevels.add(level.getHeightLevel());
        }
        for (Integer heightLevel : heightLevels) {
            listsOfTermosections.add(new LinkedList<>());
        }
        for (List<TermoSectionDto> sectionsList : listsOfTermosections) {
            for (Integer heightLevel : heightLevels) {
                for (TermoSection section : termoSections) {
                    if (section.getHeightLevel().equals(heightLevel)) {
                        sectionsList.add(termoSectionDtoMapper.mapEntityToDto(section));
                    }
                }
            }
        }
        return listsOfTermosections;
    }
}
