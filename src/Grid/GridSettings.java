package Grid;
import nl.saxion.app.SaxionApp;

public class GridSettings {

    private static final int screenHeight = SaxionApp.getHeight();
    private static final int screenWidth = SaxionApp.getWidth();

    public static int width = 10;
    public static int height = 15;
    public static int whoops = 8;

    public static int startPanelX = screenWidth / 50;
    public static int endPanelX = screenWidth / 2;
    public static int widthPanel = endPanelX - startPanelX;
    public static int startPanelY = screenHeight / 50;
    public static int blockSize = widthPanel / width;
    public static int endPanelY = startPanelY + blockSize * height;
    public static int shadowSize = blockSize / 9;
}
