package com.mycompany.warsztaty2024;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import java.sql.*;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.time.temporal.ChronoUnit;

public class paszport implements Initializable {

    @FXML private DatePicker data_wydania;
    @FXML private DatePicker data_waznosci;
    @FXML private TextField numer_karty;
    @FXML private TextField id_pracownika;
    @FXML private TextField kraj_pochodzenia_field; // Pole do wprowadzania kraju pochodzenia
    @FXML private TextField oddzial_wydajacy_field; // Pole do wprowadzania oddziału wydającego
    boolean status;
    @FXML private CheckBox edycja;

    @FXML
private void paszport(ActionEvent event) throws Exception {
    // Sprawdzenie, czy wszystkie pola są wypełnione
    if (numer_karty.getText().isEmpty() ||
        data_wydania.getValue() == null ||
        data_waznosci.getValue() == null ||
        kraj_pochodzenia_field.getText().isEmpty() ||
        oddzial_wydajacy_field.getText().isEmpty()) {
        
        // Wyświetlenie komunikatu ostrzegawczego
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Brakujące dane");
        alert.setHeaderText(null);
        alert.setContentText("Proszę wypełnić wszystkie pola.");
        alert.showAndWait();
        return; // Przerwanie metody
    }

    Connection connection = DatabaseConnection.getConnection();
    String sql;
    try {
        if (status) {
            sql = "INSERT INTO paszport(numer, data_wydania, data_waznosci, kraj_pochodzenia, oddzial_wydajacy, pracownik_id) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE paszport SET numer = ?, data_wydania = ?, data_waznosci = ?, kraj_pochodzenia = ?, oddzial_wydajacy = ? WHERE pracownik_id = ?";
        }

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, numer_karty.getText());
        ps.setDate(2, Date.valueOf(data_wydania.getValue()));
        ps.setDate(3, Date.valueOf(data_waznosci.getValue()));
        ps.setString(4, kraj_pochodzenia_field.getText()); // Ustawienie wartości kraju pochodzenia
        ps.setString(5, oddzial_wydajacy_field.getText()); // Ustawienie wartości oddziału wydającego
        ps.setInt(6, SzczegolController.pracownik_id);

        ps.executeUpdate();
    } catch (SQLException el) {
        el.printStackTrace();
    }

    // Zamknij okno po zapisie
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_pracownika.setText(String.valueOf(SzczegolController.pracownik_id));
        id_pracownika.setEditable(false);
        numer_karty.setEditable(false);
        data_waznosci.setDisable(true);
        data_wydania.setDisable(true);
        kraj_pochodzenia_field.setEditable(false);
        oddzial_wydajacy_field.setEditable(false);

        edycja.setOnAction(event -> {
            boolean wlaczenieEdycji = edycja.isSelected();
            numer_karty.setEditable(wlaczenieEdycji);
            data_waznosci.setDisable(!wlaczenieEdycji);
            data_wydania.setDisable(!wlaczenieEdycji);
            kraj_pochodzenia_field.setEditable(wlaczenieEdycji); // Ustawienie edycji pola kraju pochodzenia
            oddzial_wydajacy_field.setEditable(wlaczenieEdycji); // Ustawienie edycji pola oddziału wydającego
        });

    // Pobranie daty ważności z bazy danych i wyświetlenie komunikatu o liczbie dni do końca ważności
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT data_waznosci FROM paszport WHERE pracownik_id=?";
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
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Informacja");
                    alert.setHeaderText(null);
                    alert.setContentText("Do końca ważności paszportu kierowcy pozostało: " + dniDoKoncaWaznosci + " dni.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
                data_wydania.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        data_wydania.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isAfter(LocalDate.now())) {
                data_wydania.setValue(oldVal);
            }
        });

        data_waznosci.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });

        data_waznosci.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isBefore(LocalDate.now())) {
                data_waznosci.setValue(oldVal);
            }
        });
        
        
        
        
        try {
            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM paszport WHERE pracownik_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SzczegolController.pracownik_id);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                data_waznosci.setValue(result.getDate("data_waznosci").toLocalDate());
                data_wydania.setValue(result.getDate("data_wydania").toLocalDate());
                numer_karty.setText(result.getString("numer"));
                kraj_pochodzenia_field.setText(result.getString("kraj_pochodzenia")); // Ustawienie wartości kraju pochodzenia
                oddzial_wydajacy_field.setText(result.getString("oddzial_wydajacy")); // Ustawienie wartości oddziału wydającego
            }

            status = numer_karty.getText().isEmpty(); // Jeśli numer karty nie jest pusty, status ustawiamy na false
        } catch (SQLException el) {
            el.printStackTrace();
        }
    }
}