import Grid.GridSettings;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Game extends Canvas{

    public static GridDraw gd;
    public static GameThread gt;
    private boolean upKeyPressed, rightKeyPressed, leftKeyPressed;
    public static int scoreCount;
    public static int levelCount = 1;

    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    private static final String[] tetrisLevelUpAudio = {("resources/GameMusic/Theme(SelfMade)/Theme2.wav"),
                                                        ("resources/GameMusic/Theme(SelfMade)/Theme3.wav"),
                                                        ("resources/GameMusic/Theme(SelfMade)/Theme4.wav"),
                                                        ("resources/GameMusic/Theme(SelfMade)/Theme5.wav"),
                                                        ("resources/GameMusic/Theme(SelfMade)/Theme6.wav")};

    public Game() {
        super();

        gd = new GridDraw();
        gt = new GameThread(gd);

        upKeyPressed = false;
    }

    @Override
    public void init() {
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);

        startGame();
        scoreCount = 0;

        buttonInitialization(restartButton, 3);
        buttonInitialization(quitButton, 2);
    }

    public static void buttonInitialization(MyButton buttonName, int buttonY) {
        buttonName.x = Settings.width - Settings.width / 4;
        buttonName.y = Settings.height / buttonY;
        buttonName.width = Settings.buttonWidth / 2;
        buttonName.height = Settings.buttonHeight / 2;
    }

    public void loop() {
        draw();
    }

    public void startGame() {
        gt.start();
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            //Handles key pressed
            switch (keyboardEvent.getKeyCode()) {
                case 39, 68:
                    if (!rightKeyPressed) {
                        gd.moveBlockRight();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        rightKeyPressed = true;
                    }
                    break;
                case 37, 65:
                    if (!leftKeyPressed) {
                        gd.moveBlockLeft();
                        SaxionApp.playSound("resources/gameSounds/movement.wav");
                        leftKeyPressed = true;
                    }
                    break;
                case 40, 83:
                    gd.dropBlock();
                    break;
                case 38, 87:
                    if (!upKeyPressed) {
                        gd.rotateBlock();
                        SaxionApp.playSound("resources/gameSounds/rotation.wav");
                        upKeyPressed = true;
                    }
                    break;
            }
        } else {
            // Handle key releases
            switch (keyboardEvent.getKeyCode()) {
                case 38, 87: upKeyPressed = false; //ArrowUp or W
                case 39, 68: rightKeyPressed = false; //ArrowRight or D
                case 37, 65: leftKeyPressed = false; //ArrowLeft or A

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
                back2Main();
            }

            if (utils.Utility.checkBounds(x, y,
                    restartButton.x, restartButton.y, restartButton.width,
                    restartButton.height, true)) {
                restart();
            }
        }
    }

    private void draw(){
        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        SaxionApp.drawText("Highscore: " + scoreCount, (Settings.width / 4 - Settings.width / 12), Settings.height / 2, 20);
        SaxionApp.drawText("Level: " + levelCount, (Settings.width / 4 - Settings.width / 12), Settings.height - Settings.height / 4, 20);

        if(gd != null){
            gridDrawMethodCalling();
        }

    }

    private static void back2Main() {
        gt.interrupt();
        Canvas.stopBackgroundMusic();
        switchToScreen(new Main());
    }

    private static void restart() {
        scoreCount = 0;
        gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();
        gd = new GridDraw();
        gt = new GameThread(gd);
        gt.start();
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);
    }

    public static void updateScore(int score) {
        scoreCount = score;
    }

    public static void updateLevel(int level) {
        levelCount = level;
        levelChangingMusic();
    }

    public static void levelChangingMusic() {
        for (int i = 0; i < tetrisLevelUpAudio.length; i++) {
            if (levelCount == i) {
                Canvas.stopBackgroundMusic();
                Canvas.playBackgroundMusic(tetrisLevelUpAudio[i - 1]);
            }
        }
    }

    private static void gridDrawMethodCalling() {
        gd.drawGrid();
        gd.drawNextPieceGrid();
        gd.drawBackground();
        gd.repaint();
    }
}