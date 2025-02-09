package overcoded.childrenpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.repository.RewardsRepository;

@Service
public class RewardService {
    private final RewardsRepository rewardsRepository;

    @Autowired
    public RewardService(RewardsRepository rewardsRepository) {
        this.rewardsRepository = rewardsRepository;
    }

}
