package shapes;

import grid.Block;

/**
 * - Extends Block class
 * - Creates the "T" shape using a 3D boolean array
 */

public class TShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
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
