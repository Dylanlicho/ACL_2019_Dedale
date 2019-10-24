package fr.ul.dedale.model.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.controller.Listener;
import fr.ul.dedale.model.World;

public class ViewWorld extends ScreenAdapter {
    public SpriteBatch sb;
    public OrthographicCamera camera;
    private World world;
    public ViewWorld(){
        world = new World();
        sb = new SpriteBatch();
        camera = new OrthographicCamera(50, 30);
        camera.setToOrtho(false,50 ,30);
        Gdx.input.setInputProcessor(new Listener(this));
        camera.update();
        sb.setProjectionMatrix(camera.combined);
    }

    public void render(float delta){
        sb.begin();
        sb.draw(TextureFactory.getInstance().getImage("grass"), 0,0, 50, 50 );
        world.draw(sb);
        sb.end();
    }

    public World getWorld() {
        return world;
    }
}
