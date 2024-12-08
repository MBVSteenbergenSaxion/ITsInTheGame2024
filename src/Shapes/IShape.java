package Shapes;
import Grid.Shape;

import java.awt.*;

public class IShape extends Shape {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public IShape() {
        super(
                new Color(255, 0 ,0), // Color
                4, // Dimension
                4, // Spawn Column
                1, // Spawn Row
                new boolean[][][] { // 3D array for rotation states
                        {
                                {false, false, false, false},
                                {true, true, true, true},
                                {false, false, false, false},
                                {false, false, false, false},
                        },
                        {
                                {false, false, true, false},
                                {false, false, true, false},
                                {false, false, true, false},
                                {false, false, true, false},
                        },
                        {
                                {false, false, false, false},
                                {false, false, false, false},
                                {true, true, true, true},
                                {false, false, false, false},
                        },
                        {
                                {false, true, false, false},
                                {false, true, false, false},
                                {false, true, false, false},
                                {false, true, false, false},
                        }
                }
        );
    }
}
