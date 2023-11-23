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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        Map<Integer, List<TermoSectionDto>> heightLevelToTermoSections = new HashMap<>();
        List<TermoSection> termoSections =
                termoSectionRepository
                        .getTermoSectionByTimestampAAndGrainSection(timestamp, grainSection);

        for (TermoSection section : termoSections) {
            int heightLevel = section.getHeightLevel();
            if (!heightLevelToTermoSections.containsKey(heightLevel)) {
                heightLevelToTermoSections.put(heightLevel, new LinkedList<>());
            }

            heightLevelToTermoSections.get(heightLevel).add(termoSectionDtoMapper.mapEntityToDto(section));
        }

        List<List<TermoSectionDto>> listOfTermoSections = new LinkedList<>();
        for (Map.Entry<Integer, List<TermoSectionDto>> entry : heightLevelToTermoSections.entrySet()) {
            listOfTermoSections.add(entry.getValue());
        }

        return listOfTermoSections;
    }

}
