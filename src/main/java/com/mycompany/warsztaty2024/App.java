package com.mycompany.warsztaty2024;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Modality;

public class App extends Application {

    private static Scene loginScene;
    private static Scene mainScene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        
        FXMLLoader loginLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Parent loginRoot = loginLoader.load();
        loginScene = new Scene(loginRoot, 250, 200);
        stage.setTitle("Logowanie");

        FXMLLoader mainLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Parent mainRoot = mainLoader.load();
        mainScene = new Scene(mainRoot, 909, 600);

        // Ustawienie stage w kontrolerze
        PrimaryController primaryController = mainLoader.getController();
        primaryController.setStage(stage);
        primaryController.setMainScene(mainScene); // Przekazujemy mainScene do kontrolera

        stage.setScene(loginScene);
        stage.show();
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static Scene getLoginScene() {
        return loginScene;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    static void setMainScene() {
        // Create a new Stage (window)
        Stage modalStage = new Stage();

        // Set the owner of the new Stage
        Stage primaryStage = (Stage) loginScene.getWindow();
        modalStage.initOwner(primaryStage);
              
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.setScene(mainScene);
        modalStage.setTitle("Start");
        modalStage.centerOnScreen();
        System.out.println("1111");
        primaryStage.close();
        modalStage.showAndWait();
        System.out.println("22222222");
    }

    public static void main(String[] args) {
        launch();
    }
}
