package overcoded.childrenpoints.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
@Table(name = "rewards")
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title can't be null")
    private String title;

    @NotNull(message = "Required points can't be null")
    @Min(value = 1, message = "Required points must be at least 1")
    private Long requiredPoints;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt = Instant.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;
}
