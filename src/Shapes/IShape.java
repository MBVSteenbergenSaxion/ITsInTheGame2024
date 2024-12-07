package Shapes;
import Grid.Block;

public class IShape extends Block {

    //With super the shape object below wil be made as: private int[][] shape in Block.java
    public IShape() {
        super(new int[][]{
                {1},
                {1},
                {1},
                {1}
        });
    }
}
