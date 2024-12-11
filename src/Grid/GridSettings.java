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
        return (int) (((double) screenWidth / 2) * 0.75);
    }

    public static int getWidthPanel() {
        return (getEndPanelX() - getStartPanelX());
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

    public static void updateScreenDimensions(int newWidth, int newHeight){
        screenWidth = newWidth;
        screenHeight = newHeight;

    }
}