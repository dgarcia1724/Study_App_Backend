package danny.spaced_repetition_app.controller;

import danny.spaced_repetition_app.entity.ListEntity;
import danny.spaced_repetition_app.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@CrossOrigin(origins = "*") // Allow all origins
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping("/folder/{folderId}")
    public List<ListEntity> getListsByFolderId(@PathVariable Long folderId) {
        return listService.getListsByFolderId(folderId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable Long id) {
        ListEntity list = listService.getListById(id);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/folder/{folderId}")
    public ResponseEntity<ListEntity> createList(@PathVariable Long folderId, @RequestBody ListEntity list) {
        ListEntity savedList = listService.createList(folderId, list);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@PathVariable Long id, @RequestBody ListEntity listDetails) {
        ListEntity updatedList = listService.updateList(id, listDetails);
        return ResponseEntity.ok(updatedList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Long id) {
        listService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
