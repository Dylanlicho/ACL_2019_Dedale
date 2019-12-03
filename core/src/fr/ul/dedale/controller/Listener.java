package fr.ul.dedale.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import fr.ul.dedale.DataFactory.DirectionFactory;
import fr.ul.dedale.DataFactory.LabyrinthFactory;
import fr.ul.dedale.Game;
import fr.ul.dedale.model.View.ViewMenu;
import fr.ul.dedale.model.View.ViewWorld;

public class Listener implements InputProcessor {
    ViewWorld viewWorld ;
    Boolean turnFirstPress = false;
    private Music mp3Sound;
    private Music mp3SoundArrow;
    private Game game;

    public Listener(ViewWorld vWorld, Game game){
        this.game = game;
        this.viewWorld = vWorld;
        mp3Sound = Gdx.audio.newMusic(Gdx.files.internal("audio/sword.wav"));
        mp3SoundArrow = Gdx.audio.newMusic(Gdx.files.internal("audio/sword.wav"));
    }

    @Override
    public boolean keyDown(int keycode) {
        if (viewWorld.getWorld().isBegin()) {
            if (viewWorld.getWorld().getHero().getHp() > 0 && !viewWorld.getWorld().isCurrentLevelFinish()) {
                if (turnFirstPress == true) {
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
                if (keycode == Input.Keys.C) {
                    turnFirstPress = true;
                    return true;
                }

                if (keycode == Input.Keys.SPACE) {
                    viewWorld.getWorld().getHero().attackSword(viewWorld.getWorld());
                    viewWorld.getWorld().getHero().hit();
                    mp3Sound.play();
                }

            if(keycode== Input.Keys.F){
                if(viewWorld.getWorld().getHero().getNumberArrow() > 0 ) {
                    viewWorld.getWorld().getHero().attackArrow(viewWorld.getWorld());
                    viewWorld.getWorld().getHero().hit();
                    mp3SoundArrow.play();
                }
            }

            if (keycode == Input.Keys.ESCAPE) {
                viewWorld.getWorld().stopThread();
                game.setScreen(new ViewMenu(game));
            }
            if (keycode == Input.Keys.S) {
                viewWorld.getWorld().save();
            }

                if (keycode == Input.Keys.L) {
                    viewWorld.getWorld().load();
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
        }

        if( viewWorld.getWorld().isCurrentLevelFinish() && viewWorld.getWorld().isBegin()) {
            if (keycode == Input.Keys.ENTER) {
                if (viewWorld.getWorld().getLevel() <= LabyrinthFactory.NB_LEVEL)
                    viewWorld.getWorld().createLevel();
                else
                    viewWorld.getWorld().menuReturn();
//                return true;
            }

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (viewWorld.getWorld().isBegin()) {
            if (keycode == Input.Keys.SPACE) {
               // viewWorld.getWorld().getHero().nohit();

            }
            if (keycode == Input.Keys.C) {
                turnFirstPress = false;
                return true;
            }
        if(keycode== Input.Keys.F){

          //  viewWorld.getWorld().getHero().nohit();

        }

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if( viewWorld.getWorld().isCurrentLevelFinish() && viewWorld.getWorld().isBegin()) {
                if (button == Input.Buttons.LEFT) {
                    if (viewWorld.getWorld().getLevel() <= LabyrinthFactory.NB_LEVEL)
                        viewWorld.getWorld().createLevel();
                    else
                        viewWorld.getWorld().menuReturn();
                    return true;
                }

        }
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
