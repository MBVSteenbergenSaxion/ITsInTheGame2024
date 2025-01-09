package Shapes;


import Grid.Block;

import java.awt.*;

/**
 * - Extends Block class
 * - Creates the "S" shape using a 3D boolean array
 */

public class SShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
    public SShape() {
        super(new boolean[][][]{
                {
                        {false, true, true},
                        {true, true, false}
                },
                {
                        {true, false},
                        {true, true},
                        {false, true}
                }
        });
    }
}
