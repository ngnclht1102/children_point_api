package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import overcoded.childrenpoints.model.Points;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.Rewards;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.repository.RewardsRepository;
import overcoded.childrenpoints.services.PointsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardsController {
    private final RewardsRepository rewardsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;
    private final PointsRepository pointsRepository;
    private final PointsService pointsService;


    @Autowired
    public RewardsController(RewardsRepository rewardsRepository, PointsHistoryRepository pointsHistoryRepository, PointsRepository pointsRepository, PointsService pointsService) {
        this.rewardsRepository = rewardsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
        this.pointsRepository = pointsRepository;
        this.pointsService = pointsService;
    }

    @GetMapping
    public List<Rewards> getAllRewards(@AuthenticationPrincipal User me) {
        return rewardsRepository.findAllByUserId(me.getId());
    }

    @GetMapping("/in-your-hand")
    public List<Rewards> getRewardsByPoints(@AuthenticationPrincipal User me) {
        Long currentTotalPoint = pointsService.getCurrentPoint(me);

        System.out.println("Current points: " + currentTotalPoint);
        return rewardsRepository.findByRequiredPointsLessThanAndUserId(currentTotalPoint, me.getId());
    }

    @PostMapping("/redeem")
    public Rewards redeem(@RequestParam(value = "reward_id") long rewardId, @AuthenticationPrincipal User me) {
        System.out.println("Starting redeem process for reward ID: " + rewardId);

        // Step 1: Find reward
        Rewards reward = rewardsRepository.findByIdAndUserId(rewardId, me.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward not found"));
        System.out.println("Reward found: " + reward);

        // Step 2: Check if points are sufficient
        var currentTotalPoints = pointsService.getCurrentPoint(me);
        System.out.println("Current points before deduction: " + currentTotalPoints);
        System.out.println("Price of reward: " + reward.getRequiredPoints());

        if (currentTotalPoints < reward.getRequiredPoints()) {
            throw new IllegalArgumentException("Insufficient points to redeem this reward");
        }

        // Step 3: Create history record
        PointsHistory pointsHistory = new PointsHistory();
        pointsHistory.setReward(reward);
        pointsHistory.setPoints(reward.getRequiredPoints());
        pointsHistory.setNote("Redeemed reward - Name: " + reward.getTitle() + ", Price: " + reward.getRequiredPoints());
        pointsHistory.setType("deduct");
        pointsHistory.setUser(me);
        System.out.println("Prepared PointsHistory: " + pointsHistory);

        pointsHistoryRepository.save(pointsHistory);
        System.out.println("Points history saved for reward ID: " + rewardId);

        // Step 4: Update current points
        pointsService.deduct(me, reward.getRequiredPoints());
        System.out.println("Redeem process completed successfully for reward ID: " + rewardId);

        return reward;
    }
}
