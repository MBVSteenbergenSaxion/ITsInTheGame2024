import Grid.*;
import nl.saxion.app.SaxionApp;

import java.awt.*;

public class GridDraw {

    public static int gridRows, gridWidth, gridCellSize;

    private static Color[][] background;
    private final GameBackend gb;

    public GridDraw(GameBackend gb) {
        initializeGridSettings();

        this.gb = gb;

        background = new Color[gridRows][gridWidth];
    }

    /**
     * INITIALIZING METHODS
     * - spawnBlock()
     * - setCurrentBlock()
     * - setNextPiece()
     * - initializeGridSettings()
     */


    private void initializeGridSettings() {
        gridWidth = GridSettings.width;
        gridCellSize = GridSettings.getBlockSize();
        gridRows = GridSettings.height;

    }

    /**
     * MOVEMENT METHODS
     * - moveBlockDown()
     * - moveBlockLeft()
     * - moveBlockRight()
     * - dropBlock()
     * - rotateBlock()
     */


    /**
     * CHECK METHODS
     * - spawnRotationCheck()
     * - setNextPieceCheck()
     * - clearLines()
     * - isBlockOutOfBounds()
     * - checkBottom()
     * - checkLeft()
     * - checkRight()
     */


    public int clearLineCheck() {
        boolean lineFilled;
        int linesCleared = 0;

        for (int row = gridRows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < gridWidth; col++) {
                if (background[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                clearLine(row);
                shiftDown(row);
                linesCleared++;
                clearLine(0);
                row++;

                repaint();
                GameBackend.scoreCount++;
                SaxionApp.playSound("resources/gameSounds/lineCompletion.wav");
            }
        }
        return linesCleared;
    }

    public boolean isBlockOutOfBounds() {
        if (gb.getCurrentblock().getY() < 0) {
            return true;
        }
        return false;
    }

    public boolean checkBottom() {
        if (gb.getCurrentblock().getBottomEdge() == gridRows) {
            return false;
        }

        int[][] shape = gb.getCurrentblock().getShape();
        int width = gb.getCurrentblock().getWidth();
        int height = gb.getCurrentblock().getHeight();

        for (int col = 0; col < width; col++) {

            int row = height - 1;
            while (row <= height) {
                if (shape[row][col] != 0) {

                    int x = (gb.getCurrentblock().getX() + col);
                    int y = (gb.getCurrentblock().getY() + row + 1);

                    if (y < 0) {
                        break;
                    }

                    if (utils.Utility.isArrayInBounds(background, y, x)) {
                        if (background[y][x] != null) {
                            return false;
                        }
                    }

                    break;
                }
                row--;
            }
        }
        return true;
    }

    public boolean checkLeft() {
        if (gb.getCurrentblock().getLeftEdge() == 0) {
            return false;
        }

        int[][] shape = gb.getCurrentblock().getShape();
        int width = gb.getCurrentblock().getWidth();
        int height = gb.getCurrentblock().getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = (gb.getCurrentblock().getX() + col - 1);
                    int y = (gb.getCurrentblock().getY() + row);

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

    public boolean checkRight() {
        if (gb.getCurrentblock().getRightEdge() == gridWidth) {
            return false;
        }

        int[][] shape = gb.getCurrentblock().getShape();
        int width = gb.getCurrentblock().getWidth();
        int height = gb.getCurrentblock().getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = (gb.getCurrentblock().getX() + col + 1);
                    int y = (gb.getCurrentblock().getY() + row);

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


    /**
     * DRAWING METHODS
     * - clearLine(int row)
     * - shiftDown(int r)
     * - repaint()
     * - moveBlockToBackground()
     * - drawBlock(Color color, int x, int y)
     * - paint()
     * - drawNextPieceGrid()
     * - drawNextBlock()
     * - drawNextGridSquare(Color, int x, int y)
     */

    private void clearLine(int row) {
        for (int i = 0; i < gridWidth; i++) {
            background[row][i] = null;
        }
    }

    private void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            if (gridWidth >= 0) {
                //Copy's the array of the row from the row above
                System.arraycopy(background[row - 1], 0, background[row], 0, gridWidth);
            }
        }
    }

    public void repaint() {
        paint();
    }

    public void moveBlockToBackground() {
        if (gb.getCurrentblock() != null) {
            int[][] shape = gb.getCurrentblock().getShape();
            int height = gb.getCurrentblock().getHeight();
            int width = gb.getCurrentblock().getWidth();

            int xPos = gb.getCurrentblock().getX();
            int yPos = gb.getCurrentblock().getY();

            Color color = gb.getCurrentblock().getColor();

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (shape[row][col] == 1) {
                        int x = (xPos + col);
                        int y = (yPos + row);
                        background[y][x] = color;
                    }
                }
            }
        }
    }

    private static void drawBlock(Color color, int x, int y) {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y, GridSettings.getBlockSize(), GridSettings.getBlockSize());


        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < GridSettings.getShadowSize(); i++) {
            SaxionApp.drawLine(x + GridSettings.getBlockSize() - GridSettings.getShadowSize() + i, y, x + GridSettings.getBlockSize() - GridSettings.getShadowSize() + i, y + GridSettings.getBlockSize() - 1);
            SaxionApp.drawLine(x, y + GridSettings.getBlockSize() - GridSettings.getShadowSize() + i, x + GridSettings.getBlockSize() - 1, y + GridSettings.getBlockSize() - GridSettings.getShadowSize() + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < GridSettings.getShadowSize(); i++) {
            SaxionApp.drawLine(x, y + i, x + GridSettings.getBlockSize() - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + GridSettings.getBlockSize() - i - 1);
        }

    }

    public void paint() {
        //Draw grid in outline (Only with lines)
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());
        for (int col = 0; col < GridSettings.width; col++) {
            for (int row = 0; row < GridSettings.height; row++) {
                int x = col * gridCellSize + GridSettings.getStartPanelX();
                int y = row * gridCellSize + GridSettings.getStartPanelY();
                SaxionApp.drawRectangle(x, y, GridSettings.getBlockSize(), GridSettings.getBlockSize());

                //SaxionApp.drawLine(GridSettings.startPanelX, y * GridSettings.getBlockSize() + GridSettings.startPanelY, GridSettings.width * GridSettings.getBlockSize() + GridSettings.startPanelX, y * GridSettings.getBlockSize() + GridSettings.startPanelY);
                //SaxionApp.drawLine(x * GridSettings.getBlockSize() + GridSettings.startPanelX, GridSettings.startPanelY, x * GridSettings.getBlockSize() + GridSettings.startPanelX, GridSettings.height * GridSettings.getBlockSize() + GridSettings.startPanelY);
            }

        }

        //Draw background
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridWidth; col++) {
                color = background[row][col];

                if (color != null) {
                    int x = col * gridCellSize + GridSettings.getStartPanelX();
                    int y = row * gridCellSize + GridSettings.getStartPanelY();

                    drawBlock(color, x, y);

                }
            }
        }


        //Draw currentblock
        if (gb.getCurrentblock() != null) {
            int height = gb.getCurrentblock().getHeight();
            int width = gb.getCurrentblock().getWidth();
            color = gb.getCurrentblock().getColor();
            int[][] shape = gb.getCurrentblock().getShape();

            SaxionApp.setBorderColor(Color.LIGHT_GRAY);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (shape[row][col] == 1) {
                        int x = (gb.getCurrentblock().getX() + col) * gridCellSize + GridSettings.getStartPanelX();
                        int y = (gb.getCurrentblock().getY() + row) * gridCellSize + GridSettings.getStartPanelY();

                        if (utils.Utility.checkBounds(x, y, GridSettings.getStartPanelX(),
                                GridSettings.getStartPanelY(), GridSettings.getWidthPanel(),
                                GridSettings.getEndPanelY(), false)) {
                            drawBlock(color, x, y);
                        }
                    }
                }
            }
        }


        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(GridSettings.getStartPanelX(), GridSettings.getStartPanelY(), GridSettings.getStartPanelX(), GridSettings.getEndPanelY()); //Line LeftSide
        SaxionApp.drawLine(GridSettings.getEndPanelX(), GridSettings.getStartPanelY(), GridSettings.getEndPanelX(), GridSettings.getEndPanelY()); //Line RightSide
        SaxionApp.drawLine(GridSettings.getStartPanelX(), GridSettings.getStartPanelY(), GridSettings.getEndPanelX(), GridSettings.getStartPanelY()); //Line TopSide
        SaxionApp.drawLine(GridSettings.getStartPanelX(), GridSettings.getEndPanelY(), GridSettings.getEndPanelX(), GridSettings.getEndPanelY()); //Line BottomSide
    }




}
