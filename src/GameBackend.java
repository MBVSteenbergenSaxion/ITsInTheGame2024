import Grid.Block;
import Grid.GridSettings;
import Shapes.*;
import nl.saxion.app.SaxionApp;

import java.awt.*;

public class GameBackend {

    public static GameThread gt;
    public static GridDraw gd;
    public static SideDraw sd;

    private static Block[] blocks;
    private static Color[] colors;

    public static int randomBlock, currentBlockId, setRotation, randomColorValue;
    public static Block nextblock;
    public static Block currentblock;

    public GameBackend() {

        gd = new GridDraw(this);
        sd = new SideDraw(this);
        gt = new GameThread(gd);


        blocks = new Block[]{
                new LShape(),
                new IShape(),
                new JShape(),
                new OShape(),
                new SShape(),
                new ZShape(),
                new TShape()
        };

        colors = new Color[]{
                SaxionApp.createColor(4, 83, 255), //Blue
                SaxionApp.createColor(253, 103, 1), //Orange
                SaxionApp.createColor(254, 255, 6), //Yellow
                SaxionApp.createColor(0, 255, 6), //Green
                SaxionApp.createColor(254, 4, 253), //Pink
                SaxionApp.createColor(255, 17, 4), //Red
                SaxionApp.createColor(5, 239, 253) //Cyan
        };
    }

    public static void back2Main() {
        gt.interrupt();
        Canvas.stopBackgroundMusic();
        Game.switchScreen2Main();
    }

    public static void startGame() {
        Game.scoreCount = 0;
        gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();
        GameBackend gb = new GameBackend();
        gd = new GridDraw(gb);
        sd = new SideDraw(gb);
        gt = new GameThread(gd);
        gt.start();
        Game.startAudioGame();
    }


    public static void spawnBlock() {
        setCurrentBlock();
        currentblock.spawn(setRotation);
        nextblock.nextSpawn();
    }

    public static void setCurrentBlock() {
        spawnRotationCheck();

        currentblock = nextblock;
        currentBlockId = randomBlock;

        setNextPieceCheck();
    }

    public static void setNextPiece() {
        randomBlock = SaxionApp.getRandomValueBetween(0, blocks.length);
        randomColorValue = SaxionApp.getRandomValueBetween(0, colors.length);

        nextblock = blocks[randomBlock];
        nextblock.color = colors[randomColorValue];
    }


    public static boolean moveBlockDown() {
        if (!gd.checkBottom()) {
            return false;
        }

        currentblock.moveDown();
        gd.repaint();
        return true;
    }

    public void rightMovement() {
        if (currentblock == null) {
            return;
        }

        if (!gd.checkRight()) {
            return;
        }

        currentblock.moveRight();
        gd.repaint();
    }

    public void leftMovement() {
        if (currentblock == null) {
            return;
        }

        if (!gd.checkLeft()) {
            return;
        }

        currentblock.moveLeft();
        gd.repaint();
    }

    public void dropBlock() {
        if (currentblock == null) {
            return;
        }

        while (gd.checkBottom()) {
            currentblock.moveDown();
        }

        gd.repaint();
    }

    public void rotate() {
        if (currentblock == null) {
            return;
        }

        currentblock.rotate();

        if (currentblock.getLeftEdge() < 0) {
            currentblock.setX(0);
        }

        if (currentblock.getRightEdge() >= GridSettings.width) {
            currentblock.setX(GridSettings.width - currentblock.getWidth());
        }

        if (currentblock.getBottomEdge() >= GridSettings.height) {
            currentblock.setY(GridSettings.height - currentblock.getHeight());
        }

        gd.repaint();
    }


    public static void spawnRotationCheck() {
        if (randomBlock == 1) {
            setRotation = 1;
        } else {
            setRotation = SaxionApp.getRandomValueBetween(0, 4);
        }
    }

    public static void setNextPieceCheck() {
        setNextPiece();
        if (randomBlock == currentBlockId) {
            setNextPiece();
        }
    }

    public void checkToPaint(int score, int level) {
        if (gd != null) {
            paintOnCanvasFromGD(score, level);
        }
    }

    private void paintOnCanvasFromGD(int score, int level) {
        gd.paint();
        sd.paint();
        sd.drawScore(score, level);
        sd.drawText();
    }

    public Block getCurrentblock() {
        return currentblock;
    }

    public Block getNextblock() {
        return nextblock;
    }
    public static void main(String[] args) {

    }
}
