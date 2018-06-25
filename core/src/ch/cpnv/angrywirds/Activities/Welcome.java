package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Stage.Title;
import ch.cpnv.angrywirds.Providers.VocProvider;

/**
 * @author
 * Nicolas Henry  */
public class Welcome extends GameActivity implements InputProcessor{
    private Texture background;
    private Title title;
    private float splashTime = 2;
    private Sprite wheel1;
    private Sprite wheel2;
    private Sprite play;
    private boolean showWeehls = true;

    public Welcome()
    {
        super();
        background = new Texture(Gdx.files.internal("background.png"));
        title = new Title("Angry Wirds");
        wheel1 = new Sprite(new Texture("cog.png"));
        wheel2 = new Sprite(new Texture("cog.png"));
        play = new Sprite(new Texture("play.png"));
        wheel1.setBounds(camera.viewportWidth/2 - 45, camera.viewportHeight/4,100,100);
        wheel2.setBounds(camera.viewportWidth/2 + 45, camera.viewportHeight/4,100,100);
        play.setBounds(camera.viewportWidth/2-100, (camera.viewportHeight/4)-50,250,150);
        wheel1.setOrigin(50,50);
        wheel2.setOrigin(50,50);
        play.setOrigin(50,50);
        wheel2.setRotation(15);
        Gdx.input.setInputProcessor(this);
        VocProvider.load();
    }

    @Override
    protected void handleInput() {
    }

    @Override
    public void update(float dt) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        wheel1.rotate(1);
        wheel2.rotate(-1);
        if (splashTime > 0) {
            splashTime -= dt;
        }
        else {
            if (VocProvider.status == VocProvider.Status.ready) {
                showWeehls = false;
                //AngryWirds.gameActivityManager.push(new Play(1));
            }
        }
        spriteBatch.end();
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(spriteBatch);
        if (showWeehls) {
            wheel1.draw(spriteBatch);
            wheel2.draw(spriteBatch);
        }else{
            play.draw(spriteBatch);
        }
        if (Gdx.input.getInputProcessor() != this) {
            Gdx.input.setInputProcessor(this);
        }

        spriteBatch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 pointTouched = camera.unproject(new Vector3(screenX, screenY, 0));
        if (play.getBoundingRectangle().contains(new Vector2(pointTouched.x,pointTouched.y))) {
            AngryWirds.gameActivityManager.push(new LevelChoice()); //Play()
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
