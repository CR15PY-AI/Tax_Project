package ui.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.User;
import repository.db.*;
import repository.interfaces.*;
import service.*;

public class MainApp extends Application {

    private AuthService auth;
    private TaxService tax;
    private Stage stage;
    private Scene mainScene;   // ← Главная сцена (используем одну на всё приложение)

    @Override
    public void start(Stage s) {
        stage = s;

        // Загрузка иконки
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        if (!icon.isError()) {
            stage.getIcons().add(icon);
        }

        // Инициализация сервисов
        auth = new AuthService(new DBUserRepository());
        tax = new TaxService(new DBTaxFormRepository());

        // Создаём одну сцену и подключаем CSS
        mainScene = new Scene(new VBox(), 400, 500);
        mainScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(mainScene);
        stage.setTitle("Tax Project");

        loginUI();
        stage.show();
    }

    private void loginUI() {
        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Пароль");

        Button loginBtn = new Button("Войти");
        Button registerBtn = new Button("Регистрация");

        Label msg = new Label();
        msg.getStyleClass().add("error-label");

        loginBtn.setOnAction(e -> {
            User u = auth.login(email.getText(), pass.getText());

            if (u == null) {
                msg.setText("Неверный email или пароль");
                return;
            }

            if (u.getRole().equals("ADMIN")) {
                adminUI(u);
            } else {
                userUI(u);
            }
        });

        registerBtn.setOnAction(e -> registerUI());

        VBox layout = new VBox(15, email, pass, loginBtn, registerBtn, msg);
        layout.getStyleClass().add("root");

        mainScene.setRoot(layout);
    }

    private void registerUI() {
        TextField name = new TextField();
        name.setPromptText("Имя");

        TextField email = new TextField();
        email.setPromptText("Email");

        PasswordField pass = new PasswordField();
        pass.setPromptText("Пароль");

        ComboBox<String> role = new ComboBox<>();
        role.getItems().addAll("USER", "ADMIN");
        role.setValue("USER");

        Button registerBtn = new Button("Создать аккаунт");
        Button backBtn = new Button("Назад");

        Label msg = new Label();

        registerBtn.setOnAction(e -> {
            try {
                User user = new User(
                        0,
                        name.getText(),
                        email.getText(),
                        pass.getText(),
                        role.getValue()
                );

                auth.register(user);
                msg.setText("Аккаунт успешно создан!");
                msg.setStyle("-fx-text-fill: green;");

            } catch (Exception ex) {
                msg.setText("Ошибка: " + ex.getMessage());
                msg.setStyle("-fx-text-fill: red;");
            }
        });

        backBtn.setOnAction(e -> loginUI());

        VBox layout = new VBox(15,
                name, email, pass, role,
                registerBtn, backBtn, msg
        );
        layout.getStyleClass().add("root");

        mainScene.setRoot(layout);
    }

    private void userUI(User user) {
        TextField idField = new TextField();
        idField.setPromptText("ID формы (для обновления)");

        TextField incomeField = new TextField();
        incomeField.setPromptText("Доход");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("INDIVIDUAL", "OSOO");
        typeBox.setValue("INDIVIDUAL");

        Button createBtn = new Button("Создать");
        Button updateBtn = new Button("Обновить");
        Button viewBtn = new Button("Мои формы");

        TextArea out = new TextArea();
        out.setEditable(false);
        out.setPrefHeight(200);

        createBtn.setOnAction(e -> {
            try {
                double income = Double.parseDouble(incomeField.getText());
                String type = typeBox.getValue();
                tax.create(user, income, type);
                out.setText("Форма успешно создана!");
            } catch (Exception ex) {
                out.setText("Ошибка: " + ex.getMessage());
            }
        });

        updateBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                double income = Double.parseDouble(incomeField.getText());
                String type = typeBox.getValue();
                tax.update(id, income, type, user);
                out.setText("Форма обновлена!");
            } catch (Exception ex) {
                out.setText("Ошибка: " + ex.getMessage());
            }
        });

        viewBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder("Мои формы:\n\n");
            tax.getUserForms(user).forEach(f -> sb.append(f).append("\n"));
            out.setText(sb.toString());
        });

        VBox layout = new VBox(12,
                new Label("Добро пожаловать, " + user.getName()),
                idField, incomeField, typeBox,
                createBtn, updateBtn, viewBtn,
                out
        );
        layout.getStyleClass().add("root");

        mainScene.setRoot(layout);
    }

    private void adminUI(User user) {
        TextField idField = new TextField();
        idField.setPromptText("ID формы");

        Button viewAllBtn = new Button("Все формы");
        Button approveBtn = new Button("Одобрить");
        Button rejectBtn = new Button("Отклонить");

        TextArea out = new TextArea();
        out.setEditable(false);
        out.setPrefHeight(250);

        viewAllBtn.setOnAction(e -> {
            StringBuilder sb = new StringBuilder("Все формы:\n\n");
            tax.getAllForms().forEach(f -> sb.append(f).append("\n"));
            out.setText(sb.toString());
        });

        approveBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                tax.approve(id);
                out.setText("Форма одобрена!");
            } catch (Exception ex) {
                out.setText("Ошибка: " + ex.getMessage());
            }
        });

        rejectBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                tax.reject(id);
                out.setText("Форма отклонена!");
            } catch (Exception ex) {
                out.setText("Ошибка: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(12,
                new Label("Панель администратора"),
                idField,
                viewAllBtn, approveBtn, rejectBtn,
                out
        );
        layout.getStyleClass().add("root");

        mainScene.setRoot(layout);
    }

    public static void main(String[] args) {
        launch();
    }
}