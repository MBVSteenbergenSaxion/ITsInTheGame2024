package Shapes;
import Grid.Shape;

import java.awt.*;

public class IShape extends Shape {

    public IShape() {
        super(
                new Color(200, 0 ,0),
                4,
                4,
                1,
                new boolean[][][] {
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
