package Grid;
import nl.saxion.app.SaxionApp;

public class GridSettings {

    private static int screenHeight = SaxionApp.getHeight();
    private static int screenWidth = SaxionApp.getWidth();

    public static int width = 10;
    public static int height = 15;

    public static int nextPieceWidth = 4;
    public static int nextPieceHeight = 4;

    public static int startPanelX = screenWidth / 3;
    public static int endPanelX = startPanelX * 2;
    public static int widthPanel = endPanelX - startPanelX;
    public static int startPanelY = (int)((double) screenHeight / 3 - screenHeight * 0.20);
    //square
    public static int blockSize = widthPanel / GridSettings.width;
    public static int heightPanel = startPanelY  * height;

    public static int startNextPanelX = screenWidth / 6 - 10;
    public static int endNextPanelX = startPanelX * 2 - 10;
    public static int widthNextPanel = endPanelX - startPanelX;
    public static int startNextPanelY = (int)((double) screenHeight / 3 - screenHeight * 0.20);
    public static int blockNextSize = widthPanel / GridSettings.width;
    public static int heightNextPanel = startPanelY  * nextPieceHeight;

}
