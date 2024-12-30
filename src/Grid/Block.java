package Grid;

import nl.saxion.app.SaxionApp;
import java.awt.*;

/** Block class
 * Defines a shape, with every shape defined as integers in the Shapes package which extends this class.
 * The shapes gets defined in the GameBackend when the game starts.
 * In this class the shape gets an x and y dimension, a color for the blocks that needs to be colored and the rotations
 * */
public class Block {

    /** 2D-array for the rows and columns of a shape
     * */
    private int[][] shape;

    /** The color of the shape
     * */
    public Color color;

    /** The variable for the x and y of the shape
     * */
    private int x,y;

    /** The different calculated shapes per sort, gets calculated when the shapes are defined at the beginning.
     * Every shape has 4 shapes with each a different rotation of 90 degrees
     * */
    private int[][][] shapes;

    /** The integer of 0, 1, 2, 3. Which is the current rotation of the shape.
     * */
    private int currentRotation;

    /** The constructor method from this class, which when called it has a shape with the x and y where the different rotations are calculated.
     * */
    public Block(int[][] shape) {
        this.shape = shape;

        initShapes();
    }

    /** Calculates the four different rotations (shapes) of one shape.
     * It iterates through the four integers and calculates how the next shape is going to be
     * */
    private void initShapes() {
        shapes = new int[4][][];

        //Here it becomes rotated (explained in rotation method)
        for (int i = 0; i < 4; i++) {
            int row = shape[0].length;
            int col = shape.length;
            shapes[i] = new int[row][col];

            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    shapes[i][y][x] = shape[col - x - 1][y];
                }
            }

            shape = shapes[i];
        }
    }

    /** Spawns the shape on GameGrid
     * It gets the current given rotation at the beginning, it spawns right above the first row of the grid and on a random column.
     * */
    public void spawn(int rotation) {
        currentRotation = rotation;
        y = -getHeight();
        x = SaxionApp.getRandomValueBetween(0, GridSettings.width - getWidth() -1);

        shape = shapes[currentRotation];
    }

    /** Let the nextpiece spawn on the SideGrid, it spawns on the 0,0 coördinates and sets it to the last rotation of the shape.
     * */
    public void nextSpawn() {
        y = 0;
        x = 0;

        shape = shapes[3];
    }

    /** Returns the 2D shape when method is called.
     * */
    public int[][] getShape() {
        return shape;
    }

    /** Returns the current color of the shape, when method is called
     * */
    public Color getColor() {
        return color;
    }

    /** Returns the amount of rows of the shape (The Height)
     * */
    public int getHeight() {
        return shape.length;
    }

    /** Returns the amount of columns of the shape (The Width)
     * */
    public int getWidth() {
        return shape[0].length;
    }

    /** Returns the current X-Coordinate of the shape
     * */
    public int getX(){
        return x;
    }

    /** Sets the X-Coordinate with the given new X
     * */
    public void setX(int newX){
        x = newX;
    }

    /** Returns the Y-Coordinate of the shape
     * */
    public int getY() {
        return y;
    }

    /** Sets the Y-Coordinate with the given new Y
     * */
    public void setY(int newY){
        y = newY;
    }

    /** Moves the shape a grid block down
     * */
    public void moveDown() {
        y++;
    }

    /** Moves the shape a grid block to the left
     * */
    public void moveLeft() {
        x--;
    }

    /** Moves the shape a grid block to the right
     * */
    public void moveRight() {
        x++;
    }

    /** Sets the new rotation of the shape when called. It gets a next integer, when its more then 3 it becomes 0 again.
     * */
    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    /** Returns the bottom block of the shape
     * This means the Y coordinate of the shape with the amount of rows of the shape
     * */
    public int getBottomEdge() {
        return y + getHeight();
    }

    /** Returns the most right block of the shape
     * This means the X coordinate of the shape with the amount columns of the shape
     * */
    public int getRightEdge() {
        return x + getWidth();
    }

    /** Returns the most left block of the shape
     * This means the X coordinate of the shape
     * */
    public int getLeftEdge() {
        return x;
    }

}
