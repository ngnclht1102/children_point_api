package overcoded.childrenpoints.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teacher_students")
@Data
public class TeacherStudent {

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
}
