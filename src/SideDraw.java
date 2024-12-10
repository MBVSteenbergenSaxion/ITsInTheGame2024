import SideDraw.SideSettings;
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
        nextGridWidth = SideSettings.nextPieceWidth;
        nextGridCellSize = SideSettings.blockNextSize;
        nextGridRows = SideSettings.nextPieceHeight;
    }

    public void repaint() {
        paint();
    }

    public void paint() {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());

        for (int row = 0; row < nextGridRows; row++) {
            for (int col = 0; col < nextGridWidth; col++) {
                int x = col * nextGridCellSize + SideSettings.startNextPanelX;
                int y = row * nextGridCellSize + SideSettings.startNextPanelY;
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
                    int x = (gb.getNextblock().getX() + col) * nextGridCellSize + SideSettings.startNextPanelX;
                    int y = (gb.getNextblock().getY() + row) * nextGridCellSize + SideSettings.startNextPanelY;

                    if (utils.Utility.checkBounds(x, y, SideSettings.startNextPanelX,
                            SideSettings.startNextPanelY, SideSettings.widthNextPanel,
                            SideSettings.endNextPanelY, false)) {
                        drawNextBlock(color, x, y);
                    }
                }
            }
        }

        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(SideSettings.startNextPanelX, SideSettings.startNextPanelY, SideSettings.startNextPanelX, SideSettings.endNextPanelY); //Line LeftSide
        SaxionApp.drawLine(SideSettings.endNextPanelX, SideSettings.startNextPanelY, SideSettings.endNextPanelX, SideSettings.endNextPanelY); //Line RightSide
        SaxionApp.drawLine(SideSettings.startNextPanelX, SideSettings.startNextPanelY, SideSettings.endNextPanelX, SideSettings.startNextPanelY); //Line TopSide
        SaxionApp.drawLine(SideSettings.startNextPanelX, SideSettings.endNextPanelY, SideSettings.endNextPanelX, SideSettings.endNextPanelY); //Line BottomSide

    }

    private static void drawNextBlock(Color color, int x, int y) {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y, nextGridCellSize, nextGridCellSize);

        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < SideSettings.shadowNextSize; i++) {
            SaxionApp.drawLine(x + nextGridCellSize - SideSettings.shadowNextSize + i, y, x + nextGridCellSize - SideSettings.shadowNextSize + i, y + nextGridCellSize - 1);
            SaxionApp.drawLine(x, y + nextGridCellSize - SideSettings.shadowNextSize + i, x + nextGridCellSize - 1, y + nextGridCellSize - SideSettings.shadowNextSize + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < SideSettings.shadowNextSize; i++) {
            SaxionApp.drawLine(x, y + i, x + nextGridCellSize - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + nextGridCellSize - i - 1);
        }
    }
}
