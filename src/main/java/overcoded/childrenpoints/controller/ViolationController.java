package overcoded.childrenpoints.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import overcoded.childrenpoints.dto.ViolationDto;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.model.Violation;
import overcoded.childrenpoints.services.ViolationServices;

import java.util.List;

@RestController
@RequestMapping("/api/v1/violations")
public class ViolationController {

    private final ViolationServices violationService;

    @Autowired
    public ViolationController(ViolationServices violationService) {
        this.violationService = violationService;
    }


    @GetMapping
    public List<Violation> getAllViolations(@AuthenticationPrincipal User me) {
        return violationService.getAllViolations(me);
    }

    @PostMapping("/apply")
    public Violation applyViolation(@RequestBody ViolationDto request, @AuthenticationPrincipal User me) {
        Violation violation = violationService.getViolationToDeduct(request, me);
        violationService.deductPointsAndSaveHistory(violation, me);
        return violation;
    }
}