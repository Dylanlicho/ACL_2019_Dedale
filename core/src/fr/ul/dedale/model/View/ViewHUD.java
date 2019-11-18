package fr.ul.dedale.model.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.World;

public class ViewHUD extends ScreenAdapter {

    private final Stage stage;
    public SpriteBatch sb;
    public OrthographicCamera camera;
    private Game game;

    public ViewHUD(Game game, World world){
        this.game = game;
        sb = new SpriteBatch();
        camera = new OrthographicCamera(2, 20);
        camera.setToOrtho(false, 2, 20);
        //Gdx.input.setInputProcessor(new Listener(this));
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta){
        sb.begin();
        stage.draw();
        sb.end();

    }

}
