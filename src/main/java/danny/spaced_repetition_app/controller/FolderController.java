package danny.spaced_repetition_app.controller;


import danny.spaced_repetition_app.entity.Folder;
import danny.spaced_repetition_app.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folders")
@CrossOrigin(origins = "*") // Allow all origins
public class FolderController {

    @Autowired
    private FolderService folderService;

    // Search folders by prefix
    @GetMapping("/search")
    public ResponseEntity<List<Folder>> searchFolders(@RequestParam(value = "prefix", required = false) String prefix) {
        List<Folder> folders;

        if (prefix == null || prefix.isEmpty()) {
            folders = folderService.getAllFolders(); // Return all folders if no prefix is provided
        } else {
            folders = folderService.getFoldersByPrefix(prefix); // Return folders matching the prefix
        }

        return ResponseEntity.ok(folders);
    }
    
    @GetMapping
    public List<Folder> getAllFolders() {
        return folderService.getAllFolders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Long id) {
        Folder folder = folderService.getFolderById(id);
        return ResponseEntity.ok(folder);
    }

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        Folder savedFolder = folderService.createFolder(folder);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFolder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Folder> updateFolder(@PathVariable Long id, @RequestBody Folder folderDetails) {
        Folder updatedFolder = folderService.updateFolder(id, folderDetails);
        return ResponseEntity.ok(updatedFolder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        folderService.deleteFolder(id);
        return ResponseEntity.noContent().build();
    }
}
