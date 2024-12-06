import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.*;

public class Game extends Canvas {

    public static GridDraw gd;
    //public static GameThread gt;
    private boolean upKeyPressed, rightKeyPressed, leftKeyPressed;
    public static int scoreCount;
    public static int levelCount = 1;

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

    public Game() {
        super();

        gd = new GridDraw();
        //gt = new GameThread(gd);
    }

    /**
     * STANDARD GAME METHODS
     * - init()
     * - loop()
     * - keyboardEvent()
     * - mouseEvent()
     */

    @Override
    public void init() {
        startGame();

        buttonInitialization(restartButton, 3);
        buttonInitialization(quitButton, 2);
    }

    public void loop() {
        draw();
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        /*
        if (keyboardEvent.isKeyPressed()) {
            //Handles key pressed
            switch (keyboardEvent.getKeyCode()) {
                case 39, 68: //ArrowRight or D
                    if (!rightKeyPressed) {
                        gd.moveBlockRight();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        rightKeyPressed = true;
                    }
                    break;
                case 37, 65: //ArrowLeft or A
                    if (!leftKeyPressed) {
                        gd.moveBlockLeft();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        leftKeyPressed = true;
                    }
                    break; //ArrowDown or S
                case 40, 83:
                    gd.dropBlock();
                    break;
                case 38, 87: //ArrowUp or W
                    if (!upKeyPressed) {
                        gd.rotateBlock();
                        SaxionApp.playSound("resources/gameSounds/rotation.wav");
                        upKeyPressed = true;
                    }
                    break;
            }
        }else {
            // Handle key releases
            switch (keyboardEvent.getKeyCode()) {
                case 38, 87: //ArrowUp or W
                    upKeyPressed = false;
                case 39, 68: //ArrowRight or D
                    rightKeyPressed = false;
                case 37, 65: //ArrowLeft or A
                    leftKeyPressed = false;

            }
        }*/
    }


    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height, true)) {
                back2Main();
            }

            if (utils.Utility.checkBounds(x, y,
                    restartButton.x, restartButton.y, restartButton.width,
                    restartButton.height, true)) {
                restart();
            }
        }
    }

    /**
     * START METHODS
     * - startGame()
     * - startAudioGame()
     */

    public void startGame() {
        /*
        startAudioGame();
        gt.start();
        scoreCount = 0;*/
    }

    public static void startAudioGame() {
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);
    }

    /**
     * INITIALIZING METHODS
     * - buttonInitialization(MyButton, int buttonY)
     */

    public static void buttonInitialization(MyButton buttonName, int buttonY) {
        buttonName.x = Settings.width - Settings.width / 4;
        buttonName.y = Settings.height / buttonY;
        buttonName.width = Settings.buttonWidth / 2;
        buttonName.height = Settings.buttonHeight / 2;
    }

    /**
     * UPDATERS
     * - updateScore(int score)
     * - updateLevel (int level)
     * - levelChangingMusic()
     */

    public static void updateScore(int score) {
        scoreCount = score;
    }

    public static void updateLevel(int level) {
        levelCount = level;
        levelChangingMusic();
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
     * METHODS FOR BUTTONS
     * - back2Main()
     * - restart()
     */

    private static void back2Main() {
        //gt.interrupt();
        Canvas.stopBackgroundMusic();
        switchToScreen(new Main());
    }

    private static void restart() {
        scoreCount = 0;
        //gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();
        gd = new GridDraw();
        //gt = new GameThread(gd);
        //gt.start();
        startAudioGame();
    }

    /**
     * DRAWING
     * - draw()
     * - gridDrawMethodCalling()
     */
    private void draw() {
        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        /*
        SaxionApp.drawText("Score: " + scoreCount, (Settings.width / 4 - Settings.width / 12), Settings.height / 2, 20);
        SaxionApp.drawText("Level: " + levelCount, (Settings.width / 4 - Settings.width / 12), Settings.height - Settings.height / 4, 20);

        if (gd != null) {
            gridDrawMethodCalling();
        }
        */
    }

    /*private static void gridDrawMethodCalling() {
        gd.drawGrid();
        gd.drawNextPieceGrid();
        gd.drawBackground();
        gd.repaint();
    }*/
}