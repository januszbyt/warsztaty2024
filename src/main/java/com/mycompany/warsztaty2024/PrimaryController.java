package com.mycompany.warsztaty2024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
@FXML private Stage stage;
@FXML private TableView<Osoba> tabela;
@FXML private TableColumn<Osoba, Integer> IDColumn;
@FXML private TableColumn<Osoba, String> NazwiskoColumn;
@FXML private TableColumn<Osoba, String> ImieColumn;
@FXML private TableColumn<Osoba, String> EmailColumn;
@FXML private TableColumn<Osoba, String> AdresColumn;
@FXML private TableColumn<Osoba, String> TelefonColumn;
@FXML private TableColumn<Osoba, String> NarodowoscColumn;
@FXML private TableColumn<Osoba, String> ZrodloColumn;
@FXML private TableColumn<Osoba, String> StatusColumn;
@FXML private TableColumn<Osoba, String> LinkColumn;

@FXML private TextField ID_tekst;
@FXML private TextField nazwisko_tekst;
@FXML private TextField imie_tekst;
@FXML private TextField Email_tekst;
@FXML private TextField Adres_tekst;
@FXML private TextField Telefon_tekst;
@FXML private TextField Narodowosc_tekst;
@FXML private TextField Zrodlo_tekst;
@FXML private TextField Status_tekst;
@FXML private TextField Link_tekst;



ObservableList<Osoba> dane = FXCollections.observableArrayList(
new Osoba(1, "", "", "","","","","","","")

        ); 

public static Osoba wybranaOsobaDalej;

@FXML
private void open_Szczegol() throws IOException {
    wybranaOsobaDalej = tabela.getSelectionModel().getSelectedItem();

    // Sprawdź, czy coś jest zaznaczone
    if (wybranaOsobaDalej != null) {
        Stage newWindow = new Stage();
        newWindow.setTitle("Dane Szczegółowe");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("szczegol.fxml"));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.initModality(Modality.APPLICATION_MODAL);

        // Dodaj listener na zamknięcie okna
        newWindow.setOnHiding(event -> {
            // Uruchom metodę testBaza, aby odświeżyć dane w tabeli
            testBaza(null);
        });

        newWindow.showAndWait();
    } else {
        // Jeśli nic nie jest zaznaczone, wyświetl komunikat
        Platform.runLater(() -> {
            // Wyświetl alert z informacją dla użytkownika
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano żadnego pracownika do wyświetlenia szczegółów.");
            alert.showAndWait();
        });
    }
}

