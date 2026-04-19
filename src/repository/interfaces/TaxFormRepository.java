package repository.interfaces;

import model.TaxForm;
import java.util.List;

public interface TaxFormRepository {

    void save(TaxForm form);
    void update(TaxForm form);
    void delete(int id);

    TaxForm findById(int id);
    List<TaxForm> findAll();
    List<TaxForm> findByUserId(int userId);
}