package repository.db;

import model.TaxForm;
import repository.interfaces.TaxFormRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTaxFormRepository implements TaxFormRepository {

    @Override
    public void save(TaxForm form) {

        String sql = "INSERT INTO tax_forms(user_id, income, tax, status, tax_type) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, form.getUserId());
            stmt.setDouble(2, form.getIncome());
            stmt.setDouble(3, form.getTax());
            stmt.setString(4, form.getStatus());
            stmt.setString(5, form.getTaxType());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TaxForm> findAll() {

        List<TaxForm> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM tax_forms");

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<TaxForm> findByUserId(int userId) {

        List<TaxForm> list = new ArrayList<>();

        String sql = "SELECT * FROM tax_forms WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public TaxForm findById(int id) {

        String sql = "SELECT * FROM tax_forms WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(TaxForm form) {

        String sql = "UPDATE tax_forms SET income=?, tax=?, status=?, tax_type=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, form.getIncome());
            stmt.setDouble(2, form.getTax());
            stmt.setString(3, form.getStatus());
            stmt.setString(4, form.getTaxType());
            stmt.setInt(5, form.getId());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM tax_forms WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TaxForm map(ResultSet rs) throws SQLException {
        return new TaxForm(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getDouble("income"),
                rs.getDouble("tax"),
                rs.getString("status"),
                rs.getString("tax_type")
        );
    }
}