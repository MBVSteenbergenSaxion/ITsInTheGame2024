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

    /**
     * */
    public void spawn(int rotation) {
        currentRotation = rotation;
        y = -getHeight();
        x = SaxionApp.getRandomValueBetween(0, GridSettings.width - getWidth() -1);

        shape = shapes[currentRotation];
    }

    public void nextSpawn() {
        y = 0;
        x = 0;

        shape = shapes[3];
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

    public void setX(int newX){
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY){
        y = newY;
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

    public int getRightEdge() {
        return x + getWidth();
    }

    public int getLeftEdge() {
        return x;
    }

}
