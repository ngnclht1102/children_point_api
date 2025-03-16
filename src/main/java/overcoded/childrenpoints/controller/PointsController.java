package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/points")
public class PointsController {

    private final PointsRepository pointsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;

    @Autowired
    public PointsController(PointsRepository pointsRepository, PointsHistoryRepository pointsHistoryRepository) {
        this.pointsRepository = pointsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
    }

    @PreAuthorize("hasRole('ROLE_CHILD')")
    @GetMapping("/status")
    public Map<String, Object> getStatus(@AuthenticationPrincipal User me) {
        var currentPoint = pointsRepository.findFirstByUserId(me.getId());
        if (currentPoint == null) {
            currentPoint = new Points();
            currentPoint.setUser(me);
            currentPoint.setTotalPoints(0);
            pointsRepository.save(currentPoint);

        }
        // Count all-time used points (deduct)
        int allTimeUsedPoints = pointsHistoryRepository.getAllTimeUsedPoints(me.getId());

        // Count today's earned points (add)
        Instant startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        int todayEarnedPoints = pointsHistoryRepository.getTodayEarnedPoints(startOfDay, me.getId());

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("currentPoints", currentPoint != null ? currentPoint.getTotalPoints() : 0);
        response.put("allTimeUsedPoints", allTimeUsedPoints);
        response.put("todayEarnedPoints", todayEarnedPoints);


        response.put("status", "OK");
        return response;
    }


}
