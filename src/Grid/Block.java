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

    public Block(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
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


}
