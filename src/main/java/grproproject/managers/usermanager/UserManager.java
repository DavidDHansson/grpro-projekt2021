package grproproject.managers.usermanager;

import grproproject.Constants;
import grproproject.managers.mediaManager.Media;
import grproproject.services.CustomAlert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserManager {

    private static UserManager instance;
    private ArrayList<User> users;
    private User activeUser;
    private List<String> activeUserFavorites;

    public UserManager() {
        loadUsersFromDisk();
    }

    public static UserManager getInstance() {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    public void setActiveUser(String id) {
        int index = -1;
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().contains(id))  { index = i; break; }
        }

        activeUser = users.get(index);

        loadActiveFavoritesFromDisk();
    }

    public void addUser(User user) {
        users.add(user);
        setActiveUser(user.getId());
        saveUsersToDisk();
    }

    public void deleteUser(String id) {
        users.removeIf(user -> user.getId().contains(id));
        deleteFavoritesFromDisk(id);
        saveUsersToDisk();
    }

    public void editUser(String id, User user) {
        int index = -100;
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().contains(id))  {
                index = i;
                break;
            }
        }

        if (index < 0) { CustomAlert.showError("Error removing user"); return; }
        User newUser = user;
        newUser.setId(id);
        users.set(index, newUser);
        saveUsersToDisk();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addFavoriteToActiveUser(String mediaTitle) {
        if (!isActiveUserSet() || activeUserFavorites.contains(mediaTitle)) return;
        activeUserFavorites.add(mediaTitle);
        saveActiveFavoritesToDisk();
    }

    public void removeFavoriteFromActiveUser(String mediaTitle) {
        activeUserFavorites.removeIf(title -> title.equals(mediaTitle));
        saveActiveFavoritesToDisk();
    }

    public List<String> getFavoritesFromActiveUser() {
        if (!isActiveUserSet()) return new ArrayList<>();

        return activeUserFavorites;
    }

    private Boolean isActiveUserSet() {
        return activeUser != null;
    }

    private void deleteFavoritesFromDisk(String id) {
        activeUserFavorites = new ArrayList<>();

        File favoritesFile = new File(id + Constants.fileExtension);
        favoritesFile.delete();
    }

    private void loadActiveFavoritesFromDisk() {
        if (!isActiveUserSet()) return;
        activeUserFavorites = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(activeUser.getId() + Constants.fileExtension));
            while (s.hasNextLine()) {
                activeUserFavorites.add(s.nextLine());
            }
        } catch (Exception e) {
            return;
        }
    }

    private void loadUsersFromDisk() {
        users = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File(Constants.usersFile));
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                users.add(new User(line[0], line[1]));
            }
        } catch (Exception e) {
            users.add(new User("Default user"));
            saveUsersToDisk();
        }
    }

    private void saveActiveFavoritesToDisk() {
        try {
            // Append to users favorites
            FileWriter fileWriter = new FileWriter(activeUser.getId() + Constants.fileExtension, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String title : activeUserFavorites) {
                bufferedWriter.write(title + "\n");
            }

            bufferedWriter.close();
        } catch (Exception e) {
            CustomAlert.showError(e.getLocalizedMessage());
        }
    }

    private void saveUsersToDisk() {
        try {
            // Replace the users.txt
            FileWriter fileWriter = new FileWriter(Constants.usersFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (User u : users) {
                bufferedWriter.write(u.getId() + " " + u.getName() + "\n");
            }

            bufferedWriter.close();
        } catch (Exception e) {
            CustomAlert.showError(e.getLocalizedMessage());
        }
    }

}
