/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.warsztaty2024;

import static com.mycompany.warsztaty2024.SzczegolController.pracownik_id;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
/**
 * FXML Controller class
 *
 * @author s40479
 */
public class Wyswietl_ZdjecieController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    @FXML private TextField linkszcz;
    @FXML private ImageView myPhoto;
    @FXML private Stage stage;
    private double dragStartX;
    private double dragStartY;
    
    
    @FXML
    private void loadPhoto (ActionEvent event) {
        String path = PrimaryController.wybranaOsobaDalej.getLink(); 
        FileChooser fileChooser  = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.setTitle("Wybierz plik");
        //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki JPG", ".jpg"));
        File plik = fileChooser.showOpenDialog(stage);
        if(plik != null) {
            System.out.println("Plik: " + plik.getAbsolutePath());
            Image obraz = new Image("file:///" + plik.getAbsolutePath());
            myPhoto.setImage(obraz);
            
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Dodanie obsługi zdarzeń myszy
        myPhoto.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double deltaY = event.getDeltaY();
                // Zmiana rozmiaru obrazu w zależności od kierunku ruchu kółka myszy
                double zoomFactor = 1.05;
                if (deltaY < 0) {
                    zoomFactor = 2.0 - zoomFactor;
                }
                myPhoto.setScaleX(myPhoto.getScaleX() * zoomFactor);
                myPhoto.setScaleY(myPhoto.getScaleY() * zoomFactor);
            }
        });
        
        myPhoto.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragStartX = event.getSceneX() - myPhoto.getTranslateX();
                dragStartY = event.getSceneY() - myPhoto.getTranslateY();
            }
        });

        myPhoto.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double offsetX = event.getSceneX() - dragStartX;
                double offsetY = event.getSceneY() - dragStartY;
                myPhoto.setTranslateX(offsetX);
                myPhoto.setTranslateY(offsetY);
            }
        });
    }       
    
}
