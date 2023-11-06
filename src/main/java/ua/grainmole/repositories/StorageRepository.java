package ua.grainmole.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.grainmole.models.Storage;
import ua.grainmole.models.User;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    List<Storage> findAllByUser(User user);
}
