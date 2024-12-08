package Grid;

import java.awt.*;

public class Shape {
    private Color BASE_COLOR;
    private Color LIGHT_COLOR;
    private Color DARK_COLOR;

    private int SPAWN_COL;
    private int SPAWN_ROW;
    private int DIMENSION;

    private int rows;
    private int columns;

    private boolean[][][] blocks;

    public Shape(Color color, int dimension, int cols, int rows, boolean[][][] blocks) {
        BASE_COLOR = color;
        DIMENSION = dimension;
        columns = cols;
        this.rows = rows;
        this.blocks = blocks;

        //en initialiseer de startwaardes met col is de helft van het scherm - (de dimensie van de shape/2)
        SPAWN_COL = (GridSettings.startX_PANEL + ((GridSettings.GRID_COLUMNS * GridSettings.BLOCK_SIZE) / 2)) - (DIMENSION / 2);
        SPAWN_ROW = getTopSide(0);
    }

    public Color getBaseColor() {
        return BASE_COLOR;
    }

    public Color getLighterColor() {
        return BASE_COLOR.brighter();
    }

    public Color getDarkerColor() {
        return BASE_COLOR.darker();
    }

    public int getDimension() {
        return DIMENSION;
    }

    public int getSpawnColumn() {
        return SPAWN_COL;
    }

    public int getSpawnRow() {
        return SPAWN_ROW;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    private int getTopSide(int rotation) {
        return 0;
    }
}
