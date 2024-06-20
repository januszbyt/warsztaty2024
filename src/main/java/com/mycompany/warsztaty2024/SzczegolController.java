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
import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Hyperlink;




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
    @FXML private Hyperlink show_paszport;
    @FXML private Hyperlink show_pj;
    @FXML private Hyperlink show_wiza;
    @FXML private Hyperlink show_kk;
    @FXML private Hyperlink show_zezwolenie;
         
    public static int pracownik_id;

    private void checkPaszportFolder() {
        String folderPath = linkszcz.getText() + "/paszport";
        File folder = new File(folderPath);
        
        String folderPath1 = linkszcz.getText() + "/prawo_jazdy";
        File folder1 = new File(folderPath1);
        
        String folderPath2 = linkszcz.getText() + "/wiza";
        File folder2 = new File(folderPath2);
        
        String folderPath3 = linkszcz.getText() + "/karta_kierowcy";
        File folder3 = new File(folderPath3);
        
        String folderPath4 = linkszcz.getText() + "/zezwolenie";
        File folder4 = new File(folderPath4);

        if (folder.exists()) {
            // Jeśli folder istnieje, ustaw hiperłącze jako aktywne
            show_paszport.setDisable(false);
        } else {
            // Jeśli folder nie istnieje, hiperłącze ma być nieaktywne
            show_paszport.setDisable(true);
        }
        
        if (folder1.exists()) {
            // Jeśli folder istnieje, ustaw hiperłącze jako aktywne
            show_pj.setDisable(false);
        } else {
            // Jeśli folder nie istnieje, hiperłącze ma być nieaktywne
            show_pj.setDisable(true);
        }
        
        if (folder2.exists()) {
            // Jeśli folder istnieje, ustaw hiperłącze jako aktywne
            show_wiza.setDisable(false);
        } else {
            // Jeśli folder nie istnieje, hiperłącze ma być nieaktywne
            show_wiza.setDisable(true);
        }
        
        if (folder3.exists()) {
            // Jeśli folder istnieje, ustaw hiperłącze jako aktywne
            show_kk.setDisable(false);
        } else {
            // Jeśli folder nie istnieje, hiperłącze ma być nieaktywne
            show_kk.setDisable(true);
        }
        
        if (folder4.exists()) {
            // Jeśli folder istnieje, ustaw hiperłącze jako aktywne
            show_zezwolenie.setDisable(false);
        } else {
            // Jeśli folder nie istnieje, hiperłącze ma być nieaktywne
            show_zezwolenie.setDisable(true);
        }
    }
    
@FXML
private void switchToDokumenty() throws IOException {
    // Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Dokumenty");

    // Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dokumenty.fxml"));
    Scene scene = new Scene(loader.load());

    // Zastosuj aktualny motyw do nowego okna
    applyCurrentTheme(scene);

    // Set view in window
    newWindow.setScene(scene);
    // Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
}

@FXML
private void switchToKarta_Kierowcy() throws IOException {
    pracownik_id = Integer.valueOf(idszcz.getText());
    // Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Karta kierowcy");

    // Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Karta_Kierowcy.fxml"));
    Scene scene = new Scene(loader.load());

    // Zastosuj aktualny motyw do nowego okna
    applyCurrentTheme(scene);

    // Set view in window
    newWindow.setScene(scene);
    // Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
}

@FXML
private void switchTowizy() throws IOException {
    pracownik_id = Integer.valueOf(idszcz.getText());
    // Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Wiza");

    // Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("wizy.fxml"));
    Scene scene = new Scene(loader.load());

    // Zastosuj aktualny motyw do nowego okna
    applyCurrentTheme(scene);

    // Set view in window
    newWindow.setScene(scene);
    // Launch
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
    Parent root = loader.load();

    // Access controller and set pracownik_id
    Prawo_JazdyController controller = loader.getController();
    controller.id_pracownika.setText(String.valueOf(pracownik_id));

    // Create scene
    Scene scene = new Scene(root);

    // Zastosuj aktualny motyw do nowego okna
    applyCurrentTheme(scene);

    // Set view in window
    newWindow.setScene(scene);
    // Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
}

