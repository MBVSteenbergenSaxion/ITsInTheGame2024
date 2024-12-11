import Grid.GridSettings;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import SideDraw.*;


public class Game extends Canvas{

    public static GameBackend gb;

    private boolean upKeyPressed;
    private boolean rightKeyPressed;
    private boolean leftKeyPressed;
    public static int scoreCount;
    public static int levelCount;

    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    private static final String[] tetrisLevelUpAudio = {
            ("resources/GameMusic/Theme(SelfMade)/Theme2.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme3.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme4.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme5.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme6.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme7.wav")
    };

    /**
     * Default constructor for the Game class.
     * This constructor calls the superclass's constructor to initialize the game canvas.
     */

    public Game() {
        super();
        gb = new GameBackend();

    }

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
        GameBackend.startGame();

        restartButton.x = SideSettings.getStartNextPanelX();
        restartButton.y = SideSettings.getRestartButtonY();
        restartButton.width = SideSettings.getSideButtonWidth();
        restartButton.height = SideSettings.getSideButtonHeight();

        quitButton.x = SideSettings.getStartNextPanelX();
        quitButton.y = SideSettings.getQuitButtonY();
        quitButton.width = SideSettings.getSideButtonWidth();
        quitButton.height = SideSettings.getSideButtonHeight();
    }

    public static void levelChangingMusic() {
        for (int i = 1; i < tetrisLevelUpAudio.length; i++) {
            if (levelCount == i) {
                Canvas.stopBackgroundMusic();
                Canvas.playBackgroundMusic(tetrisLevelUpAudio[i - 1]);
            }
        }
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


    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            //Handles key pressed
            switch (keyboardEvent.getKeyCode()) {
                case 39, 68: //ArrowRight or D
                    if (!rightKeyPressed) {
                        gb.rightMovement();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        rightKeyPressed = true;
                    }
                    break;
                case 37, 65: //ArrowLeft or A
                    if (!leftKeyPressed) {
                        gb.leftMovement();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        leftKeyPressed = true;
                    }
                    break; //ArrowDown or S
                case 40, 83:
                    gb.dropBlock();
                    break;
                case 38, 87: //ArrowUp or W
                    if (!upKeyPressed) {
                        gb.rotate();
                        SaxionApp.playSound("resources/gameSounds/rotation.wav");
                        upKeyPressed = true;
                    }
                    break;
            }
        } else {
            // Handle key releases
            switch (keyboardEvent.getKeyCode()) {
                case 38, 87: //ArrowUp or W
                    upKeyPressed = false;
                case 39, 68: //ArrowRight or D
                    rightKeyPressed = false;
                case 37, 65: //ArrowLeft or A
                    leftKeyPressed = false;

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
                GameBackend.back2Main();
            }

            if (utils.Utility.checkBounds(x, y,
                    restartButton.x, restartButton.y, restartButton.width,
                    restartButton.height, true)) {
                GameBackend.startGame();
            }
        }
    }

    public static void updateScore(int score) {
        scoreCount = score;
    }

    public static void updateLevel(int level) {
        levelCount = level;
        levelChangingMusic();
    }

    /**
     * Draws the game screen components. This method performs the following actions:
     *  - Draws the restart and quit buttons with their specified properties.
     *  - Initializes a grid drawing object and draws the game grid.
     *  - Calls the spawnBlock method to introduce a new block into the game.
     *  - Draws the newly spawned block on the screen with the specified color.
     */
    private void draw() {
        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, SideSettings.getFontSize(), "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, SideSettings.getFontSize(), "Back to Menu");

        gb.checkToPaint(scoreCount, levelCount);
    }


        public static void startAudioGame() {
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);
    }
}