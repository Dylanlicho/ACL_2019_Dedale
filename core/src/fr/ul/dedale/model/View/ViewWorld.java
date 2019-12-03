package fr.ul.dedale.model.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.controller.Listener;
import fr.ul.dedale.model.World;


public class ViewWorld extends ScreenAdapter {

    public SpriteBatch sb;
    public OrthographicCamera camera;
    private Game game;

    /**
     * Constructor of the view of the world
     * @param game the game
     */
    public ViewWorld(Game game){
        this.game = game;
        create();
    }

    /**
     * Constructor of view of the world at a level
     * @param game the game
     * @param level the level where start
     */
    public ViewWorld(Game game, int level){
        this.game = game;
        game.getWorld().setLevel(level);
        getWorld().setLevel(level);
        getWorld().createLevel();
        create();
    }

    private void create() {
        sb = new SpriteBatch();
        camera = new OrthographicCamera(LabyrinthFactory.WIDTH, LabyrinthFactory.HEIGHT);
        camera.setToOrtho(false, LabyrinthFactory.WIDTH , LabyrinthFactory.HEIGHT);
        Gdx.input.setInputProcessor(new Listener(this,game));
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        game.getWorld().begin();
    }

    /**
     * Display the view
     * @param delta the delta time
     */
    public void render(float delta){
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        sb.begin();
        getWorld().draw(sb);
        // Display of the HUD
        int posX = 2;
        int posY = 20;
        for (int i = 0; i < getWorld().getHero().getHp(); i++) {
            sb.draw(TextureFactory.getInstance().getImage("life"), i * posX, posY, 2, 2);
        }
        for (int i = 0; i < getWorld().getHero().getNumberArrow(); i++) {
            sb.draw(TextureFactory.getInstance().getImage("nbRow"), 10+ i * posX, posY , 2, 2);
        }
        if(getWorld().isSaving()) {
            sb.draw(TextureFactory.getInstance().getImage("save"), 18, posY, 2, 2);
        }
        if (getWorld().isCurrentLevelFinish()) {
            if (getWorld().getLevel() <= LabyrinthFactory.NB_LEVEL)
                sb.draw(TextureFactory.getInstance().getImage("win"), 4, 5, 14,10);
            else
                sb.draw(TextureFactory.getInstance().getImage("menu"), 4, 5, 14,10);
        }
        sb.end();
    }

    /**
     * getter of the world
     * @return the world
     */
    public World getWorld() {
        return game.getWorld();
    }
}
