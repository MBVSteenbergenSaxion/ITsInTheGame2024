import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Game extends Canvas{

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

    private void draw(){

        MyButton.drawButton(restartButton.x, restartButton.y, restartButton.width, restartButton.height, Settings.fontSize / 2, "Restart Game");
        MyButton.drawButton(quitButton.x, quitButton.y, quitButton.width, quitButton.height, Settings.fontSize / 2, "Back to Menu");

        GridDraw gridDraw = new GridDraw();
        gridDraw.drawGrid();

    }

}