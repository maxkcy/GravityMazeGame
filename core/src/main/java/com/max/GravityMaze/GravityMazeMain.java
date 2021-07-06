package com.max.GravityMaze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.max.GravityMaze.Loading.Loader;
import com.max.GravityMaze.Screens.SplashScreen;
import com.max.GravityMaze.Screens.lvls.lvl1;

public class GravityMazeMain extends Game {
    public SpriteBatch batch;
    public AssetManager assMan;
    public Loader loader;
    public SplashScreen splashScreen;
    public lvl1 lvl1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assMan = new AssetManager();
        splashScreen = new SplashScreen(this);
        loader = new Loader(this.assMan);

        Gdx.app.postRunnable(()-> setScreen(splashScreen));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assMan.dispose();
        splashScreen.dispose();
        super.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}