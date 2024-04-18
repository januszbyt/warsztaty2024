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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private ImageView myimage; 
         
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


@FXML
private void openPersonalFolder() {
    String employeeFolderPath = PrimaryController.wybranaOsobaDalej.getLink();

    System.out.println("Próba otwarcia folderu pracownika: " + employeeFolderPath); 
    
    File employeeFolder = new File(employeeFolderPath);
    
    if (employeeFolder.exists() && employeeFolder.isDirectory()) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                
                Runtime.getRuntime().exec("explorer.exe " + employeeFolder.getAbsolutePath());
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                
                Runtime.getRuntime().exec("open " + employeeFolder.getAbsolutePath());
            } else {
                
                Runtime.getRuntime().exec("xdg-open " + employeeFolder.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Błąd", "Nie można otworzyć folderu.", "Sprawdź, czy masz odpowiednie uprawnienia i czy ścieżka jest prawidłowa.");
        }
    } else {
        showAlert("Błąd", "Folder nie istnieje", "Upewnij się, że ścieżka jest prawidłowa i folder istnieje.");
    }
}




private void loadEmployeePhoto() {
    String employeeFolderPath = PrimaryController.wybranaOsobaDalej.getLink(); 
    
    if (employeeFolderPath == null || employeeFolderPath.trim().isEmpty()) {
        showAlert("Błąd", "Brak ścieżki folderu pracownika", "Nie można wczytać zdjęcia, brak ścieżki folderu pracownika.");
        return;
    }
    
    String photoFolderPath = employeeFolderPath + "\\zdjecie\\"; 
    
    System.out.println("Próba wczytania zdjęcia pracownika z folderu: " + photoFolderPath); 
    
    File photoFolder = new File(photoFolderPath);

    if (photoFolder.exists() && photoFolder.isDirectory()) {
        try {
            
            System.out.println("Zawartość folderu zdjecie:");
            for (File file : photoFolder.listFiles()) {
                System.out.println(file.getName());
            }

            File[] files = photoFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
            
            if (files != null && files.length > 0) {
                File photoFile = new File(photoFolderPath + files[0].getName()); 
                Image image = new Image(photoFile.toURI().toString());
                myimage.setImage(image);
            } else {
                showAlert("Błąd", "Brak zdjęcia", "W folderze zdjecie pracownika nie ma zdjęcia.");
            }
        } catch (Exception e) {
            showAlert("Błąd", "Błąd wczytywania", "Wystąpił błąd podczas wczytywania zdjęcia.");
            e.printStackTrace();
        }
    } else {
        showAlert("Błąd", "Folder zdjecie nie istnieje", "Folder zdjecie wewnątrz folderu pracownika nie istnieje.");
    }
}

private void showAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
         loadEmployeePhoto();
    }
    }