@FXML
private void testBaza(ActionEvent event) {
    Task<Void> task;
    task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            try {
                dane.clear();
                // Pobierz połączenie z bazą danych
                Connection connection = DatabaseConnection.getConnection();
                
                // Utwórz zapytanie SQL
                String query = "SELECT * FROM pracownik";

                // Utwórz obiekt Statement
                try (Statement statement = connection.createStatement()) {
                    // Wykonaj zapytanie
                    ResultSet resultSet = statement.executeQuery(query);

                    // Lista do przechowywania pobranych danych
                    ObservableList<Osoba> osoby = FXCollections.observableArrayList();

                    // Przejdź przez wyniki zapytania i dodaj je do listy
                    while (resultSet.next()) {
                        int pracownik_id = resultSet.getInt("pracownik_id");
                        String nazwisko = resultSet.getString("nazwisko");
                        String imie = resultSet.getString("imie");
                        String email = resultSet.getString("email");
                        String adres = resultSet.getString("adres");
                        String telefon = resultSet.getString("telefon");
                        String narodowosc = resultSet.getString("narodowosc");
                        String zrodlo = resultSet.getString("zrodlo");
                        String status = resultSet.getString("status");
                        String link = resultSet.getString("link");
                        dane.add(new Osoba(pracownik_id,nazwisko, imie, email, adres, telefon, narodowosc, zrodlo, status, link));
                        //Osoba osoba = new Osoba(pracownik_id,nazwisko, imie, email, adres, telefon, narodowosc, zrodlo, status, link);
                        //osoby.add(osoba);
                    }

                    // Zamknij zasoby
                    resultSet.close();

                    // Aktualizuj interfejs użytkownika na głównym wątku
                    Platform.runLater(() -> {
                        // Wyczyść dane w tabeli
                       

                        // Dodaj wszystkie pobrane osoby do tabeli
                        tabela.getItems().addAll(osoby);
                    });
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    new Thread(task).start();
}


@FXML
private void zamknijAplikacjeAction(ActionEvent event) {
Platform.exit();
}

@FXML
    private void usunelementAction(ActionEvent event) {
        dane.remove(tabela.getSelectionModel().getSelectedItem());
    }
/*
@FXML
private void dodajelementAction(ActionEvent event) {
    int ID = ID_tekst.getText();
    String nazwisko = nazwisko_tekst.getText();
    String imie = imie_tekst.getText();
    String Email = Email_tekst.getText();
    String Adres = Adres_tekst.getText();
    String Telefon = Telefon_tekst.getText();
    String Narodowosc = Narodowosc_tekst.getText();
    String Zrodlo = Zrodlo_tekst.getText();
    String Status = Status_tekst.getText();
    String Link = Link_tekst.getText();
    
    
    
    dane.add(new Osoba(ID,nazwisko,imie,Email,Adres,Telefon,Narodowosc,Zrodlo,Status,Link));
    ID_tekst.clear();
    nazwisko_tekst.clear();
    imie_tekst.clear();
    Email_tekst.clear();
    Adres_tekst.clear();
    Telefon_tekst.clear();
    Narodowosc_tekst.clear();
    Zrodlo_tekst.clear();
    Status_tekst.clear();
    Link_tekst.clear();
    }


 private void ladujDane(String filename) {
 List<Osoba> books = new ArrayList<>();
 Path pathToFile = Paths.get(filename);

 try (BufferedReader br = Files.newBufferedReader(pathToFile,
 StandardCharsets.US_ASCII)) {

 //odczyt pierwszej linii z pliku tekstowego
 String line = br.readLine();

 //pętla odczytująca kolejne linie z pliku
 while (line != null) {
 //używamy funkcji split aby odczytać kolejne dane rozdzielone znakiem ";"
 String[] attributes = line.split(";");

 //utworzenie obiektu Osoba na podstawie danych odczytanych z jednej linii pliku
 //dodanie obiektu Osoba do listy dane
 
 Osoba o2 = new Osoba(attributes[0], attributes[1],attributes[2] ,attributes[3],attributes[4],attributes[5],attributes[6],attributes[7],attributes[8],attributes[9] );
 dane.add(o2);
 // odczyt kolejnej linii z pliku przed powtórzeniem pętli
 //jeżeli dojdziemy do końca pliku obiekt „line” będzie null
 line = br.readLine();
 }
 } catch (IOException ioe) {
 ioe.printStackTrace();
 }
 }
*/
    
     
    
    
    
@FXML
private void zapiszDoBazy(ActionEvent event) {
    if (czyWszystkiePolaUzupelnione()) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                   
                    Connection connection = DatabaseConnection.getConnection(); 

                    
                    String query = "INSERT INTO pracownik (nazwisko, imie, email, adres, telefon, narodowosc, zrodlo, status,link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        
                        
                        preparedStatement.setString(1, nazwisko_tekst.getText());
                        preparedStatement.setString(2, imie_tekst.getText());
                        preparedStatement.setString(3, Email_tekst.getText());
                        preparedStatement.setString(4, Adres_tekst.getText());
                        preparedStatement.setString(5, Telefon_tekst.getText());
                        preparedStatement.setString(6, Narodowosc_tekst.getText());
                        preparedStatement.setString(7, Zrodlo_tekst.getText());
                        preparedStatement.setString(8, Status_tekst.getText());
                        preparedStatement.setString(9, Link_tekst.getText());

                        
                        preparedStatement.executeUpdate();
                        testBaza(event);
                    }

                    
                    connection.close();

                   
                    Platform.runLater(() -> {
                    
                        nazwisko_tekst.clear();
                        imie_tekst.clear();
                        Email_tekst.clear();
                        Adres_tekst.clear();
                        Telefon_tekst.clear();
                        Narodowosc_tekst.clear();
                        Zrodlo_tekst.clear();
                        Status_tekst.clear();
                        Link_tekst.clear();
                        
                        
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                    
                }
                return null;
            }
        };

        new Thread(task).start();
    } else {
        // Wyświetlenie komunikatu o uzupełnieniu wszystkich pól
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Brakujące pola");
        alert.setHeaderText(null);
        alert.setContentText("Uzupełnij wszystkie pola");
        alert.showAndWait();
    }
}

