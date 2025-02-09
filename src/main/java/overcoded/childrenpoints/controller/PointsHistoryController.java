package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.ChallengesRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.repository.RewardsRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public List<PointsHistory> getTodayEarnedHistory() {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        return pointsHistoryRepository.findByTypeAndCreatedAtAfter("add", startOfDay);
    }

    @GetMapping("/deduced/today")
    public List<PointsHistory> getTodayDeducedHistory() {
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        System.out.println("Getting deduced history for today "+startOfDay);
        return pointsHistoryRepository.findByTypeAndCreatedAtAfter("deduct", startOfDay);
    }
}
