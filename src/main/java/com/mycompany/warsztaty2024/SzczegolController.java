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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.stage.DirectoryChooser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;





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
   boolean status;
    @FXML private CheckBox edycja;
         
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
private void switchTowizy() throws IOException {
    
    pracownik_id = Integer.valueOf(idszcz.getText());
    //Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Wiza");
    //Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("wizy.fxml"));
    //Set view in window
    newWindow.setScene(new Scene(loader.load()));
    //Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
    }

@FXML
private void switchToPrawo_jazdy() throws IOException {
    pracownik_id = Integer.valueOf(idszcz.getText());
    // Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Prawo Jazdy");
    // Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Prawo_jazdy.fxml"));
    // Set view in window
    Parent root = loader.load();
    // Access controller and set pracownik_id
    Prawo_JazdyController controller = loader.getController();
    controller.id_pracownika.setText(String.valueOf(pracownik_id)); // Set pracownik_id
    // Set view in window
    newWindow.setScene(new Scene(root));
    // Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
}
@FXML
private void switchToPaszport() throws IOException {
    
    pracownik_id = Integer.valueOf(idszcz.getText());
    //Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Paszport");
    //Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("paszport.fxml"));
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


@FXML
private void wybierzPlik(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Wybierz plik JPG");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pliki JPG", "*.jpg"));

    Stage currentStage = (Stage) nazwiskoszcz.getScene().getWindow(); // Pobranie obecnej sceny
    File selectedFile = fileChooser.showOpenDialog(currentStage); // Pokaż dialog wyboru pliku

    if (selectedFile != null) {
        try {
            // Wczytaj obrazek do ImageView
            Image image = new Image(selectedFile.toURI().toString());
            myimage.setImage(image); // Ustawienie wybranej grafiki w ImageView

            // Kopiuj plik do folderu i podfolderu
            String folderPath = linkszcz.getText();
            Path sourcePath = selectedFile.toPath();
            Path destinationPath = Paths.get(folderPath, "zdjecie", selectedFile.getName());

            Files.createDirectories(destinationPath.getParent()); // Utwórz podkatalog zdjecie, jeśli nie istnieje
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            showAlert("Błąd", "Błąd wczytywania lub kopiowania obrazu", "Wystąpił błąd podczas wczytywania lub kopiowania obrazu.");
            e.printStackTrace();
        }
    }
}

