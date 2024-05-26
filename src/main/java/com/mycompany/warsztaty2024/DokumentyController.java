package com.mycompany.warsztaty2024;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class DokumentyController implements Initializable {

    @FXML
    private Button prawoJazdyButton; // Przycisk "Prawo jazdy"

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Połączenie z bazą danych
         try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT data_waznosci FROM prawa_jazdy WHERE pracownik_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SzczegolController.pracownik_id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                LocalDate dataWaznosci = result.getDate("data_waznosci").toLocalDate();
                LocalDate dzisiaj = LocalDate.now();
                long dniDoKoncaWaznosci = ChronoUnit.DAYS.between(dzisiaj, dataWaznosci);
                
                // Sprawdzamy, czy różnica wynosi 90 dni i więcej
                if (dniDoKoncaWaznosci <= 90 && dniDoKoncaWaznosci > 0) {
                    // Tworzymy komunikat Alert informujący użytkownika
                    prawoJazdyButton.setStyle("-fx-background-color: orange;");
                }
                 if (dniDoKoncaWaznosci <= 30 && dniDoKoncaWaznosci > 0) {
                    // Tworzymy komunikat Alert informujący użytkownika
                    prawoJazdyButton.setStyle("-fx-background-color: red;");
                }
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
