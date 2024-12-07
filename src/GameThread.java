import Grid.Shape;
import Shapes.IShape;
import nl.saxion.app.SaxionApp;

public class GameThread extends Thread {

    private static int SHAPE_COUNT;
    private static Shape[] shapes;

    private Shape currentShape;
    private int currentColumn;
    private int currentRow;
    private int currentRotation;


    private static GridDraw gridDraw;
    public static int gameSpeed;

    public GameThread(GridDraw gridDraw) {
        shapes = new Shape[] {
                new IShape()
        };

        SHAPE_COUNT = shapes.length;

        GameThread.gridDraw = gridDraw;

        gameSpeed = 1000;
    }


    public static void main(String[] args) {

    }


    @Override
    public void run() {

        while (true) {
            update();
            try {
                Thread.sleep(gameSpeed);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void update() {
        gridDraw.addPiece(currentShape, currentColumn, currentRow, currentRotation);
        spawnShape();
    }

    private void spawnShape() {
        if (shapes.length > 0) {
            this.currentShape = shapes[0];  // Zorg ervoor dat de shape niet null is
            this.currentColumn = currentShape.getSpawnColumn();
            this.currentRow = currentShape.getSpawnRow();
            this.currentRotation = 0;
        } else {
            System.err.println("No shapes available to spawn.");
        }
    }


    public Shape getCurrentShape() {
        return currentShape;
    }

    public int getShapeColumns() {
        return currentColumn;
    }

    public int getShapeRows() {
        return currentRow;
    }

    public int getCurrentRotation() {
        return currentRotation;
    }
}


