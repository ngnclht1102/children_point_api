package overcoded.childrenpoints.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import overcoded.childrenpoints.dto.ViolationDto;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.model.Violation;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.repository.ViolationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationServices {
    private ViolationRepository violationRepository;
    private PointsRepository pointsRepository;
    private PointsHistoryRepository pointsHistoryRepository;

    @Autowired
    public void ViolationService(ViolationRepository violationRepository, PointsRepository pointsRepository,
                                 PointsHistoryRepository pointsHistoryRepository) {
        this.violationRepository = violationRepository;
        this.pointsRepository = pointsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
    }

    public List<Violation> getAllViolations(User me) {
        return violationRepository.findAllByUser(me);
    }

    public Optional<Violation> getViolationById(Long id) {
        return violationRepository.findById(id);
    }

    public Violation createViolation(Violation violation) {
        return violationRepository.save(violation);
    }

    public Violation getViolationToDeduct(ViolationDto request, User me) {
        Violation violation;
        if (request.getViolationId() != null) {
            Optional<Violation> existingViolation = this.getViolationById(request.getViolationId());
            if (existingViolation.isPresent()) {
                violation = existingViolation.get();
            } else {
                violation = this.createViolation(new Violation(request.getTitle(), request.getDescription(), request.getDeductedPoints(), me));
            }
        } else {
            violation = this.createViolation(new Violation(request.getTitle(), request.getDescription(), request.getDeductedPoints(), me));
        }
        return violation;
    }

    public void deductPointsAndSaveHistory(Violation violation, User me) {
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
        history.setUser(me);
        pointsHistoryRepository.save(history);

        System.out.println("Updated Points: " + points.getTotalPoints());
        System.out.println("History saved.");
    }
}
