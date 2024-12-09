package Grid;
import nl.saxion.app.SaxionApp;

public class GridSettings {

    private static final int screenHeight = SaxionApp.getHeight();
    private static final int screenWidth = SaxionApp.getWidth();

    public static int width = 10;
    public static int height = 15;

    public static int nextPieceWidth = 4;
    public static int nextPieceHeight = 4;

    public static int startPanelX = screenWidth / 3;
    public static int endPanelX = startPanelX * 2;
    public static int widthPanel = endPanelX - startPanelX;
    public static int startPanelY = (int)((double) screenHeight / 3 - screenHeight * 0.20);

    //square
    public static int blockSize = widthPanel / width;
    public static int endPanelY = startPanelY + blockSize * height;
    public static int shadowSize = blockSize / 9;

    private static final int loweringGrid = 3;

    public static int startNextPanelX = screenWidth / 10;
    public static int endNextPanelX = startNextPanelX * 2;
    public static int widthNextPanel = endNextPanelX - startNextPanelX;
    public static int blockNextSize = widthNextPanel / nextPieceWidth;
    public static int startNextPanelY = (int)((double) screenHeight / 3 - screenHeight * 0.20 + (blockSize * loweringGrid));
    public static int heightNextPanel = startPanelY  * nextPieceHeight;

}
