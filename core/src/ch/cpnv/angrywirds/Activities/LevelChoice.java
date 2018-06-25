package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Data.Vocabulary;
import ch.cpnv.angrywirds.Models.Stage.LevelButton;
import ch.cpnv.angrywirds.Providers.VocProvider;

/**
 * @author
 * Nicolas Henry  */
public class LevelChoice extends GameActivity implements InputProcessor{

    private String MESSAGE = "Choisissez votre niveau";
    private int POSX = 0;

    private Texture background;
    private ArrayList<LevelButton> levelButtons = new ArrayList<LevelButton>();
    private BitmapFont font;

    public LevelChoice() {
        super();
        background = new Texture(Gdx.files.internal("background.png"));

        font= new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(5);


        //Display all button for the game
        for ( Vocabulary vocabulary : VocProvider.vocabularies) {

            //Get the online result for "result"
            levelButtons.add(new LevelButton(((WORLD_WIDTH)/2)-150,WORLD_HEIGHT-160-POSX,vocabulary.getVocName(), vocabulary.getId(), vocabulary.getResult()));
            POSX += 150;
        }
        Gdx.input.setInputProcessor(this);
    }

    public void draw(Batch batch)
    {
        font.draw(batch, MESSAGE, 200, 200);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        for (LevelButton button: levelButtons) {
            button.draw(spriteBatch);
            button.getResult();
        }
        //Show MESSAGE
        this.draw(spriteBatch);
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 pointTouched = camera.unproject(new Vector3(screenX, screenY, 0));
        for (LevelButton levelButton: levelButtons) {
            if (levelButton.getSprite().getBoundingRectangle().contains(new Vector2(pointTouched.x,pointTouched.y))) {
                AngryWirds.gameActivityManager.push(new Play(levelButton.getLvlid())); //Play()
            }

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
