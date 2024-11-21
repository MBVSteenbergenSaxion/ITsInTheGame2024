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

    public static Block block = new Block(new int[][]{{}});

    /**
     * Default constructor for the Game class.
     *
     * This constructor calls the superclass's constructor to initialize the game canvas.
     */
    public Game() {
        super();
    }

    /**
     * Creates the two MyButton objects with the names restart and quit
     * These buttons are used in the Initialize method called init()
     */
    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();
    GameThread gameThread  = new GameThread();

    /**
     * Initializes the restart and quit buttons with their positions and dimensions.
     *
     * This method sets the coordinates and sizes of the restart and quit buttons by
     * calculating their positions relative to the canvas dimensions defined in the Settings class.
     * The restart button is positioned at one-third of the canvas height, while the quit button is
     * positioned at half the canvas height. Both buttons are placed at a quarter of the canvas width
     * horizontally.
     */
    @Override
    public void init() {

        gameThread.start();

        restartButton.x = Settings.width - Settings.width / 4;
        restartButton.y = Settings.height / 3;
        restartButton.width = Settings.buttonWidth / 2;
        restartButton.height = Settings.buttonHeight / 2;

        quitButton.x = Settings.width - Settings.width / 4;
        quitButton.y = Settings.height / 2;
        quitButton.width = Settings.buttonWidth / 2;
        quitButton.height = Settings.buttonHeight / 2;


    }

    /**
     * Executes the game loop by invoking the `draw` method.
     *
     * This method is a placeholder for the game's main loop which should ideally
     * handle periodic updates, such as moving game elements and rendering the
     * game screen. Currently, it only calls the `draw` method to render the game
     * screen components.
     *
     * Note: The commented-out code suggests that the movement logic for the game
     * elements should reside in a separate game thread. For more information,
     * refer to the `gamethread` documentation.
     */
    public void loop() {
        draw();
        
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    /**
     * Handles mouse events, primarily focusing on the left mouse button.
     * If the left mouse button is clicked within the bounds of the 'quitButton',
     * it triggers a screen switch to the main menu.
     *
     * @param mouseEvent the MouseEvent object that contains information about the mouse event
     */
    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height)) {

                switchToScreen(new Main());

            }
        }
    }


    /**
     * Spawns a new block with a defined shape and initializes its position.
     * This method creates a new Block object with the specified shape and
     * calls the spawn method to set its initial coordinates.
     */
    public static void spawnBlock() {
        int[][] shape = new int[][] { {1,0}, {1,0}, {1,1} };
        block = new Block(shape);
        block.spawn();
    }

    /**
     * Moves the current block one step down if possible and triggers a screen repaint.
     *
     * This method first checks if moving the block down is allowed by calling the checkBottom() method.
     * If the block can be moved down (checkBottom() returns true), it invokes the moveDown() method of the block.
     * After moving the block, it repaints the screen to reflect the new position of the block.
     *
     * The repaint functionality is not handled within this method and needs a separate implementation
     * as the existing API does not support Graphics directly.
     */
    public static void moveBlockDown() {

        if (!checkBottom()) {
            return;
        }

        block.moveDown();

    }

    /**
     * Checks bottom, if the bottom is reached boolean is set to false
     * */
    private static boolean checkBottom() {
        if (block.getBottomEdge() == GridSettings.height) {
            return false;
        }
        return true;
    }

    /**
     * Draws a block on the screen with the specified color.
     *
     * @param color the color to fill the block with
     */
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


    /**
     * Draws the game screen components. This method performs the following actions:
     *  - Draws the restart and quit buttons with their specified properties.
     *  - Initializes a grid drawing object and draws the game grid.
     *  - Calls the spawnBlock method to introduce a new block into the game.
     *  - Draws the newly spawned block on the screen with the specified color.
     */
    private void draw(){

        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        GridDraw gridDraw = new GridDraw();
        gridDraw.drawGrid();

        spawnBlock();
        drawBlock(Color.RED);

    }

}