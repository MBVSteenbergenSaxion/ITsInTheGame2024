package Grid;

import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Block {

    private int[][] block = { {1,0}, {1,0}, {1,1} };


    public void drawBlock(Color color, int x, int y) {
        for (int r = 0; r < block.length; r++) {
            for (int b = 0; b < block[0].length; b++) {
                if(block[r][b] == 1) {
                    SaxionApp.setFill(color);
                    SaxionApp.drawRectangle(GridSettings.blockSize * b + x, r * GridSettings.blockSize + y, GridSettings.blockSize, GridSettings.blockSize);
                }
            }
        }
    }
}
