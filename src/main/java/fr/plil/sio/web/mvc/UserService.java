package fr.plil.sio.web.mvc;

public interface UserService {

    User createUser(String username, String password);

    User findByUsername(String username);
}
