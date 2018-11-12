
package ohjelmistotekniikka.application;

import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas {
    
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final int width = 770;
    private final int height = 650;
    private int players;
    private final ArrayList<Vector> coordinates;
    
    public GameCanvas(int players) {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.players = players;
        this.coordinates = new ArrayList<>();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
    }
    
    // Draws the board and every piece in it
    public void update(int[][][] board, int width, int height, int length) {
        // Set a coordinate for every slot in the board
        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                for (int k = 1; k <= length; k++) {
                    Vector vector = new Vector();
                    vector.add(i);
                    vector.add(j);
                    vector.add(k);
                    coordinates.add(vector);
                }
            }
        }
        // Projection matrix multiplication here
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public Canvas getCanvas() {
        return this.canvas;
    }
}