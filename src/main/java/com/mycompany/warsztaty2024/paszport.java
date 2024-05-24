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
    private void paszport (ActionEvent event) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        String sql;
        try {
            if(status == true) {
                sql = "INSERT INTO paszport(numer, data_wydania, data_waznosci, pracownik_id, kraj_pochodzenia, oddzial_wydajacy) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE paszport set numer = ?, data_wydania = ?, data_waznosci = ?, kraj_pochodzenia = ?, oddzial_wydajacy = ? where pracownik_id = ?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, numer_karty.getText());
            ps.setDate(2, Date.valueOf(data_wydania.getValue()));
            ps.setDate(3, Date.valueOf(data_waznosci.getValue()));
            ps.setInt(4, SzczegolController.pracownik_id);
            ps.setString(5, kraj_pochodzenia_field.getText()); // Ustawienie wartości kraju pochodzenia
            ps.setString(6, oddzial_wydajacy_field.getText()); // Ustawienie wartości oddziału wydającego
           
            
            ps.executeUpdate();
        } catch (SQLException el) {
            el.printStackTrace();
        }
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Zamknij to okno po zapisie
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

        // Logika dla daty wydania

        // Logika dla daty ważności

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

           if (numer_karty.getText().isEmpty()) {
                status = true;
            } else {
                status = false;
            } 
        } catch (SQLException el) {
            el.printStackTrace();
        }
    }
}