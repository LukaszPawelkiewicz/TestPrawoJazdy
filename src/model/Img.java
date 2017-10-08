package model;

import javafx.scene.image.Image;

public class Img {

    private static Img instance;
    private Image img;

    private Img() {

    }

    public static Img getInstance() {
        if (instance == null) {
            instance = new Img();
        }
        return instance;
    }

    public static void loadImage(String path) {
        getInstance().img = new Image(path);
    }

    public Image getImg() {
        return img;
    }

}
