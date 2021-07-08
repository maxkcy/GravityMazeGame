package com.max.GravityMaze.Screens.lvls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.max.GravityMaze.GameStuff.BallEntity;
import com.max.GravityMaze.GameStuff.EndpointEntity;
import com.max.GravityMaze.GameStuff.WallEntity;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Loading.Paths.SpritePaths;
import com.max.GravityMaze.Loading.Paths.TiledMapPaths;
import com.max.GravityMaze.Tools.Hud;
import com.max.GravityMaze.Tools.MapBase;
import com.max.GravityMaze.Tools.MapParser;

public class lvl1 extends MapBase implements Screen {

    public OrthographicCamera bkgrndTestCam;
    public Viewport bkgrndTestViewP;
    public Texture jamesBkgrnd;


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
        //dif cam and vp
        bkgrndTestCam = new OrthographicCamera();


        jamesBkgrnd = game.assMan.get(SpritePaths.JAMES_STAR_BKGRND);

        init();
        bkgrndTestViewP = new FitViewport(800, 800, bkgrndTestCam);
        bkgrndTestCam.position.set(bkgrndTestViewP.getWorldWidth()/2, bkgrndTestViewP.getWorldHeight()/2, 0);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        bkgrndTestViewP.setWorldSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        bkgrndTestViewP.apply();
        //bkgrndTestCam.position.set(world.getRect(ball.ball).x + 8, world.getRect(ball.ball).y + 8, 0);


        bkgrndTestCam.position.set(bkgrndTestViewP.getWorldWidth()/2, bkgrndTestViewP.getWorldHeight()/2, 0);
        float camsrotation = -(float)Math.atan2(cam.up.x, cam.up.y)* MathUtils.radiansToDegrees;
        //System.out.println(camsrotation);
        float bkcamrotation = -(float)Math.atan2(bkgrndTestCam.up.x, bkgrndTestCam.up.y)* MathUtils.radiansToDegrees;
        bkgrndTestCam.rotate(-(camsrotation - bkcamrotation));
        bkgrndTestCam.update();
        cam.update();
        game.batch.setProjectionMatrix(bkgrndTestCam.combined);
        game.batch.begin();
        game.batch.draw(jamesBkgrnd,bkgrndTestViewP.getWorldWidth()/2 - 500 * 3,bkgrndTestViewP.getWorldHeight()/2 - 500 *3 ,  1000 * 3, 1000 * 3);
        game.batch.end();



        viewport.apply();
        update(delta);
        checkballspeed();

        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            game.lvl1 = new lvl1(game);
            Gdx.app.postRunnable(()-> game.setScreen(game.lvl1));
        }
        /*if (Gdx.input.isKeyPressed(Input.Keys.T)){
            world.update(ball.ball, 545, 330, 16, 16);
        }*/
    }

    @Override
    public void resize(int width, int height) {
        bkgrndTestViewP.update(width, height);
        viewport.update(width, height);
        hud.viewport.update(width,height);

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
        super.dispose();
    }

    public void checkballspeed(){
        if (Math.abs(ball.velocity.x) > 3.8f || Math.abs(ball.velocity.y) > 3.8f){
            ball.collisionFilter = ballCollision2;
            Gdx.app.log(this.toString(), "ball is a bullet about to smash");
        }else { ball.collisionFilter = ballCollision;}
    }

    public CollisionFilter ballCollision = new CollisionFilter() {
        @Override
        public Response filter(Item item, Item other) {
            if(other.userData instanceof WallEntity){
                return Response.slide;
            }else if(other.userData instanceof EndpointEntity){
                Gdx.app.log(this.toString(), "touched endpoint");
                if(((EndpointEntity) other.userData).getEndpoint().equals("end")){
                    Gdx.app.log(this.toString(),"You win!");
                    Gdx.app.postRunnable(() -> game.setScreen(game.winScreen));
                }
                return Response.touch;
            }
            return null;
        }
    };

    public CollisionFilter ballCollision2 = new CollisionFilter() {
        @Override
        public Response filter(Item item, Item other) {
            if(other.userData instanceof WallEntity){
                return Response.cross;
            }else if(other.userData instanceof EndpointEntity){
                Gdx.app.log(this.toString(), "touched endpoint");
                return Response.touch;
            }
            return null;
        }
    };
}
