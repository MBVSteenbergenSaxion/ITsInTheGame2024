import Grid.*;
import nl.saxion.app.SaxionApp;
import java.awt.*;

/** Class GridDraw
 * Draws everything on the designated coordinates. The methods get called in the gameloop to keep updated with every movement a shape makes.
 * It draws the block, the grid, the grid outline, the background, and it removes the lines when a line is completed.
 * */
public class GridDraw {

    /** 2D color array where the background gets filled on with squares of colors
     * */
    private static Color[][] background;

    /** Calling GameBackend file as gb for easy calling of this class
     * */
    private final GameBackend gb;

    /** Constructor class, with:
     * @param gb to set the game backend
     * background, 2D array is created with the number of columns and rows
     * */
    public GridDraw(GameBackend gb) {
        this.gb = gb;
        background = new Color[GridSettings.height][GridSettings.width];
    }

    /** boolean isBlockOutOfBounds()
     * Check if the shape is above the grid
     * */
    public boolean isBlockOutOfBounds() {
        if (gb.getCurrentblock().getY() < 0) {
            return true;
        }
        return false;
    }

    /** boolean checkBottom() == if return false it can't move down any further
     * first check: if the bottom of a shape is at the height of the grid, then return false
     * gets the shape, (-width, -height) to use in this method
     * second check: is there a background color underneath the bottom block(s) of the shape
     *  ...
     * */
    public boolean checkBottom() {
        if (gb.getCurrentblock().getBottomEdge() == GridSettings.height) {
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

    /** boolean checkLeft(), if false then cannot move left, if true then can move left
     * first check: if the shape is further to the left, then the edge of the grid, then return false
     * gets the shape, (-width, -height) to use in this method
     * Second check: if a block in the background is left to the current position of the shape then it also cannot move left
     *  Loops through every row and every column
     *      Per grid block it checks if the current shape is on that position and if so then the x is going to be one less
     *      if the background on that position isn't null, then the shape cannot move to the left, and it returns false
     * If both checks don't return false, then it returns true which means the shape can move to the left
     * */
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

    /** boolean checkRight(), if false then cannot move right, if true then can move right
     * first check: if the shape is further to the right, then the edge of the grid, then return false
     * gets the shape, (-width, -height) to use in this method
     * Second check: if a block in the background is right to the current position of the shape then it also cannot move right
     *  Loops through every row and every column
     *      Per grid block it checks if the current shape is on that position and if so then the x is going to be one more
     *      if the background on that position isn't null, then the shape cannot move to the right, and it returns false
     * If both checks don't return false, then it returns true which means the shape can move to the right
     * */
    public boolean checkRight() {
        if (gb.getCurrentblock().getRightEdge() == GridSettings.width) {
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

    /** int clearLineCheck()
     * Creates a boolean to check if a line is filled and sets it to true until its proven its false
     * Creates an integer of the amount lines cleared set to 0
     * Makes a for-loop that goes through every row from the bottom row until the top row
     *  Makes in that for-loop first a new for-loop that goes through every column left to right and checks if the background is null,
     *      if so then the boolean for full is set to false, and it breaks the column loop
     *  After going through the columns there is an amount of commands, it clears the filled line, every row shifts down by one, the lines cleared integer gets a plus one
     *  it clears the top row to be sure there are no blocks stuck at the top row, and it counts a 1 at the row in the row-loop, to be sure if there is another filled row, and it
     *  is right above the cleared row it that it still counted in the next loop.
     *  A score has got a plus one with a little sound.
     * After going through every row it returns the number of lines that has been cleared
     * */
    public int clearLineCheck() {
        boolean lineFilled;
        int linesCleared = 0;

        for (int row = GridSettings.height - 1; row >= 0; row--) {
            lineFilled = true;
            for (int col = 0; col < GridSettings.width; col++) {
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

    /** void clearLine(row)
     * @param row: The given row for which the line needs to be cleared
     * For loop that loops through a counter to the length of columns, where every background block is set to null onto that row and that counter-value
     * */
    private void clearLine(int row) {
        for (int i = 0; i < GridSettings.width; i++) {
            background[row][i] = null;
        }
    }

    /** void shiftDown(row)
     * @param row
     *  row stand for the row where the every needs to be shifted down to.
     *  It copies the array of the row above the current counter.
     *  Where the counter first is the row that is given, and then it becomes less until zero
     *  Where zero is the first row in the grid.
     *  The copied array is set to the row beneath the row it copies.
     * */
    private void shiftDown(int row) {
        for (int i = row; i > 0; i--) {
            if (GridSettings.width >= 0) {
                //Copy's the array of the i from the i above
                System.arraycopy(background[i - 1], 0, background[i], 0, GridSettings.width);
            }
        }
    }

    /** repaint(), paint again to make it do this method faster.
     * */
    public void repaint() {
        paint();
    }

    /** Void moveBlockToBackground(), moves a shape to the background[][] when it cannot move anymore.
     * First checks that current block does not equal to null.
     * Then gets shape with the height and width, with its positions and color.
     * Then make two for-loops for the row and column with the height and width as maximum.
     * If the shape has a one on that block, then on the background it will be drawn with the xPosition and yPosition.
     * */
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

    /** Static void drawBlock(color, x, y), draws the blocks with the color and a shadow around it.
     * First: set the border color to the brighter color of the block.
     * Set the fill color as the give color and draw a rectangle on the x and y with the block sizes.
     * *
     * Second: set the border color to the darker color of the block.
     * Make for loop to the size of the shadow, with the block sizes and every time one pixel less until the counter meets the shadow size.
     * This gives a border at the bottom and right of the block with a darker color and makes sure that the corners are diagonal instead of vertical/horizontal lines.
     * *
     * Third: set the border color to the brighter color of the block.
     * Make for loop to the size of the shadow, with the block sizes and every time one pixel less until the counter meets the shadow size.
     * This gives a border at the top and left of the block with a brighter color and makes sure that the corners are diagonal instead of vertical/horizontal lines.
     * */
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

    /** Void paint()
     * First: Draw the grid with rectangles with the canvas background color as fill and border color a little bit brighter than the background color itself.
     * Second: Draw the background, get every tile one the background with two for-loops to go through the rows and columns. And set a color to the tile if the tile is not null.
     * Third: Draw the current shape, get the current shape and set a color the tile if this needs to be colored.
     * Finally: Draw the outline around the grid.
     * */
    public void paint() {
        //Draw grid in outline (Only with lines)
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());
        for (int col = 0; col < GridSettings.width; col++) {
            for (int row = 0; row < GridSettings.height; row++) {
                int x = col * GridSettings.getBlockSize() + GridSettings.getStartPanelX();
                int y = row * GridSettings.getBlockSize() + GridSettings.getStartPanelY();
                SaxionApp.drawRectangle(x, y, GridSettings.getBlockSize(), GridSettings.getBlockSize());
            }

        }

        //Draw background
        Color color;
        for (int row = 0; row < GridSettings.height; row++) {
            for (int col = 0; col < GridSettings.width; col++) {
                color = background[row][col];

                if (color != null) {
                    int x = col * GridSettings.getBlockSize() + GridSettings.getStartPanelX();
                    int y = row * GridSettings.getBlockSize() + GridSettings.getStartPanelY();

                    drawBlock(color, x, y);

                }
            }
        }


        //Draw currentBlock
        if (gb.getCurrentblock() != null) {
            int height = gb.getCurrentblock().getHeight();
            int width = gb.getCurrentblock().getWidth();
            color = gb.getCurrentblock().getColor();
            int[][] shape = gb.getCurrentblock().getShape();

            SaxionApp.setBorderColor(Color.LIGHT_GRAY);
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (shape[row][col] == 1) {
                        int x = (gb.getCurrentblock().getX() + col) * GridSettings.getBlockSize() + GridSettings.getStartPanelX();
                        int y = (gb.getCurrentblock().getY() + row) * GridSettings.getBlockSize() + GridSettings.getStartPanelY();

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
