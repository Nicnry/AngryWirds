package ch.cpnv.angrywirds.Models.Stage;

import com.badlogic.gdx.math.Vector2;

/**
 * @author
 * Nicolas Henry  */
public class Poo extends MovingObject {

    public boolean isPooped = false;

    private static final String PICNAME = "bird.png";
    public static final Vector2 SPEED = new Vector2(0,-250);
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    //Not for this work, but you can have a second bird
    public Poo(Vector2 position) {
        super(position, WIDTH, HEIGHT, PICNAME, SPEED);
    }

    @Override
    public void accelerate(float dt) {

    }

    public void reset() {
        sprite.setX(0);
        sprite.setY(0);
        isPooped = false;
    }
}
