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
        super(new boolean[][][]{
                {
                        {true, false},
                        {true, false},
                        {true, true}
                },
                {
                        {true, true, true},
                        {true, false, false}
                },
                {
                        {true, true},
                        {false, true},
                        {false, true}
                },
                {
                        {false, false, true},
                        {true, true, true}
                }
        });
    }
}
