import SideDraw.SideSettings;
import nl.saxion.app.SaxionApp;
import utils.MyButton;

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
        nextGridCellSize = SideSettings.getBlockNextSize();
        nextGridRows = SideSettings.nextPieceHeight;
    }

    public static void buttonInitialization(MyButton buttonName, int buttonY) {
        buttonName.x = SideSettings.getStartNextPanelX();
        buttonName.y = buttonY;
        buttonName.width = Settings.buttonWidth / 2;
        buttonName.height = Settings.buttonHeight / 2;
    }

    public void repaint() {
        paint();
    }

    public void paint() {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(Canvas.getColor());

        for (int row = 0; row < nextGridRows; row++) {
            for (int col = 0; col < nextGridWidth; col++) {
                int x = col * nextGridCellSize + SideSettings.getStartNextPanelX();
                int y = row * nextGridCellSize + SideSettings.getStartNextPanelY();
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
                    int x = (gb.getNextblock().getX() + col) * nextGridCellSize + SideSettings.getStartNextPanelX();
                    int y = (gb.getNextblock().getY() + row) * nextGridCellSize + SideSettings.getStartNextPanelY();

                    if (utils.Utility.checkBounds(x, y, SideSettings.getStartNextPanelX(),
                            SideSettings.getStartNextPanelY(), SideSettings.getWidthNextPanel(),
                            SideSettings.getEndNextPanelY(), false)) {
                        drawNextBlock(color, x, y);
                    }
                }
            }
        }

        //Draw outline grid
        SaxionApp.setBorderColor(Color.lightGray);
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getStartNextPanelX(), SideSettings.getEndNextPanelY()); //Line LeftSide
        SaxionApp.drawLine(SideSettings.getEndNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getEndNextPanelY()); //Line RightSide
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getStartNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getStartNextPanelY()); //Line TopSide
        SaxionApp.drawLine(SideSettings.getStartNextPanelX(), SideSettings.getEndNextPanelY(), SideSettings.getEndNextPanelX(), SideSettings.getEndNextPanelY()); //Line BottomSide

    }

    private static void drawNextBlock(Color color, int x, int y) {
        SaxionApp.setBorderColor(Canvas.getColor().brighter());
        SaxionApp.setFill(color);
        SaxionApp.drawRectangle(x,y, nextGridCellSize, nextGridCellSize);

        SaxionApp.setBorderColor(color.darker());
        for(int i = 0; i < SideSettings.getShadowNextSize(); i++) {
            SaxionApp.drawLine(x + nextGridCellSize - SideSettings.getShadowNextSize() + i, y, x + nextGridCellSize - SideSettings.getShadowNextSize() + i, y + nextGridCellSize - 1);
            SaxionApp.drawLine(x, y + nextGridCellSize - SideSettings.getShadowNextSize() + i, x + nextGridCellSize - 1, y + nextGridCellSize - SideSettings.getShadowNextSize() + i);
        }

        SaxionApp.setBorderColor(color.brighter());
        for(int i = 0; i < SideSettings.getShadowNextSize(); i++) {
            SaxionApp.drawLine(x, y + i, x + nextGridCellSize - i - 1, y + i);
            SaxionApp.drawLine(x + i, y, x + i, y + nextGridCellSize - i - 1);
        }
    }

    public void drawScore(int scoreCount, int levelCount) {
        SaxionApp.drawText("Score: " + scoreCount, SideSettings.getStartNextPanelX(), SideSettings.getScoreY(), SideSettings.getFontSize());
        SaxionApp.drawText("Level: " + levelCount, SideSettings.getStartNextPanelX(), SideSettings.getLevelY(), SideSettings.getFontSize());

    }

    public void drawText() {

    }
}