package Shapes;

import Grid.Block;

/**
 * - Extends Block class
 * - Creates the "T" shape using a 2D integer array
 */

public class TShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public TShape() {
        super(new boolean[][][]{
                {
                        {true, true, true},
                        {false, true, false}
                },
                {
                        {false, true},
                        {true, true},
                        {false, true}
                },
                {
                        {false, true, false},
                        {true, true, true}
                },
                {
                        {true, false},
                        {true, true},
                        {true, false}
                }
        });
    }
}