private boolean czyWszystkiePolaUzupelnione() {
    return !nazwisko_tekst.getText().isEmpty() &&
           !imie_tekst.getText().isEmpty() &&
           !Email_tekst.getText().isEmpty() &&
           !Adres_tekst.getText().isEmpty() &&
           !Telefon_tekst.getText().isEmpty() &&
           !Narodowosc_tekst.getText().isEmpty() &&
           !Zrodlo_tekst.getText().isEmpty() &&
           !Status_tekst.getText().isEmpty() &&
           !Link_tekst.getText().isEmpty();
}




    @FXML
private void usunZBazy(ActionEvent event) {
    // Pobierz zaznaczony wiersz w tabeli
    Osoba wybranaOsoba = tabela.getSelectionModel().getSelectedItem();

    // Sprawdź, czy coś jest zaznaczone
    if (wybranaOsoba != null) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    // Pobierz połączenie z bazą danych
                    Connection connection = DatabaseConnection.getConnection();

                    // Utwórz zapytanie SQL
                    String query = "DELETE FROM pracownik WHERE pracownik_id=?";

                    // Utwórz prepared statement
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        // Ustaw parametry
                        preparedStatement.setInt(1, wybranaOsoba.getID());
                       

                        // Wykonaj zapytanie
                        preparedStatement.executeUpdate();
                    }

                    // Zamknij połączenie
                    connection.close();

                    // Aktualizuj interfejs użytkownika na głównym wątku
                    Platform.runLater(() -> {
                        // Usuń zaznaczony wiersz z tabeli
                        tabela.getItems().remove(wybranaOsoba);
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        new Thread(task).start();
    } else {
        // Jeśli nic nie jest zaznaczone, wyświetl komunikat
        // Tutaj możesz dodać dowolną obsługę, np. wyświetlić alert z informacją dla użytkownika
    }
}
    private void wybierzFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz folder");
        
        Stage currentStage = (Stage) tabela.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(currentStage);

        if (selectedDirectory != null) {
            Link_tekst.setText(selectedDirectory.getAbsolutePath());
        }
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       
       
    // Ustawienie danych dla tabeli
    tabela.itemsProperty().setValue(dane);
    // Powiązanie pierwszej kolumny z polem nazwisko obiektu typu Osoba
    IDColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, Integer>("ID"));
    NazwiskoColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("nazwisko"));
    // Powiązanie drugiej kolumny z polem Imie obiektu typu Osoba
    ImieColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("imie"));
    // Powiązanie trzeciej kolumny z polem wiek obiektu typu Osoba
    EmailColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Email"));
    AdresColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Adres"));
    TelefonColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Telefon"));
     NarodowoscColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Narodowosc"));
      ZrodloColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Zrodlo"));
       StatusColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Status"));
        LinkColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, String>("Link"));
           Link_tekst.setOnMouseClicked(event -> {
            wybierzFolder();
        });
     
           
         
        tabela.setItems(dane);
        testBaza(null);
        
        
        
     
        
    }
}
