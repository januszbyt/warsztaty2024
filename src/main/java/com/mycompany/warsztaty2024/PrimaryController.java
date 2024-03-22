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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
@FXML private Stage stage;
@FXML private TableView<Osoba> tabela;
@FXML private TableColumn<Osoba, String> NazwiskoColumn;
@FXML private TableColumn<Osoba, String> ImieColumn;
@FXML private TableColumn<Osoba, String> EmailColumn;
@FXML private TableColumn<Osoba, String> AdresColumn;
@FXML private TableColumn<Osoba, String> TelefonColumn;
@FXML private TableColumn<Osoba, String> NarodowoscColumn;
@FXML private TableColumn<Osoba, String> ZrodloColumn;
@FXML private TableColumn<Osoba, String> StatusColumn;

@FXML private TextField nazwisko_tekst;
@FXML private TextField imie_tekst;
@FXML private TextField Email_tekst;
@FXML private TextField Adres_tekst;
@FXML private TextField Telefon_tekst;
@FXML private TextField Narodowosc_tekst;
@FXML private TextField Zrodlo_tekst;
@FXML private TextField Status_tekst;

    
ObservableList<Osoba> dane = FXCollections.observableArrayList(
new Osoba("Baran", "Jan", "20.0", "Przemysl","111222333","cos","cos","cos"),
new Osoba("Nowak", "Maciej", "20.0", "Przeworsk","111222333","cos","cos","cos"),
new Osoba("Nowak", "Anna", "20.0", "Lancut","111222333","cos","cos","cos"),
new Osoba("Les", "wacek", "20.0", "Rzeszow","111222333","cos","cos","cos"),
new Osoba("Duda", "Kamil", "20.0", "Jarosław","111222333","cos","cos","cos")
); 

@FXML
private void switchToSecondary() throws IOException {
    App.setRoot("secondary");
    }
@FXML
    private void testBaza(ActionEvent event) throws SQLException {
           Connection connection = DatabaseConnection.getConnection(); 
    }


@FXML
private void zamknijAplikacjeAction(ActionEvent event) {
Platform.exit();
}

@FXML
    private void usunelementAction(ActionEvent event) {
        dane.remove(tabela.getSelectionModel().getSelectedItem());
    }

@FXML
private void dodajelementAction(ActionEvent event) {
    String nazwisko = nazwisko_tekst.getText();
    String imie = imie_tekst.getText();
    String Email = Email_tekst.getText();
    String Adres = Adres_tekst.getText();
    String Telefon = Telefon_tekst.getText();
    String Narodowosc = Narodowosc_tekst.getText();
    String Zrodlo = Zrodlo_tekst.getText();
    String Status = Status_tekst.getText();
    
    
    
    dane.add(new Osoba(nazwisko,imie,Email,Adres,Telefon,Narodowosc,Zrodlo,Status));
    nazwisko_tekst.clear();
    imie_tekst.clear();
    Email_tekst.clear();
    Adres_tekst.clear();
    Telefon_tekst.clear();
    Narodowosc_tekst.clear();
    Zrodlo_tekst.clear();
    Status_tekst.clear();
    }
@FXML
 private void otworzPlikAction(ActionEvent event) {
 // Tworzymy kontrolkę (okienko) służącą do wybierania pliku
 FileChooser fileChooser = new FileChooser();
 // Tytuł okienka
 fileChooser.setTitle("Otwórz Plik");
 // Dodajemy filtr rodzaju pliku - tu plików txt
 fileChooser.getExtensionFilters().add(
 new ExtensionFilter("Pliki TXT", "*.txt")
 );
 // Pokaż okno
 File plik = fileChooser.showOpenDialog(stage);
 // Jeśli zamkniemy fileChooser nie wybierając pliku zostanie zwrócony null
 // Jeśli wybierzemy plik, podejmujemy odpowiednie działania
 if (plik != null) {
 // Wyswietlenie w terminalu ścieżki do pliku.
 System.out.println("Plik: "+ plik.getAbsolutePath());
 //wywołanie funkcji odczytującej dane z pliku
 ladujDane(plik.getAbsolutePath());
 }
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
 
 Osoba o2 = new Osoba(attributes[0], attributes[1],attributes[2] ,attributes[3],attributes[4],attributes[5],attributes[6],attributes[7] );
 dane.add(o2);
 // odczyt kolejnej linii z pliku przed powtórzeniem pętli
 //jeżeli dojdziemy do końca pliku obiekt „line” będzie null
 line = br.readLine();
 }
 } catch (IOException ioe) {
 ioe.printStackTrace();
 }
 }

 @FXML
private void zapiszDoPlikuAction(ActionEvent event) {
    // Tworzymy kontrolkę (okienko) służącą do wybierania miejsca zapisu pliku
    FileChooser fileChooser = new FileChooser();
    // Tytuł okienka
    fileChooser.setTitle("Zapisz Plik");
    // Dodajemy filtr rodzaju pliku - tu plików txt
    fileChooser.getExtensionFilters().add(
        new ExtensionFilter("Pliki TXT", "*.txt")
    );
    // Pokaż okno
    File plik = fileChooser.showSaveDialog(stage);
    
    // Jeśli zamkniemy fileChooser nie wybierając miejsca zapisu zostanie zwrócony null
    // Jeśli wybierzemy miejsce zapisu, podejmujemy odpowiednie działania
    if (plik != null) {
        // Wywołanie funkcji zapisującej dane do pliku
        zapiszDaneDoPliku(plik);
    }
}

private void zapiszDaneDoPliku(File plik) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(plik))) {
        // Pętla przechodząca przez wszystkie elementy w tabeli
        for (Osoba osoba : tabela.getItems()) {
            // Tworzymy string zawierający dane jednej osoby w formacie: Nazwisko;Imie;Wiek
            String linia = osoba.getNazwisko() + ";" + osoba.getImie() + ";" + osoba.getEmail() + ";" + osoba.getAdres() + ";" + osoba.getTelefon() 
                    + ";" + osoba.getNarodowosc() + ";" + osoba.getZrodlo() + ";" + osoba.getStatus() + "\n";
            // Zapisujemy tę linię do pliku
            writer.write(linia);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    // Ustawienie danych dla tabeli
    tabela.itemsProperty().setValue(dane);
    // Powiązanie pierwszej kolumny z polem nazwisko obiektu typu Osoba
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
    }
}
