import Grid.Block;
import Grid.Grid;
import Grid.GridSettings;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class GridDraw {

    public ArrayList<ArrayList<Block>> drawnGrid = Grid.getGrid();

    private int gridRows;
    private int gridWidth;
    private static int gridCellSize;
    private Color[][] background;

    private static Block block;

    public GridDraw(int width) {
        gridWidth = width;
        gridCellSize = GridSettings.blockSize;
        gridRows = GridSettings.height;
        background = new Color[gridRows][gridWidth];
    }

    public void spawnBlock() {
        block = new Block(new int[][]{{1,0},{1,0},{1,1}}, Color.RED);
        block.spawn();
    }

    public boolean moveBlockDown() {
        if (!checkBottom()) {
            moveBlockToBackground();
            clearLines();
            return false;
        }

        block.moveDown();
        repaint();
        return true;
    }

    public void moveBlockLeft() {
        if (!checkLeft()) {
            return;
        }
        block.moveLeft();
        repaint();
    }

    public void moveBlockRight() {
        if (!checkRight()) {
            return;
        }
        block.moveRight();
        repaint();
    }

    public void dropBlock() {
        while (checkBottom() == true) {
            block.moveDown();
        }
        repaint();
    }

    public void rotateBlock() {
        block.rotate();
        repaint();
    }

    private boolean checkBottom() {
        if ( block.getBottomEdge() == gridRows ) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        //Checks first the first column and for that column every row
        //Then checks second column and for that column every row
        for (int c = 0; c < w; c++) {
            for (int r = h - 1; r <= h; r--) {
                if (shape[r][c] != 0) {
                    int x = (block.getX() + c);
                    int y = (block.getY() + r + 1); //+1 for the block next under the shape
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkLeft(){
        if ( block.getLeftEdge() == 0 ) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        //Checks first the first row and for that row every column from up to down
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] != 0) {
                    int x = (block.getX() + c - 1); //-1 for the block next left to the shape
                    int y = (block.getY() + r);
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkRight(){
        if ( block.getRightEdge() == gridWidth ) {
            return false;
        }

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        //Checks first the first row and for that row every column from up to down
        for (int r = 0; r < h; r++) {
            for (int c = w - 1; c >= 0; c--) {
                if (shape[r][c] != 0) {
                    int x = (block.getX() + c + 1); //+1 for the block next right to the shape
                    int y = (block.getY() + r);
                    if (y < 0) {
                        break;
                    }
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }

    //Rows counting from bottom to top and left to right
    public void clearLines() {
        boolean lineFilled;
        for (int r = gridRows - 1; r >= 0; r--) {
            lineFilled = true;
            //If no block == to null, it means the line is full and the line must be removed
            for (int c = 0; c < gridWidth; c++) {

                //If this term is met, the loop is terminated, and it means there was a null block in the row
                if (background[r][c] == null) {
                    lineFilled = false;
                    break;
                }
            }

            //If in the innerloop the boolean is not set to false, then the line gets cleared and repainted
            if (lineFilled) {
                clearLine(r);
                shiftdown(r);
                //remove the upper line if a line is cleared (Otherwise array out of bounds)
                clearLine(0);
                //To overcome that a row is not counted because the r is already updated
                r++;

                repaint();
            }
        }
    }

    private void clearLine(int r) {
        for (int i = 0; i < gridWidth; i++) {
            background[r][i] = null;
        }
    }

    private void shiftdown(int r) {
        //outer loop starts from the point r in thats given in clearlines method and then looks above to move everything once at a time
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridWidth; col++) {
                background[row][col] = background[row - 1][col];
            }
        }
    }

    private void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        Color color = block.getColor();

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if(shape[r][c] == 1) {
                    int x = (xPos + c);
                    int y = (yPos + r);
                    background[y][x] = color;
                }
            }
        }
    }

    public static void repaint() {
        drawBlock();
    }

    /**
     * Draws a block on the screen with the specified color.
     */
    public static void drawBlock() {
        int h = block.getHeight();
        int w = block.getWidth();
        Color color = block.getColor();
        int[][] shape = block.getShape();

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if(shape[r][c] == 1) {
                    int x = (block.getX() + c) * gridCellSize + GridSettings.startPanelX;
                    int y = (block.getY() + r) * gridCellSize  + GridSettings.startPanelY;
                    drawGridSquare(color, x, y);
                }
            }
        }
    }


    public void drawBackground() {
        Color color;

        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridWidth; c++) {
                color = background[r][c];

                if (color != null) {
                    int x = c * gridCellSize + GridSettings.startPanelX;
                    int y = r * gridCellSize + GridSettings.startPanelY;
                    drawGridSquare(color, x, y);
                }
            }
        }
    }

    /**
     * Draws a rectangular grid based on the dimensions and settings defined in GridSettings.
     *
     * This method iterates over the height and width specified in GridSettings to calculate the
     * position of each grid square. It then calls the drawGridSquare method to draw each square
     * at the computed coordinates with the default background color.
     */
    public void drawGrid() {

        for (int r = 0; r < gridRows; r++) {

            for (int c = 0; c < gridWidth; c++) {

                int x = c * gridCellSize + GridSettings.startPanelX;
                int y = r * gridCellSize + GridSettings.startPanelY;

                drawGridSquare(SaxionApp.DEFAULT_BACKGROUND_COLOR, x, y);
            }
        }
    }

    /**
     * Draws a single square of the grid at the specified coordinates with the given color.
     *
     * @param color the color to fill the grid square
     * @param x the x-coordinate of the top-left corner of the grid square
     * @param y the y-coordinate of the top-left corner of the*/
    private static void drawGridSquare(Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x, y, gridCellSize, gridCellSize);
    }


}
