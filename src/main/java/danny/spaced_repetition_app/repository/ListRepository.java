package danny.spaced_repetition_app.repository;

import danny.spaced_repetition_app.entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {
}
