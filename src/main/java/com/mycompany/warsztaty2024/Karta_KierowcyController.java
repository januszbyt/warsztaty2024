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



public class Karta_KierowcyController implements Initializable {

    @FXML private DatePicker data_wydania;
    @FXML private DatePicker data_waznosci;
    
    @FXML private TextField numer_karty;
    @FXML private TextField id_pracownika;
    boolean status;
    @FXML private CheckBox edycja;
    

    
    
    @FXML
    private void karta_kier (ActionEvent event) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        String sql;
        try {
            if(status == true) {
                sql = "INSERT INTO karty_kierowcy(numer, data_wydania, data_waznosci, pracownik_id) VALUES (?, ?, ?, ?)";
            } else {
                sql = "UPDATE karty_kierowcy set numer = ?, data_wydania = ?, data_waznosci = ? where pracownik_id = ?";
            }
            
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, numer_karty.getText());
            ps.setDate(2, Date.valueOf(data_wydania.getValue()));
            ps.setDate(3, Date.valueOf(data_waznosci.getValue()));
            ps.setInt(4, SzczegolController.pracownik_id);
            ps.executeUpdate();
            //testBaza(event);
            
        } catch (SQLException el) {
            el.printStackTrace();
        }
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    // Zamknij to okno po zapisie
    stage.close();
       
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_pracownika.setText(String.valueOf(SzczegolController.pracownik_id));
        id_pracownika.setEditable(false);
        numer_karty.setEditable(false);
        data_waznosci.setDisable(true);
        data_wydania.setDisable(true);
        
        
        
        edycja.setOnAction(event -> {
            boolean wlaczenieEdycji = edycja.isSelected();
            // Ustawienie stanu edycji pól tekstowych na podstawie zaznaczenia CheckBoxa
            numer_karty.setEditable(wlaczenieEdycji);
            data_waznosci.setDisable(!wlaczenieEdycji);
            data_wydania.setDisable(!wlaczenieEdycji);
        });
        
        data_wydania.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Dezaktywacja komórek reprezentujących daty przyszłe
                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Opcjonalne: zmiana koloru tła dla dat przyszłych
                }
            }
        });
        
        data_wydania.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isAfter(LocalDate.now())) {
                data_wydania.setValue(oldVal); // Ustawienie poprzedniej ważnej daty
            }
        });
        
        data_waznosci.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Dezaktywacja komórek reprezentujących daty przyszłe
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Opcjonalne: zmiana koloru tła dla dat przyszłych
                }
            }
        });
        
        data_waznosci.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal.isBefore(LocalDate.now())) {
                data_waznosci.setValue(oldVal); // Ustawienie poprzedniej ważnej daty
            }
        });
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            
            String query = "SELECT * FROM karty_kierowcy WHERE pracownik_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, SzczegolController.pracownik_id);
            ResultSet result = ps.executeQuery();
            while(result.next()) {
                data_waznosci.setValue(result.getDate("data_waznosci").toLocalDate());
                data_wydania.setValue(result.getDate("data_wydania").toLocalDate());
                numer_karty.setText(result.getString("numer"));
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
