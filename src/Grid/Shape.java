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
        SPAWN_ROW = getTopSide(3);
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

    public boolean isBlock(int x, int y, int rotation) {
        return blocks[rotation][x][y];
    }

    public int getLeftSideShape(int rotation) {
        for (int x = 0; x < DIMENSION; x++) {
            for (int y = 0; y < DIMENSION; y++) {
                if(isBlock(x, y, rotation)) {
                    return x;
                }
            }
        }
        return -1;
    }

    public int getRightSideShape(int rotation) {
        for (int x = DIMENSION - 1; x >= 0; x--) {
            for (int y = 0; y < DIMENSION; y++) {
                if (isBlock(x,y,rotation)) {
                    return DIMENSION - x;
                }
            }
        }
        return -1;
    }

    public int getTopSide(int rotation) {
        for (int y = 0; y < DIMENSION; y++) {
            for (int x = 0; x < DIMENSION; x++) {
                if(isBlock(x,y,rotation)) {
                    return y;
                }
            }
        }
        return -1;
    }

    public int getBottomSide(int rotation) {
        for (int y = DIMENSION - 1; y >= 0; y--) {
            for (int x = 0; x < DIMENSION; x++) {
                if(isBlock(x,y,rotation)) {
                    return DIMENSION - y;
                }
            }
        }
        return -1;
    }

}
