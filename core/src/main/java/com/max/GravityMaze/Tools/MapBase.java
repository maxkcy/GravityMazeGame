package com.max.GravityMaze.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Rect;
import com.dongbat.jbump.World;
import com.max.GravityMaze.GameStuff.EndpointEntity;
import com.max.GravityMaze.GravityMazeMain;
import com.max.GravityMaze.Loading.Paths.SpritePaths;

public class MapBase {
   protected GravityMazeMain game;
   protected TiledMap map;
   protected OrthogonalTiledMapRenderer mapRenderer;
   protected Array<Item> wallArray;
   protected Array<Item> endpointArray;
   protected ShapeRenderer shapeRenderer;
   protected OrthographicCamera cam;
   protected FitViewport viewport;
   protected World<Entity> world;
   protected Sprite start;
   protected Sprite end;
   protected MapParser mapParser;
   protected float time = 0f;
   float angle;
   boolean cameraTransitioned;
   //Array<Transitions> transitionsArray;


   protected void init(){
       mapRenderer = new OrthogonalTiledMapRenderer(map);
       wallArray = new Array<>();
       endpointArray = new Array<>();
       shapeRenderer = new ShapeRenderer();
       cam = new OrthographicCamera();
       viewport = new FitViewport(800,800, cam);
       cam.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, 0);
       world = new World<>(8);

       mapParser = new MapParser(map, world, wallArray, endpointArray);
       mapParser.parse();

       for (Item item : endpointArray) {
           if (item != null) {
               Rect rect = world.getRect(item);
               switch (((EndpointEntity)item.userData).getEndpoint()){
                   case ("end"): {
                       end.setPosition(rect.x - end.getWidth()/2, rect.y - end.getHeight()/2);
                       break;
                   }
                   case ("start"):{
                       start.setPosition(rect.x - start.getWidth()/2, rect.y - start.getHeight()/2);
                       break;
                   }
                   default:{
                       Gdx.app.error(this.toString(), "endpointArray item case not found");
                   }
               }
           }else{Gdx.app.error(this.toString(), "item is null in endpointArray");}
       }

       angle = MathUtils.atan2(start.getY() + start.getHeight()/2 - cam.position.y, start.getX() + start.getWidth()/2 - cam.position.x)
               * MathUtils.radiansToDegrees;
       angle = (((angle % 360) + 360) % 360);

       /*transitionsArray = new Array<>();
       transitionsArray.add(new OpeningTransition());*/

   }

   protected void update(float delta){
       cam.update();
       viewport.apply();  // because camera can have many viewports
       mapRenderer.setView((OrthographicCamera) cam);
       mapRenderer.render();

       viewport.apply();
       shapeRenderer.setProjectionMatrix(cam.combined);
       shapeRenderer.setAutoShapeType(true);
       shapeRenderer.begin();
       shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
       for (Item item : wallArray) {
           if (item != null) {
               shapeRenderer.setColor(Color.WHITE);
               Rect rect = world.getRect(item);
               shapeRenderer.rect(rect.x, rect.y, rect.w, rect.h);
           }else{Gdx.app.error(this.toString(), "item is null in wall array");}
       }
       shapeRenderer.end();

       viewport.apply();
       game.batch.setProjectionMatrix(cam.combined);
       game.batch.begin();
       for (Item item : endpointArray) {
           if (item != null) {
               Rect rect = world.getRect(item);
               switch (((EndpointEntity)item.userData).getEndpoint()){
                   case ("end"): {
                       end.setPosition(rect.x - end.getWidth()/2, rect.y - end.getHeight()/2);
                       end.draw(game.batch);
                       break;
                   }
                   case ("start"):{
                       start.setPosition(rect.x - start.getWidth()/2, rect.y - start.getHeight()/2);
                       start.draw(game.batch);
                       break;
                   }
                   default:{
                       Gdx.app.error(this.toString(), "endpointArray item case not found");
                   }
               }
           }else{Gdx.app.error(this.toString(), "item is null in endpointArray");}
       }
       game.batch.end();
        openingTransition(delta);
       /*for (Transitions transition: transitionsArray) {
            if (transition instanceof OpeningTransition){
                transition.TransitionMethod(delta, cam, viewport, start, angle);
                if(((OpeningTransition)transition).cameraTransitioned == true && time >= 5f){
                transitionsArray.removeValue(transition, true);
                }
            }
       }*/
   }

   public void openingTransition(float delta){
       time += delta;
       if ( time > 1.69f) {
           if (viewport.getWorldWidth() > 200) {
               viewport.setWorldWidth(viewport.getWorldWidth() - 5);
           }
           if (viewport.getWorldHeight() > 200) {
               viewport.setWorldHeight(viewport.getWorldHeight() - 5);
           }
           if(!cameraTransitioned) {
               if ((start.getX() + start.getWidth() / 2) - cam.position.x > 6 || start.getX() - cam.position.x < -6
                       && start.getY() - cam.position.y > 6 || start.getY() - cam.position.y < -6) {
                   cam.translate(MathUtils.cosDeg(angle) * 5, MathUtils.sinDeg(angle) * 5);
               } else {
                   cam.position.set(start.getX() + start.getWidth() / 2, start.getY() + start.getHeight() / 2, 0);
                   cameraTransitioned = true;
               }
           }
       }
   }
}
