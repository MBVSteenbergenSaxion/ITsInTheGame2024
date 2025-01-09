package Shapes;

import Grid.Block;

/**
 * - Extends Block class
 * - Creates the "J" shape using a 3D boolean array
 */

public class JShape extends Block {

    // With super, the shape object below will be made as: private boolean[][][] shape in Block.java
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
