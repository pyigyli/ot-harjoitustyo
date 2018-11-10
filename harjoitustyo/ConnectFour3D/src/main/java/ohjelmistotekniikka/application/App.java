
package ohjelmistotekniikka.application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ohjelmistotekniikka.gamelogic.Gamelogic;
import ohjelmistotekniikka.player.Player;

public class App extends Application {
    
    @Override
    public void start(Stage window) {
        
    // Window settings
        int screenWidth = 1200;
        int screenHeight = 800;
        
    // Default gameplay settings
        int players = 2;
        // Board size
        int length = 7;
        int width = 7;
        int height = 7;
        //Player names and their turn order
        Player player1 = new Player(1, "Player 1");
        Player player2 = new Player(2, "Player 2");
        Player player3 = new Player(3, "Player 3");
        Player player4 = new Player(4, "Player 4");
        
        Gamelogic game = new Gamelogic(players, length, width, height);
        
    // Setup application interface elements
        // Menu buttons
        Button buttonNewGame = new Button("New game");
        Button buttonSettings = new Button("Settings");
        Button buttonRules = new Button("Rules");
        Button buttonScores = new Button("Scores");
        HBox menu = new HBox();
        menu.getChildren().addAll(buttonNewGame, buttonSettings, buttonRules, buttonScores);
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);
    
        // Gameplay buttons and labels
        Label playerTurnLabel = new Label("Your turn,\n" + player1.getName());
        playerTurnLabel.setFont(new Font("Arial black", 18));
        playerTurnLabel.setPadding(new Insets(0, 0, 30, 0));
        Label Xaxis = new Label("X-axis");
        Xaxis.setFont(new Font("Arial", 16));
        Slider selectX = new Slider();
        selectX.setMin(1);
        selectX.setMajorTickUnit(1);
        selectX.setMinorTickCount(0);
        selectX.setSnapToTicks(true);
        selectX.setShowTickMarks(true);
        selectX.setShowTickLabels(true);
        Label Zaxis = new Label("Z-axis");
        Zaxis.setFont(new Font("Arial", 16));
        Zaxis.setPadding(new Insets(20, 0, 0, 0));
        Slider selectZ = new Slider();
        selectZ.setMin(1);
        selectZ.setMajorTickUnit(1);
        selectZ.setMinorTickCount(0);
        selectZ.setSnapToTicks(true);
        selectZ.setShowTickMarks(true);
        selectZ.setShowTickLabels(true);
        VBox gameplayButtons = new VBox();
        gameplayButtons.getChildren().addAll(playerTurnLabel, Xaxis, selectX, Zaxis, selectZ);
        gameplayButtons.setPadding(new Insets(20, 20, 20, 20));
        gameplayButtons.setMinWidth(300);
        gameplayButtons.setMaxWidth(300);
        gameplayButtons.setAlignment(Pos.CENTER);
        
        // Initial application opening scene
        BorderPane welcomeScreen = new BorderPane();
        welcomeScreen.setTop(menu);
        Label welcomeLabel = new Label("Welcome to Connect Four 3D!");
        welcomeLabel.setFont(new Font("Arial", 30));
        welcomeScreen.setCenter(welcomeLabel);
        Scene welcomeScene = new Scene(welcomeScreen, screenWidth, screenHeight);
        
        // Initialize gameplay scene
        BorderPane gameplayBorderpane = new BorderPane();
        gameplayBorderpane.setRight(gameplayButtons);
        Scene gameplayView = new Scene(gameplayBorderpane, screenWidth, screenHeight);
        
        // Initialize settings scene
        GridPane setPlayerNames = new GridPane();
        setPlayerNames.setVgap(10);
        setPlayerNames.setHgap(10);
        Label player1NameText1 = new Label("Player 1:");
        Label player2NameText1 = new Label("Player 2:");
        Label player3NameText1 = new Label("Player 3:");
        Label player4NameText1 = new Label("Player 4:");
        player1NameText1.setFont(new Font("Arial", 20));
        player2NameText1.setFont(new Font("Arial", 20));
        player3NameText1.setFont(new Font("Arial", 20));
        player4NameText1.setFont(new Font("Arial", 20));
        player1NameText1.setPadding(new Insets(30, 0, 0, 0));
        player2NameText1.setPadding(new Insets(30, 0, 0, 0));
        player3NameText1.setPadding(new Insets(30, 0, 0, 0));
        player4NameText1.setPadding(new Insets(30, 0, 0, 0));
        Label player1NameText2 = new Label(player1.getName());
        Label player2NameText2 = new Label(player2.getName());
        Label player3NameText2 = new Label(player3.getName());
        Label player4NameText2 = new Label(player4.getName());
        player1NameText2.setFont(new Font("Arial", 20));
        player2NameText2.setFont(new Font("Arial", 20));
        player3NameText2.setFont(new Font("Arial", 20));
        player4NameText2.setFont(new Font("Arial", 20));
        player1NameText2.setPadding(new Insets(30, 0, 0, 0));
        player2NameText2.setPadding(new Insets(30, 0, 0, 0));
        player3NameText2.setPadding(new Insets(30, 0, 0, 0));
        player4NameText2.setPadding(new Insets(30, 0, 0, 0));
        TextField player1SetName = new TextField();
        TextField player2SetName = new TextField();
        TextField player3SetName = new TextField();
        TextField player4SetName = new TextField();
        Button player1setNameButton = new Button("Set name");
        Button player2setNameButton = new Button("Set name");
        Button player3setNameButton = new Button("Set name");
        Button player4setNameButton = new Button("Set name");
        setPlayerNames.add(player1NameText1, 0, 0);
        setPlayerNames.add(player2NameText1, 0, 2);
        setPlayerNames.add(player3NameText1, 0, 4);
        setPlayerNames.add(player4NameText1, 0, 6);
        setPlayerNames.add(player1NameText2, 1, 0);
        setPlayerNames.add(player2NameText2, 1, 2);
        setPlayerNames.add(player3NameText2, 1, 4);
        setPlayerNames.add(player4NameText2, 1, 6);
        setPlayerNames.add(player1SetName, 0, 1);
        setPlayerNames.add(player2SetName, 0, 3);
        setPlayerNames.add(player3SetName, 0, 5);
        setPlayerNames.add(player4SetName, 0, 7);
        setPlayerNames.add(player1setNameButton, 1, 1);
        setPlayerNames.add(player2setNameButton, 1, 3);
        setPlayerNames.add(player3setNameButton, 1, 5);
        setPlayerNames.add(player4setNameButton, 1, 7);
        setPlayerNames.setPadding(new Insets(50, 50, 50, 50));
        
        Label changeNamesText = new Label("Change player names");
        
        Scene settingsView = new Scene(setPlayerNames, screenWidth, screenHeight);
        
        // Start new game with currently active settings
        buttonNewGame.setOnAction((event) -> {
            gameplayBorderpane.setTop(menu);
            selectX.setMax(length);
            selectZ.setMax(width);
            window.setScene(gameplayView);
        });
        
        // Open settings menu
        buttonSettings.setOnAction((event) -> {
            gameplayBorderpane.setTop(menu);
            window.setScene(settingsView);
        });
        
        window.setScene(welcomeScene);
        window.setTitle("Connect Four 3D");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(App.class);
    }
}
