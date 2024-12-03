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

    /** INITIALIZING METHODS
     * - spawnBlock()
     * - setCurrentBlock()
     * - setNextPiece()
     * - initializeGridSettings()
     * */

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

    /** MOVEMENT METHODS
     * - moveBlockDown()
     * - moveBlockLeft()
     * - moveBlockRight()
     * - dropBlock()
     * - rotateBlock()
     * */

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

    /** CHECK METHODS
     * - spawnRotationCheck()
     * - setNextPieceCheck()
     * - clearLines()
     * - isBlockOutOfBounds()
     * - checkBottom()
     * - checkLeft()
     * - checkRight()
     * */

    private void spawnRotationCheck() {
        if (randomBlock == 1) {
            setRotation = 1;
        } else {
            setRotation = SaxionApp.getRandomValueBetween(0,4);
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
            //If no block == to null, it means the line is full and the line must be removed
            for (int col = 0; col < gridWidth; col++) {

                //If this term is met, the loop is terminated, and it means there was a null block in the row
                if (background[row][col] == null) {
                    lineFilled = false;
                    break;
                }
            }

            //If in the innerloop the boolean is not set to false, then the line gets cleared and repainted
            if (lineFilled) {
                clearLine(row);
                shiftDown(row);
                linesCleared++;
                //remove the upper line if a line is cleared (Otherwise array out of bounds)
                clearLine(0);
                //To overcome that a row is not counted because the r is already updated
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

        //Checks first the first column and for that column every row
        //Then checks second column and for that column every row
        for (int col = 0; col < width; col++) {

            int row = height - 1;
            while (row <= height) {
                if (shape[row][col] != 0) {

                    int x = (currentblock.getX() + col);
                    int y = (currentblock.getY() + row + 1); //+1 for the block next under the shape

                    if (y < 0) {
                        break;
                    }

                    if(utils.Utility.isArrayInBounds(background, y, x)){
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

        //Checks first the first row and for that row every column from up to down
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] != 0) {
                    int x = (currentblock.getX() + col - 1); //-1 for the block next left to the shape
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

        //Checks first the first row and for that row every column from up to down
        for (int row = 0; row < height; row++) {
            for (int col = width - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = (currentblock.getX() + col + 1); //+1 for the block next right to the shape
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


    /** DRAWING METHODS
     * - clearLine(int row)
     * - shiftDown(int r)
     * - repaint()
     * - drawBackground()
     * - moveBlockToBackground()
     * - drawGridSquare(Color, int x, int y)
     * - drawBlock()
     * - drawGrid()
     * - drawNextGridSquare(Color, int x, int y)
     * - drawNextPieceGrid()
     * - drawNextBlock()
     * */

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
        drawBlock();
        drawNextBlock();
    }

    public void drawBackground() {
        Color color;

        SaxionApp.setBorderColor(SaxionApp.DEFAULT_BACKGROUND_COLOR);
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridWidth; col++) {
                color = background[row][col];

                if (color != null) {
                    int x = col * gridCellSize + GridSettings.startPanelX;
                    int y = row * gridCellSize + GridSettings.startPanelY;

                    drawGridSquare(color, x, y);

                }
            }
        }
    }

    public void moveBlockToBackground() {

        if(this.currentblock != null){
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

    private static void drawGridSquare(Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x, y, gridCellSize, gridCellSize);
    }

    public void drawGrid() {
        for (int row = 0; row < gridRows; row++) {
            for (int col = 0; col < gridWidth; col++) {
                int x = col * gridCellSize + GridSettings.startPanelX;
                int y = row * gridCellSize + GridSettings.startPanelY;
                SaxionApp.setBorderColor(Color.LIGHT_GRAY);
                drawGridSquare(SaxionApp.DEFAULT_BACKGROUND_COLOR, x, y);
            }
        }
    }

    public void drawBlock() {

        if(this.currentblock != null){
            int height = currentblock.getHeight();
            int width = currentblock.getWidth();
            Color color = currentblock.getColor();
            int[][] shape = currentblock.getShape();

            SaxionApp.setBorderColor(Color.LIGHT_GRAY);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (shape[row][col] == 1) {
                        int x = (currentblock.getX() + col) * gridCellSize + GridSettings.startPanelX;
                        int y = (currentblock.getY() + row) * gridCellSize + GridSettings.startPanelY;

                        if (utils.Utility.checkBounds(x, y, GridSettings.startPanelX,
                                GridSettings.startPanelY, GridSettings.widthPanel,
                                GridSettings.heightPanel, false)) {
                            drawGridSquare(color, x, y);
                        }
                    }
                }
            }
        }
    }

    private static void drawNextGridSquare (Color color, int x, int y) {
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y,nextGridCellSize,nextGridCellSize);
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

}
