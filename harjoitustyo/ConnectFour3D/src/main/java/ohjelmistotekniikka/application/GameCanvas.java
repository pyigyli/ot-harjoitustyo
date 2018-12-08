
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
    private int[][][] board;
    private int scale;
    private int offsetX;
    private int offsetY;
    private double xangle;
    private double yangle;
    private double zangle;
    private int selectX;
    private int selectZ;
    
    public GameCanvas() {
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.scale = 0;
        this.offsetX = this.width / 2;
        this.offsetY = this.height / 2;
        this.xangle = 0.0;
        this.yangle = 0.0;
        this.zangle = 0.0;
        this.selectX = 0;
        this.selectZ = 0;
        // Create white background
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);
        gc.setLineWidth(2);
    }
    
    // Converts 3D array into a matrix
    public Matrix boardToMatrix(int[][][] board, int width, int height, int length) {
        this.board = board;
        // Make a new matrix using Jama-library
        Matrix matrix = new Matrix(width*height*length, 3);
        int index = 0;
        // Make a matrix that has every 3D coordinate point
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    // Center the perspective in the middle of the array.
                    if (width % 2 == 0) {
                        matrix.set(index, 0, (i - ((width / 2) - 0.5)));
                    } else {
                        matrix.set(index, 0, (i - (width / 2)));
                    }
                    if (width % 2 == 0) {
                        matrix.set(index, 1, (-j + (height / 2) - 0.5));
                    } else {
                        matrix.set(index, 1, (-j + (height / 2)));
                    }
                    if (width % 2 == 0) {
                        matrix.set(index, 2, (-k + (length / 2) - 0.5));
                    } else {
                        matrix.set(index, 2, (-k + (length / 2)));
                    }
                    index++;
                }
            }
        }
        return matrix;
    }
    
    // Project 3D coordinates to 2D using matrix multiplication
    public Matrix projection(Matrix matrix3D, int width, int height, int length) {
        Matrix matrix2D = new Matrix(width*height*length, 2);
        for (int i = 0; i < matrix3D.getRowDimension(); i++) {
            matrix3D.set(i, 1, matrix3D.get(i, 1));
            double depthValue = matrix3D.getMatrix(i, i, 2, 2).trace();
            double[][] projectionMatrixValues = {{1 / (15 - depthValue), 0., 0.}, {0., 1 / (15 - depthValue), 0.}, {0., 0., 0.}};
            Matrix projectionMatrix = new Matrix(projectionMatrixValues);
            Matrix point = projectionMatrix.times(matrix3D.getMatrix(i, i, 0, 2).transpose());
            matrix2D.set(i, 0, point.get(0, 0));
            matrix2D.set(i, 1, point.get(1, 0));
        }
        return matrix2D;
    }
    
    // Draws the board and every piece in it
    public void drawBoard(Matrix matrix, int width, int height, int length) {
        // Clear the frame
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, this.width, this.height);
        // Draw the lines of the board
        this.drawLines(matrix, width, height, length, "X");
        this.drawLines(matrix, width, height, length, "Y");
        this.drawLines(matrix, width, height, length, "Z");
        // Draw thicker line to indicate what coordinate the player is currently choosing
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    if (j != height - 1 && i == this.selectX && k == this.selectZ) {
                        gc.setStroke(new Color(0.1, 0.1, 0.1, 0.4));
                        gc.setLineWidth(7);
                        gc.strokeLine(matrix.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                matrix.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY,
                                matrix.getMatrix(index+length, index+length, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                matrix.getMatrix(index+length, index+length, 0, 1).get(0, 1) * this.scale + this.offsetY);
                        gc.setLineWidth(2);
                    }
                    index++;
                }
            }
        }
        // Draw players' pieces on the board
        this.drawPieces(matrix, width, height, length, 0, new Color(1, 0, 0, 0.6));
        this.drawPieces(matrix, width, height, length, 1, new Color(0, 1, 0, 0.6));
        this.drawPieces(matrix, width, height, length, 2, new Color(0, 0, 1, 0.6));
        this.drawPieces(matrix, width, height, length, 3, new Color(0.9, 0.9, 0, 0.6));
    }
    
    // Draw the lines of an axis specified by the parameters
    public void drawLines(Matrix matrix, int width, int height, int length, String axis) {
        // Values are set for X-axis by default.
        int matrixPointValue = length*height;
        Color color = new Color(0.3,0,0,0.4);
        if (axis.equals("Y")) {
            matrixPointValue = length;
            color = new Color(0,0.3,0,0.4);
        } else if (axis.equals("Z")) {
            matrixPointValue = 1;
            color = new Color(0,0,0.3,0.4);
        }
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    if ((axis.equals("X") && i != width - 1) || (axis.equals("Y") && j != height - 1) || (axis.equals("Z") && k != length - 1)) {
                        gc.setStroke(color);
                        gc.strokeLine(matrix.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY,
                                    matrix.getMatrix(index+matrixPointValue, index+matrixPointValue, 0, 1).get(0, 0) * this.scale + this.offsetX,
                                    matrix.getMatrix(index+matrixPointValue, index+matrixPointValue, 0, 1).get(0, 1) * this.scale + this.offsetY);
                    }
                    index++;
                }
            }
        }
    }
    
    // Draw the pieces of a player specified by the paramiters
    public void drawPieces(Matrix matrix, int width, int height, int length, int player, Color playerColor) {
        int index = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    if (this.board[i][j][k] == player) {
                        gc.setFill(playerColor);
                        gc.fillOval(matrix.getMatrix(index, index, 0, 1).get(0, 0) * this.scale + this.offsetX - 10,
                                    matrix.getMatrix(index, index, 0, 1).get(0, 1) * this.scale + this.offsetY - 10, 20, 20);
                    }
                    index++;
                }
            }
        }
    }
    
    // Used to rotate the board on the screen
    public Matrix rotate(Matrix matrix, double turnangle, String axis) {
        Matrix rotationMatrix;
        if (axis.equals("X")) {
            this.xangle += turnangle;
            double[][] values = {{1., 0., 0.}, {0., cos(this.xangle), -sin(this.xangle)}, {0., sin(this.xangle), cos(this.xangle)}};
            rotationMatrix = new Matrix(values);
        } else if (axis.equals("Y")) {
            this.yangle += turnangle;
            double[][] values = {{cos(this.yangle), 0., -sin(this.yangle)}, {0., 1., 0.}, {sin(this.yangle), 0., cos(this.yangle)}};
            rotationMatrix = new Matrix(values);
        } else {
            this.zangle += turnangle;
            double[][] values = {{cos(this.zangle), -sin(this.zangle), 0.}, {sin(this.zangle), cos(this.zangle), 0.}, {0., 0., 1.}};
            rotationMatrix = new Matrix(values);
        }
        // Convert each 3D point into a 2D point.
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            Matrix point = rotationMatrix.times(matrix.getMatrix(i, i, 0, 2).transpose());
            matrix.set(i, 0, point.get(0, 0));
            matrix.set(i, 1, point.get(1, 0));
            matrix.set(i, 2, point.get(2, 0));
        }
        return matrix;
    }
    
    public void setSelectX(int value) {
        this.selectX = value;
    }
    
    public void setSelectZ(int value) {
        this.selectZ = value;
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