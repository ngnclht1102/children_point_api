package overcoded.childrenpoints.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "points_history")
@Data
public class PointsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long points;


    @NotBlank(message = "Note cannot be blank")
    private String note;

    @Pattern(regexp = "add|deduct|bonus", message = "Type must be one of the following: add, deduct, bonus")
    private String type; // type: add or deduct, bonus

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt = Instant.now();

    @ManyToOne
    @JoinColumn(name = "challenge_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_challenge"))
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "reward_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_reward"))
    private Rewards reward;

    @ManyToOne
    @JoinColumn(name = "violation_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_violation"))
    private Violation violation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_history"))
    private User user;
}
