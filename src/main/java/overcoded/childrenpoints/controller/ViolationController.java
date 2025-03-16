package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.dto.ViolationDto;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.model.Violation;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.services.ViolationServices;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/violations")
public class ViolationController {

    private final ViolationServices violationService;
    private final PointsRepository pointsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;

    @Autowired
    public ViolationController(ViolationServices violationService,
                               PointsRepository pointsRepository,
                               PointsHistoryRepository pointsHistoryRepository) {
        this.violationService = violationService;
        this.pointsRepository = pointsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
    }


    @GetMapping
    public List<Violation> getAllViolations(@AuthenticationPrincipal User me) {
        return violationService.getAllViolations(me);
    }

    @PostMapping("/apply")
    public Violation applyViolation(@RequestBody ViolationDto request, @AuthenticationPrincipal User me) {
        Violation violation = violationService.getViolationToDeduct(request, me);
        violationService.deductPointsAndSaveHistory(violation, me);
        return violation;
    }
}