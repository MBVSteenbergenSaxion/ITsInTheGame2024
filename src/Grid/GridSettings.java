package Grid;

import nl.saxion.app.SaxionApp;

public class GridSettings {

    private static int screenHeight = SaxionApp.getHeight();
    private static int screenWidth = SaxionApp.getWidth();

    public static int width = 10;
    public static int height = 15;

    public static int getStartPanelX() {
        return screenWidth / 50;
    }

    public static int getEndPanelX() {
        return screenWidth / 2;
    }

    public static int getWidthPanel() {
        return getEndPanelX() - getStartPanelX();
    }

    public static int getStartPanelY() {
        return screenHeight / 50;
    }

    public static int getBlockSize() {
        return getWidthPanel() / width;
    }

    public static int getEndPanelY() {
        return getStartPanelY() + getBlockSize() * height;
    }

    public static int getShadowSize() {
        return getBlockSize() / 9;
    }


    /*
    public static int getStartNextPanelX() {
        return screenWidth / 10;
    }

    public static int getEndNextPanelX() {
        return getStartNextPanelX() * 2;
    }

    public static int getWidthNextPanel() {
        return getEndNextPanelX() - getStartNextPanelX();
    }

    public static int getBlockNextSize() {
        return getWidthNextPanel() / nextPieceWidth;
    }

    public static int getStartNextPanelY() {
        return (int) ((double) screenHeight / 3 - screenHeight * 0.20 + (getBlockSize() * 3));
    }

    public static int getHeightNextPanel() {
        return getStartPanelY() * nextPieceHeight;
    }
    */
    // Update screen dimensions
    public static void updateScreenDimensions(int newWidth, int newHeight) {
        screenWidth = newWidth;
        screenHeight = newHeight;
    }

}
