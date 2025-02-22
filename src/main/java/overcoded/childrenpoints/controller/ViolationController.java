package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.dto.ViolationDto;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
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
    public List<Violation> getAllViolations() {
        return violationService.getAllViolations();
    }

    @PostMapping("/apply")
    public Violation applyViolation(@RequestBody ViolationDto request) {
        Violation violation;
        if (request.getViolationId() != null) {
            Optional<Violation> existingViolation = violationService.getViolationById(request.getViolationId());
            if (existingViolation.isPresent()) {
                violation = existingViolation.get();
            } else {
                violation = violationService.createViolation(new Violation(request.getTitle(), request.getDescription(), request.getDeductedPoints()));
            }
        } else {
            violation = violationService.createViolation(new Violation(request.getTitle(), request.getDescription(), request.getDeductedPoints()));
        }

        deductPointsAndSaveHistory(violation);
        return violation;
    }

    // 📌 **3. Deduct points & save history**
    private void deductPointsAndSaveHistory(Violation violation) {
        System.out.println("Deducting points for violation: " + violation.getTitle());

        // Fetch current points (assuming a single record for now)
        Points points = pointsRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Points table is empty"));

        long currentPoints = points.getTotalPoints();
        points.setTotalPoints(Math.max(0, currentPoints - violation.getDeductedPoints())); // Ensure points don’t go negative
        pointsRepository.save(points);

        // Save history
        PointsHistory history = new PointsHistory();
        history.setType("deduct");
        history.setPoints(violation.getDeductedPoints());
        history.setNote("Violation applied: " + violation.getTitle() + " (-" + violation.getDeductedPoints() + " points)");
        history.setViolation(violation);
        pointsHistoryRepository.save(history);

        System.out.println("Updated Points: " + points.getTotalPoints());
        System.out.println("History saved.");
    }
}