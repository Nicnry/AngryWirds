package ch.cpnv.angrywirds.Activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Models.Stage.ScoreBoard;
import ch.cpnv.angrywirds.Models.Stage.Title;
import ch.cpnv.angrywirds.Providers.VocProvider;

/**
 * @author
 * Nicolas Henry  */
public class GameOver extends GameActivity {

    private Texture background;
    public Title title;

    public GameOver(Title title)
    {
        super();
        background = new Texture(Gdx.files.internal("background.png"));

        this.title = title;
        VocProvider.submitResults(34, ScoreBoard.score,"*EFF7485DF3BCDAFA547DD4DAF868450C3FEC383F");
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            AngryWirds.gameActivityManager.pop(); // game over
            AngryWirds.gameActivityManager.pop(); // play
            AngryWirds.gameActivityManager.pop(); // Menu
            AngryWirds.gameActivityManager.pop(); // idk
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(spriteBatch);
        spriteBatch.end();
    }
}
