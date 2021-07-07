package com.max.GravityMaze.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Rect;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.World;
import com.max.GravityMaze.GameStuff.BallEntity;
import com.max.GravityMaze.GameStuff.EndpointEntity;
import com.max.GravityMaze.GameStuff.WallEntity;

public class Ball{
    Vector2 position = new Vector2();
    public Item<Entity> ball;
    protected World world;
    protected ShapeRenderer shapeRenderer;
    protected Vector2 acceleration = new Vector2();
    public Vector2 velocity = new Vector2();
    protected OrthographicCamera cam;
    public CollisionFilter collisionFilter;

    public Ball(float x, float y, World world, ShapeRenderer shapeRenderer, OrthographicCamera cam) {
        position.x = x;
        position.y = y;
        ball = new Item(new BallEntity(position.x, position.y));
        this.world = world;
        world.add(ball, ball.userData.position.x, ball.userData.position.y, ball.userData.width, ball.userData.height);
        this.shapeRenderer = shapeRenderer;
        velocity.x = 0f;
        velocity.y = 0f;
        this.cam = cam;
    }

    public void shapeRender(){
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(world.getRect(ball).x + world.getRect(ball).w/2, world.getRect(ball).y + world.getRect(ball).h/2,
                world.getRect(ball).w/2);
    }

    public void update(float camRotation){
        acceleration.x = MathUtils.cosDeg(camRotation - 90) * .01f;
        acceleration.y = MathUtils.sinDeg(camRotation - 90) * .01f;
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
        world.move(ball, world.getRect(ball).x  + velocity.x, world.getRect(ball).y + velocity.y, collisionFilter);
        if (world.getRect(ball).x == ball.userData.position.x){
            velocity.x = 0f;
        }
        if(world.getRect(ball).y == ball.userData.position.y){
            velocity.y = 0f;
        }
        ball.userData.position.x = world.getRect(ball).x;
        ball.userData.position.y = world.getRect(ball).y;

        handleInput();
    }
    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            cam.rotate(1f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            cam.rotate(-1f);
        }
    }

}
