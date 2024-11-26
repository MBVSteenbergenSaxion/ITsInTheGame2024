package Shapes;


import Grid.Block;

import java.awt.*;

public class MShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public MShape() {
        super(new int[][]{{1, 0, 1},
                {1, 1, 1},
                {1, 0, 1,},
                {1, 0, 1,}
        }, Color.RED);
    }
}