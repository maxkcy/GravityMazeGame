package com.max.GravityMaze.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Screens.lvls.lvl1;

public class SplashScreen extends ScreenAdapter {
    public GravityMazeMain game;

    public SplashScreen(GravityMazeMain game) {
        this.game = game;
    }

    public OrthographicCamera cam;
    public Viewport viewport;

    public Sprite splash;
    public Sprite loadingBarBack;
    public Sprite loadingBarFront;

    @Override
    public void show() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(720, 1020, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        splash = new Sprite(new Texture(Gdx.files.internal("sprites/splash.png")));
        loadingBarBack = new Sprite(new Texture(Gdx.files.internal("sprites/loadingbarback.png")));
        loadingBarFront = new Sprite(new Texture(Gdx.files.internal("sprites/loadingbarfront.png")));
        splash.setBounds(0, 100,720, 920);
        loadingBarBack.setBounds(0,0, 720, 100);
        loadingBarFront.setBounds(0,0, 720, 100);
        game.loader.loadMapPaths();
        game.loader.loadSpritePaths();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        game.batch.setProjectionMatrix(cam.combined);
        cam.update();
        game.batch.begin();
        splash.draw(game.batch);
        loadingBarBack.draw(game.batch);
        loadingBarFront.setSize(720 * game.assMan.getProgress(), 100);
        loadingBarFront.setRegionWidth((int) (400 * game.assMan.getProgress()));
        //loadingBarFront.setRegionHeight(40);
        //loadingBarFront.setRegion(0,0, ((int)400*game.assMan.getProgress()), 40);
        loadingBarFront.draw(game.batch);
        game.batch.end();
        game.loader.updateLoader();
        Gdx.app.log(this.toString(),  "" + 100 * game.assMan.getProgress());
        if (game.assMan.isFinished()){
            game.winScreen = new WinScreen(game);
            game.lvl1 = new lvl1(game);
            Gdx.app.postRunnable(() -> game.setScreen(game.lvl1));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }



    @Override
    public void dispose() {
        splash.getTexture().dispose();
        loadingBarBack.getTexture().dispose();
        loadingBarFront.getTexture().dispose();
    }
}
