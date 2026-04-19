package repository.db;

import model.User;
import repository.interfaces.UserRepository;

import java.sql.*;

public class DBUserRepository implements UserRepository {

    @Override
    public void save(User user) {

        String sql = "INSERT INTO users(name,email,password,role) VALUES (?,?,?,?)";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            s.setString(1, user.getEmail());
            s.setString(2, user.getEmail());
            s.setString(3, user.getPassword());
            s.setString(4, user.getRole());

            s.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email=?";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            s.setString(1, email);
            ResultSet rs = s.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}