package danny.spaced_repetition_app.service;

import danny.spaced_repetition_app.entity.Folder;
import danny.spaced_repetition_app.entity.ListEntity;
import danny.spaced_repetition_app.repository.FolderRepository;
import danny.spaced_repetition_app.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private FolderRepository folderRepository;

    public List<ListEntity> getListsByFolderId(Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        return folder.getLists();
    }

    public ListEntity getListById(Long id) {
        return listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found"));
    }

    public ListEntity createList(Long folderId, ListEntity list) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found"));
        list.setFolder(folder);
        return listRepository.save(list);
    }

    public ListEntity updateList(Long id, ListEntity listDetails) {
        ListEntity list = listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found"));
        list.setName(listDetails.getName());
        return listRepository.save(list);
    }

    public void deleteList(Long id) {
        ListEntity list = listRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "List not found"));
        listRepository.delete(list);
    }
}
