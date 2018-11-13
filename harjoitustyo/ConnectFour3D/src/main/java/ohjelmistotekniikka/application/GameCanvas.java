
package ohjelmistotekniikka.application;

import Jama.Matrix;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
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
        gc.setLineWidth(2);
    }
    
    // Converts 3D array into a matrix
    public Matrix boardToMatrix(int[][][] board, int width, int height, int length) {
        // Make a new matrix using Jama-library
        Matrix matrix = new Matrix(width*height*length, 3);
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // Make a matrix that has every 3D coordinate point
                    matrix.set(index, 0, (i - (width / 2)));
                    matrix.set(index, 1, (j - (height / 2)));
                    matrix.set(index, 2, (k - (length / 2)));
                    index++;
                }
            }
        }
        return matrix;
    }
    
    // Draws the board and every piece in it
    public void updateFrame(Matrix matrix, int width, int height, int length) {
        // Project 3D coordinates to 2D using matrix multiplication
        Matrix matrix2D = new Matrix(width*height*length, 2);
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            matrix.set(i, 1, matrix.get(i, 1) - 1.5);
            double depthValue = matrix.getMatrix(i, i, 2, 2).trace();
            double[][] projectionMatrixValues = {{1 / (11 - depthValue), 0., 0.}, {0., 1 / (11 - depthValue), 0.}, {0., 0., 0.}};
            Matrix projectionMatrix = new Matrix(projectionMatrixValues);
            Matrix point = projectionMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix2D.set(i, 0, point.get(0, 0));
            matrix2D.set(i, 1, point.get(1, 0));
        }
        // Draw the lines of the board
        int scale = 600;
        int offsetX = this.width / 2;
        int offsetY = this.height / 2 + 140;
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // If-else statements to check what lines to draw
                    if (i != width - 1) {
                        gc.setStroke(new Color(0,0,0,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * scale + offsetY,
                                    matrix2D.getMatrix(index+length*height, index+length*height, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index+length*height, index+length*height, 0, 1).get(0, 1) * scale + offsetY);
                    }
                    if (j != height - 1) {
                        gc.setStroke(new Color(0,0,0,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * scale + offsetY,
                                    matrix2D.getMatrix(index+length, index+length, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index+length, index+length, 0, 1).get(0, 1) * scale + offsetY);
                    }
                    if (k != length - 1) {
                        gc.setStroke(new Color(0,0,0,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * scale + offsetY,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 0) * scale + offsetX,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 1) * scale + offsetY);
                    }
                    index++;
                }
            }
        }
    }
    
    // Used to rotate the board on the screen
    public Matrix rotateX(Matrix matrix) {
        double angle = -0.2;
        double[][] values = {{1., 0., 0.}, {0., cos(angle), -sin(angle)}, {0., sin(angle), cos(angle)}};
        Matrix rotationMatrix = new Matrix(values);
        // Convert each 3D point into a 2D point.
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            Matrix point = rotationMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix.set(i, 0, point.get(0, 0));
            matrix.set(i, 1, point.get(1, 0));
            matrix.set(i, 2, point.get(2, 0));
        }
        return matrix;
    }
    
    // Used to rotate the board on the screen
    public Matrix rotateY(Matrix matrix) {
        double angle = 0.42;
        double[][] values = {{cos(angle), 0., -sin(angle)}, {0., 1., 0.}, {sin(angle), 0., cos(angle)}};
        Matrix rotationMatrix = new Matrix(values);
        // Convert each 3D point into a 2D point.
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            Matrix point = rotationMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix.set(i, 0, point.get(0, 0));
            matrix.set(i, 1, point.get(1, 0));
            matrix.set(i, 2, point.get(2, 0));
        }
        return matrix;
    }
    
    // Used to rotate the board on the screen
    public Matrix rotateZ(Matrix matrix) {
        double angle = 0.1;
        double[][] values = {{cos(angle), -sin(angle), 0.}, {sin(angle), cos(angle), 0.}, {0., 0., 1.}};
        Matrix rotationMatrix = new Matrix(values);
        // Convert each 3D point into a 2D point.
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            Matrix point = rotationMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix.set(i, 0, point.get(0, 0));
            matrix.set(i, 1, point.get(1, 0));
            matrix.set(i, 2, point.get(2, 0));
        }
        return matrix;
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public Canvas getCanvas() {
        return this.canvas;
    }
}