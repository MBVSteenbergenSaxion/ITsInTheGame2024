package SideDraw;

import nl.saxion.app.SaxionApp;

public class SideSettings {
    private static final int screenHeight = SaxionApp.getHeight();
    private static final int screenWidth = SaxionApp.getWidth();

    public static int nextPieceWidth = 4;
    public static int nextPieceHeight = 4;

    public static int oneFiftiethOfScreenWidth = (screenWidth / 50);
    public static int oneFiftiethOfScreenHeight = (screenHeight / 50);

    public static int startNextPanelX = oneFiftiethOfScreenWidth * 41;
    public static int endNextPanelX = oneFiftiethOfScreenWidth * 49;
    public static int widthNextPanel = endNextPanelX - startNextPanelX;
    public static int blockNextSize = widthNextPanel / nextPieceWidth;
    public static int startNextPanelY = oneFiftiethOfScreenHeight;
    public static int endNextPanelY = startNextPanelY + blockNextSize * nextPieceHeight;
    public static int shadowNextSize = blockNextSize / 9;

    public static int scoreY = oneFiftiethOfScreenHeight * 25;
    public static int levelY = oneFiftiethOfScreenHeight * 28;
    public static int getRestartButtonY() {
        return oneFiftiethOfScreenHeight * 15;
    }
    public static int getQuitButtonY() {
        return oneFiftiethOfScreenHeight * 20;
    }

}
