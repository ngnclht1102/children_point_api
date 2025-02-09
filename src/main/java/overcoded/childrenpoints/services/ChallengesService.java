package overcoded.childrenpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.repository.ChallengesRepository;

@Service
public class ChallengesService {

    private final ChallengesRepository challengesRepository;

    @Autowired
    public ChallengesService(ChallengesRepository challengesRepository) {
        this.challengesRepository = challengesRepository;
    }
}
