package com.mycompany.warsztaty2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene loginScene;
    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        loginScene = new Scene(loginRoot, 250, 200);
        stage.setTitle("Logowanie");

        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent mainRoot = mainLoader.load();
        mainScene = new Scene(mainRoot, 900, 600);

        // Ustawienie stage w kontrolerze
        PrimaryController primaryController = mainLoader.getController();
        primaryController.setStage(stage);

        stage.setScene(loginScene);
        stage.show();
    }

    static void setMainScene() {
        Stage primaryStage = (Stage) loginScene.getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Start");
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }
    
    
}