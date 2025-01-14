package sidedraw;

import nl.saxion.app.SaxionApp;

/** Class SideSettings
 * Has variables for the sizing of the NextPiece grid, the blocks in that grid, positioning for the buttons, score/level text and control information.
 * */
public class SideSettings {
    /** screenHeight, gets the screen height with the SaxionApp
     * */
    private static int screenHeight = SaxionApp.getHeight();

    /** screenWidth, gets the screen width with the SaxionApp
     * */
    private static int screenWidth = SaxionApp.getWidth();

    /** nextPieceWidth, the amount of columns for the next piece grid
     * */
    public static int nextPieceWidth = 4;

    /** nextPieceHeight, the amount of rows for the next piece grid
     * */
    public static int nextPieceHeight = 4;

    /** Returns one fiftieth of the screen width
     * */
    public static int getOneFiftiethOfScreenWidth() {
        return screenWidth / 50;
    }

    /** Returns one fiftieth of the screen height
     * */
    public static int getOneFiftiethOfScreenHeight() {
        return screenHeight / 50;
    }

    /** Returns the x coordinate of where the side panel needs to start
     * */
    public static int getStartNextPanelX() {
        return getOneFiftiethOfScreenWidth() * 30;
    }

    /** Returns the x coordinate of where the next piece grid needs to end
     * */
    public static int getEndNextPanelX() {
        return getOneFiftiethOfScreenWidth() * 38;
    }

    /** Returns the width of the next piece grid panel
     * */
    public static int getWidthNextPanel() {
        return getEndNextPanelX() - getStartNextPanelX();
    }

    /** Returns the block size for the next piece, where the block size is calculated with the amount of pixels in the width of the next piece panel en the amount of columns in the next piece grid
     * */
    public static int getBlockNextSize() {
        return getWidthNextPanel() / nextPieceWidth;
    }

    /** Returns the y coordinate of where the side panel starts
     * */
    public static int getStartNextPanelY() {
        return getOneFiftiethOfScreenHeight();
    }

    /** Returns the y coordinate of where the next piece panel ends
     * */
    public static int getEndNextPanelY() {
        return getStartNextPanelY() + getBlockNextSize() * nextPieceHeight;
    }

    /** Returns the shadow size on the next piece blocks
     * */
    public static int getShadowNextSize() {
        return getBlockNextSize() / 9;
    }

    /** Updates the screen dimensions when this method is called (for resizing)
     * */
    public static void updateScreenDimensions(int newWidth, int newHeight){
        screenWidth = newWidth;
        screenHeight = newHeight;
    }

    /** Returns an integer variable that calculates that the button height needs to be 3.5x of a one fiftieth of the screen height
     * */
    public static int getSideButtonHeight(){
        return (int) (getOneFiftiethOfScreenHeight() * 3.5);
    }

    /** Returns the button width as the width of the next piece panel
     * */
    public static int getSideButtonWidth(){
        return getWidthNextPanel();
    }

    /** Returns the Y coordinate of restart button
     * */
    public static int getRestartButtonY() {
        return getEndNextPanelY() + getOneFiftiethOfScreenHeight() * 2;
    }

    /** Returns the Y coordinate of quit button
     * */
    public static int getQuitButtonY() {
        return getOneFiftiethOfScreenHeight() * 5 + getRestartButtonY();
    }

    /** Returns the Y coordinate of the score text
     * */
    public static int getScoreY() {
        return getQuitButtonY() + getOneFiftiethOfScreenHeight() * 5;
    }

    /** Returns the Y coordinate of the level
     * */
    public static int getLevelY() {
        return getScoreY() + getOneFiftiethOfScreenHeight() * 3;
    }

    /** Returns the Y coordinate of font size of the text
     * */
    public static int getFontSize() {
        return (int) (getOneFiftiethOfScreenWidth() * 0.75);
    }

    /** Returns the Y coordinate of the controls image
     * */
    public static int getSideImageY() {
        return getLevelY() + getOneFiftiethOfScreenHeight() * 3;
    }

    /** Returns the control image width as the width of the next piece panel
     * */
    public static int getSideImageWidth() {
        return getWidthNextPanel();
    }

    /** Returns the side height
     * */
    public static int getSideHeight() {
        return ((getSideImageWidth() * 2 ) / 3);
    }

}
