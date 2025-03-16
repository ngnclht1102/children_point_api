package overcoded.childrenpoints.repository;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.Rewards;

import java.util.List;
import java.util.Optional;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
    List<Rewards> findByRequiredPointsLessThanAndUserId(Long requiredPoints, long userId);

    List<Rewards> findAllByUserId(long userId);

    Optional<Rewards> findByIdAndUserId(long id, long userId);
}
