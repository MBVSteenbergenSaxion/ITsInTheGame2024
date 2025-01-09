package Shapes;

import Grid.Block;

/**
 * - Extends Block class
 * - Creates the "L" shape using a 3D boolean array
 */

public class LShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
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
