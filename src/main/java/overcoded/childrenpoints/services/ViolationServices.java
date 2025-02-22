package overcoded.childrenpoints.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import overcoded.childrenpoints.model.Violation;
import overcoded.childrenpoints.repository.ViolationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationServices {
    private  ViolationRepository violationRepository;

    @Autowired
    public void ViolationService(ViolationRepository violationRepository) {
        this.violationRepository = violationRepository;
    }

    public List<Violation> getAllViolations() {
        return violationRepository.findAll();
    }

    public Optional<Violation> getViolationById(Long id) {
        return violationRepository.findById(id);
    }

    public Violation createViolation(Violation violation) {
        return violationRepository.save(violation);
    }

    public void deleteViolation(Long id) {
        violationRepository.deleteById(id);
    }
}
