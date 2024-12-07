import Grid.Shape;
import Shapes.IShape;
import nl.saxion.app.SaxionApp;
import utils.*;
import nl.saxion.app.interaction.*;

public class Game extends Canvas {


    public static GridDraw gd;
    public static GameThread gt;

    utils.MyButton restartButton = new MyButton();
    utils.MyButton quitButton = new MyButton();

    private static final String[] tetrisLevelUpAudio = {
            ("resources/GameMusic/Theme(SelfMade)/Theme2.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme3.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme4.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme5.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme6.wav"),
            ("resources/GameMusic/Theme(SelfMade)/Theme7.wav")};

    public Game() {
        super();

        gd = new GridDraw();
        gt = new GameThread(gd);
    }

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

            if (utils.Utility.checkBounds(x, y, quitButton.x, quitButton.y, quitButton.width, quitButton.height, true)) {
                back2Main();
            }

            if (utils.Utility.checkBounds(x, y, restartButton.x, restartButton.y, restartButton.width, restartButton.height, true)) {
                restart();
            }
        }
    }


    public void startGame() {
        gt.start();
    }



    public static void startAudioGame() {
        Canvas.playBackgroundMusic(tetrisLevelUpAudio[0]);
    }

    public static void buttonInitialization(MyButton buttonName, int buttonY) {
        buttonName.x = Settings.width - Settings.width / 4;
        buttonName.y = Settings.height / buttonY;
        buttonName.width = Settings.buttonWidth / 2;
        buttonName.height = Settings.buttonHeight / 2;
    }

    private static void back2Main() {
        //gt.interrupt();
        Canvas.stopBackgroundMusic();
        switchToScreen(new Main());
    }

    private void restart() {
        //gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();
        //gt = new GameThread(gd);
        //gt.start();
        startAudioGame();
    }

    private void draw() {
        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        if (gd != null) {
            gridDrawMethodCalling();
        }

    }

    private static void gridDrawMethodCalling() {
        gd.paint();
    }

}