package fr.ul.dedale.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.model.View.ViewWorld;
import fr.ul.dedale.model.World;

public class Listener implements InputProcessor {
    ViewWorld viewWorld ;
    Boolean turnFirstPress = false;

    public Listener(ViewWorld vWorld){
        this.viewWorld = vWorld;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(viewWorld.getWorld().getHero().getHp()>0) {
            if (turnFirstPress==true){
                if (keycode == Input.Keys.UP) {
                   viewWorld.getWorld().getHero().turn(DirectionFactory.TURNTOP);
                    return true;
                }
                if (keycode == Input.Keys.RIGHT) {
                    viewWorld.getWorld().getHero().turn(DirectionFactory.TURNRIGHT);
                    return true;
                }

                if (keycode == Input.Keys.LEFT) {
                    viewWorld.getWorld().getHero().turn(DirectionFactory.TURNLEFT);
                    return true;

                }


                if (keycode == Input.Keys.DOWN) {
                    viewWorld.getWorld().getHero().turn(DirectionFactory.TURNBOTTOM);
                    return true;

                }
            }
            if (keycode==Input.Keys.C) {
                turnFirstPress = true;
                return true;
            }



            if (keycode == Input.Keys.ESCAPE) {
                Gdx.app.exit();
            }

            if (keycode == Input.Keys.RIGHT) {
                viewWorld.getWorld().moveHero(DirectionFactory.RIGHT);
            }

            if (keycode == Input.Keys.LEFT) {
                viewWorld.getWorld().moveHero(DirectionFactory.LEFT);
            }
            if (keycode == Input.Keys.UP) {
                viewWorld.getWorld().moveHero(DirectionFactory.TOP);
            }

            if (keycode == Input.Keys.DOWN) {
                viewWorld.getWorld().moveHero(DirectionFactory.BOTTOM);
            }
        }
        viewWorld.getWorld().game();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode==Input.Keys.C) {
            turnFirstPress = false;
            return true;
        }


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
