package grproproject.managers.usermanager;

import grproproject.managers.mediaManager.Media;

import java.util.List;
import java.util.UUID;

public class User {

    private String id;
    private String name;
    private List<String> myList;

    public User(String id, String name) {
        this.id = id;
        this.name = name.replaceAll("\\s+", "");
    }

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name.replaceAll("\\s+", "");
    }

    public void updateMyList(Media media) {
        myList.add(media.getTitle());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getMyList() {
        return myList;
    }
}
