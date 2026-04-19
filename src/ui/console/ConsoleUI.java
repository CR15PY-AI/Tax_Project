package ui.console;

import model.User;
import service.AuthService;
import service.TaxService;

import java.util.Scanner;

public class ConsoleUI {

    private AuthService authService;
    private TaxService taxService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(AuthService authService, TaxService taxService) {
        this.authService = authService;
        this.taxService = taxService;
    }

    public void start() {

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) register();
            else if (choice == 2) login();
            else break;
        }
    }

    private void register() {

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(0, name, email, password, "USER");

        authService.register(user);

        System.out.println("Registered!");
    }

    private void login() {

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = authService.login(email, password);

        if (user == null) {
            System.out.println("Wrong credentials");
            return;
        }

        userMenu(user);
    }

    private void userMenu(User user) {

        while (true) {

            System.out.println("\n1. Create Tax Form");
            System.out.println("2. View Forms");
            System.out.println("3. Delete Form");
            System.out.println("0. Logout");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Income: ");
                double income = scanner.nextDouble();

                taxService.create(user, income, "INDIVIDUAL");
            }

            else if (choice == 2) {
                taxService.getAllForms().forEach(System.out::println);
            }

            else if (choice == 3) {
                System.out.print("ID: ");
                int id = scanner.nextInt();
                taxService.delete(id, user);
            }

            else break;
        }
    }
}