package fr.plil.sio.web.mvc;

import java.util.List;

public interface UserService {

    User createUser(String username, String password);

    User findByUsername(String username);

    List<User> findAll();
}
