package my.utils;

import com.sun.tools.javac.Main;
import nl.saxion.app.SaxionApp;
import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

public class Canvas implements GameLoop{

    public boolean loopStopped;

    public Canvas() {
    }

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new Canvas(), Settings.width, Settings.height, Settings.ms);

    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {

    }

}
