package danny.spaced_repetition_app.repository;

import danny.spaced_repetition_app.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
}
