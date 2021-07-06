package com.max.GravityMaze.GameStuff;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.max.GravityMaze.Tools.Entity;


public class BallEntity extends Entity {
    private float camRotation;
    private OrthographicCamera cam;

    public BallEntity(float x, float y) {
        super(x, y, 16, 16);
    }


    CollisionFilter ballCollision = new CollisionFilter() {
        @Override
        public Response filter(Item item, Item other) {
            if(other.userData instanceof WallEntity){
                return Response.slide;
            }else if(other.userData instanceof EndpointEntity){
                return Response.cross;
            }
            return null;
        }
    };

    public void setCamRotation(float camRotation) {
        this.camRotation = camRotation;
    }

    public void update(float delta){

    }



}
