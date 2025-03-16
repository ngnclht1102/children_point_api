package overcoded.childrenpoints.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parent_children")
@Data
public class ParentChild {

    @Id
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private User parent;

    @Id
    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private User child;
}
