/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.warsztaty2024;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    @FXML private TextField imieszcz;
    @FXML private TextField mailszcz;
    @FXML private TextField adresszcz;
    @FXML private TextField telszcz;
    @FXML private TextField narodszcz;
    @FXML private TextField statusszcz;
    @FXML private TextField linkszcz;
    @FXML private TextField zrodloszcz;
          
         
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
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nazwiskoszcz.setText(PrimaryController.wybranaOsobaDalej.getNazwisko());
        idszcz.setText(Integer.toString(PrimaryController.wybranaOsobaDalej.getID()));
        imieszcz.setText(PrimaryController.wybranaOsobaDalej.getImie());
        mailszcz.setText(PrimaryController.wybranaOsobaDalej.getEmail());
        adresszcz.setText(PrimaryController.wybranaOsobaDalej.getAdres());
        telszcz.setText(PrimaryController.wybranaOsobaDalej.getTelefon());
        narodszcz.setText(PrimaryController.wybranaOsobaDalej.getNarodowosc());
        zrodloszcz.setText(PrimaryController.wybranaOsobaDalej.getZrodlo());
        statusszcz.setText(PrimaryController.wybranaOsobaDalej.getStatus());
        linkszcz.setText(PrimaryController.wybranaOsobaDalej.getLink());
    }
    }
