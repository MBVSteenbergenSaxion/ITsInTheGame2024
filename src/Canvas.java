import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Canvas implements GameLoop {
    private static Canvas activeCanvas;

    public Canvas() {
    }

    public static void main(String[] args){
        Canvas mainApp = new Canvas();
        activeCanvas = new Main();

        SaxionApp.startGameLoop(mainApp, Settings.width, Settings.height, Settings.ms);

    }


    public void switchToScreen(Canvas newScreen) {
        SaxionApp.clear();

        activeCanvas = newScreen;
        activeCanvas.init();
        activeCanvas.loop();

    }

    @Override
    public void init() {
        if (activeCanvas != null) {
            activeCanvas.init();
        }
    }

    @Override
    public void loop() {
        if (activeCanvas != null) {
            activeCanvas.loop();
        }
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (activeCanvas != null) {
            activeCanvas.keyboardEvent(keyboardEvent);
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
        if (activeCanvas != null) {
            activeCanvas.mouseEvent(mouseEvent);
        }
    }
}
