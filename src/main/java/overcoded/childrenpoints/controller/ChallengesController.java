package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import overcoded.childrenpoints.model.Challenge;
import overcoded.childrenpoints.model.PointsHistory;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.repository.ChallengesRepository;
import overcoded.childrenpoints.repository.PointsHistoryRepository;
import overcoded.childrenpoints.repository.PointsRepository;
import overcoded.childrenpoints.services.PointsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/challenges")
public class ChallengesController {

    private final ChallengesRepository challengesRepository;
    private final PointsRepository pointsRepository;
    private final PointsHistoryRepository pointsHistoryRepository;
    private final PointsService pointsService;

    @Autowired
    public ChallengesController(
            ChallengesRepository challengesRepository,
            PointsRepository pointsRepository,
            PointsHistoryRepository pointsHistoryRepository,
            PointsService pointsService) {
        this.challengesRepository = challengesRepository;
        this.pointsRepository = pointsRepository;
        this.pointsHistoryRepository = pointsHistoryRepository;
        this.pointsService = pointsService;
    }

    @GetMapping
    public List<Challenge> getCurrentPoint(@AuthenticationPrincipal User me) {
        return challengesRepository.findAllByUser(me);
    }

    @PostMapping("/finish")
    public String markChallengeAsFinished(@RequestParam("challengeId") Long challengeId, @AuthenticationPrincipal User me) {
        // Retrieve the challenge by ID or throw an exception if not found
        var challenge = challengesRepository.findByIdAndUser(challengeId, me)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Challenge not found"));

        // Retrieve the points entry (assuming there's only one) or throw an exception if the table is empty
        var points = pointsService.getCurrentRecord(me);

        // Update the total points by adding the earned points from the completed challenge
        points.setTotalPoints(points.getTotalPoints() + challenge.getEarnedPoints());
        pointsRepository.save(points); // Save the updated points entry

        // Create a new points history record for the completed challenge
        var history = new PointsHistory();
        history.setPoints(challenge.getEarnedPoints()); // Set the earned points for the record
        history.setNote("Challenge completed: " + challenge.getTitle()); // Add a note describing the entry
        history.setType("add"); // Set the transaction type as "add"
        history.setChallenge(challenge); // Associate the history record with the completed challenge
        history.setUser(me);
        pointsHistoryRepository.save(history); // Save the points history record

        // Return the new total points
        return "{\"message\": \"Challenge marked as finished\", \"newTotalPoints\": " + points.getTotalPoints() + "}";
    }
    
    
}
