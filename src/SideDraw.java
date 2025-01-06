import SideDraw.*;
import nl.saxion.app.SaxionApp;
import java.awt.*;

/** class SideDraw
 *      Draws the side panel with the next piece (-grid), score/level text, quit/restart buttons and the controls image.
 * */
public class SideDraw {

    /** integers for the numbers for the next piece grid: the rows, columns, and cell size.
     * */
    public static int nextGridRows, nextGridColumns, nextGridCellSize;

    /** Variable for calling the class GameBackend
     * */
    private final GameBackend gb;

    /** Constructor method for SideDraw
     * @param gb, gets given with calling this class and gets every shape it needs.
     * */
    public SideDraw(GameBackend gb) {
        this.gb = gb;
        initializeGridSettings();
    }

    /** Void initializeGridSettings(), gets the number of columns, rows and cell size from the SideSettings class
     * */
    private void initializeGridSettings() {
        nextGridColumns = SideSettings.nextPieceWidth;
        nextGridCellSize = SideSettings.getBlockNextSize();
        nextGridRows = SideSettings.nextPieceHeight;
    }

    /** Static void drawNextBlock(color, x, y)
     * @param color, for the color given with the calling of this method. This is or the background color of the canvas or the color of the shape.
     * @param x, for the x coordinate where a block needs to be drawn on.
     * @param y, for the y coordinate where a block needs to be drawn on.
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
    private static void drawNextBlock(Color color, int x, int y) {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y, nextGridCellSize, nextGridCellSize);

        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < SideSettings.getShadowNextSize(); i++) {
            SaxionApp.drawLine(x + nextGridCellSize - SideSettings.getShadowNextSize() + i, y, x + nextGridCellSize - SideSettings.getShadowNextSize() + i, y + nextGridCellSize - 1);
            SaxionApp.drawLine(x, y + nextGridCellSize - SideSettings.getShadowNextSize() + i, x + nextGridCellSize - 1, y + nextGridCellSize - SideSettings.getShadowNextSize() + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < SideSettings.getShadowNextSize(); i++) {
            SaxionApp.drawLine(x, y + i, x + nextGridCellSize - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + nextGridCellSize - i - 1);
        }
    }

    /** void drawScore(scoreCount, levelCount)
     * @param scoreCount, the integer that represents the current score of the player, which is than shown on the screen as text
     * @param levelCount, the integer that represents the current score of the player, which is than shown on the screen as text
     * */
    public void drawScore(int scoreCount, int levelCount) {
        SaxionApp.drawText("Score: " + scoreCount, SideSettings.getStartNextPanelX(), SideSettings.getScoreY(), SideSettings.getFontSize());
        SaxionApp.drawText("Level: " + levelCount, SideSettings.getStartNextPanelX(), SideSettings.getLevelY(), SideSettings.getFontSize());
    }

    /** void drawImage(), draws the image with all the controls you can use in the game.
     * */
    public void drawImage() {
        SaxionApp.drawImage("resources/Images/tetris_controls.png", SideSettings.getStartNextPanelX(), SideSettings.getSideImageY(), SideSettings.getSideImageWidth(), SideSettings.getSideHeight());
    }

    /** Void paint(), main paint method for the SideDraw class
     * First: Draws the gridlines with a brighter border color of canvas background color and sets the background color as the fill color for the grid tiles.
     * Second: Draws the next shape in the next piece grid, where it first gets information of the shape and then sets the border color for these blocks to light gray
     *  After that it loops through the rows and columns until the height and width of the shape is reached. It draws a block on every tile in the next piece grid where it needs to be drawn on.
     * Finally, It draws the outline of the next piece grid.
     * */
    public void paint() {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());

        for (int row = 0; row < nextGridRows; row++) {
            for (int col = 0; col < nextGridColumns; col++) {
                int x = col * nextGridCellSize + SideSettings.getStartNextPanelX();
                int y = row * nextGridCellSize + SideSettings.getStartNextPanelY();
                SaxionApp.setBorderColor(Color.LIGHT_GRAY);
                SaxionApp.drawRectangle(x,y,nextGridCellSize,nextGridCellSize);
            }
        }

        //Draws next piece
        int height = gb.getNextblock().getHeight();
        int width = gb.getNextblock().getWidth();
        Color color = gb.getNextblock().getColor();
        int[][] shape = gb.getNextblock().getShape();

        SaxionApp.setBorderColor(Color.LIGHT_GRAY);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) {
                    int x = (gb.getNextblock().getX() + col) * nextGridCellSize + SideSettings.getStartNextPanelX();
                    int y = (gb.getNextblock().getY() + row) * nextGridCellSize + SideSettings.getStartNextPanelY();

                    if (utils.Utility.checkBounds(x, y, SideSettings.getStartNextPanelX(),
                            SideSettings.getStartNextPanelY(), SideSettings.getWidthNextPanel(),
                            SideSettings.getEndNextPanelY(), false)) {
                        drawNextBlock(color, x, y);
                    }
                }
            }
        }

        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getStartNextPanelX(), SideSettings.getEndNextPanelY()); //Line LeftSide
        SaxionApp.drawLine(SideSettings.getEndNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getEndNextPanelY()); //Line RightSide
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getStartNextPanelY()); //Line TopSide
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getEndNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getEndNextPanelY()); //Line BottomSide

    }
}