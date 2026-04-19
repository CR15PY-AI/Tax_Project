import repository.db.DBUserRepository;
import repository.db.DBTaxFormRepository;
import repository.interfaces.UserRepository;
import repository.interfaces.TaxFormRepository;

import service.AuthService;
import service.TaxService;

import ui.console.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepo = new DBUserRepository();
        TaxFormRepository taxRepo = new DBTaxFormRepository();

        AuthService authService = new AuthService(userRepo);
        TaxService taxService = new TaxService(taxRepo);


        ConsoleUI ui = new ConsoleUI(authService, taxService);

        ui.start();
    }
}