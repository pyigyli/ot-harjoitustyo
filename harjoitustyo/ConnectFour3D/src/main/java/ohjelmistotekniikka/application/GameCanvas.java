
package ohjelmistotekniikka.application;

import Jama.Matrix;
import java.util.HashMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas {
    
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final int width = 770;
    private final int height = 650;
    private int players;
    
    public GameCanvas(int players) {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.players = players;
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3);
    }
    
    // Draws the board and every piece in it
    public void update(int[][][] board, int width, int height, int length) {
        // Make a new matrix using Jama-library
        Matrix matrix = new Matrix(width*height*length, 3);
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // Make a matrix that has every 3D coordinate point
                    matrix.set(index, 0, i*60);
                    matrix.set(index, 1, j*60);
                    matrix.set(index, 2, k*60);
                    index++;
                }
            }
        }
        // Project 3D coordinates to 2D using matrix multiplication
        double[][] projectionMatrixValues = {{1., 0., 0.}, {0., 1., 0.}, {0., 0., 0.}};
        Matrix projectionMatrix = new Matrix(projectionMatrixValues);
        Matrix matrix2D = new Matrix(width*height*length, 2);
        // Convert 3D points into a 2D points.
        for (int i = 0; i < matrix2D.getRowDimension(); i++) {
            Matrix point = projectionMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix2D.set(i, 0, point.get(0, 0));
            matrix2D.set(i, 1, point.get(1, 0));
        }
        // Draw the board
        index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // If-else statements to check what lines to draw
                    if (index < width*height*length - 1 && k != length-1) {
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0)+150,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1)+150,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 0)+150,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 1)+150);
                    }
                    index++;
                }
            }
        }
    }
    
    public void rotate() {
        
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public Canvas getCanvas() {
        return this.canvas;
    }
}