package overcoded.childrenpoints.dto;

import lombok.Data;

@Data
public class ViolationDto {
    private Long violationId;
    private String title;
    private String description;
    private int deductedPoints;
}
