package overcoded.childrenpoints.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Data
@Table(name = "points")
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Point can't be null")
    @Min(value = 1, message = "Point must not be negative")
    private long totalPoints;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt = Instant.now();
}
