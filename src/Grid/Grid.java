package Grid;

import java.util.ArrayList;

public class Grid {

    public static ArrayList<ArrayList<Block>> grid = new ArrayList<>();

    public static ArrayList<ArrayList<Block>> getGrid(){

        for (int i = 0; i < GridSettings.height; i++) {
            grid.add(BlockRow.getBlockRow());

        }

        return grid;

    }

}
