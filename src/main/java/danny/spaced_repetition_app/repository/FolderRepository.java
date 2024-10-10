package danny.spaced_repetition_app.repository;

import danny.spaced_repetition_app.entity.Folder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

  // Case-insensitive search using ILIKE in PostgreSQL with the original method name
  @Query("SELECT f FROM Folder f WHERE f.name ILIKE CONCAT(:prefix, '%')")
  List<Folder> findByNameStartingWith(@Param("prefix") String prefix);
  
  // Filter by title A-Z
  @Query("SELECT f FROM Folder f ORDER BY f.name ASC")
  List<Folder> findAllOrderByNameAsc();

  // Filter by title Z-A
  @Query("SELECT f FROM Folder f ORDER BY f.name DESC")
  List<Folder> findAllOrderByNameDesc();
}
