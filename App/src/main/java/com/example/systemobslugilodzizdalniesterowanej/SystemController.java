package com.example.systemobslugilodzizdalniesterowanej;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class SystemController implements Initializable{

    Stage stage;
    Boolean networkStatus;
    Map map = new Map();
    public Stage getStage() {
        return stage;
    }
    public SystemController(Stage stage1){
        this.stage=stage1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        networkStatus=false;
        lightPower.setText("0%");
        exit.setCancelButton(true);
        exit.setFocusTraversable(false);
        try {
            checkConnectionWithInternet();
            gps.getChildren().add(map.getMapView());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private Label connectionStatus;

    @FXML
    private Label networkConnection;

    @FXML
    private AnchorPane gps;

    @FXML
    private Button leftFlap;

    @FXML
    private Button lightDown;

    @FXML
    private Label lightPower;

    @FXML
    private Button lightUp;

    @FXML
    private Button moveDown;

    @FXML
    private Button moveLeft;

    @FXML
    private Button moveRight;

    @FXML
    private Button moveUp;

    @FXML
    private Button rightFlap;

    @FXML
    private Button exit;

    public Button getLeftFlap() {
        return leftFlap;
    }

    public Button getRightFlap() {
        return rightFlap;
    }

    public Button getLightDown() {
        return lightDown;
    }

    public Button getLightUp() {
        return lightUp;
    }

    public Button getMoveDown() {
        return moveDown;
    }

    public Button getMoveLeft() {
        return moveLeft;
    }

    public Button getMoveRight() {
        return moveRight;
    }

    public Button getMoveUp() {
        return moveUp;
    }

    public Label getConnectionStatus() {
        return connectionStatus;
    }

    public Label getLightPower() {
        return lightPower;
    }

    public Map getMap() {
        return map;
    }

    public Label getNetworkConnection() {
        return networkConnection;
    }

    public Boolean getNetworkStatus() {
        return networkStatus;
    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialog-window.fxml"));
        Stage dialogStage = new Stage();
        DialogController dialogController = new DialogController(stage, dialogStage);
        fxmlLoader.setController(dialogController);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        dialogStage.setTitle("Zamknij aplikacje");
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void dialogNotConnect(String title, String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public void checkConnectionWithInternet() throws InterruptedException, IOException {
        try {
            URL url1 = new URL("https://www.geeksforgeeks.org/");
            URLConnection connection = url1.openConnection();
            connection.connect();
            networkConnection.setText("Polaczono z internetem!");
            networkStatus=true;
        }
        catch (Exception e) {
            dialogNotConnect("Brak internetu","Aplikacja nie moze polaczyc sie z internetem!");
            getNetworkConnection().setTextFill(Color.color(1, 0, 0));
            getNetworkConnection().setText("Brak polaczenia z internetem! Brak lokalizacji!");
        }
    }
}
