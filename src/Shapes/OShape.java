package Shapes;


import Grid.Block;

import java.awt.*;

public class OShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public OShape() {
        super(new int[][]{{1, 1},
                {1, 1}
        });
    }
}
