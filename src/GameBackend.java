/** Imports the java.awt and SaxionApp library
 *  Imports the needed classes from the Grid and Shapes packages.
 * */
import nl.saxion.app.SaxionApp;
import java.awt.*;
import Grid.Block;
import Grid.GridSettings;
import Shapes.*;

/** GameBackend
 * Gets every variable needed to make game to function or calls methods from other classes or calls a new ClassObject
 * It makes the Game class function
 * */
public class GameBackend {

    /** Calling GameThread as gt for easiness of calling later in this file
     * */
    public static GameThread gt;

    /** Calling GridDraw as gd for easiness of calling later in this file
     * */
    public static GridDraw gd;

    /** Calling SideDraw as sd for easiness of calling later in this file
     * */
    public static SideDraw sd;

    /** Makes a new array which can hold new Block class objects, the block class holds every variable that comes with a block
     * The array gets its blocks in the constructor method.
     * */
    private static Block[] blocks;

    /** Makes a new array which can hold an array of colors,
     * The array get its colors in the constructor method
     * */
    private static Color[] colors;

    /** Holds all the variables of the next block from the Block class and is set in a method further down in this class
     * */
    public static Block nextblock;

    /** Has every variable that is needed besides the next block itself, it has its ID and its color value.
     * These are set in a method further down in this class.
     * */
    public static int nextBlockId, randomColorValue;


    /** Holds all the variables of the current block from the Block class and is set in a method further down in this class
     * */
    public static Block currentblock;

    /** Has every variable that is needed besides the current block itself, it has its ID and its start rotation
     * These are set in a method further down in this class.
     * */
    public static int currentBlockId, startRotation;

    /** Private integers to print the score and the level, which gets updated with the GridDraw class and GameThread class
     * */
    public static int scoreCount, levelCount;