@FXML
private void switchToPaszport() throws IOException {
    pracownik_id = Integer.valueOf(idszcz.getText());
    // Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Paszport");

    // Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("paszport.fxml"));
    Scene scene = new Scene(loader.load());

    // Zastosuj aktualny motyw do nowego okna
    applyCurrentTheme(scene);

    // Set view in window
    newWindow.setScene(scene);
    // Launch
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
}
@FXML
private void switchToZezwolenie() throws IOException {
    
    pracownik_id = Integer.valueOf(idszcz.getText());
    //Create Stage
    Stage newWindow = new Stage();
    newWindow.setTitle("Zezwolenie");
    //Create view from FXML
    FXMLLoader loader = new FXMLLoader(getClass().getResource("zezwolenie.fxml"));
     Scene scene = new Scene(loader.load());
    //Set view in window
    applyCurrentTheme(scene);
    
    
     newWindow.setScene(scene);
    //Launch
    
    newWindow.initModality(Modality.APPLICATION_MODAL);
    newWindow.showAndWait();
    }

private void applyCurrentTheme(Scene scene) {
    if (PrimaryController.isDarkTheme()) {
        String darkTheme = getClass().getResource("dark-thema.css").toExternalForm();
        scene.getStylesheets().add(darkTheme);
    }
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
            Path destinationFolderPath = Paths.get(folderPath, "zdjecie");

            // Usuń wszystkie pliki w folderze przed skopiowaniem nowego pliku
            File folder = destinationFolderPath.toFile();
            if (folder.exists() && folder.isDirectory()) {
                for (File file : folder.listFiles()) {
                    if (!file.isDirectory()) {
                        file.delete();
                    }
                }
            }

            Path destinationPath = destinationFolderPath.resolve(selectedFile.getName());

            Files.createDirectories(destinationFolderPath); // Utwórz podkatalog zdjecie, jeśli nie istnieje
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            showAlert("Błąd", "Błąd wczytywania lub kopiowania obrazu", "Wystąpił błąd podczas wczytywania lub kopiowania obrazu.");
            e.printStackTrace();
        }
    }
}

        @FXML
        private void paszportSkan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik skanu paszportu");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pdfFilter, allFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String folderPath = linkszcz.getText();
                File destinationDir = new File(folderPath, "paszport");

                // Tworzenie folderu "paszport" jeśli nie istnieje
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                    show_paszport.setDisable(false);
                }

                File destinationFile = new File(destinationDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Plik został zapisany pomyślnie w " + destinationDir.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Wystąpił błąd podczas zapisywania pliku.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
        @FXML
        private void showPaszport(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do wyświetlenia");

            String folderPath = linkszcz.getText();
            File initialDirectory = new File(folderPath, "paszport");

            if (initialDirectory.exists()) {
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie można otworzyć wybranego pliku: " + e.getMessage());
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setHeaderText(null);
                    alert.setContentText("Otwieranie plików nie jest wspierane na tej platformie.");
                    alert.showAndWait();
                }
            }
            
        }

        
        @FXML
        private void pjSkan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik skanu prawa jazdy");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pdfFilter, allFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String folderPath = linkszcz.getText();
                File destinationDir = new File(folderPath, "prawo_jazdy");

                // Tworzenie folderu "paszport" jeśli nie istnieje
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                    show_pj.setDisable(false);
                }

                File destinationFile = new File(destinationDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Plik został zapisany pomyślnie w " + destinationDir.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Wystąpił błąd podczas zapisywania pliku.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
        @FXML
        private void showPj(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do wyświetlenia");

            String folderPath = linkszcz.getText();
            File initialDirectory = new File(folderPath, "prawo_jazdy");

            if (initialDirectory.exists()) {
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie można otworzyć wybranego pliku: " + e.getMessage());
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setHeaderText(null);
                    alert.setContentText("Otwieranie plików nie jest wspierane na tej platformie.");
                    alert.showAndWait();
                }
            }
        }
        
        @FXML
        private void wizaSkan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik skanu wizy");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pdfFilter, allFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String folderPath = linkszcz.getText();
                File destinationDir = new File(folderPath, "wiza");

                // Tworzenie folderu "paszport" jeśli nie istnieje
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                    show_wiza.setDisable(false);
                }

                File destinationFile = new File(destinationDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Plik został zapisany pomyślnie w " + destinationDir.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Wystąpił błąd podczas zapisywania pliku.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
        @FXML
        private void showWiza(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do wyświetlenia");

            String folderPath = linkszcz.getText();
            File initialDirectory = new File(folderPath, "wiza");

            if (initialDirectory.exists()) {
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie można otworzyć wybranego pliku: " + e.getMessage());
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setHeaderText(null);
                    alert.setContentText("Otwieranie plików nie jest wspierane na tej platformie.");
                    alert.showAndWait();
                }
            }
            
        }
        
        @FXML
        private void kkSkan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik skanu karty kierowcy");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pdfFilter, allFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String folderPath = linkszcz.getText();
                File destinationDir = new File(folderPath, "karta_kierowcy");

                // Tworzenie folderu "paszport" jeśli nie istnieje
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                    show_kk.setDisable(false);
                }

                File destinationFile = new File(destinationDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Plik został zapisany pomyślnie w " + destinationDir.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Wystąpił błąd podczas zapisywania pliku.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
        @FXML
        private void showKk(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do wyświetlenia");

            String folderPath = linkszcz.getText();
            File initialDirectory = new File(folderPath, "karta_kierowcy");

            if (initialDirectory.exists()) {
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie można otworzyć wybranego pliku: " + e.getMessage());
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setHeaderText(null);
                    alert.setContentText("Otwieranie plików nie jest wspierane na tej platformie.");
                    alert.showAndWait();
                }
            }
            
        }
        
        @FXML
        private void zezwolenieSkan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik skanu zezwolenia");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF Files (*.pdf)", "*.pdf");
        FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(jpgFilter, pdfFilter, allFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                String folderPath = linkszcz.getText();
                File destinationDir = new File(folderPath, "zezwolenie");

                // Tworzenie folderu "paszport" jeśli nie istnieje
                if (!destinationDir.exists()) {
                    destinationDir.mkdirs();
                    show_zezwolenie.setDisable(false);
                }

                File destinationFile = new File(destinationDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukces");
                alert.setHeaderText(null);
                alert.setContentText("Plik został zapisany pomyślnie w " + destinationDir.getAbsolutePath());
                alert.showAndWait();

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Wystąpił błąd podczas zapisywania pliku.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
    
    
        @FXML
        private void showZezwolenie(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wybierz plik do wyświetlenia");

            String folderPath = linkszcz.getText();
            File initialDirectory = new File(folderPath, "zezwolenie");

            if (initialDirectory.exists()) {
                fileChooser.setInitialDirectory(initialDirectory);
            }

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(selectedFile);
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Błąd");
                        alert.setHeaderText(null);
                        alert.setContentText("Nie można otworzyć wybranego pliku: " + e.getMessage());
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Błąd");
                    alert.setHeaderText(null);
                    alert.setContentText("Otwieranie plików nie jest wspierane na tej platformie.");
                    alert.showAndWait();
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
    
        String updateQuery = "UPDATE pracownik SET nazwisko=?, imie=?, email=?, adres=?, telefon=?, narodowosc=?, zrodlo=?, status=?, link=? WHERE pracownik_id=?";
        PreparedStatement pstmt = connection.prepareStatement(updateQuery);

        pstmt.setString(1, nazwiskoszcz.getText());
        pstmt.setString(2, imieszcz.getText());
        pstmt.setString(3, mailszcz.getText());
        pstmt.setString(4, adresszcz.getText());
        pstmt.setString(5, telszcz.getText());
        pstmt.setString(6, narodszcz.getText());
        pstmt.setString(7, zrodloszcz.getText());
        pstmt.setString(8, statusszcz.getText());
        pstmt.setString(9, linkszcz.getText());
        pstmt.setInt(10, Integer.valueOf(idszcz.getText()));

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
         checkPaszportFolder();
    }
    }
