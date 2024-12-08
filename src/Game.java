import Grid.Shape;
import Shapes.IShape;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.*;

public class Game extends Canvas {

    public static GridDraw gd;
    public static GameThread gt;
    //public static GameThread gt;
    private boolean upKeyPressed, rightKeyPressed, leftKeyPressed;
    public static int scoreCount;
    public static int levelCount = 1;

    private static int SHAPE_COUNT;
    private static Shape[] shapes;

    private Shape currentShape;
    private int currentCol, currentRow, currentRotation;

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

        shapes= new Shape[] {
                new IShape()
        };
        SHAPE_COUNT = shapes.length;


        gd = new GridDraw(this);
        gt = new GameThread(this);
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
        gt.start();
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

    public void restart() {
        //scoreCount = 0;

        /*gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();

        gd = new GridDraw(this);
        gt = new GameThread(this);
        gt.start();
        startAudioGame();
        */
        this.currentShape = shapes[SaxionApp.getRandomValueBetween(0 , SHAPE_COUNT)];
        spawnShape();
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
        */

        if (gd != null) {
            gridDrawMethodCalling();
        }

    }

    private static void gridDrawMethodCalling() {
        gd.paint();
    }


    /**
     * Updaters
     *  - updateGame()
     *  - renderGame()
     *  - spawnShape()
     * */

    public void spawnShape() {
        this.currentShape = shapes[SaxionApp.getRandomValueBetween(0 , SHAPE_COUNT)];
        this.currentCol = currentShape.getSpawnColumn();
        this.currentRow = currentShape.getSpawnRow();
        this.currentRotation = 0;

        if(!gd.isPossibleAndEmpty(currentShape, currentCol, currentRow, currentRotation)) {
            System.out.println("GameOver");
        }
    }

    public void update() {
        if (gd.isPossibleAndEmpty(currentShape, currentCol, currentRow + 1, currentRotation)) {
            currentRow++;
        } else {
            spawnShape();
        }
    }

    /**
     * Getters
     * - getCurrentShape()
     * - getShapeColumn()
     * - getShapeRow()
     * - getShapeRotation()
     * */

    public Shape getCurrentShape() {
        return currentShape;
    }

    public int getShapeColumn() {
        return currentCol;
    }

    public int getShapeRow() {
        return currentRow;
    }

    public int getShapeRotation() {
        return currentRotation;
    }

}