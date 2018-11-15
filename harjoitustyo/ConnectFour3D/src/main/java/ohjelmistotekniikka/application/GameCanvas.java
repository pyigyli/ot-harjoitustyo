
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
    private int scale;
    private int offsetX;
    private int offsetY;
    private double xangle;
    private double yangle;
    private double zangle;
    
    public GameCanvas(int players) {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.players = players;
        this.scale = 650;
        this.offsetX = this.width / 2;
        this.offsetY = this.height / 2;
        this.xangle = 0.0;
        this.yangle = 0.0;
        this.zangle = 0.0;
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
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
        // Clear the frame
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.width, this.height);
        // Project 3D coordinates to 2D using matrix multiplication
        Matrix matrix2D = new Matrix(width*height*length, 2);
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            matrix.set(i, 1, matrix.get(i, 1));
            double depthValue = matrix.getMatrix(i, i, 2, 2).trace();
            double[][] projectionMatrixValues = {{1 / (11 - depthValue), 0., 0.}, {0., 1 / (11 - depthValue), 0.}, {0., 0., 0.}};
            Matrix projectionMatrix = new Matrix(projectionMatrixValues);
            Matrix point = projectionMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix2D.set(i, 0, point.get(0, 0));
            matrix2D.set(i, 1, point.get(1, 0));
        }
        // Draw the lines of the board
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // If-else statements to check what lines to draw
                    if (i != width - 1) {
                        gc.setStroke(new Color(0.4,0,0,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY,
                                    matrix2D.getMatrix(index+length*height, index+length*height, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index+length*height, index+length*height, 0, 1).get(0, 1) * this.scale + this.offsetY);
                    }
                    if (j != height - 1) {
                        gc.setStroke(new Color(0,0.4,0,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY,
                                    matrix2D.getMatrix(index+length, index+length, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index+length, index+length, 0, 1).get(0, 1) * this.scale + this.offsetY);
                    }
                    if (k != length - 1) {
                        gc.setStroke(new Color(0,0,0.4,0.3));
                        gc.strokeLine(matrix2D.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix2D.getMatrix(index+1, index+1, 0, 1).get(0, 1) * this.scale + this.offsetY);
                    }
                    index++;
                }
            }
        }
    }
    
    // Used to rotate the board on the screen
    public Matrix rotateX(Matrix matrix, double turnangle) {
        this.xangle += turnangle;
        double[][] values = {{1., 0., 0.}, {0., cos(this.xangle), -sin(this.xangle)}, {0., sin(this.xangle), cos(this.xangle)}};
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
    public Matrix rotateY(Matrix matrix, double turnangle) {
        this.yangle += turnangle;
        double[][] values = {{cos(this.yangle), 0., -sin(this.yangle)}, {0., 1., 0.}, {sin(this.yangle), 0., cos(this.yangle)}};
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
    public Matrix rotateZ(Matrix matrix, double turnangle) {
        this.zangle += turnangle;
        double[][] values = {{cos(this.zangle), -sin(this.zangle), 0.}, {sin(this.zangle), cos(this.zangle), 0.}, {0., 0., 1.}};
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
    
    public void setScale(int value) {
        this.scale = value;
    }
    
    public void changeScale(int value) {
        this.scale += value;
    }
    
    public void setOffsetX(int value) {
        this.offsetX = value;
    }
    
    public void changeOffsetX(int value) {
        this.offsetX += value;
    }
    
    public void setOffsetY(int value) {
        this.offsetY = value;
    }
    
    public void changeOffsetY(int value) {
        this.offsetY += value;
    }
    
    public void setPlayers(int players) {
        this.players = players;
    }
    
    public Canvas getCanvas() {
        return this.canvas;
    }
    
    public double getAngleX() {
        return this.xangle;
    }
    
    public double getAngleY() {
        return this.yangle;
    }
    
    public double getAngleZ() {
        return this.zangle;
    }
    
    public int getScreenWidth() {
        return this.width;
    }
    
    public int getScreenHeight() {
        return this.height;
    }
}