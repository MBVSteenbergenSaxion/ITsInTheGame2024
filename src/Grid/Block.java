package Grid;

import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Block {

    private int[][] block = { {1,0}, {1,0}, {1,1} };

    private int[][] shape;
    private int x,y;

    public Block(int[][] shape) {
        this.shape = shape;
    }

    public void spawn() {
        y = -getHeight();
        x = (GridSettings.width - getWidth()) / 2;
    }

    public int [][] getShape (){
        return shape;
    }

    public int getHeight(){
        return shape.length;
    }

    public int getWidth(){
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

    public int getBottomEdge() {
        return y + getHeight();
    }




}
