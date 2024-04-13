/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.warsztaty2024;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author s40483
 */
public class SzczegolController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TextField nazwiskoszcz;
    @FXML private TextField idszcz;
    public static int pracownik_id;

@FXML
private void switchToKarta_Kierowcy() throws IOException {
    
    pracownik_id = Integer.valueOf(idszcz.getText());
    //Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Karta kierowcy");
    //Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Karta_Kierowcy.fxml"));
    //Set view in window
    newWindow.setScene(new Scene(loader.load()));
    //Launch
    newWindow.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nazwiskoszcz.setText(PrimaryController.wybranaOsobaDalej.getNazwisko());
        idszcz.setText(Integer.toString(PrimaryController.wybranaOsobaDalej.getID()));
    }    
    
}
