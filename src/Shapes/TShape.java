package Shapes;

import Grid.Block;

/**
 * - Extends Block class
 * - Creates the "T" shape using a 2D integer array
 */

public class TShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public TShape() {
        super(new int[][]{
                {1,1,1},
                {0,1,0}
        });
    }
}
