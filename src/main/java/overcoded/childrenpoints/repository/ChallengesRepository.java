package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.Challenge;
import overcoded.childrenpoints.model.User;

import java.util.List;
import java.util.Optional;

public interface ChallengesRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findAllByUser(User user);

    Optional<Challenge> findByIdAndUser(long id, User user);
}

