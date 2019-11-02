package fr.ul.dedale.model.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.SizeFactory;
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
        camera = new OrthographicCamera(SizeFactory.WIDTH, SizeFactory.HEIGHT);
        camera.setToOrtho(false,SizeFactory.WIDTH ,SizeFactory.HEIGHT);
        Gdx.input.setInputProcessor(new Listener(this));
        camera.update();
        sb.setProjectionMatrix(camera.combined);
    }

    public void render(float delta){
        sb.begin();
        world.draw(sb);
        sb.end();
    }

    public World getWorld() {
        return world;
    }
}
