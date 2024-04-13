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
import javafx.scene.control.TextField;



public class Karta_KierowcyController implements Initializable {

    @FXML private DatePicker data_wydania;
    @FXML private DatePicker data_waznosci;
    
    @FXML private TextField numer_karty;
    @FXML private TextField id_pracownika;
    
    

    
    
    @FXML
    private void karta_kier (ActionEvent event) throws Exception {
    Connection connection = DatabaseConnection.getConnection();
    
        try {
            String sql = "INSERT INTO karty_kierowcy(numer, data_wydania, data_waznosci, pracownik_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, numer_karty.getText());
            ps.setDate(2, Date.valueOf(data_wydania.getValue()));
            ps.setDate(3, Date.valueOf(data_waznosci.getValue()));
            ps.setInt(4, 1);
            ps.executeUpdate();
            //testBaza(event);
            
        } catch (SQLException el) {
            el.printStackTrace();
        }
        numer_karty.clear();
        data_wydania.setValue(null);
        data_waznosci.setValue(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id_pracownika.setText(String.valueOf(SzczegolController.pracownik_id));
        
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
        } catch (SQLException el) {
            el.printStackTrace();
        }
    }    
    
}
