package Grid;
import nl.saxion.app.SaxionApp;

public class GridSettings {
    /*
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
    public static int blockSize = widthPanel / GridSettings.width;
    public static int heightPanel = startPanelY  * height;

    private static final int loweringGrid = 3;

    public static int startNextPanelX = screenWidth / 10;
    public static int endNextPanelX = startNextPanelX * 2;
    public static int widthNextPanel = endNextPanelX - startNextPanelX;
    public static int blockNextSize = widthNextPanel / nextPieceWidth;
    public static int startNextPanelY = (int)((double) screenHeight / 3 - screenHeight * 0.20 + (blockSize * loweringGrid));
    public static int heightNextPanel = startPanelY  * nextPieceHeight;
    */
    private static final int screenHeight = SaxionApp.getHeight();
    private static final int screenWidth = SaxionApp.getWidth();

    public int VISIBLE_GRID_ROWS = 15;
    public int HIDDEN_GRID_ROWS = 2;
    public int GRID_COLUMNS = 10;

    public int startX_PANEL = screenWidth / 50;
    public int startY_PANEL = screenHeight / 50;
    public int endX_PANEL = screenWidth / 2;

    public int widthPanel = endX_PANEL - startX_PANEL;


    public int BLOCK_SIZE = widthPanel / GRID_COLUMNS;


    public int endY_PANEL = startY_PANEL + BLOCK_SIZE * VISIBLE_GRID_ROWS;

}
