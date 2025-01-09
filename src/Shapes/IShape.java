package Shapes;

import Grid.Block;

/**
 * - Extends Block class
 * - Creates the "I" shape using a 3D boolean array
 */

public class IShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
    public IShape() {
        super(new boolean[][][]{
                {
                        {true},
                        {true},
                        {true},
                        {true}
                },
                {
                        {true, true, true, true}
                }
        });
    }
}
