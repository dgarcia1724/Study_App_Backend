package danny.spaced_repetition_app.service;

import danny.spaced_repetition_app.entity.ListEntity;
import danny.spaced_repetition_app.entity.Problem;
import danny.spaced_repetition_app.repository.ListRepository;
import danny.spaced_repetition_app.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ListRepository listRepository;

    public List<Problem> getProblemsByListId(Long listId) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found"));
        return list.getProblems();
    }

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found"));
    }

    public Problem createProblem(Long listId, Problem problem) {
        ListEntity list = listRepository.findById(listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found"));
        problem.setList(list);
        // Set the link field while creating a new problem
        if (problem.getLink() != null) {
            problem.setLink(problem.getLink());
        }
        return problemRepository.save(problem);
    }

    public Problem updateProblem(Long id, Problem problemDetails) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found"));

        problem.setName(problemDetails.getName());
        problem.setConfidence(problemDetails.getConfidence());
        // Set the link field while updating an existing problem
        problem.setLink(problemDetails.getLink());

        return problemRepository.save(problem);
    }

    public void deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found"));
        problemRepository.delete(problem);
    }
}



