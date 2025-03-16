package overcoded.childrenpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.repository.PointsRepository;

@Service
public class PointsService {
    private final PointsRepository pointsRepository;

    @Autowired
    public PointsService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    public Points getCurrentRecord(User me) {
        var currentPoint = pointsRepository.findFirstByUserId(me.getId());
        if (currentPoint == null) {
            currentPoint = new Points();
            currentPoint.setUser(me);
            currentPoint.setTotalPoints(0);
            pointsRepository.save(currentPoint);
        }
        return currentPoint;
    }

    public long getCurrentPoint(User me) {
        var currentPoint = getCurrentRecord(me);
        return currentPoint.getTotalPoints();
    }

    public void deduct(User me, long deducePoint) {
        var currentPoint = getCurrentRecord(me);
        long newPoint = currentPoint.getTotalPoints() - deducePoint;
        currentPoint.setTotalPoints(newPoint);
        this.pointsRepository.save(currentPoint);
    }

    public void increase(User me, long bonusPoint) {
        var currentPoint = getCurrentRecord(me);
        long newPoint = currentPoint.getTotalPoints() + bonusPoint;
        currentPoint.setTotalPoints(newPoint);
        this.pointsRepository.save(currentPoint);
    }
}
