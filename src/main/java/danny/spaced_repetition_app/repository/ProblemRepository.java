package danny.spaced_repetition_app.repository;

import danny.spaced_repetition_app.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    // Custom method to find problems by listId and order by confidence ascending
    List<Problem> findByListIdOrderByConfidenceAsc(Long listId);
}
