package service;

import model.User;
import repository.interfaces.UserRepository;
import util.InputValidator;

public class AuthService {

    private UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public void register(User user) {

        if (!InputValidator.isValidEmail(user.getEmail())) {
            throw new RuntimeException("Invalid email");
        }

        repo.save(user);
    }

    public User login(String email, String pass) {

        User user = repo.findByEmail(email);

        if (user != null && user.getPassword().equals(pass)) {
            return user;
        }

        return null;
    }
}