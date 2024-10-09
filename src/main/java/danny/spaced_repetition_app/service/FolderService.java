package danny.spaced_repetition_app.service;

import danny.spaced_repetition_app.entity.Folder;
import danny.spaced_repetition_app.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    public Folder getFolderById(Long id) {
        return folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
    }

    public Folder createFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder updateFolder(Long id, Folder folderDetails) {
        Folder folder = folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        folder.setName(folderDetails.getName());
        return folderRepository.save(folder);
    }

    public void deleteFolder(Long id) {
        Folder folder = folderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        folderRepository.delete(folder);
    }
}


