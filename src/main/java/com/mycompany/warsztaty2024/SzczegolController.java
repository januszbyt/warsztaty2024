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
    String mainFolderPath = "C:\\skany\\";
    String employeeFolderName = idszcz.getText(); // Pobranie ID pracownika jako nazwa folderu
    String employeeFolderPath = mainFolderPath + employeeFolderName;
    
    System.out.println("Próba otwarcia folderu pracownika: " + employeeFolderPath); // Wydrukowanie ścieżki folderu pracownika
    
    File employeeFolder = new File(employeeFolderPath);
    
    if (employeeFolder.exists() && employeeFolder.isDirectory()) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                // Dla systemów Windows
                Runtime.getRuntime().exec("explorer.exe " + employeeFolder.getAbsolutePath());
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                // Dla systemów macOS
                Runtime.getRuntime().exec("open " + employeeFolder.getAbsolutePath());
            } else {
                // Dla systemów Linux
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
    String mainFolderPath = "C:\\skany\\";
    String employeeFolderName = idszcz.getText(); // Pobranie ID pracownika jako nazwa folderu
    if (employeeFolderName == null || employeeFolderName.trim().isEmpty()) {
        showAlert("Błąd", "Brak ID pracownika", "Nie można wczytać zdjęcia, brak ID pracownika.");
        return;
    }
    
    String employeeFolderPath = mainFolderPath + employeeFolderName + "\\zdjecie\\";
    
    System.out.println("Próba wczytania zdjęcia pracownika z folderu: " + employeeFolderPath); // Wydrukowanie ścieżki folderu pracownika
    
    File employeeFolder = new File(employeeFolderPath);

    if (employeeFolder.exists() && employeeFolder.isDirectory()) {
        try {
            // Wydrukuj zawartość folderu dla celów diagnostycznych
            System.out.println("Zawartość folderu:");
            for (File file : employeeFolder.listFiles()) {
                System.out.println(file.getName());
            }

            File[] files = employeeFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
            
            if (files != null && files.length > 0) {
                File photoFile = new File(employeeFolderPath + files[0].getName()); 
                Image image = new Image(photoFile.toURI().toString());
                myimage.setImage(image);
            } else {
                showAlert("Błąd", "Brak zdjęcia", "W folderze pracownika nie ma zdjęcia.");
            }
        } catch (Exception e) {
            showAlert("Błąd", "Błąd wczytywania", "Wystąpił błąd podczas wczytywania zdjęcia.");
            e.printStackTrace();
        }
    } else {
        showAlert("Błąd", "Folder nie istnieje", "Folder pracownika nie istnieje.");
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
