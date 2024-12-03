package Grid;
import nl.saxion.app.SaxionApp;

public class GridSettings {

    public static int screenHeight = SaxionApp.getHeight();
    public static int screenWidth = SaxionApp.getWidth();

    public static final int width = 10;
    public static final int height = 15;

    public static final int nextPieceWidth = 4;
    public static final int nextPieceHeight = 4;

    public static int getStartPanelX() {
        return screenWidth / 3;
    }

    public static int getEndPanelX() {
        return getStartPanelX() * 2;
    }

    public static int getWidthPanel() {
        return getEndPanelX() - getStartPanelX();
    }

    public static int getStartPanelY() {
        return (int) ((double) screenHeight / 3 - screenHeight * 0.20);
    }

    public static int getBlockSize() {
        return getWidthPanel() / width;
    }

    public static int getHeightPanel() {
        return getStartPanelY() * height;
    }

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

    // Update screen dimensions
    public static void updateScreenDimensions(int newWidth, int newHeight) {
        screenWidth = newWidth;
        screenHeight = newHeight;
    }
}