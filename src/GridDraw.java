import Grid.Block;
import Grid.Grid;
import Grid.GridSettings;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class GridDraw {

    public ArrayList<ArrayList<Block>> drawnGrid = Grid.getGrid();

    public void drawGrid() {

        for (int r = 0; r < GridSettings.height; r++) {

            for (int b = 0; b < GridSettings.width; b++) {

                int x = b * GridSettings.blockSize + GridSettings.startPanelX;
                int y = r * GridSettings.blockSize + GridSettings.startPanelY;

                drawGridSquare(SaxionApp.DEFAULT_BACKGROUND_COLOR, x, y);

            }
        }
    }

    private ArrayList<Integer> getBounds(){

        ArrayList<Integer> bounds = new ArrayList<>();


        return bounds;
    }

    private void drawBounds(){

    }



    private void drawGridSquare(Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x, y, GridSettings.blockSize, GridSettings.blockSize);
    }


}
