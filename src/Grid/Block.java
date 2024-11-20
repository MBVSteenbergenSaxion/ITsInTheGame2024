package Grid;

import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Block {

    private int[][] block = { {1,0}, {1,0}, {1,1} };

    private int[][] shape;


    public Block(int[][] shape) {
        this.shape = shape;
    }



    public void drawBlock(Color color) {
        int h = shape.length;
        int w = shape[0].length;

        for (int r = 0; r < h; r++) {
            for (int b = 0; b < w; b++) {
                if(shape[r][b] == 1) {
                    SaxionApp.setFill(color);
                    SaxionApp.drawRectangle(GridSettings.blockSize * b + GridSettings.startPanelX, r * GridSettings.blockSize + GridSettings.startPanelY, GridSettings.blockSize, GridSettings.blockSize);
                }
            }
        }
    }

}
