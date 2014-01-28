package fr.plil.sio.web.mvc;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    private Set<User> users = new HashSet<User>();

    public UserDaoImpl() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        users.add(user);
    }

    @Override
    public boolean save(User user) {        
        return users.add(user);
    }

    @Override
    public User getFromUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    @Override
    public boolean update(User user) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean delete(User user) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
