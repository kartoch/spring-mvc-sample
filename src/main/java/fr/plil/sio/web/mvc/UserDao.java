package fr.plil.sio.web.mvc;

import java.util.Set;

public interface UserDao {

    boolean save(User user);

    User getFromUsername(String username);

    Set<User> getAllUsers();

    boolean update(User user);

    boolean delete(User user);
}
