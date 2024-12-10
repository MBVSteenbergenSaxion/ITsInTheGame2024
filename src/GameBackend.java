import nl.saxion.app.SaxionApp;

public class GameBackend {

    public static GameThread gt;
    public static GridDraw gd;

    public GameBackend() {

        gd = new GridDraw();
        gt = new GameThread(gd);
    }

    public void startGame() {
        Game.startAudioGame();
        gt.start();
        Game.scoreCount = 0;
    }

    public static void back2Main() {
        gt.interrupt();
        Canvas.stopBackgroundMusic();
    }

    public static void restart() {
        Game.scoreCount = 0;
        gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();
        gd = new GridDraw();
        gt = new GameThread(gd);
        gt.start();
        Game.startAudioGame();
    }

    public void rightMovement() {
        gd.moveBlockRight();
    }

    public void leftMovement() {
        gd.moveBlockLeft();
    }

    public void dropBlock() {
        gd.dropBlock();
    }

    public void rotate() {
        gd.rotateBlock();
    }

    public void checkToPaint() {
        if (gd != null) {
            paintOnCanvasFromGD();
        }
    }

    private  void paintOnCanvasFromGD() {
        gd.paint();
        gd.drawNextPieceGrid();
        gd.repaint();
    }

    public static void main(String[] args) {

    }
}
