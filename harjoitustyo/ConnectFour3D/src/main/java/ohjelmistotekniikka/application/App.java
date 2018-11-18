
package ohjelmistotekniikka.application;

import Jama.Matrix;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ohjelmistotekniikka.gamelogic.Gamelogic;
import ohjelmistotekniikka.player.Player;
import ohjelmistotekniikka.scoredata.Database;
import ohjelmistotekniikka.scoredata.Score;
import ohjelmistotekniikka.scoredata.ScoreDao;

public class App extends Application {
    
    @Override
    public void start(Stage window) throws ClassNotFoundException {
        
        Database database = new Database("jdbc:sqlite:db/Scores.db");
        ScoreDao scoreDao = new ScoreDao(database);
        
    // Window settings
        final int screenWidth = 1200;
        final int screenHeight = 800;
        
    // Default gameplay settings
        Gamelogic game = new Gamelogic();
        // Default player names
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        Player player4 = new Player("Player 4");
        ArrayList<Player> players = new ArrayList<>();
        players.add(0, player1);
        players.add(1, player2);
        players.add(2, player3);
        players.add(3, player4);
        
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
        
        // Application opening and game end scene
        BorderPane welcomeScreen = new BorderPane();
        welcomeScreen.setTop(menu);
        Label welcomeLabel = new Label("Welcome to Connect Four 3D!");
        welcomeLabel.setFont(new Font("Arial", 30));
        welcomeScreen.setCenter(welcomeLabel);
        Scene welcomeScene = new Scene(welcomeScreen, screenWidth, screenHeight);
        
        // Gameplay scene
        // Camera controls
        Label cameraLabel = new Label("Camera");
        cameraLabel.setFont(new Font("Arial black", 18));
        cameraLabel.setPadding(new Insets(0, 0, 12, 43));
        Button resetCamera = new Button("Reset");
        HBox resetCameraHBox = new HBox();
        resetCameraHBox.getChildren().addAll(resetCamera);
        resetCameraHBox.setPadding(new Insets(0, 0, 5, 57));
        Label xLabel = new Label("Rotate X");
        Button xUp = new Button("Up");
        Button xDown = new Button("Down");
        Label yLabel = new Label("Rotate Y");
        Button yUp = new Button("Up");
        Button yDown = new Button("Down");
        Label zLabel = new Label("Rotate Z");
        Button zUp = new Button("Up");
        Button zDown = new Button("Down");
        Label moveLabel1 = new Label("Move");
        moveLabel1.setPadding(new Insets(10, 0, 0, 0));
        Button moveUp = new Button("Up");
        Button moveDown = new Button("Down");
        Label zoomLabel = new Label("Zoom");
        zoomLabel.setPadding(new Insets(10, 0, 0, 0));
        Button zoomIn = new Button("In");
        Button zoomOut = new Button("Out");
        Label moveLabel2 = new Label("Move");
        moveLabel2.setPadding(new Insets(10, 0, 0, 0));
        Button moveRight = new Button("Right");
        Button moveLeft = new Button("Left");
        GridPane rotateGrid = new GridPane();
        rotateGrid.addColumn(0, xLabel, xUp, xDown, moveLabel1, moveUp, moveDown);
        rotateGrid.addColumn(1, yLabel, yUp, yDown, zoomLabel, zoomIn, zoomOut);
        rotateGrid.addColumn(2, zLabel, zUp, zDown, moveLabel2, moveRight, moveLeft);
        rotateGrid.setVgap(5);
        rotateGrid.setHgap(10);
        GridPane.setHalignment(xLabel, HPos.CENTER);
        GridPane.setHalignment(xUp, HPos.CENTER);
        GridPane.setHalignment(xDown, HPos.CENTER);
        GridPane.setHalignment(yLabel, HPos.CENTER);
        GridPane.setHalignment(yUp, HPos.CENTER);
        GridPane.setHalignment(yDown, HPos.CENTER);
        GridPane.setHalignment(zLabel, HPos.CENTER);
        GridPane.setHalignment(zUp, HPos.CENTER);
        GridPane.setHalignment(zDown, HPos.CENTER);
        GridPane.setHalignment(moveLabel1, HPos.CENTER);
        GridPane.setHalignment(moveUp, HPos.CENTER);
        GridPane.setHalignment(moveDown, HPos.CENTER);
        GridPane.setHalignment(zoomLabel, HPos.CENTER);
        GridPane.setHalignment(zoomIn, HPos.CENTER);
        GridPane.setHalignment(zoomOut, HPos.CENTER);
        GridPane.setHalignment(moveLabel2, HPos.CENTER);
        GridPane.setHalignment(moveRight, HPos.CENTER);
        GridPane.setHalignment(moveLeft, HPos.CENTER);
        VBox cameraVBox = new VBox();
        cameraVBox.getChildren().addAll(cameraLabel, resetCameraHBox, rotateGrid);
        cameraVBox.setPadding(new Insets(50, 0, 50, 50));
        // Gameplay buttons and labels
        Label playerTurnLabel = new Label("Your turn,\n" + player1.getName());
        playerTurnLabel.setFont(new Font("Arial black", 18));
        playerTurnLabel.setTextFill(new Color(0.6, 0, 0, 1));
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
        gameplayButtons.setPadding(new Insets(0, 30, 0, 0));
        gameplayButtons.setMinWidth(300);
        gameplayButtons.setMaxWidth(300);
        gameplayButtons.setAlignment(Pos.CENTER);
        // Canvas
        GameCanvas canvas = new GameCanvas(game.getPlayers());
        HBox canvasNode = new HBox();
        canvasNode.getChildren().add(canvas.getCanvas());
        canvasNode.setPadding(new Insets(50, 0, 0, 60));
        // Combine all gameplay elemets
        VBox controlsVBox = new VBox();
        controlsVBox.getChildren().addAll(cameraVBox, gameplayButtons);
        BorderPane gameplayBorderpane = new BorderPane();
        gameplayBorderpane.setLeft(canvasNode);
        gameplayBorderpane.setRight(controlsVBox);
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
        Label player1NameText2 = new Label();
        Label player2NameText2 = new Label();
        Label player3NameText2 = new Label();
        Label player4NameText2 = new Label();
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
        setPlayerNames.addColumn(0, player1NameText1, player1SetName, player2NameText1, player2SetName,
                                    player3NameText1, player3SetName, player4NameText1, player4SetName);
        setPlayerNames.addColumn(1, player1NameText2, player1setNameButton, player2NameText2, player2setNameButton,
                                    player3NameText2, player3setNameButton, player4NameText2, player4setNameButton);
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
        setBoardSizes.addColumn(0, changeLengthText, changeWidthText, changeHeigthText);
        setBoardSizes.addColumn(1, changeLengthSlider, changeWidthSlider, changeHeightSlider);
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
        
        // Scores scene
        Label scoreColumnPlayer1 = new Label("Player 1");
        Label scoreColumnPlayer2 = new Label("Player 2");
        Label scoreColumnPlayer3 = new Label("Player 3");
        Label scoreColumnPlayer4 = new Label("Player 4");
        Label scoreColumnWinner = new Label("Winner");
        scoreColumnPlayer1.setFont(new Font("Arial", 20));
        scoreColumnPlayer2.setFont(new Font("Arial", 20));
        scoreColumnPlayer3.setFont(new Font("Arial", 20));
        scoreColumnPlayer4.setFont(new Font("Arial", 20));
        scoreColumnWinner.setFont(new Font("Arial", 20));
        HBox scoreColumnsVBox = new HBox();
        scoreColumnsVBox.getChildren().addAll(scoreColumnPlayer1, scoreColumnPlayer2, scoreColumnPlayer3, scoreColumnPlayer4, scoreColumnWinner);
        scoreColumnsVBox.setSpacing(150);
        scoreColumnsVBox.setPadding(new Insets(50, 0, 0, 10));
        ScrollPane scorelistPane = new ScrollPane();
        scorelistPane.setPrefSize(1, 650);
        scorelistPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        BorderPane scoreBorderpane = new BorderPane();
        scoreBorderpane.setCenter(scoreColumnsVBox);
        scoreBorderpane.setBottom(scorelistPane);
        Scene scoreView = new Scene(scoreBorderpane, screenWidth, screenHeight);
        
    // Menu button actions
        // Start new game with currently active settings
        buttonNewGame.setOnAction((event) -> {
            game.newGame();
            playerTurnLabel.setText("Your turn,\n" + player1.getName());
            playerTurnLabel.setTextFill(new Color(0.6, 0, 0, 1));
            gameplayBorderpane.setTop(menu);
            selectX.setMax(game.getWidth());
            selectZ.setMax(game.getLength());
            window.setScene(gameplayView);
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.setOffsetX(canvas.getScreenWidth() / 2);
            canvas.setOffsetY(canvas.getScreenHeight() / 2);
            canvas.rotateX(matrix, canvas.getAngleX() * -1);
            canvas.rotateY(matrix, canvas.getAngleY() * -1);
            canvas.rotateZ(matrix, canvas.getAngleZ() * -1);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        
        // Open settings menu
        buttonSettings.setOnAction((event) -> {
            settingsBorderpane.setTop(menu);
            window.setScene(settingsView);
        });
        
        // Open scores menu
        buttonScores.setOnAction((event) -> {
            VBox scoreVBox = new VBox();
            try {
                List<Score> scorelist;
                scorelist = scoreDao.findAll();
                for (Score score: scorelist) {
                    Label scoreLabelPlayer1 = new Label(score.getPlayer1());
                    Label scoreLabelPlayer2 = new Label(score.getPlayer2());
                    Label scoreLabelPlayer3 = new Label(score.getPlayer3());
                    Label scoreLabelPlayer4 = new Label(score.getPlayer4());
                    Label scoreLabelWinner = new Label(score.getWinner());
                    scoreLabelPlayer1.setFont(new Font("Arial", 16));
                    scoreLabelPlayer2.setFont(new Font("Arial", 16));
                    scoreLabelPlayer3.setFont(new Font("Arial", 16));
                    scoreLabelPlayer4.setFont(new Font("Arial", 16));
                    scoreLabelWinner.setFont(new Font("Arial", 16));
                    scoreLabelPlayer1.setMinWidth(223);
                    scoreLabelPlayer2.setMinWidth(224);
                    scoreLabelPlayer3.setMinWidth(223);
                    scoreLabelPlayer4.setMinWidth(225);
                    HBox scoreHBox = new HBox();
                    scoreHBox.getChildren().addAll(scoreLabelPlayer1, scoreLabelPlayer2, scoreLabelPlayer3, scoreLabelPlayer4, scoreLabelWinner);
                    scoreHBox.setPadding(new Insets(5, 0, 0, 10));
                    scoreVBox.getChildren().add(scoreHBox);
                }
            } catch (SQLException ex) {}
            scorelistPane.setContent(scoreVBox);
            scoreBorderpane.setTop(menu);
            window.setScene(scoreView);
        });
        
        // Open rules menu
        
    // Gameplay interface actions
        // Camera controls
        resetCamera.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.setScale(650);
            canvas.setOffsetX(canvas.getScreenWidth() / 2);
            canvas.setOffsetY(canvas.getScreenHeight() / 2);
            canvas.rotateX(matrix, canvas.getAngleX() * -1);
            canvas.rotateY(matrix, canvas.getAngleY() * -1);
            canvas.rotateZ(matrix, canvas.getAngleZ() * -1);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        xUp.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0.1);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        xDown.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, -0.1);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        yUp.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0.1);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        yDown.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, -0.1);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        zUp.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0.1);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        zDown.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, -0.1);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        moveUp.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeOffsetY(-10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        moveDown.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeOffsetY(10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        moveRight.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeOffsetX(10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        moveLeft.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeOffsetX(-10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        zoomIn.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeScale(10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        zoomOut.setOnAction((event) -> {
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.changeScale(-10);
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
        // Player input
        selectX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                canvas.setSelectX(newValue.intValue() - 1);
                Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
                canvas.rotateX(matrix, 0);
                canvas.rotateY(matrix, 0);
                canvas.rotateZ(matrix, 0);
                canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
            }
        });
        selectZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                canvas.setSelectZ(newValue.intValue() - 1);
                Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
                canvas.rotateX(matrix, 0);
                canvas.rotateY(matrix, 0);
                canvas.rotateZ(matrix, 0);
                canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
            }
        });
        placePiece.setOnAction((event) -> {
            if (game.placePiece((int) selectX.getValue() - 1, (int) selectZ.getValue() - 1, game.getBoard())) {
                int[][][] playerBoard = game.checkBoard(game.getTurn() % game.getPlayers());
                if (game.checkWin(playerBoard)) {
                    String winner = players.get(game.getTurn() % game.getPlayers()).getName();
                    welcomeLabel.setText("Congratulations " + winner);
                    welcomeScreen.setTop(menu);
                    window.setScene(welcomeScene);
                    Score score = new Score(player1.getName(), player2.getName(), player3.getName(), player4.getName(), winner);
                    if (game.getPlayers() < 4) {
                        score.setPlayer4("-");
                    }
                    if (game.getPlayers() < 3) {
                        score.setPlayer3("-");
                    }
                    try {
                        scoreDao.save(score);
                    } catch (SQLException ex) {}
                }
                game.nextTurn();
                playerTurnLabel.setText("Your turn,\n" + players.get(game.getTurn() % game.getPlayers()).getName());
                if (game.getTurn() % game.getPlayers() == 0) {
                    playerTurnLabel.setTextFill(new Color(0.6, 0, 0, 1));
                } else if (game.getTurn() % game.getPlayers() == 1) {
                    playerTurnLabel.setTextFill(new Color(0, 0.6, 0, 1));
                } else if (game.getTurn() % game.getPlayers() == 2) {
                    playerTurnLabel.setTextFill(new Color(0, 0, 0.6, 1));
                } else {
                    playerTurnLabel.setTextFill(new Color(0.6, 0.6, 0, 1));
                }
            } else {
                playerTurnLabel.setText("Column full,\n" + players.get(game.getTurn() % game.getPlayers()).getName());
            }
            Matrix matrix = canvas.boardToMatrix(game.getBoard(), game.getWidth(), game.getHeight(), game.getLength());
            canvas.rotateX(matrix, 0);
            canvas.rotateY(matrix, 0);
            canvas.rotateZ(matrix, 0);
            canvas.updateFrame(matrix, game.getWidth(), game.getHeight(), game.getLength());
        });
    
    // Settings interface actions
        // Set player names
        player1setNameButton.setOnAction((event) -> {
            if (player1SetName.getText() != null && !player1SetName.getText().isEmpty()) {
                if (player1SetName.getText().length() <= 10) {
                    player1.setName(player1SetName.getText());
                    player1NameText2.setText(player1.getName());
                } else {
                    player1.setName(player1SetName.getText().substring(0, 10));
                    player1NameText2.setText(player1.getName());
                }
            }
        });
        player2setNameButton.setOnAction((event) -> {
            if (player2SetName.getText() != null && !player2SetName.getText().isEmpty()) {
                if (player2SetName.getText().length() <= 10) {
                    player2.setName(player2SetName.getText());
                    player2NameText2.setText(player2.getName());
                } else {
                    player2.setName(player2SetName.getText().substring(0,10));
                    player2NameText2.setText(player2.getName());
                }
            }
        });
        player3setNameButton.setOnAction((event) -> {
            if (player3SetName.getText() != null && !player3SetName.getText().isEmpty()) {
                if (player3SetName.getText().length() <= 10) {
                    player3.setName(player3SetName.getText());
                    player3NameText2.setText(player3.getName());
                } else {
                    player3.setName(player3SetName.getText().substring(0, 10));
                    player3NameText2.setText(player3.getName());
                }
            }
        });
        player4setNameButton.setOnAction((event) -> {
            if (player4SetName.getText() != null && !player4SetName.getText().isEmpty()) {
                if (player4SetName.getText().length() <= 10) {
                    player4.setName(player4SetName.getText());
                    player4NameText2.setText(player4.getName());
                } else {
                    player4.setName(player4SetName.getText().substring(0, 10));
                    player4NameText2.setText(player4.getName());
                }
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