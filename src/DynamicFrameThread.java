import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DynamicFrameThread extends Thread{

    public static void main(String[] args) {
        Frame[] frames = Frame.getFrames();
        frames[0].addComponentListener(new ComponentAdapter() {
        Canvas previousCanvas = Canvas.activeCanvas;

            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Settings.width = frames[0].getWidth();
                Settings.height = frames[0].getHeight();
                Canvas.switchToScreen(new Main());
                Canvas.switchToScreen(previousCanvas);

            }

        });
    }

    @Override
    public void run() {
        while(true) {
            Frame[] frames = Frame.getFrames();
            Settings.width = frames[0].getWidth();
            Settings.height = frames[0].getHeight();
        }
    }
}
