package fr.ul.dedale.model.View;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;

import java.util.ArrayList;

public class FileChooser extends ScreenAdapter {

    private final int MAX_COLUMNS = 5;
    private final int PAD = 10;
    private final Stage stage;
    public SpriteBatch sb;
    private OrthographicCamera camera;
    private Game game;

    /**
     * Constructor of the FileChooser
     * @param game the game
     */
    public FileChooser(Game game){

        this.game = game;

        sb = new SpriteBatch();
        camera = new OrthographicCamera(LabyrinthFactory.WIDTH, LabyrinthFactory.HEIGHT);
        camera.setToOrtho(false, LabyrinthFactory.WIDTH , LabyrinthFactory.HEIGHT);

        camera.update();
        sb.setProjectionMatrix(camera.combined);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Display the view
     * @param delta the delta time
     */
    public void render(float delta){
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        sb.begin();
        stage.draw();
        sb.end();
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        int nbLevelDebloque = game.getWorld().getLastLevel();

        //create buttons
        int nbColumns = 1;
        table.row().pad(PAD, PAD, PAD, PAD);
        for (int i = 0; i < LabyrinthFactory.NB_LEVEL; i++) {
            if (nbColumns > MAX_COLUMNS) {
                table.row().pad(PAD, PAD, PAD, PAD);
                nbColumns = 1;
            }
            final int level = i + 1;
            TextButton textButton = new TextButton("" + level, skin);
            //add buttons to table
            table.add(textButton).fillX().uniformX();

            textButton.addListener(new InputListener() {

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.getSound().stop();
                    game.setScreen(new ViewWorld(game, level));
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            if (level > nbLevelDebloque) {
                textButton.setTouchable(Touchable.disabled);
                textButton.setDisabled(true);
            }
            nbColumns++;




        }

        Table tableMenu = new Table();
        tableMenu.setFillParent(true);
        stage.addActor(tableMenu);
        TextButton menu = new TextButton("Menu", skin);
        menu.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ViewMenu(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        //add buttons to table
        tableMenu.row().pad(LabyrinthFactory.SCREEN_WIDTH - 100, 0, 0, 0);
        tableMenu.add(menu).center();
    }

}
