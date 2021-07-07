package com.max.GravityMaze.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Loading.Paths.SpritePaths;

public class WinScreen implements Screen {
    GravityMazeMain game;
    OrthographicCamera cam;
    Viewport viewport;
    Texture frywinnerBackeround;

    public WinScreen(GravityMazeMain game) {
        this.game = game;
        frywinnerBackeround = game.assMan.get(SpritePaths.FRY_WINNERGF_PATH);
    }



    @Override
    public void show() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(768, 1024, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cam.update();
        viewport.apply();
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.batch.draw(frywinnerBackeround, 0, 0, 768, 1024);
        game.batch.end();
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
