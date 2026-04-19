package service;

import model.TaxForm;
import model.User;
import repository.interfaces.TaxFormRepository;

import java.util.List;

public class TaxService {

    private TaxFormRepository repo;

    public TaxService(TaxFormRepository repo) {
        this.repo = repo;
    }

    private double calculate(double income, String type) {

        if (type.equals("OSOO")) {
            return income * 0.25;
        } else {
            return income * 0.13;
        }
    }
    public void create(User user, double income, String taxType) {

        double tax = calculate(income, taxType);

        TaxForm form = new TaxForm(
                0,
                user.getId(),
                income,
                tax,
                "DRAFT",
                taxType
        );

        repo.save(form);
    }

    public List<TaxForm> getUserForms(User user) {
        return repo.findByUserId(user.getId());
    }

    public List<TaxForm> getAllForms() {
        return repo.findAll();
    }

    public void submit(int id, User user) {
        TaxForm f = repo.findById(id);
        if (f != null && f.getUserId() == user.getId()) {
            f.setStatus("SUBMITTED");
            repo.update(f);
        }
    }

    public void update(int id, double income, String taxType, User user) {

        TaxForm f = repo.findById(id);

        if (f != null && f.getUserId() == user.getId()) {

            double tax = calculate(income, taxType);

            f.setIncome(income);
            f.setTax(tax);
            f.setStatus("DRAFT");
            f.setStatus(f.getStatus());

            repo.update(f);
        }
    }

    public void delete(int id, User user) {
        TaxForm f = repo.findById(id);
        if (f != null && f.getUserId() == user.getId()) {
            repo.delete(id);
        }
    }

    // 🔥 ADMIN
    public void approve(int id) {
        TaxForm f = repo.findById(id);
        if (f != null) {
            f.setStatus("APPROVED");
            repo.update(f);
        }
    }

    public void reject(int id) {
        TaxForm f = repo.findById(id);
        if (f != null) {
            f.setStatus("REJECTED");
            repo.update(f);
        }
    }
}