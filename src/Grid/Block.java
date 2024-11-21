package Grid;

import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Block {

    /**
     * A predefined 2D array representing a specific block shape.
     * The array consists of three rows and two columns, forming an L-shaped pattern.
     * Each element in the array represents a part of the block, where 1 indicates a filled cell
     * and 0 indicates an empty cell.
     */
    private int[][] shape;
    private Color color;
    private int x,y;
    private int[][][] shapes;
    private int currentRotation;

    public Block(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;

        initShapes();
    }

    //Controls the shape with the rotations (4 rotations)
    private void initShapes() {
        shapes = new int[4][][];

        //Here it becomes rotated (explained in rotation method)
        for (int i = 0; i < 4; i++) {
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];

            for (int y = 0; y < r; y++) {
                for (int x = 0; x < c; x++) {
                    shapes[i][y][x] = shape[c - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }

    public void spawn() {
        currentRotation = 0;
        shape = shapes[currentRotation];

        y = -getHeight();
        x = (GridSettings.width - getWidth()) / 2;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveDown() {
        y++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    //2 sorts of array, array 1 has the same amount of columns (width) as array 2 has rows
    //AND where array 1 has the same amount of rows (height) as array 2 has columns (You rotate the block 90 degrees)
    //ALSO the column of array 1 equals to the row of array 2
    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    public int getBottomEdge() {
        return y + getHeight();
    }

}
