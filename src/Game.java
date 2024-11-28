import Grid.GridSettings;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Game extends Canvas{

    private GridDraw gd;
    private GameThread gt;

    /**
     * Default constructor for the Game class.
     * This constructor calls the superclass's constructor to initialize the game canvas.
     */
    public Game() {
        super();

        gd = new GridDraw(GridSettings.width);
        gt = new GameThread(gd);
    }

    /**
     * Creates the two MyButton objects with the names restart and quit
     * These buttons are used in the Initialize method called init()
     */
    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    /**
     * Initializes the restart and quit buttons with their positions and dimensions.
     * This method sets the coordinates and sizes of the restart and quit buttons by
     * calculating their positions relative to the canvas dimensions defined in the Settings class.
     * The restart button is positioned at one-third of the canvas height, while the quit button is
     * positioned at half the canvas height. Both buttons are placed at a quarter of the canvas width
     * horizontally.
     */
    @Override
    public void init() {
        startGame();

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
     * This method is a placeholder for the game's main loop which should ideally
     * handle periodic updates, such as moving game elements and rendering the
     * game screen. Currently, it only calls the `draw` method to render the game
     * screen components.
     * Note: The commented-out code suggests that the movement logic for the game
     * elements should reside in a separate game thread. For more information,
     * refer to the `gamethread` documentation.
     */
    public void loop() {
        draw();
    }

    public void startGame() {
        gt.start();
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if(keyboardEvent.isKeyPressed()){
            if (keyboardEvent.getKeyCode() == 39) {//RIGHT
                gd.moveBlockRight();
                SaxionApp.playSound("resources/gameSounds/movement.wav");
            } if (keyboardEvent.getKeyCode() == 37) { //LEFT
                gd.moveBlockLeft();
                SaxionApp.playSound("resources/gameSounds/movement.wav");
            } if (keyboardEvent.getKeyCode() == 40) { //DOWN
                gd.dropBlock();
            } if (keyboardEvent.getKeyCode() == 38) { //UP
                gd.rotateBlock();
                SaxionApp.playSound("resources/gameSounds/rotation.wav");
            }
        }
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
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height, true)) {

                gt.interrupt();
                switchToScreen(new Main());
            }

            if (utils.Utility.checkBounds(x, y,
                    restartButton.x, restartButton.y, restartButton.width,
                    restartButton.height, true)) {

                gt.interrupt();
                SaxionApp.clear();
                gd = new GridDraw(GridSettings.width);
                gt = new GameThread(gd);
                gt.start();
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

        gd.drawGrid();
        gd.drawBackground();
        gd.repaint();
    }

}