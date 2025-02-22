package overcoded.childrenpoints.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "violations")
@Data
public class Violation {
    public Violation() {
    }

    public Violation(String title, String description, int deductedPoints ) {
        this.title = title;
        this.description = description;
        this.deductedPoints = deductedPoints;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title must be less than 255 characters")
    @Column(nullable = false, length = 255)
    private String title;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Deducted points cannot be null")
    @Min(value = 1, message = "Deducted points must be greater than 0")
    @Column(nullable = false)
    private int deductedPoints;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt = Instant.now();


}
