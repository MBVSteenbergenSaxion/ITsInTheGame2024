import Grid.Block;
import Grid.Grid;
import Grid.GridSettings;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class GridDraw {

    public ArrayList<ArrayList<Block>> drawnGrid = Grid.getGrid();

    /**
     * Draws a rectangular grid based on the dimensions and settings defined in GridSettings.
     *
     * This method iterates over the height and width specified in GridSettings to calculate the
     * position of each grid square. It then calls the drawGridSquare method to draw each square
     * at the computed coordinates with the default background color.
     */
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



    /**
     * Draws a single square of the grid at the specified coordinates with the given color.
     *
     * @param color the color to fill the grid square
     * @param x the x-coordinate of the top-left corner of the grid square
     * @param y the y-coordinate of the top-left corner of the*/
    private void drawGridSquare(Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x, y, GridSettings.blockSize, GridSettings.blockSize);
    }


}