    /** GameBackend()
     *  - The before name classes are started in this constructor, and they are give the classes which are needed to make these classes function
     *  - The blocks array is made, with all the shapes twice, this is because a bug fix that the next block isn't falling also when it has the same arrayID as the current block
     *  - The colors array is made with all the possible colors, which are made from the original Tetris shapes colors.
     * */
    public GameBackend() {

        gd = new GridDraw(this);
        sd = new SideDraw(this);
        gt = new GameThread(gd);


        blocks = new Block[]{
                new LShape(), //0
                new IShape(), //1
                new JShape(), //2
                new OShape(), //3
                new SShape(), //4
                new ZShape(), //5
                new TShape(), //6
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

    /** startGame()
     * Firstly sets the level and score back to its default
     * Makes sure that also when the game restarts the Thread first stops
     * Clears the screen with SaxionApp
     * And stops the music
     * -------------------
     * A new Constructor from this class is made
     * It makes sure that the classes we call are null and not still another initialization
     * And makes these classes again
     * And after all that it starts the thread to make the game work, together with the audio
     * */
    public static void startGame() {

        scoreCount = 0;
        levelCount = 1;
        gt.interrupt();
        SaxionApp.clear();
        Canvas.stopBackgroundMusic();

        GameBackend gb = new GameBackend();

        gd = null;
        sd = null;
        gt = null;

        gd = new GridDraw(gb);
        sd = new SideDraw(gb);
        gt = new GameThread(gd);
        gt.start();

        Game.startAudioGame();
    }

    /** spawnBlock()
     * Calls the setCurrentBlock method, which will explained later in this Class
     * It calls the spawn method in the Block class as the current block with its start rotation
     * It also spawns the next block which is already set within another method which will be explained later in this class
     * */
    public static void spawnBlock() {
        setCurrentBlock();
        currentblock.spawn();
        nextblock.nextSpawn();
    }

    /** setCurrentBlock()
     * The currenBlockID is set as the same block but as the nextBlockId - 7, for the bug fix where the nextBlockID isn't the same as the currentBlockID
     * The current block is set from the blocks array with the currentBlockID
     * The current block color is set to the randomColorValue which still isn't updated because the next piece isn't set yet
     * It checks if the start rotation needs to be fixed, this is explained in its method (spawnRotationCheck())
     * It sets the next piece
     * */
    public static void setCurrentBlock() {
        currentBlockId = nextBlockId - 7;
        currentblock = blocks[currentBlockId];
        currentblock.color = colors[randomColorValue];

        spawnRotationCheck();
        setNextPiece();
    }

    /** setNextPiece()
     * Picks firstly a random integer between 7 and the rest of the blocks array length
     * Picks a random integer between 0 and the colors array length
     * Sets the next block which gets picked out with the next block id from the blocks array
     * Sets the next block color which is assigned with the colors array with the random color value
     * */
    public static void setNextPiece() {
        nextBlockId = SaxionApp.getRandomValueBetween(7, blocks.length);
        randomColorValue = SaxionApp.getRandomValueBetween(0, colors.length);

        nextblock = blocks[nextBlockId];
        nextblock.color = colors[randomColorValue];
    }

    /** Getter that return the current block, that can be used by other classes
     * */
    public Block getCurrentblock() {
        return currentblock;
    }

    /** Getter that return the next block, that can be used by other classes
     * */
    public Block getNextblock() {
        return nextblock;
    }

    /** rightMovement()
     * If the current block isn't set, then return
     * If the block cannot move to the right, then return
     * Otherwise move the block to the right, method called from the Block class
     * */
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

    /** leftMovement()
     * If the current block isn't set, then return
     * If the block cannot move to the left, then return
     * Otherwise move the block to the left, method called from the Block class
     * */
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

    /** moveBlockDown()
     * Returns a boolean with always true unless the check that is called from GridDraw returns false, then this boolean returns also false
     * If the check doesn't return false, then the current block moves down
     * And it repaints the block
     * */
    public static boolean moveBlockDown() {
        if (!gd.checkBottom()) {
            return false;
        }

        currentblock.moveDown();
        gd.repaint();
        return true;
    }

    /** dropBlock()
     * If the current block isn't set, then return
     * As long the check bottom method from the GridDraw class doesn't return false, the block moves down
     * */
    public void dropBlock() {
        if (currentblock == null) {
            return;
        }

        while (gd.checkBottom()) {
            currentblock.moveDown();
        }

        gd.repaint();
    }

    /** rotate()
     * If the current block isn't set, then return
     * Call the rotate method from the block class, as the current block
     * If the current block most left block is smaller than 0  because it is rotated then set the first x to the new x
     * If the current block most right block is bigger or equals to the grid width because it is rotated then set the first x to the new x
     * If the current block most bottom block is bigger or equals to the grid height because it is rotated then set the first Y to the new Y
     * */
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

    /** spawnRotationCheck()
     * If the current block is the shape 'I', then it needs to be spawned vertical.
     * Otherwise, there is a change that is spawned outside the grid.
     * If the current block isn't the shape 'I', then it is a random value ranging from 0 to 3
     * */
    public static void spawnRotationCheck() {
        if (currentBlockId == 1) {
            startRotation = 1;
        } else {
            startRotation = SaxionApp.getRandomValueBetween(0, 4);
        }
    }

    /** checkToPaint()
     * If the GridDraw is not null, then use the method paintOnCavas
     * */
    public void checkToPaint() {
        if (gd != null) {
            paintOnCanvasFromGD();
        }
    }

    /** paintOnCanvasFromGD()
     * Use the GridDraw paint method
     * Use the SideDraw paint method
     * Use the SideDraw drawScore method with the score and level
     * Use the SideDraw drawText method
     * */
    private void paintOnCanvasFromGD() {
        gd.paint();
        sd.paint();
        sd.drawScore(scoreCount, levelCount);
        sd.drawImage();
    }

    /** back2Main()
     * Interrupts the gamethread, so that it can be called as new when a new game starts
     * The music is stopped so that a new background music can be played
     * After the operations above are done, the application switches to the Menu screen
     * */
    public static void back2Main() {
        gt.interrupt();
        Canvas.stopBackgroundMusic();
        Canvas.switchToScreen(new Main()
        );
    }

    /** updateLevel() gets updated by the GameThread class
     * levelCount gets updated
     * levelChangingMusic is called
     * */
    public static void updateLevel(int level) {
        levelCount = level;
        Game.levelChangingMusic(levelCount);
    }
}