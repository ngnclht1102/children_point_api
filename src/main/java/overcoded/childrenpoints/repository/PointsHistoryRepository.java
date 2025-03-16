package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.PointsHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface PointsHistoryRepository extends JpaRepository<PointsHistory, Long> {
    List<PointsHistory> findByTypeAndCreatedAtAfter(String type, Instant date);

    @Query("SELECT ph FROM PointsHistory ph WHERE ph.type = :type AND ph.createdAt >= :date AND ph.reward IS NOT NULL")
    List<PointsHistory> findRewardHistory(@Param("type") String type, @Param("date") Instant date);

    @Query("SELECT ph FROM PointsHistory ph WHERE ph.type = :type AND ph.createdAt >= :date AND ph.violation IS NOT NULL")
    List<PointsHistory> findViolationHistory(@Param("type") String type, @Param("date") Instant date);

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'deduct' AND ph.user.id = :userId ")
    int getAllTimeUsedPoints(@Param("userId") long userId );

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'add' AND ph.createdAt >= :startOfDay AND ph.user.id = :userId")
    int getTodayEarnedPoints(@Param("startOfDay") Instant startOfDay, @Param("userId") long userId);
}
