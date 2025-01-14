package grid;

import nl.saxion.app.SaxionApp;
import java.awt.*;

/**
 * Block class
 * Defines a shape as a 3D boolean array, with each layer representing a rotation.
 * Shapes are defined in the Shapes package which extends this class.
 */
public class Block {

    /** 3D-array for the layers of a shape (rotations) */
    private boolean[][][] shapes;

    /** The color of the shape */
    public Color color;

    /** The position of the shape on the grid */
    private int x, y;

    /** The current rotation index of the shape */
    private int currentRotation;

    /**
     * Constructor that initializes the block with a 3D boolean array.
     * Each layer in the array represents a rotation of the shape.
     */
    public Block(boolean[][][] shape) {
        this.shapes = shape;
        this.currentRotation = 0; // Start with the default rotation
    }

    /**
     * Rotates the shape to the next layer in the 3D array.
     */
    public void rotate() {
        currentRotation++;
        if (currentRotation >= shapes.length) {
            currentRotation = 0; // Wrap around to the first rotation
        }
    }

    /**
     * Spawns the shape on the grid at a random column and just above the top row.
     */
    public void spawn() {
        y = -getHeight();
        x = SaxionApp.getRandomValueBetween(0, GridSettings.width - getWidth() - 1);
    }

    /**
     * Spawns the shape for preview at the SideGrid at position (0, 0).
     */
    public void nextSpawn() {
        y = 0;
        x = 0;
    }

    /**
     * Returns the current 2D shape of the block based on its rotation.
     */
    public boolean[][] getShape() {
        return shapes[currentRotation];
    }

    /**
     * Returns the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the height of the current rotation of the shape.
     */
    public int getHeight() {
        return getShape().length;
    }

    /**
     * Returns the width of the current rotation of the shape.
     */
    public int getWidth() {
        return getShape()[0].length;
    }

    /**
     * Returns the X-coordinate of the block.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X-coordinate of the block.
     */
    public void setX(int newX) {
        x = newX;
    }

    /**
     * Returns the Y-coordinate of the block.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate of the block.
     */
    public void setY(int newY) {
        y = newY;
    }

    /**
     * Moves the block one grid cell down.
     */
    public void moveDown() {
        y++;
    }

    /**
     * Moves the block one grid cell to the left.
     */
    public void moveLeft() {
        x--;
    }

    /**
     * Moves the block one grid cell to the right.
     */
    public void moveRight() {
        x++;
    }

    /**
     * Returns the Y-coordinate of the bottom edge of the block.
     */
    public int getBottomEdge() {
        return y + getHeight();
    }

    /**
     * Returns the X-coordinate of the right edge of the block.
     */
    public int getRightEdge() {
        return x + getWidth();
    }

    /**
     * Returns the X-coordinate of the left edge of the block.
     */
    public int getLeftEdge() {
        return x;
    }
}
