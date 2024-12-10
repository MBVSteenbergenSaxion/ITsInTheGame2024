import Grid.GridSettings;
import nl.saxion.app.SaxionApp;

import java.awt.*;

public class SideDraw {


    public static int nextGridRows, nextGridWidth, nextGridCellSize;

    private final GameBackend gb;
    public SideDraw(GameBackend gb) {
        this.gb = gb;
        initializeGridSettings();
    }

    private void initializeGridSettings() {
        nextGridWidth = GridSettings.nextPieceWidth;
        nextGridCellSize = GridSettings.blockNextSize;
        nextGridRows = GridSettings.nextPieceHeight;
    }

    public void repaint() {
        paint();
    }

    public void paint() {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());

        for (int row = 0; row < nextGridRows; row++) {
            for (int col = 0; col < nextGridWidth; col++) {
                int x = col * nextGridCellSize + GridSettings.startNextPanelX;
                int y = row * nextGridCellSize + GridSettings.startNextPanelY;
                SaxionApp.setBorderColor(Color.LIGHT_GRAY);
                SaxionApp.drawRectangle(x,y,nextGridCellSize,nextGridCellSize);
            }
        }

        //Draw nextblock
        int height = gb.getNextblock().getHeight();
        int width = gb.getNextblock().getWidth();
        Color color = gb.getNextblock().getColor();
        int[][] shape = gb.getNextblock().getShape();

        SaxionApp.setBorderColor(Color.LIGHT_GRAY);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (shape[row][col] == 1) {
                    int x = (gb.getNextblock().getX() + col) * nextGridCellSize + GridSettings.startNextPanelX;
                    int y = (gb.getNextblock().getY() + row) * nextGridCellSize + GridSettings.startNextPanelY;

                    if (utils.Utility.checkBounds(x, y, GridSettings.startNextPanelX,
                            GridSettings.startNextPanelY, GridSettings.widthNextPanel,
                            GridSettings.endNextPanelY, false)) {
                        drawNextBlock(color, x, y);
                    }
                }
            }
        }

        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(GridSettings.startNextPanelX, GridSettings.startNextPanelY, GridSettings.startNextPanelX, GridSettings.endNextPanelY); //Line LeftSide
        SaxionApp.drawLine(GridSettings.endNextPanelX, GridSettings.startNextPanelY, GridSettings.endNextPanelX, GridSettings.endNextPanelY); //Line RightSide
        SaxionApp.drawLine(GridSettings.startNextPanelX, GridSettings.startNextPanelY, GridSettings.endNextPanelX, GridSettings.startNextPanelY); //Line TopSide
        SaxionApp.drawLine(GridSettings.startNextPanelX, GridSettings.endNextPanelY, GridSettings.endNextPanelX, GridSettings.endNextPanelY); //Line BottomSide

    }

    private static void drawNextBlock(Color color, int x, int y) {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y, nextGridCellSize, nextGridCellSize);

        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < GridSettings.shadowSize; i++) {
            SaxionApp.drawLine(x + nextGridCellSize - GridSettings.shadowSize + i, y, x + nextGridCellSize - GridSettings.shadowSize + i, y + nextGridCellSize - 1);
            SaxionApp.drawLine(x, y + nextGridCellSize - GridSettings.shadowSize + i, x + nextGridCellSize - 1, y + nextGridCellSize - GridSettings.shadowSize + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < GridSettings.shadowSize; i++) {
            SaxionApp.drawLine(x, y + i, x + nextGridCellSize - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + nextGridCellSize - i - 1);
        }
    }
}
