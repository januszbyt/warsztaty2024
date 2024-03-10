package com.mycompany.warsztaty2024;

import java.io.IOException;
import java.net.URL;
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

public class PrimaryController implements Initializable {

@FXML private TableView<Osoba> tabela;
@FXML private TableColumn<Osoba, String> NazwiskoColumn;
@FXML private TableColumn<Osoba, String> ImieColumn;
@FXML private TableColumn<Osoba, Double> WiekColumn;

@FXML private TextField nazwisko_tekst;
@FXML private TextField imie_tekst;
@FXML private TextField wiek_tekst;

    
ObservableList<Osoba> dane = FXCollections.observableArrayList(
new Osoba("Baran", "Jan", 20.0),
new Osoba("Nowak", "Maciej", 30.0),
new Osoba("Nowak", "Anna", 30.0),
new Osoba("Les", "wacek", 41.0),
new Osoba("Duda", "Kamil", 21.0)
); 

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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
    String wiek = wiek_tekst.getText();
    Double w = Double.parseDouble(wiek);
    dane.add(new Osoba(nazwisko,imie,w));
    nazwisko_tekst.clear();
    imie_tekst.clear();
    wiek_tekst.clear();
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
    WiekColumn.setCellValueFactory(
    new PropertyValueFactory<Osoba, Double>("wiek"));
    }
}
