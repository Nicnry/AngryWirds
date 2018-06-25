package ch.cpnv.angrywirds.Models.Stage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author
 * Nicolas Henry  */
public class LevelButton {

    private static final String PICNAME = "buttonLvl.png";
    private static final int WIDTH = 260;
    private static final int HEIGHT = 150;
    private static final int TEXT_OFFSET_X = 40; // to place the text inside the bubble
    private static final int TEXT_OFFSET_Y = 50;
    private String NORESULT = "Aucun resultat";

    private String message;
    private Sprite sprite;
    private BitmapFont font;
    private int lvlid;
    private int result;

    //Generate button for the choice page
    public LevelButton(float x, float y, String message, int lvlid, int result) {
        this.result = result;
        this.message = message;
        sprite = new Sprite(new Texture(PICNAME));
        sprite.setBounds(x, y, WIDTH, HEIGHT);
        font= new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        this.lvlid = lvlid;
    }

    public void draw(Batch batch)
    {
        sprite.draw(batch);
        font.draw(batch, message, sprite.getX()+TEXT_OFFSET_X, sprite.getY()+TEXT_OFFSET_Y+50);

        //Check if the user has a high score
        if (result == 0){
            font.draw(batch, NORESULT, sprite.getX()+TEXT_OFFSET_X, sprite.getY()+TEXT_OFFSET_Y);
        }else {
            font.draw(batch, "Meilleur score : "+result, sprite.getX()+TEXT_OFFSET_X, sprite.getY()+TEXT_OFFSET_Y);
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getLvlid() {
        return lvlid;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
