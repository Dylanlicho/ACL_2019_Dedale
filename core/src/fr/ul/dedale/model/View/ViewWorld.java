package fr.ul.dedale.model.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.controller.Listener;
import fr.ul.dedale.model.World;

public class ViewWorld extends ScreenAdapter {

    public SpriteBatch sb;
    public OrthographicCamera camera;
    private World world;



    public ViewWorld(Game game){
        world = new World(game);
        sb = new SpriteBatch();
        camera = new OrthographicCamera(LabyrinthFactory.WIDTH, LabyrinthFactory.HEIGHT);
        camera.setToOrtho(false, LabyrinthFactory.WIDTH , LabyrinthFactory.HEIGHT);
        Gdx.input.setInputProcessor(new Listener(this));
        camera.update();
        sb.setProjectionMatrix(camera.combined);
    }

    public void render(float delta){
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );



        sb.begin();
        world.draw(sb);
        // Display of the HUD
        int posX = 2;
        int posY = 20;
        for (int i = 0; i < getWorld().getHero().getHp(); i++) {
            sb.draw(TextureFactory.getInstance().getImage("life"), i * posX, posY, 2, 2);
        }
        sb.end();

    }



    public World getWorld() {
        return world;
    }
}
