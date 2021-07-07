package com.max.GravityMaze.Tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Loading.Paths.FontPaths;
import com.max.GravityMaze.Loading.Paths.SpritePaths;

import text.formic.Stringf;

public class Hud {
    GravityMazeMain game;
    BitmapFont font;
    OrthographicCamera cam;
    public Viewport viewport;
    public float lvltime = 0f;
    String timeString;


    public Hud(GravityMazeMain game) {
        this.game = game;
        this.font = game.assMan.get(FontPaths.ENCODE_SANS_SC_PATH);
        init();
    }

    public void init(){
        cam = new OrthographicCamera();
        viewport = new FitViewport(400,400, cam);
        cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
        //font.setColor(1,1,1,1);
    }

    public void update(float delta){
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        lvltime += delta;
        timeString = Stringf.format("Time %d:%02d", (int) lvltime /60, (int) lvltime %60);
        font.draw(game.batch, timeString,250,390);
        game.batch.end();
        //note to self. apply viewport and cam after setting projection matrix.
        // tho it doesnt really matter since there is only one batch
        //tutorials show viewport and cam update before setting projection matrix
        viewport.apply();
        cam.update();
    }
}
