package com.mycompany.warsztaty2024;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.DateCell;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.time.LocalDate;

public class wizy implements Initializable {

    @FXML private DatePicker data_wydania;
    @FXML private DatePicker data_waznosci;
    @FXML private TextField obszar;
    @FXML private TextField id_pracownika;
    @FXML private TextField wydajacy;
    @FXML private TextField liczba;
    @FXML private TextField rodzaj; 
    @FXML private TextField czas; 
    @FXML private TextField cel; 
    boolean status;
    @FXML private CheckBox edycja;

    @FXML
    private void wizy(ActionEvent event) throws Exception {
        // Sprawdzenie, czy wszystkie pola są wypełnione
        if (data_wydania.getValue() == null || data_waznosci.getValue() == null || 
            obszar.getText().isEmpty() || id_pracownika.getText().isEmpty() ||
            wydajacy.getText().isEmpty() || liczba.getText().isEmpty() ||
            rodzaj.getText().isEmpty() || czas.getText().isEmpty() || cel.getText().isEmpty()) {

            // Wyświetlenie komunikatu o uzupełnieniu wszystkich pól
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brakujące pola");
            alert.setHeaderText(null);
            alert.setContentText("Uzupełnij wszystkie pola");
            alert.showAndWait();
        } else {
            Connection connection = DatabaseConnection.getConnection();
            String sql;
            try {
                if (status) {
                    sql = "INSERT INTO wizy(data_wydania, data_waznosci, obszar, wydajacy, liczba, rodzaj, czas, cel, pracownik_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                } else {
                    sql = "UPDATE wizy set data_wydania = ?, data_waznosci = ?, obszar = ?, wydajacy = ?, liczba = ?, rodzaj = ?, czas = ?, cel = ? where pracownik_id = ?";
                }
                
                PreparedStatement ps = connection.prepareStatement(sql);
                
                ps.setDate(1, Date.valueOf(data_wydania.getValue()));
                ps.setDate(2, Date.valueOf(data_waznosci.getValue()));
                ps.setString(3, obszar.getText());
                ps.setString(4, wydajacy.getText());
                ps.setInt(5, Integer.parseInt(liczba.getText()));
                ps.setString(6, rodzaj.getText());
                ps.setInt(7, Integer.parseInt(czas.getText()));
                ps.setString(8, cel.getText());
                ps.setInt(9, SzczegolController.pracownik_id);
                
                ps.executeUpdate();
            } catch (SQLException el) {
                el.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Zamknij to okno po zapisie
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_pracownika.setText(String.valueOf(SzczegolController.pracownik_id));
        id_pracownika.setEditable(false);
        data_waznosci.setDisable(true);
        data_wydania.setDisable(true);
        obszar.setEditable(false);
        wydajacy.setEditable(false);
        rodzaj.setEditable(false);
        czas.setEditable(false);
        liczba.setEditable(false);
        cel.setEditable(false);

        edycja.setOnAction(event -> {
            boolean wlaczenieEdycji = edycja.isSelected();
            data_waznosci.setDisable(!wlaczenieEdycji);
            data_wydania.setDisable(!wlaczenieEdycji);
            obszar.setEditable(wlaczenieEdycji);
            wydajacy.setEditable(wlaczenieEdycji);
            rodzaj.setEditable(wlaczenieEdycji);
            czas.setEditable(wlaczenieEdycji);
            liczba.setEditable(wlaczenieEdycji);
            cel.setEditable(wlaczenieEdycji);
        });

        try {
            Connection connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Wizy WHERE pracownik_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SzczegolController.pracownik_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                data_waznosci.setValue(result.getDate("data_waznosci").toLocalDate());
                data_wydania.setValue(result.getDate("data_wydania").toLocalDate());
                obszar.setText(result.getString("obszar"));
                wydajacy.setText(result.getString("wydajacy")); 
                liczba.setText(result.getString("liczba")); 
                rodzaj.setText(result.getString("rodzaj")); 
                czas.setText(result.getString("czas")); 
                cel.setText(result.getString("cel")); 
            }

            status = obszar.getText().isEmpty();
        } catch (SQLException el) {
            el.printStackTrace();
        }

        // Dodanie ograniczeń do DatePickerów
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
    }
}
