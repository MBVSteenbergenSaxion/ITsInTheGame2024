package SideDraw;

import nl.saxion.app.SaxionApp;

public class SideSettings {
    private static final int screenHeight = SaxionApp.getHeight();
    private static final int screenWidth = SaxionApp.getWidth();

    public static int nextPieceWidth = 4;
    public static int nextPieceHeight = 4;

    public static int startNextPanelX = (screenWidth * 41 ) / 50;
    public static int endNextPanelX = (screenWidth * 49) / 50;
    public static int widthNextPanel = endNextPanelX - startNextPanelX;
    public static int blockNextSize = widthNextPanel / nextPieceWidth;
    public static int startNextPanelY = screenHeight / 50;
    public static int endNextPanelY = startNextPanelY + blockNextSize * nextPieceHeight;
    public static int shadowNextSize = blockNextSize / 9;

}
