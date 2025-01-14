package grid;

import nl.saxion.app.SaxionApp;

/** Class GridSettings
 * Has variables for the sizing of every shape / line in the game grid
 * */
public class GridSettings {
    /** screenHeight, gets the screen height with the SaxionApp
     * */
    private static int screenHeight = SaxionApp.getHeight();

    /** screenWidth, gets the screen width with the SaxionApp
     * */
    private static int screenWidth = SaxionApp.getWidth();

    /** Sets the amount of columns of the game grid
     * */
    public static int width = 10;

    /** Sets the amount of rows of the game grid
     * */
    public static int height = 15;


    /** getStartPanelX()
     * returns first fiftieth of the screen width
     * */
    public static int getStartPanelX() {
        return screenWidth / 50;
    }

    /** getEndPanelX()
     * returns the calculated half of the middle x value of the screen. Here ends the game grid
     * */
    public static int getEndPanelX() {
        return (int) (((double) screenWidth / 50) * 25);
    }

    /** getWidthPanel()
     * returns amount of pixels between the start x and end x. This returns as the width of the game grid panel.
     * */
    public static int getWidthPanel() {
        return (getEndPanelX() - getStartPanelX());
    }

    /** getStartPanelY()
     * returns the first fiftieth of the screen height, which will be start position on the Y
     * */
    public static int getStartPanelY() {
        return screenHeight / 50;
    }

    /** getBlockSize()
     * returns how big the block on the grid needs to be, this is the game grid panel width / the amount of columns for the game grid.
     * */
    public static int getBlockSize() {
        return getWidthPanel() / width;
    }

    /** getEndPanelY()
     * returns the end of the Y for the game grid panel, this is the startY with the amount of blocks * how big a block needs to be
     * */
    public static int getEndPanelY() {
        return getStartPanelY() + getBlockSize() * height;
    }

    /** getShadowSize()
     * returns the size of the shadow around the edges of a block
     * calculated with one ninth of the block size
     * */
    public static int getShadowSize() {
        return getBlockSize() / 9;
    }

    /** updateScreenDimensions(newWidth, newHeight)
     * When the screen is resized, the dimensions of how the x, y and blocks values needs to be updated.
     * For this, this method is called to update the screenWidth/screenHeight with the given newWidth/newHeight
     * */
    public static void updateScreenDimensions(int newWidth, int newHeight){
        screenWidth = newWidth;
        screenHeight = newHeight;
    }
}