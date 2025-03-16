package overcoded.childrenpoints.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import overcoded.childrenpoints.model.PointsHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import overcoded.childrenpoints.model.User;

import java.time.Instant;
import java.util.List;

public interface PointsHistoryRepository extends JpaRepository<PointsHistory, Long> {
    @Query("SELECT ph FROM PointsHistory ph WHERE ph.type = :type AND ph.createdAt >= :startOfDay AND ph.createdAt <= :endOfDay AND ph.challenge IS NOT NULL and ph.user.id = :userId")
    List<PointsHistory> findEarningTodayHistory(@Param("type") String type, @Param("startOfDay") Instant startOfDay, @Param("endOfDay") Instant endOfDay, @Param("userId") long userId);

    @Query("SELECT ph FROM PointsHistory ph WHERE ph.type = :type AND ph.createdAt >= :startOfDay AND ph.createdAt <= :endOfDay AND ph.reward IS NOT NULL and ph.user.id = :userId")
    List<PointsHistory> findRewardHistory(@Param("type") String type, @Param("startOfDay") Instant startOfDay, @Param("endOfDay") Instant endOfDay,  @Param("userId") long userId);

    @Query("SELECT ph FROM PointsHistory ph WHERE ph.type = :type AND ph.createdAt >= :startOfDay AND ph.createdAt <= :endOfDay AND ph.violation IS NOT NULL AND ph.user.id = :userId")
    List<PointsHistory> findViolationHistory(@Param("type") String type, @Param("startOfDay") Instant startOfDay, @Param("endOfDay") Instant endOfDay,  @Param("userId") long userId);

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'deduct' AND ph.user.id = :userId ")
    int getAllTimeUsedPoints(@Param("userId") long userId );

    @Query("SELECT COALESCE(SUM(ph.points), 0) FROM PointsHistory ph WHERE ph.type = 'add' AND ph.createdAt >= :startOfDay AND ph.createdAt <= :endOfDay AND ph.user.id = :userId")
    int getTodayEarnedPoints(@Param("startOfDay") Instant startOfDay, @Param("endOfDay") Instant endOfDay,  @Param("userId") long userId);
}
