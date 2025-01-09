package Shapes;


import Grid.Block;

import java.awt.*;

/**
 * - Extends Block class
 * - Creates the "J" shape using a 2D integer array
 */

public class JShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public JShape() {
        super(new boolean[][][]{
                {
                        {false, true},
                        {false, true},
                        {true, true}
                },
                {
                        {true, false, false},
                        {true, true, true}
                },
                {
                        {true, true},
                        {true, false},
                        {true, false}
                },
                {
                        {true, true, true},
                        {false, false, true}
                }
        });
    }
}
