package SideDraw;

import nl.saxion.app.SaxionApp;

public class SideSettings {
    private static int screenHeight = SaxionApp.getHeight();
    private static int screenWidth = SaxionApp.getWidth();
    public static int nextPieceWidth = 4;
    public static int nextPieceHeight = 4;

    public static int getOneFiftiethOfScreenWidth() {
        return screenWidth / 50;
    }

    public static int getOneFiftiethOfScreenHeight() {
        return screenHeight / 50;
    }

    public static int getStartNextPanelX() {
        return getOneFiftiethOfScreenWidth() * 30;
    }

    public static int getEndNextPanelX() {
        return getOneFiftiethOfScreenWidth() * 38;
    }

    public static int getWidthNextPanel() {
        return getEndNextPanelX() - getStartNextPanelX();
    }

    public static int getBlockNextSize() {
        return getWidthNextPanel() / nextPieceWidth;
    }

    public static int getStartNextPanelY() {
        return getOneFiftiethOfScreenHeight();
    }

    public static int getEndNextPanelY() {
        return getStartNextPanelY() + getBlockNextSize() * nextPieceHeight;
    }

    public static int getShadowNextSize() {
        return getBlockNextSize() / 9;
    }

    public static void updateScreenDimensions(int newWidth, int newHeight){
        screenWidth = newWidth;
        screenHeight = newHeight;
    }

    public static int getSideButtonHeight(){
        return (int) (getOneFiftiethOfScreenHeight() * 3.5);
    }

    public static int getSideButtonWidth(){
        return getWidthNextPanel();
    }

    public static int getRestartButtonY() {
        return getEndNextPanelY() + getOneFiftiethOfScreenHeight() * 2;
    }

    public static int getQuitButtonY() {
        return getOneFiftiethOfScreenHeight() * 5 + getRestartButtonY();
    }

    public static int getScoreY() {
        return getQuitButtonY() + getOneFiftiethOfScreenHeight() * 5;
    }

    public static int getLevelY() {
        return getScoreY() + getOneFiftiethOfScreenHeight() * 3;
    }



    public static int getFontSize() {
        return (int) (getOneFiftiethOfScreenWidth() * 0.75);
    }

}
