package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.Challenge;

public interface ChallengesRepository extends JpaRepository<Challenge, Long> {
}

