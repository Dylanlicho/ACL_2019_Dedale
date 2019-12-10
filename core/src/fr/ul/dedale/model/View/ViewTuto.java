package fr.ul.dedale.model.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.DataFactory.TextureFactory;
import fr.ul.dedale.Game;

public class ViewTuto extends ScreenAdapter {

    private final Stage stage;
    public SpriteBatch sb;
    public OrthographicCamera camera;
    //    private World world;
    private Game game;

    public ViewTuto(Game game){

        this.game = game;

//        world = new World(game);
        sb = new SpriteBatch();
        camera = new OrthographicCamera(LabyrinthFactory.WIDTH, LabyrinthFactory.HEIGHT);
        camera.setToOrtho(false, LabyrinthFactory.WIDTH , LabyrinthFactory.HEIGHT);
        //Gdx.input.setInputProcessor(new Listener(this));
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void render(float delta){
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        sb.begin();
        stage.draw();
        Texture texture = TextureFactory.getInstance().getImage("tuto");
        sb.draw(texture, 2, LabyrinthFactory.HEIGHT - 4, 7, 4, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        sb.end();
    }

    @Override
    public void show() {
        super.show();

        // temporary until we have asset manager in
        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        // Create a table that fills the screen. Everything else will go inside this table.
        Table tableTitle = new Table();
        tableTitle.setFillParent(true);
        stage.addActor(tableTitle);

        Image title = new Image(TextureFactory.getInstance().getImage("howtitle"));
        tableTitle.row().pad(LabyrinthFactory.SCREEN_WIDTH - 1000, 0, 0, 0);
        tableTitle.add(title).top();

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Image move = new Image(TextureFactory.getInstance().getImage("tuto"));
        table.row().pad(LabyrinthFactory.SCREEN_WIDTH - 900, 30, 0, 0);
        table.add(move);//.width(LabyrinthFactory.SCREEN_WIDTH-40);

        Image turn = new Image(TextureFactory.getInstance().getImage("howturn"));
        table.add(turn);

        Table tableHit = new Table();
        tableHit.setFillParent(true);
        stage.addActor(tableHit);

        Image hit = new Image(TextureFactory.getInstance().getImage("howhit"));
        tableHit.row().pad(LabyrinthFactory.SCREEN_WIDTH - 600, 10, 0, 0);
        tableHit.add(hit);//.width(LabyrinthFactory.SCREEN_WIDTH-40);

        Image arrow = new Image(TextureFactory.getInstance().getImage("howarrow"));
        tableHit.add(arrow);

        Table table2 = new Table();
        table2.setFillParent(true);
        stage.addActor(table2);

        Image save = new Image(TextureFactory.getInstance().getImage("howsave"));
        table2.row().pad(LabyrinthFactory.SCREEN_WIDTH - 400, 30, 0, 0);
        table2.add(save);//.width(LabyrinthFactory.SCREEN_WIDTH-40);

        Image returnMenu = new Image(TextureFactory.getInstance().getImage("howmenu"));
        table2.add(returnMenu);


        Table tableMenu = new Table();
        tableMenu.setFillParent(true);
        stage.addActor(tableMenu);

        tableMenu.row().pad(LabyrinthFactory.SCREEN_WIDTH - 100, 0, 10, 0);
        TextButton menu = new TextButton("Menu", skin);
        tableMenu.add(menu).center();
        tableMenu.row();

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
    }
}
