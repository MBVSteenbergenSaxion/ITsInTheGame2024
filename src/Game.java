import Grid.Grid;
import utils.*;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Game extends Canvas{

    public Game() {
        super();
    }


    @Override
    public void init() {


    }


    public void loop() {
        draw();

    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {


    }

    private void draw(){
        GridDraw gridDraw = new GridDraw();
        gridDraw.drawGrid();

    }

}