package Shapes;


import Grid.Block;

import java.awt.*;

public class ZShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public ZShape() {
        super(new int[][]{{1, 1, 0},
                {0, 1, 1}
        });
    }
}
