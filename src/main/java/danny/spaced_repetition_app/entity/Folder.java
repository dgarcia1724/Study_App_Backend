package danny.spaced_repetition_app.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<ListEntity> lists;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListEntity> getLists() {
        return lists;
    }

    public void setLists(List<ListEntity> lists) {
        this.lists = lists;
    }
}
