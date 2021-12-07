package grproproject.models;

import grproproject.managers.usermanager.User;
import grproproject.managers.usermanager.UserManager;
import grproproject.services.router.Router;
import grproproject.services.router.Routes;

public class ProfilesModel implements Model {

    public ProfilesModel() { }

    public void setUser(User user) {
        UserManager.getInstance().setActiveUser(user.getId());
        Router.goTo(Routes.HOME, new HomeModel(user.getName()), true);
    }

    public void addUser(String name) {
        String userName = name.isEmpty() ? "User" : name;
        UserManager.getInstance().addUser(new User(userName));
    }

    public void editUser(String id, String name) {
        String userName = name.isEmpty() ? "User" : name;
        UserManager.getInstance().editUser(id, new User(userName));
    }

    public void deleteUser(String id) {
        UserManager.getInstance().deleteUser(id);
    }

}
