package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.Points;

public interface PointsRepository extends JpaRepository<Points, Long> {
    Points findByTotalPoints(int totalPoints);

    Points findFirstByUserId(long userId);
}
