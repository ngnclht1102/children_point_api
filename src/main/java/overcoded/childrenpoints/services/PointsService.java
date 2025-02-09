package overcoded.childrenpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.repository.PointsRepository;

@Service
public class PointsService {
    private final PointsRepository pointsRepository;

    @Autowired
    public PointsService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }
}
