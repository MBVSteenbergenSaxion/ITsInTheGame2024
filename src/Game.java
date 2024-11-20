import Grid.Block;
import Grid.GridSettings;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends Canvas{

    private Block block;

    public Game() {
        super();
    }


    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    @Override
    public void init() {
        restartButton.x = Settings.width - Settings.width / 4;
        restartButton.y = Settings.height / 3;
        restartButton.width = Settings.buttonWidth / 2;
        restartButton.height = Settings.buttonHeight / 2;

        quitButton.x = Settings.width - Settings.width / 4;
        quitButton.y = Settings.height / 2;
        quitButton.width = Settings.buttonWidth / 2;
        quitButton.height = Settings.buttonHeight / 2;


    }

    public void loop() {
        draw();
        /*
        Not good see gamethread for more info
        Dit moet in gamethread.
        while (true) {
        block.moveDown();
        SaxionApp.sleep(1);
        }

        */
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (MyButton.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height)) {

                switchToScreen(new Main());

            }
        }
    }

    public void spawnBlock() {
        block = new Block(new int[][] { {1,0}, {1,0}, {1,1} });
        block.spawn();
    }

    public void moveBlockDown() {
        /*
        Voor wanneer het in de GameThread werkt

        if (checkBottom() == false) {
            return;
        }

        block.moveDown();
        repaint(); --> Andere functie hiervoor? Dit is helaas geen Graphics maar Saxion
        */
    }

    private boolean checkBottom() {
        if (block.getBottomEdge() == GridSettings.height) {
            return false;
        }
        return true;
    }

    public void drawBlock(Color color) {
        int h = block.getHeight();
        int w = block.getWidth();
        int[][] shape = block.getShape();

        for (int r = 0; r < h; r++) {
            for (int b = 0; b < w; b++) {
                if(shape[r][b] == 1) {
                    int x = (block.getX() + b) * GridSettings.blockSize + GridSettings.startPanelX;
                    int y = (block.getY() + r) * GridSettings.blockSize  + GridSettings.startPanelY;

                    SaxionApp.setFill(color);
                    SaxionApp.drawRectangle(x, y, GridSettings.blockSize, GridSettings.blockSize);
                }
            }
        }
    }

    private void draw(){

        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        GridDraw gridDraw = new GridDraw();
        gridDraw.drawGrid();

        spawnBlock();
        drawBlock(Color.RED);

    }

}