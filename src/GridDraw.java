import Grid.*;
import Shapes.*;
import nl.saxion.app.SaxionApp;

import java.awt.*;

public class GridDraw {

    private static int gridRows, gridWidth, gridCellSize;
    private static int nextGridRows, nextGridWidth, nextGridCellSize;

    private static Color[][] background;

    private static Block[] blocks;
    private static Color[] colors;

    public static int randomBlock, currentBlockId, setRotation, randomColorValue;
    private Block nextblock, currentblock;

    public GridDraw() {
        initializeGridSettings();

        background = new Color[gridRows][gridWidth];

        blocks = new Block[]{
                new LShape(),
                new IShape(),
                new JShape(),
                new OShape(),
                new SShape(),
                new ZShape(),
                new TShape()
        };

        colors = new Color[]{
                SaxionApp.createColor(4, 83, 255), //Blue
                SaxionApp.createColor(253, 103, 1), //Orange
                SaxionApp.createColor(254, 255, 6), //Yellow
                SaxionApp.createColor(0, 255, 6), //Green
                SaxionApp.createColor(254, 4, 253), //Pink
                SaxionApp.createColor(255, 17, 4), //Red
                SaxionApp.createColor(5, 239, 253) //Cyan
        };
    }

    /**
     * INITIALIZING METHODS
     * - spawnBlock()
     * - setCurrentBlock()
     * - setNextPiece()
     * - initializeGridSettings()
     */

    public void spawnBlock() {
        setCurrentBlock();
        currentblock.spawn(setRotation);
        nextblock.nextSpawn();
    }

    public void setCurrentBlock() {
        spawnRotationCheck();

        currentblock = nextblock;
        currentBlockId = randomBlock;

        setNextPieceCheck();
    }

    public void setNextPiece() {
        randomBlock = SaxionApp.getRandomValueBetween(0, blocks.length);
        randomColorValue = SaxionApp.getRandomValueBetween(0, colors.length);

        nextblock = blocks[randomBlock];
        nextblock.color = colors[randomColorValue];
    }

    private void initializeGridSettings() {
        gridWidth = GridSettings.width;
        gridCellSize = GridSettings.blockSize;
        gridRows = GridSettings.height;

        nextGridWidth = GridSettings.nextPieceWidth;
        nextGridCellSize = GridSettings.blockNextSize;
        nextGridRows = GridSettings.nextPieceHeight;
    }

    /**
     * MOVEMENT METHODS
     * - moveBlockDown()
     * - moveBlockLeft()
     * - moveBlockRight()
     * - dropBlock()
     * - rotateBlock()
     */

    public boolean moveBlockDown() {
        if (!checkBottom()) {
            return false;
        }

        currentblock.moveDown();
        repaint();
        return true;
    }

    public void moveBlockLeft() {
        if (currentblock == null) {
            return;
        }

        if (!checkLeft()) {
            return;
        }

        currentblock.moveLeft();
        repaint();
    }

    public void moveBlockRight() {
        if (currentblock == null) {
            return;
        }

        if (!checkRight()) {
            return;
        }

        currentblock.moveRight();
        repaint();
    }

    public void dropBlock() {
        if (currentblock == null) {
            return;
        }

        while (checkBottom()) {
            currentblock.moveDown();
        }

        repaint();
    }

    public void rotateBlock() {
        if (currentblock == null) {
            return;
        }

        currentblock.rotate();

        if (currentblock.getLeftEdge() < 0) {
            currentblock.setX(0);
        }

        if (currentblock.getRightEdge() >= gridWidth) {
            currentblock.setX(gridWidth - currentblock.getWidth());
        }

        if (currentblock.getBottomEdge() >= gridRows) {
            currentblock.setY(gridRows - currentblock.getHeight());
        }

        repaint();
    }

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

    private void spawnRotationCheck() {
        if (randomBlock == 1) {
            setRotation = 1;
        } else {
            setRotation = SaxionApp.getRandomValueBetween(0, 4);
        }
    }

