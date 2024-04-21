package com.mycompany.warsztaty2024;

import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void handleLoginButtonAction() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            Connection conn = DatabaseConnection.getConnection();
            // Sprawdzanie poprawności danych logowania
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Jeśli znaleziono pasujące dane, kontynuuj logowanie
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Logowanie udane", "Witaj, " + username + "!");
                App.setMainScene();
            } else {
                // Jeśli nie znaleziono pasujących danych, wyświetl alert o nieprawidłowym loginie lub haśle
                showAlert(Alert.AlertType.ERROR, "Błąd logowania", "Nieprawidłowy login lub hasło", "Spróbuj ponownie.");
            }
        } catch (SQLException e) {
            // Jeśli wystąpi błąd podczas łączenia z bazą danych, wyświetl alert
            showAlert(Alert.AlertType.ERROR, "Błąd logowania", "Błąd połączenia z bazą danych", "Sprawdź połączenie z internetem oraz ustawienia bazy danych.");

            // W przypadku błędu połączenia z bazą danych, wypisz informację o błędzie
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
        
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
