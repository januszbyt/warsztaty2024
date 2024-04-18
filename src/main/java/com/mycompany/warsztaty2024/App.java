package com.mycompany.warsztaty2024;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Application;

public class App extends Application {

    private static Scene loginScene;
    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        
        Parent loginRoot = loadFXML("login");
        loginScene = new Scene(loginRoot, 250, 180);

       
        Parent mainRoot = loadFXML("primary");
        mainScene = new Scene(mainRoot, 1000, 500);

        
        stage.setScene(loginScene);
        stage.show();
    }

    static void setMainScene() {
        Stage stage = (Stage) loginScene.getWindow();
        stage.setScene(mainScene);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}