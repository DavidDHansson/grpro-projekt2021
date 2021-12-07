package grproproject.managers.usermanager;

import grproproject.services.CustomAlert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    private static UserManager instance;
    private ArrayList<User> users;

    public UserManager() {
        loadUsersFromDisk();
    }

    public static UserManager getInstance() {
        if (instance == null) instance = new UserManager();
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
        saveUsersToDisk();
    }

    public void removeUser(String id) {
        users.removeIf(user -> user.getId().contains(id));
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

    private void loadUsersFromDisk() {
        users = new ArrayList<>();

        try {
            Scanner s = new Scanner(new File("users.txt"));
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                users.add(new User(line[0], line[1]));
            }
        } catch (Exception e) {
            users.add(new User("Default user"));
            saveUsersToDisk();
        }
    }

    private void saveUsersToDisk() {
        try {
            // Replace the users.txt
            FileWriter fileWriter = new FileWriter("users.txt", false);

            for (User u : users) {
                fileWriter.write(u.getId() + " " + u.getName() + "\n");
            }

            fileWriter.close();
        } catch (Exception e) {
            CustomAlert.showError(e.getLocalizedMessage());
        }
    }

}
