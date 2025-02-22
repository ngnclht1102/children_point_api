package overcoded.childrenpoints.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import overcoded.childrenpoints.model.Violation;

@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {
}
