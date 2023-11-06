package ua.grainmole.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.grainmole.models.SeedType;
import ua.grainmole.repositories.SeedTypeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeedTypeService {

    private final SeedTypeRepository seedTypeRepository;

    public SeedType returnSeedTypeByName(String seedTypeName) {
        Optional<SeedType> seedType = seedTypeRepository
                .findSeedTypeByName(seedTypeName);
        if (seedType.isPresent()) {
            return seedType.get();
        } else {
            throw new EntityNotFoundException(
                    "Does not found seed type with name: " + seedTypeName);
        }
    }
}