    private void setNextPieceCheck() {
        setNextPiece();
        if (randomBlock == currentBlockId) {
            setNextPiece();
        }
    }

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
                Game.scoreCount++;
                SaxionApp.playSound("resources/gameSounds/lineCompletion.wav");
            }
        }
        return linesCleared;
    }

    public boolean isBlockOutOfBounds() {
        if (currentblock.getY() < 0) {
            currentblock = null;
            return true;
        }
        return false;
    }

    private boolean checkBottom() {
        if (currentblock.getBottomEdge() == gridRows) {
            return false;
        }

        int[][] shape = currentblock.getShape();
        int width = currentblock.getWidth();
        int height = currentblock.getHeight();

        for (int col = 0; col < width; col++) {

            int row = height - 1;
            while (row <= height) {
                if (shape[row][col] != 0) {

                    int x = (currentblock.getX() + col);
                    int y = (currentblock.getY() + row + 1);

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

    private boolean checkLeft() {
        if (currentblock.getLeftEdge() == 0) {
            return false;
        }

        int[][] shape = currentblock.getShape();
        int width = currentblock.getWidth();
        int height = currentblock.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = (currentblock.getX() + col - 1);
                    int y = (currentblock.getY() + row);

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

    private boolean checkRight() {
        if (currentblock.getRightEdge() == gridWidth) {
            return false;
        }

        int[][] shape = currentblock.getShape();
        int width = currentblock.getWidth();
        int height = currentblock.getHeight();

        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = (currentblock.getX() + col + 1);
                    int y = (currentblock.getY() + row);

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
        drawNextBlock();
    }

    public void moveBlockToBackground() {
        if (this.currentblock != null) {
            int[][] shape = currentblock.getShape();
            int height = currentblock.getHeight();
            int width = currentblock.getWidth();

            int xPos = currentblock.getX();
            int yPos = currentblock.getY();

            Color color = currentblock.getColor();

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
        SaxionApp.drawRectangle(x,y, GridSettings.blockSize, GridSettings.blockSize);

        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < GridSettings.shadowSize; i++) {
            SaxionApp.drawLine(x + GridSettings.blockSize - GridSettings.shadowSize + i, y, x + GridSettings.blockSize - GridSettings.shadowSize + i, y + GridSettings.blockSize - 1);
            SaxionApp.drawLine(x, y + GridSettings.blockSize - GridSettings.shadowSize + i, x + GridSettings.blockSize - 1, y + GridSettings.blockSize - GridSettings.shadowSize + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < GridSettings.shadowSize; i++) {
            SaxionApp.drawLine(x, y + i, x + GridSettings.blockSize - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + GridSettings.blockSize - i - 1);
        }

    }

    public void paint() {
        //Draw grid in outline (Only with lines)
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());
        for (int col = 0; col < GridSettings.width; col++) {
            for (int row = 0; row < GridSettings.height; row++) {
                int x = col * gridCellSize + GridSettings.startPanelX;
                int y = row * gridCellSize + GridSettings.startPanelY;
                SaxionApp.drawRectangle(x, y, GridSettings.blockSize, GridSettings.blockSize);

                //SaxionApp.drawLine(GridSettings.startPanelX, y * GridSettings.blockSize + GridSettings.startPanelY, GridSettings.width * GridSettings.blockSize + GridSettings.startPanelX, y * GridSettings.blockSize + GridSettings.startPanelY);
                //SaxionApp.drawLine(x * GridSettings.blockSize + GridSettings.startPanelX, GridSettings.startPanelY, x * GridSettings.blockSize + GridSettings.startPanelX, GridSettings.height * GridSettings.blockSize + GridSettings.startPanelY);
            }

        }

        //Draw background
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridWidth; col++) {
                color = background[row][col];

                if (color != null) {
                    int x = col * gridCellSize + GridSettings.startPanelX;
                    int y = row * gridCellSize + GridSettings.startPanelY;

                    drawBlock(color, x, y);

                }
            }
        }


        //Draw currentblock
        if (this.currentblock != null) {
            int height = currentblock.getHeight();
            int width = currentblock.getWidth();
            color = currentblock.getColor();
            int[][] shape = currentblock.getShape();

            SaxionApp.setBorderColor(Color.LIGHT_GRAY);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (shape[row][col] == 1) {
                        int x = (currentblock.getX() + col) * gridCellSize + GridSettings.startPanelX;
                        int y = (currentblock.getY() + row) * gridCellSize + GridSettings.startPanelY;

                        if (utils.Utility.checkBounds(x, y, GridSettings.startPanelX,
                                GridSettings.startPanelY, GridSettings.widthPanel,
                                GridSettings.endPanelY, false)) {
                            drawBlock(color, x, y);
                        }
                    }
                }
            }
        }


        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(GridSettings.startPanelX, GridSettings.startPanelY, GridSettings.startPanelX, GridSettings.endPanelY); //Line LeftSide
        SaxionApp.drawLine(GridSettings.endPanelX, GridSettings.startPanelY, GridSettings.endPanelX, GridSettings.endPanelY); //Line RightSide
        SaxionApp.drawLine(GridSettings.startPanelX, GridSettings.startPanelY, GridSettings.endPanelX, GridSettings.startPanelY); //Line TopSide
        SaxionApp.drawLine(GridSettings.startPanelX, GridSettings.endPanelY, GridSettings.endPanelX, GridSettings.endPanelY); //Line BottomSide
    }

    public void drawNextPieceGrid() {
        for (int row = 0; row < nextGridRows; row++) {
            for (int col = 0; col < nextGridWidth; col++) {
                int x = col * nextGridCellSize + GridSettings.startNextPanelX;
                int y = row * nextGridCellSize + GridSettings.startNextPanelY;
                SaxionApp.setBorderColor(Color.LIGHT_GRAY);
                drawNextGridSquare(SaxionApp.DEFAULT_BACKGROUND_COLOR, x, y);
            }
        }
    }

    public void drawNextBlock() {
        int height = nextblock.getHeight();
        int width = nextblock.getWidth();
        Color color = nextblock.getColor();
        int[][] shape = nextblock.getShape();

        SaxionApp.setBorderColor(Color.LIGHT_GRAY);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) {
                    int x = (nextblock.getX() + col) * nextGridCellSize + GridSettings.startNextPanelX;
                    int y = (nextblock.getY() + row) * nextGridCellSize + GridSettings.startNextPanelY;

                    if (utils.Utility.checkBounds(x, y, GridSettings.startNextPanelX,
                            GridSettings.startNextPanelY, GridSettings.widthNextPanel,
                            GridSettings.heightNextPanel, false)) {
                        drawNextGridSquare(color, x, y);
                    }
                }
            }
        }
    }

    private static void drawNextGridSquare(Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x, y, nextGridCellSize, nextGridCellSize);
    }

}
