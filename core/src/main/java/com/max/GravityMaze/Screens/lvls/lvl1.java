package com.max.GravityMaze.Screens.lvls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Loading.Paths.SpritePaths;
import com.max.GravityMaze.Loading.Paths.TiledMapPaths;
import com.max.GravityMaze.Tools.MapBase;
import com.max.GravityMaze.Tools.MapParser;

public class lvl1 extends MapBase implements Screen {


    public lvl1(GravityMazeMain game) {
        this.game = game;
        map = game.assMan.get(TiledMapPaths.LVL1_PATH);
        start = new Sprite((Texture) game.assMan.get(SpritePaths.START_PATH));
        start.setScale(.5f);
        //start.setOrigin(0,0);
        end = new Sprite((Texture) game.assMan.get(SpritePaths.END_PATH));
        end.setScale(.5f);
        //end.setOrigin(0,0);
    }


    @Override
    public void show() {
        init();
    }

    float time = 0f;
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
