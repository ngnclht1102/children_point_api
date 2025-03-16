package overcoded.childrenpoints.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import overcoded.childrenpoints.model.User;
import overcoded.childrenpoints.model.Violation;

import java.util.List;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {
    List<Violation> findAllByUser(User user);
}
