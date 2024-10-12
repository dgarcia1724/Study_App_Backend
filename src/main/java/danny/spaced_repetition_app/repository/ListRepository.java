package danny.spaced_repetition_app.repository;

import danny.spaced_repetition_app.entity.ListEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long> {

    // Case-insensitive search using ILIKE in PostgreSQL with a prefix for a specific folder
    @Query("SELECT l FROM ListEntity l WHERE l.name ILIKE CONCAT(:prefix, '%') AND l.folder.id = :folderId")
    List<ListEntity> findByNameStartingWithAndFolderId(@Param("folderId") Long folderId, @Param("prefix") String prefix);

    // Filter lists by title A-Z for a specific folder
    @Query("SELECT l FROM ListEntity l WHERE l.folder.id = :folderId ORDER BY l.name ASC")
    List<ListEntity> findAllOrderByNameAscAndFolderId(@Param("folderId") Long folderId);

    // Filter lists by title Z-A for a specific folder
    @Query("SELECT l FROM ListEntity l WHERE l.folder.id = :folderId ORDER BY l.name DESC")
    List<ListEntity> findAllOrderByNameDescAndFolderId(@Param("folderId") Long folderId);
}
