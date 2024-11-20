package Grid;

import java.util.ArrayList;

public class BlockRow {

    public static ArrayList<Block> blocks = new ArrayList<>();

    public static ArrayList<Block> getBlockRow(){

        for (int i = 0; i < GridSettings.width; i++) {
            //locks.add(new Block());
        }

        return blocks;

    }

}
