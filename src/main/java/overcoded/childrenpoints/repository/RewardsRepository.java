package overcoded.childrenpoints.repository;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.Rewards;

import java.util.List;

public interface RewardsRepository extends JpaRepository<Rewards, Long> {
    List<Rewards> findByRequiredPointsLessThan(@NotNull(message = "Required points can't be null") @Min(value = 1, message = "Required points must be at least 1") Long requiredPoints);
}
