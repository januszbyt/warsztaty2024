/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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

public class Prawo_JazdyController implements Initializable {

    @FXML private DatePicker data_wydania;
    @FXML private DatePicker data_waznosci;
    @FXML private TextField kategorie;
    @FXML private TextField numer_pj;
    @FXML private TextField kraj_pochodzenia;
    @FXML public TextField id_pracownika;
    boolean status;
    @FXML private CheckBox edycja;

   



@FXML
private void prawo_jazdy(ActionEvent event) throws Exception {
    Connection connection = DatabaseConnection.getConnection();
    String sql;
    try {
        if(status == true) {
            sql = "INSERT INTO prawa_jazdy(Numer_pj, kategorie, data_wydania, data_waznosci, kraj_pochodzenia, pracownik_id) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE prawa_jazdy set Numer_pj = ?, kategorie = ?, data_wydania = ?, data_waznosci = ?, kraj_pochodzenia = ? where pracownik_id = ?";
        }

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, numer_pj.getText()); // Zapis numeru prawa jazdy do kolumny Numer_pj
        ps.setString(2, kategorie.getText());
        ps.setDate(3, Date.valueOf(data_wydania.getValue()));
        ps.setDate(4, Date.valueOf(data_waznosci.getValue()));
        ps.setString(5, kraj_pochodzenia.getText());
        ps.setInt(6, SzczegolController.pracownik_id);
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
        kategorie.setEditable(false);
        numer_pj.setEditable(false);
        kraj_pochodzenia.setEditable(false);
        data_waznosci.setDisable(true);
        data_wydania.setDisable(true);

        edycja.setOnAction(event -> {
            boolean wlaczenieEdycji = edycja.isSelected();
            kategorie.setEditable(wlaczenieEdycji);
            numer_pj.setEditable(wlaczenieEdycji);
            kraj_pochodzenia.setEditable(wlaczenieEdycji);
            data_waznosci.setDisable(!wlaczenieEdycji);
            data_wydania.setDisable(!wlaczenieEdycji);
        });

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

            String query = "SELECT * FROM prawa_jazdy WHERE pracownik_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SzczegolController.pracownik_id);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                kategorie.setText(result.getString("kategorie"));
                kraj_pochodzenia.setText(result.getString("kraj_pochodzenia"));
                data_waznosci.setValue(result.getDate("data_waznosci").toLocalDate());
                data_wydania.setValue(result.getDate("data_wydania").toLocalDate());
                numer_pj.setText(result.getString("numer_pj"));
                ps.setString(1, numer_pj.getText());
            }

            if (kategorie.getText().isEmpty() && kraj_pochodzenia.getText().isEmpty()) {
                status = true;
            } else {
                status = false;
            }
        } catch (SQLException el) {
            el.printStackTrace();
        }
    }    
}
