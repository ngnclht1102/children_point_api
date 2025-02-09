package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.PointsHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface PointsHistoryRepository extends JpaRepository<PointsHistory, Long> {
    List<PointsHistory> findByTypeAndCreatedAtAfter(String type, Instant date);

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'deduct'")
    int getAllTimeUsedPoints();

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'add' AND ph.createdAt >= :startOfDay")
    int getTodayEarnedPoints(@Param("startOfDay") Instant startOfDay);
}
