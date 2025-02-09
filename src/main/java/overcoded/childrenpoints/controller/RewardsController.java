package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.Rewards;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.repository.RewardsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardsController {
    private final RewardsRepository rewardsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;
    private final PointsRepository pointsRepository;

    @Autowired
    public RewardsController(RewardsRepository rewardsRepository, PointsHistoryRepository pointsHistoryRepository, PointsRepository pointsRepository) {
        this.rewardsRepository = rewardsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
        this.pointsRepository = pointsRepository;
    }

    @GetMapping
    public List<Rewards> getAllRewards() {
        return rewardsRepository.findAll();
    }

    @GetMapping("/in-your-hand")
    public List<Rewards> getRewardsByPoints() {
        var currentPoints = pointsRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Points table is empty"));
        System.out.println("Current points before deduction: " + currentPoints.getTotalPoints());
        return rewardsRepository.findByRequiredPointsLessThan(currentPoints.getTotalPoints());
    }

    @PostMapping("/redeem")
    public Rewards redeem(@RequestParam(value = "reward_id") long rewardId) {
        System.out.println("Starting redeem process for reward ID: " + rewardId);

        // Step 1: Find reward
        Rewards reward = rewardsRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward not found"));
        System.out.println("Reward found: " + reward);

        // Step 2: Check if points are sufficient
        var currentPoints = pointsRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Points table is empty"));
        System.out.println("Current points before deduction: " + currentPoints.getTotalPoints());
        System.out.println("Price of reward: " + reward.getRequiredPoints());

        if (currentPoints.getTotalPoints() < reward.getRequiredPoints()) {
            throw new IllegalArgumentException("Insufficient points to redeem this reward");
        }

        // Step 3: Create history record
        PointsHistory pointsHistory = new PointsHistory();
        pointsHistory.setReward(reward);
        pointsHistory.setPoints(reward.getRequiredPoints());
        pointsHistory.setNote("Redeemed reward - Name: " + reward.getTitle() + ", Price: " + reward.getRequiredPoints());
        pointsHistory.setType("deduct");
        System.out.println("Prepared PointsHistory: " + pointsHistory);

        pointsHistoryRepository.save(pointsHistory);
        System.out.println("Points history saved for reward ID: " + rewardId);

        // Step 4: Update current points
        currentPoints.setTotalPoints(currentPoints.getTotalPoints() - reward.getRequiredPoints());
        pointsRepository.save(currentPoints);

        System.out.println("Updated points after deduction: " + currentPoints.getTotalPoints());
        System.out.println("Redeem process completed successfully for reward ID: " + rewardId);

        return reward;
    }
}
