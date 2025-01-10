import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;
import SideDraw.*;

import java.awt.*;

/**
 * GAME CLASS
 * New scene from the canvas class where every method that has to do with drawing, sounds, buttons, key pressing or mouse pushes
 */
public class Game extends Canvas {

    /**
     * Calling gamebackend as gb for easiness of calling later in this file
     */
    public static GameBackend gb;

    /**
     * Private boolean values to check if a key is already pressed or not
     */
    private boolean upKeyPressed, rightKeyPressed, leftKeyPressed;

    /**
     * Making two new buttons (Restart and Quit), making a new MyButton() from our utils package
     */
    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    /**
     * A String array with all the path names for the used songs while leveling up in the game itself.
     */
    private static final String[] tetrisLevelUpAudio = {
            ("resources/GameMusic/Theme(SelfMade)/Theme2.wav"), //0
            ("resources/GameMusic/Theme(SelfMade)/Theme3.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme4.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme5.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme6.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme7.wav")
    };

    /**
     * Default constructor for this class
     * Has a super call for the class that it extends (Canvas)
     * Sets the called GameBackend variable to a new called GameBackend() class
     */
    public Game() {
        super();
        gb = new GameBackend();
    }

    /**
     * Method init
     * Calls te startGame() from GameBackend so that the game starts
     * Makes the buttons for on the GameScene
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

    /**
     * Loop()
     * Every gameloop the draw method is called.
     */
    public void loop() {
        draw();
    }

    /**
     * KeyBoardEvent(), combines a key of the keyboard to an action in the game
     * If the right-arrow-key or the letter 'D' is pressed then the block moves one square to the right, it sets the pressed boolean to true and sets it immediately back to false by the gameloop, this is made so you can press it only once per movement.
     * If the left-arrow-key or the letter 'A' is pressed then the block moves one square to the left, it sets the pressed boolean to true and sets it immediately back to false by the gameloop, this is made so you can press it only once per movement.
     * If the up-arrow-key or the letter 'W' is pressed then the block rotates once, it sets the pressed boolean to true and sets it immediately back to false by the gameloop, this is made so you can press it only once per movement.
     * If the down-arrow-key or the letter 'S' is pressed then the block drops immediately down.
     * For every movement left, right or a rotation the game makes a sound
     */
    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.isKeyPressed()) {
            //Handles key pressed
            switch (keyboardEvent.getKeyCode()) {
                case 39, 68: //ArrowRight or D
                {
                    gb.rightMovement();
                    SaxionApp.playSound("resources/gameSounds/movement.wav");
                    rightKeyPressed = true;
                }
                break;
                case 37, 65: //ArrowLeft or A
                {
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
     * MouseEvent()
     * A integer of x and y are made, if the left-mouse-button is clicked then it sets the x and y to that current position.
     * If the x and y are in the box of the Quit or Restart button then one of these button is clicked and does something in GameBackend.
     */
    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        int x, y;

        if (mouseEvent.isLeftMouseButton()) {

            x = mouseEvent.getX();
            y = mouseEvent.getY();

            if (utils.Utility.checkBounds(x, y,
                    quitButton.x, quitButton.y, quitButton.width, quitButton.height, true)) {
                GameThread.resetScore();
                GameBackend.back2Main();
            }

            if (utils.Utility.checkBounds(x, y,
                    restartButton.x, restartButton.y, restartButton.width,
                    restartButton.height, true)) {
                GameThread.resetScore();
                GameBackend.startGame();
            }
        }
    }

    /**
     * startAudioGame()
     * Sets the start audio for the game as the first path of the possible audio's
     */
    public static void startAudioGame() {
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);
    }

    /**
     * LevelChangingMusic() changes the music based on the level,
     * It starts counting from 1 and checks with the level count and if the level is up then it equals to the next counter value,
     * which updates the String array of the gameaudio to the next song.
     */
    public static void levelChangingMusic(int levelCount) {
        for (int i = 1; i <= tetrisLevelUpAudio.length; i++) {
            if (levelCount == i) {
                Canvas.stopBackgroundMusic();
                Canvas.playBackgroundMusic(tetrisLevelUpAudio[i - 1]);
            }
        }
    }

    /**
     * draw()
     * Draws the game screen components. This method performs the following actions:
     * - Draws the restart and quit buttons with their specified properties.
     * - Calls the GameBackend checks to paint method which draws everything further in that class.
     */
    private void draw() {
        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, SideSettings.getFontSize(), "Restart Game", Color.ORANGE);
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, SideSettings.getFontSize(), "Back to Menu", Color.RED);

        gb.checkToPaint();
    }
}