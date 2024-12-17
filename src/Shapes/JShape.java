package Shapes;


import Grid.Block;

import java.awt.*;

public class JShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public JShape() {
        super(new int[][]{
                {0, 1},
                {0, 1},
                {1, 1}
        });
    }
}