@FXML
private void openImage(MouseEvent event) {
    try {
        if (event.getClickCount() == 2) { // Podwójne kliknięcie
            // Utwórz nowe okno
            Stage imageStage = new Stage();
            imageStage.setTitle("Zdjęcie");

            // Utwórz obrazek
            Image image = myimage.getImage();

            // Utwórz widok obrazka
            ImageView imageView = new ImageView(image);

            // Ustaw zachowanie skalowania obrazka
            imageView.setPreserveRatio(true);

            // Utwórz kontener StackPane, aby umieścić obrazek na środku
            StackPane rootPane = new StackPane();
            rootPane.getChildren().add(imageView);

            // Utwórz kontener ScrollPane
            ScrollPane scrollPane = new ScrollPane(rootPane);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setPannable(true);

            // Utwórz scenę i dodaj ScrollPane
            Scene scene = new Scene(scrollPane);
            imageStage.setScene(scene);

            // Obsługa zoomowania za pomocą przewijania myszką
            scrollPane.addEventFilter(ScrollEvent.ANY, event1 -> {
                double zoomFactor = event1.getDeltaY() > 0 ? 1.1 : 0.9; // Powiększ lub pomniejsz
                double oldWidth = imageView.getBoundsInParent().getWidth();
                double oldHeight = imageView.getBoundsInParent().getHeight();
                double newWidth = oldWidth * zoomFactor;
                double newHeight = oldHeight * zoomFactor;

                // Przeskaluj obrazek zachowując proporcje
                imageView.setScaleX(imageView.getScaleX() * zoomFactor);
                imageView.setScaleY(imageView.getScaleY() * zoomFactor);

                // Przeskaluj kontener StackPane
                rootPane.setPrefWidth(newWidth);
                rootPane.setPrefHeight(newHeight);

                // Przeskaluj kontener ScrollPane
                scrollPane.setPrefViewportWidth(newWidth);
                scrollPane.setPrefViewportHeight(newHeight);

                // Dostosuj rozmiar okna
                imageStage.setWidth(newWidth + 20); // Dodajemy 20 pikseli, aby zapobiec przesunięciu pojawiającego się paska przewijania
                imageStage.setHeight(newHeight + 20); // Dodajemy 20 pikseli, aby zapobiec przesunięciu pojawiającego się paska przewijania
            });

            // Ustaw rozmiar okna na początkowy rozmiar obrazka
            imageStage.setWidth(image.getWidth() + 20); // Dodajemy 20 pikseli, aby zapobiec przesunięciu pojawiającego się paska przewijania
            imageStage.setHeight(image.getHeight() + 20); // Dodajemy 20 pikseli, aby zapobiec przesunięciu pojawiającego się paska przewijania

            // Wyśrodkuj obrazek w kontenerze StackPane
            rootPane.setAlignment(Pos.CENTER);

            // Wyśrodkuj okno na ekranie
            imageStage.centerOnScreen();

            // Wyświetl okno
            imageStage.show();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@FXML
private void zapiszDane() {
    try {
        Connection connection = DatabaseConnection.getConnection();
    
        String updateQuery = "UPDATE pracownik SET nazwisko=?, imie=?, email=?, adres=?, telefon=?, narodowosc=?, zrodlo=?, status=? WHERE pracownik_id=?";
        PreparedStatement pstmt = connection.prepareStatement(updateQuery);

        pstmt.setString(1, nazwiskoszcz.getText());
        pstmt.setString(2, imieszcz.getText());
        pstmt.setString(3, mailszcz.getText());
        pstmt.setString(4, adresszcz.getText());
        pstmt.setString(5, telszcz.getText());
        pstmt.setString(6, narodszcz.getText());
        pstmt.setString(7, zrodloszcz.getText());
        pstmt.setString(8, statusszcz.getText());
        pstmt.setInt(9, Integer.valueOf(idszcz.getText()));

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            showAlert("Sukces", "Dane zaktualizowane", "Dane pracownika zostały zapisane w bazie danych.");
        } else {
            showAlert("Błąd", "Nie udało się zaktualizować danych", "Wystąpił problem podczas aktualizacji danych pracownika.");
        }

        pstmt.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        showAlert("Błąd", "Błąd bazy danych", "Wystąpił problem podczas zapisywania danych pracownika do bazy danych.");
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
    } 
}


@FXML
    public void openPhoto() throws IOException {
    
    //pracownik_id = Integer.valueOf(idszcz.getText());
    //Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Okno wyboru zdjęcia/skanu");
    //Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Wyswietl_Zdjecie.fxml"));
    //Set view in window
    newWindow.setScene(new Scene(loader.load()));
    //Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
    }


private void showAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}

private void aktualizujFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz folder");
        
        Stage currentStage = (Stage) linkszcz.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(currentStage);

        if (selectedDirectory != null) {
            linkszcz.setText(selectedDirectory.getAbsolutePath());
        }
    }

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idszcz.setText(Integer.toString(PrimaryController.wybranaOsobaDalej.getID()));
        idszcz.setDisable(true);
        nazwiskoszcz.setText(PrimaryController.wybranaOsobaDalej.getNazwisko());
        nazwiskoszcz.setDisable(true);
        imieszcz.setText(PrimaryController.wybranaOsobaDalej.getImie());
        imieszcz.setDisable(true);
        mailszcz.setText(PrimaryController.wybranaOsobaDalej.getEmail());
        mailszcz.setDisable(true);
        adresszcz.setText(PrimaryController.wybranaOsobaDalej.getAdres());
        adresszcz.setDisable(true);
        telszcz.setText(PrimaryController.wybranaOsobaDalej.getTelefon());
        telszcz.setDisable(true);
        narodszcz.setText(PrimaryController.wybranaOsobaDalej.getNarodowosc());
        narodszcz.setDisable(true);
        zrodloszcz.setText(PrimaryController.wybranaOsobaDalej.getZrodlo());
        zrodloszcz.setDisable(true);
        statusszcz.setText(PrimaryController.wybranaOsobaDalej.getStatus());
        statusszcz.setDisable(true);
        linkszcz.setText(PrimaryController.wybranaOsobaDalej.getLink());
        linkszcz.setDisable(true);
        
        linkszcz.setOnMouseClicked(event -> {
            aktualizujFolder();
        });
        
      
            edycja.setOnAction(event -> {
            boolean wlaczenieEdycji = edycja.isSelected();
            // Ustawienie stanu edycji pól tekstowych na podstawie zaznaczenia CheckBoxa
            nazwiskoszcz.setDisable(!wlaczenieEdycji);
            imieszcz.setDisable(!wlaczenieEdycji);
            mailszcz.setDisable(!wlaczenieEdycji);
            adresszcz.setDisable(!wlaczenieEdycji);
            telszcz.setDisable(!wlaczenieEdycji);
            narodszcz.setDisable(!wlaczenieEdycji);
            zrodloszcz.setDisable(!wlaczenieEdycji);
            statusszcz.setDisable(!wlaczenieEdycji);
            linkszcz.setDisable(!wlaczenieEdycji);
        });
        
        
        
         loadEmployeePhoto();
    }
    }
        
 