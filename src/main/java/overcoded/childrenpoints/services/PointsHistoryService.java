package overcoded.childrenpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.repository.PointsHistoryRepository;

@Service
public class PointsHistoryService {

    private  final PointsHistoryRepository pointsHistoryRepository;

    @Autowired
    public PointsHistoryService(PointsHistoryRepository pointsHistoryRepository) {
        this.pointsHistoryRepository = pointsHistoryRepository;
    }
}
