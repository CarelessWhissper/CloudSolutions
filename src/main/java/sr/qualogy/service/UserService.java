package sr.qualogy.service;



import sr.qualogy.configuration.JPAConfiguration;
import sr.qualogy.entity.User;
import sr.qualogy.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository(JPAConfiguration.getEntityManager());
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteUserById(id);
    }

    public User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public User authenticateUser(String email, String password) {
        // Fetch user by email
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // If user exists and passwords match, return the authenticated user
            return user;
        }
        return null; // Authentication failed
    }
}

