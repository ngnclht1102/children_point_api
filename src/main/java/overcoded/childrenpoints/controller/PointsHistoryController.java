package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.ChallengesRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.repository.RewardsRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@RestController
@RequestMapping("/api/v1/points-history")
public class PointsHistoryController {

    private final PointsHistoryRepository pointsHistoryRepository;

    @Autowired
    public PointsHistoryController(PointsHistoryRepository pointsHistoryRepository) {
        this.pointsHistoryRepository = pointsHistoryRepository;
    }

    @GetMapping("/earned/today")
    public List<PointsHistory> getTodayEarnedHistory(@AuthenticationPrincipal User me) {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = startOfDay.plus(1, ChronoUnit.DAYS).minusNanos(1);
        System.out.println("Getting deduced history for today "+startOfDay + ' '+endOfDay);
        return pointsHistoryRepository.findEarningTodayHistory("add", startOfDay, endOfDay, me.getId());
    }

    @GetMapping("/rewarded/today")
    public List<PointsHistory> getTodayDeducedHistory(@AuthenticationPrincipal User me) {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = startOfDay.plus(1, ChronoUnit.DAYS).minusNanos(1);
        System.out.println("Getting deduced history for today "+startOfDay + ' '+endOfDay);
        return pointsHistoryRepository.findRewardHistory("deduct", startOfDay, endOfDay, me.getId());
    }

    @GetMapping("/violations/today")
    public List<PointsHistory> getTodayViolationHistory(@AuthenticationPrincipal User me) {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = startOfDay.plus(1, ChronoUnit.DAYS).minusNanos(1);
        System.out.println("Getting deduced history for today "+startOfDay + ' '+endOfDay);
        return pointsHistoryRepository.findViolationHistory("deduct", startOfDay, endOfDay, me.getId());
    }
}
