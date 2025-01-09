package Shapes;


import Grid.Block;

import java.awt.*;

/**
 * - Extends Block class
 * - Creates the "O" shape using a 3D boolean array
 */

public class OShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
    public OShape() {
        super(new boolean[][][]{
                {
                        {true, true},
                        {true, true}
                }
        });
    }
}
