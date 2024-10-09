package danny.spaced_repetition_app.controller;

import danny.spaced_repetition_app.entity.Problem;
import danny.spaced_repetition_app.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/list/{listId}")
    public List<Problem> getProblemsByListId(@PathVariable Long listId) {
        return problemService.getProblemsByListId(listId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> getProblemById(@PathVariable Long id) {
        Problem problem = problemService.getProblemById(id);
        return ResponseEntity.ok(problem);
    }

    @PostMapping("/list/{listId}")
    public ResponseEntity<Problem> createProblem(@PathVariable Long listId, @RequestBody Problem problem) {
        Problem savedProblem = problemService.createProblem(listId, problem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProblem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable Long id, @RequestBody Problem problemDetails) {
        Problem updatedProblem = problemService.updateProblem(id, problemDetails);
        return ResponseEntity.ok(updatedProblem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }
}
