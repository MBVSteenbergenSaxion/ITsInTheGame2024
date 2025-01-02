package Shapes;


import Grid.Block;

import java.awt.*;

/**
 * - Extends Block class
 * - Creates the "L" shape using a 2D integer array
 */

public class LShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public LShape() {
        super(new int[][] {
                {1,0},
                {1,0},
                {1,1}
        });
    }
}
