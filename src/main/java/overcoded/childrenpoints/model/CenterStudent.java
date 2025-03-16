package overcoded.childrenpoints.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "center_students")
@Data
public class CenterStudent {

    @Id
    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private User center;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
}
