package business;

import dao.UserDao;
import entities.Users;

public class UserManager {
    private final UserDao userDao;


    public UserManager() {
        this.userDao = new UserDao();
    }

    public Users findByLogin(String username, String password) {
        //farklı işlemler yapabiliriz.
        return this.userDao.findByLogin(username, password);
    }
}
