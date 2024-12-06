package Shapes;

import java.awt.*;
import Grid.Shape;

public class IShape extends Shape {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public IShape() {
        super(
                new Color(255, 0, 0),
                4,
                4,
                1,
                new boolean[][][] {
                        {       {false, false, false, false},
                                {true, true, true, true},
                                {false, false, false, false},
                                {false, false, false, false},
                        },
                        {       {false, true, false, false},
                                {false, true, false, false},
                                {false, true, false, false},
                                {false, true, false, false},
                        },
                        {       {false, false, false, false},
                                {false, false, false, false},
                                {true, true, true, true},
                                {false, false, false, false},
                        },
                        {       {false, false, true, false},
                                {false, false, true, false},
                                {false, false, true, false},
                                {false, false, true, false},
                        }
                }

        );
    }
}
