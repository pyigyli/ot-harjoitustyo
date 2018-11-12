
package ohjelmistotekniikka.application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
        final int screenWidth = 1200;
        final int screenHeight = 800;
        
    // Default gameplay settings
        Gamelogic game = new Gamelogic();
        // Default player names
        Player player1 = new Player(1, "Player 1");
        Player player2 = new Player(2, "Player 2");
        Player player3 = new Player(3, "Player 3");
        Player player4 = new Player(4, "Player 4");
        
    // Setup application interface elements
        // Menu buttons
        Button buttonNewGame = new Button("New game");
        Button buttonSettings = new Button("Settings");
        Button buttonScores = new Button("Scores");
        Button buttonRules = new Button("Rules");
        HBox menu = new HBox();
        menu.getChildren().addAll(buttonNewGame, buttonSettings, buttonScores, buttonRules);
        menu.setPadding(new Insets(50, 0, 0, 0));
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);
        
        // Application opening scene
        BorderPane welcomeScreen = new BorderPane();
        welcomeScreen.setTop(menu);
        Label welcomeLabel = new Label("Welcome to Connect Four 3D!");
        welcomeLabel.setFont(new Font("Arial", 30));
        welcomeScreen.setCenter(welcomeLabel);
        Scene welcomeScene = new Scene(welcomeScreen, screenWidth, screenHeight);
        
        // Gameplay scene
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
        selectZ.setPadding(new Insets(0, 0, 50, 0));
        Button placePiece = new Button("Place piece");
        VBox gameplayButtons = new VBox();
        gameplayButtons.getChildren().addAll(playerTurnLabel, Xaxis, selectX, Zaxis, selectZ, placePiece);
        gameplayButtons.setPadding(new Insets(0, 50, 0, 0));
        gameplayButtons.setMinWidth(300);
        gameplayButtons.setMaxWidth(300);
        gameplayButtons.setAlignment(Pos.CENTER);
        // Canvas
        GameCanvas canvas = new GameCanvas(game.getPlayers());
        HBox canvasNode = new HBox();
        canvasNode.getChildren().add(canvas.getCanvas());
        canvasNode.setPadding(new Insets(50, 0, 0, 60));
        // Combine all gameplay elemets
        BorderPane gameplayBorderpane = new BorderPane();
        gameplayBorderpane.setLeft(canvasNode);
        gameplayBorderpane.setRight(gameplayButtons);
        Scene gameplayView = new Scene(gameplayBorderpane, screenWidth, screenHeight);
        
        // Settings scene
        // Change player names -section
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
        GridPane setPlayerNames = new GridPane();
        setPlayerNames.setVgap(10);
        setPlayerNames.setHgap(10);
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
        setPlayerNames.setPadding(new Insets(0, 0, 0, 35));
        BorderPane playerNameChangeSection = new BorderPane();
        Label changeNamesText = new Label("Change player names");
        changeNamesText.setFont(new Font("Arial", 30));
        playerNameChangeSection.setTop(changeNamesText);
        playerNameChangeSection.setCenter(setPlayerNames);
        playerNameChangeSection.setPadding(new Insets(100, 0, 0, 150));
        // Change board size -section
        Label changeLengthText = new Label("Length:");
        Label changeWidthText = new Label("Width:");
        Label changeHeigthText = new Label("Height:");
        changeLengthText.setFont(new Font("Arial", 20));
        changeWidthText.setFont(new Font("Arial", 20));
        changeHeigthText.setFont(new Font("Arial", 20));
        changeLengthText.setPadding(new Insets(0, 0, 17, 0));
        changeWidthText.setPadding(new Insets(0, 0, 17, 0));
        changeHeigthText.setPadding(new Insets(0, 0, 17, 0));
        Slider changeLengthSlider = new Slider();
        changeLengthSlider.setValue(game.getLength());
        changeLengthSlider.setMin(4);
        changeLengthSlider.setMax(10);
        changeLengthSlider.setMajorTickUnit(1);
        changeLengthSlider.setMinorTickCount(0);
        changeLengthSlider.setSnapToTicks(true);
        changeLengthSlider.setShowTickMarks(true);
        changeLengthSlider.setShowTickLabels(true);
        changeLengthSlider.setMinWidth(270);
        changeLengthSlider.setMaxWidth(270);
        Slider changeWidthSlider = new Slider();
        changeWidthSlider.setValue(game.getWidth());
        changeWidthSlider.setMin(4);
        changeWidthSlider.setMax(10);
        changeWidthSlider.setMajorTickUnit(1);
        changeWidthSlider.setMinorTickCount(0);
        changeWidthSlider.setSnapToTicks(true);
        changeWidthSlider.setShowTickMarks(true);
        changeWidthSlider.setShowTickLabels(true);
        changeWidthSlider.setMinWidth(270);
        changeWidthSlider.setMaxWidth(270);
        Slider changeHeightSlider = new Slider();
        changeHeightSlider.setValue(game.getHeight());
        changeHeightSlider.setMin(4);
        changeHeightSlider.setMax(10);
        changeHeightSlider.setMajorTickUnit(1);
        changeHeightSlider.setMinorTickCount(0);
        changeHeightSlider.setSnapToTicks(true);
        changeHeightSlider.setShowTickMarks(true);
        changeHeightSlider.setShowTickLabels(true);
        changeHeightSlider.setMinWidth(270);
        changeHeightSlider.setMaxWidth(270);
        GridPane setBoardSizes = new GridPane();
        setBoardSizes.setVgap(10);
        setBoardSizes.setHgap(10);
        setBoardSizes.add(changeLengthText, 0, 0);
        setBoardSizes.add(changeWidthText, 0, 1);
        setBoardSizes.add(changeHeigthText, 0, 2);
        setBoardSizes.add(changeLengthSlider, 1, 0);
        setBoardSizes.add(changeWidthSlider, 1, 1);
        setBoardSizes.add(changeHeightSlider, 1, 2);
        BorderPane boardSizeChangeSection = new BorderPane();
        Label changeBoardSizesText = new Label("Change board sizes:");
        changeBoardSizesText.setFont(new Font("Arial", 30));
        changeBoardSizesText.setPadding(new Insets(0, 0, 30, 33));
        boardSizeChangeSection.setTop(changeBoardSizesText);
        boardSizeChangeSection.setCenter(setBoardSizes);
        boardSizeChangeSection.setPadding(new Insets(100, 200, 0, 0));
        // Change the number of players -section
        Label playerNumberText = new Label("Number of players:");
        playerNumberText.setFont(new Font("Arial", 20));
        playerNumberText.setPadding(new Insets(0, 20, 0, 0));
        ChoiceBox changePlayerNumberChoicebox = new ChoiceBox(FXCollections.observableArrayList("2", "3", "4"));
        changePlayerNumberChoicebox.setValue(game.getPlayers() + "");
        Label changePlayerNumberText = new Label("     Change the\nnumber of players:");
        changePlayerNumberText.setFont(new Font("Arial", 30));
        HBox changePlayerNumberHBox = new HBox();
        changePlayerNumberHBox.getChildren().addAll(playerNumberText, changePlayerNumberChoicebox);
        changePlayerNumberHBox.setPadding(new Insets(30, 0, 0, 10));
        VBox changePlayerNumberSelection = new VBox();
        changePlayerNumberSelection.getChildren().addAll(changePlayerNumberText, changePlayerNumberHBox);
        changePlayerNumberSelection.setPadding(new Insets(80, 0, 0, 45));
        // Combine all settings sections
        VBox settingsVBox = new VBox();
        settingsVBox.getChildren().addAll(boardSizeChangeSection, changePlayerNumberSelection);
        BorderPane settingsBorderpane = new BorderPane();
        settingsBorderpane.setLeft(playerNameChangeSection);
        settingsBorderpane.setRight(settingsVBox);
        Scene settingsView = new Scene(settingsBorderpane, screenWidth, screenHeight);
        
    // Buttons and other interface actions
        // Start new game with currently active settings
        buttonNewGame.setOnAction((event) -> {
            gameplayBorderpane.setTop(menu);
            selectX.setMax(game.getLength());
            selectZ.setMax(game.getWidth());
            window.setScene(gameplayView);
            canvas.update(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
        });
        
        // Open settings menu
        buttonSettings.setOnAction((event) -> {
            settingsBorderpane.setTop(menu);
            window.setScene(settingsView);
        });
        
        // Open scores menu
        
        // Open rules menu
        
    // Gameplay interface actions
    
    // Settings interface actions
        // Set player names
        player1setNameButton.setOnAction((event) -> {
            if (player1SetName.getText() != null && !player1SetName.getText().isEmpty()) {
                player1.setName(player1SetName.getText());
                player1NameText2.setText(player1.getName());
            }
        });
        player2setNameButton.setOnAction((event) -> {
            if (player2SetName.getText() != null && !player2SetName.getText().isEmpty()) {
                player2.setName(player2SetName.getText());
                player2NameText2.setText(player2.getName());
            }
        });
        player3setNameButton.setOnAction((event) -> {
            if (player3SetName.getText() != null && !player1SetName.getText().isEmpty()) {
                player3.setName(player3SetName.getText());
                player3NameText2.setText(player3.getName());
            }
        });
        player4setNameButton.setOnAction((event) -> {
            if (player4SetName.getText() != null && !player1SetName.getText().isEmpty()) {
                player4.setName(player4SetName.getText());
                player4NameText2.setText(player4.getName());
            }
        });
        // Set board sizes
        changeLengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                game.setLength(newValue.intValue());
            }
        });
        changeWidthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                game.setWidth(newValue.intValue());
            }
        });
        changeHeightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                game.setHeight(newValue.intValue());
            }
        });
        // Set the number of players
        changePlayerNumberChoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                game.setPlayers(newValue.intValue() + 2);
            }
        });
        
        window.setScene(welcomeScene);
        window.setTitle("Connect Four 3D");
        window.show();
    }
    
    public static void main(String[] args) {
        launch(App.class);
    }
}