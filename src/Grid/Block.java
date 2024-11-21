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
    private int[][] block = { {1,0}, {1,0}, {1,1} };

    /**
     * A 2D array representing the shape of the block.
     *
     * The first dimension represents the rows, while the
     * second dimension represents the columns. Each cell
     * contains an integer value that denotes whether a part
     * of the block is present 1 or absent 0.
     */
    private int[][] shape;

    /**
     * The current x-coordinate and y-coordinate of the block within the game grid.
     * It determines the horizontal and vertical position of the block.
     */
    private int x,y;

    /**
     * Creates a new Block instance with the specified shape.
     *
     * @param shape a 2D array representing the shape of the block
     */
    public Block(int[][] shape) {
        this.shape = shape;
    }

    /**
     * Sets the initial position of the block when it is spawned.
     * The block's y-coordinate is set to a negative value equal to its height,
     * making it appear off-screen initially. The x-coordinate centers
     * the block horizontally within the grid based on the current grid width.
     */
    public void spawn() {
        y = -getHeight();
        x = (GridSettings.width - getWidth()) / 2;
    }

    /**
     * Retrieves the shape of the block as a 2D array.
     *
     * @return a 2D array representing the shape of the block
     */
    public int [][] getShape (){
        return shape;
    }

    /**
     * Retrieves the height of the block shape.
     *
     * @return the number of rows in the shape array
     */
    public int getHeight(){
        return shape.length;
    }

    /**
     * Gets the width of the block's shape.
     *
     * @return the number of columns in the block's shape
     */
    public int getWidth(){
        return shape[0].length;
    }

    /**
     * Gets the current x-coordinate of the block.
     *
     * @return the x-coordinate of the block
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the current y-coordinate of the block.
     *
     * @return the y-coordinate of the block
     */
    public int getY() {
        return y;
    }

    /**
     * Moves the block one unit down by incrementing its y-coordinate.
     */
    public void moveDown() {
        y++;
    }

    /**
     * Moves the block one unit to the left by decrementing its x-coordinate.
     */
    public void moveLeft() {
        x--;
    }

    /**
     * Moves the block one unit to the right by incrementing the x-coordinate.
     */
    public void moveRight() {
        x++;
    }

    /**
     * Calculates the bottom edge position of the block within the game grid.
     *
     * @return the y-coordinate of the bottom edge of the block
     */
    public int getBottomEdge() {
        return y + getHeight();
    }




}
